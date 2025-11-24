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
                            <small class="text-muted">Devoluciones Hoy</small>
                            <h5 class="fw-bold" style="color: #e4572e;">
                                <asp:Literal ID="litDevoluciones" runat="server" Text="S/.0.00"></asp:Literal>
                            </h5>
                        </div>
                        <i class="bi bi-arrow-counterclockwise fs-3" style="color: #e4572e;"></i>
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
            
                        <!-- Icono y Título del Movimiento -->
                        <div class="d-flex align-items-center">
                            <i class="bi <%# Eval("Icono") %> fs-4 me-3" style="color: <%# Eval("ColorIcono") %>; width: 30px; text-align: center;"></i>
                            <div>
                                <div class="fw-bold"><%# Eval("Titulo") %></div>
                                <small class="text-muted"><i class="bi bi-clock me-1"></i><%# Eval("FechaHora") %></small>
                            </div>
                        </div>
            
                        <!-- Monto, Badge y Botón de Detalle -->
                        <div class="text-end d-flex align-items-center gap-3">
                
                            <!-- Información de Monto y Tipo -->
                            <div class="d-none d-sm-block">
                                <span class="fw-bold fs-6 me-2" style="color: <%# Eval("ColorMonto") %>;"><%# Eval("Monto") %></span><br />
                                <span class="badge rounded-pill px-2 py-1" style="background-color: <%# Eval("ColorBadge") %>; font-size: 0.75rem;"><%# Eval("TipoBadge") %></span>
                            </div>
                
                            <!-- Botón Ver Detalle -->
                            <asp:LinkButton ID="btnVerDetalle" runat="server" 
                                CssClass="btn btn-outline-info rounded-circle d-flex align-items-center justify-content-center shadow-sm"
                                Style="width: 40px; height: 40px; "
                                CommandName="VerDetalle"
                                CommandArgument='<%# Eval("Id") + "|" + Eval("Tipo") %>'
                                OnCommand="btnVerDetalle_Command"
                                ToolTip="Ver Detalle del Movimiento">
                                <i class="bi bi-eye fs-6"></i>
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
                                <asp:Literal ID="litEstadoCompletada" runat="server" Visible="false"><span class="text-success">Completada</span></asp:Literal>
                                <asp:Literal ID="litEstadoPendiente" runat="server" Visible="false"><span class="text-danger">Pendiente</span></asp:Literal>
                                <asp:Literal ID="litEstadoRegistrado" runat="server" Visible="false"><span class="text-success">Registrado</span></asp:Literal>
                            </strong>
                        </div>
                    </div>

                    <!-- Información adicional del cliente (solo para pagos) -->
                    <asp:Panel ID="pnlInfoCliente" runat="server" Visible="false">
                        <div class="card bg-light mb-3">
                            <div class="card-body">
                                <h6 class="card-title mb-2">Información del Cliente</h6>
                                <div class="row">
                                    <div class="col-6">
                                        <small class="text-muted">Nombre Completo</small><br />
                                        <strong><asp:Literal ID="litClienteNombre" runat="server"></asp:Literal></strong>
                                    </div>
                                    <div class="col-6">
                                        <small class="text-muted">Alias</small><br />
                                        <strong><asp:Literal ID="litClienteAlias" runat="server"></asp:Literal></strong>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-6">
                                        <small class="text-muted">Teléfono</small><br />
                                        <strong><asp:Literal ID="litClienteTelefono" runat="server"></asp:Literal></strong>
                                    </div>
                                    <div class="col-6">
                                        <small class="text-muted">Deuda Actual</small><br />
                                        <strong class="text-danger">
                                            <asp:Literal ID="litClienteDeuda" runat="server"></asp:Literal>
                                        </strong>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </asp:Panel>

                    <!-- Lista de Productos -->
                    <asp:Panel ID="pnlProductos" runat="server">
                        <h6 class="mt-4 mb-2">Productos</h6>
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
                            <asp:Panel ID="pnlTotalPositivo" runat="server" Visible="false" CssClass="d-inline">
                                <span class="text-success"><asp:Literal ID="litTotalPositivoValor" runat="server"></asp:Literal></span>
                            </asp:Panel>
                            <asp:Panel ID="pnlTotalNegativo" runat="server" Visible="false" CssClass="d-inline">
                                <span class="text-danger"><asp:Literal ID="litTotalNegativoValor" runat="server"></asp:Literal></span>
                            </asp:Panel>
                        </h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        function mostrarModal() {
            var modal = new bootstrap.Modal(document.getElementById('DetalleModal'));
            modal.show();
        }
    </script>
</asp:Content>