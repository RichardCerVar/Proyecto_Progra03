using System;
using System.Collections.Generic;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Linq;
using SoftBodBusiness;
using WSClienteAlFiado = SoftBodBusiness.SoftWSClienteAlFiado;

namespace SoftBodWA
{
    public partial class Clientes : System.Web.UI.Page
    {
        private ClienteAlFiadoBO clienteBO;
        private List<WSClienteAlFiado.clienteAlFiadoDTO> clientes;
        public Clientes()
        {
            clienteBO = new ClienteAlFiadoBO();
            clientes = clienteBO.listarTodosClientesAlFiado();
        }

        private List<WSClienteAlFiado.clienteAlFiadoDTO> ClientesData
        {
            get
            {
                if (Session["ClientesList"] == null)
                {
                    Session["ClientesList"] = clientes;
                }
                return (List<WSClienteAlFiado.clienteAlFiadoDTO>)Session["ClientesList"];
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

            string script = "var myModal = new bootstrap.Modal(document.getElementById('modalAgregarCliente')); myModal.show();";
            ScriptManager.RegisterStartupScript(this, GetType(), "ShowAddClientModal", script, true);
        }


        protected void btnGuardarCliente_Click(object sender, EventArgs e)
        {
            try
            {
                string nombreCompleto = txtNombreCompleto.Text.Trim();
                string alias = txtAlias.Text.Trim();
                string telefono = txtTelefono.Text.Trim();
                string fechaLimiteStr = DateTime.Parse(txtFechaLimite.Text).ToString("yyyy-MM-dd");

                if (string.IsNullOrEmpty(nombreCompleto) || string.IsNullOrEmpty(alias) || string.IsNullOrEmpty(fechaLimiteStr))
                {
                    string errorScript = "alert('Por favor complete los campos Nombre, Alias y Fecha Límite.'); $('#modalAgregarCliente').modal('show');";
                    ScriptManager.RegisterStartupScript(this, GetType(), "alertError", errorScript, true);
                    return;
                }

                int nuevoIDCli = clienteBO.insertarClienteAlFiado(alias, nombreCompleto, telefono, fechaLimiteStr);
                var nuevoCliente = clienteBO.obtenerClienteAlFiadoPorId(nuevoIDCli);
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
                Response.Redirect("Clientes.aspx");
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

        private void ActualizarResumen(List<WSClienteAlFiado.clienteAlFiadoDTO> clientes)
        {
            var clientesConDeuda = clientes.Where(c => c.montoDeuda > 0).ToList();

            double totalDeuda = clientesConDeuda.Sum(c => c.montoDeuda);
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
            var clienteAfectado = clientes.FirstOrDefault(c => c.alias.Equals(alias, StringComparison.OrdinalIgnoreCase));

            if (clienteAfectado == null)
            {
                message = $"No se encontró el cliente con alias '{alias}'.";
            }
            else
            {
                switch (e.CommandName)
                {
                    case "Pagar":
                        clienteAfectado.montoDeuda = (double)0.00m;
                        message = $"Pago total procesado para {alias}.";
                        reload = true;
                        break;
                    case "Editar":
                        message = $"Lógica de edición: Abrir formulario para modificar a {alias}.";
                        break;
                    case "Eliminar":
                        clientes.Remove(clienteAfectado);
                        clienteBO.eliminarClienteAlFiado(clienteAfectado.clienteId);
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