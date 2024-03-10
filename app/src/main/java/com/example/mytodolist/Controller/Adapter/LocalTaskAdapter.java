package com.example.mytodolist.Controller.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytodolist.Model.Class.Task;
import com.example.mytodolist.Model.LocalDB;
import com.example.mytodolist.R;
import com.example.mytodolist.View.LocalTodoDetailActivity;
import com.example.mytodolist.View.UpdLocalTaskActivity;

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
        ImageButton optionsButton = convertView.findViewById(R.id.localTaskOptions);

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(optionsButton, currentItem);
            }
        });

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
                int newValidation = isChecked ? 1 : 0;
                localdb.updateTaskValidation(currentItem.getId(), newValidation);
            }
        });

        return convertView;
    }

    private void showPopupMenu(View view, Task task) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.local_task_options, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.modifyLocalTaskOption) {
                    Intent intent = new Intent(getContext(), UpdLocalTaskActivity.class);
                    intent.putExtra("selectedTask", task);
                    getContext().startActivity(intent);
                } else if (id == R.id.deleteLocalTaskOption) {
                    localdb.deleteTask(task.getId());
                    ((Activity) getContext()).recreate();
                }
                else {
                    return false;
                }
                return true;
            }
        });

        popupMenu.show();
    }
}
