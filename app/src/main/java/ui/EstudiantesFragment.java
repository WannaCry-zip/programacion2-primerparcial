package com.example.academiaapp.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.academiaapp.R;
import com.example.academiaapp.adapters.EstudianteAdapter;
import com.example.academiaapp.database.Estudiante;
import com.example.academiaapp.databinding.DialogEstudianteBinding;
import com.example.academiaapp.databinding.FragmentEstudiantesBinding;

public class EstudiantesFragment extends Fragment implements EstudianteAdapter.OnEstudianteClickListener {

    private FragmentEstudiantesBinding binding;
    private EstudianteViewModel viewModel;
    private EstudianteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEstudiantesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);

        setupRecyclerView();
        observeData();
        setupFab();
    }

    private void setupRecyclerView() {
        adapter = new EstudianteAdapter(this);
        binding.recyclerViewEstudiantes.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewEstudiantes.setAdapter(adapter);
    }

    private void observeData() {
        viewModel.getAllEstudiantes().observe(getViewLifecycleOwner(), estudiantes -> {
            adapter.setEstudiantes(estudiantes);
        });
    }

    private void setupFab() {
        binding.fabAddEstudiante.setOnClickListener(v -> showAddEstudianteDialog());
    }

    private void showAddEstudianteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        DialogEstudianteBinding dialogBinding = DialogEstudianteBinding.inflate(getLayoutInflater());

        builder.setView(dialogBinding.getRoot())
                .setTitle(R.string.dialog_agregar_estudiante)
                .setPositiveButton(R.string.btn_guardar, (dialog, which) -> {
                    String nombre = dialogBinding.etNombre.getText().toString().trim();
                    String apellido = dialogBinding.etApellido.getText().toString().trim();
                    String email = dialogBinding.etEmail.getText().toString().trim();
                    String telefono = dialogBinding.etTelefono.getText().toString().trim();

                    if (validateInput(nombre, apellido, email, telefono)) {
                        Estudiante estudiante = new Estudiante(nombre, apellido, email, telefono);
                        viewModel.insert(estudiante);
                        Toast.makeText(getContext(), R.string.msg_estudiante_agregado, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.btn_cancelar, null)
                .show();
    }

    @Override
    public void onEditClick(Estudiante estudiante) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        DialogEstudianteBinding dialogBinding = DialogEstudianteBinding.inflate(getLayoutInflater());

        dialogBinding.etNombre.setText(estudiante.getNombre());
        dialogBinding.etApellido.setText(estudiante.getApellido());
        dialogBinding.etEmail.setText(estudiante.getEmail());
        dialogBinding.etTelefono.setText(estudiante.getTelefono());

        builder.setView(dialogBinding.getRoot())
                .setTitle(R.string.dialog_editar_estudiante)
                .setPositiveButton(R.string.btn_actualizar, (dialog, which) -> {
                    String nombre = dialogBinding.etNombre.getText().toString().trim();
                    String apellido = dialogBinding.etApellido.getText().toString().trim();
                    String email = dialogBinding.etEmail.getText().toString().trim();
                    String telefono = dialogBinding.etTelefono.getText().toString().trim();

                    if (validateInput(nombre, apellido, email, telefono)) {
                        estudiante.setNombre(nombre);
                        estudiante.setApellido(apellido);
                        estudiante.setEmail(email);
                        estudiante.setTelefono(telefono);
                        viewModel.update(estudiante);
                        Toast.makeText(getContext(), R.string.msg_estudiante_actualizado, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.btn_cancelar, null)
                .show();
    }

    @Override
    public void onDeleteClick(Estudiante estudiante) {
        String mensaje = getString(R.string.msg_confirmar_eliminar_estudiante, estudiante.getNombreCompleto());

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.dialog_eliminar_estudiante)
                .setMessage(mensaje)
                .setPositiveButton(R.string.btn_eliminar, (dialog, which) -> {
                    viewModel.delete(estudiante);
                    Toast.makeText(getContext(), R.string.msg_estudiante_eliminado, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.btn_cancelar, null)
                .show();
    }

    private boolean validateInput(String nombre, String apellido, String email, String telefono) {
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(getContext(), R.string.msg_campos_obligatorios, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
