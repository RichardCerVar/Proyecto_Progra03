using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftWSVentaAlFiado;

namespace SoftBodBusiness
{
    public class VentaAlFiadoBO
    {
        private VentaAlFiadoClient ventaFiadoSOAP;

        public VentaAlFiadoBO()
        {
            ventaFiadoSOAP = new VentaAlFiadoClient();
        }

        public int insertarVentaAlFiado(
            clienteAlFiadoDTO cliente,
            usuarioDTO usuario,
            string metodoPago,
            detalleVentaDTO[] detallesVenta)
        {
            return this.ventaFiadoSOAP.insertarVentaAlFiado(
                cliente, usuario, metodoPago, detallesVenta
            );
        }

        public ventaAlFiadoDTO obtenerVentaAlFiadoPorId(int idVentaFiada)
        {
            return this.ventaFiadoSOAP.obtenerVentaAlFiadoPorId(idVentaFiada);
        }

        public List<ventaAlFiadoDTO> listarTodasVentasAlFiado()
        {
            return this.ventaFiadoSOAP.listarTodasVentasAlFiado()?.ToList()
                   ?? new List<ventaAlFiadoDTO>();
        }

        public List<ventaAlFiadoDTO> listarVentasAlFiadoPorAliasCliente(string aliasCliente)
        {
            return this.ventaFiadoSOAP.listarVentasAlFiadoPorAliasCliente(aliasCliente)?.ToList()
                   ?? new List<ventaAlFiadoDTO>();
        }

        public List<ventaAlFiadoDTO> listarVentasAlFiadoPorAliasClienteYFecha(string aliasCliente, string fecha)
        {
            return this.ventaFiadoSOAP
                .listarVentasAlFiadoPorAliasClienteYFecha(aliasCliente, fecha)?.ToList()
                ?? new List<ventaAlFiadoDTO>();
        }
    }
}