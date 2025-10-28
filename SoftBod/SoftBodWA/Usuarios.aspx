<%@ Page Title="Usuarios" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Usuarios.aspx.cs" Inherits="SoftBodWA.Usuarios" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Scripts/SoftBodScripts/AgregarOperario.js"></script>
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
        <asp:Repeater ID="rptUsuarios" runat="server">
            <ItemTemplate>
                <div class="card border-0 shadow-sm mb-3 <%# (bool)Eval("Activo") ? "" : "opacity-50" %>">
                    <div class="card-body d-flex justify-content-between align-items-center">
                        <div>
                            <h6 class="fw-bold mb-0"><%# Eval("Nombre") %></h6>
                            <small class="text-muted">@<%# Eval("Usuario") %></small><br />
                            <small class="text-muted"><%# Eval("Correo") %></small><br />
                            <small class="text-muted"><%# Eval("Telefono") %></small>
                        </div>

                        <div class="d-flex align-items-center gap-3">
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch" <%# (bool)Eval("Activo") ? "checked" : "" %> disabled />
                                <label class="form-check-label text-muted"><%# (bool)Eval("Activo") ? "Activo" : "Desactivado" %></label>
                            </div>

                            <asp:Button ID="btnEditar" runat="server" Text="✎" CssClass="btn btn-outline-primary btn-sm" CommandArgument='<%# Eval("Usuario") %>' CommandName="Editar" />
                            <asp:Button ID="btnEliminar" runat="server" Text="🗑" CssClass="btn btn-outline-danger btn-sm" CommandArgument='<%# Eval("Usuario") %>' CommandName="Eliminar" />
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

</asp:Content>