package com.example.mytodolist.Controller.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.R;

import java.util.ArrayList;

public class LocalTaskAdapter extends ArrayAdapter<Task> {
    private static LocalDB localdb;

    public LocalTaskAdapter(Context context, ArrayList<Task> tasksList) {
        super(context, 0, tasksList);
        localdb  = new LocalDB(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.local_task, parent, false);
        }

        Task currentItem = getItem(position);

        CheckBox checkBox = convertView.findViewById(R.id.checkBoxTask);
        checkBox.setText(currentItem.getTitle());

        // Réinitialiser l'écouteur d'événements
        checkBox.setOnCheckedChangeListener(null);

        if (currentItem.getValidation() == 1) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("OnEstLa", "Checkbox clicked !");
                // Mettre à jour la valeur de validation lorsque la CheckBox est cliquée
                int newValidation = isChecked ? 1 : 0;
                localdb.updateTaskValidation(currentItem.getId(), newValidation);
            }
        });

        return convertView;
    }
}
