<%@ Page Title="Gestión de Devoluciones"
    Language="C#"
    MasterPageFile="~/SoftBod.Master"
    AutoEventWireup="true"
    CodeBehind="RegistrarDevolucion.aspx.cs"
    Inherits="SoftBodWA.RegistrarDevolucion" %><asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

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

            <!-- 🟢 PANEL DE DETALLES DE VENTA SELECCIONADA (Simulado por JS) -->
            <div id="pnlDetalleVentaCargada" class="border p-3 rounded mb-4 d-none">
                <!-- Información general de la venta -->
                <h6 class="fw-bold mb-3 text-primary" id="lblInfoVenta"></h6>

                <h6 class="fw-bold mb-2 text-danger border-bottom pb-1">Productos a Devolver</h6>
                
                <!-- Contenedor para la lista de productos y botones de cantidad (Simulado) -->
                <div id="productosDevolucionList">
                    <!-- Contenido se llenará con JS -->
                </div>
                
                <p class="text-muted text-center mt-3 d-none" id="noProductosMsg">Selecciona una venta para ver los productos.</p>
            </div>
            <!-- 🔴 FIN PANEL DE DETALLES -->

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

            <asp:Repeater ID="rptVentasRecientes" runat="server">
                <ItemTemplate>
                    <div class="list-group-item d-flex justify-content-between align-items-start mb-2 p-3 border rounded">
                        <div>
                            <h6 class="mb-1 fw-bold">
                                Venta #<%# Eval("ventaId") %> - <%# Eval("clienteNombre") %>
                            </h6>
                            <small class="text-muted"><%# Eval("FechaFormat") %></small>

                            <div class="mt-2 ps-2 border-start">
                                <asp:Repeater ID="rptProductosVenta" runat="server"
                                    DataSource='<%# Eval("productos") %>'>
                                    <ItemTemplate>
                                        <div class="d-flex justify-content-between">
                                            <small class="text-secondary">
                                                <%# Eval("nombre") %> x<%# Eval("cantidad") %>
                                            </small>
                                            <small class="text-secondary">
                                                - S/.<%# (double)Eval("precio") * (int)Eval("cantidad") %>
                                            </small>
                                        </div>
                                    </ItemTemplate>
                                </asp:Repeater>
                            </div>
                        </div>

                        <div class="text-end d-flex align-items-center flex-column">
                            <span class="fw-bold text-success me-3">S/.<%# Eval("TotalFormat") %></span>

                            <div class="mt-2 d-flex">

                                <!-- Botón Seleccionar -->
                                <button type="button"
                                    class="btn btn-sm btn-warning text-white btn-seleccionar-venta"
                                    data-venta-id="<%# Eval("ventaId") %>"
                                    data-cliente="<%# Eval("clienteNombre") %>"
                                    data-total="<%# Eval("total") %>"
                                    data-fecha="<%# Eval("FechaFormat") %>"
                                    data-productos='<%# Eval("ProductosJson") %>'>
                                    Seleccionar
                                </button>
                            </div>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>
    </div>
    
    <script>
        // Global state to track selected items for return (Simulación de carrito)
        var devolucionState = {
            idVenta: null,
            productosOriginales: [], // Productos de la venta
            productosADevolver: {},  // { productoId: cantidad }
        };

        function formatCurrency(value) {
            return 'S/.' + parseFloat(value).toFixed(2);
        }

        // -------------------------------------------------------------------------
        // RENDERIZADO Y CÁLCULO
        // -------------------------------------------------------------------------
        function renderProductosDevolucion() {
            var $list = $('#productosDevolucionList');
            $list.empty();
            var totalDevolucion = 0;
            var txtProcesarDevolucionId = '<%= btnProcesarDevolucion.ClientID %>';

            if (devolucionState.productosOriginales.length === 0) {
                $list.html('<p class="text-muted text-center mt-3">No hay productos disponibles para devolución.</p>');
                return;
            }

            devolucionState.productosOriginales.forEach(function (producto) {
                var cantDevolver = devolucionState.productosADevolver[producto.id] || 0;
                var subtotal = cantDevolver * producto.precio;
                totalDevolucion += subtotal;

                var productHtml = `
                    <div class="d-flex justify-content-between align-items-center border-bottom py-2 bg-light p-2 rounded mb-1">
                        <div>
                            <span class="fw-bold">${producto.nombre}</span> 
                            <span class="badge bg-danger" data-producto-id="${producto.id}">${cantDevolver}</span> 
                            <small class="text-muted">x ${formatCurrency(producto.precio)} (Vendidos: ${producto.cantidad})</small>
                        </div>
                        <div class="text-end d-flex align-items-center">
                            <span class="fw-bold text-danger me-3">${formatCurrency(subtotal)}</span>
                            <button type="button" class="btn btn-sm btn-outline-danger btn-devolver-remove me-1" data-id="${producto.id}" ${cantDevolver <= 0 ? 'disabled' : ''}> - </button>
                            <button type="button" class="btn btn-sm btn-outline-success btn-devolver-add" data-id="${producto.id}" ${cantDevolver >= producto.cantidad ? 'disabled' : ''}> + </button>
                        </div>
                    </div>
                `;
                $list.append(productHtml);
            });

            // Actualizar el botón Procesar Devolución
            $('#' + txtProcesarDevolucionId).text(' Procesar Devolución - ' + formatCurrency(totalDevolucion));

            if (totalDevolucion > 0) {
                $('#' + txtProcesarDevolucionId).removeClass('btn-warning').addClass('btn-danger');
                $('#' + txtProcesarDevolucionId).prop('disabled', false);
            } else {
                $('#' + txtProcesarDevolucionId).removeClass('btn-danger').addClass('btn-warning');
                $('#' + txtProcesarDevolucionId).prop('disabled', true);
            }
        }

        // -------------------------------------------------------------------------
        // MANEJADORES DE EVENTOS
        // -------------------------------------------------------------------------

        // 1. Manejador para los botones de Añadir (+) y Quitar (-) del carrito
        function handleDevolucionChange(e) {
            var $btn = $(e.currentTarget);
            var productoId = $btn.data('id');
            var isAdd = $btn.hasClass('btn-devolver-add');

            var currentCount = devolucionState.productosADevolver[productoId] || 0;
            var producto = devolucionState.productosOriginales.find(p => p.id === productoId);

            if (!producto) return;

            if (isAdd && currentCount < producto.cantidad) {
                devolucionState.productosADevolver[productoId] = currentCount + 1;
            } else if (!isAdd && currentCount > 0) {
                devolucionState.productosADevolver[productoId] = currentCount - 1;
            } else if (!isAdd && currentCount === 0) {
                // Eliminar del estado si llega a 0
                delete devolucionState.productosADevolver[productoId];
            }

            // Si se decrementa a 0, se elimina del objeto
            if (devolucionState.productosADevolver[productoId] === 0) {
                delete devolucionState.productosADevolver[productoId];
            }

            renderProductosDevolucion();
        }

        // 2. Manejador para el botón "Seleccionar" de la lista de ventas
        function seleccionarVenta(e) {
            var $btn = $(e.currentTarget);
            var idVenta = $btn.data('venta-id');
            var cliente = $btn.data('cliente');
            var total = $btn.data('total');
            var fecha = $btn.data('fecha');

            // 🟢 CORRECCIÓN CLAVE: Parsear la cadena JSON a un objeto/array de JS
            var productosJsonString = $btn.data('productos');
            var productos;

            try {
                // Asegurarse de limpiar las entidades HTML que ASP.NET pueda haber puesto
                productos = JSON.parse(productosJsonString.replace(/&quot;/g, '"'));
            } catch (e) {
                console.error("Error al parsear JSON de productos:", e);
                productos = [];
            }

            // 1. APLICAR CORRECCIÓN AQUÍ: Usar el ID de Cliente ya definido en la función ready.

            var txtIdVentaId = '<%= txtIdVenta.ClientID %>'; // Esta evaluación debería funcionar si el bloque JS está al final.
            $('#' + txtIdVentaId).val(idVenta); // Usar el ID que ASP.NET genera.

            // 2. Actualizar el estado global
            devolucionState.idVenta = idVenta;
            devolucionState.productosOriginales = productos;
            devolucionState.productosADevolver = {}; // Resetear el carrito

            // 3. Actualizar el panel de detalles
            $('#lblInfoVenta').html(`Venta #${idVenta} - ${cliente}<br><small>${fecha} | Total: ${formatCurrency(total)}</small>`);
            $('#pnlDetalleVentaCargada').removeClass('d-none');

            // 4. Renderizar los productos a devolver
            renderProductosDevolucion();
        }

        $(document).ready(function () {
            // Inicializar el panel de devolución oculto
            $('#pnlDetalleVentaCargada').addClass('d-none');

            // 1. Asignar manejador al botón "Seleccionar"
            $(document).on('click', '.btn-seleccionar-venta', seleccionarVenta);

            // 2. Asignar manejadores a los botones de añadir/quitar (delegados)
            $(document).on('click', '.btn-devolver-add, .btn-devolver-remove', handleDevolucionChange);

            // 3. Lógica para manejar la entrada manual o borrado del ID de Venta
            var txtIdVentaId = '<%= txtIdVenta.ClientID %>';
            var btnProcesarDevolucionId = '<%= btnProcesarDevolucion.ClientID %>';

            $('#' + txtIdVentaId).on('blur', function () {
                var idVenta = $(this).val();

                // Si el usuario borra el ID, limpiamos el panel
                if (idVenta === '' || isNaN(parseInt(idVenta))) {
                    devolucionState.idVenta = null;
                    devolucionState.productosOriginales = [];
                    devolucionState.productosADevolver = {};
                    $('#pnlDetalleVentaCargada').addClass('d-none');
                    renderProductosDevolucion(); // Limpia el HiddenField y el botón

                    // Restablecer el botón
                    $('#' + btnProcesarDevolucionId).text(' Procesar Devolución - S/.0.00');
                    $('#' + btnProcesarDevolucionId).removeClass('btn-danger').addClass('btn-warning');
                    $('#' + btnProcesarDevolucionId).prop('disabled', true);
                } else {
                    // Simulación de carga: Buscar el ID ingresado en los botones de venta reciente
                    var $ventaBtn = $(`.btn-seleccionar-venta[data-venta-id="${idVenta}"]`);
                    if ($ventaBtn.length) {
                        // Si encontramos la venta, simulamos el click para cargar los datos
                        $ventaBtn.trigger('click');
                    } else {
                        // Si el ID de venta no está en las ventas recientes (simulación de postback/AJAX real fallido)
                        // Podrías poner aquí un mensaje de 'Venta no encontrada'.
                        // Por ahora, dejamos el ID en el campo y no cargamos nada más.
                        // Nota: En un entorno real, aquí harías una llamada a C# para buscar la venta.
                    }
                }
            });
        });
    </script>
</asp:Content>