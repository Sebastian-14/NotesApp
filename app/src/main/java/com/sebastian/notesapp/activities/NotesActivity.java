package com.sebastian.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sebastian.notesapp.R;
import com.sebastian.notesapp.repositories.NoteRepository;

public class NotesActivity extends AppCompatActivity {

    private EditText titleInput;
    private EditText contentInput;
    private Button registerNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        titleInput = findViewById(R.id.title_input);
        contentInput = findViewById(R.id.content_input);
        registerNoteButton = findViewById(R.id.register_note_button);
        registerNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegister();
            }
        });
    }
    public void callRegister(){
        try {
            String title = titleInput.getText().toString();
            String content = contentInput.getText().toString();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            NoteRepository.create(title,content);
            Toast.makeText(this, "Nota agregada satisfactoriamente", Toast.LENGTH_SHORT).show();

            finish();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
