package mx.edu.utez.taskmanager.modules.tarea;

import java.time.LocalDate;

public class Tarea {

    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private boolean pendiente;

    // Constructor principal
    public Tarea(String nombre, String descripcion, LocalDate fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.pendiente = true; // Por defecto, las tareas son pendientes
    }

    // Constructor vacío, útil para frameworks como Spring
    public Tarea() {
        this.pendiente = true; // Por defecto, todas las tareas son pendientes
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    // Método toString para imprimir la tarea de manera legible
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nDescripción: " + descripcion + "\nCompletada: " + (pendiente ? "No" : "Sí") + "\nFecha: " + fecha;
    }
}
