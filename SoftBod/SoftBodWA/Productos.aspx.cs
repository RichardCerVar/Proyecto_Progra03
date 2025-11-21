using SoftBodBusiness;
using SoftBodBusiness.SoftWSCategoria;
using SoftBodBusiness.SoftWSProducto;
using SoftBodBusiness.SoftWSUsuario;
using System;
using System.Collections.Generic;
using System.Data.SqlTypes;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WSCategoria = SoftBodBusiness.SoftWSCategoria;
using WSProducto = SoftBodBusiness.SoftWSProducto;

namespace SoftBodWA
{
    public partial class Productos : System.Web.UI.Page
    {
        private List<WSProducto.productoDTO> listaProductos;
        private ProductoBO productoBO;
        private CategoriaBO categoriaBO;
        private List<WSCategoria.categoriaDTO> listaCategoria;
        private DetalleVentaBO detalleventaBO;

        public Productos()
        {
            detalleventaBO = new DetalleVentaBO();
            productoBO = new ProductoBO();
            categoriaBO = new CategoriaBO();
            listaProductos = productoBO.listarTodosProductos();
            listaCategoria = categoriaBO.listarTodasCategorias();
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
            var productos = productoBO.listarTodosProductos();
            rptProducto.DataSource = productos;
            rptProducto.DataBind();

            ActualizarResumenProductos(listaProductos);
        }

        private void CargarCategoria()
        {
            var categorias = listaCategoria;

            ddlCategoriaFiltro.DataSource = categorias;
            ddlCategoriaFiltro.DataTextField = "descripcion";
            ddlCategoriaFiltro.DataValueField = "categoriaId";
            ddlCategoriaFiltro.DataBind();
        }

        private void CargarCategoriaModal()
        {
            ddlCategoria.DataSource = listaCategoria;
            ddlCategoria.DataTextField = "descripcion";
            ddlCategoria.DataValueField = "categoriaId";
            ddlCategoria.DataBind();
        }

        private void CargarCategoriaEdit()
        {
            ddlCategoriaEdit.DataSource = listaCategoria;
            ddlCategoriaEdit.DataTextField = "descripcion";
            ddlCategoriaEdit.DataValueField = "categoriaId";
            ddlCategoriaEdit.DataBind();
        }

        private void ActualizarResumenProductos(List<WSProducto.productoDTO> productos)
        {
            // 1. Productos activos
            int activos = productos.Count(p => p.activo == true);

            // 2. Stock bajo (stock <= stockMinimo)
            int stockBajo = productos.Count(p => p.stock <= p.stockMinimo);

            // 3. Valor total del inventario
            double valorInventario = productos.Sum(p => p.stock * p.precioUnitario);

            // Asignar a los labels
            lblProductosActivos.InnerText = activos.ToString();
            lblStockBajo.InnerText = stockBajo.ToString();
            lblValorInventario.InnerText = $"S/. {valorInventario:N2}";
        }

        protected void btnAgregarProducto_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalAgregarNuevoProducto(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "", script, true);

        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            try
            {
                // 🔹 Capturar datos del formulario
                string nombre = txtNombreProducto.Text.Trim();
                int categoriaId = int.Parse(ddlCategoria.SelectedValue);
                string nuevaCategoria = txtNuevaCategoria.Text.Trim();
                double precio = double.Parse(txtPrecio.Text.Trim());
                int stockInicial = int.Parse(txtStockInicial.Text.Trim());
                int stockMinimo = int.Parse(txtStockMinimo.Text.Trim());
                string unidadMedida = ddlMedida.SelectedValue;

                // 1️⃣ Validar campos completos
                if (!ValidarCamposProducto(nombre, txtPrecio.Text.Trim(), txtStockInicial.Text.Trim(),
                    txtStockMinimo.Text.Trim(), categoriaId, out string msgError))
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "valCampos",
                        $"alert('{msgError}');", true);
                    return;
                }

                // 2️⃣ Validar datos numéricos
                if (!ValidarNumericosProducto(txtPrecio.Text.Trim(), txtStockInicial.Text.Trim(),
                    txtStockMinimo.Text.Trim(), out msgError))
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "valNumeros",
                        $"alert('{msgError}');", true);
                    return;
                }

                // 3️⃣ Validar nombre único
                if (!ValidarProductoUnico(nombre, out msgError))
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "valUnico",
                        $"alert('{msgError}');", true);
                    return;
                }
                WSProducto.categoriaDTO categDTO = new WSProducto.categoriaDTO();
                if (!string.IsNullOrEmpty(nuevaCategoria))
                {
                    categDTO.categoriaId = categoriaBO.insertarCategoria(nuevaCategoria);
                }
                else
                {
                    categDTO.categoriaId = categoriaId;
                }
                categDTO.categoriaIdSpecified = true;


                productoBO.insertarProducto(categDTO, nombre, precio, unidadMedida, stockInicial, stockMinimo, true);

                LimpiarCamposModal();

                // ✅ Mostrar mensaje y cerrar el modal correctamente (compatible con UpdatePanel)
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alertaProducto",
                    "alert('Producto agregado exitosamente'); " +
                    "var modal = bootstrap.Modal.getInstance(document.getElementById('modalAgregarProducto')); " +
                    "if(modal) modal.hide();", true);

                Response.Redirect(Request.RawUrl);

                // Opcional: cerrar modal con JavaScript
                ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModal", "$('#modalAgregarProducto').modal('hide');", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorProducto",
                     $"alert('Error al crear nuevo producto: {ex.Message}');", true);
            }
        }

        protected void rptProducto_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            int productoId;
            switch (e.CommandName)
            {
                case "CambiarEstado":
                    productoId = Convert.ToInt32(e.CommandArgument);
                    // Cambiar estado activo/inactivo del producto
                    CambiarEstadoProducto(productoId);
                    break;
                case "AjustarStock":
                    productoId = Convert.ToInt32(e.CommandArgument);
                    hdnProductoIdAjustar.Value = productoId.ToString();
                    Session["productoIdStock"] = productoId;
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "abrirModalAjustar",
                        "setTimeout(function() { " +
                        "var modal = new bootstrap.Modal(document.getElementById('modalAjustarStock')); " +
                        "modal.show(); " +
                        "}, 200);", true);
                    break;
                case "Editar":
                    productoId = Convert.ToInt32(e.CommandArgument);
                    CargarDatosProductoParaEditar(productoId);
                    // Abrir modal con JavaScript después de cargar los datos
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "abrirModalEditar",
                        "setTimeout(function() { " +
                        "var modal = new bootstrap.Modal(document.getElementById('modalEditarProducto')); " +
                        "modal.show(); " +
                        "}, 200);", true);
                    break;
                case "EliminarProducto":
                    string[] args = e.CommandArgument.ToString().Split('|');
                    // ✅ CommandArgument: id|producto
                    string idEliminar = args[0];
                    string productoEliminar = args[1];

                    hdnProductoIdEliminar.Value = idEliminar;
                    ltNombreProductoEliminar.Text = productoEliminar;

                    updEliminarProducto.Update();

                    ScriptManager.RegisterStartupScript(this, GetType(), "abrirEliminarProducto",
                        "setTimeout(function() { " +
                        "var modal = new bootstrap.Modal(document.getElementById('modalEliminarProducto')); " +
                        "modal.show(); " +
                        "}, 200);", true);
                    break;
            }
        }

        private void CargarDatosProductoParaEditar(int productoId)
        {
            try
            {
                var producto = listaProductos.FirstOrDefault(p => p.productoId == productoId);
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

                }
                else
                {
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "debugProducto",
                        $"alert('Producto no encontrado. ID: {productoId}\\nTotal productos: {listaProductos?.Count ?? 0}');", true);
                }


            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorCargar",
                    $"alert('Error al cargar datos del producto: {ex.Message}\\n\\nStack: {ex.StackTrace}');", true);
            }

        }

        private void CambiarEstadoProducto(int productoId)
        {
            try
            {
                // Obtener el producto actual
                var producto = productoBO.obtenerProductoPorId(productoId);

                if (producto != null)
                {
                    // Cambiar el estado
                    bool nuevoEstado = !producto.activo;
                    producto.activo = nuevoEstado;
                    producto.activoSpecified = true;

                    // Llamar al método de negocio para actualizar el estado
                    int resultado = productoBO.modificarProducto(producto);

                    if (resultado > 0)
                    {
                        string mensaje = nuevoEstado ? "Producto activado" : "Producto desactivado";
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "estadoCambiado",
                            $"alert('{mensaje} exitosamente');", true);
                        Response.Redirect(Request.RawUrl);
                    }
                }
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorEstado",
                    $"alert('Error al cambiar estado: {ex.Message}');", true);
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
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "errorCantidad",
                        "alert('La cantidad debe ser mayor a 0');", true);
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
                            ScriptManager.RegisterStartupScript(this, this.GetType(), "errorStock",
                                "alert('No hay suficiente stock para reducir esa cantidad');", true);
                            return;
                        }
                    }
                    producto.stock = nuevoStock;
                    producto.stockSpecified = true;

                    int resultado = productoBO.modificarProducto(producto);

                    if (resultado > 0)
                    {
                        txtCantidadAjustar.Text = "";
                        string accion = stockMode == "Agregar" ? "agregado" : "reducido";
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "stockAjustado",
                            $"alert('Stock {accion} exitosamente'); " +
                            "var modal = bootstrap.Modal.getInstance(document.getElementById('modalAjustarStock')); " +
                            "if(modal) modal.hide(); " +
                            "document.querySelector('.modal-backdrop')?.remove();", true);
                        Response.Redirect(Request.RawUrl);
                    }
                }
            }
            catch (FormatException)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorFormato",
                    "alert('Por favor ingrese valores numéricos válidos');", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorAjuste",
                    $"alert('Error al ajustar stock: {ex.Message}');", true);
            }
        }

        protected void btnActualizarProducto_Click(object sender, EventArgs e)
        {
            try
            {
                if (string.IsNullOrWhiteSpace(txtNombreProductoEdit.Text))
                {
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "errorNombre",
                        "alert('Por favor ingrese el nombre del producto');", true);
                    return;
                }

                if (int.Parse(txtStockMinimoEdit.Text.Trim()) < 0)
                {
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "errorNombre",
                        "alert('El stock mínimo no puede ser negativo');", true);
                    return;
                }

                if (double.Parse(txtPrecioEdit.Text.Trim()) < 0.00)
                {
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "errorNombre",
                        "alert('El precio no puede ser negativo');", true);
                    return;
                }

                if (double.Parse(txtPrecioEdit.Text.Trim()) == 0.00)
                {
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "errorNombre",
                        "alert('El precio no puede ser cero');", true);
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
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "productoActualizado",
                        "alert('Producto actualizado exitosamente'); " +
                        "var modal = bootstrap.Modal.getInstance(document.getElementById('modalEditarProducto')); " +
                        "if(modal) modal.hide(); " +
                        "document.querySelector('.modal-backdrop')?.remove();", true);
                    Response.Redirect(Request.RawUrl);
                }
                else
                {
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "errorActualizar",
                        "alert('Error al actualizar el producto');", true);
                }
            }
            catch (FormatException)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorFormato",
                    "alert('Por favor verifique que los valores numéricos sean correctos');", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorActualizar",
                    $"alert('Error al actualizar producto: {ex.Message}');", true);
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
                    string mensaje = "";
                    if (tieneVentas > 0)
                    {
                        mensaje = "El producto tiene ventas asociadas, no se puede eliminar.";
                    }
                    else
                    {
                        int resultado = productoBO.eliminarProducto(idProducto);
                        mensaje = "Operario eliminado exitosamente.";
                    }
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alertaEliminacion",
                       $@"var modal = bootstrap.Modal.getInstance(document.getElementById('modalEliminarProducto')); 
                       if(modal) modal.hide();
                       document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
                       document.body.classList.remove('modal-open');
                       document.body.style.overflow = '';
                       alert('{mensaje}');
                       window.location.href = window.location.pathname;", true);
                    Response.Redirect(Request.RawUrl);
                }
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorEliminar",
                    $"alert('Error: {ex.Message}');", true);
            }
        }

        private void LimpiarCamposModal()
        {
            // Limpiar campos después de agregar
            txtNombreProducto.Text = "";
            txtPrecio.Text = "";
            txtStockInicial.Text = "";
            txtStockMinimo.Text = "";
            txtNuevaCategoria.Text = "";
            ddlCategoria.SelectedIndex = 0;
        }

        private void cargarProductosFiltro(bool activo, string categoria, string nombre)
        {
            var lista = productoBO.listarTodosConFiltroProductos(activo, categoria, nombre);
            rptProducto.DataSource = lista;
            rptProducto.DataBind();
        }

        protected void btnBuscarProducto_Click(object sender, EventArgs e)
        {
            string texto = txtBuscarProducto.Text.Trim();
            string categoriaNombre = ddlCategoriaFiltro.SelectedItem.Text;

            // Si quieres que cuando el usuario no elija nada,
            // el texto de categoría sea vacío:
            if (ddlCategoriaFiltro.SelectedIndex == 0) // opción "Seleccione..."
                categoriaNombre = "";

            cargarProductosFiltro(true, categoriaNombre, texto);
        }

        // VALIDACIONES

        private bool ValidarCamposProducto(string nombre, string precio, string stockInicial, string stockMinimo, int categoriaId, out string mensaje)
        {
            mensaje = "";

            if (string.IsNullOrWhiteSpace(nombre) ||
                string.IsNullOrWhiteSpace(precio) ||
                string.IsNullOrWhiteSpace(stockInicial) ||
                string.IsNullOrWhiteSpace(stockMinimo))
            {
                mensaje = "Por favor complete todos los campos.";
                return false;
            }

            if (categoriaId <= 0)
            {
                mensaje = "Debe seleccionar una categoría válida.";
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

            if (listaProductos.Any(p =>
                p.nombre.Equals(nombre, StringComparison.OrdinalIgnoreCase)))
            {
                mensaje = "Ya existe un producto con este nombre.";
                return false;
            }

            return true;
        }
    }
}