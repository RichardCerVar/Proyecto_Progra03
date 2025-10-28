using System;
using System.Collections.Generic;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBodWA
{
    public partial class Clientes : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarClientes();
            }
        }

        private void CargarClientes()
        {
            // Datos simulados (puedes reemplazar con datos de BD)
            var clientes = new List<Cliente>()
            {
                new Cliente { Alias="juan123", Nombre="Juan Pérez", Telefono="123-456-7890", Deuda=150.00m, FechaLimite=new DateTime(2024,2,15)},
                new Cliente { Alias="maria456", Nombre="María García", Telefono="098-765-4321", Deuda=80.00m, FechaLimite=new DateTime(2024,2,20)},
                new Cliente { Alias="pedro789", Nombre="Pedro Torres", Telefono="987-654-3210", Deuda=0.00m, FechaLimite=new DateTime(2024,3,1)}
            };

            rptClientes.DataSource = clientes;
            rptClientes.DataBind();

            // Total deuda y clientes activos
            decimal totalDeuda = 0;
            int activos = 0;

            foreach (var c in clientes)
            {
                if (c.Deuda > 0)
                {
                    totalDeuda += c.Deuda;
                    activos++;
                }
            }

            lblTotalDeuda.InnerText = $"S/{totalDeuda:F2}";
            lblActivos.InnerText = activos.ToString();
        }

        protected void rptClientes_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            string alias = e.CommandArgument.ToString();

            switch (e.CommandName)
            {
                case "Pagar":
                    // Aquí podrías abrir un modal o procesar el pago
                    Response.Write($"<script>alert('Pago procesado para {alias}');</script>");
                    break;
                case "Editar":
                    Response.Write($"<script>alert('Editar cliente: {alias}');</script>");
                    break;
                case "Eliminar":
                    Response.Write($"<script>alert('Cliente eliminado: {alias}');</script>");
                    break;
            }
        }

        public class Cliente
        {
            public string Alias { get; set; }
            public string Nombre { get; set; }
            public string Telefono { get; set; }
            public decimal Deuda { get; set; }
            public DateTime FechaLimite { get; set; }
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalAgregarCliente() }; ";
            ClientScript.RegisterStartupScript(this.GetType(), "", script, true);
        }
        protected void btnGuardarCliente_Click(object sender, EventArgs e)
        {
            try
            {
                // Obtener datos del formulario
                string nombreCompleto = txtNombreCompleto.Text.Trim();
                string alias = txtAlias.Text.Trim();
                string telefono = txtTelefono.Text.Trim();
                string fechaLimiteStr = txtFechaLimite.Text;

                // Validaciones básicas
                if (string.IsNullOrEmpty(nombreCompleto) || string.IsNullOrEmpty(alias))
                {
                    // Mostrar mensaje de error
                    ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                        "alert('Por favor complete los campos obligatorios.');", true);
                    return;
                }

                DateTime fechaLimite = DateTime.MinValue;
                if (!string.IsNullOrEmpty(fechaLimiteStr))
                {
                    fechaLimite = DateTime.Parse(fechaLimiteStr);
                }

                // TODO: Aquí va la lógica para guardar el cliente

                // Limpiar campos
                LimpiarCamposModal();

                // Recargar datos
                CargarClientes();
                ActualizarResumen();

                // Cerrar modal y mostrar mensaje de éxito
                ScriptManager.RegisterStartupScript(this, GetType(), "success",
                    "alert('Cliente agregado exitosamente.');"+
                    "var modal = bootstrap.Modal.getInstance(document.getElementById('modalAgregarCliente')); " +
                    "if(modal) modal.hide();",true);

                
            }
            catch (Exception ex)
            {
                // Manejo de errores
                ScriptManager.RegisterStartupScript(this, GetType(), "error",
                    $"alert('Error al agregar cliente: {ex.Message}');", true);
            }
        }

        private void ActualizarResumen()
        {
            // TODO: Calcular totales

        }

        private void LimpiarCamposModal()
        {
            txtNombreCompleto.Text = "";
            txtAlias.Text = "";
            txtTelefono.Text = "";
            txtFechaLimite.Text = "";
        }
    
    }
}