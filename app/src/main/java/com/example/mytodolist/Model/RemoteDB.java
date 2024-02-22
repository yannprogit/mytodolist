package com.example.mytodolist.Model;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RemoteDB {
    private static List<User> users = new ArrayList<User>();
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static Task<Boolean> userExists(String mail, String pseudo) {
        DatabaseReference usersRef = db.getReference("users");
        final TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource<>();
        usersRef.orderByChild("mail").equalTo(mail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    boolean userExists = dataSnapshot.exists();
                    taskCompletionSource.setResult(userExists);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                taskCompletionSource.setResult(false);
            }
        });

        return taskCompletionSource.getTask();
    }

    static public Task<AuthResult> saveUser(User newUser) {
        /*DatabaseReference usersRef = db.getReference("users");
        DatabaseReference id = usersRef.push();
        return usersRef.child(id.getKey()).setValue(newUser);*/
        return mAuth.createUserWithEmailAndPassword(newUser.getMail(), newUser.getPassword());
    }

    public static List<User> getUsers() {
        return users;
    }

    public static FirebaseDatabase getDb() {
        return db;
    }
}
