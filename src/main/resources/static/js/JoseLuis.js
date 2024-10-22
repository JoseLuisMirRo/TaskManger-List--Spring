let endpoint = 'http://localhost:8080/api/tareas';
let tareasList = [];

// Evento para registrar nueva tarea
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
            loadTable(); // Recargar la tabla de tareas después de agregar una nueva
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un problema al registrar la tarea');
        });
});

// Evento para limpiar todas las tareas
document.getElementById('cleanTaskForm').addEventListener('submit', function(event) {
    event.preventDefault();
    fetch(endpoint + "/limpiar", {
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
            cargarTareas(); // Recargar la tabla de tareas después de eliminarlas
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un problema al eliminar las tareas');
        });
});

//Funcion para generar lista de tareas
const findAllTareas = async () =>{
    await fetch (endpoint, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }).then(response => response.json()).then(response => {
        console.log(response);
        tareasList = response.data;
    }).catch(console.log());
}

//Funcion para cargar tablas de tareas
const loadTable = async () => {
    await findAllTareas(); // Asegúrate de que la lista de tareas esté actualizada

    let tbody = document.getElementById("tasksBody");
    let content = '';

    // Verificar si hay tareas disponibles
    if (tareasList.length > 0) {
        tareasList.forEach((tarea, index) => {

            const estadoBtn = tarea.pendiente
                ? `<button type="button" class="btn text-center" style="background-color: #ECB600;" data-bs-toggle="modal" onclick="cambiarEstadoTarea(${index})" >Pendiente</button>`
                : `<button type="button" class="btn text-center" style="background-color: #98D7C2;" data-bs-toggle="modal" onclick="cambiarEstadoTarea(${index})" >Hecho</button>`;

            content += `
                <tr>
                    <th scope="row">${index + 1}</th>
                    <td>${tarea.nombre}</td>
                    <td>${tarea.descripcion}</td>
                    <td>${tarea.fecha}</td>
                    <td> ${estadoBtn}</td>
                    <td class="text-center fw-light">
                        <button type="button" class="btn ms-auto" style="background-color: #88B2CC;" data-bs-toggle="modal" data-bs-target="#editTaskModal">Editar</button>
                        <button type="button" class="btn ms-auto" style="background-color: #E98973;" data-bs-toggle="modal" data-bs-target="#deleteTaskModal">Eliminar</button>
                    </td>
                </tr>`;
        });
    } else {
        content = `
            <tr>
                <td colspan="6" class="text-center">No hay tareas disponibles</td>
            </tr>`;
    }

    tbody.innerHTML = content;
};
(async () => {
    await findAllTareas();
    await loadTable(); // Asegúrate de llamar a loadTable después de obtener las tareas
})();
