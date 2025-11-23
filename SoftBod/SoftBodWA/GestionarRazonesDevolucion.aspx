<%@ Page Title="Gestionar Razones de Devolución" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="GestionarRazonesDevolucion.aspx.cs" Inherits="SoftBodWA.GestionarRazonesDevolucion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h4 class="dashboard-title mb-1">Gestionar Razones de Devolución</h4>
                <small class="text-muted">Administra las razones disponibles para procesar devoluciones</small>
            </div>
            <asp:LinkButton ID="btnVolver" runat="server" 
                CssClass="btn btn-outline-secondary btn-sm"
                OnClick="btnVolver_Click">
                <i class="bi bi-arrow-left me-1"></i>Volver
            </asp:LinkButton>
        </div>

        <div class="row">
            <!-- Formulario para Agregar Nueva Razón -->
            <div class="col-md-5 mb-4">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h6 class="mb-0"><i class="bi bi-plus-circle me-2"></i>Agregar Nueva Razón</h6>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <label for="txtDescripcionRazon" class="form-label">Descripción *</label>
                            <asp:TextBox ID="txtDescripcionRazon" runat="server" 
                                CssClass="form-control" 
                                MaxLength="200"
                                placeholder="Ej: Producto defectuoso"
                                TextMode="MultiLine"
                                Rows="3">
                            </asp:TextBox>
                            <small class="text-muted">Máximo 200 caracteres</small>
                        </div>
                        
                        <div class="d-grid">
                            <asp:LinkButton ID="btnAgregarRazon" runat="server" 
                                CssClass="btn btn-primary"
                                OnClick="btnAgregarRazon_Click">
                                <i class="bi bi-check-circle me-1"></i>Agregar Razón
                            </asp:LinkButton>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Lista de Razones Existentes -->
            <div class="col-md-7">
                <div class="card">
                    <div class="card-header">
                        <h6 class="mb-0"><i class="bi bi-list-ul me-2"></i>Razones Registradas</h6>
                    </div>
                    <div class="card-body p-0">
                        <asp:Repeater ID="rptRazones" runat="server">
                            <ItemTemplate>
                                <div class="list-group-item d-flex justify-content-between align-items-center border-bottom py-3">
                                    <div class="flex-grow-1 me-3">
                                        <div class="fw-bold mb-1"><%# Eval("descripcion") %></div>
                                        <small class="text-muted">
                                            <i class="bi bi-hash"></i>ID: <%# Eval("razonDevolucionId") %>
                                        </small>
                                    </div>
                                    
                                    <!-- Botón Eliminar -->
                                    <asp:LinkButton ID="btnEliminarRazon" runat="server" 
                                        CssClass="btn btn-outline-danger btn-sm"
                                        CommandName="Eliminar"
                                        CommandArgument='<%# Eval("razonDevolucionId") %>'
                                        OnCommand="btnEliminarRazon_Command"
                                        OnClientClick="return confirm('¿Está seguro de eliminar esta razón de devolución?');"
                                        ToolTip="Eliminar razón">
                                        <i class="bi bi-trash"></i>
                                    </asp:LinkButton>
                                </div>
                            </ItemTemplate>
                        </asp:Repeater>

                        <asp:Panel ID="pnlSinRazones" runat="server" Visible="false" CssClass="text-center py-5">
                            <i class="bi bi-inbox fs-1 text-muted"></i>
                            <p class="text-muted mt-2">No hay razones de devolución registradas</p>
                            <small class="text-muted">Agrega tu primera razón usando el formulario de la izquierda</small>
                        </asp:Panel>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>