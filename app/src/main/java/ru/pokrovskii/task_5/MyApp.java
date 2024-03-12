package ru.pokrovskii.task_5;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

public class MyApp extends Application {
    private ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mainViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
        mainViewModel = new MainViewModel();
        viewModelFactory = new ViewModelProvider.NewInstanceFactory() {
            @Override
            public <T extends androidx.lifecycle.ViewModel> T create(Class<T> modelClass) {
                if (modelClass.isAssignableFrom(MainViewModel.class)) {
                    return (T) mainViewModel;
                }
                return super.create(modelClass);
            }
        };
    }

    public ViewModelProvider.Factory getViewModelFactory() {
        return viewModelFactory;
    }

    public MainViewModel getSharedViewModel() {
        return mainViewModel;
    }
}
