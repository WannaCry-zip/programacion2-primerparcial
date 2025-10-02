package com.example.academiaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MateriaDao {

    @Insert
    long insert(Materia materia);

    @Update
    void update(Materia materia);

    @Delete
    void delete(Materia materia);

    @Query("SELECT * FROM materias ORDER BY nombre")
    LiveData<List<Materia>> getAllMaterias();

    @Query("SELECT * FROM materias WHERE id = :id")
    LiveData<Materia> getMateriaById(int id);

    @Query("SELECT * FROM materias WHERE codigo = :codigo")
    Materia getMateriaByCodigo(String codigo);

    @Query("DELETE FROM materias")
    void deleteAll();
}
