package com.example.academiaapp.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "calificaciones",
        foreignKeys = {
                @ForeignKey(entity = Inscripcion.class,
                        parentColumns = "id",
                        childColumns = "inscripcionId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("inscripcionId")})
public class Calificacion {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int inscripcionId;
    private String tipoEvaluacion; // Parcial, Final, Trabajo, etc.
    private double nota;
    private String fecha;
    private String observaciones;

    // Constructor
    public Calificacion(int inscripcionId, String tipoEvaluacion, double nota, String fecha, String observaciones) {
        this.inscripcionId = inscripcionId;
        this.tipoEvaluacion = tipoEvaluacion;
        this.nota = nota;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(int inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
