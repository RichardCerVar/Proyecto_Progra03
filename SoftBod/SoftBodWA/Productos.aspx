<%@ Page Title="Gestión de Productos" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Productos.aspx.cs" Inherits="SoftBodWA.Productos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Scripts/SoftBodScripts/AgregarNuevoProducto.js"></script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <!-- Encabezado principal -->
    <div class="container mt-4 mb-5">
        <h3 class="fw-bold mb-4">Gestión de Productos</h3>

        <!-- Tarjetas resumen -->
        <div class="row mb-4">
            <div class="col-md-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body d-flex align-items-center">
                        <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                        <div>
                            <p class="mb-0 text-muted">Productos Activos</p>
                            <h4 class="fw-bold">9</h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body d-flex align-items-center">
                        <i class="fa-solid fa-triangle-exclamation fa-2x text-warning me-3"></i>
                        <div>
                            <p class="mb-0 text-muted">Stock Bajo</p>
                            <h4 class="fw-bold text-warning">1</h4>
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
                            <h4 class="fw-bold text-success">S/. 587.85</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Buscador -->
        <div class="row mb-4 align-items-center">
            <div class="col-md-6 mb-2">
               <div class="input-group">
                    <asp:TextBox ID="txtBuscarProducto" CssClass="form-control" placeholder="Buscar productos por nombre..." runat="server"></asp:TextBox>
                    <asp:Button ID="btnBuscarNombreProducto" CssClass="btn btn-primary" runat="server" Text="Buscar" OnClick="btnBuscarNombreProducto_Click"/>
                </div>
            </div>
            <div class="col-md-3 mb-2">
                <asp:DropDownList ID="ddlCategoriaFiltro" CssClass="form-select" runat="server">
                    <asp:ListItem Text="Todas las categorías" Value="0" Selected="True"></asp:ListItem>
                    <asp:ListItem Text="Granos" Value="1"></asp:ListItem>
                    <asp:ListItem Text="Lácteos" Value="2"></asp:ListItem>
                    <asp:ListItem Text="Aceites" Value="3"></asp:ListItem>
                </asp:DropDownList>
            </div>
            <div class="col-md-3 text-end">

                <asp:Button ID="btnAgregar" runat="server" Text="+ Agregar Producto" CssClass="btn btn-primary fw-bold shadow" OnClick="btnAgregarProducto_Click" />
                
            </div>
        </div>

        <!-- Lista de productos -->
        <div class="card mb-3 shadow-sm border-0">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                    <div>
                        <strong>Arroz Diana 500g</strong><br>
                        <small class="text-muted">Granos — Min: 10 unidades</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 2.50</p>
                    <small class="text-muted">Stock: 45</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" checked>
                        <label class="form-check-label small text-muted">Activo</label>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-3 shadow-sm border-0">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                    <div>
                        <strong>Aceite Girasol 1L</strong><br>
                        <small class="text-muted">Aceites — Min: 15 unidades</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold text-warning"><i class="fa-solid fa-triangle-exclamation me-1"></i>Stock: 12</p>
                    <p class="mb-0">S/. 3.80</p>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" checked>
                        <label class="form-check-label small text-muted">Activo</label>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-3 shadow-sm border-0">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                    <div>
                        <strong>Leche Entera 1L</strong><br>
                        <small class="text-muted">Lácteos — Min: 10 unidades</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 1.85</p>
                    <small class="text-muted">Stock: 25</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" checked>
                        <label class="form-check-label small text-muted">Activo</label>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- Modal Agregar Producto -->
    <asp:ScriptManager runat="server"></asp:ScriptManager>

    <div class="modal fade" id="modalAgregarProducto" tabindex="-1" aria-labelledby="modalAgregarProductoLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header border-0">
                    <h5 class="modal-title fw-bold" id="modalAgregarProductoLabel">Agregar Nuevo Producto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <!-- Nombre del Producto -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Nombre del Producto"></asp:Label>
                                <asp:TextBox ID="txtNombreProducto" CssClass="form-control" placeholder="Ej: Arroz Diana 500g" runat="server"></asp:TextBox>
                            </div>

                            <!-- Categoría -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Categoría"></asp:Label>
                                <asp:DropDownList ID="ddlCategoria" CssClass="form-select" runat="server">
                                    <asp:ListItem Text="Seleccionar categoría" Value="0" Selected="True"></asp:ListItem>
                                    <asp:ListItem Text="Granos" Value="1"></asp:ListItem>
                                    <asp:ListItem Text="Lácteos" Value="2"></asp:ListItem>
                                    <asp:ListItem Text="Aceites" Value="3"></asp:ListItem>
                                </asp:DropDownList>
                            </div>

                            <!-- O crear nueva categoría -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="O crear nueva categoría"></asp:Label>
                                <asp:TextBox ID="txtNuevaCategoria" CssClass="form-control" placeholder="Nueva categoría" runat="server"></asp:TextBox>
                            </div>

                            <!-- Precio y Stock Inicial -->
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Precio"></asp:Label>
                                    <asp:TextBox ID="txtPrecio" CssClass="form-control" placeholder="0.00" runat="server"></asp:TextBox>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Stock Inicial"></asp:Label>
                                    <asp:TextBox ID="txtStockInicial" CssClass="form-control" placeholder="0" runat="server"></asp:TextBox>
                                </div>
                            </div>

                            <!-- Stock Mínimo -->
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Stock Mínimo"></asp:Label>
                                <asp:TextBox ID="txtStockMinimo" CssClass="form-control" placeholder="5" runat="server"></asp:TextBox>
                            </div>

                            <!-- Botón Agregar -->
                            <asp:LinkButton ID="btnGuardarProducto" runat="server" CssClass="btn btn-dark w-100 py-2 fw-semibold" Text="Agregar Producto" OnClick="btnGuardarProducto_Click" />
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>

</asp:Content>