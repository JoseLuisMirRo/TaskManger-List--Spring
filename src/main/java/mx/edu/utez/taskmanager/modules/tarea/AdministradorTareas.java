package mx.edu.utez.taskmanager.modules.tarea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class AdministradorTareas {
    private LinkedList<Tarea> listTareas = new LinkedList<>();

    // Agregar tarea a la lista
    public void agregarTarea(Tarea tarea) {
        listTareas.add(tarea);
    }

    // Verificar si una tarea está pendiente (por nombre)
    public boolean verificarTarea(String nombre) {
        for (Tarea tarea : listTareas) {
            if (tarea.getNombre().equals(nombre) && tarea.isPendiente()) {
                return true;
            }
        }
        return false;
    }

    // Verificar si una tarea está pendiente (por posición)
    public boolean verificarTarea(int posicion) {
        if (posicion >= 0 && posicion < listTareas.size()) {
            return listTareas.get(posicion).isPendiente();
        }
        return false;
    }

    // Consultar el número de tareas pendientes
    public int consultarNumeroTareasPendientes() {
        int count = 0;
        for (Tarea tarea : listTareas) {
            if (tarea.isPendiente()) {
                count++;
            }
        }
        return count;
    }

    // Eliminar una tarea por su posición en la lista
    public void eliminarTarea(int posicion) {
        if (posicion >= 0 && posicion < listTareas.size()) {
            listTareas.remove(posicion);
        }
    }

    // Obtener la lista de todas las tareas
    public LinkedList<Tarea> getTareas() {
        return listTareas;
    }

    // Cambiar el estado de una tarea (Hecho/Pendiente)
    public void cambiarEstadoTarea(int posicion) {
        if (posicion >= 0 && posicion < listTareas.size()) {
            Tarea tarea = listTareas.get(posicion);
            tarea.setPendiente(!tarea.isPendiente());
        }
    }

    // Imprimir lista de tareas pendientes en un archivo de texto
    public void imprimirListaTareasPendientes() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("tareas_pendientes.txt"));
            for (Tarea tarea : listTareas) {
                if (tarea.isPendiente()) {
                    writer.write(tarea.toString());
                    writer.newLine();
                    writer.write("-------------");
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Limpiar la lista de tareas
    public void limpiarLista() {
        listTareas.clear();
    }

    // Exportar la lista de tareas a un archivo de texto
    public void exportarListaTareas() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("lista_tareas.txt"));
            for (Tarea tarea : listTareas) {
                writer.write(tarea.toString());
                writer.newLine();
                writer.write("-------------");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
