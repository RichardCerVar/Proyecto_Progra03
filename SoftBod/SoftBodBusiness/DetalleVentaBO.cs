using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftWSDetalleVenta;

namespace SoftBodBusiness
{
    public class DetalleVentaBO
    {
        private DetalleVentaClient detalleVentaSOAP;

        public DetalleVentaBO()
        {
            detalleVentaSOAP = new DetalleVentaClient();
        }

        public int insertarDetalleVenta(detalleVentaDTO detalleVenta)
        {
            return detalleVentaSOAP.insertarDetalleVenta(detalleVenta);
        }

        public detalleVentaDTO obtenerDetalleVentaPorId(int productoId, int ventaId)
        {
            return detalleVentaSOAP.obtenerDetalleVentaPorId(productoId, ventaId);
        }

        public List<detalleVentaDTO> listarTodosDetallesVenta()
        {
            return detalleVentaSOAP.listarTodosDetallesVenta()?.ToList()
                   ?? new List<detalleVentaDTO>();
        }

        public List<detalleVentaDTO> listarDetallesVentaPorProducto(int productoId)
        {
            return detalleVentaSOAP.listarDetallesVentaPorProducto(productoId)?.ToList()
                   ?? new List<detalleVentaDTO>();
        }

        public List<detalleVentaDTO> listarDetallesVentaPorVenta(int ventaId)
        {
            return detalleVentaSOAP.listarDetallesVentaPorVenta(ventaId)?.ToList()
                   ?? new List<detalleVentaDTO>();
        }
    }
}