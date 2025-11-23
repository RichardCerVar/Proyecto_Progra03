using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBodBusiness;
using WSVenta = SoftBodBusiness.SoftWSVenta;
using WSDetalleVenta = SoftBodBusiness.SoftWSDetalleVenta;
using WSDevolucion = SoftBodBusiness.SoftWSDevolucion;
using WSRazonDevolucion = SoftBodBusiness.SoftWSRazonDevolucion;
using WSUsuario = SoftBodBusiness.SoftWSUsuario;

namespace SoftBodWA
{
    public partial class RegistrarDevolucion : System.Web.UI.Page
    {
        private VentaBO ventaBO;
        private DetalleVentaBO detalleVentaBO;
        private DevolucionBO devolucionBO;
        private RazonDevolucionBO razonDevolucionBO;

        private List<WSVenta.ventaDTO> ListaVentasHoy
        {
            get { return Session["VentasHoy"] as List<WSVenta.ventaDTO> ?? new List<WSVenta.ventaDTO>(); }
            set { Session["VentasHoy"] = value; }
        }

        private int VentaSeleccionadaId
        {
            get { return ViewState["VentaSeleccionadaId"] != null ? (int)ViewState["VentaSeleccionadaId"] : 0; }
            set { ViewState["VentaSeleccionadaId"] = value; }
        }

        private List<WSDetalleVenta.detalleVentaDTO> DetallesVentaSeleccionada
        {
            get { return ViewState["DetallesVentaSeleccionada"] as List<WSDetalleVenta.detalleVentaDTO> ?? new List<WSDetalleVenta.detalleVentaDTO>(); }
            set { ViewState["DetallesVentaSeleccionada"] = value; }
        }

        public RegistrarDevolucion()
        {
            ventaBO = new VentaBO();
            detalleVentaBO = new DetalleVentaBO();
            devolucionBO = new DevolucionBO();
            razonDevolucionBO = new RazonDevolucionBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarVentasHoy();
                CargarRazonesDevolucion();
            }
        }

        private void CargarVentasHoy()
        {
            try
            {
                string fechaActual = DateTime.Now.ToString("yyyy-MM-dd");

                // Verificar si ya existen en sesión
                if (ListaVentasHoy == null || !ListaVentasHoy.Any())
                {
                    ListaVentasHoy = ventaBO.listarVentasPorFecha(fechaActual);
                }

                if (ListaVentasHoy != null && ListaVentasHoy.Any())
                {
                    var ventasFormateadas = ListaVentasHoy.Select(v => new
                    {
                        v.ventaId,
                        v.total,
                        v.metodoPago,
                        FechaHora = DateTime.Parse(v.fecha).ToString("hh:mm tt"),
                        TipoBadge = v.metodoPago == WSVenta.tipoDePago.TRANSFERENCIA ? "Transferencia" : "Al Contado",
                        ColorBadge = v.metodoPago == WSVenta.tipoDePago.TRANSFERENCIA ? "#007bff" : "#28a745"
                    }).ToList();

                    rptVentas.DataSource = ventasFormateadas;
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
                MostrarMensaje("Error al cargar ventas: " + ex.Message, "danger");
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
                MostrarMensaje("Error al cargar razones de devolución: " + ex.Message, "warning");
            }
        }

        protected void btnSeleccionarVenta_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "Seleccionar")
            {
                try
                {
                    int ventaId = int.Parse(e.CommandArgument.ToString());
                    VentaSeleccionadaId = ventaId;

                    var venta = ListaVentasHoy.FirstOrDefault(v => v.ventaId == ventaId);
                    if (venta != null)
                    {
                        // Cargar detalles de la venta
                        DetallesVentaSeleccionada = detalleVentaBO.listarDetallesVentaPorVenta(ventaId);

                        if (DetallesVentaSeleccionada != null && DetallesVentaSeleccionada.Any())
                        {
                            MostrarPanelDevolucion(venta);
                        }
                        else
                        {
                            MostrarMensaje("No se encontraron detalles para esta venta", "warning");
                        }
                    }
                }
                catch (Exception ex)
                {
                    MostrarMensaje("Error al seleccionar venta: " + ex.Message, "danger");
                }
            }
        }

        private void MostrarPanelDevolucion(WSVenta.ventaDTO venta)
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
                    int ventaId = int.Parse(e.CommandArgument.ToString());
                    var venta = ListaVentasHoy.FirstOrDefault(v => v.ventaId == ventaId);

                    if (venta != null)
                    {
                        var detalles = detalleVentaBO.listarDetallesVentaPorVenta(ventaId);

                        litModalTituloVenta.Text = $"Detalles de Venta #{ventaId}";
                        litModalFechaVenta.Text = DateTime.Parse(venta.fecha).ToString("dd/MM/yyyy hh:mm tt");
                        litModalMetodoPago.Text = venta.metodoPago.ToString();
                        litModalTotalVenta.Text = $"S/.{venta.total:F2}";

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

                        ScriptManager.RegisterStartupScript(this, GetType(), "MostrarModalVenta", "mostrarModalVenta();", true);
                    }
                }
                catch (Exception ex)
                {
                    MostrarMensaje("Error al mostrar detalle: " + ex.Message, "danger");
                }
            }
        }

        protected void btnVolverListaVentas_Click(object sender, EventArgs e)
        {
            pnlDetalleDevolucion.Visible = false;
            pnlSeleccionVenta.Visible = true;
            VentaSeleccionadaId = 0;
            DetallesVentaSeleccionada = null;
        }

        protected void btnRegistrarDevolucion_Click(object sender, EventArgs e)
        {
            if (!Page.IsValid)
                return;

            try
            {
                // Validar que se haya seleccionado una razón
                if (ddlRazonDevolucion.SelectedValue == "0")
                {
                    MostrarMensaje("Debe seleccionar una razón de devolución", "warning");
                    return;
                }

                // Obtener el usuario de sesión 
                var usuario = new WSDevolucion.usuarioDTO();
                usuario.usuarioId= (int)Session["UsuarioId"];
                usuario.usuarioIdSpecified = true;

                // Obtener la razón de devolución seleccionada
                int razonId = int.Parse(ddlRazonDevolucion.SelectedValue);
                var razon = razonDevolucionBO.obtenerRazonDevolucionPorId(razonId);

                // Crear lista de detalles de devolución
                List<WSDevolucion.detalleDevolucionDTO> detalles = new List<WSDevolucion.detalleDevolucionDTO>();
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
                                // Validar cantidad
                                if (cantidadDevolver > detalleVenta.cantidad)
                                {
                                    MostrarMensaje($"La cantidad a devolver de {detalleVenta.producto.nombre} no puede exceder la cantidad vendida", "warning");
                                    return;
                                }

                                WSDevolucion.detalleDevolucionDTO detalle = new WSDevolucion.detalleDevolucionDTO
                                {
                                    producto = new WSDevolucion.productoDTO
                                    {
                                        productoId = detalleVenta.producto.productoId,
                                        productoIdSpecified = true,
                                        nombre = detalleVenta.producto.nombre,
                                        precioUnitario = detalleVenta.producto.precioUnitario,
                                        precioUnitarioSpecified = true
                                    },
                                    cantidad = cantidadDevolver,
                                    cantidadSpecified = true,
                                    subtotal = cantidadDevolver * precio,
                                    subtotalSpecified = true,
                                    razonDevolucion = new WSDevolucion.razonDevolucionDTO
                                    {
                                        razonDevolucionId = razon.razonDevolucionId,
                                        razonDevolucionIdSpecified = true,
                                        descripcion = razon.descripcion
                                    }
                                };

                                detalles.Add(detalle);
                            }
                        }
                    }
                }

                if (!tieneProductosADevolver)
                {
                    MostrarMensaje("Debe seleccionar al menos un producto para devolver", "warning");
                    return;
                }

                // Registrar la devolución
                int resultado = devolucionBO.insertarDevolucion(usuario, detalles.ToArray());

                if (resultado > 0)
                {
                    // Limpiar la sesión de ventas para forzar recarga
                    ListaVentasHoy = null;

                    MostrarMensaje("Devolución registrada exitosamente", "success");

                    // Volver a la lista de ventas
                    btnVolverListaVentas_Click(null, null);
                    CargarVentasHoy();
                }
                else
                {
                    MostrarMensaje("Error al registrar la devolución", "danger");
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al registrar devolución: " + ex.Message, "danger");
            }
        }

        protected void btnGestionarRazones_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarRazonesDevolucion.aspx");
        }

        private void MostrarMensaje(string mensaje, string tipo)
        {
            string script = $@"
                var alertDiv = document.createElement('div');
                alertDiv.className = 'alert alert-{tipo} alert-dismissible fade show position-fixed top-0 start-50 translate-middle-x mt-3';
                alertDiv.style.zIndex = '9999';
                alertDiv.innerHTML = '{mensaje}<button type=""button"" class=""btn-close"" data-bs-dismiss=""alert""></button>';
                document.body.appendChild(alertDiv);
                setTimeout(function() {{ 
                    alertDiv.remove(); 
                }}, 5000);
            ";
            ScriptManager.RegisterStartupScript(this, GetType(), "MostrarMensaje", script, true);
        }
    }
}