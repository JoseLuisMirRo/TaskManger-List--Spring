let endpoint = 'http://localhost:8080/api/tareas';

document.getElementById('addTaskForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita que se recargue la página

    // Obtener los datos del formulario
    const tarea = {
        nombre: document.getElementById('name').value,
        descripcion: document.getElementById('description').value,
        fecha: document.getElementById('dateTask').value,
        pendiente: true,
    };

    // Enviar los datos al backend mediante fetch
    fetch(endpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(tarea) // Convertir el objeto en JSON
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.text();
        })
        .then(data => {
            alert('Tarea registrada correctamente'); // Mostrar un mensaje con la respuesta
            const modal = bootstrap.Modal.getInstance(document.getElementById('addTaskModal'));
            modal.hide();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un problema al registrar la tarea');
        });

    //Eliminar Tarea
    document.getElementById('cleanTaskForm').addEventListener('submit', function(event) {
        event.preventDefault();
            fetch(endpoint+"/limpiar", {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al eliminar las tareas');
                    }
                    return response.text();
                })
                .then(data => {
                    const modal = bootstrap.Modal.getInstance(document.getElementById('cleanTaskModal'));
                    modal.hide(); // Cerrar el modal de eliminar tareas
                    alert('Lista limpiada exitosamente'); // Mostrar mensaje de éxito
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Hubo un problema al eliminar las tareas');
                });
    });

});