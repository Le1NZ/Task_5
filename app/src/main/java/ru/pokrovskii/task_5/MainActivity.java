package ru.pokrovskii.task_5;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ru.pokrovskii.task_5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyApp myApplication = (MyApp) getApplication();
        viewModel = new ViewModelProvider(this, myApplication.getViewModelFactory()).get(MainViewModel.class);

        CreateAdapter();

        binding.btnToScrollView.setOnClickListener(v -> {
            Intent intent = new Intent(this, ScrollViewActivity.class);
            startActivity(intent);
        });

        binding.btnToSpinner.setOnClickListener(v -> {
            Intent intent = new Intent(this, SpinnerActivity.class);
            startActivity(intent);
        });
    }

    private void CreateAdapter() {
        viewModel.initializeData();
        MainAdapter adapter = new MainAdapter(
                new ArrayList<>(viewModel.getData().getValue().keySet())
        );
        binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.mainRecyclerView.setAdapter(adapter);
    }
}