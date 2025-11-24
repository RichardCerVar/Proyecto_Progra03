using SoftBodBusiness;
using System;
using System.Collections.Generic;
using SoftBodBusiness.SoftBodWSServices;


namespace SoftBodWA
{
    public partial class Reportes : System.Web.UI.Page
    {

        private VentaBO ventaBO = new VentaBO();
        private ProductoBO productoBO = new ProductoBO();
        private ClienteAlFiadoBO clienteBO = new ClienteAlFiadoBO();
        private HistorialOperacionesBO historialOperacionesBO = new HistorialOperacionesBO();

        private List<clienteAlFiadoDTO> clientes;

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

       


        protected void btnExportarReporteVentas_Click(object sender, EventArgs e)
        {
            string tipoFecha = ddlTipoFecha.SelectedValue;  

            DateTime fecha = DateTime.MinValue;
            DateTime fechaInicio = DateTime.MinValue;
            DateTime fechaFin = DateTime.MinValue;

            byte[] reporte = null;

            if (tipoFecha == "Diario")
            {
                DateTime.TryParse(txtFecha.Text, out fecha);
                string fechaStr = fecha.ToString("yyyy-MM-dd");
                if (fecha != DateTime.MinValue)
                    reporte = ventaBO.ReporteDevolucionesYVentas(fechaStr, fechaStr);
                else
                {
                    lblMensaje.Text = "Por favor, seleccione una fecha válida";
                    return;
                }
            }
            else if (tipoFecha == "Rango")
            {
                DateTime.TryParse(txtFechaInicio.Text, out fechaInicio);
                DateTime.TryParse(txtFechaFin.Text, out fechaFin);

                string fechaInicioStr = fechaInicio.ToString("yyyy-MM-dd");
                string fechaFinStr = fechaFin.ToString("yyyy-MM-dd");

                if (fechaInicio != DateTime.MinValue && fechaFin != DateTime.MinValue)
                    if(fechaInicio <= fechaFin)
                        reporte = ventaBO.ReporteDevolucionesYVentas(fechaInicioStr, fechaFinStr);
                    else
                    {
                        lblMensaje.Text = "La fecha de inicio no puede ser mayor que la fecha fin.";
                        return;
                    }
                else
                    {
                        lblMensaje.Text = "Por favor, seleccione una fecha válida";
                        return;
                    }

                

            }

            ventaBO.abrirReporte(Response, "ReporteDevolucionesYVentas.pdf", reporte);

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
            else if (ddlTipoFechaOperario.SelectedValue == "Rango")
            {
                divFechaOperarioUnica.Visible = false;
                divFechaOperarioInicio.Visible = true;
                divFechaOperarioFin.Visible = true;
            }
        }


        protected void btnExportarReporteInventario_Click(object sender, EventArgs e)
        {
            byte[] reporte = productoBO.ReporteDeInventario();
            productoBO.abrirReporte(Response, "ReporteInventario.pdf", reporte);
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
            byte[] reporte = null;
            if (tipoFecha == "Diario")
            {
                DateTime.TryParse(txtFechaCliente.Text, out fecha);
                string fechaStr = fecha.ToString("yyyy-MM-dd");

                if (fecha != DateTime.MinValue)

                    reporte = clienteBO.ReporteClienteAlFiado(fechaStr, fechaStr, clienteId);
                else
                {
                    lblMensaje2.Text = "Por favor, seleccione una fecha válida";
                    return;
                }
                
            }
            else if (tipoFecha == "Rango")
            {
                DateTime.TryParse(txtFechaClienteInicio.Text, out fechaInicio);
                DateTime.TryParse(txtFechaClienteFin.Text, out fechaFin);

                string fechaInicioStr = fechaInicio.ToString("yyyy-MM-dd");
                string fechaFinStr = fechaFin.ToString("yyyy-MM-dd");

                if (fechaInicio != DateTime.MinValue && fechaFin != DateTime.MinValue)
                    if (fechaInicio <= fechaFin)
                        reporte = clienteBO.ReporteClienteAlFiado(fechaInicioStr, fechaFinStr, clienteId);
                    else
                    {
                        lblMensaje2.Text = "La fecha de inicio no puede ser mayor que la fecha fin.";
                        return;
                    }
                
                else
                {
                    lblMensaje2.Text = "Por favor, seleccione una fecha válida";
                    return;
                }
                
            }

            clienteBO.abrirReporte(Response, "ReporteClienteAlFiado.pdf", reporte);


        }
        protected void btnExportarReporteOperaciones_Click(object sender, EventArgs e)
        {
            string tipoFecha = ddlTipoFechaOperario.SelectedValue;

            DateTime fecha = DateTime.MinValue;
            DateTime fechaInicio = DateTime.MinValue;
            DateTime fechaFin = DateTime.MinValue;

            byte[] reporte = null;

            if (tipoFecha == "Diario")
            {
                DateTime.TryParse(txtFechaOperario.Text, out fecha);
                string fechaStr = fecha.ToString("yyyy-MM-dd");

                if (fecha != DateTime.MinValue)
                    reporte = historialOperacionesBO.ReporteHistorialDeOperaciones(fechaStr, fechaStr);
                else
                {
                    lblMensaje3.Text = "Por favor, seleccione una fecha válida";
                    return;
                }
                
            }
            else if (tipoFecha == "Rango")
            {
                DateTime.TryParse(txtFechaOperarioIncio.Text, out fechaInicio);
                DateTime.TryParse(txtFechaOperarioFin.Text, out fechaFin);

                string fechaInicioStr = fechaInicio.ToString("yyyy-MM-dd");
                string fechaFinStr = fechaFin.ToString("yyyy-MM-dd");

                if (fechaInicio != DateTime.MinValue && fechaFin != DateTime.MinValue)
                    if (fechaInicio <= fechaFin)
                        reporte = historialOperacionesBO.ReporteHistorialDeOperaciones(fechaInicioStr, fechaFinStr);
                    else
                    {
                        lblMensaje3.Text = "La fecha de inicio no puede ser mayor que la fecha fin.";
                        return;
                    }
                
                else
                {
                    lblMensaje3.Text = "Por favor, seleccione una fecha válida";
                    return;
                }

                
            }
            historialOperacionesBO.abrirReporte(Response, "ReporteDevolucionesYVentas.pdf", reporte);
        }

        

    }
}