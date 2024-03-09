package com.example.mytodolist.Controller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;
import com.example.mytodolist.View.LocalTodoDetailActivity;

import java.util.ArrayList;

public class LocalTodoAdapter extends ArrayAdapter<TodoList> {

    public LocalTodoAdapter(Context context, ArrayList<TodoList> todoLists) {
        super(context, 0, todoLists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.local_todolist, parent, false);
        }

        TodoList currentItem = getItem(position);

        TextView textViewTitle = convertView.findViewById(R.id.todoTitle);
        textViewTitle.setText(currentItem.getTitle());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LocalTodoDetailActivity.class);
                intent.putExtra("selectedTodo", currentItem);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
