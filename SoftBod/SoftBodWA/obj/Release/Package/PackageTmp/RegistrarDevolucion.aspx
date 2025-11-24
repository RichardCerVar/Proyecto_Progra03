<%@ Page Title="Registrar Devolución" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="RegistrarDevolucion.aspx.cs" Inherits="SoftBodWA.RegistrarDevolucion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid">
        <!-- Panel de Selección de Venta -->
        <asp:Panel ID="pnlSeleccionVenta" runat="server" Visible="true">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <div>
                    <h4 class="dashboard-title mb-1">Ventas de Hoy </h4>
                    <small class="text-muted">Selecciona una venta para procesar su devolución</small>
                </div>
                <div class="d-flex gap-2">
                    <!-- BOTÓN DE VOLVER NUEVO -->
                    <asp:LinkButton ID="btnVolverInicio" runat="server" 
                        CssClass="btn btn-outline-secondary btn-sm"
                        OnClick="btnVolverInicio_Click">
                        <i class="bi bi-arrow-left me-1"></i>Volver
                    </asp:LinkButton>
            
                    <asp:LinkButton ID="btnGestionarRazones" runat="server" 
                        CssClass="btn btn-outline-secondary btn-sm"
                        OnClick="btnGestionarRazones_Click">
                        <i class="bi bi-gear me-1"></i>Gestionar Razones
                    </asp:LinkButton>
                </div>
            </div>

            <!-- Lista de Ventas -->
            <div class="card">
                <div class="card-body p-0">
                    <asp:Repeater ID="rptVentas" runat="server">
                        <ItemTemplate>
                            <div class="list-group-item list-group-item-action d-flex align-items-center justify-content-between border-bottom py-3">
                                <!-- Información de la Venta -->
                                <div class="d-flex align-items-center flex-grow-1">
                                    <i class="bi <%# Eval("Icono") %> fs-4 me-3" style="color: <%# Eval("ColorIcono") %>; width: 30px; text-align: center;"></i>
                                    <div>
                                        <div class="fw-bold"><%# Eval("Titulo") %></div>
                                        <small class="text-muted">
                                            <i class="bi bi-clock me-1"></i><%# Eval("FechaHora") %>
                                        </small>
                                    </div>
                                </div>

                                <!-- Monto y Acciones -->
                                <div class="text-end d-flex align-items-center gap-2">
                                    <div class="d-none d-sm-block me-2">
                                        <span class="fw-bold fs-6" style="color: <%# Eval("ColorMonto") %>;"><%# Eval("Monto") %></span><br />
                                        <span class="badge rounded-pill px-2 py-1" style="background-color: <%# Eval("ColorBadge") %>; font-size: 0.75rem;">
                                            <%# Eval("TipoBadge") %>
                                        </span>
                                    </div>

                                    <!-- Botón Ver Detalle -->
                                    <asp:LinkButton ID="btnVerDetalleVenta" runat="server" 
                                        CssClass="btn btn-outline-info rounded-circle d-flex align-items-center justify-content-center shadow-sm"
                                        Style="width: 40px; height: 40px;"
                                        CommandName="VerDetalle"
                                        CommandArgument='<%# Eval("Id") + "|" + Eval("Tipo") %>'
                                        OnCommand="btnVerDetalleVenta_Command"
                                        ToolTip="Ver Detalle">
                                        <i class="bi bi-eye fs-6"></i>
                                    </asp:LinkButton>

                                    <!-- Botón Seleccionar -->
                                    <asp:LinkButton ID="btnSeleccionarVenta" runat="server" 
                                        CssClass="btn btn-primary"
                                        CommandName="Seleccionar"
                                        CommandArgument='<%# Eval("Id") + "|" + Eval("Tipo") %>'
                                        OnCommand="btnSeleccionarVenta_Command">
                                        <i class="bi bi-check-circle me-1"></i>Seleccionar
                                    </asp:LinkButton>
                                </div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>

                    <asp:Panel ID="pnlSinVentas" runat="server" Visible="false" CssClass="text-center py-5">
                        <i class="bi bi-inbox fs-1 text-muted"></i>
                        <p class="text-muted mt-2">No hay ventas registradas hoy</p>
                    </asp:Panel>
                </div>
            </div>
        </asp:Panel>

        <!-- Panel de Detalle de Devolución -->
        <asp:Panel ID="pnlDetalleDevolucion" runat="server" Visible="false">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <div>
                    <h4 class="dashboard-title mb-1">Procesar Devolución</h4>
                    <small class="text-muted">Venta #<asp:Literal ID="litVentaIdSeleccionada" runat="server"></asp:Literal></small>
                </div>
                <asp:LinkButton ID="btnVolverListaVentas" runat="server" 
                    CssClass="btn btn-outline-secondary btn-sm"
                    OnClick="btnVolverListaVentas_Click">
                    <i class="bi bi-arrow-left me-1"></i>Volver
                </asp:LinkButton>
            </div>

            <!-- Información de la Venta -->
            <div class="card mb-3">
                <div class="card-body">
                    <div class="row">
                        <div class="col-6">
                            <small class="text-muted">Fecha</small><br />
                            <strong><asp:Literal ID="litFechaVenta" runat="server"></asp:Literal></strong>
                        </div>
                        <div class="col-6 text-end">
                            <small class="text-muted">Total</small><br />
                            <strong class="text-success">S/.<asp:Literal ID="litTotalVenta" runat="server"></asp:Literal></strong>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Productos de la Venta -->
            <div class="card mb-3">
                <div class="card-header">
                    <h6 class="mb-0">Productos a Devolver</h6>
                </div>
                <div class="card-body">
                    <asp:Repeater ID="rptDetalleVenta" runat="server">
                        <ItemTemplate>
                            <div class="row align-items-center mb-3 pb-3 border-bottom">
                                <div class="col-md-5">
                                    <strong><%# Eval("NombreProducto") %></strong><br />
                                    <small class="text-muted">Precio: S/.<%# String.Format("{0:F2}", Eval("PrecioUnitario")) %></small></div>
                                <div class="col-md-3">
                                    <small class="text-muted d-block">Cantidad Vendida</small>
                                    <strong><%# Eval("Cantidad") %> unidades</strong>
                                </div>
                                <div class="col-md-2">
                                    <label class="form-label mb-1">Cant. a Devolver</label>
                                    <asp:TextBox ID="txtCantidadDevolver" runat="server" 
                                        CssClass="form-control form-control-sm text-center" 
                                        TextMode="Number" 
                                        min="0" 
                                        max='<%# Eval("Cantidad") %>'
                                        Text="0"
                                        data-producto-id='<%# Eval("ProductoId") %>'
                                        data-precio='<%# Eval("PrecioUnitario") %>'
                                        data-cantidad-maxima='<%# Eval("Cantidad") %>'
                                        onchange="calcularTotalDevolucion()">
                                    </asp:TextBox>
                                </div>
                                <div class="col-md-2 text-end">
                                    <small class="text-muted d-block">Subtotal</small>
                                    <strong class="text-danger" id="subtotal_<%# Eval("ProductoId") %>">S/.0.00</strong>
                                </div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>
                </div>
            </div>

            <!-- Razón de Devolución y Total -->
            <div class="card mb-3">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-8 mb-3">
                            <label class="form-label">Razón de Devolución *</label>
                            <asp:DropDownList ID="ddlRazonDevolucion" runat="server" CssClass="form-select">
                            </asp:DropDownList>
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Total a Devolver</label>
                            <div class="input-group">
                                <span class="input-group-text">S/.</span>
                                <asp:TextBox ID="txtTotalDevolucion" runat="server" 
                                    CssClass="form-control text-end fw-bold" 
                                    ReadOnly="true"
                                    Text="0.00">
                                </asp:TextBox>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Botones de Acción -->
            <div class="d-flex justify-content-end gap-2">
                <asp:LinkButton ID="btnCancelarDevolucion" runat="server" 
                    CssClass="btn btn-secondary"
                    OnClick="btnVolverListaVentas_Click">
                    <i class="bi bi-x-circle me-1"></i>Cancelar
                </asp:LinkButton>
                <asp:LinkButton ID="btnRegistrarDevolucion" runat="server" 
                    CssClass="btn btn-danger"
                    OnClick="btnRegistrarDevolucion_Click">
                    <i class="bi bi-check-circle me-1"></i>Registrar Devolución
                </asp:LinkButton>
            </div>
        </asp:Panel>
    </div>

    <!-- Modal de Detalle de Venta -->
    <div class="modal fade" id="DetalleVentaModal" tabindex="-1" aria-labelledby="DetalleVentaModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="DetalleVentaModalLabel">
                        <asp:Literal ID="litModalTituloVenta" runat="server"></asp:Literal>
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Información de la Venta -->
                    <div class="row mb-3">
                        <div class="col-6">
                            <small class="text-muted">Fecha y Hora</small><br />
                            <strong><asp:Literal ID="litModalFechaVenta" runat="server"></asp:Literal></strong>
                        </div>
                        <div class="col-6 text-end">
                            <small class="text-muted">
                                <asp:Literal ID="litModalTipoLabel" runat="server" Text="Método de Pago"></asp:Literal>
                            </small><br />
                            <strong><asp:Literal ID="litModalMetodoPago" runat="server"></asp:Literal></strong>
                        </div>
                    </div>

                    <!-- Información del Cliente (solo para fiados) -->
                    <asp:Panel ID="pnlModalInfoCliente" runat="server" Visible="false">
                        <div class="row mb-3">
                            <div class="col-6">
                                <small class="text-muted">Cliente</small><br />
                                <strong><asp:Literal ID="litModalCliente" runat="server"></asp:Literal></strong>
                            </div>
                            <div class="col-6 text-end">
                                <small class="text-muted">Estado</small><br />
                                <strong>
                                    <asp:Literal ID="litModalEstadoPendiente" runat="server" Visible="false">
                                        <span class="text-danger">Pendiente</span>
                                    </asp:Literal>
                                    <asp:Literal ID="litModalEstadoCompletada" runat="server" Visible="false">
                                        <span class="text-success">Completada</span>
                                    </asp:Literal>
                                </strong>
                            </div>
                        </div>
                    </asp:Panel>

                    <!-- Lista de Productos -->
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

                    <!-- Total -->
                    <div class="d-flex justify-content-between mt-3 pt-2 border-top">
                        <h5 class="mb-0">Total:</h5>
                        <h5 class="mb-0 text-success">
                            <asp:Literal ID="litModalTotalVenta" runat="server"></asp:Literal>
                        </h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        function mostrarModalVenta() {
            var modal = new bootstrap.Modal(document.getElementById('DetalleVentaModal'));
            modal.show();
        }

        function calcularTotalDevolucion() {
            let total = 0;

            document.querySelectorAll('[id*="txtCantidadDevolver"]').forEach(function (input) {
                const cantidad = parseFloat(input.value) || 0;
                const precio = parseFloat(input.getAttribute('data-precio')) || 0;
                const cantidadMaxima = parseFloat(input.getAttribute('data-cantidad-maxima')) || 0;
                const productoId = input.getAttribute('data-producto-id');

                if (cantidad > cantidadMaxima) {
                    input.value = cantidadMaxima;
                    alert('La cantidad a devolver no puede exceder la cantidad vendida');
                    return;
                }

                const subtotal = cantidad * precio;
                total += subtotal;

                const subtotalElement = document.getElementById('subtotal_' + productoId);
                if (subtotalElement) {
                    subtotalElement.textContent = 'S/.' + subtotal.toFixed(2);
                }
            });

            const totalInput = document.querySelector('[id*="txtTotalDevolucion"]');
            if (totalInput) {
                totalInput.value = total.toFixed(2);
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            calcularTotalDevolucion();
        });
    </script>
</asp:Content>