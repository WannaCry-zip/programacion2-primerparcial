package com.example.academiaapp.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.academiaapp.database.Estudiante;
import com.example.academiaapp.repository.EstudianteRepository;

import java.util.List;

public class EstudianteViewModel extends AndroidViewModel {

    private EstudianteRepository repository;
    private LiveData<List<Estudiante>> allEstudiantes;

    public EstudianteViewModel(@NonNull Application application) {
        super(application);
        repository = new EstudianteRepository(application);
        allEstudiantes = repository.getAllEstudiantes();
    }

    public LiveData<List<Estudiante>> getAllEstudiantes() {
        return allEstudiantes;
    }

    public void insert(Estudiante estudiante) {
        repository.insert(estudiante);
    }

    public void update(Estudiante estudiante) {
        repository.update(estudiante);
    }

    public void delete(Estudiante estudiante) {
        repository.delete(estudiante);
    }
}
