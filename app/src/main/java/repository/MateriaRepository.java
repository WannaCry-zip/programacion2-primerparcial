package com.example.academiaapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.academiaapp.database.AppDatabase;
import com.example.academiaapp.database.Materia;
import com.example.academiaapp.database.MateriaDao;

import java.util.List;

public class MateriaRepository {

    private MateriaDao materiaDao;
    private LiveData<List<Materia>> allMaterias;

    public MateriaRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        materiaDao = database.materiaDao();
        allMaterias = materiaDao.getAllMaterias();
    }

    public LiveData<List<Materia>> getAllMaterias() {
        return allMaterias;
    }

    public void insert(Materia materia) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            materiaDao.insert(materia);
        });
    }

    public void update(Materia materia) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            materiaDao.update(materia);
        });
    }

    public void delete(Materia materia) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            materiaDao.delete(materia);
        });
    }
}
