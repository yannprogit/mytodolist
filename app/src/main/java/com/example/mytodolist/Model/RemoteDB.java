package com.example.mytodolist.Model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytodolist.Model.Class.TodoList;
import com.example.mytodolist.Model.Class.User;
import com.example.mytodolist.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteDB {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    static public Task<AuthResult> saveUser(String pseudo, String mail, String password) {
        return mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference usersRef = db.getReference("users");
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    //usersRef.child(firebaseUser.getUid()).setValue(newUser.getPseudo());
                    User user = new User(firebaseUser.getUid(), pseudo, mail);
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put(user.getUid(), user);
                    usersRef.updateChildren(userMap);
                }
            }
        });
    }

    static public Task<AuthResult> loginUser(String mail, String password) {
        return mAuth.signInWithEmailAndPassword(mail, password);
    }

    public static void saveTodoLists(ArrayList<TodoList> todoLists) {
        DatabaseReference todoListsRef = db.getReference("todoLists");

        Map<String, Object> todoListsMap = new HashMap<>();

        for (TodoList list : todoLists) {
            todoListsMap.put(list.getId(), list);
        }

        todoListsRef.updateChildren(todoListsMap);
    }

    public static void saveTodoList(TodoList todoList) {
        DatabaseReference todoListsRef = db.getReference("todoLists");

        Map<String, Object> todoListMap = new HashMap<>();

        todoListMap.put(todoList.getId(), todoList);

        todoListsRef.updateChildren(todoListMap);
    }

    public static void getTodoListsByUserId(String userId, ValueEventListener valueEventListener) {
        DatabaseReference todoListsRef = db.getReference("todoLists");

        Query query = todoListsRef.orderByChild("userId").equalTo(userId);
        query.addValueEventListener(valueEventListener);
    }

    public static void getUsers(ValueEventListener valueEventListener) {
        DatabaseReference usersRef = db.getReference("users");
        usersRef.addValueEventListener(valueEventListener);
    }

    public static void deleteTodoList(String todoListId) {
        DatabaseReference todoListRef = FirebaseDatabase.getInstance().getReference("todolists").child(todoListId);

        todoListRef.removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firebase", "TodoList deleted");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firebase", "Error", e);
                    }
                });
    }


    public static String generateId(String ref) {
        DatabaseReference refId = db.getReference(ref);
        return refId.push().getKey();
    }


    public static FirebaseDatabase getDb() {
        return db;
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }
}
