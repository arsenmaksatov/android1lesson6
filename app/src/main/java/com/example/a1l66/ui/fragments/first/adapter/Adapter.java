package com.example.a1l66.ui.fragments.first.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1l66.databinding.HolderListDataBinding;
import com.example.a1l66.listener.OnClickListener;
import com.example.a1l66.model.Model;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.HolderData> {
    public ArrayList<Model> list = new ArrayList<>();
    OnClickListener onClickListener;

    public void setOnItemClickListener(OnClickListener onItemClickListener) {
        this.onClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderData(HolderListDataBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(Model model) {
        this.list.add(model);
        notifyDataSetChanged();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        private HolderListDataBinding binding;

        public HolderData(@NonNull HolderListDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Model model) {
            binding.tvData.setText(model.getData());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onItemClickListener(getAdapterPosition(), model);
                }
            });


        }
    }
}
