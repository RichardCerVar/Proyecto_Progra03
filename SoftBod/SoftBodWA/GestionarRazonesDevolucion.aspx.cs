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
                MostrarMensajeError("Error al cargar razones: " + ex.Message);
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
                    MostrarMensajeError("La descripción no puede estar vacía");
                    return;
                }

                if (descripcion.Length > 200)
                {
                    MostrarMensajeError("La descripción no puede exceder los 200 caracteres");
                    return;
                }

                var razonesExistentes = razonDevolucionBO.listarTodasRazonesDevolucion();
                if (razonesExistentes != null && razonesExistentes.Any(r =>
                    r.descripcion.Equals(descripcion, StringComparison.OrdinalIgnoreCase)))
                {
                    MostrarMensajeError("Ya existe una razón con esta descripción");
                    return;
                }

                int resultado = razonDevolucionBO.insertarRazonDevolucion(descripcion);

                if (resultado > 0)
                {
                    txtDescripcionRazon.Text = string.Empty;
                    CargarRazones();
                    MostrarMensajeExitoYRecargar("Razón de devolución agregada exitosamente");
                }
                else
                {
                    MostrarMensajeError("Error al agregar la razón de devolución");
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError("Error al agregar razón: " + ex.Message);
            }
        }

        protected void btnEliminarRazon_Command(object sender, CommandEventArgs e)
        {
            if (e.CommandName == "Eliminar")
            {
                try
                {
                    int razonId = int.Parse(e.CommandArgument.ToString());

                    var razon = razonDevolucionBO.obtenerRazonDevolucionPorId(razonId);
                    if (razon == null)
                    {
                        MostrarMensajeError("La razón de devolución no existe");
                        return;
                    }

                    int resultado = razonDevolucionBO.eliminarRazonDevolucion(razonId);

                    if (resultado > 0)
                    {
                        CargarRazones();
                        MostrarMensajeExitoYRecargar("Razón de devolución eliminada exitosamente");
                    }
                    else
                    {
                        MostrarMensajeError("No se pudo eliminar. La razón puede estar en uso");
                    }
                }
                catch (Exception ex)
                {
                    MostrarMensajeError("Error al eliminar razón: " + ex.Message);
                }
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarDevolucion.aspx");
        }

        private void MostrarMensajeError(string mensaje)
        {
            string mensajeEscapado = EscapeJavaScript(mensaje);
            ScriptManager.RegisterStartupScript(this, this.GetType(), "error",
                $"alert('{mensajeEscapado}');", true);
        }

        private void MostrarMensajeExitoYRecargar(string mensaje)
        {
            string mensajeEscapado = EscapeJavaScript(mensaje);
            string script = $@"
                alert('{mensajeEscapado}');
                window.location.href = window.location.pathname;
            ";
            ScriptManager.RegisterStartupScript(this, this.GetType(), "exitoYRecarga", script, true);
        }

        private string EscapeJavaScript(string text)
        {
            return text.Replace("'", "\\'").Replace("\"", "\\\"").Replace("\n", "\\n").Replace("\r", "");
        }
    }
}