using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftWSRazonDevolucion;

namespace SoftBodBusiness
{
    public class RazonDevolucionBO
    {
        private RazonDevolucionClient razonSOAP;

        public RazonDevolucionBO()
        {
            razonSOAP = new RazonDevolucionClient();
        }

        public int insertarRazonDevolucion(string descripcion)
        {
            // Si el proxy genera un arg0 fantasma, pon 0
            return this.razonSOAP.insertarRazonDevolucion(descripcion);
        }

        public int eliminarRazonDevolucion(int idRazonDevolucion)
        {
            return this.razonSOAP.eliminarRazonDevolucion(idRazonDevolucion);
        }

        public razonDevolucionDTO obtenerRazonDevolucionPorId(int razonId)
        {
            return this.razonSOAP.obtenerRazonDevolucionPorId(razonId);
        }

        public List<razonDevolucionDTO> listarTodasRazonesDevolucion()
        {
            return this.razonSOAP.listarTodasRazonesDevolucion().ToList();
        }

        public List<razonDevolucionDTO> listarRazonesDevolucionPorNombreParcial(string nombre)
        {
            return this.razonSOAP.listarRazonesDevolucionPorNombreParcial(nombre).ToList();
        }
    }
}
