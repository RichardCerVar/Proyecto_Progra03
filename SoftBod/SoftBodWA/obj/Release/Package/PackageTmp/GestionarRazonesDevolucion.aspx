<%@ Page Title="Gestionar Razones de Devolución" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="GestionarRazonesDevolucion.aspx.cs" Inherits="SoftBodWA.GestionarRazonesDevolucion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid px-3 py-3">
        <!-- Header -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h5 class="fw-bold mb-0">Razones de Devolución</h5>
            <asp:LinkButton ID="btnVolver" runat="server" 
                CssClass="btn btn-outline-secondary btn-sm"
                OnClick="btnVolver_Click">
                <i class="bi bi-arrow-left me-1"></i>Volver
            </asp:LinkButton>
        </div>

        <div class="row">
            <!-- Formulario -->
            <div class="col-md-4 mb-3">
                <div class="card border-0 shadow-sm rounded-3">
                    <div class="card-body">
                        <h6 class="fw-bold mb-3"><i class="bi bi-plus-circle me-2"></i>Nueva Razón</h6>
                        
                        <div class="mb-3">
                            <label class="form-label fw-semibold">Descripción</label>
                            <asp:TextBox ID="txtDescripcionRazon" runat="server" 
                                CssClass="form-control" 
                                MaxLength="200"
                                placeholder="Ej: Producto defectuoso"
                                TextMode="MultiLine"
                                Rows="2">
                            </asp:TextBox>
                            <small class="text-muted">Máx. 200 caracteres</small>
                        </div>
                        
                        <asp:LinkButton ID="btnAgregarRazon" runat="server" 
                            CssClass="btn btn-primary w-100 fw-semibold"
                            OnClick="btnAgregarRazon_Click">
                            <i class="bi bi-check-circle me-1"></i>Agregar
                        </asp:LinkButton>
                    </div>
                </div>
            </div>

            <!-- Lista -->
            <div class="col-md-8">
                <div class="card border-0 shadow-sm rounded-3">
                    <div class="card-body">
                        <h6 class="fw-bold mb-3"><i class="bi bi-list-ul me-2"></i>Razones Registradas</h6>
                        
                        <asp:Repeater ID="rptRazones" runat="server">
                            <ItemTemplate>
                                <div class="d-flex justify-content-between align-items-center border-bottom py-2 mb-2">
                                    <div class="flex-grow-1">
                                        <span class="d-block fw-semibold"><%# Eval("descripcion") %></span>
                                    </div>
                                    
                                    <asp:LinkButton ID="btnEliminarRazon" runat="server" 
                                        CssClass="btn btn-outline-danger btn-sm d-flex align-items-center justify-content-center"
                                        Style="width: 40px; height: 40px;"
                                        CommandName="Eliminar"
                                        CommandArgument='<%# Eval("razonDevolucionId") %>'
                                        OnCommand="btnEliminarRazon_Command"
                                        ToolTip="Eliminar">
                                        <i class="bi bi-trash"></i>
                                    </asp:LinkButton>
                                </div>
                            </ItemTemplate>
                        </asp:Repeater>

                        <asp:Panel ID="pnlSinRazones" runat="server" Visible="false" CssClass="text-center py-4">
                            <i class="bi bi-inbox fs-2 text-muted mb-2"></i>
                            <p class="text-muted mb-0">No hay razones registradas</p>
                            <small class="text-muted">Agrega tu primera razón</small>
                        </asp:Panel>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>