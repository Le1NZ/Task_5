package ru.pokrovskii.task_5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MainViewModel extends ViewModel {

    private MutableLiveData<HashMap<String, ArrayList<String>>> data;

    public void initializeData() {
        data = new MutableLiveData<>();
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        hashMap.put("Apples", new ArrayList<>());
        hashMap.put("Oranges", new ArrayList<>());
        hashMap.put("Pies", new ArrayList<>());
        hashMap.put("Wheels", new ArrayList<>());
        data.setValue(hashMap);
    }

    public LiveData<HashMap<String, ArrayList<String>>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }

    public void addToData(String category, String object) {
        HashMap<String, ArrayList<String>> currData = data.getValue();
        currData.get(category).add(object);
        data.setValue(currData);
    }

    public void removeFromData(String category, int id) {
        HashMap<String, ArrayList<String>> currData = data.getValue();
        currData.get(category).remove(id);
        data.setValue(currData);
    }

}
