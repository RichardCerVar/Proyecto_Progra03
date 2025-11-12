<%@ Page Title="Reportes" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Reportes.aspx.cs" Inherits="SoftBodWA.Reportes" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphScripts" runat="server">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <style>
        .report-card {
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            cursor: pointer;
        }
        .report-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .report-icon {
            font-size: 1.8rem;
            margin-bottom: 0.5rem;
        }
        .report-container {
            background-color: #fff;
            border-radius: 10px;
            padding: 1.5rem;
            box-shadow: 0 1px 3px rgba(0,0,0,0.05);
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-4">
        <h2 class="fw-bold mb-4">Reportes</h2>

        <div class="report-container">
            <h5 class="mb-3"><i class="fa-solid fa-file-lines me-2 text-primary"></i>Seleccionar Tipo de Reporte</h5>

            <div class="row g-3">
                <!-- Reporte de Ventas -->
                <div class="col-md-6">
                    <div class="card report-card text-center p-4" onclick="window.location.href='ReporteVentas.aspx'">
                        <i class="fa-solid fa-cart-shopping report-icon text-primary"></i>
                        <h6 class="fw-semibold mb-0">Reporte de Ventas</h6>
                    </div>
                </div>

                <!-- Reporte de Inventario -->
                <div class="col-md-6">
                    <div class="card report-card text-center p-4" onclick="window.location.href='ReporteInventario.aspx'">
                        <i class="fa-solid fa-box report-icon text-success"></i>
                        <h6 class="fw-semibold mb-0">Reporte de Inventario</h6>
                    </div>
                </div>

                <!-- Reporte Kardex -->
                <div class="col-md-6">
                    <div class="card report-card text-center p-4" onclick="window.location.href='ReporteKardex.aspx'">
                        <i class="fa-solid fa-chart-line report-icon text-info"></i>
                        <h6 class="fw-semibold mb-0">Reporte Kardex</h6>
                    </div>
                </div>

                <!-- Reporte de Clientes al Fiado -->
                <div class="col-md-6">
                    <div class="card report-card text-center p-4" onclick="window.location.href='ReporteClientesFiado.aspx'">
                        <i class="fa-solid fa-credit-card report-icon text-warning"></i>
                        <h6 class="fw-semibold mb-0">Reporte de Clientes al Fiado</h6>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>