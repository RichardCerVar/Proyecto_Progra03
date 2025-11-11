using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftWSDetalleDevolucion;

namespace SoftBodBusiness
{
    public class DetalleDevolucionBO
    {
        private DetalleDevolucionClient detalleDevolucionSOAP;

        public DetalleDevolucionBO()
        {
            detalleDevolucionSOAP = new DetalleDevolucionClient();
        }

        public int insertarDetalleDevolucion(detalleDevolucionDTO detalle)
        {
            return detalleDevolucionSOAP.insertarDetalleDevolucion(detalle);
        }

        public detalleDevolucionDTO obtenerDetalleDevolucionPorId(int productoId, int devolucionId)
        {
            return detalleDevolucionSOAP.obtenerDetalleDevolucionPorId(productoId, devolucionId);
        }

        public List<detalleDevolucionDTO> listarTodosDetallesDevolucion()
        {
            return detalleDevolucionSOAP.listarTodosDetallesDevolucion().ToList();
        }

        public List<detalleDevolucionDTO> listarDetallesDevolucionPorProducto(int productoId)
        {
            return detalleDevolucionSOAP.listarDetallesDevolucionPorProducto(productoId).ToList();
        }

        public List<detalleDevolucionDTO> listarDetallesDevolucionPorDevolucion(int devolucionId)
        {
            return detalleDevolucionSOAP.listarDetallesDevolucionPorDevolucion(devolucionId).ToList();
        }

        public List<detalleDevolucionDTO> listarDetallesDevolucionPorRazon(string razonDevolucion)
        {
            return detalleDevolucionSOAP.listarDetallesDevolucionPorRazon(razonDevolucion).ToList();
        }
    }
}
