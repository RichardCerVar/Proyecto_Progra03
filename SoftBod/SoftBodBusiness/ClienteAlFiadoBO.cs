using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Metadata.W3cXsd2001;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using SoftBodBusiness.SoftBodWSServices;

namespace SoftBodBusiness
{
    public class ClienteAlFiadoBO
    {
        private ClienteAlFiadoClient clienteAlFiadoSOAP;

        public ClienteAlFiadoBO()
        {
            clienteAlFiadoSOAP = new ClienteAlFiadoClient();
        }

        public int insertarClienteAlFiado(string alias, string nombre, string telefono, string fechaDePago)
        {
            return clienteAlFiadoSOAP.insertarClienteAlFiado(alias, nombre, telefono, fechaDePago);
        }

        public int modificarClienteAlFiado(clienteAlFiadoDTO cliModificado)
        {
            return clienteAlFiadoSOAP.modificarClienteAlFiado(cliModificado);
        }

        public int eliminarClienteAlFiado(int idCLiente)
        {
            return clienteAlFiadoSOAP.eliminarClienteAlFiado(idCLiente);
        }

        public clienteAlFiadoDTO obtenerClienteAlFiadoPorId(int clienteId)
        {
            return clienteAlFiadoSOAP.obtenerClienteAlFiadoPorId(clienteId);
        }

        public List<clienteAlFiadoDTO> listarTodosClientesAlFiado()
        {
            return clienteAlFiadoSOAP.listarTodosClientesAlFiado()?.ToList()
                   ?? new List<clienteAlFiadoDTO>();
        }

        public List<clienteAlFiadoDTO> listarTodosClientesAlFiadoLike(string cadena)
        {
            return clienteAlFiadoSOAP.listarTodosClientesAlFiadoLike(cadena)?.ToList()
                   ?? new List<clienteAlFiadoDTO>();
        }

        public void bloquearClienteAlFiadoPorMorosidad(int clienteId)
        {
            clienteAlFiadoSOAP.bloquearClienteAlFiadoPorMorosidad(clienteId);
        }

        public void desbloquearClienteAlFiado(int clienteId)
        {
            clienteAlFiadoSOAP.desbloquearClienteAlFiado(clienteId);
        }

        public byte[] ReporteClienteAlFiado(string fechaInicio, string fechaFin, int clienteId)
        {
            return clienteAlFiadoSOAP.reporteClienteAlFiado(fechaInicio, fechaFin, clienteId);
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



