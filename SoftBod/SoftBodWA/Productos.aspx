<%@ Page Title="Gestión de Productos" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Productos.aspx.cs" Inherits="SoftBodWA.Productos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

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
                <input type="text" class="form-control" placeholder="Buscar productos por nombre...">
            </div>
            <div class="col-md-3 mb-2">
                <select class="form-select">
                    <option>Todas las categorías</option>
                    <option>Granos</option>
                    <option>Lácteos</option>
                    <option>Aceites</option>
                </select>
            </div>
            <div class="col-md-3 text-end">
                <button class="btn btn-primary">
                    <i class="fa-solid fa-plus me-2"></i>Agregar Producto
                </button>
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

</asp:Content>