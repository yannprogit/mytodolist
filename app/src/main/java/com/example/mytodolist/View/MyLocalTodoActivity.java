package com.example.mytodolist.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mytodolist.Controller.Adapter.LocalTodoAdapter;
import com.example.mytodolist.Controller.AddLocalTodoController;
import com.example.mytodolist.Controller.LoginController;
import com.example.mytodolist.Controller.MyLocalTodoController;
import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.R;

import java.util.ArrayList;

public class MyLocalTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_local_todo);

        ListView listView = findViewById(R.id.localTodoList);
        ArrayList<TodoList> todoLists = MyLocalTodoController.getTodoLists(this);
        LocalTodoAdapter adapter = new LocalTodoAdapter(this, todoLists);
        listView.setAdapter(adapter);

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyLocalTodoActivity.this);
                builder.setMessage(getString(R.string.quit_confirmation_message))
                        .setTitle(getString(R.string.warning))
                        .setPositiveButton(getString(R.string.yes_button), (dialog, which) -> {
                            finish();
                            dialog.dismiss();
                        })
                        .setNegativeButton(getString(R.string.no_button), (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listView = findViewById(R.id.localTodoList);
        ArrayList<TodoList> todoLists = MyLocalTodoController.getTodoLists(this);
        LocalTodoAdapter adapter = new LocalTodoAdapter(this, todoLists);
        listView.setAdapter(adapter);
    }

    public void clickOnLogout(View view) {
        LoginController.logoutUser(this);
        finish();
    }

    public void clickOnGoToAddLocalTodo(View view) {
        MyLocalTodoController.goToAddLocalTodo(this);
    }

    public void clickOnGoToSharedTodo(View view) {
        /*finish();
        MyLocalTodoController.goToSharedTodo(this);*/
        Toast.makeText(this, this.getString(R.string.non_working), Toast.LENGTH_SHORT).show();
    }

    public void clickOnGoToSharedWithMe(View view) {
        Toast.makeText(this, this.getString(R.string.non_working), Toast.LENGTH_SHORT).show();
    }

}