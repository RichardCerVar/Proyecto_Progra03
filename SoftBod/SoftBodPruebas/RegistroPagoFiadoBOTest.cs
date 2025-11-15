//using System;
//using System.Collections.Generic;
//using Microsoft.VisualStudio.TestTools.UnitTesting;
//using SoftBodBusiness;
//using SoftBodBusiness.SoftWSRegistroPagoFiado;

//namespace SoftBodPruebas
//{
//    [TestClass]
//    public class RegistroPagoFiadoBOTest
//    {
//        private RegistroPagoFiadoBO registroPagoFiadoBO;

//        public RegistroPagoFiadoBOTest()
//        {
//            this.registroPagoFiadoBO = new RegistroPagoFiadoBO();
//        }

//        //[TestMethod]
//        //public void TestInsertarRegistroPagoFiado()
//        //{
//        //    Console.WriteLine("Insertar Registro de Pago Fiado");
//        //    // solo importa el ID
//        //    usuarioDTO usuario = new usuarioDTO();
//        //    usuario.usuarioId = 1;
//        //    usuario.usuarioIdSpecified = true;
//        //    usuario.nombre = "Juan Pérez";
//        //    usuario.correo = "juan.perez@example.com";
//        //    // solo importa el ID
//        //    clienteAlFiadoDTO cliente = new clienteAlFiadoDTO();
//        //    cliente.clienteId = 1;
//        //    cliente.clienteIdSpecified = true;
//        //    cliente.alias = "CLIENTE001";
//        //    cliente.nombre = "María González";

//        //    int resultado = this.registroPagoFiadoBO.insertarRegistroPagoFiado(
//        //        0, // pagoId se genera automáticamente
//        //        usuario,
//        //        cliente,
//        //        "EFECTIVO",
//        //        150.50
//        //    );

//        //    Assert.IsTrue(resultado > 0);

//        //    resultado = this.registroPagoFiadoBO.insertarRegistroPagoFiado(
//        //        0,
//        //        usuario,
//        //        cliente,
//        //        "TRANSFERENCIA",
//        //        200.00
//        //    );

//        //    Assert.IsTrue(resultado > 0);
//        //}

//        [TestMethod]
//        public void TestListarTodosRegistrosPagoFiado()
//        {
//            Console.WriteLine("Listar Todos los Registros de Pago Fiado");

//            List<registroPagoFiadoDTO> registros = this.registroPagoFiadoBO.listarTodosRegistrosPagoFiado();
//            foreach (registroPagoFiadoDTO r in registros)
//            {
//                Console.WriteLine(r.usuario.usuario);
//                Console.WriteLine(r.cliente.alias);
//                Console.WriteLine(r.metodoPago);
//            }
//            Assert.IsNotNull(registros);
//            Assert.IsTrue(registros.Count >= 0);
//        }

//        [TestMethod]
//        public void TestListarRegistrosPagoFiadoPorAliasCliente()
//        {
//            Console.WriteLine("Listar Registros de Pago Fiado por Alias de Cliente");

//            List<registroPagoFiadoDTO> registros = this.registroPagoFiadoBO.listarRegistrosPagoFiadoPorAliasCliente(
//                "ma"
//            );
//            foreach (registroPagoFiadoDTO r in registros)
//            {
//                Console.WriteLine(r.usuario.usuario);
//                Console.WriteLine(r.cliente.alias);
//                Console.WriteLine(r.metodoPago);
//            }

//            Assert.IsNotNull(registros);
//            Assert.IsTrue(registros.Count >= 0);
//        }

//        [TestMethod]
//        public void TestListarRegistrosPagoFiadoPorAliasClienteConFechaFin()
//        {
//            Console.WriteLine("Listar Registros de Pago Fiado por Alias y Fecha Fin");

//            string fechaFin = "2025-10-07";

//            List<registroPagoFiadoDTO> registros = this.registroPagoFiadoBO.listarRegistrosPagoFiadoPorAliasClienteConFechaFin(
//                "a",
//                fechaFin
//            );

//            Assert.IsNotNull(registros);
//            Assert.IsTrue(registros.Count >= 0);
//        }

//    }
//}
