// AjustarStock.js (versión robusta para archivo externo)
document.addEventListener('DOMContentLoaded', function () {

    // Selectores robustos para controles ASP.NET (id final puede cambiar)
    function getByIdEndsWith(suffix) {
        return document.querySelector('[id$="' + suffix + '"]');
    }

    const modalAjustarStock = document.getElementById('modalAjustarStock');
    if (!modalAjustarStock) return; // nada que hacer si no existe

    const btnModeAgregar = getByIdEndsWith('btnModeAgregar') || document.getElementById('btnModeAgregar');
    const btnModeReducir = getByIdEndsWith('btnModeReducir') || document.getElementById('btnModeReducir');

    // El botón ASP.NET renderiza un id distinto, buscamos por sufijo
    const btnEjecutarAjusteStock = getByIdEndsWith('btnEjecutarAjusteStock') || document.getElementById('btnEjecutarAjusteStock');

    // Hidden fields (estos son inputs html, sus ids no cambian, pero por si acaso usamos sufijo)
    const hdnProductoIdAjustar = getByIdEndsWith('hdnProductoIdAjustar') || document.getElementById('hdnProductoIdAjustar');
    const hdnStockMode = getByIdEndsWith('hdnStockMode') || document.getElementById('hdnStockMode');

    // Título del modal
    const modalTitle = document.getElementById('modalAjustarStockLabel') || getByIdEndsWith('modalAjustarStockLabel');

    // Seguridad: si algún elemento crítico falta, logueamos y salimos
    if (!btnModeAgregar || !btnModeReducir || !btnEjecutarAjusteStock || !hdnProductoIdAjustar || !hdnStockMode || !modalTitle) {
        console.warn('AjustarStock.js: faltan elementos del DOM. Comprueba IDs:', {
            btnModeAgregar: !!btnModeAgregar,
            btnModeReducir: !!btnModeReducir,
            btnEjecutar: !!btnEjecutarAjusteStock,
            hdnProducto: !!hdnProductoIdAjustar,
            hdnMode: !!hdnStockMode,
            modalTitle: !!modalTitle
        });
        // No return here si quieres que parte funcione; pero mejor devolver para evitar errores.
    }

    // Manejo cuando el modal se muestra: capturamos el boton trigger
    modalAjustarStock.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        if (!button) return;

        // ASP.NET suele renderizar CommandArgument como data-commandargument
        let productoId = button.getAttribute('data-commandargument') || button.getAttribute('CommandArgument') || button.getAttribute('commandargument');

        // fallback: si el botón tiene data-producto
        if (!productoId && button.dataset && button.dataset.producto) productoId = button.dataset.producto;

        if (hdnProductoIdAjustar && productoId) {
            hdnProductoIdAjustar.value = productoId;
        }

        // set default mode: Agregar
        setMode('Agregar');
    });

    // Añadimos listeners seguros (si existen)
    if (btnModeAgregar) {
        btnModeAgregar.addEventListener('click', function () {
            setMode('Agregar');
        });
    }
    if (btnModeReducir) {
        btnModeReducir.addEventListener('click', function () {
            setMode('Reducir');
        });
    }

    // Función robusta para cambiar modos
    function setMode(mode) {
        if (!btnModeAgregar || !btnModeReducir || !btnEjecutarAjusteStock || !hdnStockMode || !modalTitle) return;

        if (mode === 'Agregar') {
            btnModeAgregar.classList.remove('btn-outline-secondary');
            btnModeAgregar.classList.add('btn-dark');

            btnModeReducir.classList.remove('btn-dark');
            btnModeReducir.classList.add('btn-outline-secondary');

            // Cambia texto del botón ASP.NET (LinkButton renderizado)
            btnEjecutarAjusteStock.textContent = 'Agregar Stock';
            modalTitle.textContent = 'Agregar Stock';
            hdnStockMode.value = 'Agregar';

        } else if (mode === 'Reducir') {
            btnModeReducir.classList.remove('btn-outline-secondary');
            btnModeReducir.classList.add('btn-dark');

            btnModeAgregar.classList.remove('btn-dark');
            btnModeAgregar.classList.add('btn-outline-secondary');

            btnEjecutarAjusteStock.textContent = 'Reducir Stock';
            modalTitle.textContent = 'Reducir Stock';
            hdnStockMode.value = 'Reducir';
        }
    }

});