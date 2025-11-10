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

        [TestMethod]
        public void TestInsertarProducto()
        {
            Console.WriteLine("Insertar Producto");

            categoriaDTO categoria = new categoriaDTO();
            categoria.categoriaId = 1;
            categoria.descripcion = "Abarrotes";

            int resultado = this.productoBO.insertarProducto(
                categoria,
                "Arroz Superior",
                25.50,
                "KILOGRAMO",
                100,
                10,
                true
            );

            Assert.IsTrue(resultado > 0);

            resultado = this.productoBO.insertarProducto(
                categoria,
                "Aceite Vegetal",
                15.00,
                "LITRO",
                50,
                5,
                true
            );

            Assert.IsTrue(resultado > 0);
        }

        [TestMethod]
        public void TestModificarProducto()
        {
            Console.WriteLine("Modificar Producto");
            productoDTO producto1 = this.productoBO.obtenerProductoPorId(1);
            Console.WriteLine("Producto antes: ");
            Console.WriteLine(producto1.nombre);

            productoDTO producto = new productoDTO();
            producto.categoria = new categoriaDTO();
            producto.categoria.categoriaId = 2;
            producto.productoId = 1;
            producto.nombre = "Arroz Premium Modificado";
            producto.precioUnitario = 28.00;
            producto.stock = 120;
            producto.stockMinimo = 15;
            producto.activo = true;
            int resultado = this.productoBO.modificarProducto(producto);

            Assert.IsTrue(resultado > 0);

            productoDTO producto2 = this.productoBO.obtenerProductoPorId(1);
            Console.WriteLine("Producto despues: ");
            Console.WriteLine(producto2.nombre);
        }

        [TestMethod]
        public void TestObtenerProductoPorId()
        {
            Console.WriteLine("Obtener Producto Por Id");

            productoDTO producto = this.productoBO.obtenerProductoPorId(1);
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
            foreach(productoDTO productoDTO in productos)
            {
                Console.WriteLine(productoDTO.nombre);
            }
            Assert.IsNotNull(productos);
            Assert.IsTrue(productos.Count >= 0);
        }

        [TestMethod]
        public void TestListarTodosProductosActivos()
        {
            Console.WriteLine("Listar Productos Activos con Filtros");

            List<productoDTO> productos = this.productoBO.listarTodosConFiltroProductos(
                true,
                "Abarrotes",
                "Arroz"
            );

            foreach (productoDTO productoDTO in productos)
            {
                Console.WriteLine(productoDTO.nombre);
            }

            Assert.IsNotNull(productos);
            Assert.IsTrue(productos.Count >= 0);

            // Test con filtros vacíos
            productos = this.productoBO.listarTodosConFiltroProductos(true, "", "");
            Assert.IsNotNull(productos);
        }
    }
}
