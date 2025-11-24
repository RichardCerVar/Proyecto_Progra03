using System;
using System.Collections.Generic;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Linq;
using SoftBodBusiness;
using WSClienteAlFiado = SoftBodBusiness.SoftWSClienteAlFiado;
using WSRegistroPagoFiado = SoftBodBusiness.SoftWSRegistroPagoFiado;
using SoftBodBusiness.SoftWSClienteAlFiado;

namespace SoftBodWA
{
    public partial class Clientes : System.Web.UI.Page
    {
        private ClienteAlFiadoBO clienteBO;

        private List<WSClienteAlFiado.clienteAlFiadoDTO> clientes;
        private RegistroPagoFiadoBO registroPagoFiadoBO;
        public Clientes()
        {
            clienteBO = new ClienteAlFiadoBO();
            clientes = clienteBO.listarTodosClientesAlFiado();
            registroPagoFiadoBO = new RegistroPagoFiadoBO();
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
                DateTime fechaLimite = DateTime.Parse(txtFechaLimite.Text);
                string fechaLimiteStr = DateTime.Parse(txtFechaLimite.Text).ToString("yyyy-MM-dd");

                if (string.IsNullOrEmpty(nombreCompleto) || string.IsNullOrEmpty(alias) || string.IsNullOrEmpty(fechaLimiteStr))
                {
                    string errorScript = "alert('Por favor complete los campos Nombre, Alias y Fecha Límite.'); $('#modalAgregarCliente').modal('show');";
                    ScriptManager.RegisterStartupScript(this, GetType(), "alertError", errorScript, true);
                    return;
                }
                if (!telefono.All(char.IsDigit))
                {
                    string errorScript = "alert('El campo de teléfono solo adminte números.'); $('#modalAgregarCliente').modal('show');";
                    ScriptManager.RegisterStartupScript(this, GetType(), "alertError", errorScript, true);
                    return;
                }

                if (fechaLimite <= DateTime.Today)
                {
                    string errorScript =
                        "alert('La fecha límite debe ser mayor al día de hoy.'); $('#modalAgregarCliente').modal('show');";
                    ScriptManager.RegisterStartupScript(this, GetType(), "fechaInvalida", errorScript, true);
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
                    "setTimeout(function(){ alert('Cliente agregado exitosamente.'); window.location.href = window.location.href; }, 50);";

                ScriptManager.RegisterStartupScript(this, GetType(), "success", successScript, true);

            }
            catch (Exception ex)
            {
                string enableButtonScript =
                "var btn = document.getElementById('" + btnGuardarCliente.ClientID + "');" +
                "if (btn) { btn.disabled = false; btn.value = 'Agregar Cliente'; }";

                string errorScript =
                    $"alert('Error al agregar cliente: Verifique el formato de la fecha. Detalles: {ex.Message}');" +
                    "$('#modalAgregarCliente').modal('show');" + enableButtonScript;

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
            ClientesData = clienteBO.listarTodosClientesAlFiado();
            rptClientes.DataSource = ClientesData;
            rptClientes.DataBind();

            ActualizarResumen(ClientesData);
        }

        private void ActualizarResumen(List<WSClienteAlFiado.clienteAlFiadoDTO> clientes)
        {
            var clientesConDeuda = clientes.Where(c => c.activo = true).ToList();

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
                        double deudaDou = (clienteAfectado.montoDeuda > 1 ? clienteAfectado.montoDeuda : 0.00);
                        string deuda = deudaDou.ToString();
                        lblAlias.Text = alias;
                        lblDeudaActual.Text = deuda;
                        txtMontoPagar.Text = "";

                        string scriptPago = "var myModal = new bootstrap.Modal(document.getElementById('modalPago')); myModal.show();";
                        ScriptManager.RegisterStartupScript(this, GetType(), "ShowPagoModal", scriptPago, true);
                        //poner mensaje
                        message = "se pudo ?";
                        break;

                    case "Editar":

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
                        string clienteID = args[1];
                        hfClienteIDEliminar.Value = clienteID.ToString();
                        lblAliasEliminar.Text = alias;
                        string scriptEliminar = "var myModal = new bootstrap.Modal(document.getElementById('modalEliminarCliente')); myModal.show();";
                        ScriptManager.RegisterStartupScript(this, GetType(), "showEliminarCliente", scriptEliminar, true);
                        //poner mensaje


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
            try
            {
                // Obtener los datos del modal

                int clienteID = int.Parse(lblIdClienteEditar.Text);
                string nombre = txtNombreEditar.Text.Trim();
                string alias = txtAliasEditar.Text.Trim();
                string telefono = txtTelefonoEditar.Text.Trim();
                string fechaLimite = DateTime.Parse(txtFechaLimiteEditar.Text).ToString("yyyy-MM-dd");
                DateTime fechaLimiteN = DateTime.Parse(txtFechaLimite.Text);

                clienteAlFiadoDTO clienteDTO = new clienteAlFiadoDTO();
                clienteDTO = clienteBO.obtenerClienteAlFiadoPorId(int.Parse(lblIdClienteEditar.Text));
                clienteDTO.alias = alias;
                clienteDTO.nombre = nombre;
                clienteDTO.telefono = telefono;
                clienteDTO.fechaDePago = fechaLimite;


                // Validaciones simples
                if (string.IsNullOrEmpty(nombre) || string.IsNullOrEmpty(alias) || string.IsNullOrEmpty(fechaLimite))
                {
                    ScriptManager.RegisterStartupScript(
                        this, GetType(),
                        "validacionUpdate",
                        "alert('Complete todos los campos obligatorios.');",
                        true
                    );
                    return;
                }

                if (fechaLimiteN <= DateTime.Today)
                {
                    string errorScript =
                        "alert('La fecha límite debe ser mayor al día de hoy.'); $('#modalAgregarCliente').modal('show');";
                    ScriptManager.RegisterStartupScript(this, GetType(), "fechaInvalida", errorScript, true);
                    return;
                }


                // Llamar a la lógica de negocio
                clienteBO.modificarClienteAlFiado(clienteDTO);

                // Recargar los datos en memoria y pantalla
                CargarClientes();

                // Generar script para cerrar modal y mostrar aviso
                string script =
                    "var modal = bootstrap.Modal.getInstance(document.getElementById('modalEditarCliente')); " +
                    "if(modal) modal.hide();" +
                    "alert('Cliente actualizado correctamente.');";

                ScriptManager.RegisterStartupScript(this, GetType(), "successUpdate", script, true);

            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(
                    this, GetType(),
                    "errorUpdate",
                    $"alert('Error al actualizar cliente: {ex.Message}');",
                    true
                );
            }
        }

        protected void btnRegistrarPago_Click(object sender, EventArgs e)
        {
            try
            {
                // Obtener alias y monto a pagar desde los controles del modal
                string alias = lblAlias.Text;
                string montoStr = txtMontoPagar.Text.Trim();
                double monto = Convert.ToDouble(montoStr);

                if (string.IsNullOrEmpty(alias) || !double.TryParse(montoStr, out monto) || monto <= 0)
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "errorPago",
                        "alert('Ingrese un monto válido para el pago.');", true);
                    return;
                }

                // Buscar el cliente por alias
                var clientes = ClientesData;
                Session["Cliente"] = clientes.FirstOrDefault(c => c.alias.Equals(alias, StringComparison.OrdinalIgnoreCase));
                var cliente = Session["Cliente"] as WSClienteAlFiado.clienteAlFiadoDTO;
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

                ClienteAlFiadoBO clienteMod = new ClienteAlFiadoBO();
                cliente.montoDeuda = cliente.montoDeuda - monto;

                clienteBO.modificarClienteAlFiado(cliente);
                int idUser = (int)Session["UsuarioId"];
                WSRegistroPagoFiado.usuarioDTO user = new SoftBodBusiness.SoftWSRegistroPagoFiado.usuarioDTO();
                user.usuarioId = idUser;
                user.usuarioIdSpecified = true;

                int idClie = (Session["Cliente"] as WSClienteAlFiado.clienteAlFiadoDTO).clienteId;
                WSRegistroPagoFiado.clienteAlFiadoDTO clieDTO = new WSRegistroPagoFiado.clienteAlFiadoDTO();
                clieDTO.clienteId = idClie;
                clieDTO.clienteIdSpecified = true;

                registroPagoFiadoBO.insertarRegistroPagoFiado(user, clieDTO, "EFECTIVO", monto);
                CargarClientes();


                ScriptManager.RegisterStartupScript(this, GetType(), "successPago",
                    "alert('Pago registrado exitosamente.');", true);

                //cerrar el modal con JS
                string script = "var modal = bootstrap.Modal.getInstance(document.getElementById('modalPago')); if(modal) modal.hide();";
                ScriptManager.RegisterStartupScript(this, GetType(), "HidePagoModal", script, true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(
                    this, GetType(),
                    "errorUpdate",
                    $"alert('Error al actualizar cliente: {ex.Message}');",
                    true
                );
            }
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            string busqueda = txtBuscar.Text.Trim();

            CargarClientes(busqueda);
        }

        private void CargarClientes(string termoBusca = "")
        {



            if (string.IsNullOrWhiteSpace(termoBusca))
            {

                clientes = clienteBO.listarTodosClientesAlFiado();

                ClientesData = clientes;
            }
            else
            {
                clientes = clienteBO.listarTodosClientesAlFiadoLike(termoBusca);

            }

            rptClientes.DataSource = clientes;
            rptClientes.DataBind();

            ActualizarResumen(clientes);
        }


        protected void btnEliminarConfirmado_Click(object sender, EventArgs e)
        {
            try
            {
                int clienteID = int.Parse(hfClienteIDEliminar.Value);
                clienteBO.eliminarClienteAlFiado(clienteID);
                CargarClientes();

                string script =
                    "var modal = bootstrap.Modal.getInstance(document.getElementById('modalEliminarCliente')); " +
                    "if(modal) modal.hide();" +
                    "alert('Cliente eliminado correctamente.');";

                ScriptManager.RegisterStartupScript(this, GetType(), "successDelete", script, true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(
                    this, GetType(),
                    "errorDelete",
                    $"alert('Error al eliminar el cliente: {ex.Message}');",
                    true
                );
            }

        }

    }
}