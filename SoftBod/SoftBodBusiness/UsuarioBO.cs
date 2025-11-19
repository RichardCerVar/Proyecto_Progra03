using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftWSUsuario;

namespace SoftBodBusiness
{
    public class UsuarioBO
    {
        private UsuarioClient usuarioSOAP;

        public UsuarioBO()
        {
            usuarioSOAP = new UsuarioClient();
        }

        public int insertarUsuario(string usuario, string correo, string tipoUsuarios,
                                   string contrasenha, string nombre, string telefono, bool activo)
        {
            // Si aparece error por demasiados argumentos → borra el 0
            return this.usuarioSOAP.insertarUsuario(usuario, correo, tipoUsuarios,
                                                    contrasenha, nombre, telefono, activo);
        }

        public int eliminarUsuario(int usuarioId)
        {
            return this.usuarioSOAP.eliminarUsuario(usuarioId);
        }

        public int modificarUsuario(int usuarioId, string usuario, string correo, string tipoUsuarios,
                                   string contrasenha, string nombre, string telefono, bool activo)
        {
            return this.usuarioSOAP.modificarUsuario(usuarioId, usuario, correo, tipoUsuarios,
                                                     contrasenha, nombre, telefono, activo);
        }

        public int eliminarLogicoUsuario(int usuarioId, string usuario, string correo, string tipoUsuarios,
                                         string contrasenha, string nombre, string telefono, bool activo)
        {
            return this.usuarioSOAP.eliminarLogicoUsuario(usuarioId, usuario, correo, tipoUsuarios,
                                                          contrasenha, nombre, telefono, activo);
        }

        public usuarioDTO obtenerUsuarioPorId(int usuarioId)
        {
            return this.usuarioSOAP.obtenerUsuarioPorId(usuarioId);
        }

        public usuarioDTO obtenerCuentaUsuario(string correo, string contrasenha)
        {
            return this.usuarioSOAP.obtenerCuentaUsuario(correo, contrasenha);
        }

        public usuarioDTO obtenerUsuarioPorCorreo(string emailUser)
        {
            return this.usuarioSOAP.obtenerUsuarioPorCorreo(emailUser);
        }

        public List<usuarioDTO> listarTodosUsuarios()
        {
            return this.usuarioSOAP.listarTodosUsuarios().ToList();
        }

        public List<usuarioDTO> listarUsuariosActivos()
        {
            return this.usuarioSOAP.listarUsuariosActivos().ToList();
        }

        public List<usuarioDTO> listarUsuariosInactivos()
        {
            return this.usuarioSOAP.listarUsuariosInactivos().ToList();
        }

        public List<usuarioDTO> listarUsuariosPorNombreParcial(string nombreUser)
        {
            return this.usuarioSOAP.listarUsuariosPorNombreParcial(nombreUser).ToList();
        }
    }
}
