package com.example.academiaapp.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "inscripciones",
        foreignKeys = {
                @ForeignKey(entity = Estudiante.class,
                        parentColumns = "id",
                        childColumns = "estudianteId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Materia.class,
                        parentColumns = "id",
                        childColumns = "materiaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("estudianteId"), @Index("materiaId")})
public class Inscripcion {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int estudianteId;
    private int materiaId;
    private String fecha;
    private String estado; // Activo, Finalizado, Retirado

    // Constructor
    public Inscripcion(int estudianteId, int materiaId, String fecha, String estado) {
        this.estudianteId = estudianteId;
        this.materiaId = materiaId;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
