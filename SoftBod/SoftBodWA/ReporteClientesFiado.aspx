<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ReporteClientesFiado.aspx.cs" Inherits="SoftBodWA.ReporteClientesFiado" MasterPageFile="~/SoftBod.master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Reporte Clientes al Fiado
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    
    <style>
        .report-title { 
            font-size: 24px; 
            font-weight: 700; 
            margin-bottom: 20px; 
            color: #34495e; 
        }
        
        /* Contenedor principal para filtros */
        .header-section { 
            background-color: #fff; 
            padding: 20px; 
            border-radius: 8px; 
            box-shadow: 0 2px 4px rgba(0,0,0,0.05); 
            margin-bottom: 20px; 
            border: 1px solid #e0e0e0; /* Borde añadido */
        }
        
        .filter-group { 
            display: flex; 
            gap: 20px; 
            align-items: flex-end; 
        }
        
        /* Contenedores para información del cliente y tablas */
        .content-card { 
            background-color: #fff; 
            padding: 20px; 
            border-radius: 8px; 
            box-shadow: 0 4px 8px rgba(0,0,0,0.05); /* Sombra para el efecto "flotante" */
            border: 1px solid #e0e0e0; /* <-- Borde sutil para definir la caja */
            margin-bottom: 20px;
        }

        .client-info-grid { 
            display: grid; 
            grid-template-columns: 1fr 1fr; 
            gap: 40px; 
        }
        .summary-label { font-weight: 500; color: #7f8c8d; }
        .summary-value { font-weight: 700; color: #2c3e50; }
        .text-red { color: #e74c3c; } 
        .text-green { color: #27ae60; } 

        /* Tablas */
        .data-table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        .data-table th { background-color: #f8fafc; padding: 10px; text-align: left; font-weight: 600; color: #34495e; border-bottom: 2px solid #e0e0e0; }
        .data-table td { padding: 10px 0; border-bottom: 1px solid #f0f0f0; vertical-align: top; }
        .products-list { list-style: none; padding: 0; margin: 0; }
        .products-list li { display: flex; justify-content: space-between; padding: 2px 0; font-size: 0.9em; }
        .products-list-item-price { font-weight: 600; }
        
        /* Botones de acción superior */
        .action-buttons { 
            display: flex; 
            justify-content: flex-end; 
            gap: 10px; 
            margin-bottom: 15px;
        }
    </style>

    <div class="action-buttons">
        <asp:Button ID="btnExportarReporte" runat="server" Text="Exportar Reporte" CssClass="btn btn-primary" />
        <asp:Button ID="btnVolver" runat="server" Text="Volver" CssClass="btn btn-secondary" />
    </div>

    <div class="report-title">Reporte de Clientes al Fiado</div>
            
    <div class="header-section">
        <div class="filter-group">
            <div>
                <asp:Label ID="Label1" runat="server" Text="Seleccionar Cliente"></asp:Label><br />
                <asp:DropDownList ID="ddlCliente" runat="server" DataTextField="Alias" DataValueField="ClienteId" ClientIDMode="Static" CssClass="form-control" Style="width: 250px;"></asp:DropDownList>
            </div>
            <div>
                <asp:Label ID="Label2" runat="server" Text="Tipo de Fecha"></asp:Label><br />
                <asp:DropDownList ID="ddlTipoFecha" runat="server" ClientIDMode="Static" CssClass="form-control" Style="width: 150px;">
                    <asp:ListItem Text="Diario" Value="Diario" Selected="True" />
                    <asp:ListItem Text="Rango" Value="Rango" />
                </asp:DropDownList>
            </div>
            <div>
                <asp:Label ID="Label3" runat="server" Text="Fecha"></asp:Label><br />
                <asp:TextBox ID="txtFecha" runat="server" TextMode="Date" ClientIDMode="Static" CssClass="form-control" Style="width: 150px;"></asp:TextBox>
            </div>
             <div>
                <asp:Button ID="btnBuscar" runat="server" Text="Buscar" OnClick="btnBuscar_Click" CssClass="btn btn-primary" />
            </div>
        </div>
    </div>

    <div class="content-card">
        <h2>Información del Cliente - <asp:Label ID="lblAlias" runat="server" CssClass="summary-value"></asp:Label></h2>
        <div class="client-info-grid">
            
            <div>
                <h3>Datos Personales</h3>
                <p><span class="summary-label">Alias:</span> <asp:Label ID="lblDatosAlias" runat="server"></asp:Label></p>
                <p><span class="summary-label">Nombre:</span> <asp:Label ID="lblDatosNombre" runat="server"></asp:Label></p>
                <p><span class="summary-label">Teléfono:</span> <asp:Label ID="lblDatosTelefono" runat="server"></asp:Label></p>
            </div>
            
            <div>
                <h3>Resumen de Cuenta</h3>
                <p><span class="summary-label">Deuda Total:</span> <asp:Label ID="lblDeudaTotal" runat="server" CssClass="summary-value text-red"></asp:Label></p>
                <p><span class="summary-label">Última Compra:</span> <asp:Label ID="lblUltimaCompra" runat="server"></asp:Label></p>
                <p><span class="summary-label">Compras en Periodo:</span> <asp:Label ID="lblComprasPeriodo" runat="server"></asp:Label></p>
                <p><span class="summary-label">Pagos en Periodo:</span> <asp:Label ID="lblPagosPeriodo" runat="server"></asp:Label></p>
                <p><span class="summary-label">Total Pagos:</span> <asp:Label ID="lblTotalPagos" runat="server" CssClass="summary-value"></asp:Label></p>
            </div>
        </div>
    </div>

    <div class="content-card">
        <h2>Historial de Compras</h2>
        <asp:Repeater ID="rptHistorialCompras" runat="server" OnItemDataBound="rptHistorialCompras_ItemDataBound">
            <HeaderTemplate>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th style="width: 15%;">Fecha</th>
                            <th style="width: 10%;">Hora</th>
                            <th style="width: 15%;">Total</th>
                            <th style="width: 60%;">Productos</th>
                        </tr>
                    </thead>
                    <tbody>
            </HeaderTemplate>
            <ItemTemplate>
                <tr>
                    <td><%# Eval("Venta.Fecha", "{0:dd/MM/yyyy}") %></td>
                    <td><%# Eval("Venta.Fecha", "{0:HH:mm}") %></td>
                    <td class="summary-value">S/. <%# String.Format("{0:N2}", Eval("Venta.Total")) %></td>
                    <td>
                        <asp:Repeater ID="rptDetalleProductos" runat="server">
                            <ItemTemplate>
                                <div class="products-list">
                                    <li>
                                        <span class="products-list-item-name">
                                            <%# Eval("Producto.Nombre") %> (x<%# Eval("Cantidad") %>)
                                        </span>
                                        <span class="products-list-item-price">
                                            S/. <%# String.Format("{0:N2}", Eval("Subtotal")) %>
                                        </span>
                                    </li>
                                </div>
                            </ItemTemplate>
                        </asp:Repeater>
                    </td>
                </tr>
            </ItemTemplate>
            <FooterTemplate>
                    </tbody>
                </table>
            </FooterTemplate>
        </asp:Repeater>
    </div>

    <div class="content-card">
        <h2>Historial de Pagos</h2>
        <asp:Repeater ID="rptHistorialPagos" runat="server">
            <HeaderTemplate>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th style="width: 20%;">Fecha</th>
                            <th style="width: 20%;">Monto</th>
                            <th style="width: 60%;">Descripción</th>
                        </tr>
                    </thead>
                    <tbody>
            </HeaderTemplate>
            <ItemTemplate>
                <tr>
                    <td><%# Eval("Fecha") %></td>
                    <td class="summary-value text-green">S/. <%# String.Format("{0:N2}", Eval("Monto")) %></td>
                    <td><%# Eval("Descripcion") %></td> 
                </tr>
            </ItemTemplate>
            <FooterTemplate>
                    </tbody>
                </table>
            </FooterTemplate>
        </asp:Repeater>
    </div>

</asp:Content>