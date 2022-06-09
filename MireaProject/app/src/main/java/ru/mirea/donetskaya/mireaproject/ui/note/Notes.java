package ru.mirea.donetskaya.mireaproject.ui.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int nid;

    @ColumnInfo(name = "Name")
    public String toDo;

    @ColumnInfo(name = "Text")
    public String when;
}