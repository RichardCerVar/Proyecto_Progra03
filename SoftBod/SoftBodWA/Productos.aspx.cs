using SoftBodBusiness;
using SoftBodBusiness.SoftWSCategoria;
using SoftBodBusiness.SoftWSProducto;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using WSCategoria = SoftBodBusiness.SoftWSCategoria;
using WSProducto = SoftBodBusiness.SoftWSProducto;

namespace SoftBodWA
{
    public partial class Productos : System.Web.UI.Page
    {
        private ProductoBO productoBO;
        private CategoriaBO categoriaBO;
        private DetalleVentaBO detalleventaBO;

        // Cache de productos - solo se carga cuando es null o se fuerza recarga
        private List<WSProducto.productoDTO> ListaProductos
        {
            get
            {
                if (ViewState["ListaProductos"] == null)
                {
                    ViewState["ListaProductos"] = productoBO.listarTodosProductos();
                }
                return (List<WSProducto.productoDTO>)ViewState["ListaProductos"];
            }
            set
            {
                ViewState["ListaProductos"] = value;
            }
        }

        private List<WSCategoria.categoriaDTO> ListaCategoria
        {
            get
            {
                if (ViewState["ListaCategoria"] == null)
                {
                    ViewState["ListaCategoria"] = categoriaBO.listarTodasCategorias();
                }
                return (List<WSCategoria.categoriaDTO>)ViewState["ListaCategoria"];
            }
            set
            {
                ViewState["ListaCategoria"] = value;
            }
        }

        public Productos()
        {
            detalleventaBO = new DetalleVentaBO();
            productoBO = new ProductoBO();
            categoriaBO = new CategoriaBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarProductos();
                CargarCategoria();
                CargarCategoriaModal();
            }
        }

        private void CargarProductos()
        {
            var productos = ListaProductos;

            if (productos != null && productos.Any())
            {
                rptProducto.DataSource = productos;
                rptProducto.DataBind();
                rptProducto.Visible = true;
                pnlSinProductos.Visible = false;
                ActualizarResumenProductos(productos);
            }
            else
            {
                rptProducto.Visible = false;
                pnlSinProductos.Visible = true;
            }
        }

        // Método para forzar recarga de datos desde BD
        private void RecargarDatosYActualizar()
        {
            ViewState["ListaProductos"] = null; // Invalida cache
            ViewState["ListaCategoria"] = null; // Invalida cache de categorías
            CargarProductos();
            CargarCategoria();
            CargarCategoriaModal();
        }

        private void CargarCategoria()
        {
            var categorias = ListaCategoria;

            ddlCategoriaFiltro.DataSource = categorias;
            ddlCategoriaFiltro.DataTextField = "descripcion";
            ddlCategoriaFiltro.DataValueField = "categoriaId";
            ddlCategoriaFiltro.DataBind();
        }

        private void CargarCategoriaModal()
        {
            ddlCategoria.DataSource = ListaCategoria;
            ddlCategoria.DataTextField = "descripcion";
            ddlCategoria.DataValueField = "categoriaId";
            ddlCategoria.DataBind();
        }

        private void CargarCategoriaEdit()
        {
            ddlCategoriaEdit.DataSource = ListaCategoria;
            ddlCategoriaEdit.DataTextField = "descripcion";
            ddlCategoriaEdit.DataValueField = "categoriaId";
            ddlCategoriaEdit.DataBind();
        }

        private void ActualizarResumenProductos(List<WSProducto.productoDTO> productos)
        {
            int activos = productos.Count(p => p.activo == true);
            int stockBajo = productos.Count(p => p.stock <= p.stockMinimo);
            double valorInventario = productos.Sum(p => p.stock * p.precioUnitario);

            lblProductosActivos.InnerText = activos.ToString();
            lblStockBajo.InnerText = stockBajo.ToString();
            lblValorInventario.InnerText = $"S/. {valorInventario:N2}";
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            try
            {
                string nombre = txtNombreProducto.Text.Trim();
                string categoriaIdStr = ddlCategoria.SelectedValue;
                string nuevaCategoria = txtNuevaCategoria.Text.Trim();
                string precioStr = txtPrecio.Text.Trim();
                string stockInicialStr = txtStockInicial.Text.Trim();
                string stockMinimoStr = txtStockMinimo.Text.Trim();
                string unidadMedida = ddlMedida.SelectedValue;

                // ✅ VALIDACIÓN: Debe seleccionar una categoría O crear una nueva, no ambas ni ninguna
                if (!ValidarCategoriaUnica(categoriaIdStr, nuevaCategoria, out string msgErrorCategoria))
                {
                    MostrarMensajeError(msgErrorCategoria);
                    return;
                }

                // Determinar el ID de categoría a usar
                int categoriaId = 0;
                if (!string.IsNullOrEmpty(categoriaIdStr) && categoriaIdStr != "0")
                {
                    categoriaId = int.Parse(categoriaIdStr);
                }

                // Validaciones existentes
                if (string.IsNullOrWhiteSpace(nombre) ||
                    string.IsNullOrWhiteSpace(precioStr) ||
                    string.IsNullOrWhiteSpace(stockInicialStr) ||
                    string.IsNullOrWhiteSpace(stockMinimoStr))
                {
                    MostrarMensajeError("Por favor complete todos los campos obligatorios.");
                    return;
                }

                if (!ValidarNumericosProducto(precioStr, stockInicialStr, stockMinimoStr, out string msgError))
                {
                    MostrarMensajeError(msgError);
                    return;
                }

                if (!ValidarProductoUnico(nombre, out msgError))
                {
                    MostrarMensajeError(msgError);
                    return;
                }

                double precio = double.Parse(precioStr);
                int stockInicial = int.Parse(stockInicialStr);
                int stockMinimo = int.Parse(stockMinimoStr);

                WSProducto.categoriaDTO categDTO = new WSProducto.categoriaDTO();

                if (!string.IsNullOrEmpty(nuevaCategoria))
                {
                    // Crear nueva categoría
                    categDTO.categoriaId = categoriaBO.insertarCategoria(nuevaCategoria);
                }
                else
                {
                    // Usar categoría existente
                    categDTO.categoriaId = categoriaId;
                }
                categDTO.categoriaIdSpecified = true;

                productoBO.insertarProducto(categDTO, nombre, precio, unidadMedida, stockInicial, stockMinimo, true);

                // ✅ CRÍTICO: Invalidar cache ANTES de mostrar mensaje
                ViewState["ListaProductos"] = null;
                ViewState["ListaCategoria"] = null;

                LimpiarCamposModal();
                MostrarMensajeExitoYRecargar("Producto agregado exitosamente", "modalAgregarProducto");
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al crear nuevo producto: {ex.Message}");
            }
        }

        protected void rptProducto_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            int productoId;
            switch (e.CommandName)
            {
                case "CambiarEstado":
                    productoId = Convert.ToInt32(e.CommandArgument);
                    CambiarEstadoProducto(productoId);
                    break;
                case "AjustarStock":
                    productoId = Convert.ToInt32(e.CommandArgument);
                    MostrarModalAjustarStock(productoId);
                    break;
                case "Editar":
                    productoId = Convert.ToInt32(e.CommandArgument);
                    MostrarModalEditar(productoId);
                    break;
                case "EliminarProducto":
                    string[] args = e.CommandArgument.ToString().Split('|');
                    MostrarModalEliminar(args);
                    break;
            }
        }
        private void MostrarModalAjustarStock(int productoId)
        {
            hdnProductoIdAjustar.Value = productoId.ToString();
            Session["productoIdStock"] = productoId;

            ScriptManager.RegisterStartupScript(this, GetType(), "abrirModalAjustar",
                "var modal = new bootstrap.Modal(document.getElementById('modalAjustarStock')); modal.show();", true);
        }

        private void MostrarModalEditar(int productoId)
        {
            try
            {
                var producto = ListaProductos.FirstOrDefault(p => p.productoId == productoId);
                if (producto != null)
                {
                    hdnProductoIdEditar.Value = producto.productoId.ToString();
                    txtNombreProductoEdit.Text = producto.nombre;
                    txtPrecioEdit.Text = producto.precioUnitario.ToString("F2");
                    txtStockEdit.Text = producto.stock.ToString();
                    txtStockMinimoEdit.Text = producto.stockMinimo.ToString();

                    CargarCategoriaEdit();

                    if (producto.categoria != null)
                    {
                        ddlCategoriaEdit.SelectedValue = producto.categoria.categoriaId.ToString();
                    }

                    updEditarProducto.Update();

                    ScriptManager.RegisterStartupScript(this, GetType(), "showEditarProducto",
                        "var myModal = new bootstrap.Modal(document.getElementById('modalEditarProducto')); myModal.show();", true);
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al cargar datos del producto: {ex.Message}");
            }
        }

        private void MostrarModalEliminar(string[] args)
        {
            // args[0] = productoId, args[1] = nombre
            hdnProductoIdEliminar.Value = args[0];
            ltNombreProductoEliminar.Text = args[1];

            updEliminarProducto.Update();

            ScriptManager.RegisterStartupScript(this, GetType(), "abrirEliminarProducto",
                "var modal = new bootstrap.Modal(document.getElementById('modalEliminarProducto')); modal.show();", true);
        }

        private void CambiarEstadoProducto(int productoId)
        {
            try
            {
                var producto = productoBO.obtenerProductoPorId(productoId);

                if (producto != null)
                {
                    bool nuevoEstado = !producto.activo;
                    producto.activo = nuevoEstado;
                    producto.activoSpecified = true;

                    int resultado = productoBO.modificarProducto(producto);

                    if (resultado > 0)
                    {
                        // ✅ CRÍTICO: Invalidar cache ANTES de recargar
                        ViewState["ListaProductos"] = null;
                        RecargarDatosYActualizar();
                    }
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al cambiar estado: {ex.Message}");
            }
        }

        protected void btnAjustarStock_Click(object sender, EventArgs e)
        {
            try
            {
                int productoId = int.Parse(Session["productoIdStock"].ToString());
                string stockMode = hdnStockMode.Value;
                int cantidad = int.Parse(txtCantidadAjustar.Text.Trim());

                if (cantidad <= 0)
                {
                    MostrarMensajeError("La cantidad debe ser mayor a 0");
                    return;
                }

                var producto = productoBO.obtenerProductoPorId(productoId);

                if (producto != null)
                {
                    int nuevoStock = producto.stock;

                    if (stockMode == "Agregar")
                    {
                        nuevoStock += cantidad;
                    }
                    else if (stockMode == "Reducir")
                    {
                        nuevoStock -= cantidad;

                        if (nuevoStock < 0)
                        {
                            MostrarMensajeError("No hay suficiente stock para reducir esa cantidad");
                            return;
                        }
                    }

                    producto.stock = nuevoStock;
                    producto.stockSpecified = true;

                    int resultado = productoBO.modificarProducto(producto);

                    if (resultado > 0)
                    {
                        // ✅ CRÍTICO: Invalidar cache ANTES de mostrar mensaje
                        ViewState["ListaProductos"] = null;

                        txtCantidadAjustar.Text = "";
                        string accion = stockMode == "Agregar" ? "agregado" : "reducido";
                        MostrarMensajeExitoYRecargar($"Stock {accion} exitosamente", "modalAjustarStock");
                    }
                }
            }
            catch (FormatException)
            {
                MostrarMensajeError("Por favor ingrese valores numéricos válidos");
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al ajustar stock: {ex.Message}");
            }
        }

        protected void btnActualizarProducto_Click(object sender, EventArgs e)
        {
            try
            {
                if (string.IsNullOrWhiteSpace(txtNombreProductoEdit.Text))
                {
                    MostrarMensajeError("Por favor ingrese el nombre del producto");
                    return;
                }

                if (int.Parse(txtStockMinimoEdit.Text.Trim()) < 0)
                {
                    MostrarMensajeError("El stock mínimo no puede ser negativo");
                    return;
                }

                if (double.Parse(txtPrecioEdit.Text.Trim()) <= 0.00)
                {
                    MostrarMensajeError("El precio debe ser mayor a cero");
                    return;
                }

                int productoId = int.Parse(hdnProductoIdEditar.Value);
                string nombre = txtNombreProductoEdit.Text.Trim();
                int categoriaId = int.Parse(ddlCategoriaEdit.SelectedValue);
                double precio = double.Parse(txtPrecioEdit.Text.Trim());
                int stock = int.Parse(txtStockEdit.Text.Trim());
                int stockMinimo = int.Parse(txtStockMinimoEdit.Text.Trim());

                WSProducto.categoriaDTO categDTO = new WSProducto.categoriaDTO();
                categDTO.categoriaId = categoriaId;
                categDTO.categoriaIdSpecified = true;

                WSProducto.productoDTO productoDTO = productoBO.obtenerProductoPorId(productoId);

                productoDTO.nombre = nombre;
                productoDTO.precioUnitario = precio;
                productoDTO.precioUnitarioSpecified = true;
                productoDTO.stock = stock;
                productoDTO.stockSpecified = true;
                productoDTO.stockMinimo = stockMinimo;
                productoDTO.stockMinimoSpecified = true;
                productoDTO.activo = true;
                productoDTO.activoSpecified = true;
                productoDTO.categoria = categDTO;

                int resultado = productoBO.modificarProducto(productoDTO);

                if (resultado > 0)
                {
                    // ✅ CRÍTICO: Invalidar cache ANTES de mostrar mensaje
                    ViewState["ListaProductos"] = null;

                    MostrarMensajeExitoYRecargar("Producto actualizado exitosamente", "modalEditarProducto");
                }
                else
                {
                    MostrarMensajeError("Error al actualizar el producto");
                }
            }
            catch (FormatException)
            {
                MostrarMensajeError("Por favor verifique que los valores numéricos sean correctos");
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al actualizar producto: {ex.Message}");
            }
        }

        protected void btnConfirmarEliminacion_Click(object sender, EventArgs e)
        {
            try
            {
                int idProducto = int.Parse(hdnProductoIdEliminar.Value);
                WSProducto.productoDTO producto = productoBO.obtenerProductoPorId(idProducto);

                if (producto != null)
                {
                    var tieneVentas = detalleventaBO.listarDetallesVentaPorProducto(producto.productoId).Count();

                    if (tieneVentas > 0)
                    {
                        MostrarMensajeError("El producto tiene ventas asociadas, no se puede eliminar");
                    }
                    else
                    {
                        int resultado = productoBO.eliminarProducto(idProducto);

                        // ✅ CRÍTICO: Invalidar cache ANTES de mostrar mensaje
                        ViewState["ListaProductos"] = null;

                        MostrarMensajeExitoYRecargar("Producto eliminado exitosamente", "modalEliminarProducto");
                    }
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error: {ex.Message}");
            }
        }

        private void LimpiarCamposModal()
        {
            txtNombreProducto.Text = "";
            txtPrecio.Text = "";
            txtStockInicial.Text = "";
            txtStockMinimo.Text = "";
            txtNuevaCategoria.Text = "";
            ddlCategoria.SelectedIndex = 0;
        }

        private void CargarProductosFiltro(bool activo, string categoria, string nombre)
        {
            var lista = productoBO.listarTodosConFiltroProductos(activo, categoria, nombre);

            if (lista != null && lista.Any())
            {
                rptProducto.DataSource = lista;
                rptProducto.DataBind();
                rptProducto.Visible = true;
                pnlSinProductos.Visible = false;
            }
            else
            {
                rptProducto.Visible = false;
                pnlSinProductos.Visible = true;
            }
        }

        protected void btnBuscarProducto_Click(object sender, EventArgs e)
        {
            string texto = txtBuscarProducto.Text.Trim();
            string categoriaNombre = ddlCategoriaFiltro.SelectedItem.Text;

            if (ddlCategoriaFiltro.SelectedIndex == 0)
                categoriaNombre = "";

            CargarProductosFiltro(true, categoriaNombre, texto);
        }

        // ===== MÉTODOS DE VALIDACIÓN =====

        private bool ValidarCategoriaUnica(string categoriaIdStr, string nuevaCategoria, out string mensaje)
        {
            mensaje = "";

            bool categoriaSeleccionada = !string.IsNullOrEmpty(categoriaIdStr) && categoriaIdStr != "0" && categoriaIdStr != "";
            bool nuevaCategoriaIngresada = !string.IsNullOrEmpty(nuevaCategoria);

            // Caso 1: No seleccionó ninguna
            if (!categoriaSeleccionada && !nuevaCategoriaIngresada)
            {
                mensaje = "Debe seleccionar una categoría existente o ingresar una nueva categoría.";
                return false;
            }

            // Caso 2: Seleccionó ambas
            if (categoriaSeleccionada && nuevaCategoriaIngresada)
            {
                mensaje = "Debe seleccionar solo una opción: categoría existente O nueva categoría, no ambas.";
                return false;
            }

            return true;
        }

        private bool ValidarNumericosProducto(string precio, string stockInicial, string stockMinimo, out string mensaje)
        {
            mensaje = "";

            if (!double.TryParse(precio, out double precioNum) || precioNum <= 0)
            {
                mensaje = "El precio debe ser un número mayor a 0.";
                return false;
            }

            if (!int.TryParse(stockInicial, out int stockIni) || stockIni < 0)
            {
                mensaje = "El stock inicial debe ser un número mayor o igual a 0.";
                return false;
            }

            if (!int.TryParse(stockMinimo, out int stockMin) || stockMin < 0)
            {
                mensaje = "El stock mínimo debe ser un número mayor o igual a 0.";
                return false;
            }

            if (int.Parse(stockMinimo) > int.Parse(stockInicial))
            {
                mensaje = "El stock mínimo no puede ser mayor al stock inicial.";
                return false;
            }

            return true;
        }

        private bool ValidarProductoUnico(string nombre, out string mensaje)
        {
            mensaje = "";

            if (ListaProductos.Any(p => p.nombre.Equals(nombre, StringComparison.OrdinalIgnoreCase)))
            {
                mensaje = "Ya existe un producto con este nombre.";
                return false;
            }

            return true;
        }

        // ===== MÉTODOS DE UI =====

        private void MostrarMensajeError(string mensaje)
        {
            ScriptManager.RegisterStartupScript(this, this.GetType(), "error",
                $"alert('{mensaje}');", true);
        }

        private void MostrarMensajeExitoYRecargar(string mensaje, string modalId)
        {
            ScriptManager.RegisterStartupScript(this, this.GetType(), "exitoYRecarga",
                $@"var modal = bootstrap.Modal.getInstance(document.getElementById('{modalId}')); 
                   if(modal) modal.hide();
                   document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
                   document.body.classList.remove('modal-open');
                   document.body.style.overflow = '';
                   alert('{mensaje}');
                   window.location.href = window.location.pathname;", true);
        }
    }
}
