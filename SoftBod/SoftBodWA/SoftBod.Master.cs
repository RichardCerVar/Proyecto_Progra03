using System;
using System.IO;

namespace SoftBodWA
{
    public partial class SoftBod : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                SetActiveNav();
            }
        }

        private void SetActiveNav()
        {
            // Obtiene el nombre del archivo actual
            string current = Path.GetFileName(Request.Path).ToLowerInvariant();

            // Asegura que el atributo "class" exista y no duplique "active"
            void MarkActive(System.Web.UI.HtmlControls.HtmlAnchor anchor)
            {
                string cls = anchor.Attributes["class"] ?? "";
                if (!cls.Contains("active"))
                {
                    anchor.Attributes["class"] = (cls + " active").Trim();
                }
            }

            switch (current)
            {
                case "inicio.aspx":
                case "": // por si acceden a la raíz y rediriges
                    MarkActive(navInicio);
                    break;

                case "productos.aspx":
                    MarkActive(navProductos);
                    break;

                case "clientes.aspx":
                    MarkActive(navClientes);
                    break;

                case "reportes.aspx":
                    MarkActive(navReportes);
                    break;

                case "usuarios.aspx":
                    MarkActive(navUsuarios);
                    break;

                default:
                    // si no coincide con ninguno, no marcamos nada
                    break;
            }
        }
    }
}