<%@ Page Title="" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="SoftBodWA.Inicio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid">
        <h4 class="dashboard-title mb-4">Mi Bodega</h4>

        <div class="row mb-4">
            <div class="col-6">
                <a href="RegistrarVenta.aspx" class="card text-center text-white bg-primary text-decoration-none border-0">
                    <div class="card-body">
                        <i class="bi bi-cart-plus fs-3"></i>
                        <h6 class="mt-2 mb-0">Registrar Venta</h6>
                    </div>
                </a>
            </div>
            <div class="col-6">
                <a href="RegistrarDevolucion.aspx" class="text-decoration-none" >
                <div class="card text-center text-white" style="background-color:#e4572e;">
                    <div class="card-body">
                        <i class="bi bi-arrow-counterclockwise fs-3"></i>
                        <h6 class="mt-2 mb-0">Registrar Devolución</h6>
                    </div>
                </div>
                </a>
            </div>
        </div>

        <div class="row g-3 mb-4">
            <div class="col-6">
                <div class="card p-3">
                    <div class="d-flex justify-content-between">
                        <div>
                            <small class="text-muted">Ventas Hoy</small>
                            <h5 class="fw-bold">S/.782.15</h5>
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
                            <h5 class="fw-bold">38</h5>
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
                            <h5 class="fw-bold">31</h5>
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
                            <h5 class="fw-bold text-danger">S/.1,335.54</h5>
                        </div>
                        <i class="bi bi-credit-card text-warning fs-3"></i>
                    </div>
                </div>
            </div>
        </div>

        <div class="card p-3">
            <h6 class="fw-bold mb-3">Movimientos Recientes</h6>

            <div class="list-group-item list-group-item-action d-flex align-items-center justify-content-between border-bottom py-2">
                <div class="d-flex align-items-center">
                    <i class="bi bi-cart-check text-success fs-4 me-2"></i>
                    <div>
                        <div class="fw-bold">Venta - Venta ID 19</div>
                        <small class="text-muted">10:20 AM (2025-10-07)</small>
                    </div>
                </div>
                <div class="text-end d-flex align-items-center">
                    <div>
                        <span class="text-success fw-bold me-2">+S/.22.15</span><br />
                        <span class="badge bg-primary text-white">Transferencia</span>
                    </div>
                    <button type="button" class="btn btn-sm btn-outline-secondary ms-2 p-1" data-bs-toggle="modal" data-bs-target="#DetalleVentaModal" data-id="19" data-tipo="Venta">
                        <i class="bi bi-eye"></i>
                    </button>
                </div>
            </div>

            <div class="list-group-item list-group-item-action d-flex align-items-center justify-content-between border-bottom py-2">
                <div class="d-flex align-items-center">
                    <i class="bi bi-arrow-counterclockwise text-danger fs-4 me-2"></i>
                    <div>
                        <div class="fw-bold">Devolución - ID 8</div>
                        <small class="text-muted">3:00 PM (2025-10-06)</small>
                    </div>
                </div>
                <div class="text-end d-flex align-items-center">
                    <div>
                        <span class="text-danger fw-bold me-2">-S/.7.00</span><br />
                        <span class="badge bg-secondary">Devolución</span>
                    </div>
                     <button type="button" class="btn btn-sm btn-outline-secondary ms-2 p-1" data-bs-toggle="modal" data-bs-target="#DetalleVentaModal" data-id="8" data-tipo="Devolucion">
                        <i class="bi bi-eye"></i>
                    </button>
                </div>
            </div>

            <div class="list-group-item list-group-item-action d-flex align-items-center justify-content-between border-bottom py-2">
                <div class="d-flex align-items-center">
                    <i class="bi bi-cash text-info fs-4 me-2"></i>
                    <div>
                        <div class="fw-bold">Pago Fiado - Vecino 26</div>
                        <small class="text-muted">Fecha desconocida (2025-10-08)</small>
                    </div>
                </div>
                <div class="text-end d-flex align-items-center">
                    <div>
                        <span class="text-info fw-bold me-2">S/.18.00</span><br />
                        <span class="badge bg-warning text-dark">Pago Fiado</span>
                    </div>
                     <button type="button" class="btn btn-sm btn-outline-secondary ms-2 p-1" data-bs-toggle="modal" data-bs-target="#DetalleVentaModal" data-id="21" data-tipo="Pago">
                        <i class="bi bi-eye"></i>
                    </button>
                </div>
            </div>

            <div class="list-group-item list-group-item-action d-flex align-items-center justify-content-between border-bottom py-2">
                <div class="d-flex align-items-center">
                    <i class="bi bi-cart-check text-success fs-4 me-2"></i>
                    <div>
                        <div class="fw-bold">Venta Fiada - El Vecino 1</div>
                        <small class="text-muted">Hora cercana a 10:20 AM (2025-10-07)</small>
                    </div>
                </div>
                <div class="text-end d-flex align-items-center">
                    <div>
                        <span class="text-success fw-bold me-2">+S/.68.00</span><br />
                        <span class="badge bg-warning text-dark">Fiado</span>
                    </div>
                     <button type="button" class="btn btn-sm btn-outline-secondary ms-2 p-1" data-bs-toggle="modal" data-bs-target="#DetalleVentaModal" data-id="62" data-tipo="Venta">
                        <i class="bi bi-eye"></i>
                    </button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="DetalleVentaModal" tabindex="-1" aria-labelledby="DetalleVentaModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="DetalleVentaModalLabel">Detalles de la Transacción #<span id="transactionId"></span></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row mb-3">
                        <div class="col-6">
                            <small class="text-muted">Cliente</small><br />
                            <strong id="modalCliente">Juan Pérez</strong>
                        </div>
                        <div class="col-6 text-end">
                            <small class="text-muted">Fecha y Hora</small><br />
                            <strong id="modalFechaHora">11/11/2025 10:30 AM</strong>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <small class="text-muted">Tipo</small><br />
                            <strong id="modalTipo">Fiado</strong>
                        </div>
                        <div class="col-6 text-end">
                            <small class="text-muted">Estado</small><br />
                            <strong id="modalEstado" class="text-success">Completada</strong>
                        </div>
                    </div>

                    <h6 class="mt-4 mb-2">Productos</h6>
                    <ul class="list-group list-group-flush" id="modalProductos">
                        <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                            <span>Arroz Diana 500g x2</span>
                            <span>S/.15.00</span>
                        </li>
                         <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                            <span>Aceite Girasol 1L x1</span>
                            <span>S/.15.50</span>
                        </li>
                    </ul>

                    <div class="d-flex justify-content-between mt-3 pt-2 border-top">
                        <h5 class="mb-0">Total:</h5>
                        <h5 class="mb-0 text-success" id="modalTotal">+S/.45.50</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
        $(document).ready(function () {
            $('#DetalleVentaModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget);
                var id = button.data('id');
                var tipo = button.data('tipo');

                var modal = $(this);


                if (tipo === "Venta" && id === 19) {
                    modal.find('.modal-title').text('Detalles de Venta #' + id);
                    $('#transactionId').text(id);
                    $('#modalCliente').text('Usuario Caja 2');
                    $('#modalFechaHora').text('2025-10-07 10:20 AM');
                    $('#modalTipo').text('Transferencia');
                    $('#modalEstado').text('Completada').removeClass('text-danger').addClass('text-success');
                    $('#modalTotal').text('+S/.22.15').removeClass('text-danger').addClass('text-success');

                    $('#modalProductos').html(
                        '<li class="list-group-item d-flex justify-content-between align-items-center px-0"><span>Torta de Chocolate (porc) x1</span><span>S/.6.00</span></li>' +
                        '<li class="list-group-item d-flex justify-content-between align-items-center px-0"><span>Aceite de Oliva Extra x1</span><span>S/.8.50</span></li>' +
                        '<li class="list-group-item d-flex justify-content-between align-items-center px-0"><span>Velitas Aromaticas x1</span><span>S/.3.00</span></li>'
                    );
                }
                else if (tipo === "Venta" && id === 62) {
                    modal.find('.modal-title').text('Detalles de Venta Fiada #' + id);
                    $('#transactionId').text(id);
                    $('#modalCliente').text('El Vecino 1');
                    $('#modalFechaHora').text('2025-10-07 10:20 AM');
                    $('#modalTipo').text('Fiado');
                    $('#modalEstado').text('Pendiente').removeClass('text-success').addClass('text-danger');
                    $('#modalTotal').text('+S/.68.00').removeClass('text-danger').addClass('text-success');

                    $('#modalProductos').html(
                        '<li class="list-group-item d-flex justify-content-between align-items-center px-0"><span>Pila AA Duradera (4u) x5</span><span>S/.65.00</span></li>' +
                        '<li class="list-group-item d-flex justify-content-between align-items-center px-0"><span>Agua Mineral 1L x2</span><span>S/.3.00</span></li>'
                    );
                }
                else if (tipo === "Devolucion" && id === 8) {
                    modal.find('.modal-title').text('Detalles de Devolución #' + id);
                    $('#transactionId').text(id);
                    $('#modalCliente').text('Usuario Caja 2');
                    $('#modalFechaHora').text('2025-10-06 3:00 PM');
                    $('#modalTipo').text('Devolución');
                    $('#modalEstado').text('Completada').removeClass('text-danger').addClass('text-success');
                    $('#modalTotal').text('-S/.7.00').removeClass('text-success').addClass('text-danger');

                    $('#modalProductos').html(
                        '<li class="list-group-item d-flex justify-content-between align-items-center px-0"><span>Detergente Liquido 1L x2</span><span>S/.4.00</span></li>' +
                        '<li class="list-group-item d-flex justify-content-between align-items-center px-0"><span>Resmas de Papel A4 x2</span><span>S/.5.00</span></li>'
                    );
                }
                else if (tipo === "Pago" && id === 21) {
                    modal.find('.modal-title').text('Detalles de Pago Fiado #' + id);
                    $('#transactionId').text(id);
                    $('#modalCliente').text('Vecino 26');
                    $('#modalFechaHora').text('2025-10-08 10:00 AM');
                    $('#modalTipo').text('EFECTIVO');
                    $('#modalEstado').text('Registrado').removeClass('text-danger').addClass('text-success');
                    $('#modalTotal').text('S/.18.00').removeClass('text-danger').addClass('text-success');

                    $('#modalProductos').html(
                        '<li class="list-group-item d-flex justify-content-center align-items-center px-0"><span>* No aplica detalle de productos para pagos *</span></li>'
                    );
                }
                else {
                    modal.find('.modal-title').text('Detalles de Transacción');
                    $('#modalCliente').text('N/A');
                    $('#modalFechaHora').text('N/A');
                    $('#modalTipo').text('N/A');
                    $('#modalEstado').text('Error').removeClass('text-success').addClass('text-danger');
                    $('#modalTotal').text('N/A');
                    $('#modalProductos').html('<li class="list-group-item d-flex justify-content-center align-items-center px-0"><span>Detalles no disponibles.</span></li>');
                }
            })
        });
    </script>
</asp:Content>