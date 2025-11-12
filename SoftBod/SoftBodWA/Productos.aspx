<%@ Page Title="Gestión de Productos" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Productos.aspx.cs" Inherits="SoftBodWA.Productos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Scripts/SoftBodScripts/AgregarNuevoProducto.js"></script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

        <h3 class="fw-bold mb-4">Gestión de Productos</h3>

        <div class="row mb-4">
            <div class="col-md-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body d-flex align-items-center">
                        <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                        <div>
                            <p class="mb-0 text-muted">Productos Activos</p>
                            <h4 class="fw-bold">34</h4>
                        </div>
                    </div>
                </div>
            &nbsp;
                <br />
            </div>
            <div class="col-md-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body d-flex align-items-center">
                        <i class="fa-solid fa-triangle-exclamation fa-2x text-warning me-3"></i>
                        <div>
                            <p class="mb-0 text-muted">Stock Bajo</p>
                            <h4 class="fw-bold text-warning">6</h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body d-flex align-items-center">
                        <i class="fa-solid fa-sack-dollar fa-2x text-success me-3"></i>
                        <div>
                            <p class="mb-0 text-muted">Valor Inventario</p>
                            <h4 class="fw-bold text-success">S/. 2,347.00</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-4 align-items-center">
    <!-- 🔍 Buscador -->
    <div class="col-md-6 mb-2">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Buscar productos por nombre..." id="txtBuscarProductoHTML" />
            <button type="button" class="btn btn-primary">Buscar</button>
        </div>
    </div>

    <!-- 🧾 Filtro de categoría -->
    <div class="col-md-3 mb-2">
        <asp:DropDownList ID="ddlCategoriaFiltro" runat="server" CssClass="form-select form-select-sm">
        </asp:DropDownList>
    </div>

    <!-- ➕ Botón agregar producto -->
    <div class="col-md-3 mb-2 text-end">
        <button type="button" class="btn btn-primary fw-bold shadow"
                data-bs-toggle="modal"
                data-bs-target="#modalAgregarProducto">
            + Agregar Producto
        </button>
    </div>
</div>

    <asp:Repeater ID="rptProducto" runat="server">
    <ItemTemplate>
        <div class="card mb-3 shadow-sm border-0 <%# (bool)Eval("activo") ? "" : "opacity-50" %>">
            <div class="card-body d-flex justify-content-between align-items-center">

                <!-- Columna izquierda: icono + nombre + categoría -->
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                    <div>
                        <strong><%# Eval("nombre") %></strong><br />
                        <small class="text-muted">
                            <%# Eval("Categoria.descripcion") %> — Min: <%# Eval("stockMinimo") %> UNIDAD
                        </small>
                    </div>
                </div>

                <!-- Columna derecha: stock, precio y switch de activo -->
                <div class="text-end">
                    <p class="mb-0 fw-bold text-warning">
                        <i class="fa-solid fa-triangle-exclamation me-1"></i>
                        Stock: <%# Eval("stock") %>
                    </p>
                    <p class="mb-0">S/. <%# String.Format("{0:N2}", Eval("precioUnitario")) %></p>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox"
                               <%# (bool)Eval("activo") ? "checked" : "" %>
                               id='chk<%# Eval("productoId") %>' disabled />
                        <label class="form-check-label small text-muted">
                            <%# (bool)Eval("activo") ? "activo" : "inactivo" %>
                        </label>
                    </div>
                </div>

            </div>
        </div>
    </ItemTemplate>
    </asp:Repeater>

    <asp:ScriptManager runat="server"></asp:ScriptManager>

<div class="modal fade" id="modalAgregarProducto" tabindex="-1" aria-labelledby="modalAgregarProductoLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-0 shadow-lg rounded-4">

            <!-- Encabezado -->
            <div class="modal-header border-0 pb-0">
                <h5 class="modal-title fw-bold fs-5" id="modalAgregarProductoLabel">Agregar Nuevo Producto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <!-- Cuerpo -->
            <div class="modal-body pt-2">
                <asp:UpdatePanel runat="server">
                    <ContentTemplate>

                        <!-- Nombre del Producto -->
                        <div class="mb-3">
                            <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Nombre del Producto" AssociatedControlID="txtNombreProducto"></asp:Label>
                            <asp:TextBox ID="txtNombreProducto" CssClass="form-control" placeholder="Ej: Arroz Diana 500g" runat="server"></asp:TextBox>
                        </div>

                        <!-- Categoría -->
                        <div class="mb-3">
                            <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Categoría" AssociatedControlID="ddlCategoria"></asp:Label>
                            <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-select form-select-sm">
                            </asp:DropDownList>
                        </div>

                        <!-- Crear nueva categoría -->
                        <div class="mb-3">
                            <asp:Label CssClass="form-label fw-semibold" runat="server" Text="O crear nueva categoría" AssociatedControlID="txtNuevaCategoria"></asp:Label>
                            <asp:TextBox ID="txtNuevaCategoria" CssClass="form-control" placeholder="Nueva categoría" runat="server"></asp:TextBox>
                        </div>

                        <!-- Precio y Stock Inicial -->
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Precio" AssociatedControlID="txtPrecio"></asp:Label>
                                <asp:TextBox ID="txtPrecio" CssClass="form-control" placeholder="0.00" runat="server"></asp:TextBox>
                            </div>
                            <div class="col-md-6 mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Stock Inicial" AssociatedControlID="txtStockInicial"></asp:Label>
                                <asp:TextBox ID="txtStockInicial" CssClass="form-control" placeholder="0" runat="server"></asp:TextBox>
                            </div>
                        </div>

                        <!-- Stock Mínimo -->
                        <div class="mb-3">
                            <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Stock Mínimo" AssociatedControlID="txtStockMinimo"></asp:Label>
                            <asp:TextBox ID="txtStockMinimo" CssClass="form-control" placeholder="5" runat="server"></asp:TextBox>
                        </div>

                        <!-- Botón Agregar Producto -->
                        <asp:LinkButton ID="btnAgregarProducto" runat="server"
                            CssClass="btn btn-dark w-100 py-2 fw-semibold rounded-3"
                            Text="Agregar Producto"
                            OnClick="btnAgregar_Click" />

                    </ContentTemplate>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
</div>

</asp:Content>