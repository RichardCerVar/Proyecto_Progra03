<%@ Page Title="Reporte de Ventas" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="ReporteVentas.aspx.cs" Inherits="SoftBodWA.ReporteVentas" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <style>
        .summary-card {
            border: none;
            border-radius: 10px;
            padding: 15px;
        }

        .card-ventas { background-color: #e6ffe6; color: #1e7e34; }
        .card-devoluciones { background-color: #ffe6e6; color: #dc3545; }
        .card-netas { background-color: #e6f0ff; color: #007bff; }
        
        .summary-icon { font-size: 1.5rem; margin-right: 10px; }
        .summary-label { font-size: 0.9rem; color: #6c757d; }
        .summary-value { font-size: 1.6rem; font-weight: bold; }
        
        .type-badge { padding: 4px 8px; border-radius: 5px; font-size: 0.8rem; font-weight: 600; }
        .badge-venta { background-color: #ccffcc; color: #1e7e34; }
        .badge-devolucion { background-color: #ffcccc; color: #dc3545; }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-4">
        <h2 class="fw-bold mb-4">Reporte de Ventas</h2>

        <div class="card p-4 mb-4">
            <h5 class="mb-3"><i class="fa-solid fa-filter me-2"></i>Filtro de Reporte</h5>
            
            <div class="row g-3 align-items-end">
                <div class="col-md-3">
                    <label class="form-label">Tipo de Fecha</label>
                    <asp:DropDownList ID="ddlTipoFecha" runat="server" CssClass="form-select">
                        <asp:ListItem Text="Diario" Value="Diario" Selected="True"></asp:ListItem>
                        <%-- Si se implementa, se añadirían Semanal, Mensual, etc. --%>
                    </asp:DropDownList>
                </div>
                
                <div class="col-md-3">
                    <label class="form-label">Fecha</label>
                    <asp:TextBox ID="txtFecha" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                </div>

                <div class="col-md-6 text-end">
                    <asp:Button ID="btnFiltrar" runat="server" Text="Filtrar Reporte" OnClick="btnFiltrar_Click" CssClass="btn btn-primary me-2" />
                    <asp:Button ID="btnExportarReporte" runat="server" Text="Exportar Reporte" OnClick="btnExportarReporte_Click" CssClass="btn btn-warning" />
                    <a href="Reportes.aspx" class="btn btn-secondary">Volver</a>
                </div>
            </div>
        </div>

        <h3 class="fw-bold mb-3">Resumen del Periodo</h3>
        <div class="row g-3 mb-4">
            
            <div class="col-md-4">
                <div class="card summary-card card-ventas">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-dollar-sign summary-icon"></i>
                        <div>
                            <div class="summary-label">Ventas Totales</div>
                            <div class="summary-value">S/.<asp:Label ID="lblVentasTotales" runat="server" Text="0.00"></asp:Label></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card summary-card card-devoluciones">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-arrow-right-arrow-left summary-icon"></i>
                        <div>
                            <div class="summary-label">Devoluciones</div>
                            <div class="summary-value">S/.<asp:Label ID="lblDevoluciones" runat="server" Text="0.00"></asp:Label></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card summary-card card-netas">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-chart-line summary-icon"></i>
                        <div>
                            <div class="summary-label">Ventas Netas</div>
                            <div class="summary-value">S/.<asp:Label ID="lblVentasNetas" runat="server" Text="0.00"></asp:Label></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        
        <h3 class="fw-bold mb-3">Detalle de Ventas y Devoluciones</h3>
        <div class="card p-4">
            <asp:GridView ID="gvDetalleMovimientos" runat="server" 
                AutoGenerateColumns="false" 
                CssClass="table table-hover table-striped"
                EmptyDataText="No se encontraron movimientos para la fecha seleccionada."
                GridLines="None">
                
                <Columns>
                    <asp:BoundField DataField="ID" HeaderText="ID" SortExpression="ID" />
                    <asp:BoundField DataField="Fecha" HeaderText="Fecha" DataFormatString="{0:dd/MM/yyyy}" />
                    <asp:BoundField DataField="Hora" HeaderText="Hora" />
                    
                    <asp:TemplateField HeaderText="Tipo">
                        <ItemTemplate>
                            <span class='type-badge <%# Eval("Tipo").ToString().Contains("Venta") ? "badge-venta" : "badge-devolucion" %>'>
                                <%# Eval("Tipo") %>
                            </span>
                        </ItemTemplate>
                    </asp:TemplateField>

                    <asp:BoundField DataField="Cliente" HeaderText="Cliente" />
                    <asp:BoundField DataField="TipoPago" HeaderText="Tipo Pago" />
                    
                    <asp:TemplateField HeaderText="Total">
                        <ItemTemplate>
                            <asp:Label ID="lblTotal" runat="server" 
                                Text='<%# Eval("Total", "{0:N2}") %>' 
                                CssClass='<%# Eval("Tipo").ToString().Contains("Venta") ? "text-success fw-bold" : "text-danger fw-bold" %>' />
                        </ItemTemplate>
                        <ItemStyle HorizontalAlign="Right" /> </asp:TemplateField>
                    <asp:TemplateField HeaderText="Productos">
                        <ItemTemplate>
                            <%# Eval("ProductosResumen") %>
                            <asp:Label ID="lblEsFiado" runat="server" 
                                Text='<%# Eval("EsFiado").Equals(true) ? "<span class=\"badge bg-warning text-dark ms-2\">FIADO</span>" : "" %>' 
                                Visible='<%# Eval("EsFiado") %>'>
                            </asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>