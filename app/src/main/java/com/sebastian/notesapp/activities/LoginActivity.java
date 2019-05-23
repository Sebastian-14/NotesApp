package com.sebastian.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sebastian.notesapp.R;
import com.sebastian.notesapp.models.User;
import com.sebastian.notesapp.repositories.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText nicknameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nicknameInput = findViewById(R.id.nickname_input);
        passwordInput = findViewById(R.id.password_input);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLogin();
            }
        });

        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegister();
            }
        });

        verifySession();
    }

    private void showRegister(){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void callLogin(){
        String nickname = nicknameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(nickname.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = UserRepository.login(nickname, password);

        if(user == null) {
            Toast.makeText(this, "Email y/o password inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean success = sp.edit()
                .putString("nickname", nickname)
                .putLong("id", user.getId())
                .putBoolean("islogged", true)
                .commit();

        // Go to main
        goMain();

    }

    private void verifySession() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.getBoolean("islogged", false)){
            // Go to main
            goMain();
        }
    }

    private void goMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
