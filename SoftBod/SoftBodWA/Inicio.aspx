<%@ Page Title="" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="SoftBodWA.Inicio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid">
        <h4 class="dashboard-title mb-4">Mi Bodega</h4>

        <!-- Botones de acciones -->
        <div class="row mb-4">
            <div class="col-6">
                <div class="card text-center text-white bg-primary">
                    <div class="card-body">
                        <i class="bi bi-cart-plus fs-3"></i>
                        <h6 class="mt-2 mb-0">Registrar Venta</h6>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="card text-center text-white" style="background-color:#e4572e;">
                    <div class="card-body">
                        <i class="bi bi-arrow-counterclockwise fs-3"></i>
                        <h6 class="mt-2 mb-0">Registrar Devolución</h6>
                    </div>
                </div>
            </div>
        </div>

        <!-- Estadísticas -->
        <div class="row g-3 mb-4">
            <div class="col-6">
                <div class="card p-3">
                    <div class="d-flex justify-content-between">
                        <div>
                            <small class="text-muted">Ventas Hoy</small>
                            <h5 class="fw-bold">S/.1,250.00</h5>
                        </div>
                        <i class="bi bi-currency-dollar text-success fs-3"></i>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="card p-3">
                    <div class="d-flex justify-content-between">
                        <div>
                            <small class="text-muted">Productos</small>
                            <h5 class="fw-bold">156</h5>
                        </div>
                        <i class="bi bi-box text-primary fs-3"></i>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="card p-3">
                    <div class="d-flex justify-content-between">
                        <div>
                            <small class="text-muted">Clientes</small>
                            <h5 class="fw-bold">89</h5>
                        </div>
                        <i class="bi bi-people text-purple fs-3"></i>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="card p-3">
                    <div class="d-flex justify-content-between">
                        <div>
                            <small class="text-muted">Fiados</small>
                            <h5 class="fw-bold text-danger">S/.3,480.00</h5>
                        </div>
                        <i class="bi bi-credit-card text-warning fs-3"></i>
                    </div>
                </div>
            </div>
        </div>

        <!-- Movimientos recientes -->
        <div class="card p-3">
            <h6 class="fw-bold mb-3">Movimientos Recientes</h6>
            <div class="d-flex justify-content-between align-items-center border-bottom pb-2 mb-2">
                <div>
                    <i class="bi bi-cart-check text-success"></i> Venta - juan123<br />
                    <small class="text-muted">10:30 AM</small>
                </div>
                <div class="text-end">
                    <span class="text-success fw-bold">+S/.45.50</span><br />
                    <span class="badge bg-warning text-dark">Fiado</span>
                </div>
            </div>
        </div>
    </div>
</asp:Content>