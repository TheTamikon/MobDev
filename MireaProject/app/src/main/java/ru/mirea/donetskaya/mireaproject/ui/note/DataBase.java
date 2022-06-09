package ru.mirea.donetskaya.mireaproject.ui.note;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract RoomDao notesDao();
}
