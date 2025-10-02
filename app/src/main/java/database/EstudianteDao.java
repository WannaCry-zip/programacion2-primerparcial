package com.example.academiaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EstudianteDao {

    @Insert
    long insert(Estudiante estudiante);

    @Update
    void update(Estudiante estudiante);

    @Delete
    void delete(Estudiante estudiante);

    @Query("SELECT * FROM estudiantes ORDER BY apellido, nombre")
    LiveData<List<Estudiante>> getAllEstudiantes();

    @Query("SELECT * FROM estudiantes WHERE id = :id")
    LiveData<Estudiante> getEstudianteById(int id);

    @Query("SELECT * FROM estudiantes WHERE email = :email")
    Estudiante getEstudianteByEmail(String email);

    @Query("DELETE FROM estudiantes")
    void deleteAll();
}
