package ru.pokrovskii.task_5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainViewModel viewModel;
    private String category;
    private List<String> data;

    public void updateData(List<String> data) {
        this.data = data;
    }

    public CategoryAdapter(MainViewModel viewModel, String category, List<String> data) {
        this.viewModel = viewModel;
        this.category = category;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: {
                View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.image_recycler_card,
                        parent,
                        false
                );
                return new ImageViewHolder(view);
            }
            case 1: {
                View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycler_card_with_delete,
                        parent,
                        false
                );
                return new CurrViewHolder(view);
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0: {
                ImageViewHolder viewHolder = (ImageViewHolder) holder;
                switch (category) {
                    case "Apples":
                        viewHolder.imageView.setImageResource(R.drawable.apple);
                        break;
                    case "Pies":
                        viewHolder.imageView.setImageResource(R.drawable.pie);
                        break;
                    case "Wheels":
                        viewHolder.imageView.setImageResource(R.drawable.wheel);
                        break;
                    default:
                        viewHolder.imageView.setImageResource(R.drawable.orange);
                        break;
                }
                break;
            }
            case 1: {
                CurrViewHolder viewHolder = (CurrViewHolder) holder;
                viewHolder.textView.setText(data.get(position - 1));
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class CurrViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageButton imageButton;

        public CurrViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.nameOfCard);
            imageButton = itemView.findViewById(R.id.btnDelete);

            imageButton.setOnClickListener(v -> {
                viewModel.removeFromData(category, getAdapterPosition() - 1);
                notifyItemRemoved(getAdapterPosition());
            });
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
