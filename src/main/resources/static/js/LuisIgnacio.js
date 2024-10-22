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