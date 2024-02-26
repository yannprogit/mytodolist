package com.example.mytodolist.Model;

import androidx.annotation.NonNull;

import com.example.mytodolist.R;
import com.google.android.gms.tasks.OnCompleteListener;
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
        return mAuth.createUserWithEmailAndPassword(newUser.getMail(), newUser.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference usersRef = db.getReference("pseudos");
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    usersRef.child(firebaseUser.getUid()).setValue(newUser.getPseudo());
                }
            }
        });
    }

    static public Task<AuthResult> loginUser(User user) {
        return mAuth.signInWithEmailAndPassword(user.getMail(), user.getPassword());
    }

    public static List<User> getUsers() {
        return users;
    }

    public static FirebaseDatabase getDb() {
        return db;
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }
}
