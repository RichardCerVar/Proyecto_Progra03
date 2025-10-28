using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBodWA
{
    public partial class Productos : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        
        protected void btnAgregarProducto_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalAgregarNuevoProducto() }; ";
            ClientScript.RegisterStartupScript(this.GetType(), "", script, true);
        }

        protected void btnGuardarProducto_Click(object sender, EventArgs e)
        {
            try
            {
                string nombreProducto = txtNombreProducto.Text;
                string categoriaId = ddlCategoria.SelectedValue;
                string nuevaCategoria = txtNuevaCategoria.Text;
                decimal precio = decimal.Parse(txtPrecio.Text);
                int stockInicial = 0;
                int stockMinimo = 0;

                if (!string.IsNullOrEmpty(txtStockInicial.Text))
                    stockInicial = int.Parse(txtStockInicial.Text);

                if (!string.IsNullOrEmpty(txtStockMinimo.Text))
                    stockMinimo = int.Parse(txtStockMinimo.Text);

                // TODO: Guardar producto...

                LimpiarCamposModal();

                ScriptManager.RegisterStartupScript(this, this.GetType(), "alert",
                    "alert('Producto agregado exitosamente'); " +
                    "var modal = bootstrap.Modal.getInstance(document.getElementById('modalAgregarProducto')); " +
                    "if(modal) modal.hide();", true);
            }
            catch (Exception ex)
            {
                // También con ScriptManager para que sí se vea el error
                ScriptManager.RegisterStartupScript(this, this.GetType(), "error",
                    $"alert('Error al agregar producto: {ex.Message}');", true);
            }


        }
        private void LimpiarCamposModal()
        {
            txtNombreProducto.Text = "";
            ddlCategoria.SelectedIndex = 0;
            txtNuevaCategoria.Text = "";
            txtPrecio.Text = "";
            txtStockInicial.Text = "";
            txtStockMinimo.Text = "";
        }


        protected void btnBuscarNombreProducto_Click(object sender, EventArgs e)
        {

        }
    }
}