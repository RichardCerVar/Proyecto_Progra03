<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="SoftBodWA.Login" %>
<!DOCTYPE html>
<html>
<head runat="server">
    <title>Iniciar Sesión - Sistema de Bodega</title>
    <meta charset="utf-8" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', sans-serif;
        }
        .login-card {
            max-width: 420px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }
        .btn-dark {
            background-color: #111827;
            border: none;
        }
        .btn-dark:hover {
            background-color: #0a0f1a;
        }
        .form-control {
            border-radius: 6px;
        }
        .hint {
            font-size: 0.9rem;
            color: #6b7280;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="d-flex justify-content-center align-items-center vh-100">
            <div class="login-card p-4">
                <h4 class="text-center fw-bold mb-2">Sistema de Bodega</h4>
                <p class="text-center text-muted mb-4">Ingrese sus credenciales</p>

                <div class="mb-3">
                    <label class="form-label">Correo electrónico</label>
                    <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" placeholder="ejemplo@correo.com"></asp:TextBox>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>

                    <div class="input-group password-container">
        
                    <asp:TextBox 
                        ID="txtPassword" 
                        runat="server" 
                        CssClass="form-control" 
                        TextMode="Password" 
                        placeholder="********"
                        ClientIDMode="Static"> </asp:TextBox>
        
                    <button 
                        class="btn btn-outline-secondary toggle-password" 
                        type="button" 
                        id="togglePassword"
                        onclick="togglePasswordVisibility('txtPassword'); return false;"> <i class="bi bi-eye"></i> </button>
                    </div>
                </div>
                <div class="d-grid mb-2">
                    <asp:Button ID="btnLogin" runat="server" Text="Iniciar Sesión" CssClass="btn btn-dark fw-bold" OnClick="btnLogin_Click" />
                </div>

                <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger d-block text-center mt-2"></asp:Label>

                <hr />
                <p class="text-center hint mb-1"><strong>Credenciales de prueba:</strong></p>
                <p class="text-center hint mb-0">Dueño: admin@bodega.com / admin123</p>
                <p class="text-center hint">Operario: operario@bodega.com / operario123</p>
            </div>
        </div>
    </form>
    <script src="Scripts/SoftBodScripts/Contrasena.js"></script>

</body>
</html>