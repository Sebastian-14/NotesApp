package com.sebastian.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sebastian.notesapp.R;
import com.sebastian.notesapp.models.Note;
import com.sebastian.notesapp.repositories.NoteRepository;
import  com.sebastian.notesapp.adapter.NoteAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView fullnameText;

    private SharedPreferences sharedPreferences;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REGISTER_FORM_REQUEST = 100;

    private RecyclerView notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullnameText = findViewById(R.id.fullname_text);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        notesList = (RecyclerView) findViewById(R.id.note_list);
        notesList.setLayoutManager(new LinearLayoutManager(this));

        List<Note> notes = NoteRepository.list();
        notesList.setAdapter(new NoteAdapter(notes));
    }

    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, NotesActivity.class), REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        NoteAdapter adapter = (NoteAdapter)notesList.getAdapter();

        List<Note> notes = NoteRepository.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();

    }


    public void callLogout(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
        finish();
    }
}
