package mx.edu.utez.taskmanager.modules.tarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    @Autowired
    private TareaService tareaService;

    //Consultar todas las tareas
    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return tareaService.findAll();
    }

    //Registrar tarea
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Tarea tarea){
        return tareaService.save(tarea);
    }

    //Cambiar estado de tarea - Enviar numero de posicion de tarea en el arreglo
    @PutMapping("/{posicion}")
    public ResponseEntity<?> cambiarEstadoTarea(@PathVariable int posicion){
        return tareaService.cambiarEstadoTarea(posicion);
    }

    //Eliminar tarea - Enviar posicion en el arreglo de la tarea a eliminar
    @DeleteMapping("/{posicion}")
    public ResponseEntity<?> eliminarTarea(@PathVariable int posicion){
        return tareaService.eliminarTarea(posicion);
    }

    //Consultar tareas pendientes
    @GetMapping("/pendientes")
    public ResponseEntity<?> consultarTareasPendientes(){
        return tareaService.consultarNumeroTareasPendientes();
    }

    // Verificar si una tarea está en la lista y si está pendiente
    @GetMapping("/verificar/{posicion}")
    public ResponseEntity<?> verificarTarea(@PathVariable int posicion){
        return tareaService.verificarTarea(posicion);
    }

    // Imprimir la lista de tareas pendientes
    @GetMapping("/imprimir")
    public ResponseEntity<?> imprimirTareasPendientes(){
        return tareaService.imprimirTareasPendientes();
    }

    // Limpiar la lista de tareas
    @DeleteMapping("/limpiar")
    public ResponseEntity<?> limpiarLista(){
        return tareaService.limpiarLista();
    }

    // Convertir la lista de tareas en un archivo de texto
    @GetMapping("/exportar")
    public ResponseEntity<?> exportarLista(){
        return tareaService.exportarLista();
    }
}
