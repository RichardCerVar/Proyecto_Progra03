using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

namespace SoftBodWA
{
    public partial class Usuarios : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
                CargarOperarios();
        }

        private void CargarOperarios()
        {
            // Datos de ejemplo
            var operarios = new List<UsuarioDTO>()
            {
                new UsuarioDTO { Nombre="Ana Rodríguez", Usuario="ana.rodriguez", Correo="ana@bodega.com", Telefono="987-654-321", Activo=true },
                new UsuarioDTO { Nombre="Carlos Mendoza", Usuario="carlos.mendoza", Correo="carlos@bodega.com", Telefono="912-345-678", Activo=true },
                new UsuarioDTO { Nombre="Luis García", Usuario="luis.garcia", Correo="luis@bodega.com", Telefono="998-765-432", Activo=false }
            };

            rptUsuarios.DataSource = operarios;
            rptUsuarios.DataBind();

            // Totales
            lblTotalOperarios.InnerText = operarios.Count.ToString();
            lblActivos.InnerText = operarios.FindAll(o => o.Activo).Count.ToString();
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
    }
}