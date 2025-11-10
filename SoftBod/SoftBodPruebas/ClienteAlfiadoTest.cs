using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftBodBusiness;
using SoftBodBusiness.SoftWSClienteAlFiado;
using System;

namespace SoftBodPruebas
{
    [TestClass]
    public class ClienteAlfiadoTest
    {
        private ClienteAlFiadoBO cliBo;

        public ClienteAlfiadoTest() 
        {
            this.cliBo = new ClienteAlFiadoBO();
        }

        [TestMethod]
        public void PruebasClienteAlFiado()
        {
            //insertar
            string alias = "Eduardo";
            string nombre = "Lalo";
            string telefono = "98932";
            string fechaDePago = DateTime.ParseExact("09/11/2025", "dd/MM/yyyy", null).ToString("yyyy-MM-dd");
            int idNuevo = this.cliBo.insertarClienteAlFiado(alias,nombre,telefono, fechaDePago);
            
            clienteAlFiadoDTO nuevoCli = this.cliBo.obtenerClienteAlFiadoPorId(idNuevo);
            if (nuevoCli != null)
            {
                System.Console.WriteLine("Cliente por id");
            }



        }
    }
}
