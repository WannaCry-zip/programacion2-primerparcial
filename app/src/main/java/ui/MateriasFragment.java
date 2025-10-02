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
import com.example.academiaapp.adapters.MateriaAdapter;
import com.example.academiaapp.database.Materia;
import com.example.academiaapp.databinding.DialogMateriaBinding;
import com.example.academiaapp.databinding.FragmentMateriasBinding;

public class MateriasFragment extends Fragment implements MateriaAdapter.OnMateriaClickListener {

    private FragmentMateriasBinding binding;
    private MateriaViewModel viewModel;
    private MateriaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMateriasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MateriaViewModel.class);

        setupRecyclerView();
        observeData();
        setupFab();
    }

    private void setupRecyclerView() {
        adapter = new MateriaAdapter(this);
        binding.recyclerViewMaterias.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewMaterias.setAdapter(adapter);
    }

    private void observeData() {
        viewModel.getAllMaterias().observe(getViewLifecycleOwner(), materias -> {
            adapter.setMaterias(materias);
        });
    }

    private void setupFab() {
        binding.fabAddMateria.setOnClickListener(v -> showAddMateriaDialog());
    }

    private void showAddMateriaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        DialogMateriaBinding dialogBinding = DialogMateriaBinding.inflate(getLayoutInflater());

        builder.setView(dialogBinding.getRoot())
                .setTitle(R.string.dialog_agregar_materia)
                .setPositiveButton(R.string.btn_guardar, (dialog, which) -> {
                    String nombre = dialogBinding.etNombreMateria.getText().toString().trim();
                    String codigo = dialogBinding.etCodigo.getText().toString().trim();
                    String creditosStr = dialogBinding.etCreditos.getText().toString().trim();
                    String descripcion = dialogBinding.etDescripcion.getText().toString().trim();

                    if (validateInput(nombre, codigo, creditosStr, descripcion)) {
                        int creditos = Integer.parseInt(creditosStr);
                        Materia materia = new Materia(nombre, codigo, creditos, descripcion);
                        viewModel.insert(materia);
                        Toast.makeText(getContext(), R.string.msg_materia_agregada, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.btn_cancelar, null)
                .show();
    }

    @Override
    public void onEditClick(Materia materia) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        DialogMateriaBinding dialogBinding = DialogMateriaBinding.inflate(getLayoutInflater());

        dialogBinding.etNombreMateria.setText(materia.getNombre());
        dialogBinding.etCodigo.setText(materia.getCodigo());
        dialogBinding.etCreditos.setText(String.valueOf(materia.getCreditos()));
        dialogBinding.etDescripcion.setText(materia.getDescripcion());

        builder.setView(dialogBinding.getRoot())
                .setTitle(R.string.dialog_editar_materia)
                .setPositiveButton(R.string.btn_actualizar, (dialog, which) -> {
                    String nombre = dialogBinding.etNombreMateria.getText().toString().trim();
                    String codigo = dialogBinding.etCodigo.getText().toString().trim();
                    String creditosStr = dialogBinding.etCreditos.getText().toString().trim();
                    String descripcion = dialogBinding.etDescripcion.getText().toString().trim();

                    if (validateInput(nombre, codigo, creditosStr, descripcion)) {
                        int creditos = Integer.parseInt(creditosStr);
                        materia.setNombre(nombre);
                        materia.setCodigo(codigo);
                        materia.setCreditos(creditos);
                        materia.setDescripcion(descripcion);
                        viewModel.update(materia);
                        Toast.makeText(getContext(), R.string.msg_materia_actualizada, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.btn_cancelar, null)
                .show();
    }

    @Override
    public void onDeleteClick(Materia materia) {
        String mensaje = getString(R.string.msg_confirmar_eliminar_materia, materia.getNombre());

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.dialog_eliminar_materia)
                .setMessage(mensaje)
                .setPositiveButton(R.string.btn_eliminar, (dialog, which) -> {
                    viewModel.delete(materia);
                    Toast.makeText(getContext(), R.string.msg_materia_eliminada, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.btn_cancelar, null)
                .show();
    }

    private boolean validateInput(String nombre, String codigo, String creditosStr, String descripcion) {
        if (nombre.isEmpty() || codigo.isEmpty() || creditosStr.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(getContext(), R.string.msg_campos_obligatorios, Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int creditos = Integer.parseInt(creditosStr);
            if (creditos <= 0) {
                Toast.makeText(getContext(), R.string.msg_creditos_mayor_cero, Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), R.string.msg_creditos_invalidos, Toast.LENGTH_SHORT).show();
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
