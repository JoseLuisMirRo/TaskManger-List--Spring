package mx.edu.utez.taskmanager.modules.tarea;

import java.time.LocalDate;

public class Tarea {

    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private boolean pendiente;

    public Tarea(String nombre, String descripcion, LocalDate fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.pendiente = true; // Por defecto, las tareas son pendientes
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nDescripción: " + descripcion + "\nCompletada: " + (pendiente ? "No" : "Sí") + "\nFecha: " + fecha;
    }
}

