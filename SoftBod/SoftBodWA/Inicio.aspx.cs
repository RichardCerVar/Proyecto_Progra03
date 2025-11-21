using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBodBusiness;
using WSVenta = SoftBodBusiness.SoftWSVenta;
using WSVentaAlFiado = SoftBodBusiness.SoftWSVentaAlFiado;
using WSDevolucion = SoftBodBusiness.SoftWSDevolucion;
using WSRegistroPagoFiado = SoftBodBusiness.SoftWSRegistroPagoFiado;
using WSDetalleVenta = SoftBodBusiness.SoftWSDetalleVenta;
using WSDetalleDevolucion = SoftBodBusiness.SoftWSDetalleDevolucion;
using WSProducto = SoftBodBusiness.SoftWSProducto;
using WSClienteAlFiado = SoftBodBusiness.SoftWSClienteAlFiado;

namespace SoftBodWA
{
    public partial class Inicio : System.Web.UI.Page
    {
        private VentaBO ventaBO;
        private VentaAlFiadoBO ventaFiadoBO;
        private DevolucionBO devolucionBO;
        private RegistroPagoFiadoBO registroPagoBO;
        private DetalleVentaBO detalleVentaBO;
        private DetalleDevolucionBO detalleDevolucionBO;
        private ClienteAlFiadoBO clienteAlFiadoBO;

        private string FechaActual
        {
            get { return ViewState["FechaActual"]?.ToString(); }
            set { ViewState["FechaActual"] = value; }
        }

        private List<WSVenta.ventaDTO> ListaVentasHoy
        {
            get { return ViewState["ListaVentasHoy"] as List<WSVenta.ventaDTO> ?? new List<WSVenta.ventaDTO>(); }
            set { ViewState["ListaVentasHoy"] = value; }
        }

        private List<WSVentaAlFiado.ventaAlFiadoDTO> ListaVentasFiadas
        {
            get { return ViewState["ListaVentasFiadas"] as List<WSVentaAlFiado.ventaAlFiadoDTO> ?? new List<WSVentaAlFiado.ventaAlFiadoDTO>(); }
            set { ViewState["ListaVentasFiadas"] = value; }
        }

        private List<WSDevolucion.devolucionDTO> ListaDevoluciones
        {
            get { return ViewState["ListaDevoluciones"] as List<WSDevolucion.devolucionDTO> ?? new List<WSDevolucion.devolucionDTO>(); }
            set { ViewState["ListaDevoluciones"] = value; }
        }

        private List<WSRegistroPagoFiado.registroPagoFiadoDTO> ListaPagos
        {
            get { return ViewState["ListaPagos"] as List<WSRegistroPagoFiado.registroPagoFiadoDTO> ?? new List<WSRegistroPagoFiado.registroPagoFiadoDTO>(); }
            set { ViewState["ListaPagos"] = value; }
        }

        private List<WSClienteAlFiado.clienteAlFiadoDTO> ListaClientes
        {
            get { return ViewState["ListaClientes"] as List<WSClienteAlFiado.clienteAlFiadoDTO> ?? new List<WSClienteAlFiado.clienteAlFiadoDTO>(); }
            set { ViewState["ListaClientes"] = value; }
        }

        public Inicio()
        {
            ventaBO = new VentaBO();
            ventaFiadoBO = new VentaAlFiadoBO();
            devolucionBO = new DevolucionBO();
            registroPagoBO = new RegistroPagoFiadoBO();
            detalleVentaBO = new DetalleVentaBO();
            detalleDevolucionBO = new DetalleDevolucionBO();
            clienteAlFiadoBO = new ClienteAlFiadoBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                InicializarDatos();
                CargarEstadisticas();
                CargarMovimientosRecientes();
            }
        }

        private void InicializarDatos()
        {
            FechaActual = DateTime.Now.ToString("yyyy-MM-dd");
            ListaVentasHoy = ventaBO.listarVentasPorFecha(FechaActual);
            ListaVentasFiadas = ventaFiadoBO.listarVentasAlFiadoPorAliasClienteYFecha("", FechaActual);
            ListaDevoluciones = devolucionBO.listarDevolucionesPorFecha(FechaActual);
            ListaPagos = registroPagoBO.listarTodosRegistrosPagoFiado().Where(p => DateTime.Parse(p.fecha).ToString("yyyy-MM-dd") == FechaActual).ToList();
            ListaClientes = clienteAlFiadoBO.listarTodosClientesAlFiado();
        }

        private void CargarEstadisticas()
        {
            litClientes.Text = ListaClientes.Count(c => c.activo).ToString();

            var idsVentasFiadas = ListaVentasFiadas.Select(vf => vf.venta.ventaId).ToList();
            var ventasContado = ListaVentasHoy.Where(v => !idsVentasFiadas.Contains(v.ventaId)).ToList();
            var ventasFiadas = ListaVentasHoy.Where(v => idsVentasFiadas.Contains(v.ventaId)).ToList();

            litVentasHoy.Text = $"S/.{ventasContado.Sum(v => v.total):F2}";
            litFiados.Text = $"S/.{ventasFiadas.Sum(v => v.total):F2}";
            litDevoluciones.Text = $"S/.{ListaDevoluciones.Sum(d => d.total):F2}";
        }

        private void CargarMovimientosRecientes()
        {
            var movimientos = new List<MovimientosInicio>();
            var idsVentasFiadas = ListaVentasFiadas.Select(vf => vf.venta.ventaId).ToList();

            foreach (var venta in ListaVentasHoy)
            {
                bool esFiado = idsVentasFiadas.Contains(venta.ventaId);
                int idMovimiento = venta.ventaId;
                string tipoMovimiento = "Venta";

                if (esFiado)
                {
                    var ventaFiada = ListaVentasFiadas.FirstOrDefault(vf => vf.venta.ventaId == venta.ventaId);
                    idMovimiento = ventaFiada?.ventaFiadaId ?? venta.ventaId;
                    tipoMovimiento = "VentaFiada";
                }

                movimientos.Add(new MovimientosInicio
                {
                    Id = idMovimiento,
                    Tipo = tipoMovimiento,
                    Titulo = esFiado ? $"Venta Fiada - ID {idMovimiento}" : $"Venta - ID {venta.ventaId}",
                    FechaHora = DateTime.Parse(venta.fecha).ToString("hh:mm tt (yyyy-MM-dd)"),
                    Monto = $"+S/.{venta.total:F2}",
                    ColorMonto = esFiado ? "#ffc107" : "#28a745",
                    TipoBadge = esFiado ? "Fiado" : "Al Contado",
                    ColorBadge = esFiado ? "#ffc107" : (venta.metodoPago == WSVenta.tipoDePago.EFECTIVO ? "#28a745" : "#007bff"),
                    Icono = "bi-cart-check",
                    ColorIcono = esFiado ? "#ffc107" : "#28a745",
                    FechaOrden = DateTime.Parse(venta.fecha)
                });
            }

            foreach (var devolucion in ListaDevoluciones)
            {
                movimientos.Add(new MovimientosInicio
                {
                    Id = devolucion.devolucionId,
                    Tipo = "Devolucion",
                    Titulo = $"Devolución - ID {devolucion.devolucionId}",
                    FechaHora = DateTime.Parse(devolucion.fecha).ToString("hh:mm tt (yyyy-MM-dd)"),
                    Monto = $"-S/.{devolucion.total:F2}",
                    ColorMonto = "#dc3545",
                    TipoBadge = "Devolución",
                    ColorBadge = "#6c757d",
                    Icono = "bi-arrow-counterclockwise",
                    ColorIcono = "#dc3545",
                    FechaOrden = DateTime.Parse(devolucion.fecha)
                });
            }

            foreach (var pago in ListaPagos)
            {
                movimientos.Add(new MovimientosInicio
                {
                    Id = pago.pagoId,
                    Tipo = "Pago",
                    Titulo = $"Pago Fiado - ID {pago.pagoId}",
                    FechaHora = DateTime.Parse(pago.fecha).ToString("hh:mm tt (yyyy-MM-dd)"),
                    Monto = $"+S/.{pago.monto:F2}",
                    ColorMonto = "#17a2b8",
                    TipoBadge = "Pago Fiado",
                    ColorBadge = "#17a2b8",
                    Icono = "bi-cash",
                    ColorIcono = "#17a2b8",
                    FechaOrden = DateTime.Parse(pago.fecha)
                });
            }

            movimientos = movimientos.OrderByDescending(m => m.FechaOrden).Take(10).ToList();

            if (movimientos.Any())
            {
                rptMovimientos.DataSource = movimientos;
                rptMovimientos.DataBind();
                pnlSinMovimientos.Visible = false;
            }
            else
            {
                pnlSinMovimientos.Visible = true;
            }
        }

        protected void btnVerDetalle_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "VerDetalle")
            {
                string[] parametros = e.CommandArgument.ToString().Split('|');
                int id = int.Parse(parametros[0]);
                string tipo = parametros[1];

                switch (tipo)
                {
                    case "Venta":
                        CargarDetalleVenta(id);
                        break;
                    case "VentaFiada":
                        CargarDetalleVentaFiada(id);
                        break;
                    case "Devolucion":
                        CargarDetalleDevolucion(id);
                        break;
                    case "Pago":
                        CargarDetallePago(id);
                        break;
                }

                ScriptManager.RegisterStartupScript(this, GetType(), "MostrarModal", "mostrarModal();", true);
            }
        }

        private void OcultarTodosEstados()
        {
            litEstadoCompletada.Visible = false;
            litEstadoPendiente.Visible = false;
            litEstadoRegistrado.Visible = false;
        }

        private void OcultarTodosTotales()
        {
            pnlTotalPositivo.Visible = false;
            pnlTotalNegativo.Visible = false;
        }

        private void CargarDetalleVenta(int ventaId)
        {
            var venta = ListaVentasHoy.FirstOrDefault(v => v.ventaId == ventaId);
            if (venta == null) return;

            var detalles = detalleVentaBO.listarDetallesVentaPorVenta(ventaId);

            litModalTitulo.Text = $"Detalles de Venta #{ventaId}";
            litModalCliente.Text = "N/A";
            litModalFechaHora.Text = DateTime.Parse(venta.fecha).ToString("hh:mm tt (yyyy-MM-dd)");
            litModalTipo.Text = venta.metodoPago.ToString();

            OcultarTodosEstados();
            litEstadoCompletada.Visible = true;

            OcultarTodosTotales();
            litTotalPositivoValor.Text = $"+S/.{venta.total:F2}";
            pnlTotalPositivo.Visible = true;

            pnlInfoCliente.Visible = false;
            pnlProductos.Visible = true;
            pnlSinProductos.Visible = false;

            if (detalles != null && detalles.Any())
            {
                var productosModal = detalles.Select(d => new
                {
                    Descripcion = $"{d.producto.nombre} x{d.cantidad}",
                    Precio = $"S/.{d.subtotal:F2}"
                }).ToList();

                rptProductosModal.DataSource = productosModal;
                rptProductosModal.DataBind();
            }
            else
            {
                pnlProductos.Visible = false;
                pnlSinProductos.Visible = true;
                litMensajeSinProductos.Text = "No se encontraron detalles de productos";
            }
        }

        private void CargarDetalleVentaFiada(int ventaFiadaId)
        {
            var ventaFiada = ListaVentasFiadas.FirstOrDefault(vf => vf.ventaFiadaId == ventaFiadaId);
            if (ventaFiada == null || ventaFiada.venta == null) return;

            var detalles = detalleVentaBO.listarDetallesVentaPorVenta(ventaFiada.venta.ventaId);

            litModalTitulo.Text = $"Detalles de Venta Fiada #{ventaFiadaId}";
            litModalCliente.Text = ventaFiada.cliente?.alias ?? "N/A";
            litModalFechaHora.Text = DateTime.Parse(ventaFiada.venta.fecha).ToString("hh:mm tt (yyyy-MM-dd)");
            litModalTipo.Text = "Fiado";

            OcultarTodosEstados();
            bool pendiente = ventaFiada.cliente?.montoDeuda > 0;
            if (pendiente)
                litEstadoPendiente.Visible = true;
            else
                litEstadoCompletada.Visible = true;

            OcultarTodosTotales();
            litTotalPositivoValor.Text = $"+S/.{ventaFiada.venta.total:F2}";
            pnlTotalPositivo.Visible = true;

            pnlInfoCliente.Visible = false;
            pnlProductos.Visible = true;
            pnlSinProductos.Visible = false;

            if (detalles != null && detalles.Any())
            {
                var productosModal = detalles.Select(d => new
                {
                    Descripcion = $"{d.producto.nombre} x{d.cantidad}",
                    Precio = $"S/.{d.subtotal:F2}"
                }).ToList();

                rptProductosModal.DataSource = productosModal;
                rptProductosModal.DataBind();
            }
            else
            {
                pnlProductos.Visible = false;
                pnlSinProductos.Visible = true;
                litMensajeSinProductos.Text = "No se encontraron detalles de productos";
            }
        }

        private void CargarDetalleDevolucion(int devolucionId)
        {
            var devolucion = ListaDevoluciones.FirstOrDefault(d => d.devolucionId == devolucionId);
            if (devolucion == null) return;

            var detalles = detalleDevolucionBO.listarDetallesDevolucionPorDevolucion(devolucionId);

            litModalTitulo.Text = $"Detalles de Devolución #{devolucionId}";
            litModalCliente.Text = "N/A";
            litModalFechaHora.Text = DateTime.Parse(devolucion.fecha).ToString("hh:mm tt (yyyy-MM-dd)");
            litModalTipo.Text = "Devolución";

            OcultarTodosEstados();
            litEstadoCompletada.Visible = true;

            OcultarTodosTotales();
            litTotalNegativoValor.Text = $"-S/.{devolucion.total:F2}";
            pnlTotalNegativo.Visible = true;

            pnlInfoCliente.Visible = false;
            pnlProductos.Visible = true;
            pnlSinProductos.Visible = false;

            if (detalles != null && detalles.Any())
            {
                var productosModal = detalles.Select(d => new
                {
                    Descripcion = $"{d.producto.nombre} x{d.cantidad}",
                    Precio = $"S/.{d.subtotal:F2}"
                }).ToList();

                rptProductosModal.DataSource = productosModal;
                rptProductosModal.DataBind();
            }
            else
            {
                pnlProductos.Visible = false;
                pnlSinProductos.Visible = true;
                litMensajeSinProductos.Text = "No se encontraron detalles de productos";
            }
        }

        private void CargarDetallePago(int pagoId)
        {
            var pago = ListaPagos.FirstOrDefault(p => p.pagoId == pagoId);
            if (pago == null) return;

            litModalTitulo.Text = $"Detalles de Pago Fiado #{pagoId}";
            litModalCliente.Text = pago.cliente?.alias ?? "N/A";
            litModalFechaHora.Text = DateTime.Parse(pago.fecha).ToString("hh:mm tt (yyyy-MM-dd)");
            litModalTipo.Text = pago.metodoPago.ToString();

            OcultarTodosEstados();
            litEstadoRegistrado.Visible = true;

            OcultarTodosTotales();
            litTotalPositivoValor.Text = $"S/.{pago.monto:F2}";
            pnlTotalPositivo.Visible = true;

            litClienteNombre.Text = pago.cliente?.nombre ?? "N/A";
            litClienteAlias.Text = pago.cliente?.alias ?? "N/A";
            litClienteTelefono.Text = pago.cliente?.telefono ?? "N/A";
            litClienteDeuda.Text = $"S/.{pago.cliente?.montoDeuda:F2}";

            pnlInfoCliente.Visible = true;
            pnlProductos.Visible = false;
            pnlSinProductos.Visible = false;
        }
    }

}