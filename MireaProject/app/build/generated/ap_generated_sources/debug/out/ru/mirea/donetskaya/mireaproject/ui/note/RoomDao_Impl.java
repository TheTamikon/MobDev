package ru.mirea.donetskaya.mireaproject.ui.note;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RoomDao_Impl implements RoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Notes> __insertionAdapterOfNotes;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public RoomDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotes = new EntityInsertionAdapter<Notes>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Notes` (`nid`,`Name`,`Text`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Notes value) {
        stmt.bindLong(1, value.nid);
        if (value.toDo == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.toDo);
        }
        if (value.when == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.when);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM notes";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Notes note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotes.insert(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Notes> getAll() {
    final String _sql = "SELECT * FROM notes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNid = CursorUtil.getColumnIndexOrThrow(_cursor, "nid");
      final int _cursorIndexOfToDo = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfWhen = CursorUtil.getColumnIndexOrThrow(_cursor, "Text");
      final List<Notes> _result = new ArrayList<Notes>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Notes _item;
        _item = new Notes();
        _item.nid = _cursor.getInt(_cursorIndexOfNid);
        if (_cursor.isNull(_cursorIndexOfToDo)) {
          _item.toDo = null;
        } else {
          _item.toDo = _cursor.getString(_cursorIndexOfToDo);
        }
        if (_cursor.isNull(_cursorIndexOfWhen)) {
          _item.when = null;
        } else {
          _item.when = _cursor.getString(_cursorIndexOfWhen);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Notes> loadAllByIds(final int[] ids) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM notes WHERE nid IN (");
    final int _inputSize = ids.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int _item : ids) {
      _statement.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNid = CursorUtil.getColumnIndexOrThrow(_cursor, "nid");
      final int _cursorIndexOfToDo = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfWhen = CursorUtil.getColumnIndexOrThrow(_cursor, "Text");
      final List<Notes> _result = new ArrayList<Notes>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Notes _item_1;
        _item_1 = new Notes();
        _item_1.nid = _cursor.getInt(_cursorIndexOfNid);
        if (_cursor.isNull(_cursorIndexOfToDo)) {
          _item_1.toDo = null;
        } else {
          _item_1.toDo = _cursor.getString(_cursorIndexOfToDo);
        }
        if (_cursor.isNull(_cursorIndexOfWhen)) {
          _item_1.when = null;
        } else {
          _item_1.when = _cursor.getString(_cursorIndexOfWhen);
        }
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Notes findByTask(final String first) {
    final String _sql = "SELECT * FROM notes WHERE name LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (first == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, first);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNid = CursorUtil.getColumnIndexOrThrow(_cursor, "nid");
      final int _cursorIndexOfToDo = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfWhen = CursorUtil.getColumnIndexOrThrow(_cursor, "Text");
      final Notes _result;
      if(_cursor.moveToFirst()) {
        _result = new Notes();
        _result.nid = _cursor.getInt(_cursorIndexOfNid);
        if (_cursor.isNull(_cursorIndexOfToDo)) {
          _result.toDo = null;
        } else {
          _result.toDo = _cursor.getString(_cursorIndexOfToDo);
        }
        if (_cursor.isNull(_cursorIndexOfWhen)) {
          _result.when = null;
        } else {
          _result.when = _cursor.getString(_cursorIndexOfWhen);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
