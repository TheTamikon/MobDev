package ru.mirea.donetskaya.mireaproject;

import static ru.mirea.donetskaya.mireaproject.MainActivity.preferences;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = FireActivity.class.getSimpleName();
    private EditText mEmailField;
    private EditText mPasswordField;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        mEmailField = findViewById(R.id.editTextEmail);
        mPasswordField = findViewById(R.id.editTextPassword);
        findViewById(R.id.buttonSign).setOnClickListener(this);
        findViewById(R.id.buttonCreate).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.buttonCreate) {
            createAccount(
                    mEmailField.getText().toString(),
                    mPasswordField.getText().toString()
            );
        } else if (i == R.id.buttonSign) {
            signIn(
                    mEmailField.getText().toString(),
                    mPasswordField.getText().toString()
            );
        }
    }

    private void sendEmailVerification() {
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification();
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }
        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }
        return valid;
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent main = new Intent(
                                this,
                                MainActivity.class
                        );
                        startActivity(main);
                        finish();
                    } else {
                        Log.w(
                                TAG,
                                "signInWithEmail:failure",
                                task.getException()
                        );
                        Toast.makeText(
                                FireActivity.this,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }

    public void signOut() {

        mAuth.signOut();
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(
                                TAG,
                                "createUserWithEmail:failure",
                                task.getException()
                        );
                        Toast.makeText(
                                FireActivity.this,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}
