<%@ Page Title="Clientes" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Clientes.aspx.cs" Inherits="SoftBodWA.Clientes" %>

<asp:Content ID="MainContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid px-3 py-3">
        <h4 class="fw-bold mb-4">Gestión de Clientes al Fiado</h4>

        <!-- BUSCADOR -->
        <div class="input-group mb-4">
            <span class="input-group-text bg-white border-end-0">
                <i class="bi bi-search text-muted"></i>
            </span>
            <asp:TextBox ID="txtBuscar" runat="server" CssClass="form-control border-start-0" placeholder="Buscar cliente por alias..." />
        </div>

        <!-- RESUMEN DEUDA TOTAL -->
        <div class="card border-0 mb-4" style="background: linear-gradient(to right, #fff6f1, #fff9f4);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <i class="bi bi-credit-card fs-3 text-warning"></i>
                    <span class="d-block text-muted small">Total Cuentas Fiadas</span>
                    <h5 class="fw-bold mb-0 text-danger" id="lblTotalDeuda" runat="server">S/0.00</h5>
                </div>
                <div class="text-end">
                    <span class="small text-muted">Clientes Activos</span>
                    <h4 class="fw-bold mb-0" id="lblActivos" runat="server">0</h4>
                </div>
            </div>
        </div>

        <!-- LISTADO DE CLIENTES -->
        <asp:Repeater ID="rptClientes" runat="server">
            <ItemTemplate>
                <div class="card border-0 shadow-sm mb-3">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex align-items-center">
                                <i class="bi bi-person-circle fs-3 text-primary me-2"></i>
                                <div>
                                    <h6 class="mb-0 fw-bold"><%# Eval("Alias") %></h6>
                                    <small class="text-muted d-block"><%# Eval("Nombre") %></small>
                                    <small class="text-muted"><i class="bi bi-telephone"></i> <%# Eval("Telefono") %></small>
                                </div>
                            </div>
                            <div class="text-end">
                                <span class="text-danger fw-bold">Deuda: <%# Eval("Deuda", "S/{0:F2}") %></span><br />
                                <small class="text-muted"><i class="bi bi-calendar"></i> Fecha Límite: <%# Eval("FechaLimite", "{0:dd/MM/yyyy}") %></small>
                            </div>
                        </div>

                        <div class="mt-3 d-flex gap-2 justify-content-end">
                            <asp:Button ID="btnPagar" runat="server" Text="$ Pagar" CssClass="btn btn-success btn-sm" CommandArgument='<%# Eval("Alias") %>' CommandName="Pagar" />
                            <asp:Button ID="btnEditar" runat="server" Text="✎" CssClass="btn btn-outline-secondary btn-sm" CommandArgument='<%# Eval("Alias") %>' CommandName="Editar" />
                            <asp:Button ID="btnEliminar" runat="server" Text="🗑" CssClass="btn btn-outline-danger btn-sm" CommandArgument='<%# Eval("Alias") %>' CommandName="Eliminar" />
                        </div>
                    </div>
                </div>
            </ItemTemplate>
        </asp:Repeater>
    </div>

    <!-- BOTÓN FIJO PARA AGREGAR CLIENTE -->
    <div class="position-fixed" style="top: 90px; right: 25px; z-index: 1050;">
        <asp:Button ID="btnAgregar" runat="server" Text="+ Agregar Cliente" CssClass="btn btn-primary fw-bold shadow" />
    </div>
</asp:Content>