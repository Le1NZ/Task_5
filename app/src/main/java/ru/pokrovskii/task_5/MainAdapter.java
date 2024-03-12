package ru.pokrovskii.task_5;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CurrViewHolder> {

    private List<String> data;

    public MainAdapter(List<String> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public CurrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.default_recycler_card, parent, false);

        return new CurrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CurrViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public CurrViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.nameOfCard);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                Intent intent = new Intent(view.getContext(), CategoryActivity.class);
                intent.putExtra("category", data.get(position));
                view.getContext().startActivity(intent);
            });
        }
    }
}
