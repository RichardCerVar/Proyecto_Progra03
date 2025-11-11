using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftBodBusiness;
using SoftBodBusiness.SoftWSUsuario;

namespace SoftBodPruebas
{
    [TestClass]
    public class UsuarioBOTest
    {
        private UsuarioBO usuarioBO;

        public UsuarioBOTest()
        {
            this.usuarioBO = new UsuarioBO();
        }

        //[TestMethod]
        //public void TestInsertarUsuario()
        //{
        //    Console.WriteLine("Insertar Usuario");

        //    int resultado = this.usuarioBO.insertarUsuario(
        //        "GomezBol",
        //        "GomezBol.perez@example.com",
        //        "ADMINISTRADOR",
        //        "password13",
        //        "GomezBol Pérez",
        //        "987654321",
        //        true
        //    );

        //    Assert.IsTrue(resultado > 0);

        //}

        //[TestMethod]
        //public void TestEliminarLogicoUsuario()
        //{
        //    Console.WriteLine("Eliminar Lógico Usuario");

        //    int resultado = this.usuarioBO.eliminarLogicoUsuario(
        //        10,
        //        "op_delivery",
        //        "carlos.lopez@example.com",
        //        "OPERARIO",
        //        "pass789",
        //        "Carlos López",
        //        "923456789",
        //        false // Marcado como inactivo
        //    );

        //    Assert.IsTrue(resultado > 0);
        //}

        [TestMethod]
        public void TestObtenerUsuarioPorId()
        {
            Console.WriteLine("Obtener Usuario Por Id");

            usuarioDTO usuario = this.usuarioBO.obtenerUsuarioPorId(11);

            Assert.IsNotNull(usuario);
            Assert.IsTrue(usuario.usuarioId > 0);
            Assert.IsNotNull(usuario.nombre);
            Assert.IsNotNull(usuario.correo);
        }

        [TestMethod]
        public void TestObtenerCuentaUsuario()
        {
            Console.WriteLine("Obtener Cuenta de Usuario (Login)");

            usuarioDTO usuario = this.usuarioBO.obtenerCuentaUsuario(
                "ausente3@bodega.com",
                "op_I3"
            );

            Assert.IsNotNull(usuario);
            Assert.IsNotNull(usuario.correo);
        }

        [TestMethod]
        public void TestObtenerUsuarioPorCorreo()
        {
            Console.WriteLine("Obtener Usuario Por Correo");

            usuarioDTO usuario = this.usuarioBO.obtenerUsuarioPorCorreo(
                "ausente3@bodega.com"
            );

            Assert.IsNotNull(usuario);
            Assert.IsNotNull(usuario.nombre);
            Assert.AreEqual("ausente3@bodega.com", usuario.correo);
        }

        [TestMethod]
        public void TestListarTodosUsuarios()
        {
            Console.WriteLine("Listar Todos los Usuarios");

            List<usuarioDTO> usuarios = this.usuarioBO.listarTodosUsuarios();
            foreach (usuarioDTO t in usuarios)
            {
                Console.WriteLine(t.usuario);
            }
            Assert.IsNotNull(usuarios);
            Assert.IsTrue(usuarios.Count >= 0);
        }

        [TestMethod]
        public void TestListarUsuariosActivos()
        {
            Console.WriteLine("Listar Usuarios Activos");

            List<usuarioDTO> usuarios = this.usuarioBO.listarUsuariosActivos();
            foreach (usuarioDTO t in usuarios)
            {
                Console.WriteLine(t.usuario);
            }
            Assert.IsNotNull(usuarios);
            Assert.IsTrue(usuarios.Count >= 0);
        }

        [TestMethod]
        public void TestListarUsuariosInactivos()
        {
            Console.WriteLine("Listar Usuarios Inactivos");

            List<usuarioDTO> usuarios = this.usuarioBO.listarUsuariosInactivos();
            foreach (usuarioDTO t in usuarios)
            {
                Console.WriteLine(t.usuario);
                Console.WriteLine(t.activo);
            }
            Assert.IsNotNull(usuarios);
            Assert.IsTrue(usuarios.Count >= 0);
        }

        [TestMethod]
        public void TestListarUsuariosPorNombreParcial()
        {
            Console.WriteLine("Listar Usuarios por Nombre Parcial");

            List<usuarioDTO> usuarios = this.usuarioBO.listarUsuariosPorNombreParcial("R");
            foreach (usuarioDTO t in usuarios)
            {
                Console.WriteLine(t.usuario);
            }
            Assert.IsNotNull(usuarios);
            Assert.IsTrue(usuarios.Count >= 0);

            // Test con búsqueda vacía
            //usuarios = this.usuarioBO.listarUsuariosPorNombreParcial("");
            //Assert.IsNotNull(usuarios);
        }

        //[TestMethod]
        //public void TestModificarUsuario()
        //{
        //    Console.WriteLine("Modificar Usuario");
        //    Console.WriteLine("Usuario antes: ");
        //    usuarioDTO usu = this.usuarioBO.obtenerUsuarioPorId(21);
        //    Console.WriteLine(usu.correo);
        //    Console.WriteLine("Modificar Usuario");
        //    int resultado = this.usuarioBO.modificarUsuario(
        //        21,
        //        "Noelia",
        //        "Noelia.modificado@example.com",
        //        "ADMINISTRADOR",
        //        "newpassword133",
        //        "Noelia Modificado",
        //        "999889777",
        //        true
        //    );
        //    Console.WriteLine("Usuario despues: ");
        //    usuarioDTO usus = this.usuarioBO.obtenerUsuarioPorId(21);
        //    Console.WriteLine(usus.correo);
        //    Assert.IsTrue(resultado > 0);
        //}
    }
}
