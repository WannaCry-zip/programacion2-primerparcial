package com.example.academiaapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.academiaapp.database.AppDatabase;
import com.example.academiaapp.database.Estudiante;
import com.example.academiaapp.database.EstudianteDao;

import java.util.List;

public class EstudianteRepository {

    private EstudianteDao estudianteDao;
    private LiveData<List<Estudiante>> allEstudiantes;

    public EstudianteRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        estudianteDao = database.estudianteDao();
        allEstudiantes = estudianteDao.getAllEstudiantes();
    }

    public LiveData<List<Estudiante>> getAllEstudiantes() {
        return allEstudiantes;
    }

    public void insert(Estudiante estudiante) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            estudianteDao.insert(estudiante);
        });
    }

    public void update(Estudiante estudiante) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            estudianteDao.update(estudiante);
        });
    }

    public void delete(Estudiante estudiante) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            estudianteDao.delete(estudiante);
        });
    }
}
