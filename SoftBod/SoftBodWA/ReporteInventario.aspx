<%@ Page Title="Reporte de Inventario" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="ReporteInventario.aspx.cs" Inherits="SoftBodWA.ReporteInventario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    </asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="fw-bold">Reportes</h3>
        <div class="d-flex gap-2">
            <asp:LinkButton ID="btnExportarReporte" runat="server" CssClass="btn btn-warning fw-bold shadow-sm d-flex align-items-center">
                <i class="fa-solid fa-file-export me-2"></i> Exportar Reporte
            </asp:LinkButton>
            <asp:LinkButton ID="btnVolver" runat="server" CssClass="btn btn-outline-secondary d-flex align-items-center">
                <i class="fa-solid fa-arrow-left me-2"></i> Volver
            </asp:LinkButton>
        </div>
    </div>

    <div class="card shadow-sm border-0 mb-5">
        <div class="card-body">
            <h5 class="fw-bold mb-3">Reporte de Inventario</h5>
            <div class="row">
                <div class="col-md-4">
                    <label class="form-label fw-semibold">Fecha del Inventario</label>
                    <div class="input-group">
                        <asp:TextBox ID="txtFechaInventario" runat="server" CssClass="form-control" TextMode="Date" placeholder="mm/dd/yyyy"></asp:TextBox>
                        <span class="input-group-text"><i class="fa-solid fa-calendar-alt"></i></span>
                    </div>
                </div>
                <div class="col-md-8 d-flex align-items-end">
                    <%-- <asp:Button ID="btnGenerarReporte" runat="server" CssClass="btn btn-primary" Text="Generar Reporte" /> --%>
                </div>
            </div>
        </div>
    </div>

    <h4 class="fw-bold mb-3">Estado del Inventario</h4>
    <div class="card shadow-sm border-0 mb-5">
        <div class="card-body p-0">
            <asp:Repeater ID="rptEstadoInventario" runat="server">
                <HeaderTemplate>
                    <table class="table table-striped table-hover mb-0">
                        <thead class="bg-light">
                            <tr>
                                <th scope="col">Producto</th>
                                <th scope="col">Categoría</th>
                                <th scope="col" class="text-end">Stock Actual</th>
                                <th scope="col" class="text-end">Stock Mínimo</th>
                                <th scope="col" class="text-end">Valor Total</th>
                                <th scope="col" class="text-center">Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                </HeaderTemplate>
                <ItemTemplate>
                    <tr>
                        <td><%# Eval("ProductoNombre") %></td>
                        <td><%# Eval("CategoriaDescripcion") %></td>
                        <td class="text-end"><%# Eval("StockActual") %></td>
                        <td class="text-end"><%# Eval("StockMinimo") %></td>
                        <td class="text-end">S/. <%# String.Format("{0:N2}", Eval("ValorTotal")) %></td>
                        <td class="text-center">
                            <span class="badge 
                                <%# (int)Eval("StockActual") > (int)Eval("StockMinimo") ? "bg-success-subtle text-success" : "bg-danger-subtle text-danger" %>">
                                <%# (int)Eval("StockActual") > (int)Eval("StockMinimo") ? "Stock Bien" : "Stock Bajo" %>
                            </span>
                        </td>
                    </tr>
                </ItemTemplate>
                <FooterTemplate>
                    </tbody>
                    </table>
                </FooterTemplate>
            </asp:Repeater>
        </div>
    </div>

    <h4 class="fw-bold mb-3">Resumen del Inventario</h4>
    <div class="row">
        <div class="col-md-6 mb-3">
            <div class="card shadow-sm border-0">
                <div class="card-body d-flex align-items-center bg-light-purple rounded-3">
                    <i class="fa-solid fa-box-open fa-2x text-purple me-3"></i>
                    <div>
                        <p class="mb-0 text-muted">Valor Total Inventario</p>
                        <h4 class="fw-bold text-dark">
                            <asp:Label ID="lblValorTotalInventario" runat="server" Text="S/. 2,347.00"></asp:Label>
                        </h4>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6 mb-3">
            <div class="card shadow-sm border-0">
                <div class="card-body d-flex align-items-center bg-light-orange rounded-3">
                    <i class="fa-solid fa-triangle-exclamation fa-2x text-warning me-3"></i>
                    <div>
                        <p class="mb-0 text-muted">Productos con Stock Bajo</p>
                        <h4 class="fw-bold text-dark">
                            <asp:Label ID="lblProductosStockBajo" runat="server" Text="6"></asp:Label>
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>

</asp:Content>