using SoftBodBusiness;
using System;
using System.IO;
using System.Linq;
using System.Web.UI;
using SoftBodBusiness.SoftBodWSServices;

namespace SoftBodWA
{
    public partial class SoftBod : System.Web.UI.MasterPage
    {
        private ClienteAlFiadoBO clienteBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            clienteBO = new ClienteAlFiadoBO();

            if (!IsPostBack)
            {
                SetActiveNav();
                CargarAlertas();
            }

            if (Session["RolUsuario"] == null)
            {
                Response.Redirect("Login.aspx");
                return;
            }

            string rol = Session["RolUsuario"].ToString();
            string nombre = Session["NombreUsuario"] != null ? Session["NombreUsuario"].ToString() : rol;

            lblUsuario.InnerText = $"Bienvenido, {nombre}";

            // Controlar vistas según el rol
            if (rol == "OPERARIO")
            {
                navReportes.Visible = false;
                navUsuarios.Visible = false;
            }
        }
        private void CargarAlertas()
        {
            var clientes = clienteBO.listarTodosClientesAlFiado();

            var hoy = DateTime.Now.Date;

            var alertas = clientes
                .Where(
                    c =>
                    {
                        DateTime fecha = DateTime.Parse(c.fechaDePago);
                        return c.activo
                             && c.montoDeuda > 0
                             && (fecha.Date - hoy).TotalDays <= 4;
                    })
                .Select(c =>
                {
                    var fecha = DateTime.Parse(c.fechaDePago);

                    return new
                    {
                        Nombre = c.nombre,
                        Alias = c.alias,
                        Deuda = c.montoDeuda,
                        FechaLimite = fecha,
                        DiasRestantes = (int)(fecha.Date - hoy).TotalDays,
                        Vencido = fecha.Date < hoy
                    };
                })
                .ToList();

            if (alertas.Any())
            {
                rptAlertas.DataSource = alertas;
                rptAlertas.DataBind();
                rptAlertas.Visible = true;
                pnlSinAlertas.Visible = false;
            }
            else
            {
                rptAlertas.Visible = false;
                pnlSinAlertas.Visible = true;
            }
        }


        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            Session.Clear();
            Session.Abandon();
            Response.Redirect("Login.aspx");
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

        protected void MostrarAlerta(string mensaje, string tipo)
        {
            string color = tipo == "success" ? "alert-success" : tipo == "error" ? "alert-danger" : "alert-warning";
            string script = $"document.getElementById('alertas').innerHTML = `<div class='alert {color} alert-dismissible fade show'>{mensaje}<button type='button' class='btn-close' data-bs-dismiss='alert'></button></div>`;";
            ScriptManager.RegisterStartupScript(this, GetType(), "alerta", script, true);
        }

    }
}