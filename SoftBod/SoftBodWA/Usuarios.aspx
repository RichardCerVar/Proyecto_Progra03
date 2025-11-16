<%@ Page Title="Usuarios" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Usuarios.aspx.cs" Inherits="SoftBodWA.Usuarios" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Scripts/SoftBodScripts/AgregarOperario.js"></script>
    <script src="Scripts/SoftBodScripts/EditarOperario.js"></script>
    <script src="Scripts/SoftBodScripts/EliminarOperario.js"></script>
</asp:Content>

<asp:Content ID="MainContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid px-3 py-3">
        <h4 class="fw-bold mb-4">Gestión de Operarios</h4>

        <!-- BOTÓN AGREGAR -->
        <div class="d-flex justify-content-end mb-3">
            <asp:Button ID="btnAgregarOperario" runat="server" Text="+ Agregar Operario" CssClass="btn btn-primary fw-bold shadow-sm" OnClick="btnAgregarOperario_Click"/>
        </div>

        <!-- RESUMEN DE OPERARIOS -->
        <div class="row mb-4">
            <div class="col-md-6 mb-3">
                <div class="card border-0 shadow-sm" style="background-color: #f0f5ff;">
                    <div class="card-body d-flex justify-content-between align-items-center">
                        <div>
                            <i class="bi bi-people fs-3 text-primary"></i>
                            <span class="d-block text-muted small">Total Operarios</span>
                        </div>
                        <h4 class="fw-bold text-primary mb-0" id="lblTotalOperarios" runat="server">0</h4>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <div class="card border-0 shadow-sm" style="background-color: #eafaf0;">
                    <div class="card-body d-flex justify-content-between align-items-center">
                        <div>
                            <i class="bi bi-person-check fs-3 text-success"></i>
                            <span class="d-block text-muted small">Operarios Activos</span>
                        </div>
                        <h4 class="fw-bold text-success mb-0" id="lblActivos" runat="server">0</h4>
                    </div>
                </div>
            </div>
        </div>

        <!-- LISTA DE OPERARIOS -->
        <asp:Repeater ID="rptUsuarios" runat="server" OnItemCommand="rptUsuarios_ItemCommand">
            <ItemTemplate>
                <div class="card border-0 shadow-sm mb-3 <%# (bool)Eval("activo") ? "" : "opacity-50" %>">
                    <div class="card-body d-flex justify-content-between align-items-center">
                        <div>
                            <h6 class="fw-bold mb-0"><%# Eval("nombre") %></h6>
                            <small class="text-muted">@<%# Eval("usuario") %></small><br />
                            <small class="text-muted"><%# Eval("correo") %></small><br />
                            <small class="text-muted"><%# Eval("telefono") %></small>
                        </div>

                        <div class="d-flex align-items-center gap-3">
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch" <%# (bool)Eval("Activo") ? "checked" : "" %> disabled />
                                <label class="form-check-label text-muted"><%# (bool)Eval("Activo") ? "Activo" : "Desactivado" %></label>
                            </div>

                            <asp:Button ID="btnEditar" runat="server" Text="✎" CssClass="btn btn-outline-primary btn-sm" CommandArgument='<%# Eval("nombre") + "|" + Eval("usuario")+ "|" + Eval("correo")+ "|" + Eval("telefono")%>' CommandName="Editar" />
                            <asp:Button ID="btnEliminar" runat="server" Text="🗑" CssClass="btn btn-outline-danger btn-sm" CommandArgument='<%# Eval("nombre") %>' CommandName="Eliminar" />
                        </div>
                    </div>
                </div>
            </ItemTemplate>
        </asp:Repeater>
    </div>

     <!-- Modal Agregar Operario -->
     <asp:ScriptManager runat="server"></asp:ScriptManager>

     <div class="modal fade" id="modalAgregarOperario" tabindex="-1" aria-labelledby="modalAgregarOperarioLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content border-0 shadow-lg rounded-4">
            
                <!-- Encabezado -->
                <div class="modal-header border-0 pb-0">
                    <h5 class="modal-title fw-bold fs-5" id="modalAgregarOperarioLabel">Agregar Nuevo Operario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <!-- Cuerpo -->
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
                                <asp:TextBox ID="txtEmail" CssClass="form-control" placeholder="ana@bodega.com" runat="server"></asp:TextBox>
                            </div>

                            <!-- Teléfono -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Teléfono" AssociatedControlID="txtTelefono"></asp:Label>
                                <asp:TextBox ID="txtTelefono" CssClass="form-control" placeholder="987-654-321" runat="server"></asp:TextBox>
                            </div>

                            <!-- Contraseña Temporal -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Contraseña Temporal" AssociatedControlID="txtContraseñaTemporal"></asp:Label>
                                <asp:TextBox ID="txtContraseñaTemporal" CssClass="form-control" TextMode="Password" placeholder="********" runat="server"></asp:TextBox>
                            </div>

                            <!-- Botón Crear Operario -->
                            <asp:LinkButton ID="btnCrearOperario" runat="server"
                                CssClass="btn btn-dark w-100 py-2 fw-semibold rounded-3"
                                Text="Crear Operario"
                                OnClick="btnCrearOperario_Click" />
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>

    <!--editar operario-->
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
                                <asp:TextBox ID="txtEditTelefono" CssClass="form-control" placeholder="987-654-321" runat="server" TextMode="Phone"></asp:TextBox>
                            </div>
                        
                            <asp:LinkButton ID="btnGuardarCambios" runat="server"
                                CssClass="btn btn-dark w-100 py-2 fw-semibold rounded-3"
                                Text="Guardar Cambios"
                                OnClick="btnGuardarCambios_Click" />

                        </ContentTemplate>
                        <Triggers>
                             <asp:AsyncPostBackTrigger ControlID="btnGuardarCambios" EventName="Click" />
                        </Triggers>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>

    <!--Eliminar operario-->
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
                                    OnClick="btnConfirmarEliminacion_Click" />
                            </div>
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