using SoftBodBusiness;
using System;
using System.Collections.Generic;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using WSUsuario = SoftBodBusiness.SoftWSUsuario;
namespace SoftBodWA
{
    public partial class Usuarios : System.Web.UI.Page
    {
        private List<WSUsuario.usuarioDTO> listaUsuarios;
        private UsuarioBO usuarioBO;

        public Usuarios()
        {
            usuarioBO = new UsuarioBO();
            listaUsuarios = usuarioBO.listarTodosUsuarios();
        }
        protected void Page_Load(object sender, EventArgs e)
        {

            if (!IsPostBack)
                CargarOperarios();
        }

        private void CargarOperarios()
        {
            // Datos de ejemplo
            var operarios = listaUsuarios;

            rptUsuarios.DataSource = operarios;
            rptUsuarios.DataBind();

            // Totales
            lblTotalOperarios.InnerText = operarios.Count.ToString();
            lblActivos.InnerText = operarios.FindAll(o => o.activo).Count.ToString();
        }

        protected void rptUsuarios_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            string usuario = e.CommandArgument.ToString();

            switch (e.CommandName)
            {
                case "Editar":
                    Response.Write($"<script>alert('Editar operario: {usuario}');</script>");
                    break;
                case "Eliminar":
                    Response.Write($"<script>alert('Operario eliminado: {usuario}');</script>");
                    break;
            }
        }

        public class UsuarioDTO
        {
            public string Nombre { get; set; }
            public string Usuario { get; set; }
            public string Correo { get; set; }
            public string Telefono { get; set; }
            public bool Activo { get; set; }
        }


        protected void btnAgregarOperario_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalAgregarOperario() }; ";
            ClientScript.RegisterStartupScript(this.GetType(), "", script, true);
        }

        protected void btnCrearOperario_Click(object sender, EventArgs e)
        {
            try
            {
                string nombre = txtNombreCompleto.Text.Trim();
                string usuario = txtUsuario.Text.Trim();
                string correo = txtEmail.Text.Trim();
                string telefono = txtTelefono.Text.Trim();
                string contraseñaTemporal = txtContraseñaTemporal.Text.Trim();
                usuarioBO.insertarUsuario(usuario, correo, "OPERARIO", contraseñaTemporal, nombre, telefono, true);
                // lógica real para guardar el nuevo operario

                LimpiarCamposModal();


                // ✅ Mostrar mensaje y cerrar el modal correctamente (UpdatePanel compatible)
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alertaOperario",
                    "alert('Operario creado exitosamente'); " +
                    "var modal = bootstrap.Modal.getInstance(document.getElementById('modalAgregarOperario')); " +
                    "if(modal) modal.hide();", true);

                CargarOperarios();
                Response.Redirect(Request.RawUrl);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "errorOperario",
                     $"alert('Error al crear operario: {ex.Message}');", true);
            }
        }

        private void LimpiarCamposModal()
        {
            txtNombreCompleto.Text = "";
            txtUsuario.Text = "";
            txtEmail.Text = "";
            txtTelefono.Text = "";
            txtContraseñaTemporal.Text = "";
        }

    }
}