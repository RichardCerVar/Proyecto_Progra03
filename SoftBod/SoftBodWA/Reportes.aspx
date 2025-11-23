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


                <div class="col-12">
                    <div class="card p-4 mb-3 report-block">
                        <h5 class="fw-semibold mb-3">
                            <i class="fa-solid fa-cart-shopping report-icon me-3 text-primary"></i>
                            Reporte de Ventas
                        </h5>

                        <!-- Aquí luego pones filtros -->
                    </div>
                </div>

                <!-- Reporte de Inventario -->
               <div class="col-12">
                    <div class="card p-4 mb-3 report-block">
                        <h5 class="fw-semibold mb-3">
                            <i class="fa-solid fa-box report-icon text-success me-3"></i>
                                Reporte de Inventario

                        </h5>
                    </div>
                </div>

                <!-- Reporte de Clientes al Fiado -->
                <div class="col-12">
                    <div class="card p-4 mb-3 report-block">
                        <h5 class="fw-semibold mb-3">
                            <i class="fa-solid fa-credit-card report-icon text-warning me-3"></i>
                            Reporte de Clientes al Fiado
                        </h5>

                        <!-- Aquí luego pones filtros -->
                    </div>
                </div>

            </div>
        </div>
    </div>
</asp:Content>