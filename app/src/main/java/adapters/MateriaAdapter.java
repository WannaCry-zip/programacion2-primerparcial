package com.example.academiaapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academiaapp.database.Materia;
import com.example.academiaapp.databinding.ItemMateriaBinding;

import java.util.ArrayList;
import java.util.List;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder> {

    private List<Materia> materias = new ArrayList<>();
    private OnMateriaClickListener listener;

    public interface OnMateriaClickListener {
        void onEditClick(Materia materia);
        void onDeleteClick(Materia materia);
    }

    public MateriaAdapter(OnMateriaClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMateriaBinding binding = ItemMateriaBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new MateriaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        Materia materia = materias.get(position);
        holder.bind(materia);
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
        notifyDataSetChanged();
    }

    class MateriaViewHolder extends RecyclerView.ViewHolder {
        private ItemMateriaBinding binding;

        public MateriaViewHolder(ItemMateriaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Materia materia) {
            binding.tvNombreMateria.setText(materia.getNombre());
            binding.tvCodigoMateria.setText("Código: " + materia.getCodigo());
            binding.tvCreditos.setText("Créditos: " + materia.getCreditos());
            binding.tvDescripcion.setText(materia.getDescripcion());

            binding.btnEdit.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEditClick(materia);
                }
            });

            binding.btnDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDeleteClick(materia);
                }
            });
        }
    }
}
