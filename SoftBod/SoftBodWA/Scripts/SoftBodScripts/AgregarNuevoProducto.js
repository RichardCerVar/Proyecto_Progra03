function showModalAgregarNuevoProducto() {
    var modalAgregarNuevoProducto = new bootstrap.Modal(document.getElementById('modalAgregarProducto'));
    modalAgregarNuevoProducto.toggle();
}

$(document).ready(function () {
    // Referencias a los campos
    var ddlCategoria = $('#<%= ddlCategoria.ClientID %>');
    var txtNuevaCategoria = $('#<%= txtNuevaCategoria.ClientID %>');

    // Cuando se selecciona una categoría existente
    ddlCategoria.on('change', function () {
        if ($(this).val() !== "" && $(this).val() !== "0") {
            // Si selecciona una categoría, deshabilita el campo de nueva categoría
            txtNuevaCategoria.prop('disabled', true);
            txtNuevaCategoria.val(''); // Limpia el campo
            txtNuevaCategoria.removeClass('is-invalid');
        } else {
            // Si no hay categoría seleccionada, habilita nueva categoría
            txtNuevaCategoria.prop('disabled', false);
        }
    });

    // Cuando escribe en nueva categoría
    txtNuevaCategoria.on('input', function () {
        if ($(this).val().trim() !== "") {
            // Si escribe nueva categoría, deshabilita el dropdown
            ddlCategoria.prop('disabled', true);
            ddlCategoria.val(''); // Limpia la selección
        } else {
            // Si borra el texto, habilita el dropdown
            ddlCategoria.prop('disabled', false);
        }
    });

    // Cuando se abre el modal, resetear los estados
    $('#modalAgregarProducto').on('show.bs.modal', function () {
        ddlCategoria.prop('disabled', false);
        txtNuevaCategoria.prop('disabled', false);
    });

    // Validación antes de enviar
    $('#<%= btnAgregarProducto.ClientID %>').on('click', function (e) {
        var categoriaSeleccionada = ddlCategoria.val();
        var nuevaCategoria = txtNuevaCategoria.val().trim();

        // Validar que al menos uno esté lleno
        if ((categoriaSeleccionada === "" || categoriaSeleccionada === "0") && nuevaCategoria === "") {
            e.preventDefault();
            alert('Debe seleccionar una categoría existente o ingresar una nueva categoría.');
            return false;
        }

        // Validar que solo uno esté lleno
        if ((categoriaSeleccionada !== "" && categoriaSeleccionada !== "0") && nuevaCategoria !== "") {
            e.preventDefault();
            alert('Debe seleccionar solo una opción: categoría existente O nueva categoría, no ambas.');
            return false;
        }
    });
});