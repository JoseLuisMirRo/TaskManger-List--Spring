package mx.edu.utez.taskmanager.tarea;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private AdministradorTareas adminTareas = new AdministradorTareas();
    //sirve
    @PostMapping("/agregar")
    public String agregarTarea(@RequestBody Tarea tarea) {
        adminTareas.agregarTarea(tarea.getNombre(), tarea.getDescripcion(), tarea.getFecha());
        return "Tarea agregada.";
    }
    //sirve
    @PostMapping("/verificar")
    public boolean verificarTarea(@RequestBody Tarea tarea) {
        String nombre = tarea.getNombre();
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tarea es requerido.");
        }
        return adminTareas.verificarTarea(nombre);
    }

    //sirve
    @GetMapping("/numeroPendientes")
    public int consultarNumeroTareasPendientes() {
        return adminTareas.consultarNumeroTareasPendientes();
    }
    //sirve
    @DeleteMapping("/eliminar")
    public String eliminarTarea(@RequestBody Tarea tarea) {
        String nombre = tarea.getNombre();
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tarea es requerido.");
        }
        adminTareas.eliminarTarea(nombre);
        return "Tarea eliminada.";
    }

    //sirve
    @GetMapping("/imprimir")
    public String imprimirListaTareasPendientes() {
        StringBuilder sb = new StringBuilder();
        for (Tarea tarea : adminTareas.getTareas()) { // Cambiado a getTareas()
            if (tarea.isPendiente()) {
                sb.append(tarea.toString()).append("\n-------------\n");
            }
        }
        return sb.toString();
    }
    //pendiente
    @PostMapping("/cambiarEstado")
    public String cambiarEstadoTarea(@RequestBody Tarea tarea) {
        String nombre = tarea.getNombre();
        boolean estado = tarea.isPendiente();

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tarea es requerido.");
        }

        adminTareas.cambiarEstadoTarea(nombre, estado);
        return "Estado de la tarea actualizado.";
    }


    //pendiente
    @GetMapping("/exportar")
    public String exportarTareas() {
        adminTareas.convertirListaATexto();
        return "Tareas exportadas a tareas.txt.";
    }
}
