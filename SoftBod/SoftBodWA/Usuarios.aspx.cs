using SoftBodBusiness;
using SoftBodBusiness.SoftWSUsuario;
using System;
using System.Collections.Generic;
using System.Linq;
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
            ListaUsuarios = usuarioBO.listarTodosUsuarios();

            rptUsuarios.DataSource = ListaUsuarios;
            rptUsuarios.DataBind();

            ActualizarTotales();
        }

        private void ActualizarTotales()
        {
            lblTotalOperarios.InnerText = ListaUsuarios.Count.ToString();
            lblActivos.InnerText = ListaUsuarios.Count(u => u.activo).ToString();
        }

        protected void rptUsuarios_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            string[] args = e.CommandArgument.ToString().Split('|');

            switch (e.CommandName)
            {
                case "Editar":
                    // CommandArgument: nombre|usuario|correo|telefono
                    string nombre = args[0];
                    string usuario = args[1];
                    string email = args[2];
                    string telefono = args[3];

                    txtEditNombreCompleto.Text = nombre;
                    txtEditUsuario.Text = usuario;
                    txtEditEmail.Text = email;
                    txtEditTelefono.Text = telefono;

                    hdnEditUsuarioID.Value = usuario;

                    updEditarOperario.Update();

                    ScriptManager.RegisterStartupScript(this, GetType(), "showEditarOperario",
                        "var myModal = new bootstrap.Modal(document.getElementById('modalEditarOperario')); myModal.show();", true);
                    break;

                case "Eliminar":
                    // ✅ CommandArgument: nombre|usuario
                    string nombreEliminar = args[0];
                    string usuarioEliminar = args[1];

                    ltNombreEliminar.Text = nombreEliminar;
                    hdnUsuarioIDEliminar.Value = usuarioEliminar;

                    updEliminarOperario.Update();

                    ScriptManager.RegisterStartupScript(this, GetType(), "showEliminarOperario",
                        "var myModal = new bootstrap.Modal(document.getElementById('modalEliminarOperario')); myModal.show();", true);
                    break;

                case "ToggleActivo":
                    // ✅ CommandArgument: usuarioId|usuario|correo|tipoUsuarios|contrasenha|nombre|telefono|activo
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

                        CargarOperarios();
                    }
                    catch (Exception ex)
                    {
                        MostrarMensajeError($"Error al actualizar el estado: {ex.Message}");
                    }
                    break;
            }
        }

        protected void btnAgregarOperario_Click(object sender, EventArgs e)
        {
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

                if (usuarioActual != null)
                {
                    usuarioBO.modificarUsuario(
                        usuarioActual.usuarioId,
                        nuevoUsuario,
                        nuevoCorreo,
                        usuarioActual.tipoUsuarios.ToString(),
                        usuarioActual.contrasenha,
                        nuevoNombre,
                        nuevoTelefono,
                        usuarioActual.activo
                    );

                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alertaEdicion",
                        @"var modal = bootstrap.Modal.getInstance(document.getElementById('modalEditarOperario')); 
                          if(modal) modal.hide();
                          document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
                          document.body.classList.remove('modal-open');
                          document.body.style.overflow = '';
                          alert('Operario actualizado exitosamente');
                          window.location.href = window.location.pathname;", true);
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

                    int tieneReg = historialBO.listarHistorialOperacionesPorUsuario(usuario.usuarioId).Count;
                    string mensaje = "";
                    if( tieneReg > 0)
                    {
                        int resultadoLogico = usuarioBO.eliminarLogicoUsuario(
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
                        int resultado = usuarioBO.eliminarUsuario(usuario.usuarioId);
                        mensaje = "Operario eliminado exitosamente.";
                    }

                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alertaEliminacion",
                    $@"var modal = bootstrap.Modal.getInstance(document.getElementById('modalEliminarOperario')); 
                       if(modal) modal.hide();
                       document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
                       document.body.classList.remove('modal-open');
                       document.body.style.overflow = '';
                       alert('{mensaje}');
                       window.location.href = window.location.pathname;", true);
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

                if (!ValidarFormatoContraseña(contraseña, out mensajeError))
                {
                    MostrarMensajeError(mensajeError);
                    return;
                }

                usuarioBO.insertarUsuario(usuario, correo, "OPERARIO", contraseña, nombre, telefono, true);

                LimpiarCamposModal();

                ScriptManager.RegisterStartupScript(this, this.GetType(), "alertaOperario",
                    @"var modal = bootstrap.Modal.getInstance(document.getElementById('modalAgregarOperario')); 
                      if(modal) modal.hide();
                      document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
                      document.body.classList.remove('modal-open');
                      document.body.style.overflow = '';
                      alert('Operario creado exitosamente');
                      window.location.href = window.location.pathname;", true);
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

        private void MostrarMensajeError(string mensaje)
        {
            ScriptManager.RegisterStartupScript(this, this.GetType(), "error",
                $"alert('{mensaje}');", true);
        }
    }
}