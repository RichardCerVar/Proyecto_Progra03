using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftBodBusiness;
using System;
using System.Collections.Generic;
using SoftBodBusiness.SoftWSProducto;

namespace SoftBodPruebas
{
    [TestClass]
    public class ProductoBOTest
    {
        private ProductoBO productoBO;

        public ProductoBOTest()
        {
            this.productoBO = new ProductoBO();
        }

        //[TestMethod]
        //public void TestInsertarProducto()
        //{
        //    Console.WriteLine("Insertar Producto");

        //    categoriaDTO categoria = new categoriaDTO();
        //    categoria.categoriaId = 1;
        //    categoria.categoriaIdSpecified = true;
        //    categoria.descripcion = "Abarrotes";

        //    int resultado = this.productoBO.insertarProducto(
        //        categoria,
        //        "JAArroz Superior",
        //        25.50,
        //        "KILOGRAMO",
        //        100,
        //        10,
        //        true
        //    );

        //    Assert.IsTrue(resultado > 0);

        //    resultado = this.productoBO.insertarProducto(
        //        categoria,
        //        "JAAceite Vegetal",
        //        15.00,
        //        "LITRO",
        //        50,
        //        5,
        //        true
        //    );

        //    Assert.IsTrue(resultado > 0);
        //}

        //[TestMethod]
        //public void TestModificarProducto()
        //{
        //    Console.WriteLine("Modificar Producto");
        //    productoDTO producto1 = this.productoBO.obtenerProductoPorId(10);
        //    Console.WriteLine("Producto antes: ");
        //    Console.WriteLine(producto1.nombre);
        //    producto1.nombre = "CAMBIO";

        //    int resultado = this.productoBO.modificarProducto(producto1);
        //    Assert.IsTrue(resultado > 0);
        //    productoDTO producto2 = this.productoBO.obtenerProductoPorId(10);
        //    Console.WriteLine("Producto despues: ");
        //    Console.WriteLine(producto2.nombre);
        //}

        [TestMethod]
        public void TestObtenerProductoPorId()
        {
            Console.WriteLine("Obtener Producto Por Id");

            productoDTO producto = this.productoBO.obtenerProductoPorId(30);
            Console.WriteLine(producto.nombre);

            Assert.IsNotNull(producto);
            Assert.IsTrue(producto.productoId > 0);
            Assert.IsNotNull(producto.nombre);
        }

        [TestMethod]
        public void TestListarTodosProductos()
        {
            Console.WriteLine("Listar Todos los Productos");

            List<productoDTO> productos = this.productoBO.listarTodosProductos();
            foreach (productoDTO productoDTO in productos)
            {
                Console.WriteLine(productoDTO.nombre);
            }
            Assert.IsNotNull(productos);
            Assert.IsTrue(productos.Count >= 0);
        }

        [TestMethod]
        public void TestListarTodosConFiltroProductos()
        {
            Console.WriteLine("Listar Productos Activos con Filtros");

            List<productoDTO> productos = this.productoBO.listarTodosConFiltroProductos(
                true,
                "Lácteos",
                "Arroz"
            );

            foreach (productoDTO productoDTO in productos)
            {
                Console.WriteLine(productoDTO.nombre);
            }

            Assert.IsNotNull(productos);
            Assert.IsTrue(productos.Count >= 0);
        }
    }
}
