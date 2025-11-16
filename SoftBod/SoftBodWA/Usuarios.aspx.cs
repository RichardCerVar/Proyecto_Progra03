using SoftBodBusiness;
using SoftBodBusiness.SoftWSUsuario;
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
            
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            //listaUsuarios = usuarioBO.listarTodosUsuarios();
            if (!IsPostBack)
                CargarOperarios();
        }

        private void CargarOperarios()
        {
            listaUsuarios = usuarioBO.listarTodosUsuarios();
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
            
            string[] args = e.CommandArgument.ToString().Split('|');
            string nombre = args[0];
            
            string message = "";
            bool reload = false;


            
                switch (e.CommandName)
            {
                case "Editar":
                    string usuario = args[1];
                    string email = args[2];
                    string telefono = args[3];
                    

                    txtEditNombreCompleto.Text = nombre;
                    txtEditUsuario.Text = usuario;
                    txtEditEmail.Text = email;
                    txtEditTelefono.Text = telefono;

                    hdnEditUsuarioID.Value = usuario;

                    updEditarOperario.Update();

                    string scriptPago = "var myModal = new bootstrap.Modal(document.getElementById('modalEditarOperario')); myModal.show();";
                    ScriptManager.RegisterStartupScript(this, GetType(), "showEditarOperario", scriptPago, true);

                    
                    break;

                case "Eliminar":
                    ltNombreEliminar.Text = nombre;
                    string scriptEliminar = "var myModal = new bootstrap.Modal(document.getElementById('modalEliminarOperario')); myModal.show();";
                    ScriptManager.RegisterStartupScript(this, GetType(), "showEliminarOperario", scriptEliminar, true);
                    break;

                case "ToggleActivo":
                    try
                    {
                        string usuarioID = args[1];
                        string correo = args[2];

                        WSUsuario.usuarioDTO aux = usuarioBO.obtenerUsuarioPorCorreo(correo);
                        bool nuevoEstado = !aux.activo; 

                        //logica BO
                        usuarioBO.eliminarLogicoUsuario(aux.usuarioId,aux.usuario,aux.correo,aux.tipoUsuarios.ToString(),aux.contrasenha,aux.nombre,aux.telefono,aux.activo);

                        CargarOperarios();
                    }
                    catch (Exception ex)
                    {
                        ScriptManager.RegisterStartupScript(Page, this.GetType(), "errorSwitch",
                           $"alert('Error al actualizar el estado: {ex.Message}');", true);
                    }
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

       

        protected void btnGuardarCambios_Click(object sender, EventArgs e)
        {
            
        }
        protected void btnConfirmarEliminacion_Click(object sender, EventArgs e)
        {
            
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

        //para lo del activar y deasctivar el boton
        


    }
}