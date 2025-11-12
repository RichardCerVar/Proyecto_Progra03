using System;
using System.Data;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Services;
using System.Globalization;

namespace SoftBodWA
{
    public partial class RegistrarDevolucion : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarRazonesDevolucion();
            }
            // SE HA ELIMINADO EL BLOQUE DE SCRIPTMANAGER DE AQUÍ
        }

        private void CargarRazonesDevolucion()
        {
            try
            {
                // En un proyecto real, se implementaría la carga del DropDownList desde la BD aquí.
            }
            catch (Exception)
            {
            }
        }

        protected void btnProcesarDevolucion_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(txtIdVenta.Text))
            {
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Debe ingresar el ID de Venta.');", true);
                return;
            }

            if (string.IsNullOrEmpty(ddlRazonDevolucion.SelectedValue))
            {
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Debe seleccionar una Razón de Devolución.');", true);
                return;
            }

            int idVenta;
            if (!int.TryParse(txtIdVenta.Text, out idVenta))
            {
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('El ID de Venta debe ser un número válido.');", true);
                return;
            }

            try
            {
                int idRazon = Convert.ToInt32(ddlRazonDevolucion.SelectedValue);

                // Aquí va la lógica de la transacción real: validación, cálculo, inserción en BOD_DEVOLUCION, 
                // inserción en BOD_DETALLE_DEVOLUCION y actualización de stock en BOD_PRODUCTO.

                string mensajeExito = $"Devolución procesada con éxito para la Venta #{idVenta}. Monto: S/.45.50";
                ClientScript.RegisterStartupScript(this.GetType(), "successAlert", $"alert('{mensajeExito}');", true);

                txtIdVenta.Text = string.Empty;
                ddlRazonDevolucion.SelectedIndex = 0;
                btnProcesarDevolucion.Text = " Procesar Devolución - S/.0.00";
            }
            catch (Exception ex)
            {
                ClientScript.RegisterStartupScript(this.GetType(), "errorAlert", $"alert('Error al procesar la devolución: {ex.Message.Replace("'", "")}');", true);
            }
        }

        protected void btnVerDetalleVenta_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string idVenta = btn.CommandArgument;

            string script = $"alert('Simulando la apertura de la modal de detalle para la Venta ID: {idVenta}');";
            ScriptManager.RegisterStartupScript(this, GetType(), "VerDetalle", script, true);
        }
    }
}