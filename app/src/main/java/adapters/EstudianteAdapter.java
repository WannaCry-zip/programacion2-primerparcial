package com.example.academiaapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academiaapp.database.Estudiante;
import com.example.academiaapp.databinding.ItemEstudianteBinding;

import java.util.ArrayList;
import java.util.List;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder> {

    private List<Estudiante> estudiantes = new ArrayList<>();
    private OnEstudianteClickListener listener;

    public interface OnEstudianteClickListener {
        void onEditClick(Estudiante estudiante);
        void onDeleteClick(Estudiante estudiante);
    }

    public EstudianteAdapter(OnEstudianteClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEstudianteBinding binding = ItemEstudianteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new EstudianteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudianteViewHolder holder, int position) {
        Estudiante estudiante = estudiantes.get(position);
        holder.bind(estudiante);
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
        notifyDataSetChanged();
    }

    class EstudianteViewHolder extends RecyclerView.ViewHolder {
        private ItemEstudianteBinding binding;

        public EstudianteViewHolder(ItemEstudianteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Estudiante estudiante) {
            binding.tvNombreCompleto.setText(estudiante.getNombreCompleto());
            binding.tvEmail.setText(estudiante.getEmail());
            binding.tvTelefono.setText(estudiante.getTelefono());

            binding.btnEdit.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEditClick(estudiante);
                }
            });

            binding.btnDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDeleteClick(estudiante);
                }
            });
        }
    }
}
