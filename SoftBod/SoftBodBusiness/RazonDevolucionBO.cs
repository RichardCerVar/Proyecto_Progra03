using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftBodWSServices;

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
            return this.razonSOAP.listarTodasRazonesDevolucion()?.ToList()
                   ?? new List<razonDevolucionDTO>();
        }

        public List<razonDevolucionDTO> listarRazonesDevolucionPorNombreParcial(string nombre)
        {
            return this.razonSOAP.listarRazonesDevolucionPorNombreParcial(nombre)?.ToList()
                   ?? new List<razonDevolucionDTO>();
        }
    }
}