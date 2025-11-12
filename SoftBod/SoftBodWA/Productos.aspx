<%@ Page Title="Gestión de Productos" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" Inherits="SoftBodWA.Productos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Scripts/SoftBodScripts/AgregarNuevoProducto.js"></script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <div class="container mt-4 mb-5">
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
            <div class="col-md-6 mb-2">
               <div class="input-group">
                    <input type="text" class="form-control" placeholder="Buscar productos por nombre..." id="txtBuscarProductoHTML" />
                    <button type="button" class="btn btn-primary">Buscar</button>
                </div>
            </div>
            <div class="col-md-3 mb-2">
                <select class="form-select" id="ddlCategoriaFiltroHTML">
                    <option value="0" selected>Todas las categorías</option>
                    <option value="1">Bebidas</option>
                    <option value="2">Snacks</option>
                    <option value="3">Lácteos</option>
                    <option value="4">Panadería</option>
                    <option value="5">Limpieza</option>
                    <option value="6">Higiene Personal</option>
                    <option value="7">Conservas</option>
                    <option value="8">Frutas y Verduras</option>
                    <option value="9">Bebidas Alcoholicas</option>
                    <option value="10">Golosinas</option>
                    <option value="11">Cereales y Granos</option>
                    <option value="12">Pescados y Mariscos</option>
                    <option value="13">Congelados</option>
                    <option value="14">Panadería y Pastelería</option>
                    <option value="15">Condimentos y Salsas</option>
                    <option value="16">Hogar y Cocina</option>
                    <option value="17">Ferretería</option>
                    <option value="18">Artículos de Oficina</option>
                </select>
            </div>
            <div class="col-md-3 text-end">
                <button type="button" class="btn btn-primary fw-bold shadow" data-bs-toggle="modal" data-bs-target="#modalAgregarProducto">
                    + Agregar Producto
                </button>
            </div>
        </div>

        <div class="card mb-3 shadow-sm border-0">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                    <div>
                        <strong>Pila AA Duradera (4u)</strong><br>
                        <small class="text-muted">Bebidas Alcoholicas — Min: 8 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 28.00</p>
                    <small class="text-muted">Stock: 34</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk1" checked />
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
                        <strong>Cuaderno Espiral A4</strong><br>
                        <small class="text-muted">Golosinas — Min: 15 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 7.00</p>
                    <small class="text-muted">Stock: 78</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk2" checked />
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
                        <strong>Agua Mineral 1L</strong><br>
                        <small class="text-muted">Bebidas — Min: 20 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 1.50</p>
                    <small class="text-muted">Stock: 126</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk3" checked />
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
                        <strong>Gaseosa Cola 2L</strong><br>
                        <small class="text-muted">Bebidas — Min: 15 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 3.80</p>
                    <small class="text-muted">Stock: 80</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk4" checked />
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
                        <strong>Papas Fritas Grandes</strong><br>
                        <small class="text-muted">Snacks — Min: 10 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 2.50</p>
                    <small class="text-muted">Stock: 45</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk5" checked />
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
                        <small class="text-muted">Lácteos — Min: 12 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 2.20</p>
                    <small class="text-muted">Stock: 60</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk6" checked />
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
                        <strong>Pan de Molde</strong><br>
                        <small class="text-muted">Panadería — Min: 5 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 4.00</p>
                    <small class="text-muted">Stock: 30</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk7" checked />
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
                        <strong>Detergente Liquido 1L</strong><br>
                        <small class="text-muted">Limpieza — Min: 8 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 5.50</p>
                    <small class="text-muted">Stock: 25</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk8" checked />
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
                        <strong>Jabon de Tocador</strong><br>
                        <small class="text-muted">Higiene Personal — Min: 25 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 1.20</p>
                    <small class="text-muted">Stock: 100</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk9" checked />
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
                        <strong>Atún en Lata</strong><br>
                        <small class="text-muted">Conservas — Min: 15 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 2.00</p>
                    <small class="text-muted">Stock: 75</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk10" checked />
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
                        <strong>Manzanas Rojas</strong><br>
                        <small class="text-muted">Frutas y Verduras — Min: 10 KILOGRAMO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 3.50</p>
                    <small class="text-muted">Stock: 50</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk11" checked />
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
                        <strong>Limpia Vidrios</strong><br>
                        <small class="text-muted">Limpieza — Min: 5 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold text-warning"><i class="fa-solid fa-triangle-exclamation me-1"></i>Stock: 10</p>
                    <p class="mb-0">S/. 3.00</p>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk12" />
                        <label class="form-check-label small text-muted">Inactivo</label>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-3 shadow-sm border-0">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                    <div>
                        <strong>Galletas de Vainilla</strong><br>
                        <small class="text-muted">Snacks — Min: 30 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 1.80</p>
                    <small class="text-muted">Stock: 120</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk13" checked />
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
                        <strong>Yogur Natural</strong><br>
                        <small class="text-muted">Lácteos — Min: 20 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 1.00</p>
                    <small class="text-muted">Stock: 90</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk14" checked />
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
                        <strong>Clavos de 2 Pulgadas</strong><br>
                        <small class="text-muted">Bebidas Alcoholicas — Min: 10 KILOGRAMO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 0.10</p>
                    <small class="text-muted">Stock: 50</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk15" />
                        <label class="form-check-label small text-muted">Inactivo</label>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-3 shadow-sm border-0">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                    <div>
                        <strong>Tinta Impresora Negra</strong><br>
                        <small class="text-muted">Golosinas — Min: 2 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold text-warning"><i class="fa-solid fa-triangle-exclamation me-1"></i>Stock: 5</p>
                    <p class="mb-0">S/. 25.00</p>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk16" />
                        <label class="form-check-label small text-muted">Inactivo</label>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-3 shadow-sm border-0">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-cube fa-2x text-primary me-3"></i>
                    <div>
                        <strong>Harina de Trigo 5kg</strong><br>
                        <small class="text-muted">Cereales y Granos — Min: 20 KILOGRAMO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 10.00</p>
                    <small class="text-muted">Stock: 110</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk17" checked />
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
                        <strong>Filete de Merluza Cong.</strong><br>
                        <small class="text-muted">Pescados y Mariscos — Min: 10 KILOGRAMO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 15.00</p>
                    <small class="text-muted">Stock: 40</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk18" checked />
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
                        <strong>Bolsa de Hielo</strong><br>
                        <small class="text-muted">Congelados — Min: 30 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 2.00</p>
                    <small class="text-muted">Stock: 200</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk19" checked />
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
                        <strong>Torta de Chocolate (porc)</strong><br>
                        <small class="text-muted">Panadería y Pastelería — Min: 5 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 6.00</p>
                    <small class="text-muted">Stock: 25</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk20" checked />
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
                        <strong>Aceite de Oliva Extra</strong><br>
                        <small class="text-muted">Condimentos y Salsas — Min: 15 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 8.50</p>
                    <small class="text-muted">Stock: 60</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk21" checked />
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
                        <strong>Velitas Aromaticas</strong><br>
                        <small class="text-muted">Hogar y Cocina — Min: 20 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 3.00</p>
                    <small class="text-muted">Stock: 150</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk22" checked />
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
                        <strong>Cinta Adhesiva Ancha</strong><br>
                        <small class="text-muted">Ferretería — Min: 10 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 4.00</p>
                    <small class="text-muted">Stock: 80</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk23" checked />
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
                        <strong>Resmas de Papel A4</strong><br>
                        <small class="text-muted">Artículos de Oficina — Min: 15 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 5.00</p>
                    <small class="text-muted">Stock: 70</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk24" checked />
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
                        <strong>Cerveza Premium 6P</strong><br>
                        <small class="text-muted">Bebidas — Min: 10 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 15.00</p>
                    <small class="text-muted">Stock: 50</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk25" checked />
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
                        <strong>Vino Tinto Reserva</strong><br>
                        <small class="text-muted">Bebidas — Min: 5 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 35.00</p>
                    <small class="text-muted">Stock: 15</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk26" checked />
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
                        <strong>Chocolate Barra Grande</strong><br>
                        <small class="text-muted">Snacks — Min: 30 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 4.50</p>
                    <small class="text-muted">Stock: 200</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk27" checked />
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
                        <strong>Gomitas Surtidas 100g</strong><br>
                        <small class="text-muted">Snacks — Min: 50 KILOGRAMO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 1.00</p>
                    <small class="text-muted">Stock: 350</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk28" checked />
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
                        <strong>Avena Hojuelas 1kg</strong><br>
                        <small class="text-muted">Lácteos — Min: 15 KILOGRAMO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 6.50</p>
                    <small class="text-muted">Stock: 80</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk29" checked />
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
                        <strong>Arroz Extra</strong><br>
                        <small class="text-muted">Lácteos — Min: 25 KILOGRAMO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 3.00</p>
                    <small class="text-muted">Stock: 120</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk30" checked />
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
                        <strong>Sardinas en Aceite</strong><br>
                        <small class="text-muted">Panadería — Min: 20 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 2.50</p>
                    <small class="text-muted">Stock: 150</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk31" checked />
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
                        <strong>Papas Pre-Fritas 1kg</strong><br>
                        <small class="text-muted">Limpieza — Min: 10 KILOGRAMO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 7.00</p>
                    <small class="text-muted">Stock: 40</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk32" checked />
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
                        <strong>Croissant Unitario</strong><br>
                        <small class="text-muted">Higiene Personal — Min: 15 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 1.50</p>
                    <small class="text-muted">Stock: 60</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk33" checked />
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
                        <strong>Mayonesa Grande</strong><br>
                        <small class="text-muted">Conservas — Min: 12 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 5.00</p>
                    <small class="text-muted">Stock: 75</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk34" checked />
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
                        <strong>Bolsas de Basura Rll</strong><br>
                        <small class="text-muted">Frutas y Verduras — Min: 20 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 3.50</p>
                    <small class="text-muted">Stock: 110</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk35" checked />
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
                        <strong>Licor de Hierbas</strong><br>
                        <small class="text-muted">Bebidas — Min: 5 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold text-warning"><i class="fa-solid fa-triangle-exclamation me-1"></i>Stock: 8</p>
                    <p class="mb-0">S/. 25.00</p>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk36" checked />
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
                        <strong>Chicle Menta</strong><br>
                        <small class="text-muted">Snacks — Min: 10 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold text-warning"><i class="fa-solid fa-triangle-exclamation me-1"></i>Stock: 15</p>
                    <p class="mb-0">S/. 0.50</p>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk37" checked />
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
                        <strong>Helado Vainilla 1L</strong><br>
                        <small class="text-muted">Limpieza — Min: 5 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold text-warning"><i class="fa-solid fa-triangle-exclamation me-1"></i>Stock: 10</p>
                    <p class="mb-0">S/. 12.00</p>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk38" checked />
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
                        <strong>Salsa Picante Importada</strong><br>
                        <small class="text-muted">Conservas — Min: 5 LITRO</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 9.00</p>
                    <small class="text-muted">Stock: 18</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk39" checked />
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
                        <strong>Trapeador de Algodón</strong><br>
                        <small class="text-muted">Frutas y Verduras — Min: 5 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold">S/. 11.00</p>
                    <small class="text-muted">Stock: 12</small>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk40" checked />
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
                        <strong>Milanesas De Carne (3u)</strong><br>
                        <small class="text-muted">Conservas — Min: 2 UNIDAD</small>
                    </div>
                </div>
                <div class="text-end">
                    <p class="mb-0 fw-bold text-warning"><i class="fa-solid fa-triangle-exclamation me-1"></i>Stock: 10</p>
                    <p class="mb-0">S/. 10.50</p>
                    <div class="form-check form-switch mt-1">
                        <input class="form-check-input" type="checkbox" id="chk41" checked />
                        <label class="form-check-label small text-muted">Activo</label>
                    </div>
                </div>
            </div>
        </div>
        
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
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Nombre del Producto"></asp:Label>
                                <asp:TextBox ID="txtNombreProducto" CssClass="form-control" placeholder="Ej: Arroz Diana 500g" runat="server"></asp:TextBox>
                            </div>

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Categoría"></asp:Label>
                                <asp:DropDownList ID="ddlCategoria" CssClass="form-select" runat="server">
                                    <asp:ListItem Text="Seleccionar categoría" Value="0" Selected="True"></asp:ListItem>
                                    <asp:ListItem Text="Bebidas" Value="1"></asp:ListItem>
                                    <asp:ListItem Text="Snacks" Value="2"></asp:ListItem>
                                    <asp:ListItem Text="Lácteos" Value="3"></asp:ListItem>
                                    <asp:ListItem Text="Panadería" Value="4"></asp:ListItem>
                                    <asp:ListItem Text="Limpieza" Value="5"></asp:ListItem>
                                    <asp:ListItem Text="Higiene Personal" Value="6"></asp:ListItem>
                                    <asp:ListItem Text="Conservas" Value="7"></asp:ListItem>
                                    <asp:ListItem Text="Frutas y Verduras" Value="8"></asp:ListItem>
                                    <asp:ListItem Text="Bebidas Alcoholicas" Value="9"></asp:ListItem>
                                    <asp:ListItem Text="Golosinas" Value="10"></asp:ListItem>
                                    <asp:ListItem Text="Cereales y Granos" Value="11"></asp:ListItem>
                                    <asp:ListItem Text="Pescados y Mariscos" Value="12"></asp:ListItem>
                                    <asp:ListItem Text="Congelados" Value="13"></asp:ListItem>
                                    <asp:ListItem Text="Panadería y Pastelería" Value="14"></asp:ListItem>
                                    <asp:ListItem Text="Condimentos y Salsas" Value="15"></asp:ListItem>
                                    <asp:ListItem Text="Hogar y Cocina" Value="16"></asp:ListItem>
                                    <asp:ListItem Text="Ferretería" Value="17"></asp:ListItem>
                                    <asp:ListItem Text="Artículos de Oficina" Value="18"></asp:ListItem>
                                </asp:DropDownList>
                            </div>

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="O crear nueva categoría"></asp:Label>
                                <asp:TextBox ID="txtNuevaCategoria" CssClass="form-control" placeholder="Nueva categoría" runat="server"></asp:TextBox>
                            </div>

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

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Stock Mínimo"></asp:Label>
                                <asp:TextBox ID="txtStockMinimo" CssClass="form-control" placeholder="5" runat="server"></asp:TextBox>
                            </div>

                            <a href="#" class="btn btn-dark w-100 py-2 fw-semibold">Agregar Producto</a>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>

</asp:Content>