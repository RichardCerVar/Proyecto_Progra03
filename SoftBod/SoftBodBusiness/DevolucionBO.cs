using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftBodWSServices;

namespace SoftBodBusiness
{
    public class DevolucionBO
    {
        private DevolucionClient devolucionSOAP;

        public DevolucionBO()
        {
            devolucionSOAP = new DevolucionClient();
        }

        public int insertarDevolucion(usuarioDTO usuario, detalleDevolucionDTO[] detalles)
        {
            return devolucionSOAP.insertarDevolucion(usuario, detalles);
        }

        public devolucionDTO obtenerDevolucionPorId(int devolucionId)
        {
            return devolucionSOAP.obtenerDevolucionPorId(devolucionId);
        }

        public List<devolucionDTO> listarTodasDevoluciones()
        {
            return devolucionSOAP.listarTodasDevoluciones()?.ToList()
                   ?? new List<devolucionDTO>();
        }

        public List<devolucionDTO> listarDevolucionesPorFecha(string fecha)
        {
            return devolucionSOAP.listarDevolucionesPorFecha(fecha)?.ToList()
                   ?? new List<devolucionDTO>();
        }

        public List<devolucionDTO> listarDevolucionesPorUsuario(int usuarioId)
        {
            return devolucionSOAP.listarDevolucionesPorUsuario(usuarioId)?.ToList()
                   ?? new List<devolucionDTO>();
        }

        public List<devolucionDTO> listarDevolucionesPorUsuarioYFecha(int usuarioId, string fecha)
        {
            return devolucionSOAP.listarDevolucionesPorUsuarioYFecha(usuarioId, fecha)?.ToList()
                   ?? new List<devolucionDTO>();
        }
    }
}