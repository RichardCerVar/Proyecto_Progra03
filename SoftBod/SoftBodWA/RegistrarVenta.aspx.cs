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

namespace SoftBodWA
{
    public partial class RegistrarVenta : System.Web.UI.Page
    {
        private readonly VentaBO ventaBO;
        private readonly VentaAlFiadoBO ventaAlFiadoBO;
        private readonly ClienteAlFiadoBO clienteAlFiadoBO;
        private readonly ProductoBO productoBO;

        public RegistrarVenta()
        {
            this.ventaBO = new VentaBO();
            this.ventaAlFiadoBO = new VentaAlFiadoBO();
            this.clienteAlFiadoBO = new ClienteAlFiadoBO();
            this.productoBO = new ProductoBO();
        }

        // Propiedad para mantener el carrito en Sesión usando el DTO del WS
        private List<WSDetalleVenta.detalleVentaDTO> Carrito
        {
            get
            {
                if (Session["CarritoVenta"] == null)
                    // Se usa el detalleVentaDTO del WS de DetalleVenta ya que es el que tiene la estructura base.
                    Session["CarritoVenta"] = new List<SoftBodBusiness.SoftWSDetalleVenta.detalleVentaDTO>();
                return (List<SoftBodBusiness.SoftWSDetalleVenta.detalleVentaDTO>)Session["CarritoVenta"];
            }
            set => Session["CarritoVenta"] = value;
        }

        // Propiedad para mantener la lista de productos disponibles en Sesión
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
                CargarClientesDropDownList();
            }
            CargarProductosDisponibles();
            ActualizarInterfaz();
        }

        // --- LÓGICA DE CARGA DE DATOS REAL (Usando BOs) ---

        private void CargarProductosDisponibles()
        {
            List<WSProducto.productoDTO> productos;

            bool activoFiltro = true;
            string categoriaFiltro = ddlCategoriaFiltro.SelectedValue;
            string nombreFiltro = txtBuscarProducto.Text.Trim();

            if (categoriaFiltro == "0") categoriaFiltro = "";

            try
            {
                // LLAMADA REAL A LA CAPA DE NEGOCIO CON FILTROS
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

            // Mostrar solo los productos con stock > 0
            rptProductosDisponibles.DataSource = productos.Where(p => (p.stock) > 0).ToList();
            rptProductosDisponibles.DataBind();
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

            btnRegistrarVenta.Text = $"Registrar Venta - S/. {lblTotal.Text}";

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

            pnlCliente.Visible = ddlTipoPago.SelectedValue == Tipo_de_pago.FIADO.ToString();
        }

        protected void btnAdd_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            int productoId = int.Parse(btn.CommandArgument);

            WSProducto.productoDTO productoInfo = ObtenerProductoInfo(productoId);
            if (productoInfo == null) return;

            var itemExistente = Carrito.FirstOrDefault(p => p.producto.productoId == productoId);

            int cantidadEnCarrito = itemExistente?.cantidad ?? 0;
            if (cantidadEnCarrito >= productoInfo.stock)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "stockAlert", $"alert('No hay suficiente stock disponible para {productoInfo.nombre}.');", true);
                return;
            }

            if (itemExistente != null)
            {
                itemExistente.cantidad++;
            }
            else
            {
                var productoDetalle = new SoftBodBusiness.SoftWSDetalleVenta.productoDTO
                {
                    productoId = productoInfo.productoId,
                    nombre = productoInfo.nombre,
                    precioUnitario = productoInfo.precioUnitario
                };
                Carrito.Add(new SoftBodBusiness.SoftWSDetalleVenta.detalleVentaDTO
                {
                    producto = productoDetalle,
                    cantidad = 1
                });
            }

            var itemActualizado = Carrito.First(p => p.producto.productoId == productoId);
            itemActualizado.subtotal = itemActualizado.cantidad * itemActualizado.producto.precioUnitario;

            ActualizarInterfaz();
        }

        protected void btnRemove_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            int productoId = int.Parse(btn.CommandArgument);

            var item = Carrito.FirstOrDefault(p => p.producto.productoId == productoId);

            if (item != null)
            {
                item.cantidad--;
                if (item.cantidad <= 0)
                {
                    Carrito.Remove(item);
                }
                else
                {
                    // Recalcular subtotal si solo se reduce la cantidad
                    item.subtotal = item.cantidad * item.producto.precioUnitario;
                }
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
            ActualizarInterfaz();
        }

        protected void txtBuscarProducto_TextChanged(object sender, EventArgs e)
        {
            CargarProductosDisponibles();
            ActualizarInterfaz();
        }

        protected void btnRegistrarVenta_Click(object sender, EventArgs e)
        {
            if (!Carrito.Any())
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "alert", "alert('El carrito está vacío.');", true);
                return;
            }

            // 1. Mapear DTO de Carrito (SoftWSDetalleVenta) al DTO de Venta (SoftWSVenta)
            // Aunque son estructuras idénticas, pueden ser de namespaces distintos.
            var detallesVentaWSVenta = Carrito.Select(item => new SoftBodBusiness.SoftWSVenta.detalleVentaDTO
            {
                producto = new SoftBodBusiness.SoftWSVenta.productoDTO
                {
                    // Asegurarse de que el DTO del WS de Venta acepte la estructura correcta del Producto
                    productoId = item.producto.productoId,
                    // Si el DTO de Venta requiere más campos del producto, deberían mapearse aquí.
                },
                cantidad = item.cantidad,
                subtotal = item.subtotal
            }).ToArray();


            // 2. Obtener Parámetros Comunes
            Tipo_de_pago tipoPagoEnum;
            Enum.TryParse(ddlTipoPago.SelectedValue, out tipoPagoEnum);

            SoftBodBusiness.SoftWSVenta.tipoDePago metodoPagoWSVenta;
            Enum.TryParse(ddlTipoPago.SelectedValue, out metodoPagoWSVenta);

            // Se necesita el tipo de pago del WS de VentaAlFiado también si es necesario, 
            // pero el Enum metodoPagoWSVenta es generalmente compatible si usa el mismo tipo.
            SoftBodBusiness.SoftWSVentaAlFiado.tipoDePago metodoPagoWSFiado;
            Enum.TryParse(ddlTipoPago.SelectedValue, out metodoPagoWSFiado);

            // SIMULACIÓN: Obtener Usuario logueado
            SoftBodBusiness.SoftWSVenta.usuarioDTO usuarioLogueadoVenta = new SoftBodBusiness.SoftWSVenta.usuarioDTO
            {
                usuarioId = 99,
                nombre = "Operario Demo",
            };

            SoftBodBusiness.SoftWSVentaAlFiado.usuarioDTO usuarioLogueadoFiado = new SoftBodBusiness.SoftWSVentaAlFiado.usuarioDTO
            {
                usuarioId = 99,
                nombre = "Operario Demo",
            };


            int idVentaRegistrada = 0;
            string mensaje = "";

            try
            {
                if (tipoPagoEnum == Tipo_de_pago.FIADO)
                {
                    string clienteIdStr = ddlCliente.SelectedValue;
                    if (clienteIdStr == "0")
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "alert", "alert('Debe seleccionar un cliente para la venta al Fiado.');", true);
                        return;
                    }

                    // 3. Registrar Venta al Fiado
                    int clienteId = int.Parse(clienteIdStr);

                    SoftBodBusiness.SoftWSVentaAlFiado.clienteAlFiadoDTO clienteSeleccionadoWS = new SoftBodBusiness.SoftWSVentaAlFiado.clienteAlFiadoDTO
                    {
                        clienteId = clienteId,
                        nombre = ddlCliente.SelectedItem.Text
                    };

                    // Mapeo de detalleVentaDTO al esperado por VentaAlFiadoBO
                    SoftBodBusiness.SoftWSVentaAlFiado.detalleVentaDTO[] detallesVentaWSFiado = detallesVentaWSVenta.Select(item => new SoftBodBusiness.SoftWSVentaAlFiado.detalleVentaDTO
                    {
                        producto = new SoftBodBusiness.SoftWSVentaAlFiado.productoDTO { productoId = item.producto.productoId },
                        cantidad = item.cantidad,
                        subtotal = item.subtotal
                    }).ToArray();

                    // LLAMADA REAL A VentaAlFiadoBO
                    idVentaRegistrada = this.ventaAlFiadoBO.insertarVentaAlFiado(
                        clienteSeleccionadoWS,
                        usuarioLogueadoFiado,
                        metodoPagoWSFiado,
                        detallesVentaWSFiado
                    );

                    mensaje = $"✅ Venta Fiada registrada con éxito (ID: {idVentaRegistrada}). Total: S/. {lblTotal.Text}. Cliente: {ddlCliente.SelectedItem.Text}.";
                }
                else
                {
                    // 3. Registrar Venta Normal (CONTADO/TRANSFERENCIA)

                    // LLAMADA REAL A VentaBO
                    idVentaRegistrada = this.ventaBO.insertarVenta(
                        usuarioLogueadoVenta,
                        metodoPagoWSVenta,
                        detallesVentaWSVenta
                    );

                    mensaje = $"✅ Venta registrada con éxito (ID: {idVentaRegistrada}). Tipo: {tipoPagoEnum}. Total: S/. {lblTotal.Text}.";
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

        // --- Método Auxiliar de Simulación de Stock Local ---
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
    }
}