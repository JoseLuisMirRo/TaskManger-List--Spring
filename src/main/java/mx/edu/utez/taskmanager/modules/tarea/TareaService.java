package mx.edu.utez.taskmanager.modules.tarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.edu.utez.taskmanager.utils.*;

import java.util.LinkedList;
import java.util.List;

@Service
public class TareaService {
    AdministradorTareas administradorTareas = new AdministradorTareas();

    @Autowired
    private CustomResponseEntity customResponseEntity;

    //Guardar Tarea
    public ResponseEntity<?> save(Tarea tarea){
        try{
            administradorTareas.agregarTarea(tarea);
            return customResponseEntity.getOkResponse("Registro exitoso",
                                                        "OK",
                                                        200,
                                                        null
            );
        }catch (Exception e){
            return customResponseEntity.get400Response();
        }
    }

    //Consultar todas las tareas
    public ResponseEntity<?> findAll(){
        List<Tarea> list = new LinkedList<>();
        String message="";
        if(administradorTareas.getTareas().isEmpty()) {
            message = "No hay tareas registradas";
        }else{
            list = administradorTareas.getTareas();
            message = "Consulta exitosa";
        }
        return customResponseEntity.getOkResponse(message,
                                                    "OK",
                                                    200,
                                                    list
        );
    }

    //Cambiar estado de tarea
    public ResponseEntity<?> cambiarEstadoTarea(int posicion){
        try{
            administradorTareas.cambiarEstadoTarea(posicion);
            return customResponseEntity.getOkResponse("Cambio de estado exitoso",
                                                        "OK",
                                                        200,
                                                        null
            );
        }catch (Exception e){
            return customResponseEntity.get400Response();
        }
    }

    //Eliminar tarea
    public ResponseEntity<?> eliminarTarea(int posicion){
        try{
            administradorTareas.eliminarTarea(posicion);
            return customResponseEntity.getOkResponse("Eliminación exitosa",
                                                        "OK",
                                                        200,
                                                        null
            );
        }catch (Exception e){
            return customResponseEntity.get400Response();
        }
    }

    //Consultar numero de tareas pendientes
    public ResponseEntity<?> consultarNumeroTareasPendientes(){
        int count = administradorTareas.consultarNumeroTareasPendientes();
        return customResponseEntity.getOkResponse("Consulta exitosa",
                                                    "OK",
                                                    200,
                                                    count
        );
    }

    //Imprimir lista de tareas pendientes -- Esto revisarlo por que lo ideal sería que el navegador descargue el archivo
    public ResponseEntity<?> imprimirListaTareasPendientes(){
        administradorTareas.imprimirListaTareasPendientes();
        return customResponseEntity.getOkResponse("Impresión exitosa",
                                                    "OK",
                                                    200,
                                                    null
        );
    }


}
