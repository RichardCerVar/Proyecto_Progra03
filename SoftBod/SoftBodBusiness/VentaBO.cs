using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.Remoting.Metadata.W3cXsd2001;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using SoftBodBusiness.SoftBodWSServices;

namespace SoftBodBusiness
{
    public class VentaBO
    {
        private VentaClient ventaSOAP;

        public VentaBO()
        {
            ventaSOAP = new VentaClient();
        }

        public int insertarVenta(usuarioDTO usuario, string metodoPago, detalleVentaDTO[] detallesVenta)
        {
            return this.ventaSOAP.insertarVenta(usuario, metodoPago, detallesVenta);
        }

        public ventaDTO obtenerVentaPorId(int idVenta)
        {
            return this.ventaSOAP.obtenerVentaPorId(idVenta);
        }

        public List<ventaDTO> listarTodasVentas()
        {
            return this.ventaSOAP.listarTodosVentas()?.ToList()
                   ?? new List<ventaDTO>();
        }

        public List<ventaDTO> listarVentasPorFecha(string fecha)
        {
            return this.ventaSOAP.listarVentasPorFecha(fecha)?.ToList()
                   ?? new List<ventaDTO>();
        }

        public byte[] ReporteDevolucionesYVentas(string fechaInicio, string fechaFin)
        {
            return this.ventaSOAP.reporteDevolucionesYVentas(fechaInicio, fechaFin);
        }

        public void abrirReporte(HttpResponse response, string nombReporte, byte[] reporte)
        {
            response.Clear();
            response.ContentType = "application/pdf";
            response.AddHeader("Content-Disposition", "inline; filename=" + nombReporte + ".pdf");
            response.BinaryWrite(reporte);
            response.End();
        }
    }
}