using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftBodBusiness;
using SoftBodBusiness.SoftWSRazonDevolucion;

namespace SoftBodPruebas
{
    [TestClass]
    public class RazonDevolucionBOTest
    {
        private RazonDevolucionBO razonDevolucionBO;

        public RazonDevolucionBOTest()
        {
            this.razonDevolucionBO = new RazonDevolucionBO();
        }

        //[TestMethod]
        //public void TestInsertarRazonDevolucion()
        //{
        //    Console.WriteLine("Insertar Razón de Devolución");

        //    int resultado = this.razonDevolucionBO.insertarRazonDevolucion(
        //        "BIProducto en mal estado"
        //    );

        //    Assert.IsTrue(resultado > 0);

        //    resultado = this.razonDevolucionBO.insertarRazonDevolucion(
        //        "BIProducto vencido"
        //    );

        //    Assert.IsTrue(resultado > 0);

        //    resultado = this.razonDevolucionBO.insertarRazonDevolucion(
        //        "BICliente insatisfecho"
        //    );

        //    Assert.IsTrue(resultado > 0);
        //}


        [TestMethod]
        public void TestObtenerRazonDevolucionPorId()
        {
            Console.WriteLine("Obtener Razón de Devolución Por Id");
            Console.WriteLine("Obteniendo Id = 1 ...");
            razonDevolucionDTO razon = this.razonDevolucionBO.obtenerRazonDevolucionPorId(1);
            Console.WriteLine(razon.descripcion);

            Assert.IsNotNull(razon);
            Assert.IsTrue(razon.razonDevolucionId > 0);
            Assert.IsNotNull(razon.descripcion);
        }

        [TestMethod]
        public void TestListarTodasRazonesDevolucion()
        {
            Console.WriteLine("Listar Todas las Razones de Devolución");

            List<razonDevolucionDTO> razones = this.razonDevolucionBO.listarTodasRazonesDevolucion();
            foreach (razonDevolucionDTO r in razones)
            {
                Console.WriteLine(r.descripcion);
            }

            Assert.IsNotNull(razones);
            Assert.IsTrue(razones.Count >= 0);
        }

        [TestMethod]
        public void TestListarRazonesDevolucionPorNombreParcial()
        {
            Console.WriteLine("Listar Razones de Devolución por Nombre Parcial");

            List<razonDevolucionDTO> razones = this.razonDevolucionBO.listarRazonesDevolucionPorNombreParcial(
                "Producto"
            );

            foreach (razonDevolucionDTO r in razones)
            {
                Console.WriteLine(r.descripcion);
            }

            Assert.IsNotNull(razones);
            Assert.IsTrue(razones.Count >= 0);

            // Test con búsqueda vacía
            razones = this.razonDevolucionBO.listarRazonesDevolucionPorNombreParcial("");
            Assert.IsNotNull(razones);
        }

        //[TestMethod]
        //public void TestEliminarRazonDevolucion()
        //{
        //    Console.WriteLine("Eliminar Razón de Devolución");
        //    Console.WriteLine("Eliminaddo Id = 1 ...");
        //    int resultado = this.razonDevolucionBO.eliminarRazonDevolucion(44);

        //    Assert.IsTrue(resultado > 0);
        //}
    }
}
