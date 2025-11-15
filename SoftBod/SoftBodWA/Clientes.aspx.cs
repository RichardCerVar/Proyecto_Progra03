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
            var clientes = ClientesData;
            string[] args = e.CommandArgument.ToString().Split('|');
            string alias = args[0];
            string message = "";
            bool reload = false;


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
                        string deuda = args.Length > 1 ? args[1] : "0.00";
                        lblAlias.Text = alias;
                        lblDeudaActual.Text = deuda;
                        txtMontoPagar.Text = "";

                        string scriptPago = "var myModal = new bootstrap.Modal(document.getElementById('modalPago')); myModal.show();";
                        ScriptManager.RegisterStartupScript(this, GetType(), "ShowPagoModal", scriptPago, true);
                        //poner mensaje
                        message = "se pudo ?";
                        break;

                    case "Editar":
                        string telefono = args[1];
                        string fecha = args[2];
                        
                        
                        lblIdClienteEditar.Text = clienteAfectado.clienteId.ToString();
                        txtNombreEditar.Text = clienteAfectado.nombre;
                        txtAliasEditar.Text = clienteAfectado.alias;
                        txtTelefonoEditar.Text = clienteAfectado.telefono;
                        txtFechaLimiteEditar.Text = clienteAfectado.fechaDePago;

                        string scriptEditar = "var myModal = new bootstrap.Modal(document.getElementById('modalEditarCliente')); myModal.show();";
                        ScriptManager.RegisterStartupScript(this, GetType(), "ShowEditClientModal", scriptEditar, true);
                        //poner mensaje
                        message = "se pudo ?";
                        break;

                    case "Eliminar":
                        lblAliasEliminar.Text = alias;
                        string scriptEliminar = "var myModal = new bootstrap.Modal(document.getElementById('modalEliminarCliente')); myModal.show();";
                        ScriptManager.RegisterStartupScript(this, GetType(), "showEliminarCliente", scriptEliminar, true);
                        //poner mensaje
                        message = "se pudo ?";

                        break;
                }
            }

            if (reload)
            {
                CargarClientes();
            }

            if (!string.IsNullOrEmpty(message) && clienteAfectado == null)
            {
                //si hay error mensaje
                message = "ERROR";
                ScriptManager.RegisterStartupScript(this, GetType(), "alertAction",
                    $"alert('{message}');", true);
            }
        }

        protected void btnActualizarCliente_Click(object sender, EventArgs e)
        {

        }

        protected void btnRegistrarPago_Click(object sender, EventArgs e)
        {
            // Obtener alias y monto a pagar desde los controles del modal
            string alias = lblAlias.Text;
            string montoStr = txtMontoPagar.Text.Trim();
            double monto;

            if (string.IsNullOrEmpty(alias) || !double.TryParse(montoStr, out monto) || monto <= 0)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "errorPago",
                    "alert('Ingrese un monto válido para el pago.');", true);
                return;
            }

            // Buscar el cliente por alias
            var clientes = ClientesData;
            var cliente = clientes.FirstOrDefault(c => c.alias.Equals(alias, StringComparison.OrdinalIgnoreCase));
            if (cliente == null)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "errorPago",
                    $"alert('No se encontró el cliente con alias {alias}.');", true);
                return;
            }

            // Validar que el monto no sea mayor a la deuda
            if (monto > cliente.montoDeuda)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "errorPago",
                    "alert('El monto a pagar no puede ser mayor a la deuda actual.');", true);
                return;
            }

            // Aquí va la logica de negocio para registrar el pago
            

            ScriptManager.RegisterStartupScript(this, GetType(), "successPago",
                "alert('Pago registrado exitosamente.');", true);

            //cerrar el modal con JS
            string script = "var modal = bootstrap.Modal.getInstance(document.getElementById('modalPago')); if(modal) modal.hide();";
            ScriptManager.RegisterStartupScript(this, GetType(), "HidePagoModal", script, true);
        }
        
        protected void btnEliminarConfirmado_Click(object sender, EventArgs e)
        {

        }

    }
}