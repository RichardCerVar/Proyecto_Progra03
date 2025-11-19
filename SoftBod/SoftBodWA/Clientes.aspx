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
        
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h4 class="fw-bold mb-0">Gestión de Clientes al Fiado</h4>
            
            <asp:Button ID="btnAgregar" runat="server" Text="+ Agregar Cliente" 
                        CssClass="btn btn-primary fw-bold shadow-sm" 
                        OnClick="btnAgregar_Click" />
        </div>

        <asp:Panel ID="pnlBusqueda" runat="server" DefaultButton="btnBuscar">
            <div class="input-group mb-4">
                <span class="input-group-text bg-white border-end-0">
                    <i class="bi bi-search text-muted"></i>
                </span>
                <!-- El TextBox ya no necesita el border-start-0 ya que el botón lo continuará -->
                <asp:TextBox ID="txtBuscar" runat="server"
                    CssClass="form-control border-0"
                    placeholder="Buscar cliente por alias..." />
                
                <!-- Botón de Búsqueda Dedicado -->
                <asp:Button ID="btnBuscar" runat="server"
                    Text="Buscar"
                    CssClass="btn btn-primary fw-bold"
                    OnClick="btnBuscar_Click" />
            </div>
        </asp:Panel>

        <div class="card border-0 mb-4" style="background: linear-gradient(to right, #fff6f1, #fff9f4);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <i class="bi bi-credit-card fs-3 text-warning"></i>
                    <span class="d-block text-muted small">Total Cuentas Fiadas</span>
                    <h5 class="fw-bold mb-0 text-danger" id="lblTotalDeuda" runat="server">S/0.00</h5>
                </div>
                <div class="text-end">
                    <span class="small text-muted">Clientes Activos</span>
                    <h4 class="fw-bold mb-0" id="lblActivos" runat="server">0</h4>
                </div>
            </div>
        </div>

        <asp:Repeater ID="rptClientes" runat="server" OnItemCommand="rptClientes_ItemCommand">
            <ItemTemplate>
                <div class="card border-0 shadow-sm mb-3">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex align-items-center">
                                <i class="bi bi-person-circle fs-3 text-primary me-2"></i>
                                <div>
                                    <h6 class="mb-0 fw-bold"><%# Eval("alias") %></h6>
                                    <small class="text-muted d-block"><%# Eval("nombre") %></small>
                                    <small class="text-muted"><i class="bi bi-telephone"></i> <%# Eval("telefono") %></small>
                                </div>
                            </div>
                            <div class="text-end">
                                <span class="text-danger fw-bold">Deuda: <%# Eval("montoDeuda", "S/{0:F2}") %></span><br />
                                <small class="text-muted"><i class="bi bi-calendar"></i> Fecha Límite: <%# Eval("fechaDePago", "{0:dd/MM/yyyy}") %></small>
                            </div>
                        </div>

                        <div class="mt-3 d-flex gap-2 justify-content-end">
                            <asp:Button ID="btnPagar"   runat="server" Text="$ Pagar" CssClass="btn btn-success btn-sm" CommandArgument='<%# Eval("alias") + "|" + Eval("montoDeuda") %>'  CommandName="Pagar" />
                            <asp:Button ID="btnEditar" runat="server" Text="✎" CssClass="btn btn-outline-secondary btn-sm" CommandArgument='<%# Eval("alias")+ "|" + Eval("telefono") + "|" + Eval("fechaDePago")+ "|" + Eval("clienteId")%>' CommandName="Editar" />
                            <asp:Button ID="btnEliminar" runat="server" Text="🗑" CssClass="btn btn-outline-danger btn-sm" CommandArgument='<%# Eval("alias") + "|" + Eval("clienteId")%>' CommandName="Eliminar" />
                        </div>
                    </div>
                </div>
            </ItemTemplate>
            <FooterTemplate>
                <%# (rptClientes.Items.Count == 0) ? "<div class='alert alert-info text-center'>No se encontraron clientes activos.</div>" : string.Empty %>
            </FooterTemplate>
        </asp:Repeater>
    </div>

    <!--Agregar cliente-->
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
                                OnClientClick="this.disabled = true; this.innerText='Procesando...';"
                                UseSubmitBehavior="false"/>
                             
                                 
                                     
                         </ContentTemplate>
                     </asp:UpdatePanel>
                 </div>
             </div>
         </div>
     </div>


    <!-- Modal Registrar Pago -->
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
                        OnClick="btnRegistrarPago_Click" />

                </div>

            </div>
        </div>
    </div>

    <!-- Modal Editar Cliente -->
    <div class="modal fade" id="modalEditarCliente" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content" style="border-radius: 14px; padding: 20px 25px;">

                <!-- Header -->
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="m-0" style="font-weight: 600; font-size: 22px;">Editar Cliente</h4>

                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- BODY -->
                <div class="modal-body p-0">

                    <asp:Label ID="lblIdClienteEditar" runat="server" Visible="false"></asp:Label>

                    <label class="form-label" style="font-weight: 600;">Nombre Completo</label>
                    <asp:TextBox ID="txtNombreEditar"
                                 runat="server"
                                 CssClass="form-control mb-3"
                                 placeholder="Juan Pérez"
                                 style="height: 45px; border-radius: 10px;" />

                    <label class="form-label" style="font-weight: 600;">Alias</label>
                    <asp:TextBox ID="txtAliasEditar"
                                 runat="server"
                                 CssClass="form-control mb-3"
                                 placeholder="juan123"
                                 style="height: 45px; border-radius: 10px;" />

                    <label class="form-label" style="font-weight: 600;">Teléfono</label>
                    <asp:TextBox ID="txtTelefonoEditar"
                                 runat="server"
                                 CssClass="form-control mb-3"
                                 placeholder="123-456-7890"
                                 style="height: 45px; border-radius: 10px;" />

                    <label class="form-label" style="font-weight: 600;">Fecha Límite</label>
                    <asp:TextBox ID="txtFechaLimiteEditar"
                                 runat="server"
                                 TextMode="Date"
                                 CssClass="form-control mb-3"
                                 style="height: 45px; border-radius: 10px;" />
                </div>

                <!-- FOOTER -->
                <div class="mt-4">
                    <asp:Button ID="btnActualizarCliente"
                                runat="server"
                                Text="Actualizar Cliente"
                                CssClass="btn w-100"
                                Style="background-color: #0B1537; color: white; font-size: 17px;
                                       height: 48px; border-radius: 10px; font-weight: 600;"
                                OnClick="btnActualizarCliente_Click" 
                                OnClientClick="this.disabled = true; this.innerText='Procesando...';"
                                UseSubmitBehavior="false"/>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Confirmar Eliminación -->
    <div class="modal fade" id="modalEliminarCliente" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content" 
                 style="border-radius: 14px; padding: 25px; max-width: 550px; margin: auto;">

                <!-- Header -->
                <h4 class="mb-2" style="font-weight: 700; font-size: 22px;">
                    ¿Confirmar eliminación?
                </h4>

                <!-- BODY -->
                <p style="color: #6c757d; font-size: 15px; line-height: 1.4;">
                    Esta acción eliminará permanentemente al cliente 
                    "<asp:Label ID="lblAliasEliminar" runat="server"></asp:Label>" 
                    del sistema. Esta acción no se puede deshacer.
                </p>

                

                <!-- BOTONES -->
                <div class="d-flex justify-content-end gap-2 mt-4">

                    <button type="button" class="btn btn-light"
                            data-bs-dismiss="modal"
                            style="border-radius: 8px; padding: 8px 18px;">
                        Cancelar
                    </button>

                    <asp:Button ID="btnEliminarConfirmado" runat="server"
                                Text="Eliminar"
                                CssClass="btn"
                                Style="background-color: #dc3545; color: white; 
                                       border-radius: 8px; padding: 8px 18px; font-weight: 600;"
                                OnClick="btnEliminarConfirmado_Click" 
                                OnClientClick="this.disabled = true; this.innerText='Procesando...';"
                                UseSubmitBehavior="false"/>
                </div>

            </div>
        </div>
    </div>


   

</asp:Content>