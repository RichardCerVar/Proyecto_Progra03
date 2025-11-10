using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftWSDevolucion;

namespace SoftBodBusiness
{
    public class DevolucionBO
    {
        private DevolucionClient devolucionSOAP;

        public DevolucionBO()
        {
            devolucionSOAP = new DevolucionClient();
        }

        public int insertarDevolucion(double total, string fecha, usuarioDTO usuario)
        {
            return devolucionSOAP.insertarDevolucion(total, fecha, usuario);
        }

        public devolucionDTO obtenerDevolucionPorId(int devolucionId)
        {
            return devolucionSOAP.obtenerDevolucionPorId(devolucionId);
        }

        public List<devolucionDTO> listarTodasDevoluciones()
        {
            return devolucionSOAP.listarTodasDevoluciones().ToList();
        }

        public List<devolucionDTO> listarDevolucionesPorFecha(string fecha)
        {
            return devolucionSOAP.listarDevolucionesPorFecha(fecha).ToList();
        }

        public List<devolucionDTO> listarDevolucionesPorUsuario(int usuarioId)
        {
            return devolucionSOAP.listarDevolucionesPorUsuario(usuarioId).ToList();
        }

        public List<devolucionDTO> listarDevolucionesPorUsuarioYFecha(int usuarioId, string fecha)
        {
            return devolucionSOAP.listarDevolucionesPorUsuarioYFecha(usuarioId, fecha).ToList();
        }

        public int registrarDevolucionCompleta(devolucionDTO devolucion, List<detalleDevolucionDTO> detalles)
        {
            return devolucionSOAP.registrarDevolucionCompleta(devolucion, detalles.ToArray());
        }

    }
}
