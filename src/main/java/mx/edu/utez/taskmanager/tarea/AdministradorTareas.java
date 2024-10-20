package mx.edu.utez.taskmanager.tarea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

public class AdministradorTareas {
    private LinkedList<Tarea> tareas;

    public AdministradorTareas() {
        this.tareas = new LinkedList<>();
    }

    public void agregarTarea(String nombre, String descripcion, LocalDate fecha) {
        tareas.add(new Tarea(nombre, descripcion, fecha));
    }

    public boolean verificarTarea(String nombre) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equalsIgnoreCase(nombre) && tarea.isPendiente()) {
                return true;
            }
        }
        return false;
    }

    public int consultarNumeroTareasPendientes() {
        int contador = 0;
        for (Tarea tarea : tareas) {
            if (tarea.isPendiente()) {
                contador++;
            }
        }
        return contador;
    }

    public void eliminarTarea(String nombre) {
        tareas.removeIf(tarea -> tarea.getNombre().equalsIgnoreCase(nombre));
    }

    public void imprimirListaTareasPendientes() {
        for (Tarea tarea : tareas) {
            if (tarea.isPendiente()) {
                System.out.println(tarea);
                System.out.println("-------------");
            }
        }
    }

    public void limpiarLista() {
        tareas.clear();
    }

    public void cambiarEstadoTarea(String nombre, boolean estado) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equalsIgnoreCase(nombre)) {
                tarea.setPendiente(estado); // Asegúrate de que setPendiente esté implementado
                return;
            }
        }
        throw new IllegalArgumentException("Tarea no encontrada: " + nombre);
    }


    public void convertirListaATexto() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tareas.txt"))) {
            for (Tarea tarea : tareas) {
                writer.write(tarea.toString());
                writer.write("\n-------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<Tarea> getTareas() {
        return tareas;
    }
}

