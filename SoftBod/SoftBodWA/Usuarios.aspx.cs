using SoftBodBusiness;
using SoftBodBusiness.SoftWSUsuario;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mail;
using System.Text.RegularExpressions;
using System.Web.UI;
using System.Web.UI.WebControls;
using WSUsuario = SoftBodBusiness.SoftWSUsuario;

namespace SoftBodWA
{
    public partial class Usuarios : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;
        private HistorialOperacionesBO historialBO;

        // Cache de usuarios - solo se carga cuando es null o se fuerza recarga
        private List<WSUsuario.usuarioDTO> ListaUsuarios
        {
            get
            {
                if (ViewState["ListaUsuarios"] == null)
                {
                    ViewState["ListaUsuarios"] = usuarioBO.listarTodosUsuarios();
                }
                return (List<WSUsuario.usuarioDTO>)ViewState["ListaUsuarios"];
            }
            set
            {
                ViewState["ListaUsuarios"] = value;
            }
        }

        // Nueva propiedad para obtener solo los operarios (sin el administrador)
        private List<WSUsuario.usuarioDTO> ListaOperarios
        {
            get
            {
                var lista = ListaUsuarios;
                return lista.Count > 1 ? lista.Skip(1).ToList() : new List<WSUsuario.usuarioDTO>();
            }
        }

        public Usuarios()
        {
            usuarioBO = new UsuarioBO();
            historialBO = new HistorialOperacionesBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarOperarios();
            }
        }

        private void CargarOperarios()
        {
            var operarios = ListaOperarios;

            if (operarios.Count > 0)
            {
                rptUsuarios.DataSource = operarios;
                rptUsuarios.DataBind();
                rptUsuarios.Visible = true;
                pnlSinUsuarios.Visible = false;
            }
            else
            {
                rptUsuarios.Visible = false;
                pnlSinUsuarios.Visible = true;
            }

            ActualizarTotales();
        }

        private void ActualizarTotales()
        {
            var operarios = ListaOperarios;
            lblTotalOperarios.InnerText = operarios.Count.ToString();
            lblActivos.InnerText = operarios.Count(u => u.activo).ToString();
        }

        // Método para forzar recarga de datos desde BD
        private void RecargarDatosYActualizar()
        {
            ViewState["ListaUsuarios"] = null; // Invalida cache
            CargarOperarios();
        }

        protected void rptUsuarios_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            string[] args = e.CommandArgument.ToString().Split('|');

            switch (e.CommandName)
            {
                case "Editar":
                    MostrarModalEditar(args);
                    break;

                case "Eliminar":
                    MostrarModalEliminar(args);
                    break;

                case "ToggleActivo":
                    CambiarEstadoUsuario(args);
                    break;
            }
        }

        private void MostrarModalEditar(string[] args)
        {
            // CommandArgument: nombre|usuario|correo|telefono|contrasena
            txtEditNombreCompleto.Text = args[0];
            txtEditUsuario.Text = args[1];
            txtEditEmail.Text = args[2];
            txtEditTelefono.Text = args[3];
            txtEditContrasena.Attributes["value"] = args[4];
            hdnEditUsuarioID.Value = args[1];

            updEditarOperario.Update();
            ScriptManager.RegisterStartupScript(this, GetType(), "showEditarOperario",
                "var myModal = new bootstrap.Modal(document.getElementById('modalEditarOperario')); myModal.show();", true);
        }

        private void MostrarModalEliminar(string[] args)
        {
            // CommandArgument: nombre|usuario
            ltNombreEliminar.Text = args[0];
            hdnUsuarioIDEliminar.Value = args[1];

            updEliminarOperario.Update();
            ScriptManager.RegisterStartupScript(this, GetType(), "showEliminarOperario",
                "var myModal = new bootstrap.Modal(document.getElementById('modalEliminarOperario')); myModal.show();", true);
        }

        private void CambiarEstadoUsuario(string[] args)
        {
            // CommandArgument: usuarioId|usuario|correo|tipoUsuarios|contrasenha|nombre|telefono|activo
            try
            {
                int usuarioId = int.Parse(args[0]);
                string usuarioNombre = args[1];
                string correo = args[2];
                string tipoUsuario = args[3];
                string contrasenha = args[4];
                string nombreCompleto = args[5];
                string tel = args[6];
                bool estadoActual = bool.Parse(args[7]);
                bool nuevoEstado = !estadoActual;

                usuarioBO.modificarUsuario(
                    usuarioId,
                    usuarioNombre,
                    correo,
                    tipoUsuario,
                    contrasenha,
                    nombreCompleto,
                    tel,
                    nuevoEstado
                );

                RecargarDatosYActualizar();
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al actualizar el estado: {ex.Message}");
            }
        }

        protected void btnAgregarOperario_Click(object sender, EventArgs e)
        {
            LimpiarCamposModal(); // Limpiar antes de mostrar
            ScriptManager.RegisterStartupScript(this, GetType(), "showModalAgregar",
                "var myModal = new bootstrap.Modal(document.getElementById('modalAgregarOperario')); myModal.show();", true);
        }

        protected void btnGuardarCambios_Click(object sender, EventArgs e)
        {
            try
            {
                string usuarioOriginal = hdnEditUsuarioID.Value;
                string nuevoNombre = txtEditNombreCompleto.Text.Trim();
                string nuevoUsuario = txtEditUsuario.Text.Trim();
                string nuevoCorreo = txtEditEmail.Text.Trim();
                string nuevoTelefono = txtEditTelefono.Text.Trim();
                string nuevaContrasena = txtEditContrasena.Text.Trim();

                // Validaciones
                if (!ValidarCamposCompletos(nuevoNombre, nuevoUsuario, nuevoCorreo, nuevoTelefono, "TempPass1!", out string mensajeError))
                {
                    MostrarMensajeError(mensajeError);
                    return;
                }

                if (!ValidarUsuarioUnicoParaEdicion(nuevoNombre, nuevoUsuario, nuevoCorreo, nuevoTelefono, usuarioOriginal, out mensajeError))
                {
                    MostrarMensajeError(mensajeError);
                    return;
                }

                WSUsuario.usuarioDTO usuarioActual = ListaUsuarios.FirstOrDefault(u => u.usuario == usuarioOriginal);
                if (string.IsNullOrEmpty(nuevaContrasena))
                    nuevaContrasena = usuarioActual.contrasenha;

                string mensaje = "";
                if (!ValidarFormatoContraseña(nuevaContrasena,out mensaje))
                {
                    MostrarMensajeError(mensaje);
                    return;
                }

                if (usuarioActual != null)
                {
                    usuarioBO.modificarUsuario(
                        usuarioActual.usuarioId,
                        nuevoUsuario,
                        nuevoCorreo,
                        usuarioActual.tipoUsuarios.ToString(),
                        nuevaContrasena,
                        nuevoNombre,
                        nuevoTelefono,
                        usuarioActual.activo
                    );

                    // ✅ CRÍTICO: Invalidar cache ANTES de mostrar mensaje
                    ViewState["ListaUsuarios"] = null;

                    MostrarMensajeExitoYRecargar("Operario actualizado exitosamente", "modalEditarOperario");
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al actualizar operario: {ex.Message}");
            }
        }

        protected void btnConfirmarEliminacion_Click(object sender, EventArgs e)
        {
            try
            {
                string usuarioEliminar = hdnUsuarioIDEliminar.Value;
                WSUsuario.usuarioDTO usuario = ListaUsuarios.FirstOrDefault(u => u.usuario == usuarioEliminar);

                if (usuario != null)
                {
                    var tieneReg = historialBO.listarHistorialOperacionesPorUsuario(usuario.usuarioId).Count();
                    string mensaje = "";

                    if (tieneReg > 0)
                    {
                        usuarioBO.eliminarLogicoUsuario(
                            usuario.usuarioId,
                            usuario.usuario,
                            usuario.correo,
                            usuario.tipoUsuarios.ToString(),
                            usuario.contrasenha,
                            usuario.nombre,
                            usuario.telefono,
                            false
                        );
                        mensaje = "El operario tenía registros asociados, por lo que se realizó una eliminación lógica.";
                    }
                    else
                    {
                        usuarioBO.eliminarUsuario(usuario.usuarioId);
                        mensaje = "Operario eliminado exitosamente.";
                    }

                    // ✅ CRÍTICO: Invalidar cache ANTES de mostrar mensaje
                    ViewState["ListaUsuarios"] = null;

                    MostrarMensajeExitoYRecargar(mensaje, "modalEliminarOperario");
                }
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al eliminar operario: {ex.Message}");
            }
        }

        protected void btnCrearOperario_Click(object sender, EventArgs e)
        {
            try
            {
                string nombre = txtNombreCompleto.Text.Trim();
                string usuario = txtUsuario.Text.Trim();
                string correo = txtEmail.Text.Trim();
                string telefono = txtTelefono.Text.Trim();
                string contraseña = txtContraseñaTemporal.Text.Trim();

                // Validaciones
                if (!ValidarCamposCompletos(nombre, usuario, correo, telefono, contraseña, out string mensajeError))
                {
                    MostrarMensajeError(mensajeError);
                    return;
                }

                if (!ValidarUsuarioUnico(nombre, usuario, correo, telefono, out mensajeError))
                {
                    MostrarMensajeError(mensajeError);
                    return;
                }

                if (!ValidarFormatoCorreo(correo, out mensajeError))
                {
                    MostrarMensajeError(mensajeError);
                    return;
                }
                if (!ValidarFormatoTelefono(telefono, out mensajeError))
                {
                    MostrarMensajeError(mensajeError);
                    return;
                }

                if (!ValidarFormatoContraseña(contraseña, out mensajeError))
                {
                    MostrarMensajeError(mensajeError);
                    return;
                }

                

                usuarioBO.insertarUsuario(usuario, correo, "OPERARIO", contraseña, nombre, telefono, true);

                // ✅ CRÍTICO: Invalidar cache ANTES de mostrar mensaje y limpiar
                ViewState["ListaUsuarios"] = null;

                LimpiarCamposModal();
                MostrarMensajeExitoYRecargar("Operario creado exitosamente", "modalAgregarOperario");
            }
            catch (Exception ex)
            {
                MostrarMensajeError($"Error al crear operario: {ex.Message}");
            }
        }

        private void LimpiarCamposModal()
        {
            txtNombreCompleto.Text = "";
            txtUsuario.Text = "";
            txtEmail.Text = "";
            txtTelefono.Text = "";
            txtContraseñaTemporal.Text = "";
        }

        // ===== MÉTODOS DE VALIDACIÓN =====

        private bool ValidarCamposCompletos(string nombre, string usuario, string correo, string telefono, string contraseña, out string mensaje)
        {
            mensaje = "";

            if (string.IsNullOrEmpty(nombre) || string.IsNullOrEmpty(usuario) ||
                string.IsNullOrEmpty(correo) || string.IsNullOrEmpty(telefono) ||
                string.IsNullOrEmpty(contraseña))
            {
                mensaje = "Por favor, complete todos los campos.";
                return false;
            }

            if (!telefono.All(char.IsDigit))
            {
                mensaje = "El campo de teléfono solo adminte números.";
                return false;
            }

            return true;
        }

        private bool ValidarUsuarioUnico(string nombre, string usuario, string correo, string telefono, out string mensaje)
        {
            mensaje = "";
            List<string> camposDuplicados = new List<string>();

            if (ListaUsuarios.Any(u => u.usuario.Equals(usuario, StringComparison.OrdinalIgnoreCase)))
                camposDuplicados.Add("Usuario");

            if (ListaUsuarios.Any(u => u.correo.Equals(correo, StringComparison.OrdinalIgnoreCase)))
                camposDuplicados.Add("Correo");

            if (ListaUsuarios.Any(u => u.nombre.Equals(nombre, StringComparison.OrdinalIgnoreCase)))
                camposDuplicados.Add("Nombre");

            if (ListaUsuarios.Any(u => u.telefono.Equals(telefono, StringComparison.OrdinalIgnoreCase)))
                camposDuplicados.Add("Teléfono");

            if (camposDuplicados.Count > 0)
            {
                mensaje = $"Ya existe un usuario con el mismo: {string.Join(", ", camposDuplicados)}.";
                return false;
            }

            return true;
        }

        private bool ValidarUsuarioUnicoParaEdicion(string nombre, string usuario, string correo, string telefono, string usuarioOriginal, out string mensaje)
        {
            mensaje = "";
            List<string> camposDuplicados = new List<string>();

            if (ListaUsuarios.Any(u => u.usuario.Equals(usuario, StringComparison.OrdinalIgnoreCase) && u.usuario != usuarioOriginal))
                camposDuplicados.Add("Usuario");

            if (ListaUsuarios.Any(u => u.correo.Equals(correo, StringComparison.OrdinalIgnoreCase) && u.usuario != usuarioOriginal))
                camposDuplicados.Add("Correo");

            if (ListaUsuarios.Any(u => u.nombre.Equals(nombre, StringComparison.OrdinalIgnoreCase) && u.usuario != usuarioOriginal))
                camposDuplicados.Add("Nombre");

            if (ListaUsuarios.Any(u => u.telefono.Equals(telefono, StringComparison.OrdinalIgnoreCase) && u.usuario != usuarioOriginal))
                camposDuplicados.Add("Teléfono");

            if (camposDuplicados.Count > 0)
            {
                mensaje = $"Ya existe otro usuario con el mismo: {string.Join(", ", camposDuplicados)}.";
                return false;
            }

            return true;
        }

        private bool ValidarFormatoContraseña(string contraseña, out string mensaje)
        {
            mensaje = "";
            string formatPassword = @"^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$";

            if (!Regex.IsMatch(contraseña, formatPassword))
            {
                mensaje = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un carácter especial.";
                return false;
            }

            return true;
        }
        private bool ValidarFormatoCorreo(string correo, out string mensaje)
        {
            mensaje = "";

            try
            {
                var mail = new MailAddress(correo);
                return true;
            }
            catch
            {
                mensaje = "El formato del correo ingresado no es válido.";
                return false;
            }
        }

        private bool ValidarFormatoTelefono(string telefono, out string mensaje)
        {
            mensaje = "";

            if (telefono.Length != 9 || !telefono.All(char.IsDigit))
            {
                mensaje="El teléfono debe tener máximo 9 dígitos.";
                return false;
            }
            return true;
        }

        // ===== MÉTODOS DE UI =====

        private void MostrarMensajeError(string mensaje)
        {
            ScriptManager.RegisterStartupScript(this, this.GetType(), "error",
                $"alert('{mensaje}');", true);
        }

        private void MostrarMensajeExitoYRecargar(string mensaje, string modalId)
        {
            ScriptManager.RegisterStartupScript(this, this.GetType(), "exitoYRecarga",
                $@"var modal = bootstrap.Modal.getInstance(document.getElementById('{modalId}')); 
                   if(modal) modal.hide();
                   document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
                   document.body.classList.remove('modal-open');
                   document.body.style.overflow = '';
                   alert('{mensaje}');
                   window.location.href = window.location.pathname;", true);
        }
    }
}