package ru.mirea.donetskaya.mireaproject.ui.note;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {
    @Query("SELECT * FROM notes")
    List<Notes> getAll();

    @Query("SELECT * FROM notes WHERE nid IN (:ids)")
    List<Notes> loadAllByIds(int[] ids);

    @Query("SELECT * FROM notes WHERE name LIKE :first LIMIT 1")
    Notes findByTask(String first);

    @Insert
    void insert(Notes note);

    @Query("DELETE FROM notes")
    void deleteAll();
}
