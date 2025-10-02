package com.example.academiaapp.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.academiaapp.database.Materia;
import com.example.academiaapp.repository.MateriaRepository;

import java.util.List;

public class MateriaViewModel extends AndroidViewModel {

    private MateriaRepository repository;
    private LiveData<List<Materia>> allMaterias;

    public MateriaViewModel(@NonNull Application application) {
        super(application);
        repository = new MateriaRepository(application);
        allMaterias = repository.getAllMaterias();
    }

    public LiveData<List<Materia>> getAllMaterias() {
        return allMaterias;
    }

    public void insert(Materia materia) {
        repository.insert(materia);
    }

    public void update(Materia materia) {
        repository.update(materia);
    }

    public void delete(Materia materia) {
        repository.delete(materia);
    }
}
