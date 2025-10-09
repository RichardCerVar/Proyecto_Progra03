<%@ Page Title="Usuarios" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Usuarios.aspx.cs" Inherits="SoftBodWA.Usuarios" %>

<asp:Content ID="MainContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid px-3 py-3">
        <h4 class="fw-bold mb-4">Gestión de Operarios</h4>

        <!-- BOTÓN AGREGAR -->
        <div class="d-flex justify-content-end mb-3">
            <asp:Button ID="btnAgregarOperario" runat="server" Text="+ Agregar Operario" CssClass="btn btn-primary fw-bold shadow-sm" />
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
</asp:Content>