package mx.edu.utez.taskmanager.modules.tarea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class AdministradorTareas {
    private LinkedList<Tarea> listTareas = new LinkedList<>();

    public void agregarTarea(Tarea tarea) {
        listTareas.add(tarea);
    }

    public boolean verificarTarea(String nombre) {
        for (Tarea tarea : listTareas) {
            if (tarea.getNombre().equals(nombre) && tarea.isPendiente()) {
                return true;
            }
        }
        return false;
    }

    public int consultarNumeroTareasPendientes() {
        int count = 0;
        for (Tarea tarea : listTareas) {
            if (tarea.isPendiente()) {
                count++;
            }
        }
        return count;
    }

    public void eliminarTarea(int posicion) {
        listTareas.remove(posicion);
    }

    public LinkedList<Tarea> getTareas() {
        return listTareas;
    }

    public void cambiarEstadoTarea(int posicion) {
        listTareas.get(posicion).setPendiente(!listTareas.get(posicion).isPendiente());
    }

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
}