using SoftBodBusiness;
using SoftBodBusiness.SoftWSCategoria;
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

        public Productos()
        {
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
            var productos = listaProductos;

            rptProducto.DataSource = productos;
            rptProducto.DataBind();
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
                WSProducto.categoriaDTO categDTO = new WSProducto.categoriaDTO();
                categDTO.categoriaId = categoriaId;
                categDTO.categoriaIdSpecified = true;

                productoBO.insertarProducto(categDTO, nombre, precio, "UNIDAD", stockInicial,stockMinimo,true);

                LimpiarCamposModal();

                // ✅ Mostrar mensaje y cerrar el modal correctamente (compatible con UpdatePanel)
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alertaProducto",
                    "alert('Producto agregado exitosamente'); " +
                    "var modal = bootstrap.Modal.getInstance(document.getElementById('modalAgregarProducto')); " +
                    "if(modal) modal.hide();", true);

                CargarProductos();
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

    }
}