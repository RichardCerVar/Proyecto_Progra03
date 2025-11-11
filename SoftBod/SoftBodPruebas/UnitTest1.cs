using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftBodBusiness;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftBodPruebas
{
    /// <summary>
    /// Descripción resumida de UnitTest1
    /// </summary>
    [TestClass]
    public class DevolucionBOTest
    {
        private DevolucionBO devolucionBO;

        public DevolucionBOTest()
        {
            this.devolucionBO = new DevolucionBO();
        }

        [TestMethod]
        public void PruebasDevolucion()
        {

            Console.WriteLine("Insertar Devolucion.\n");

            int idUser = 2;
            SoftBodBusiness.SoftWSDevolucion.usuarioDTO user = new SoftBodBusiness.SoftWSDevolucion.usuarioDTO();
            user.usuarioId = idUser;


            List<SoftBodBusiness.SoftWSDevolucion.detalleDevolucionDTO> detallesDev = new List<SoftBodBusiness.SoftWSDevolucion.detalleDevolucionDTO>();

            // 🔁 Crear 4 detalles distintos
            for (int i = 1; i <= 4; i++)
            {
                var detalleDev = new SoftBodBusiness.SoftWSDevolucion.detalleDevolucionDTO();

                detalleDev.razonDevolucion = new SoftBodBusiness.SoftWSDevolucion.razonDevolucionDTO();
                detalleDev.razonDevolucion.razonDevolucionId = 1;
                detalleDev.subtotal = 10.90;
                detalleDev.cantidad = 4;
                detalleDev.producto = new SoftBodBusiness.SoftWSDevolucion.productoDTO();
                detalleDev.producto.productoId = i; // 1, 2, 3, 4

                detallesDev.Add(detalleDev);
            }
            var devBo = new DevolucionBO();
            int nuevaDev = devBo.insertarDevolucion(user, detallesDev.ToArray());
            Console.WriteLine("Nueva Devolucion con id: " + nuevaDev + " insertada");

        }
    }
}
