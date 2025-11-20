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
        .btn-add {
            background-color: #2ecc71;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 6px;
            font-weight: 600;
            cursor: pointer;
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
            border-top: 1px solid #e0e0e0;
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
        <a href="Inicio.aspx" class="btn btn-outline-secondary d-flex align-items-center me-3">
            <i class="bi bi-arrow-left me-2"></i>
            <span class="fw-semibold">Regresar</span>
        </a>
        <h4 class="fw-bold mb-0">Gestión de Ventas</h4>
        <div class="text-white rounded px-4 py-2 fw-semibold" style="background-color: #0d6efd;">
            Total: S/. <asp:Label ID="lblTotal" runat="server" Text="0.00"></asp:Label>
        </div>
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
                    <asp:Repeater ID="rptProductosDisponibles" runat="server">
                        <ItemTemplate>
                            <div class="product-item">
                                <div class="product-info">
                                    <div class="product-name"><%# Eval("nombre") %></div>
                                    <div class="product-stock">Stock: <%# Eval("stock") %></div>
                                </div>
                                <div class="price-add-group">
                                    <div class="product-price">S/. <%# Eval("precioUnitario", "{0:N2}") %></div>
                                    <asp:LinkButton ID="btnAdd" runat="server" CssClass="btn-add" CommandName="Agregar" CommandArgument='<%# Eval("productoId") %>' OnClick="btnAdd_Click">
                                        <i class="bi bi-plus"></i>
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
                                        CssClass="btn btn-sm btn-outline-secondary border-0" 
                                        CommandName="Quitar" 
                                        CommandArgument='<%# Eval("producto.productoId") %>' 
                                        OnClick="btnRemove_Click">
                                        <i class="bi bi-dash"></i>
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

                <asp:Panel ID="pnlCliente" runat="server" Visible="false">
                    <h6 class="fw-bold">Cliente</h6>
                    <asp:DropDownList ID="ddlCliente" CssClass="form-select mb-4" runat="server">
                        <asp:ListItem Text="Seleccionar cliente" Value="0" Selected="True"></asp:ListItem>
                    </asp:DropDownList>
                </asp:Panel>

                <div class="cart-total-footer">
                    <asp:Button ID="btnRegistrarVenta" runat="server" Text="Registrar Venta" 
                        OnClick="btnRegistrarVenta_Click" CssClass="btn-register-venta" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>