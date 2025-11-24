using System;
using SoftBodBusiness;
using SoftBodBusiness.SoftBodWSServices;


namespace SoftBodWA
{
    public partial class Login : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;
        public Login()
        {
            usuarioBO = new UsuarioBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string email = txtEmail.Text.Trim();
            string password = txtPassword.Text.Trim();

            if(email == "" || password == "")
            {
                lblMensaje.Text = "Por favor, complete todos los campos.";
                return;
            }

            usuarioDTO usuario = usuarioBO.obtenerCuentaUsuario(email, password);
            if (usuario != null)
            {
                Session["UsuarioId"] = usuario.usuarioId;
                Session["RolUsuario"] = usuario.tipoUsuarios.ToString();
                Session["NombreUsuario"] = usuario.usuario; //nombre de usuario
                Response.Redirect("Inicio.aspx");
            }
            else
            {
                lblMensaje.Text = "Correo o contraseña incorrectos.";
            }

        }
    }
}