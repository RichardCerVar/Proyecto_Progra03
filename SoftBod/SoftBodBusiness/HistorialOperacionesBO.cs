using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using SoftBodBusiness.SoftWSHistorialOperaciones;

namespace SoftBodBusiness
{
    public class HistorialOperacionesBO
    {
        private HistorialOperacionesClient historialSOAP;

        public HistorialOperacionesBO()
        {
            historialSOAP = new HistorialOperacionesClient();
        }

        public int insertarHistorialOperacion(historialOperacionesDTO historial)
        {
            return historialSOAP.insertarHistorialOperacion(historial);
        }

        public historialOperacionesDTO obtenerHistorialOperacionPorId(int historialId)
        {
            return historialSOAP.obtenerHistorialOperacionPorId(historialId);
        }

        public List<historialOperacionesDTO> listarTodosHistorialOperaciones()
        {
            return historialSOAP.listarTodosHistorialOperaciones()?.ToList()
                   ?? new List<historialOperacionesDTO>();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorUsuario(int usuarioId)
        {
            return historialSOAP.listarHistorialOperacionesPorUsuario(usuarioId)?.ToList()
                   ?? new List<historialOperacionesDTO>();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorTabla(string nombreTabla)
        {
            return historialSOAP.listarHistorialOperacionesPorTabla(nombreTabla)?.ToList()
                   ?? new List<historialOperacionesDTO>();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorTipoOperacion(string tipoOperacion)
        {
            return historialSOAP.listarHistorialOperacionesPorTipoOperacion(tipoOperacion)?.ToList()
                   ?? new List<historialOperacionesDTO>();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorUsuarioYTabla(int usuarioId, string nombreTabla)
        {
            return historialSOAP.listarHistorialOperacionesPorUsuarioYTabla(usuarioId, nombreTabla)?.ToList()
                   ?? new List<historialOperacionesDTO>();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorTablaYTipoOperacion(string nombreTabla, string tipoOperacion)
        {
            return historialSOAP.listarHistorialOperacionesPorTablaYTipoOperacion(nombreTabla, tipoOperacion)?.ToList()
                   ?? new List<historialOperacionesDTO>();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorFecha(string fecha)
        {
            return historialSOAP.listarHistorialOperacionesPorFecha(fecha)?.ToList()
                   ?? new List<historialOperacionesDTO>();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesConFiltros(int? operacionId, string nombreTabla, string tipoOperacion,
                                                                                  string fechaOperacion, int? usuarioId, string usuario,
                                                                                  string tipoUsuario, bool estado)
        {
            return historialSOAP.listarHistorialOperacionesConFiltros(operacionId, nombreTabla, tipoOperacion,
                                                                      fechaOperacion, usuarioId, usuario, tipoUsuario, estado)?.ToList()
                   ?? new List<historialOperacionesDTO>();
        }

        public void registroHistorialDeOperaciones (usuarioDTO usuario, string nombreTabla, string operacion)
        {
            historialSOAP.registrarHistorialDeOperacion(usuario, nombreTabla, operacion);
        }

        public byte[] ReporteHistorialDeOperaciones(string fechaInicio, string fechaFin)
        {
            return historialSOAP.reporteHistorialDeOperaciones(fechaInicio, fechaFin);
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