﻿<%@ Page Title="" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="RegistrarVenta.aspx.cs" Inherits="SoftBodWA.RegistrarVenta" %>
<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Encabezado -->
    <div class="d-flex justify-content-between align-items-center mb-4">

        <!-- Botón Regresar -->
        <div class="d-flex align-items-center">
            <a href="Inicio.aspx" class="btn btn-outline-secondary me-3">
                <i class="bi bi-arrow-left"></i>
                 <span class="fw-semibold">Regresar</span>
            </a>
            <h4 class="fw-bold mb-0">Gestión de Ventas</h4>
        </div>

        <!-- Total -->
        <div class="bg-primary text-white rounded px-4 py-2 fw-semibold shadow-sm">
            Total: S/. <asp:Label ID="lblTotal" runat="server" Text="0.00"></asp:Label>
        </div>
    </div>

    <!-- Buscador -->
    <div class="row mb-4 align-items-center">
        <div class="col-md-6 mb-2">
           <div class="input-group">
                <asp:TextBox ID="txtBuscarProducto" CssClass="form-control" placeholder="Buscar productos por nombre..." runat="server"></asp:TextBox>
                <asp:Button ID="btnBuscarNombreProducto" CssClass="btn btn-primary" runat="server" Text="Buscar" OnClick="btnBuscarNombreProducto_Click"/>
            </div>
        </div>
        <div class="col-md-3 mb-2">
            <asp:DropDownList ID="ddlCategoriaFiltro" CssClass="form-select" runat="server">
                <asp:ListItem Text="Todas las categorías" Value="0" Selected="True"></asp:ListItem>
                <asp:ListItem Text="Granos" Value="1"></asp:ListItem>
                <asp:ListItem Text="Lácteos" Value="2"></asp:ListItem>
                <asp:ListItem Text="Aceites" Value="3"></asp:ListItem>
            </asp:DropDownList>
        </div>
        
    </div>

    <!-- Contenedor principal -->
    <div class="card p-4 shadow-sm">

            

        <!-- Tabla de productos -->
        <asp:GridView ID="gvProductos" runat="server" CssClass="table table-striped table-hover text-center align-middle" AutoGenerateColumns="False" ShowHeaderWhenEmpty="True">
            <Columns>
                <asp:BoundField DataField="Nombre" />
                <asp:BoundField HeaderText="Stock" DataField="Stock" />
                <asp:BoundField DataField="Precio" DataFormatString="{0:F2}" />
                
            </Columns>
        </asp:GridView>

        <!-- Botón guardar -->
        <div class="text-end mt-3">
            <asp:Button ID="btnRegistrarVenta" runat="server" CssClass="btn btn-primary px-4" Text="Registrar Venta" OnClick="btnRegistrarVenta_Click" />
        </div>
    </div>
</asp:Content>