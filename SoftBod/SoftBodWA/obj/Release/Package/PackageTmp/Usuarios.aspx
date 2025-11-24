<%@ Page Title="Usuarios" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Usuarios.aspx.cs" Inherits="SoftBodWA.Usuarios" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Scripts/SoftBodScripts/AgregarOperario.js"></script>
    <script src="Scripts/SoftBodScripts/EditarOperario.js"></script>
    <script src="Scripts/SoftBodScripts/EliminarOperario.js"></script>
    <script src="Scripts/SoftBodScripts/Contrasena.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</asp:Content>



<asp:Content ID="MainContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid px-3 py-3">
        <h4 class="fw-bold mb-4">Gestión de Operarios</h4>

        <!-- BOTÓN AGREGAR (Actualizado a LinkButton estilizado) -->
        <div class="d-flex justify-content-end mb-4">
            <asp:LinkButton 
                ID="btnAgregarOperario" 
                runat="server" 
                CssClass="btn btn-primary fw-bold shadow-sm rounded-3 d-flex align-items-center gap-2 px-3 py-2" 
                OnClick="btnAgregarOperario_Click">
                <i class="fa-solid fa-user-plus"></i>
                + Agregar Operario
            </asp:LinkButton>
        </div>

        <!-- RESUMEN DE OPERARIOS (Diseño limpio) -->
        <div class="row mb-4">
            <div class="col-md-6 mb-3">
                <div class="card border-0 shadow-sm rounded-3" style="background-color: #f0f5ff;">
                    <div class="card-body d-flex justify-content-between align-items-center py-3">
                        <div>
                            <i class="bi bi-people fs-3 text-primary"></i>
                            <span class="d-block text-muted small">Total Operarios</span>
                        </div>
                        <h4 class="fw-bold text-primary mb-0" id="lblTotalOperarios" runat="server">0</h4>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <div class="card border-0 shadow-sm rounded-3" style="background-color: #eafaf0;">
                    <div class="card-body d-flex justify-content-between align-items-center py-3">
                        <div>
                            <i class="bi bi-person-check fs-3 text-success"></i>
                            <span class="d-block text-muted small">Operarios Activos</span>
                        </div>
                        <h4 class="fw-bold text-success mb-0" id="lblActivos" runat="server">0</h4>
                    </div>
                </div>
            </div>
        </div>

        <!-- MENSAJE CUANDO NO HAY USUARIOS -->
        <asp:Panel ID="pnlSinUsuarios" runat="server" Visible="false">
            <div class="card border-0 shadow-sm rounded-3">
                <div class="card-body text-center py-5">
                    <i class="bi bi-people fs-1 text-muted mb-3"></i>
                    <h5 class="text-muted mb-2">No hay operarios registrados</h5>
                    <p class="text-muted small mb-0">Comienza agregando un nuevo operario usando el botón "+ Agregar Operario"</p>
                </div>
            </div>
        </asp:Panel>

        <!-- LISTA DE OPERARIOS -->
        <asp:Repeater ID="rptUsuarios" runat="server" OnItemCommand="rptUsuarios_ItemCommand">
            <ItemTemplate>
                <div class="card border-0 shadow-sm mb-3 rounded-3 <%# (bool)Eval("activo") ? "" : "opacity-75 bg-light" %>">
                    <div class="card-body d-flex justify-content-between align-items-center">
                        
                        <!-- Información del Operario -->
                        <div class="flex-grow-1 me-3">
                            <h6 class="fw-bold mb-0"><%# Eval("nombre") %></h6>
                            <small class="text-primary fw-semibold d-block">@<%# Eval("usuario") %></small>
                            <small class="text-muted"><i class="bi bi-envelope me-1"></i><%# Eval("correo") %></small>
                            <small class="text-muted d-block"><i class="bi bi-phone me-1"></i><%# Eval("telefono") %></small>
                        </div>

                        <!-- Estado y Botonería -->
                        <div class="d-flex align-items-center gap-2">
                            
                            <!-- Toggle Activo -->
                            <div class="form-check form-switch me-3" style="min-width: 100px;">
                                <input
                                    class="form-check-input"
                                    type="checkbox"
                                    role="switch"
                                    id='<%# "switch_" + Eval("usuario") %>'
                                    <%# (bool)Eval("activo") ? "checked" : "" %>
                                    onclick="document.getElementById('<%# Container.FindControl("btnToggleActivo").ClientID %>').click();"
                                />
                                <label
                                    class="form-check-label text-muted small"
                                    for='<%# "switch_" + Eval("usuario") %>'>
                                    <%# (bool)Eval("activo") ? "Activo" : "Inactivo" %>
                                </label>
                            </div>
                            
                            <!-- Botón Toggle Activo (Oculto) -->
                            <asp:Button
                                ID="btnToggleActivo"
                                runat="server"
                                style="display:none;" 
                                CommandName="ToggleActivo"
                                CommandArgument='<%# Eval("usuarioId") + "|" + Eval("usuario") + "|" + Eval("correo") + "|" + Eval("tipoUsuarios") + "|" + Eval("contrasenha") + "|" + Eval("nombre") + "|" + Eval("telefono") + "|" + Eval("activo") %>'
                            />

                            <!-- Botón Editar (LINKBUTTON ESTILIZADO) -->
                            <asp:LinkButton ID="btnEditar" runat="server"
                                CssClass="btn btn-outline-secondary rounded-3 d-flex align-items-center justify-content-center"
                                Style="width: 45px; height: 45px; --bs-btn-border-color: #dee2e6; color: #495057;"
                                CommandName="Editar"
                                CommandArgument='<%# Eval("nombre") + "|" + Eval("usuario") + "|" + Eval("correo") + "|" + Eval("telefono") + "|" + Eval("contrasenha")%>'
                                ToolTip="Editar Datos del Operario">
                                <i class="fa-solid fa-pen-to-square"></i>
                            </asp:LinkButton>
                            
                            <!-- Botón Eliminar (LINKBUTTON ESTILIZADO) -->
                            <asp:LinkButton ID="btnEliminar" runat="server"
                                CssClass="btn btn-outline-danger rounded-3 d-flex align-items-center justify-content-center"
                                Style="width: 45px; height: 45px; border-color: #dc3545; color: #dc3545;"
                                CommandName="Eliminar"
                                CommandArgument='<%# Eval("nombre") + "|" + Eval("usuario") %>'
                                ToolTip="Eliminar Operario">
                                <i class="fa-solid fa-trash-can"></i>
                            </asp:LinkButton>
                        </div>
                    </div>
                </div>
            </ItemTemplate>
        </asp:Repeater>
    </div>

    <!-- Modals (Sin cambios en diseño, solo estructura) -->
    <asp:ScriptManager runat="server"></asp:ScriptManager>

    <!-- Modal Agregar Operario -->
    <div class="modal fade" id="modalAgregarOperario" tabindex="-1" aria-labelledby="modalAgregarOperarioLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content border-0 shadow-lg rounded-4">
                <div class="modal-header border-0 pb-0">
                    <h5 class="modal-title fw-bold fs-5" id="modalAgregarOperarioLabel">Agregar Nuevo Operario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body pt-2">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <!-- Nombre Completo -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Nombre Completo" AssociatedControlID="txtNombreCompleto"></asp:Label>
                                <asp:TextBox ID="txtNombreCompleto" CssClass="form-control" placeholder="Ej: Ana Rodríguez" runat="server"></asp:TextBox>
                            </div>
                            <!-- Usuario -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Usuario" AssociatedControlID="txtUsuario"></asp:Label>
                                <asp:TextBox ID="txtUsuario" CssClass="form-control" placeholder="ana.rodriguez" runat="server"></asp:TextBox>
                            </div>
                            <!-- Email -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Email" AssociatedControlID="txtEmail"></asp:Label>
                                <asp:TextBox ID="txtEmail" CssClass="form-control" placeholder="ana@bodega.com" runat="server" TextMode="Email"></asp:TextBox>
                            </div>
                            <!-- Teléfono -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Teléfono" AssociatedControlID="txtTelefono"></asp:Label>
                                <asp:TextBox ID="txtTelefono" CssClass="form-control" placeholder="987-654-321" runat="server"></asp:TextBox>
                            </div>
                            <!-- Contraseña Temporal -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Contraseña Temporal" AssociatedControlID="txtContraseñaTemporal"></asp:Label>
                                <div class="input-group password-container">
                                <asp:TextBox ID="txtContraseñaTemporal" CssClass="form-control" TextMode="Password" placeholder="********" runat="server" ClientIDMode="Static"></asp:TextBox>
                                <button 
                                    class="btn btn-outline-secondary toggle-password" 
                                    type="button" 
                                    id="togglePassword"
                                    onclick="togglePasswordVisibility('txtContraseñaTemporal'); return false;"> <i class="bi bi-eye"></i> </button>
                                </div>
                            </div>
                            <!-- Botón Crear Operario -->
                            <asp:Button ID="btnCrearOperario" runat="server"
                                CssClass="btn btn-dark w-100 py-2 fw-semibold rounded-3"
                                Text="Crear Operario"
                                OnClick="btnCrearOperario_Click" 
                                OnClientClick="this.disabled = true; this.value='Procesando...';"
                                UseSubmitBehavior="false"
                                />
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Editar Operario -->
    <div class="modal fade" id="modalEditarOperario" tabindex="-1" aria-labelledby="modalEditarOperarioLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content border-0 shadow-lg rounded-4">
                <div class="modal-header border-0 pb-0">
                    <h5 class="modal-title fw-bold fs-5" id="modalEditarOperarioLabel">Editar Operario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body pt-2">
                    <asp:UpdatePanel ID="updEditarOperario" runat="server" UpdateMode="Conditional">
                        <ContentTemplate>

                            <asp:HiddenField ID="hdnEditUsuarioID" runat="server" />

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Nombre Completo" AssociatedControlID="txtEditNombreCompleto"></asp:Label>
                                <asp:TextBox ID="txtEditNombreCompleto" CssClass="form-control" placeholder="Ej: Ana Rodríguez" runat="server"></asp:TextBox>
                            </div>

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Usuario" AssociatedControlID="txtEditUsuario"></asp:Label>
                                <asp:TextBox ID="txtEditUsuario" CssClass="form-control" placeholder="ana.rodriguez" runat="server"></asp:TextBox>
                            </div>

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Email" AssociatedControlID="txtEditEmail"></asp:Label>
                                <asp:TextBox ID="txtEditEmail" CssClass="form-control" placeholder="ana@bodega.com" runat="server" TextMode="Email"></asp:TextBox>
                            </div>

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Teléfono" AssociatedControlID="txtEditTelefono"></asp:Label>
                                <asp:TextBox ID="txtEditTelefono" CssClass="form-control" placeholder="987654321" runat="server"></asp:TextBox>
                            </div>
                                <div class="mb-3">
                                    <asp:Label CssClass="form-label fw-semibold" 
                                               runat="server" 
                                               Text="Contraseña Temporal" 
                                               AssociatedControlID="txtEditContrasena">
                                    </asp:Label>
                                    <div class="input-group password-container">
                                        <asp:TextBox 
                                            ID="txtEditContrasena"
                                            runat="server"
                                            CssClass="form-control"
                                            TextMode="Password"
                                            placeholder="********"
                                            ClientIDMode="Static">
                                        </asp:TextBox>
                                        <button 
                                            id="togglePasswordEdit"
                                            class="btn btn-outline-secondary"
                                            type="button"
                                            onclick="togglePasswordVisibilityEdit('txtEditContrasena','togglePasswordEdit'); return false;">
                                            <i class="bi bi-eye"></i>
                                        </button>
                                    </div>
                                </div>
                            <asp:Button ID="btnGuardarCambios" runat="server"
                                CssClass="btn btn-dark w-100 py-2 fw-semibold rounded-3"
                                Text="Guardar Cambios"
                                OnClick="btnGuardarCambios_Click" 
                                OnClientClick="this.disabled = true; this.value='Procesando...';"
                                UseSubmitBehavior="false"/>
                        </ContentTemplate>

                        <Triggers>
                            <asp:AsyncPostBackTrigger ControlID="btnGuardarCambios" EventName="Click" />
                        </Triggers>

                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Eliminar Operario -->
    <div class="modal fade" id="modalEliminarOperario" tabindex="-1" aria-labelledby="modalEliminarLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content border-0 shadow-lg rounded-4">
                <asp:UpdatePanel ID="updEliminarOperario" runat="server" UpdateMode="Conditional">
                    <ContentTemplate>
                        <div class="modal-body p-4 text-center">
                            <i class="bi bi-trash-fill fs-1 text-danger mb-2"></i>
                            <h5 class="fw-bold mb-3" id="modalEliminarLabel">¿Eliminar operario?</h5>
                            <p class="text-muted">
                                Esta acción no se puede deshacer. Se eliminará permanentemente la
                                cuenta de <strong class="text-dark"><asp:Literal ID="ltNombreEliminar" runat="server" /></strong> del sistema.
                            </p>
                            <asp:HiddenField ID="hdnUsuarioIDEliminar" runat="server" />
                            <div class="d-flex gap-2 mt-4">
                                <button type="button" class="btn btn-light w-100" data-bs-dismiss="modal">Cancelar</button>
                                <asp:Button ID="btnConfirmarEliminacion" runat="server" 
                                    Text="Sí, Eliminar" 
                                    CssClass="btn btn-danger w-100" 
                                    OnClick="btnConfirmarEliminacion_Click" 
                                    OnClientClick="this.disabled = true; this.value='Procesando...';"
                                    UseSubmitBehavior="false"/>
                            </div>
                        </ContentTemplate>
                        <Triggers>
                            <asp:AsyncPostBackTrigger ControlID="btnConfirmarEliminacion" EventName="Click" />
                        </Triggers>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
  

</asp:Content>