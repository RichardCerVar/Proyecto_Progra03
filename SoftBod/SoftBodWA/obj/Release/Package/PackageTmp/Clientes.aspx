<%@ Page Title="Clientes" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Clientes.aspx.cs" Inherits="SoftBodWA.Clientes" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Scripts/SoftBodScripts/AgregarCliente.js"></script>
    <script src="Scripts/SoftBodScripts/PagoDeuda.js"></script>
    <script src="Scripts/SoftBodScripts/EditarCliente.js"></script>
</asp:Content>

<asp:Content ID="MainContent" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>
    <asp:HiddenField ID="hfClienteIDEliminar" runat="server" />
    <div class="container-fluid px-3 py-3">
        
        <h3 class="fw-bold mb-4">Gestión de Clientes al Fiado</h3>

        <div class="row mb-4">
            
            <div class="col-md-6"> <div class="card shadow-sm border-0">
                    <div class="card-body d-flex align-items-center">
                        <i class="bi bi-credit-card fa-2x text-danger me-3"></i>
                        <div>
                            <p class="mb-0 text-muted">Total Cuentas Fiadas</p>
                            <h4 class="fw-bold text-danger" id="lblTotalDeuda" runat="server">S/0.00</h4>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-md-6"> <div class="card shadow-sm border-0">
                    <div class="card-body d-flex align-items-center">
                        <i class="bi bi-people-fill fa-2x text-primary me-3"></i>
                        <div>
                            <p class="mb-0 text-muted">Clientes Activos</p>
                            <h4 class="fw-bold text-primary" id="lblActivos" runat="server">0</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-4 align-items-center">
            <div class="col-md-6 mb-2">
                <asp:Panel ID="pnlBusqueda" runat="server" DefaultButton="btnBuscar">
                    <div class="input-group">
                        <asp:TextBox ID="txtBuscar" runat="server"
                            CssClass="form-control"
                            placeholder="Buscar cliente por alias..." />
                        <asp:Button ID="btnBuscar" runat="server"
                            Text="Buscar"
                            CssClass="btn btn-primary fw-bold"
                            OnClick="btnBuscar_Click"
                            OnClientClick="this.disabled = true; "
                            UseSubmitBehavior="false"/>
                    </div>
                </asp:Panel>
            </div>

            <div class="col-md-3 mb-2">
                <asp:DropDownList ID="ddlFiltroDeuda" runat="server" CssClass="form-select form-select-sm" AppendDataBoundItems="true">
                    <asp:ListItem Text="Todos los clientes" Value=""></asp:ListItem>
                    <asp:ListItem Text="Con Deuda" Value="ConDeuda"></asp:ListItem>
                    <asp:ListItem Text="Al Día" Value="AlDia"></asp:ListItem>
                </asp:DropDownList>
            </div>

            <div class="col-md-3 mb-2 text-end">
                <asp:Button ID="btnAgregar" runat="server" Text="+ Agregar Cliente" 
                    CssClass="btn btn-primary fw-bold shadow" 
                    OnClick="btnAgregar_Click" OnClientClick="this.disabled = true;"
                    UseSubmitBehavior="false"/>
            </div>
        </div>
        
        <asp:Repeater ID="rptClientes" runat="server" OnItemCommand="rptClientes_ItemCommand" OnItemDataBound="rptClientes_ItemDataBound">
            <ItemTemplate>
                <div class="card mb-3 shadow-sm border-0">
                    <div class="card-body d-flex justify-content-between align-items-center">
                        
                        <div class="d-flex align-items-center">
                            <i class="bi bi-person-circle fa-2x text-primary me-3"></i>
                            <div>
                                <h6 class="mb-0 fw-bold"><%# Eval("alias") %></h6>
                                <small class="text-muted d-block"><%# Eval("nombre") %></small>
                                <small class="text-muted"><i class="bi bi-telephone"></i> <%# Eval("telefono") %></small>
                            </div>
                        </div>

                        <div class="col-auto" style="min-width: 250px;">
                            <div class="d-flex align-items-center justify-content-end gap-2">
        
                                <div class="text-end me-3">
                                    <%# Convert.ToDecimal(Eval("montoDeuda")) > 0 ? 
                                    "<p class='mb-0 fw-bold text-danger'>Deuda: " + Eval("montoDeuda", "S/{0:F2}") + "</p>" :
                                    "<p class='mb-0 fw-bold text-success'>Al Día</p>" %>
            
                                    <small class="text-muted">
                                        <i class="bi bi-calendar"></i> F. Límite: <%# Eval("fechaDePago", "{0:dd/MM/yyyy}") %>
                                    </small>
                                </div>

                                <asp:LinkButton ID="btnPagar" runat="server" 
                                    CssClass="btn btn-outline-success rounded-3 d-flex align-items-center justify-content-center"
                                    Style="width: 45px; height: 45px; border-width: 2px;"
                                    CommandArgument='<%# Eval("alias") + "|" + Eval("montoDeuda") %>' 
                                    CommandName="Pagar"
                                    ToolTip="Registrar Pago">
                                    <i class="fa-solid fa-sack-dollar"></i> </asp:LinkButton>
        
                                <asp:LinkButton ID="btnEditar" runat="server" 
                                    CssClass="btn btn-outline-secondary rounded-3 d-flex align-items-center justify-content-center"
                                    Style="width: 45px; height: 45px; --bs-btn-border-color: #dee2e6; color: #495057;"
                                    CommandArgument='<%# Eval("alias")+ "|" + Eval("telefono") + "|" + Eval("fechaDePago")+ "|" + Eval("clienteId")%>' 
                                    CommandName="Editar"
                                    ToolTip="Editar Cliente">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </asp:LinkButton>
        
                                <asp:LinkButton ID="btnEliminar" runat="server" 
                                    CssClass="btn btn-outline-danger rounded-3 d-flex align-items-center justify-content-center"
                                    Style="width: 45px; height: 45px; border-color: #dc3545; color: #dc3545;"
                                    CommandArgument='<%# Eval("alias") + "|" + Eval("clienteId")%>' 
                                    CommandName="Eliminar"
                                    ToolTip="Eliminar Cliente">
                                    <i class="fa-solid fa-trash-can"></i>
                                </asp:LinkButton>
                            </div>
                        </div>
                    </div>
                </div>
            </ItemTemplate>
            <FooterTemplate>
                <%# (rptClientes.Items.Count == 0) ? "<div class='alert alert-info text-center mt-3 border-0 shadow-sm'>No se encontraron clientes activos.</div>" : string.Empty %>
            </FooterTemplate>
        </asp:Repeater>
    </div>

    <div class="modal fade" id="modalAgregarCliente" tabindex="-1" aria-labelledby="modalAgregarClienteLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header border-0">
                    <h5 class="modal-title fw-bold" id="modalAgregarClienteLabel">Agregar Nuevo Cliente</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                        <ContentTemplate>
                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Nombre Completo"></asp:Label>
                                <asp:TextBox ID="txtNombreCompleto" CssClass="form-control" placeholder="Ej: Juan Pérez" runat="server"></asp:TextBox>
                            </div>

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Alias"></asp:Label>
                                <asp:TextBox ID="txtAlias" CssClass="form-control" placeholder="Ej: juan123" runat="server"></asp:TextBox>
                            </div>

                            <div class="mb-3">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Teléfono"></asp:Label>
                                <asp:TextBox ID="txtTelefono" CssClass="form-control" placeholder="123-456-7890" runat="server"></asp:TextBox>
                            </div>

                            <div class="mb-4">
                                <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Fecha Límite"></asp:Label>
                                <asp:TextBox ID="txtFechaLimite" CssClass="form-control" TextMode="Date" runat="server"></asp:TextBox>
                            </div>

                            <asp:Button ID="btnGuardarCliente" runat="server"
                                CssClass="btn btn-dark w-100 py-2 fw-semibold"
                                Text="Agregar Cliente"
                                OnClick="btnGuardarCliente_Click"
                                OnClientClick="this.disabled = true; this.value='Procesando...';"
                                UseSubmitBehavior="false"/>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="modalPago" tabindex="-1" role="dialog" aria-labelledby="modalPagoLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 class="modal-title fw-bold" id="modalPagoLabel">
                        Registrar Pago - <asp:Label ID="lblAlias" runat="server" />
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">

                    <p class="mb-1">
                        Deuda Actual: <span class="fw-bold">S/.</span>
                        <asp:Label ID="lblDeudaActual" runat="server" CssClass="fw-bold" />
                    </p>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">Monto a Pagar</label>
                        <asp:TextBox ID="txtMontoPagar" runat="server" CssClass="form-control" />
                    </div>

                    <asp:Button ID="btnRegistrarPago" runat="server"
                        Text="Registrar Pago"
                        CssClass="btn btn-success w-100"
                        OnClick="btnRegistrarPago_Click" OnClientClick="this.disabled = true; this.value='Procesando...';"
                        UseSubmitBehavior="false"/>

                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="modalEditarCliente" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content rounded-4 p-4">

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="m-0 fw-bold fs-5">Editar Cliente</h4>

                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body p-0">

                    <asp:Label ID="lblIdClienteEditar" runat="server" Visible="false"></asp:Label>

                    <label class="form-label fw-semibold">Nombre Completo</label>
                    <asp:TextBox ID="txtNombreEditar"
                        runat="server"
                        CssClass="form-control mb-3"
                        placeholder="Juan Pérez"/>

                    <label class="form-label fw-semibold">Alias</label>
                    <asp:TextBox ID="txtAliasEditar"
                        runat="server"
                        CssClass="form-control mb-3"
                        placeholder="juan123"/>

                    <label class="form-label fw-semibold">Teléfono</label>
                    <asp:TextBox ID="txtTelefonoEditar"
                        runat="server"
                        CssClass="form-control mb-3"
                        placeholder="123-456-7890"/>

                    <label class="form-label fw-semibold">Fecha Límite</label>
                    <asp:TextBox ID="txtFechaLimiteEditar"
                        runat="server"
                        TextMode="Date"
                        CssClass="form-control mb-3"/>
                </div>

                <div class="mt-4">
                    <asp:Button ID="btnActualizarCliente"
                        runat="server"
                        Text="Actualizar Cliente"
                        CssClass="btn btn-dark w-100 py-2 fw-semibold rounded-3"
                        OnClick="btnActualizarCliente_Click" 
                        OnClientClick="this.disabled = true; this.value='Procesando...';"
                        UseSubmitBehavior="false"/>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modalEliminarCliente" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content rounded-4 p-4">

                <h4 class="mb-2 fw-bold fs-5">
                    ¿Confirmar eliminación?
                </h4>

                <p class="text-muted small">
                    Esta acción eliminará permanentemente al cliente 
                    "<asp:Label ID="lblAliasEliminar" runat="server"></asp:Label>" 
                    del sistema. Esta acción no se puede deshacer.
                </p>

                <div class="d-flex justify-content-end gap-2 mt-4">

                    <button type="button" class="btn btn-light"
                            data-bs-dismiss="modal">
                        Cancelar
                    </button>

                    <asp:Button ID="btnEliminarConfirmado" runat="server"
                        Text="Eliminar"
                        CssClass="btn btn-danger fw-semibold"
                        OnClick="btnEliminarConfirmado_Click" 
                        OnClientClick="this.disabled = true; this.value='Procesando...';"
                        UseSubmitBehavior="false"/>
                </div>
            </div>
        </div>
    </div>
</asp:Content>