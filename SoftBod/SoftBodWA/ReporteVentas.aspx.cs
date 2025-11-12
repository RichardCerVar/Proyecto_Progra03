using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web.UI;
using SoftBodBusiness;
using SoftBodBusiness.ReporteDTO;

using WSVenta = SoftBodBusiness.SoftWSVenta;
using WSDevolucion = SoftBodBusiness.SoftWSDevolucion;
using WSAlFiado = SoftBodBusiness.SoftWSVentaAlFiado;
using WSDetalle = SoftBodBusiness.SoftWSDetalleVenta;
using WSDetalleDev = SoftBodBusiness.SoftWSDetalleDevolucion;
using WSProd = SoftBodBusiness.SoftWSProducto;

namespace SoftBodWA
{
    public partial class ReporteVentas : Page
    {
        private VentaBO ventaBO = new VentaBO();
        private DevolucionBO devolucionBO = new DevolucionBO();
        private VentaAlFiadoBO ventaAlFiadoBO = new VentaAlFiadoBO();
        private DetalleVentaBO detalleVentaBO = new DetalleVentaBO();
        private DetalleDevolucionBO detalleDevolucionBO = new DetalleDevolucionBO();
        private ProductoBO productoBO = new ProductoBO();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                txtFecha.Text = DateTime.Now.ToString("yyyy-MM-dd");
                CargarDatosReporte();
            }
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            CargarDatosReporte();
        }

        private void CargarDatosReporte()
        {
            if (string.IsNullOrEmpty(txtFecha.Text))
            {
                ClientScript.RegisterStartupScript(GetType(), "alert", "alert('Debe seleccionar una fecha.');", true);
                return;
            }

            string fechaFiltro = DateTime.Parse(txtFecha.Text).ToString("yyyy-MM-dd");
            var movimientosReporteVacio = new List<MovimientoReporteDTO>();
            
            try
            {
                List<WSVenta.ventaDTO> listaVentas = ventaBO.listarVentasPorFecha(fechaFiltro);
                List<WSDevolucion.devolucionDTO> listaDevoluciones = devolucionBO.listarDevolucionesPorFecha(fechaFiltro);

                var movimientosReporte = ConsolidarMovimientos(listaVentas, listaDevoluciones);

                gvDetalleMovimientos.DataSource = movimientosReporte
                    .OrderByDescending(m => m.Fecha)
                    .ThenByDescending(m => m.Hora)
                    .ToList();

                CalcularResumen(movimientosReporte);

                if (movimientosReporte.Count == 0 && (listaVentas.Count > 0 || listaDevoluciones.Count > 0))
                {
                    // Este caso puede indicar un error en ConsolidarMovimientos o no hay datos para esa fecha.
                    // Si el BO devolvió listas vacías por fallo de conexión, el resumen será 0.00, lo cual es correcto.
                }
            }
            catch (Exception ex)
            {
                string mensajeError = $"Error al cargar el reporte (Detalles: {ex.Message}). Se mostrará un reporte vacío.";
                ClientScript.RegisterStartupScript(GetType(), "error", $"alert('{mensajeError}');", true);

                gvDetalleMovimientos.DataSource = movimientosReporteVacio;
                CalcularResumen(movimientosReporteVacio);
            }
            finally
            {
                gvDetalleMovimientos.DataBind();
            }
        }

        private List<MovimientoReporteDTO> ConsolidarMovimientos(List<WSVenta.ventaDTO> ventas, List<WSDevolucion.devolucionDTO> devoluciones)
        {
            var movimientos = new List<MovimientoReporteDTO>();

            // --- PROCESAR VENTAS ---
            if (ventas != null)
            {
                foreach (var venta in ventas)
                {
                    List<WSDetalle.detalleVentaDTO> detalles = detalleVentaBO.listarDetallesVentaPorVenta(venta.ventaId);

                    string nombreCliente = "Cliente Genérico";
                    bool esFiado = venta.metodoPago.ToString().ToLower().Contains("fiado");

                    if (esFiado)
                    {
                        WSAlFiado.ventaAlFiadoDTO ventaFiado = ventaAlFiadoBO.obtenerVentaAlFiadoPorId(venta.ventaId);
                        if (ventaFiado != null && ventaFiado.cliente != null)
                        {
                            nombreCliente = ventaFiado.cliente.alias;
                        }
                    }

                    movimientos.Add(new MovimientoReporteDTO
                    {
                        ID = venta.ventaId,
                        Fecha = DateTime.Parse(venta.fecha),
                        Hora = DateTime.Parse(venta.fecha).ToString("HH:mm:ss"),
                        Tipo = "Venta",
                        Cliente = nombreCliente,
                        TipoPago = venta.metodoPago.ToString(),
                        Total = venta.total,
                        ProductosResumen = GenerarResumenProductos(detalles.Cast<object>().ToList(), "Venta"),
                        EsFiado = esFiado
                    });
                }
            }
            // --- PROCESAR DEVOLUCIONES ---
            if (devoluciones != null)
            {
                foreach (var devolucion in devoluciones)
                {
                    List<WSDetalleDev.detalleDevolucionDTO> detalles = detalleDevolucionBO.listarDetallesDevolucionPorDevolucion(devolucion.devolucionId);

                    movimientos.Add(new MovimientoReporteDTO
                    {
                        ID = devolucion.devolucionId,
                        Fecha = DateTime.Parse(devolucion.fecha),
                        Hora = DateTime.Parse(devolucion.fecha).ToString("HH:mm:ss"),
                        Tipo = "Devolución",
                        Cliente = devolucion.usuario != null ? devolucion.usuario.nombre : "N/A",
                        TipoPago = "Contado",
                        Total = devolucion.total,
                        ProductosResumen = GenerarResumenProductos(detalles.Cast<object>().ToList(), "Devolucion"),
                        EsFiado = false
                    });
                }
            }
            return movimientos;
        }

        private string GenerarResumenProductos(List<object> detalles, string tipo)
        {
            var resumen = new StringBuilder();

            if (tipo == "Venta")
            {
                foreach (WSDetalle.detalleVentaDTO detalle in detalles.Cast<WSDetalle.detalleVentaDTO>())
                {
                    WSProd.productoDTO producto = productoBO.obtenerProductoPorId(detalle.producto.productoId);
                    if (producto != null)
                    {
                        resumen.Append($"{producto.nombre} (x{detalle.cantidad}) - S/.{detalle.subtotal:N2}<br/>");
                    }
                }
            }
            else if (tipo == "Devolucion")
            {
                foreach (WSDetalleDev.detalleDevolucionDTO detalle in detalles.Cast<WSDetalleDev.detalleDevolucionDTO>())
                {
                    WSProd.productoDTO producto = productoBO.obtenerProductoPorId(detalle.producto.productoId);
                    if (producto != null)
                    {
                        resumen.Append($"{producto.nombre} (x{detalle.cantidad}) - S/.{detalle.subtotal:N2}<br/>");
                    }
                }
            }
            return resumen.ToString();
        }

        private void CalcularResumen(List<MovimientoReporteDTO> movimientos)
        {
            double ventasTotales = movimientos.Where(m => m.Tipo == "Venta").Sum(m => m.Total);
            double devolucionesTotales = movimientos.Where(m => m.Tipo == "Devolución").Sum(m => m.Total);
            double ventasNetas = ventasTotales - devolucionesTotales;

            lblVentasTotales.Text = string.Format("{0:N2}", ventasTotales);
            lblDevoluciones.Text = string.Format("{0:N2}", devolucionesTotales);
            lblVentasNetas.Text = string.Format("{0:N2}", ventasNetas);
        }

        protected void btnExportarReporte_Click(object sender, EventArgs e)
        {
            ClientScript.RegisterStartupScript(GetType(), "export", "alert('Funcionalidad de exportar reporte a PDF pendiente de implementación en el Back-End.');", true);
        }
    }
}