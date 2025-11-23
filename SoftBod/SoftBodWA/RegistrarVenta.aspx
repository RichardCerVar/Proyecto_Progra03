<%@ Page Title="Registrar Venta" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="RegistrarVenta.aspx.cs" Inherits="SoftBodWA.RegistrarVenta" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Gestión de Ventas
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    
    <style>
        .product-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 12px 0;
            border-bottom: 1px solid #f0f0f0;
        }
        .product-info {
            flex-grow: 1;
        }
        .product-name {
            font-weight: 600;
            color: #2c3e50;
        }
        .product-stock {
            font-size: 0.85em;
            color: #7f8c8d;
        }
        .price-add-group {
            display: flex;
            align-items: center;
            gap: 15px;
        }
        .product-price {
            font-weight: 700;
            font-size: 1.1em;
            color: #34495e;
        }
        .quantity-input {
            width: 70px;
            padding: 6px;
            border: 1px solid #ddd;
            border-radius: 4px;
            text-align: center;
        }
        .btn-add {
            background-color: #2ecc71;
            color: white;
            border: none;
            padding: 8px 10px;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.2s;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .btn-add:hover {
            background-color: #27ae60;
        }
        .cart-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
            border-bottom: 1px dashed #f0f0f0;
        }
        .cart-item-info {
            flex-grow: 1;
        }
        .cart-total-footer {
            margin-top: 20px;
            padding-top: 15px;
            border-top: 2px solid #e0e0e0;
        }
        .total-display {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            padding: 12px 15px;
            background-color: #f8f9fa;
            border-radius: 8px;
        }
        .total-label {
            font-size: 1.1em;
            font-weight: 600;
            color: #495057;
        }
        .total-amount {
            font-size: 1.3em;
            font-weight: 700;
            color: #0d6efd;
        }
        .btn-register-venta {
            background-color: #6c757d;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 8px;
            width: 100%;
            font-size: 1.1em;
            font-weight: 600;
            transition: background-color 0.3s;
        }
    </style>

    <div class="d-flex justify-content-between align-items-center mb-4">
        <a href="Inicio.aspx" class="btn btn-outline-secondary d-flex align-items-center">
            <i class="bi bi-arrow-left me-2"></i>
            <span class="fw-semibold">Regresar</span>
        </a>
        <h4 class="fw-bold mb-0">Gestión de Ventas</h4>
        <div style="width: 200px;"></div>
    </div>

    <div class="row mb-4 align-items-center">
        <div class="col-md-7 mb-2">
           <div class="input-group">
                <span class="input-group-text bg-white border-end-0"><i class="bi bi-search"></i></span>
                <asp:TextBox ID="txtBuscarProducto" CssClass="form-control border-start-0" placeholder="Buscar productos por nombre..." runat="server" OnTextChanged="txtBuscarProducto_TextChanged" AutoPostBack="True"></asp:TextBox>
            </div>
        </div>
        <div class="col-md-5 mb-2">
            <asp:DropDownList ID="ddlCategoriaFiltro" CssClass="form-select" runat="server" OnSelectedIndexChanged="ddlCategoriaFiltro_SelectedIndexChanged" AutoPostBack="True">
            </asp:DropDownList>
        </div>
    </div>

    <div class="row">
        <div class="col-md-7">
            <div class="card p-4 shadow-sm h-100">
                <h5 class="fw-bold mb-3">Productos Disponibles</h5>
                
                <div style="max-height: 50vh; overflow-y: auto;">
                    <!-- Panel para cuando NO hay productos -->
                    <asp:Panel ID="pnlNoProductos" runat="server" Visible="false" CssClass="text-center text-muted mt-3">
                        <i class="bi bi-inbox fs-1 mb-2"></i>
                        <p>No se encontraron productos disponibles.</p>
                    </asp:Panel>

                    <!-- Repeater de productos -->
                    <asp:Repeater ID="rptProductosDisponibles" runat="server" OnItemDataBound="rptProductosDisponibles_ItemDataBound">
                        <ItemTemplate>
                            <div class="product-item">
                                <div class="product-info">
                                    <div class="product-name"><%# Eval("nombre") %></div>
                                    <div class="product-stock">Stock: <%# Eval("stock") %></div>
                                </div>
                                <div class="price-add-group">
                                    <div class="product-price">S/. <%# Eval("precioUnitario", "{0:N2}") %></div>
                                    <asp:TextBox ID="txtCantidad" runat="server" CssClass="quantity-input" Text="0" TextMode="Number"></asp:TextBox>
                                    <asp:HiddenField ID="hfProductoId" runat="server" Value='<%# Eval("productoId") %>' />
                                    <asp:HiddenField ID="hfStockDisponible" runat="server" Value='<%# Eval("stock") %>' />
                                    <asp:LinkButton ID="btnAdd" runat="server" CssClass="btn-add" CommandName="Agregar" OnClick="btnAdd_Click" ToolTip="Agregar al carrito">
                                        <i class="bi bi-cart-plus fs-5"></i>
                                    </asp:LinkButton>
                                </div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>
                </div>
            </div>
        </div>  

        <div class="col-md-5">
            <div class="card p-4 shadow-sm h-100">
                <h5 class="fw-bold mb-3"><i class="bi bi-cart-fill me-2"></i> Carrito de Compras</h5>
                
                <div style="min-height: 100px;">
                    <asp:Repeater ID="rptCarrito" runat="server">
                        <ItemTemplate>
                            <div class="cart-item">
                                <div class="cart-item-info">
                                    <%# Eval("producto.nombre") %>
                                    <span class="text-muted small"> (S/. <%# Eval("producto.precioUnitario", "{0:N2}") %> x <%# Eval("cantidad") %>)</span>
                                </div>
                                <div class="d-flex align-items-center">
                                    <div class="fw-bold me-3">S/. <%# Eval("subtotal", "{0:N2}") %></div>
                                    <asp:LinkButton ID="btnRemove" runat="server" 
                                        CssClass="btn btn-sm btn-outline-danger" 
                                        CommandName="Quitar" 
                                        CommandArgument='<%# Eval("producto.productoId") %>' 
                                        OnClick="btnRemove_Click"
                                        ToolTip="Eliminar del carrito">
                                        <i class="bi bi-trash"></i>
                                    </asp:LinkButton>
                                </div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>
                    <asp:Panel ID="pnlCarritoVacio" runat="server" Visible="true" CssClass="text-center text-muted mt-3">
                        <p>El carrito está vacío.</p>
                    </asp:Panel>
                </div>
                
                <div class="mt-4">
                    <h6 class="fw-bold">Tipo de Pago</h6>
                    <asp:DropDownList ID="ddlTipoPago" CssClass="form-select mb-3" runat="server" OnSelectedIndexChanged="ddlTipoPago_SelectedIndexChanged" AutoPostBack="True">
                        <asp:ListItem Text="Contado" Value="CONTADO" Selected="True"></asp:ListItem>
                        <asp:ListItem Text="Fiado" Value="FIADO"></asp:ListItem>
                    </asp:DropDownList>
                </div>

                <!-- Panel para Método de Pago (solo para CONTADO) -->
                <asp:Panel ID="pnlMetodoPago" runat="server" Visible="true" CssClass="mb-3">
                    <h6 class="fw-bold">Método de Pago</h6>
                    <asp:DropDownList ID="ddlMetodoPago" CssClass="form-select" runat="server">
                        <asp:ListItem Text="Efectivo" Value="EFECTIVO" Selected="True"></asp:ListItem>
                        <asp:ListItem Text="Transferencia" Value="TRANSFERENCIA"></asp:ListItem>
                    </asp:DropDownList>
                </asp:Panel>

                <!-- Panel para Cliente (solo para FIADO) -->
                <asp:Panel ID="pnlCliente" runat="server" Visible="false">
                    <h6 class="fw-bold">Cliente</h6>
                    <asp:DropDownList ID="ddlCliente" CssClass="form-select mb-4" runat="server">
                        <asp:ListItem Text="Seleccionar cliente" Value="0" Selected="True"></asp:ListItem>
                    </asp:DropDownList>
                </asp:Panel>

                <div class="cart-total-footer">
                    <div class="total-display">
                        <span class="total-label">Total a Pagar:</span>
                        <span class="total-amount">S/. <asp:Label ID="lblTotal" runat="server" Text="0.00"></asp:Label></span>
                    </div>
                    <asp:Button ID="btnRegistrarVenta" runat="server" Text="Registrar Venta" 
                        OnClick="btnRegistrarVenta_Click" CssClass="btn-register-venta" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>