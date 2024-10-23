// Función para cambiar el estado de una tarea
const cambiarEstadoTarea = async (posicion) => {
    await fetch(`${endpoint}/${posicion}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cambiar el estado de la tarea');
            }
            return response.json(); // Parsear la respuesta como JSON
        })
        .then(data => {
            alert('Estado de la tarea cambiado exitosamente'); // Mensaje de éxito
            loadTable(); // Recargar la tabla de tareas


        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un problema al cambiar el estado de la tarea'); // Mensaje de error
        });
};

document.getElementById('formBuscarTarea').addEventListener('submit', async function (e) {
    e.preventDefault(); // Evitar que el formulario se envíe normalmente

    const nombreTarea = document.getElementById('nombreTarea').value.trim(); // Obtener el nombre de la tarea

    if (!nombreTarea) {
        alert("Por favor, ingresa un nombre de tarea.");
        return;
    }

    try {
        // Hacer una petición GET para verificar si la tarea está pendiente
        const response = await fetch(endpoint + `/verificar/nombre/${nombreTarea}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error al verificar la tarea');
        }

        const data = await response.text(); // Obtener el mensaje del servidor

        // Mostrar el resultado en una alerta o puedes manipular el DOM para mostrar el mensaje
        alert(data);

    } catch (error) {
        console.error('Error:', error);
        alert('Hubo un problema al verificar la tarea');
    }
});