function togglePasswordVisibility(fieldID) {
    // 1. Obtiene el campo de entrada (input) por su ID
    const passwordField = document.getElementById(fieldID);

    // 2. Obtiene el botón y el icono asociado para cambiar el símbolo
    const toggleButton = document.getElementById('togglePassword');
    const toggleIcon = toggleButton ? toggleButton.querySelector('i') : null;

    if (!passwordField) {
        console.error(`Error: Campo de contraseña no encontrado con ID: ${fieldID}`);
        return;
    }

    // 3. Alterna el tipo de campo entre 'password' y 'text'
    if (passwordField.type === 'password') {
        // Mostrar la contraseña
        passwordField.type = 'text';

        // Cambiar icono a 'ojo tachado' (ocultar)
        if (toggleIcon) {
            toggleIcon.classList.remove('bi-eye');
            toggleIcon.classList.add('bi-eye-slash');
        }
    } else {
        // Ocultar la contraseña
        passwordField.type = 'password';

        // Cambiar icono a 'ojo abierto' (ver)
        if (toggleIcon) {
            toggleIcon.classList.remove('bi-eye-slash');
            toggleIcon.classList.add('bi-eye');
        }
    }
}