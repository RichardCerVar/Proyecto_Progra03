<%@ Page Title="Gestión de Devoluciones" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="RegistrarDevolucion.aspx.cs" Inherits="SoftBodWA.RegistrarDevolucion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="container-fluid">
        <div class="d-flex align-items-center mb-4">
            <a href="Inicio.aspx" class="btn btn-outline-secondary me-3">
                <i class="bi bi-arrow-left"></i> Regresar
            </a>
            <h4 class="mb-0">Gestión de Devoluciones</h4>
        </div>
        
        <div class="card p-4 shadow-sm mb-5">
            <h5 class="fw-bold mb-3">Procesar Devolución</h5>

            <div class="mb-3">
                <label for="<%= txtIdVenta.ClientID %>" class="form-label">ID de Venta</label>
                <asp:TextBox ID="txtIdVenta" runat="server" CssClass="form-control" placeholder="Ingrese el ID de la venta"></asp:TextBox>
                <small class="form-text text-muted">Presiona ENTER o haz clic fuera para cargar los productos de la venta.</small>
            </div>

            <div class="mb-4">
                <label for="<%= ddlRazonDevolucion.ClientID %>" class="form-label">Razón de la Devolución</label>
                <asp:DropDownList ID="ddlRazonDevolucion" runat="server" CssClass="form-select">
                    <asp:ListItem Text="Seleccionar razón" Value="" Disabled="true" Selected="true"></asp:ListItem>
                    <asp:ListItem Text="Producto dañado" Value="1"></asp:ListItem>
                    <asp:ListItem Text="Fecha de caducidad vencida" Value="2"></asp:ListItem>
                    <asp:ListItem Text="Error en el pedido" Value="3"></asp:ListItem>
                    <asp:ListItem Text="Cliente ya no lo quiere" Value="4"></asp:ListItem>
                    <asp:ListItem Text="Problema de calidad" Value="5"></asp:ListItem>
                    <asp:ListItem Text="Rotura durante el transporte" Value="13"></asp:ListItem>
                </asp:DropDownList>
            </div>

            <asp:Button ID="btnProcesarDevolucion" runat="server" Text=" Procesar Devolución - S/.0.00" CssClass="btn btn-lg btn-warning text-white w-100" OnClick="btnProcesarDevolucion_Click" />

        </div>

        <div class="mt-4">
            <h5 class="fw-bold mb-3"><i class="bi bi-search me-2"></i> Ventas Recientes (Últimas 24 horas)</h5>

            <div class="list-group-item d-flex justify-content-between align-items-center mb-2 p-3 border rounded">
                <div>
                    <h6 class="mb-1 fw-bold">Venta #1 - Juan Pérez</h6>
                    <small class="text-muted">2025-11-12 10:30</small>
                </div>
                <div class="text-end d-flex align-items-center">
                    <span class="fw-bold text-success me-3">S/.8.80</span>
                    <asp:Button ID="btnVerVenta1" runat="server" Text="Ver" CssClass="btn btn-sm btn-outline-primary" OnClick="btnVerDetalleVenta_Click" CommandArgument="1" />
                </div>
            </div>

            <div class="list-group-item d-flex justify-content-between align-items-center mb-2 p-3 border rounded">
                <div>
                    <h6 class="mb-1 fw-bold">Venta #62 - El Vecino 1</h6>
                    <small class="text-muted">2025-10-07 10:20</small>
                </div>
                <div class="text-end d-flex align-items-center">
                    <span class="fw-bold text-success me-3">S/.68.00</span>
                    <asp:Button ID="btnVerVenta2" runat="server" Text="Ver" CssClass="btn btn-sm btn-outline-primary" OnClick="btnVerDetalleVenta_Click" CommandArgument="62" />
                </div>
            </div>

            <div class="list-group-item d-flex justify-content-between align-items-center mb-2 p-3 border rounded">
                <div>
                    <h6 class="mb-1 fw-bold">Venta #20 - Cliente Genérico</h6>
                    <small class="text-muted">2025-10-03 14:05</small>
                </div>
                <div class="text-end d-flex align-items-center">
                    <span class="fw-bold text-success me-3">S/.8.00</span>
                    <asp:Button ID="btnVerVenta3" runat="server" Text="Ver" CssClass="btn btn-sm btn-outline-primary" OnClick="btnVerDetalleVenta_Click" CommandArgument="20" />
                </div>
            </div>

        </div>
    </div>
    
    <script>
        $(document).ready(function () {
            var txtIdVentaId = '<%= txtIdVenta.ClientID %>';
            var btnProcesarDevolucionId = '<%= btnProcesarDevolucion.ClientID %>';
            
            $('#' + txtIdVentaId).on('blur', function () {
                var idVenta = $(this).val();
                if (idVenta !== '') {
                    $('#' + btnProcesarDevolucionId).text(' Procesar Devolución - S/.45.50');
                    $('#' + btnProcesarDevolucionId).removeClass('btn-warning').addClass('btn-danger');
                } else {
                    $('#' + btnProcesarDevolucionId).text(' Procesar Devolución - S/.0.00');
                    $('#' + btnProcesarDevolucionId).removeClass('btn-danger').addClass('btn-warning');
                }
            });
        });
    </script>
</asp:Content>