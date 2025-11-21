<%@ Page Title="" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="SoftBodWA.Inicio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid">
        <h4 class="dashboard-title mb-4">Mi Bodega</h4>

        <!-- Botones de Acción Principal -->
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
                <a href="RegistrarDevolucion.aspx" class="text-decoration-none">
                    <div class="card text-center text-white" style="background-color:#e4572e;">
                        <div class="card-body">
                            <i class="bi bi-arrow-counterclockwise fs-3"></i>
                            <h6 class="mt-2 mb-0">Registrar Devolución</h6>
                        </div>
                    </div>
                </a>
            </div>
        </div>

        <!-- Tarjetas de Estadísticas -->
        <div class="row g-3 mb-4">
            <div class="col-6">
                <div class="card p-3">
                    <div class="d-flex justify-content-between">
                        <div>
                            <small class="text-muted">Ventas Hoy</small>
                            <h5 class="fw-bold">
                                <asp:Literal ID="litVentasHoy" runat="server" Text="S/.0.00"></asp:Literal>
                            </h5>
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
                            <h5 class="fw-bold">
                                <asp:Literal ID="litProductos" runat="server" Text="0"></asp:Literal>
                            </h5>
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
                            <h5 class="fw-bold">
                                <asp:Literal ID="litClientes" runat="server" Text="0"></asp:Literal>
                            </h5>
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
                            <h5 class="fw-bold text-danger">
                                <asp:Literal ID="litFiados" runat="server" Text="S/.0.00"></asp:Literal>
                            </h5>
                        </div>
                        <i class="bi bi-credit-card text-warning fs-3"></i>
                    </div>
                </div>
            </div>
        </div>

        <!-- Movimientos Recientes -->
        <div class="card p-3">
            <h6 class="fw-bold mb-3">Movimientos De Hoy</h6>

            <asp:Repeater ID="rptMovimientos" runat="server">
                <ItemTemplate>
                    <div class="list-group-item list-group-item-action d-flex align-items-center justify-content-between border-bottom py-2">
                        <div class="d-flex align-items-center">
                            <i class="bi <%# Eval("Icono") %> fs-4 me-2" style="color: <%# Eval("ColorIcono") %>;"></i>
                            <div>
                                <div class="fw-bold"><%# Eval("Titulo") %></div>
                                <small class="text-muted"><%# Eval("FechaHora") %></small>
                            </div>
                        </div>
                        <div class="text-end d-flex align-items-center">
                            <div>
                                <span class="fw-bold me-2" style="color: <%# Eval("ColorMonto") %>;"><%# Eval("Monto") %></span><br />
                                <span class="badge" style="background-color: <%# Eval("ColorBadge") %>;"><%# Eval("TipoBadge") %></span>
                            </div>
                            <asp:LinkButton ID="btnVerDetalle" runat="server" 
                                CssClass="btn btn-sm btn-outline-secondary ms-2 p-1"
                                CommandName="VerDetalle"
                                CommandArgument='<%# Eval("Id") + "|" + Eval("Tipo") %>'
                                OnCommand="btnVerDetalle_Command">
                                <i class="bi bi-eye"></i>
                            </asp:LinkButton>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>

            <asp:Panel ID="pnlSinMovimientos" runat="server" Visible="false" CssClass="text-center py-4">
                <i class="bi bi-inbox fs-1 text-muted"></i>
                <p class="text-muted mt-2">No hay movimientos recientes</p>
            </asp:Panel>
        </div>
    </div>

    <!-- Modal de Detalles -->
    <div class="modal fade" id="DetalleModal" tabindex="-1" aria-labelledby="DetalleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="DetalleModalLabel">
                        <asp:Literal ID="litModalTitulo" runat="server"></asp:Literal>
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Información del Cliente y Fecha -->
                    <div class="row mb-3">
                        <div class="col-6">
                            <small class="text-muted">Cliente</small><br />
                            <strong><asp:Literal ID="litModalCliente" runat="server"></asp:Literal></strong>
                        </div>
                        <div class="col-6 text-end">
                            <small class="text-muted">Fecha y Hora</small><br />
                            <strong><asp:Literal ID="litModalFechaHora" runat="server"></asp:Literal></strong>
                        </div>
                    </div>

                    <!-- Tipo y Estado -->
                    <div class="row mb-3">
                        <div class="col-6">
                            <small class="text-muted">Tipo</small><br />
                            <strong><asp:Literal ID="litModalTipo" runat="server"></asp:Literal></strong>
                        </div>
                        <div class="col-6 text-end">
                            <small class="text-muted">Estado</small><br />
                            <strong>
                                <asp:Literal ID="litModalEstado" runat="server"></asp:Literal>
                            </strong>
                        </div>
                    </div>

                    <!-- Lista de Productos -->
                    <h6 class="mt-4 mb-2">Productos</h6>
                    <asp:Panel ID="pnlProductos" runat="server">
                        <ul class="list-group list-group-flush">
                            <asp:Repeater ID="rptProductosModal" runat="server">
                                <ItemTemplate>
                                    <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                        <span><%# Eval("Descripcion") %></span>
                                        <span><%# Eval("Precio") %></span>
                                    </li>
                                </ItemTemplate>
                            </asp:Repeater>
                        </ul>
                    </asp:Panel>

                    <!-- Mensaje cuando no hay productos -->
                    <asp:Panel ID="pnlSinProductos" runat="server" Visible="false">
                        <div class="text-center text-muted py-2">
                            <asp:Literal ID="litMensajeSinProductos" runat="server"></asp:Literal>
                        </div>
                    </asp:Panel>

                    <!-- Total -->
                    <div class="d-flex justify-content-between mt-3 pt-2 border-top">
                        <h5 class="mb-0">Total:</h5>
                        <h5 class="mb-0">
                            <asp:Literal ID="litModalTotal" runat="server"></asp:Literal>
                        </h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        // Mostrar modal cuando se actualiza desde el servidor
        function mostrarModal() {
            var modal = new bootstrap.Modal(document.getElementById('DetalleModal'));
            modal.show();
        }
    </script>
</asp:Content>