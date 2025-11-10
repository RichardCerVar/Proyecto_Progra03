using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
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
            return historialSOAP.listarTodosHistorialOperaciones().ToList();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorUsuario(int usuarioId)
        {
            return historialSOAP.listarHistorialOperacionesPorUsuario(usuarioId).ToList();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorTabla(string nombreTabla)
        {
            return historialSOAP.listarHistorialOperacionesPorTabla(nombreTabla).ToList();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorTipoOperacion(string tipoOperacion)
        {
            return historialSOAP.listarHistorialOperacionesPorTipoOperacion(tipoOperacion).ToList();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorUsuarioYTabla(int usuarioId, string nombreTabla)
        {
            return historialSOAP.listarHistorialOperacionesPorUsuarioYTabla(usuarioId, nombreTabla).ToList();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorTablaYTipoOperacion(string nombreTabla, string tipoOperacion)
        {
            return historialSOAP.listarHistorialOperacionesPorTablaYTipoOperacion(nombreTabla, tipoOperacion).ToList();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesPorFecha(string fecha)
        {
            return historialSOAP.listarHistorialOperacionesPorFecha(fecha).ToList();
        }

        public List<historialOperacionesDTO> listarHistorialOperacionesConFiltros(int operacionId, string nombreTabla, string tipoOperacion,
                                                                                  string fechaOperacion, int usuarioId, string usuario,
                                                                                  string tipoUsuario, bool estado)
        {
            return historialSOAP.listarHistorialOperacionesConFiltros(operacionId, nombreTabla, tipoOperacion,
                                                                      fechaOperacion, usuarioId, usuario, tipoUsuario, estado).ToList();
        }
    }
}
