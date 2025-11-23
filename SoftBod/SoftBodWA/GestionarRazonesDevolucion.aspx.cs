using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBodBusiness;
using WSRazonDevolucion = SoftBodBusiness.SoftWSRazonDevolucion;

namespace SoftBodWA
{
    public partial class GestionarRazonesDevolucion : System.Web.UI.Page
    {
        private RazonDevolucionBO razonDevolucionBO;

        public GestionarRazonesDevolucion()
        {
            razonDevolucionBO = new RazonDevolucionBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarRazones();
            }
        }

        private void CargarRazones()
        {
            try
            {
                var razones = razonDevolucionBO.listarTodasRazonesDevolucion();

                if (razones != null && razones.Any())
                {
                    rptRazones.DataSource = razones;
                    rptRazones.DataBind();
                    pnlSinRazones.Visible = false;
                }
                else
                {
                    rptRazones.DataSource = null;
                    rptRazones.DataBind();
                    pnlSinRazones.Visible = true;
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al cargar razones: " + ex.Message, "danger");
                pnlSinRazones.Visible = true;
            }
        }

        protected void btnAgregarRazon_Click(object sender, EventArgs e)
        {
            try
            {
                string descripcion = txtDescripcionRazon.Text.Trim();

                if (string.IsNullOrWhiteSpace(descripcion))
                {
                    MostrarMensaje("La descripción no puede estar vacía", "warning");
                    return;
                }

                // Validar longitud
                if (descripcion.Length > 200)
                {
                    MostrarMensaje("La descripción no puede exceder los 200 caracteres", "warning");
                    return;
                }

                // Verificar si ya existe una razón similar
                var razonesExistentes = razonDevolucionBO.listarTodasRazonesDevolucion();
                if (razonesExistentes != null && razonesExistentes.Any(r =>
                    r.descripcion.Equals(descripcion, StringComparison.OrdinalIgnoreCase)))
                {
                    MostrarMensaje("Ya existe una razón con esta descripción", "warning");
                    return;
                }

                // Insertar la nueva razón
                int resultado = razonDevolucionBO.insertarRazonDevolucion(descripcion);

                if (resultado > 0)
                {
                    MostrarMensaje("Razón de devolución agregada exitosamente", "success");
                    txtDescripcionRazon.Text = string.Empty;
                    CargarRazones();
                }
                else
                {
                    MostrarMensaje("Error al agregar la razón de devolución", "danger");
                }
            }
            catch (Exception ex)
            {
                MostrarMensaje("Error al agregar razón: " + ex.Message, "danger");
            }
        }

        protected void btnEliminarRazon_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "Eliminar")
            {
                try
                {
                    int razonId = int.Parse(e.CommandArgument.ToString());

                    // Validar que la razón existe
                    var razon = razonDevolucionBO.obtenerRazonDevolucionPorId(razonId);
                    if (razon == null)
                    {
                        MostrarMensaje("La razón de devolución no existe", "warning");
                        return;
                    }

                    // Eliminar la razón
                    int resultado = razonDevolucionBO.eliminarRazonDevolucion(razonId);

                    if (resultado > 0)
                    {
                        MostrarMensaje("Razón de devolución eliminada exitosamente", "success");
                        CargarRazones();
                    }
                    else
                    {
                        MostrarMensaje("No se pudo eliminar la razón de devolución. Puede estar en uso.", "warning");
                    }
                }
                catch (Exception ex)
                {
                    MostrarMensaje("Error al eliminar razón: " + ex.Message, "danger");
                }
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarDevolucion.aspx");
        }

        private void MostrarMensaje(string mensaje, string tipo)
        {
            string script = $@"
                var alertDiv = document.createElement('div');
                alertDiv.className = 'alert alert-{tipo} alert-dismissible fade show position-fixed top-0 start-50 translate-middle-x mt-3';
                alertDiv.style.zIndex = '9999';
                alertDiv.innerHTML = '{mensaje}<button type=""button"" class=""btn-close"" data-bs-dismiss=""alert""></button>';
                document.body.appendChild(alertDiv);
                setTimeout(function() {{ 
                    alertDiv.remove(); 
                }}, 5000);
            ";
            ScriptManager.RegisterStartupScript(this, GetType(), "MostrarMensaje", script, true);
        }
    }
}