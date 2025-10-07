<%@ Page Title="" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Reportes.aspx.cs" Inherits="SoftBodWA.Reportes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-4">
        <h2 class="text-center mb-4">Reportes de la Bodega</h2>

        <div class="row text-center">
            <div class="col-md-6 mb-3">
                <div class="card p-4 shadow-sm">
                    <h5>📊 Ventas Diarias</h5>
                    <p>Resumen de las ventas del día actual.</p>
                    <button class="btn btn-outline-primary">Ver Detalles</button>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <div class="card p-4 shadow-sm">
                    <h5>💰 Productos Más Vendidos</h5>
                    <p>Top 10 productos más vendidos del mes.</p>
                    <button class="btn btn-outline-success">Ver Gráfico</button>
                </div>
            </div>
        </div>
    </div>
</asp:Content>