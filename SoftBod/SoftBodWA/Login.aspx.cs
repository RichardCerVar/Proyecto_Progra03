using System;

namespace SoftBodWA
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session.Clear();
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string usuario = txtUsuario.Text.Trim();
            string password = txtPassword.Text.Trim();

            if (usuario == "admin@bodega.com" && password == "admin123")
            {
                Session["RolUsuario"] = "Administrador";
                Session["NombreUsuario"] = "Administrador";
                Response.Redirect("Inicio.aspx");
            }
            else if (usuario == "operario@bodega.com" && password == "operario123")
            {
                Session["RolUsuario"] = "Operario";
                Session["NombreUsuario"] = "Operario";
                Response.Redirect("Inicio.aspx");
            }
            else
            {
                lblMensaje.Text = "Correo o contraseña incorrectos.";
            }
        }
    }
}