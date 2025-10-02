package com.example.academiaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CalificacionDao {

    @Insert
    long insert(Calificacion calificacion);

    @Update
    void update(Calificacion calificacion);

    @Delete
    void delete(Calificacion calificacion);

    @Query("SELECT * FROM calificaciones ORDER BY fecha DESC")
    LiveData<List<Calificacion>> getAllCalificaciones();

    @Query("SELECT * FROM calificaciones WHERE inscripcionId = :inscripcionId")
    LiveData<List<Calificacion>> getCalificacionesByInscripcion(int inscripcionId);

    @Query("DELETE FROM calificaciones")
    void deleteAll();
}
