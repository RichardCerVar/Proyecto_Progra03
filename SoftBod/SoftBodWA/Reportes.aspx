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
                        
    
                        <div class="row g-3 align-items-end">
                            <div class="col-md-2">
                                <label class="form-label">Tipo de Fecha</label>
                                <asp:DropDownList ID="ddlTipoFecha" runat="server" CssClass="form-select"
                                    AutoPostBack="true"
                                    OnSelectedIndexChanged="ddlTipoFecha_SelectedIndexChanged">
                                    <asp:ListItem Text="Diario" Value="Diario" Selected="True"></asp:ListItem>
                                    <asp:ListItem Text="Rango" Value="Rango" Selected="False"></asp:ListItem>
                                    <%-- Si se implementa, se añadirían Semanal, Mensual, etc. --%>
                                </asp:DropDownList>
                            </div>
        
                            <div class="col-md-3" id="divFechaUnica" runat="server">
                                <label class="form-label">Fecha</label>
                                <asp:TextBox ID="txtFecha" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <div class="col-md-3" id="divFechaInicio" runat="server" visible="false">
                                <label class="form-label">Fecha Inicio</label>
                                <asp:TextBox ID="txtFechaInicio" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <div class="col-md-3" id="divFechaFin" runat="server" visible="false">
                                <label class="form-label">Fecha Fin</label>
                                <asp:TextBox ID="txtFechaFin" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger d-block text-center mt-2"></asp:Label>

                            <div class="col-12 text-end">
                                <asp:Button ID="btnExportarReporte" runat="server" Text="Exportar Reporte" OnClick="btnExportarReporteVentas_Click" CssClass="btn btn-warning" />
                            </div>
                        </div>
                        
                    </div>
                </div>

                <!-- Reporte de Inventario -->
               <div class="col-12">
                    <div class="card p-4 mb-3 report-block">
                        <h5 class="fw-semibold mb-3">
                            <i class="fa-solid fa-box report-icon text-success me-3"></i>
                                Reporte de Inventario
                        </h5>

                        
                        <div class="row g-3 align-items-end">
                            
                            
                            <div class="col-12 text-end">
                                <asp:Button ID="Button1" runat="server" Text="Exportar Reporte" OnClick="btnExportarReporteInventario_Click" CssClass="btn btn-warning" />
                            </div>
                        </div>
                        
                    </div>
                </div>

                <!-- Reporte de Clientes al Fiado -->
               <div class="col-12">
                    <div class="card p-4 mb-3 report-block">
                        <h5 class="fw-semibold mb-3">
                            <i class="fa-solid fa-credit-card report-icon text-warning me-3"></i>
                            Reporte de Clientes al Fiado
                        </h5>

                        <div class="row g-3 align-items-end">

                            <!-- Seleccionar Cliente -->
                            <div class="col-md-3">
                                <label class="form-label">Seleccionar Cliente</label>
                                <asp:DropDownList ID="ddlCliente" runat="server" DataTextField="Alias" DataValueField="ClienteId"
                                    CssClass="form-select"></asp:DropDownList>
                            </div>

                            <!-- Tipo de Fecha -->
                            <div class="col-md-2">
                                <label class="form-label">Tipo de Fecha</label>
                                <asp:DropDownList ID="ddlTipoFechaCliente" runat="server" CssClass="form-select"
                                    AutoPostBack="true"
                                    OnSelectedIndexChanged="ddlTipoFechaCliente_SelectedIndexChanged">
                                    <asp:ListItem Text="Diario" Value="Diario" Selected="True" />
                                    <asp:ListItem Text="Rango" Value="Rango" />
                                </asp:DropDownList>
                            </div>

                            <!-- Fecha Única -->
                            <div class="col-md-3" id="divFechaClienteUnica" runat="server">
                                <label class="form-label">Fecha</label>
                                <asp:TextBox ID="txtFechaCliente" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <!-- Fecha Inicio -->
                            <div class="col-md-3" id="divFechaClienteInicio" runat="server" Visible="false">
                                <label class="form-label">Fecha Inicio</label>
                                <asp:TextBox ID="txtFechaClienteInicio" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <!-- Fecha Fin -->
                            <div class="col-md-3" id="divFechaClienteFin" runat="server" Visible="false">
                                <label class="form-label">Fecha Fin</label>
                                <asp:TextBox ID="txtFechaClienteFin" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <asp:Label ID="lblMensaje2" runat="server" CssClass="text-danger d-block text-center mt-2"></asp:Label>
                            <!-- Botones -->
                            <div class="col-12 text-end mt-2">
                                <asp:Button ID="btnExportarReporteCliente" runat="server" Text="Exportar Reporte" OnClick="btnExportarReporteClientes_Click" CssClass="btn btn-warning" />
                            </div>

                        </div>
                    </div>
                </div>



                <div class="col-12">
                    <div class="card p-4 mb-3 report-block">
                        <h5 class="fw-semibold mb-3">
                            <i class="fa-solid fa-receipt report-icon text-primary me-3"></i>
                            Reporte de Operaciones
                        </h5>
        
    
                        <div class="row g-3 align-items-end">
                            <div class="col-md-2">
                                <label class="form-label">Tipo de Fecha</label>
                                <asp:DropDownList ID="ddlTipoFechaOperario" runat="server" CssClass="form-select"
                                    AutoPostBack="true"
                                    OnSelectedIndexChanged="ddlTipoFechaOperario_SelectedIndexChanged">
                                    <asp:ListItem Text="Diario" Value="Diario" Selected="True"></asp:ListItem>
                                    <asp:ListItem Text="Rango" Value="Rango" Selected="False"></asp:ListItem>
                                    <%-- Si se implementa, se añadirían Semanal, Mensual, etc. --%>
                                </asp:DropDownList>
                            </div>
        
                            <div class="col-md-3" id="divFechaOperarioUnica" runat="server">
                                <label class="form-label">Fecha</label>
                                <asp:TextBox ID="txtFechaOperario" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <div class="col-md-3" id="divFechaOperarioInicio" runat="server" visible="false">
                                <label class="form-label">Fecha Inicio</label>
                                <asp:TextBox ID="txtFechaOperarioIncio" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <div class="col-md-3" id="divFechaOperarioFin" runat="server" visible="false">
                                <label class="form-label">Fecha Fin</label>
                                <asp:TextBox ID="txtFechaOperarioFin" runat="server" TextMode="Date" CssClass="form-control"></asp:TextBox>
                            </div>

                            <asp:Label ID="lblMensaje3" runat="server" CssClass="text-danger d-block text-center mt-2"></asp:Label>

                            <div class="col-12 text-end">
                                <asp:Button ID="btnExportarReporteOperario" runat="server" Text="Exportar Reporte" OnClick="btnExportarReporteOperaciones_Click" CssClass="btn btn-warning" />
                            </div>
                        </div>
            
        
                    </div>
                </div>

            </div>
        </div>
                
    </div>
</asp:Content>