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
        private string FechaActual = DateTime.Now.ToString("yyyy-MM-dd");

        private List<WSVenta.ventaDTO> ListaVentasHoy
        {
            get
            {
                if (Session["VentasHoy"] == null)
                {
                    Session["VentasHoy"] = ventaBO.listarVentasPorFecha(FechaActual);
                }
                return Session["VentasHoy"] as List<WSVenta.ventaDTO>;
            }
            set { Session["VentasHoy"] = value; }
        }

        private List<WSVentaAlFiado.ventaAlFiadoDTO> ListaVentasFiadas
        {
            get
            {
                if (Session["VentasFiadas"] == null)
                {
                    Session["VentasFiadas"] = ventaFiadoBO.listarVentasAlFiadoPorAliasClienteYFecha("", FechaActual);
                }
                return Session["VentasFiadas"] as List<WSVentaAlFiado.ventaAlFiadoDTO>;
            }
            set { Session["VentasFiadas"] = value; }
        }

        private List<WSDevolucion.devolucionDTO> ListaDevoluciones
        {
            get
            {
                if (Session["Devoluciones"] == null)
                {
                    Session["Devoluciones"] = devolucionBO.listarDevolucionesPorFecha(FechaActual);
                }
                return Session["Devoluciones"] as List<WSDevolucion.devolucionDTO>;
            }
            set { Session["Devoluciones"] = value; }
        }

        private List<WSRegistroPagoFiado.registroPagoFiadoDTO> ListaPagos
        {
            get
            {
                if (Session["Pagos"] == null)
                {
                    Session["Pagos"] = registroPagoBO.listarRegistrosPagoFiadoPorAliasClienteConFechaFin("", FechaActual).ToList();
                }
                return Session["Pagos"] as List<WSRegistroPagoFiado.registroPagoFiadoDTO>;
            }
            set { Session["Pagos"] = value; }
        }

        private List<WSClienteAlFiado.clienteAlFiadoDTO> ListaClientes
        {
            get
            {
                if (Session["ClientesList"] == null)
                {
                    Session["ClientesList"] = clienteAlFiadoBO.listarTodosClientesAlFiado();
                }
                return Session["ClientesList"] as List<WSClienteAlFiado.clienteAlFiadoDTO>;
            }
            set { Session["ClientesList"] = value; }
        }

        // Cache para detalles de venta
        private Dictionary<int, List<WSDetalleVenta.detalleVentaDTO>> CacheDetallesVenta
        {
            get
            {
                if (Session["CacheDetallesVenta"] == null)
                {
                    Session["CacheDetallesVenta"] = new Dictionary<int, List<WSDetalleVenta.detalleVentaDTO>>();
                }
                return Session["CacheDetallesVenta"] as Dictionary<int, List<WSDetalleVenta.detalleVentaDTO>>;
            }
            set { Session["CacheDetallesVenta"] = value; }
        }

        // Cache para detalles de devolución
        private Dictionary<int, List<WSDetalleDevolucion.detalleDevolucionDTO>> CacheDetallesDevolucion
        {
            get
            {
                if (Session["CacheDetallesDevolucion"] == null)
                {
                    Session["CacheDetallesDevolucion"] = new Dictionary<int, List<WSDetalleDevolucion.detalleDevolucionDTO>>();
                }
                return Session["CacheDetallesDevolucion"] as Dictionary<int, List<WSDetalleDevolucion.detalleDevolucionDTO>>;
            }
            set { Session["CacheDetallesDevolucion"] = value; }
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
                CargarEstadisticas();
                CargarMovimientosRecientes();
            }
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

                bool esTransferencia = !esFiado && venta.metodoPago == WSVenta.tipoDePago.TRANSFERENCIA;

                movimientos.Add(new MovimientosInicio
                {
                    Id = idMovimiento,
                    Tipo = tipoMovimiento,
                    Titulo = esFiado ? $"Venta Fiada - ID {idMovimiento}" : $"Venta - ID {venta.ventaId}",
                    FechaHora = DateTime.Parse(venta.fecha).ToString("hh:mm tt (yyyy-MM-dd)"),
                    Monto = $"+S/.{venta.total:F2}",
                    ColorMonto = esFiado ? "#ffc107" : "#28a745",
                    TipoBadge = esFiado ? "Fiado" : (esTransferencia ? "Transferencia" : "Efectivo"),
                    ColorBadge = esFiado ? "#ffc107" : (esTransferencia ? "#007bff" : "#28a745"),
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

        private List<WSDetalleVenta.detalleVentaDTO> ObtenerDetallesVenta(int ventaId)
        {
            var cache = CacheDetallesVenta;

            if (!cache.ContainsKey(ventaId))
            {
                cache[ventaId] = detalleVentaBO.listarDetallesVentaPorVenta(ventaId);
                CacheDetallesVenta = cache;
            }

            return cache[ventaId];
        }

        private List<WSDetalleDevolucion.detalleDevolucionDTO> ObtenerDetallesDevolucion(int devolucionId)
        {
            var cache = CacheDetallesDevolucion;

            if (!cache.ContainsKey(devolucionId))
            {
                cache[devolucionId] = detalleDevolucionBO.listarDetallesDevolucionPorDevolucion(devolucionId);
                CacheDetallesDevolucion = cache;
            }

            return cache[devolucionId];
        }

        private void CargarDetalleVenta(int ventaId)
        {
            var venta = ListaVentasHoy.FirstOrDefault(v => v.ventaId == ventaId);
            if (venta == null) return;

            var detalles = ObtenerDetallesVenta(ventaId);

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

            var detalles = ObtenerDetallesVenta(ventaFiada.venta.ventaId);

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

            var detalles = ObtenerDetallesDevolucion(devolucionId);

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