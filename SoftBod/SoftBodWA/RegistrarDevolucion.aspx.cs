using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBodBusiness;
using SoftBodBusiness.SoftBodWSServices;

namespace SoftBodWA
{
    public partial class RegistrarDevolucion : System.Web.UI.Page
    {
        private VentaBO ventaBO;
        private VentaAlFiadoBO ventaFiadoBO;
        private DetalleVentaBO detalleVentaBO;
        private DevolucionBO devolucionBO;
        private RazonDevolucionBO razonDevolucionBO;

        private List<ventaDTO> ListaVentasHoy
        {
            get { return Session["VentasHoy"] as List<ventaDTO> ?? new List<ventaDTO>(); }
            set { Session["VentasHoy"] = value; }
        }

        private List<ventaAlFiadoDTO> ListaVentasFiadas
        {
            get { return Session["VentasFiadas"] as List<ventaAlFiadoDTO> ?? new List<ventaAlFiadoDTO>(); }
            set { Session["VentasFiadas"] = value; }
        }

        private int VentaSeleccionadaId
        {
            get { return ViewState["VentaSeleccionadaId"] != null ? (int)ViewState["VentaSeleccionadaId"] : 0; }
            set { ViewState["VentaSeleccionadaId"] = value; }
        }

        private string TipoVentaSeleccionada
        {
            get { return ViewState["TipoVentaSeleccionada"] as string ?? ""; }
            set { ViewState["TipoVentaSeleccionada"] = value; }
        }

        private List<detalleVentaDTO> DetallesVentaSeleccionada
        {
            get { return ViewState["DetallesVentaSeleccionada"] as List<detalleVentaDTO> ?? new List<detalleVentaDTO>(); }
            set { ViewState["DetallesVentaSeleccionada"] = value; }
        }

        public RegistrarDevolucion()
        {
            ventaBO = new VentaBO();
            ventaFiadoBO = new VentaAlFiadoBO();
            detalleVentaBO = new DetalleVentaBO();
            devolucionBO = new DevolucionBO();
            razonDevolucionBO = new RazonDevolucionBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                InicializarDatos();
                CargarVentasHoy();
                CargarRazonesDevolucion();
            }
        }

        private void InicializarDatos()
        {
            string fechaActual = DateTime.Now.ToString("yyyy-MM-dd");

            if (ListaVentasHoy == null || !ListaVentasHoy.Any())
            {
                ListaVentasHoy = ventaBO.listarVentasPorFecha(fechaActual);
            }

            if (ListaVentasFiadas == null || !ListaVentasFiadas.Any())
            {
                ListaVentasFiadas = ventaFiadoBO.listarVentasAlFiadoPorAliasClienteYFecha("", fechaActual);
            }
        }

        private void CargarVentasHoy()
        {
            try
            {
                if (ListaVentasHoy != null && ListaVentasHoy.Any())
                {
                    var idsVentasFiadas = ListaVentasFiadas.Select(vf => vf.venta.ventaId).ToList();
                    var ventasFormateadas = new List<MovimientosInicio>();

                    foreach (var venta in ListaVentasHoy)
                    {
                        bool esFiado = idsVentasFiadas.Contains(venta.ventaId);
                        int idMovimiento = venta.ventaId;
                        string tipoVenta = "Venta";

                        if (esFiado)
                        {
                            var ventaFiada = ListaVentasFiadas.FirstOrDefault(vf => vf.venta.ventaId == venta.ventaId);
                            idMovimiento = ventaFiada?.ventaFiadaId ?? venta.ventaId;
                            tipoVenta = "VentaFiada";
                        }

                        bool esTransferencia = !esFiado && venta.metodoPago == tipoDePago.TRANSFERENCIA;

                        ventasFormateadas.Add(new MovimientosInicio
                        {
                            Id = venta.ventaId,
                            Tipo = tipoVenta,
                            Titulo = esFiado ? $"Venta Fiada - ID {idMovimiento}" : $"Venta - ID {venta.ventaId}",
                            FechaHora = DateTime.Parse(venta.fecha).ToString("hh:mm tt"),
                            Monto = $"S/.{venta.total:F2}",
                            ColorMonto = esFiado ? "#ffc107" : "#28a745",
                            TipoBadge = esFiado ? "Fiado" : (esTransferencia ? "Transferencia" : "Efectivo"),
                            ColorBadge = esFiado ? "#ffc107" : (esTransferencia ? "#007bff" : "#28a745"),
                            Icono = "bi-cart-check",
                            ColorIcono = esFiado ? "#ffc107" : "#28a745"
                        });
                    }


                    rptVentas.DataSource = ventasFormateadas.OrderBy(v => v.FechaHora).ToList(); ;
                    rptVentas.DataBind();
                    pnlSinVentas.Visible = false;
                }
                else
                {
                    pnlSinVentas.Visible = true;
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError("Error al cargar ventas: " + ex.Message);
            }
        }

        private void CargarRazonesDevolucion()
        {
            try
            {
                var razones = razonDevolucionBO.listarTodasRazonesDevolucion();

                ddlRazonDevolucion.Items.Clear();
                ddlRazonDevolucion.Items.Add(new ListItem("-- Seleccione una razón --", "0"));

                if (razones != null && razones.Any())
                {
                    foreach (var razon in razones)
                    {
                        ddlRazonDevolucion.Items.Add(new ListItem(razon.descripcion, razon.razonDevolucionId.ToString()));
                    }
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError("Error al cargar razones de devolución: " + ex.Message);
            }
        }

        protected void btnSeleccionarVenta_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "Seleccionar")
            {
                try
                {
                    string[] parametros = e.CommandArgument.ToString().Split('|');
                    int ventaId = int.Parse(parametros[0]);
                    string tipoVenta = parametros[1];

                    VentaSeleccionadaId = ventaId;
                    TipoVentaSeleccionada = tipoVenta;

                    var venta = ListaVentasHoy.FirstOrDefault(v => v.ventaId == ventaId);
                    if (venta != null)
                    {
                        DetallesVentaSeleccionada = detalleVentaBO.listarDetallesVentaPorVenta(ventaId);

                        if (DetallesVentaSeleccionada != null && DetallesVentaSeleccionada.Any())
                        {
                            MostrarPanelDevolucion(venta);
                        }
                        else
                        {
                            MostrarMensajeError("No se encontraron detalles para esta venta");
                        }
                    }
                }
                catch (Exception ex)
                {
                    MostrarMensajeError("Error al seleccionar venta: " + ex.Message);
                }
            }
        }

        private void MostrarPanelDevolucion(ventaDTO venta)
        {
            pnlSeleccionVenta.Visible = false;
            pnlDetalleDevolucion.Visible = true;

            litVentaIdSeleccionada.Text = venta.ventaId.ToString();
            litFechaVenta.Text = DateTime.Parse(venta.fecha).ToString("dd/MM/yyyy hh:mm tt");
            litTotalVenta.Text = venta.total.ToString("F2");

            var detallesFormateados = DetallesVentaSeleccionada.Select(d => new
            {
                ProductoId = d.producto.productoId,
                NombreProducto = d.producto.nombre,
                Cantidad = d.cantidad,
                PrecioUnitario = d.producto.precioUnitario,
                Subtotal = d.subtotal
            }).ToList();

            rptDetalleVenta.DataSource = detallesFormateados;
            rptDetalleVenta.DataBind();

            txtTotalDevolucion.Text = "0.00";
        }

        protected void btnVerDetalleVenta_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "VerDetalle")
            {
                try
                {
                    string[] parametros = e.CommandArgument.ToString().Split('|');
                    int ventaId = int.Parse(parametros[0]);
                    string tipoVenta = parametros[1];

                    if (tipoVenta == "VentaFiada")
                    {
                        CargarDetalleVentaFiada(ventaId);
                    }
                    else
                    {
                        CargarDetalleVenta(ventaId);
                    }

                    ScriptManager.RegisterStartupScript(this, GetType(), "MostrarModalVenta", "mostrarModalVenta();", true);
                }
                catch (Exception ex)
                {
                    MostrarMensajeError("Error al mostrar detalle: " + ex.Message);
                }
            }
        }

        private void CargarDetalleVenta(int ventaId)
        {
            var venta = ListaVentasHoy.FirstOrDefault(v => v.ventaId == ventaId);
            if (venta == null) return;

            var detalles = detalleVentaBO.listarDetallesVentaPorVenta(ventaId);

            litModalTituloVenta.Text = $"Detalles de Venta #{ventaId}";
            litModalFechaVenta.Text = DateTime.Parse(venta.fecha).ToString("hh:mm tt (yyyy-MM-dd)");
            litModalTipoLabel.Text = "Método de Pago";
            litModalMetodoPago.Text = venta.metodoPago.ToString();
            litModalTotalVenta.Text = $"S/.{venta.total:F2}";

            pnlModalInfoCliente.Visible = false;

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
        }

        private void CargarDetalleVentaFiada(int ventaId)
        {
            var venta = ListaVentasHoy.FirstOrDefault(v => v.ventaId == ventaId);
            if (venta == null) return;

            var ventaFiada = ListaVentasFiadas.FirstOrDefault(vf => vf.venta.ventaId == ventaId);
            var detalles = detalleVentaBO.listarDetallesVentaPorVenta(ventaId);

            litModalTituloVenta.Text = $"Detalles de Venta Fiada #{ventaFiada?.ventaFiadaId ?? ventaId}";
            litModalFechaVenta.Text = DateTime.Parse(venta.fecha).ToString("hh:mm tt (yyyy-MM-dd)");
            litModalTipoLabel.Text = "Tipo";
            litModalMetodoPago.Text = "Fiado";
            litModalCliente.Text = ventaFiada?.cliente?.alias ?? "N/A";
            litModalTotalVenta.Text = $"S/.{venta.total:F2}";

            pnlModalInfoCliente.Visible = true;

            bool pendiente = ventaFiada?.cliente?.montoDeuda > 0;
            litModalEstadoPendiente.Visible = pendiente;
            litModalEstadoCompletada.Visible = !pendiente;

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
        }

        protected void btnVolverListaVentas_Click(object sender, EventArgs e)
        {
            pnlDetalleDevolucion.Visible = false;
            pnlSeleccionVenta.Visible = true;
            VentaSeleccionadaId = 0;
            TipoVentaSeleccionada = "";
            DetallesVentaSeleccionada = null;
        }

        protected void btnRegistrarDevolucion_Click(object sender, EventArgs e)
        {
            if (!Page.IsValid)
                return;

            try
            {
                if (ddlRazonDevolucion.SelectedValue == "0")
                {
                    MostrarMensajeError("Debe seleccionar una razón de devolución");
                    return;
                }

                var usuario = new usuarioDTO();
                usuario.usuarioId = (int)Session["UsuarioId"];
                usuario.usuarioIdSpecified = true;

                int razonId = int.Parse(ddlRazonDevolucion.SelectedValue);
                var razon = razonDevolucionBO.obtenerRazonDevolucionPorId(razonId);

                List<detalleDevolucionDTO> detalles = new List<detalleDevolucionDTO>();
                bool tieneProductosADevolver = false;

                foreach (RepeaterItem item in rptDetalleVenta.Items)
                {
                    TextBox txtCantidad = (TextBox)item.FindControl("txtCantidadDevolver");

                    if (txtCantidad != null)
                    {
                        int cantidadDevolver = 0;
                        int.TryParse(txtCantidad.Text, out cantidadDevolver);

                        if (cantidadDevolver > 0)
                        {
                            tieneProductosADevolver = true;

                            int productoId = int.Parse(txtCantidad.Attributes["data-producto-id"]);
                            double precio = double.Parse(txtCantidad.Attributes["data-precio"]);

                            var detalleVenta = DetallesVentaSeleccionada.FirstOrDefault(d => d.producto.productoId == productoId);

                            if (detalleVenta != null)
                            {
                                if (cantidadDevolver > detalleVenta.cantidad)
                                {
                                    MostrarMensajeError($"La cantidad a devolver de {detalleVenta.producto.nombre} no puede exceder la cantidad vendida");
                                    return;
                                }

                                detalleDevolucionDTO detalle = new detalleDevolucionDTO
                                {
                                    producto = new productoDTO
                                    {
                                        productoId = detalleVenta.producto.productoId,
                                        productoIdSpecified = true,
                                    },
                                    cantidad = cantidadDevolver,
                                    cantidadSpecified = true,
                                    subtotal = cantidadDevolver * precio,
                                    subtotalSpecified = true,
                                    razonDevolucion = new razonDevolucionDTO
                                    {
                                        razonDevolucionId = razon.razonDevolucionId,
                                        razonDevolucionIdSpecified = true,
                                    }
                                };

                                detalles.Add(detalle);
                            }
                        }
                    }
                }

                if (!tieneProductosADevolver)
                {
                    MostrarMensajeError("Debe seleccionar al menos un producto para devolver");
                    return;
                }

                int resultado = devolucionBO.insertarDevolucion(usuario, detalles.ToArray());

                if (resultado > 0)
                {
                    ListaVentasHoy = null;
                    ListaVentasFiadas = null;

                    MostrarMensajeExitoYRecargar("Devolución registrada exitosamente");
                }
                else
                {
                    MostrarMensajeError("Error al registrar la devolución");
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError("Error al registrar devolución: " + ex.Message);
            }
        }

        protected void btnGestionarRazones_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarRazonesDevolucion.aspx");
        }

        private void MostrarMensajeError(string mensaje)
        {
            string mensajeEscapado = EscapeJavaScript(mensaje);
            ScriptManager.RegisterStartupScript(this, this.GetType(), "error",
                $"alert('{mensajeEscapado}');", true);
        }

        private void MostrarMensajeExitoYRecargar(string mensaje)
        {
            string mensajeEscapado = EscapeJavaScript(mensaje);
            string script = $@"
                alert('{mensajeEscapado}');
                window.location.href = window.location.pathname;
            ";
            ScriptManager.RegisterStartupScript(this, this.GetType(), "exitoYRecarga", script, true);
        }

        private string EscapeJavaScript(string text)
        {
            return text.Replace("'", "\\'").Replace("\"", "\\\"").Replace("\n", "\\n").Replace("\r", "");
        }

        protected void btnVolverInicio_Click(object sender, EventArgs e)
        {
            Response.Redirect("Inicio.aspx");
        }
    }
}