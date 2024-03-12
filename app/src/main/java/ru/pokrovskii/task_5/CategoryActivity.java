package ru.pokrovskii.task_5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import ru.pokrovskii.task_5.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String currCategory = getIntent().getStringExtra("category");
        binding.category.setText(currCategory);

        MyApp myApplication = (MyApp) getApplication();
        viewModel = new ViewModelProvider(this, myApplication.getViewModelFactory()).get(MainViewModel.class);

        CategoryAdapter adapter = createAdapter(currCategory);

        binding.btnAddCard.setOnClickListener(v -> {
            onAdded(currCategory, adapter);
        });
    }

    private void onAdded(String currCategory, CategoryAdapter adapter) {
        if (!binding.etAddName.getText().toString().isEmpty()) {
            viewModel.addToData(currCategory, binding.etAddName.getText().toString());
            adapter.notifyItemInserted(viewModel.getData().getValue().get(currCategory).size());
        }
    }

    @NonNull
    private CategoryAdapter createAdapter(String currCategory) {
        CategoryAdapter adapter = new CategoryAdapter(
                viewModel,
                currCategory,
                viewModel.getData().getValue().get(currCategory)
        );
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        return adapter;
    }

}