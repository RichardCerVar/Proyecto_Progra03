using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBodBusiness;
using WSVenta = SoftBodBusiness.SoftWSVenta;
using WSVentaAlFiado = SoftBodBusiness.SoftWSVentaAlFiado;
using WSDetalleVenta = SoftBodBusiness.SoftWSDetalleVenta;
using WSClienteAlFiado = SoftBodBusiness.SoftWSClienteAlFiado;
using WSProducto = SoftBodBusiness.SoftWSProducto;
using SoftBodBusiness.SoftWSDetalleVenta;

namespace SoftBodWA
{
    public partial class RegistrarVenta : System.Web.UI.Page
    {
        private readonly VentaBO ventaBO;
        private readonly VentaAlFiadoBO ventaAlFiadoBO;
        private readonly ClienteAlFiadoBO clienteAlFiadoBO;
        private readonly ProductoBO productoBO;
        private readonly CategoriaBO categoriaBO;

        public RegistrarVenta()
        {
            this.ventaBO = new VentaBO();
            this.ventaAlFiadoBO = new VentaAlFiadoBO();
            this.clienteAlFiadoBO = new ClienteAlFiadoBO();
            this.productoBO = new ProductoBO();
            this.categoriaBO = new CategoriaBO();
        }

        private List<WSDetalleVenta.detalleVentaDTO> Carrito
        {
            get
            {
                if (Session["CarritoVenta"] == null)
                    Session["CarritoVenta"] = new List<SoftBodBusiness.SoftWSDetalleVenta.detalleVentaDTO>();
                return (List<SoftBodBusiness.SoftWSDetalleVenta.detalleVentaDTO>)Session["CarritoVenta"];
            }
            set => Session["CarritoVenta"] = value;
        }

        private List<WSProducto.productoDTO> ProductosDisponibles
        {
            get
            {
                if (Session["ProductosDisponibles"] == null)
                    Session["ProductosDisponibles"] = new List<WSProducto.productoDTO>();
                return (List<WSProducto.productoDTO>)Session["ProductosDisponibles"];
            }
            set => Session["ProductosDisponibles"] = value;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Limpiar carrito al entrar por primera vez
                Carrito.Clear();

                CargarCategoriasDropDownList();
                CargarClientesDropDownList();
                CargarProductosDisponibles();
                ActualizarInterfaz();
            }
        }

        private void CargarCategoriasDropDownList()
        {
            ddlCategoriaFiltro.Items.Clear();
            ddlCategoriaFiltro.Items.Add(new ListItem("Todas las categorías", "0"));

            try
            {
                var categorias = categoriaBO.listarTodasCategorias();

                var items = categorias
                    .Select(c => new ListItem(c.descripcion, c.categoriaId.ToString()))
                    .ToArray();
                ddlCategoriaFiltro.Items.AddRange(items);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "errorCargaCategorias", $"alert('Error al cargar categorías: {ex.Message}');", true);
            }
        }

        private void CargarProductosDisponibles()
        {
            List<WSProducto.productoDTO> productos;

            bool activoFiltro = true;
            string categoriaFiltro = "";
            string nombreFiltro = txtBuscarProducto.Text.Trim();

            // Si se seleccionó una categoría específica (no "Todas")
            if (ddlCategoriaFiltro.SelectedValue != "0")
            {
                int categoriaId = int.Parse(ddlCategoriaFiltro.SelectedValue);
                // Buscar el nombre de la categoría por su ID
                try
                {
                    var categoria = categoriaBO.listarTodasCategorias()
                        .FirstOrDefault(c => c.categoriaId == categoriaId);

                    if (categoria != null)
                    {
                        categoriaFiltro = categoria.descripcion;
                    }
                }
                catch (Exception ex)
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "errorCategoria", $"alert('Error al obtener categoría: {ex.Message}');", true);
                }
            }

            try
            {
                productos = this.productoBO.listarTodosConFiltroProductos(
                    activoFiltro,
                    categoriaFiltro,
                    nombreFiltro
                );
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "errorCarga", $"alert('Error al cargar productos: {ex.Message}');", true);
                productos = new List<WSProducto.productoDTO>();
            }

            ProductosDisponibles = productos;
            var productosConStock = productos.Where(p => p.stock > 0).ToList();

            // Controlar visibilidad según si hay productos con stock
            if (productosConStock.Any())
            {
                rptProductosDisponibles.DataSource = productos;
                rptProductosDisponibles.DataBind();
                rptProductosDisponibles.Visible = true;
                pnlNoProductos.Visible = false;
            }
            else
            {
                rptProductosDisponibles.Visible = false;
                pnlNoProductos.Visible = true;
            }
        }

        private void CargarClientesDropDownList()
        {
            List<WSClienteAlFiado.clienteAlFiadoDTO> clientes;
            try
            {
                clientes = this.clienteAlFiadoBO.listarTodosClientesAlFiado();
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "errorCargaClientes", $"alert('Error al cargar clientes al fiado: {ex.Message}');", true);
                clientes = new List<WSClienteAlFiado.clienteAlFiadoDTO>();
            }

            ddlCliente.Items.Clear();
            ddlCliente.Items.Add(new ListItem("Seleccionar cliente", "0"));

            foreach (var cliente in clientes)
            {
                string display = !string.IsNullOrEmpty(cliente.alias) ?
                                 $"[{cliente.alias}] {cliente.nombre} (Deuda: S/. {cliente.montoDeuda:N2})" :
                                 cliente.nombre;
                ddlCliente.Items.Add(new ListItem(display, cliente.clienteId.ToString()));
            }
        }

        private WSProducto.productoDTO ObtenerProductoInfo(int productoId)
        {
            return ProductosDisponibles.FirstOrDefault(p => p.productoId == productoId);
        }

        private void ActualizarInterfaz()
        {
            rptCarrito.DataSource = Carrito;
            rptCarrito.DataBind();

            pnlCarritoVacio.Visible = !Carrito.Any();

            double total = Carrito.Sum(p => p.subtotal);
            lblTotal.Text = total.ToString("N2");

            bool carritoVacio = !Carrito.Any();

            if (!carritoVacio)
            {
                btnRegistrarVenta.Enabled = true;
                btnRegistrarVenta.CssClass = "btn btn-lg btn-primary";
            }
            else
            {
                btnRegistrarVenta.Enabled = false;
                btnRegistrarVenta.CssClass = "btn btn-lg btn-secondary";
            }

            // Mostrar panel de cliente solo para FIADO
            pnlCliente.Visible = ddlTipoPago.SelectedValue == tipoDePago.FIADO.ToString();

            // Mostrar panel de método de pago solo para CONTADO
            pnlMetodoPago.Visible = ddlTipoPago.SelectedValue == "CONTADO";
        }

        protected void rptProductosDisponibles_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                // Obtener el producto actual
                var producto = (WSProducto.productoDTO)e.Item.DataItem;

                // Encontrar los controles
                TextBox txtCantidad = (TextBox)e.Item.FindControl("txtCantidad");

                // Configurar atributos del TextBox
                if (txtCantidad != null)
                {
                    txtCantidad.Attributes["min"] = "0";
                    txtCantidad.Attributes["max"] = producto.stock.ToString();
                }
            }
        }

        protected void btnAdd_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            RepeaterItem item = (RepeaterItem)btn.NamingContainer;

            // Obtener los controles del repeater item
            HiddenField hfProductoId = (HiddenField)item.FindControl("hfProductoId");
            HiddenField hfStockDisponible = (HiddenField)item.FindControl("hfStockDisponible");
            TextBox txtCantidad = (TextBox)item.FindControl("txtCantidad");

            if (hfProductoId == null || txtCantidad == null || hfStockDisponible == null)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "errorControl", "alert('Error al obtener los controles del producto.');", true);
                return;
            }

            int productoId = int.Parse(hfProductoId.Value);
            int stockDisponible = int.Parse(hfStockDisponible.Value);
            int cantidadSolicitada;

            // Validar que la cantidad sea un número válido y mayor a 0
            if (!int.TryParse(txtCantidad.Text, out cantidadSolicitada) || cantidadSolicitada <= 0)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "cantidadInvalida", "alert('Por favor, ingrese una cantidad válida mayor a 0.');", true);
                txtCantidad.Text = "0"; // Resetear a 0
                return;
            }

            WSProducto.productoDTO productoInfo = ObtenerProductoInfo(productoId);
            if (productoInfo == null)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "productoNoEncontrado", "alert('Producto no encontrado.');", true);
                return;
            }

            var itemExistente = Carrito.FirstOrDefault(p => p.producto.productoId == productoId);
            int cantidadEnCarrito = itemExistente?.cantidad ?? 0;

            // Validar stock disponible
            if (cantidadEnCarrito + cantidadSolicitada > productoInfo.stock)
            {
                int disponible = productoInfo.stock - cantidadEnCarrito;
                ScriptManager.RegisterStartupScript(this, GetType(), "stockInsuficiente",
                    $"alert('Stock insuficiente para {productoInfo.nombre}.\\nEn carrito: {cantidadEnCarrito}\\nDisponible para agregar: {disponible}\\nStock total: {productoInfo.stock}');", true);
                return;
            }

            if (itemExistente != null)
            {
                // Actualizar cantidad del producto existente
                itemExistente.cantidad += cantidadSolicitada;
                itemExistente.cantidadSpecified = true;
                itemExistente.subtotal = itemExistente.cantidad * itemExistente.producto.precioUnitario;
                itemExistente.subtotalSpecified = true;
            }
            else
            {
                // Agregar nuevo producto al carrito
                var productoDetalle = new SoftBodBusiness.SoftWSDetalleVenta.productoDTO
                {
                    productoId = productoInfo.productoId,
                    productoIdSpecified = true,
                    nombre = productoInfo.nombre,
                    precioUnitario = productoInfo.precioUnitario,
                    precioUnitarioSpecified = true
                };

                var nuevoDetalle = new SoftBodBusiness.SoftWSDetalleVenta.detalleVentaDTO
                {
                    producto = productoDetalle,
                    cantidad = cantidadSolicitada,
                    cantidadSpecified = true,
                    subtotal = cantidadSolicitada * productoInfo.precioUnitario,
                    subtotalSpecified = true
                };

                Carrito.Add(nuevoDetalle);
            }

            // Resetear el campo de cantidad a 0
            txtCantidad.Text = "0";

            ActualizarInterfaz();
        }

        protected void btnRemove_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            int productoId = int.Parse(btn.CommandArgument);

            var item = Carrito.FirstOrDefault(p => p.producto.productoId == productoId);

            if (item != null)
            {
                // Eliminar el producto completamente del carrito
                Carrito.Remove(item);
            }

            ActualizarInterfaz();
        }

        protected void ddlTipoPago_SelectedIndexChanged(object sender, EventArgs e)
        {
            ActualizarInterfaz();
        }

        protected void ddlCategoriaFiltro_SelectedIndexChanged(object sender, EventArgs e)
        {
            CargarProductosDisponibles();
        }

        protected void txtBuscarProducto_TextChanged(object sender, EventArgs e)
        {
            CargarProductosDisponibles();
        }

        protected void btnRegistrarVenta_Click(object sender, EventArgs e)
        {
            if (!Carrito.Any())
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "alert", "alert('El carrito está vacío.');", true);
                return;
            }

            // 1. Mapear DTO de Carrito al DTO de Venta con Specified
            var detallesVentaWSVenta = Carrito.Select(item => new SoftBodBusiness.SoftWSVenta.detalleVentaDTO
            {
                producto = new SoftBodBusiness.SoftWSVenta.productoDTO
                {
                    productoId = item.producto.productoId,
                    productoIdSpecified = true
                },
                cantidad = item.cantidad,
                cantidadSpecified = true,
                subtotal = item.subtotal,
                subtotalSpecified = true
            }).ToArray();

            // 2. Obtener Parámetros Comunes
            tipoDePago tipoPagoEnum;
            Enum.TryParse(ddlTipoPago.SelectedValue, out tipoPagoEnum);

            SoftBodBusiness.SoftWSVenta.tipoDePago metodoPagoWSVenta;
            Enum.TryParse(ddlTipoPago.SelectedValue, out metodoPagoWSVenta);

            SoftBodBusiness.SoftWSVentaAlFiado.tipoDePago metodoPagoWSFiado;
            Enum.TryParse(ddlTipoPago.SelectedValue, out metodoPagoWSFiado);

            int idVentaRegistrada = 0;
            string mensaje = "";

            try
            {
                if (tipoPagoEnum == tipoDePago.FIADO)
                {
                    int idUser = int.Parse((Session["UsuarioId"].ToString()));
                    var usuarioLogueadoFiado = new SoftBodBusiness.SoftWSVentaAlFiado.usuarioDTO
                    {
                        usuarioId = idUser
                    };
                    usuarioLogueadoFiado.usuarioIdSpecified = true;

                    string clienteIdStr = ddlCliente.SelectedValue;
                    if (clienteIdStr == "0")
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "alert", "alert('Debe seleccionar un cliente para la venta al Fiado.');", true);
                        return;
                    }

                    int clienteId = int.Parse(clienteIdStr);

                    SoftBodBusiness.SoftWSVentaAlFiado.clienteAlFiadoDTO clienteSeleccionadoWS = new SoftBodBusiness.SoftWSVentaAlFiado.clienteAlFiadoDTO
                    {
                        clienteId = clienteId,
                        clienteIdSpecified = true,
                        nombre = ddlCliente.SelectedItem.Text
                    };

                    // Mapeo con Specified para VentaAlFiado
                    SoftBodBusiness.SoftWSVentaAlFiado.detalleVentaDTO[] detallesVentaWSFiado = detallesVentaWSVenta.Select(item => new SoftBodBusiness.SoftWSVentaAlFiado.detalleVentaDTO
                    {
                        producto = new SoftBodBusiness.SoftWSVentaAlFiado.productoDTO
                        {
                            productoId = item.producto.productoId,
                            productoIdSpecified = true
                        },
                        cantidad = item.cantidad,
                        cantidadSpecified = true,
                        subtotal = item.subtotal,
                        subtotalSpecified = true
                    }).ToArray();

                    idVentaRegistrada = this.ventaAlFiadoBO.insertarVentaAlFiado(
                        clienteSeleccionadoWS,
                        usuarioLogueadoFiado,
                        metodoPagoWSFiado.ToString(),
                        detallesVentaWSFiado
                    );

                    mensaje = $"✅ Venta Fiada registrada con éxito (ID: {idVentaRegistrada}). Total: S/. {lblTotal.Text}. Cliente: {ddlCliente.SelectedItem.Text}.";
                    var cliente = clienteAlFiadoBO.obtenerClienteAlFiadoPorId(clienteId);
                    cliente.montoDeuda += double.Parse(lblTotal.Text);
                    int resModify = clienteAlFiadoBO.modificarClienteAlFiado(cliente);

                }
                else
                {
                    // Validar método de pago para venta al contado
                    string metodoPagoContado = ddlMetodoPago.SelectedValue;
                    if (string.IsNullOrEmpty(metodoPagoContado))
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "alert", "alert('Debe seleccionar un método de pago.');", true);
                        return;
                    }

                    int idUserVenta = int.Parse(Session["UsuarioId"].ToString());
                    var usuarioLogueadoVenta = new SoftBodBusiness.SoftWSVenta.usuarioDTO
                    {
                        usuarioId = idUserVenta
                    };
                    usuarioLogueadoVenta.usuarioIdSpecified = true;

                    idVentaRegistrada = this.ventaBO.insertarVenta(
                        usuarioLogueadoVenta,
                        metodoPagoContado,
                        detallesVentaWSVenta
                    );

                    mensaje = $"✅ Venta registrada con éxito (ID: {idVentaRegistrada}). Tipo: {tipoPagoEnum}. Método: {metodoPagoContado}. Total: S/. {lblTotal.Text}.";
                }

                // 4. Finalizar y Limpiar
                ActualizarStockLocal(detallesVentaWSVenta);

                Carrito.Clear();
                CargarClientesDropDownList();
                CargarProductosDisponibles();
                ActualizarInterfaz();
                ScriptManager.RegisterStartupScript(this, GetType(), "success", $"alert('{mensaje}');", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al registrar la venta: {ex.Message}');", true);
            }
        }

        private void ActualizarStockLocal(SoftBodBusiness.SoftWSVenta.detalleVentaDTO[] detallesVenta)
        {
            foreach (var detalle in detallesVenta)
            {
                var productoEnStock = ProductosDisponibles.FirstOrDefault(p => p.productoId == detalle.producto.productoId);
                if (productoEnStock != null)
                {
                    productoEnStock.stock -= detalle.cantidad;
                }
            }
        }

        protected void rptProductosDisponibles_ItemCommand(object source, RepeaterCommandEventArgs e)
        {

        }
    }
}