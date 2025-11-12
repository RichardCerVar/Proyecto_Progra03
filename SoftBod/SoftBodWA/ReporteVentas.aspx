<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ReporteVentas.aspx.cs" Inherits="SoftBodWA.ReporteVentas" %>

<!DOCTYPE html>
<html lang="es">
<head runat="server">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Bodega - Reportes de Ventas</title>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    <style>
        /* [MISMO CSS QUE ANTES, OMITIDO POR ESPACIO] */
        /* Manteniendo la estructura y estilos visuales de la imagen de referencia */
        body { font-family: Arial, sans-serif; margin: 0; background-color: #f0f2f5; padding-top: 20px; padding-bottom: 20px; }
        .report-container { max-width: 1200px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); min-height: calc(100vh - 40px); }
        .report-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; padding-bottom: 15px; }
        .report-header h2 { font-size: 20px; color: #333; margin: 0; }
        .filter-buttons { display: flex; gap: 10px; }
        .btn { padding: 10px 15px; border: none; border-radius: 5px; cursor: pointer; font-size: 14px; display: flex; align-items: center; }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-secondary { background-color: #6c757d; color: white; }
        .btn i { margin-right: 5px; }
        .report-filters { display: flex; gap: 15px; margin-bottom: 20px; align-items: flex-end; }
        .filter-group { display: flex; flex-direction: column; }
        .filter-group label { font-size: 13px; color: #666; margin-bottom: 5px; }
        .filter-group select, .filter-group input[type="text"] { padding: 8px 10px; border: 1px solid #ccc; border-radius: 5px; font-size: 14px; }
        .table-section-title { font-size: 20px; color: #333; margin-bottom: 15px; margin-top: 30px; }
        .table-container { margin-top: 0; overflow-x: auto; }
        .data-table { width: 100%; border-collapse: collapse; background-color: #fff; box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1); border-radius: 8px; }
        .data-table th, .data-table td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #eee; }
        .data-table th { background-color: #f8f9fa; font-weight: bold; color: #333; }
        .data-table tr:hover { background-color: #f1f1f1; }
        .tag { padding: 5px 10px; border-radius: 15px; font-size: 12px; font-weight: bold; text-align: center; display: inline-block; }
        .tag-venta { background-color: #e6ffed; color: #28a745; }
        .tag-devolucion { background-color: #ffe6e6; color: #dc3545; }
        .total-amount-positive { color: #28a745; font-weight: bold; }
        .total-amount-negative { color: #dc3545; font-weight: bold; }
        .summary-section { display: flex; justify-content: space-around; margin-top: 30px; gap: 20px; flex-wrap: wrap; }
        .summary-card { background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); text-align: center; flex: 1; min-width: 250px; }
        .summary-card.total-ventas { background-color: #e6ffe6; border-left: 5px solid #28a745; }
        .summary-card.devoluciones { background-color: #ffe6e6; border-left: 5px solid #dc3545; }
        .summary-card.ventas-netas { background-color: #e0f2f7; border-left: 5px solid #007bff; }
        .summary-card i { font-size: 28px; margin-bottom: 10px; }
        .summary-card .value { font-size: 28px; font-weight: bold; }
        .summary-card .label { font-size: 14px; color: #666; margin-top: 5px; }
        .notification { position: fixed; bottom: 20px; right: 20px; background-color: #28a745; color: white; padding: 15px 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); display: none; align-items: center; gap: 10px; z-index: 1000; }
        .notification i { font-size: 20px; }
        .loading-bar { width: 100%; height: 5px; background-color: #eee; margin-top: 10px; border-radius: 3px; overflow: hidden; display: none; }
        .loading-bar-fill { height: 100%; width: 0%; background-color: #007bff; animation: loading 2s linear forwards; }
        @keyframes loading { 0% { width: 0%; } 100% { width: 100%; } }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="report-container">
            <div class="report-header">
                <h2>Reporte de Ventas</h2>
                <div class="filter-buttons">
                    <button type="button" class="btn btn-primary" id="exportReportBtn">
                        <i class="fas fa-file-export"></i> Exportar Reporte
                    </button>
                    <a href="/Reportes.aspx" style="text-decoration: none" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i> Volver
                    </a>
                </div>
            </div>

            <div id="loadingBar" class="loading-bar">
                <div class="loading-bar-fill"></div>
            </div>

            <div class="report-filters">
                <div class="filter-group">
                    <label for="tipoFecha">Tipo de Fecha</label>
                    <select id="tipoFecha">
                        <option value="diario">Diario</option>
                        <option value="semanal">Semanal</option>
                        <option value="mensual">Mensual</option>
                    </select>
                </div>
                <div class="filter-group">
                    <label for="fecha">Fecha</label>
                    <input type="text" id="fecha" placeholder="mm/dd/yyyy" value="07/10/2025">
                </div>
            </div>

            <h2 class="table-section-title">Detalle de Ventas y Devoluciones (Fecha: 07/10/2025)</h2>
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Tipo</th>
                            <th>Cliente</th>
                            <th>Tipo Pago</th>
                            <th>Total</th>
                            <th>Productos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>15</td>
                            <td>07/10/2025</td>
                            <td>10:20</td>
                            <td><span class="tag tag-venta">Venta</span></td>
                            <td>Cliente Genérico</td>
                            <td>Transferencia</td>
                            <td><span class="total-amount-positive">S/100.00</span></td>
                            <td>
                                Detergente Liquido 1L (x1) - S/5.50<br>
                                Jabon de Tocador (x1) - S/1.20
                            </td>
                        </tr>
                        <tr>
                            <td>16</td>
                            <td>07/10/2025</td>
                            <td>10:20</td>
                            <td><span class="tag tag-venta">Venta</span></td>
                            <td>Cliente Genérico</td>
                            <td>Efectivo</td>
                            <td><span class="total-amount-positive">S/50.00</span></td>
                            <td>
                                Atún en Lata (x1) - S/2.00
                            </td>
                        </tr>
                        <tr>
                            <td>62</td>
                            <td>07/10/2025</td>
                            <td>10:20</td>
                            <td><span class="tag tag-venta">Venta Fiada</span></td>
                            <td>El Vecino 1</td>
                            <td>Transferencia</td>
                            <td><span class="total-amount-positive">S/68.00</span></td>
                            <td>
                                Pila AA Duradera (4u) (x5) - S/65.00<br>
                                Agua Mineral 1L (x2) - S/3.00
                            </td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>07/10/2025</td>
                            <td>17:00</td>
                            <td><span class="tag tag-devolucion">Devolución</span></td>
                            <td>-</td>
                            <td>-</td>
                            <td><span class="total-amount-negative">S/6.50</span></td>
                            <td>
                                Papas Fritas Grandes (x1) - S/4.00<br>
                                Torta de Chocolate (porc) (x2) - S/10.00
                            </td>
                        </tr>
                         <tr>
                            <td>17</td>
                            <td>07/10/2025</td>
                            <td>10:20</td>
                            <td><span class="tag tag-venta">Venta</span></td>
                            <td>Cliente Genérico</td>
                            <td>Transferencia</td>
                            <td><span class="total-amount-positive">S/12.30</span></td>
                            <td>Manzanas Rojas (x1) - S/3.50<br>Atún en Lata (x1) - S/3.00</td>
                        </tr>
                        <tr>
                            <td>18</td>
                            <td>07/10/2025</td>
                            <td>10:20</td>
                            <td><span class="tag tag-venta">Venta</span></td>
                            <td>Cliente Genérico</td>
                            <td>Efectivo</td>
                            <td><span class="total-amount-positive">S/9.90</span></td>
                            <td>Galletas de Vainilla (x1) - S/1.80<br>Yogur Natural (x1) - S/1.00</td>
                        </tr>
                        <tr>
                            <td>16</td>
                            <td>07/10/2025</td>
                            <td>17:00</td>
                            <td><span class="tag tag-devolucion">Devolución</span></td>
                            <td>-</td>
                            <td>-</td>
                            <td><span class="total-amount-negative">S/125.50</span></td>
                            <td>-</td>
                        </tr>
                         <tr>
                            <td>63</td>
                            <td>07/10/2025</td>
                            <td>10:20</td>
                            <td><span class="tag tag-venta">Venta Fiada</span></td>
                            <td>El Vecino 1</td>
                            <td>Transferencia</td>
                            <td><span class="total-amount-positive">S/68.00</span></td>
                            <td>Pila AA Duradera (4u) (x5) - S/65.00<br>Agua Mineral 1L (x2) - S/3.00</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <%--
                Ventas Totales (IDs 15, 16, 17, 18, 19, 62, 63):
                100.00 + 50.00 + 12.30 + 9.90 + 22.15 + 68.00 + 68.00 = 330.35

                Devoluciones (IDs 5, 6, 16, 17):
                6.50 + 30.00 + 125.50 + 125.50 = 287.50 
                
                Ventas Netas:
                330.35 - 287.50 = 42.85
            --%>
            <div class="summary-section">
                <div class="summary-card total-ventas">
                    <i class="fas fa-dollar-sign" style="color: #28a745;"></i>
                    <div class="value">S/330.35</div>
                    <div class="label">Ventas Totales (Brutas)</div>
                </div>
                <div class="summary-card devoluciones">
                    <i class="fas fa-redo" style="color: #dc3545;"></i>
                    <div class="value">S/287.50</div>
                    <div class="label">Devoluciones</div>
                </div>
                <div class="summary-card ventas-netas">
                    <i class="fas fa-chart-line" style="color: #007bff;"></i>
                    <div class="value">S/42.85</div>
                    <div class="label">Ventas Netas</div>
                </div>
            </div>
        </div>

        <div id="successNotification" class="notification">
            <i class="fas fa-check-circle"></i>
            ¡Reporte exportado exitosamente! El reporte ha sido procesado correctamente.
        </div>

        <script>
            $(document).ready(function () {
                // ... (Script de la barra de carga y notificación se mantiene igual)
                $('#exportReportBtn').on('click', function () {
                    $('#loadingBar').show();
                    $('.loading-bar-fill').stop(true, true).css('width', '0%');
                    $('.loading-bar-fill').animate({ width: '100%' }, 2000, function () {
                        $('#loadingBar').hide();
                        $('#successNotification').fadeIn().delay(3000).fadeOut();
                    });
                });
            });
        </script>
    </form>
</body>
</html>