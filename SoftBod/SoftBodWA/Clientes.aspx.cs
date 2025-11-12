using System;
using System.Collections.Generic;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Linq;

namespace SoftBodWA
{
    public partial class Clientes : System.Web.UI.Page
    {
        public class Cliente
        {
            public string Alias { get; set; }
            public string Nombre { get; set; }
            public string Telefono { get; set; }
            public decimal Deuda { get; set; }
            public DateTime FechaLimite { get; set; }
        }

        private List<Cliente> ClientesData
        {
            get
            {
                if (Session["ClientesList"] == null)
                {
                    Session["ClientesList"] = new List<Cliente>()
                    {
                        new Cliente { Alias="juan123", Nombre="Juan Pérez", Telefono="123-456-7890", Deuda=150.00m, FechaLimite=new DateTime(2024,12,15)},
                        new Cliente { Alias="maria456", Nombre="María García", Telefono="098-765-4321", Deuda=80.00m, FechaLimite=new DateTime(2024,12,20)},
                        new Cliente { Alias="carlos22", Nombre="Carlos Soto", Telefono="911-222-333", Deuda=25.50m, FechaLimite=new DateTime(2025,1,5)},
                        new Cliente { Alias="luisa11", Nombre="Luisa Rojas", Telefono="955-444-111", Deuda=100.00m, FechaLimite=new DateTime(2024,11,30)}
                    };
                }
                return (List<Cliente>)Session["ClientesList"];
            }
            set
            {
                Session["ClientesList"] = value;
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarClientes();
            }
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            LimpiarCamposModal();

            string script = "$('#modalAgregarCliente').modal('show');";
            ScriptManager.RegisterStartupScript(this, GetType(), "ShowAddClientModal", script, true);
        }

        protected void btnGuardarCliente_Click(object sender, EventArgs e)
        {
            try
            {
                string nombreCompleto = txtNombreCompleto.Text.Trim();
                string alias = txtAlias.Text.Trim();
                string telefono = txtTelefono.Text.Trim();
                string fechaLimiteStr = txtFechaLimite.Text;

                if (string.IsNullOrEmpty(nombreCompleto) || string.IsNullOrEmpty(alias) || string.IsNullOrEmpty(fechaLimiteStr))
                {
                    string errorScript = "alert('Por favor complete los campos Nombre, Alias y Fecha Límite.'); $('#modalAgregarCliente').modal('show');";
                    ScriptManager.RegisterStartupScript(this, GetType(), "alertError", errorScript, true);
                    return;
                }

                DateTime fechaLimite = DateTime.Parse(fechaLimiteStr);

                var nuevoCliente = new Cliente
                {
                    Alias = alias,
                    Nombre = nombreCompleto,
                    Telefono = telefono,
                    Deuda = 0.00m,
                    FechaLimite = fechaLimite
                };

                var clientes = ClientesData;
                clientes.Add(nuevoCliente);
                ClientesData = clientes;

                LimpiarCamposModal();
                CargarClientes();

                string successScript =
                    "var modal = bootstrap.Modal.getInstance(document.getElementById('modalAgregarCliente')); " +
                    "if(modal) modal.hide();" +
                    "alert('Cliente agregado exitosamente.');";

                ScriptManager.RegisterStartupScript(this, GetType(), "success", successScript, true);
            }
            catch (Exception ex)
            {
                string errorScript =
                    $"alert('Error al agregar cliente: Verifique el formato de la fecha. Detalles: {ex.Message}');" +
                    "$('#modalAgregarCliente').modal('show');";

                ScriptManager.RegisterStartupScript(this, GetType(), "error", errorScript, true);
            }
        }

        private void LimpiarCamposModal()
        {
            txtNombreCompleto.Text = "";
            txtAlias.Text = "";
            txtTelefono.Text = "";
            txtFechaLimite.Text = "";
        }

        private void CargarClientes()
        {
            var clientes = ClientesData;

            rptClientes.DataSource = clientes;
            rptClientes.DataBind();

            ActualizarResumen(clientes);
        }

        private void ActualizarResumen(List<Cliente> clientes)
        {
            var clientesConDeuda = clientes.Where(c => c.Deuda > 0).ToList();

            decimal totalDeuda = clientesConDeuda.Sum(c => c.Deuda);
            int activos = clientesConDeuda.Count;

            lblTotalDeuda.InnerText = $"S/{totalDeuda:N2}";
            lblActivos.InnerText = activos.ToString();
        }

        protected void rptClientes_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            string alias = e.CommandArgument.ToString();
            string message = "";
            bool reload = false;

            var clientes = ClientesData;
            var clienteAfectado = clientes.FirstOrDefault(c => c.Alias.Equals(alias, StringComparison.OrdinalIgnoreCase));

            if (clienteAfectado == null)
            {
                message = $"No se encontró el cliente con alias '{alias}'.";
            }
            else
            {
                switch (e.CommandName)
                {
                    case "Pagar":
                        clienteAfectado.Deuda = 0.00m;
                        message = $"Pago total procesado para {alias}.";
                        reload = true;
                        break;
                    case "Editar":
                        message = $"Lógica de edición: Abrir formulario para modificar a {alias}.";
                        break;
                    case "Eliminar":
                        clientes.Remove(clienteAfectado);
                        ClientesData = clientes;
                        message = $"Cliente '{alias}' eliminado exitosamente.";
                        reload = true;
                        break;
                }
            }

            if (reload)
            {
                CargarClientes();
            }

            ScriptManager.RegisterStartupScript(this, GetType(), "alertAction",
                $"alert('{message}');", true);
        }
    }
}