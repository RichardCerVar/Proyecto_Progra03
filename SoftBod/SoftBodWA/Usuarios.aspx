<%@ Page Title="" Language="C#" MasterPageFile="~/SoftBod.Master" AutoEventWireup="true" CodeBehind="Usuarios.aspx.cs" Inherits="SoftBodWA.Usuarios" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-4">
        <h2 class="text-center mb-4">Gestión de Usuarios</h2>

        <div class="d-flex justify-content-end mb-3">
            <button type="button" class="btn btn-secondary">+ Nuevo Usuario</button>
        </div>

        <table class="table table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Usuario</th>
                    <th>Rol</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>U001</td>
                    <td>admin</td>
                    <td>Administrador</td>
                    <td><span class="badge bg-success">Activo</span></td>
                    <td>
                        <button class="btn btn-sm btn-warning">Editar</button>
                        <button class="btn btn-sm btn-danger">Desactivar</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</asp:Content>