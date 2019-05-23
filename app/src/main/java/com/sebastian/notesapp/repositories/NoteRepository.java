package com.sebastian.notesapp.repositories;

import com.orm.SugarRecord;
import com.sebastian.notesapp.models.Note;

import java.util.List;

public class NoteRepository {

    public static List<Note> list(){
        List<Note> notes = SugarRecord.listAll(Note.class);
        return notes;
    }


    public static void create(String titulo, String contenido){
        //Creando la nota
        Note note = new Note(titulo,contenido);
        SugarRecord.save(note);
    }

}
