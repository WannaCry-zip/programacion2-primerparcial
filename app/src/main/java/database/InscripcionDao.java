package com.example.academiaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InscripcionDao {

    @Insert
    long insert(Inscripcion inscripcion);

    @Update
    void update(Inscripcion inscripcion);

    @Delete
    void delete(Inscripcion inscripcion);

    @Query("SELECT * FROM inscripciones ORDER BY fecha DESC")
    LiveData<List<Inscripcion>> getAllInscripciones();

    @Query("SELECT * FROM inscripciones WHERE estudianteId = :estudianteId")
    LiveData<List<Inscripcion>> getInscripcionesByEstudiante(int estudianteId);

    @Query("SELECT * FROM inscripciones WHERE materiaId = :materiaId")
    LiveData<List<Inscripcion>> getInscripcionesByMateria(int materiaId);

    @Query("DELETE FROM inscripciones")
    void deleteAll();
}
