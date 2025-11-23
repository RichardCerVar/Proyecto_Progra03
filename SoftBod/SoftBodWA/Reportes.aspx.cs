using SoftBodBusiness;
using SoftBodBusiness.ReporteDTO;
using System;
using System.Collections.Generic;
using WSClienteAlFiado = SoftBodBusiness.SoftWSClienteAlFiado;


namespace SoftBodWA
{
    public partial class Reportes : System.Web.UI.Page
    {

        private VentaBO ventaBO = new VentaBO();
        private DevolucionBO devolucionBO = new DevolucionBO();
        private VentaAlFiadoBO ventaAlFiadoBO = new VentaAlFiadoBO();
        private DetalleVentaBO detalleVentaBO = new DetalleVentaBO();
        private DetalleDevolucionBO detalleDevolucionBO = new DetalleDevolucionBO();
        private ProductoBO productoBO = new ProductoBO();
        private ClienteAlFiadoBO clienteBO;

        private List<WSClienteAlFiado.clienteAlFiadoDTO> clientes;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarClientesDropDownList();
                
            }
        }

        private void CargarClientesDropDownList()
        {
            clienteBO = new ClienteAlFiadoBO();
            var clientes = clienteBO.listarTodosClientesAlFiado();

            ddlCliente.DataSource = clientes;
            ddlCliente.DataTextField = "Alias";
            ddlCliente.DataValueField = "ClienteId";
            ddlCliente.DataBind();
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            CargarDatosReporte();
        }

        private void CargarDatosReporte()
        {
            if (string.IsNullOrEmpty(txtFecha.Text))
            {
                ClientScript.RegisterStartupScript(GetType(), "alert", "alert('Debe seleccionar una fecha.');", true);
                return;
            }

            string fechaFiltro = DateTime.Parse(txtFecha.Text).ToString("yyyy-MM-dd");
            var movimientosReporteVacio = new List<MovimientoReporteDTO>();

            
        }

        protected void btnExportarReporteVentas_Click(object sender, EventArgs e)
        {
            string tipoFecha = ddlTipoFechaCliente.SelectedValue;

            DateTime fecha = DateTime.MinValue;
            DateTime fechaInicio = DateTime.MinValue;
            DateTime fechaFin = DateTime.MinValue;

            if (tipoFecha == "Diario")
            {
                DateTime.TryParse(txtFechaCliente.Text, out fecha);
                string fechaStr = fecha.ToString("yyyy-MM-dd");


            }
            else if (tipoFecha == "Rango")
            {
                DateTime.TryParse(txtFechaClienteInicio.Text, out fechaInicio);
                DateTime.TryParse(txtFechaClienteFin.Text, out fechaFin);

                string fechaInicioStr = fechaInicio.ToString("yyyy-MM-dd");
                string fechaFinStr = fechaFin.ToString("yyyy-MM-dd");

            }

            // Llamar a tu función que genera el reporte


            ClientScript.RegisterStartupScript(GetType(), "export", "alert('Funcionalidad de exportar reporte a PDF pendiente de implementación en el Back-End.');", true);
        }

        protected void ddlTipoFecha_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ddlTipoFecha.SelectedValue == "Diario")
            {
                divFechaUnica.Visible = true;
                divFechaInicio.Visible = false;
                divFechaFin.Visible = false;
            }
            else if (ddlTipoFecha.SelectedValue == "Rango")
            {
                divFechaUnica.Visible = false;
                divFechaInicio.Visible = true;
                divFechaFin.Visible = true;
            }
        }
        protected void ddlTipoFechaCliente_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ddlTipoFechaCliente.SelectedValue == "Diario")
            {
                divFechaClienteUnica.Visible = true;
                divFechaClienteInicio.Visible = false;
                divFechaClienteFin.Visible = false;
            }
            else if (ddlTipoFechaCliente.SelectedValue == "Rango")
            {
                divFechaClienteUnica.Visible = false;
                divFechaClienteInicio.Visible = true;
                divFechaClienteFin.Visible = true;
            }
        }
        protected void ddlTipoFechaOperario_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ddlTipoFechaOperario.SelectedValue == "Diario")
            {
                divFechaOperarioUnica.Visible = true;
                divFechaOperarioInicio.Visible = false;
                divFechaOperarioFin.Visible = false;
            }
            else if (ddlTipoFechaCliente.SelectedValue == "Rango")
            {
                divFechaOperarioUnica.Visible = false;
                divFechaOperarioInicio.Visible = true;
                divFechaOperarioFin.Visible = true;
            }
        }


        protected void btnExportarReporteInventario_Click(object sender, EventArgs e)
        {

            
            
        }
        protected void btnExportarReporteClientes_Click(object sender, EventArgs e)
        {
            
            int clienteId = 0;
            if (!string.IsNullOrEmpty(ddlCliente.SelectedValue))
            {
                clienteId = int.Parse(ddlCliente.SelectedValue);
            }

            string tipoFecha = ddlTipoFechaCliente.SelectedValue; 

            DateTime fecha = DateTime.MinValue;
            DateTime fechaInicio = DateTime.MinValue;
            DateTime fechaFin = DateTime.MinValue;

            if (tipoFecha == "Diario")
            {
                DateTime.TryParse(txtFechaCliente.Text, out fecha);
                string fechaStr = fecha.ToString("yyyy-MM-dd");
                
                
            }
            else if (tipoFecha == "Rango")
            {
                DateTime.TryParse(txtFechaClienteInicio.Text, out fechaInicio);
                DateTime.TryParse(txtFechaClienteFin.Text, out fechaFin);

                string fechaInicioStr = fechaInicio.ToString("yyyy-MM-dd");
                string fechaFinStr = fechaFin.ToString("yyyy-MM-dd");

            }

            // Llamar a tu función que genera el reporte
 
        }
        protected void btnExportarReporteOperaciones_Click(object sender, EventArgs e)
        {
            string tipoFecha = ddlTipoFechaCliente.SelectedValue;

            DateTime fecha = DateTime.MinValue;
            DateTime fechaInicio = DateTime.MinValue;
            DateTime fechaFin = DateTime.MinValue;

            if (tipoFecha == "Diario")
            {
                DateTime.TryParse(txtFechaCliente.Text, out fecha);
                string fechaStr = fecha.ToString("yyyy-MM-dd");


            }
            else if (tipoFecha == "Rango")
            {
                DateTime.TryParse(txtFechaClienteInicio.Text, out fechaInicio);
                DateTime.TryParse(txtFechaClienteFin.Text, out fechaFin);

                string fechaInicioStr = fechaInicio.ToString("yyyy-MM-dd");
                string fechaFinStr = fechaFin.ToString("yyyy-MM-dd");

            }

            // Llamar a tu función que genera el reporte
        }

    }
}