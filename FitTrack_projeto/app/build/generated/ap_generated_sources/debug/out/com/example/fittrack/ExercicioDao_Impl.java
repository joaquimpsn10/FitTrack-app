package com.example.fittrack;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ExercicioDao_Impl implements ExercicioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExercicioEntity> __insertionAdapterOfExercicioEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByNome;

  public ExercicioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExercicioEntity = new EntityInsertionAdapter<ExercicioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `exercicios` (`id`,`nome`,`grupoMuscular`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final ExercicioEntity entity) {
        statement.bindLong(1, entity.id);
        if (entity.nome == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.nome);
        }
        if (entity.grupoMuscular == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.grupoMuscular);
        }
      }
    };
    this.__preparedStmtOfDeleteByNome = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM exercicios WHERE nome = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final ExercicioEntity exercicio) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfExercicioEntity.insertAndReturnId(exercicio);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteByNome(final String nome) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByNome.acquire();
    int _argIndex = 1;
    if (nome == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, nome);
    }
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteByNome.release(_stmt);
    }
  }

  @Override
  public List<ExercicioEntity> getAll() {
    final String _sql = "SELECT * FROM exercicios";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfGrupoMuscular = CursorUtil.getColumnIndexOrThrow(_cursor, "grupoMuscular");
      final List<ExercicioEntity> _result = new ArrayList<ExercicioEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final ExercicioEntity _item;
        final String _tmpNome;
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _tmpNome = null;
        } else {
          _tmpNome = _cursor.getString(_cursorIndexOfNome);
        }
        final String _tmpGrupoMuscular;
        if (_cursor.isNull(_cursorIndexOfGrupoMuscular)) {
          _tmpGrupoMuscular = null;
        } else {
          _tmpGrupoMuscular = _cursor.getString(_cursorIndexOfGrupoMuscular);
        }
        _item = new ExercicioEntity(_tmpNome,_tmpGrupoMuscular);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
