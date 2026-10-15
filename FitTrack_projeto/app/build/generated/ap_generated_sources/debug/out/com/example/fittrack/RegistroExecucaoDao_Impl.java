package com.example.fittrack;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
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
public final class RegistroExecucaoDao_Impl implements RegistroExecucaoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RegistroExecucaoEntity> __insertionAdapterOfRegistroExecucaoEntity;

  private final EntityDeletionOrUpdateAdapter<RegistroExecucaoEntity> __updateAdapterOfRegistroExecucaoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteForTreino;

  public RegistroExecucaoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRegistroExecucaoEntity = new EntityInsertionAdapter<RegistroExecucaoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `registros_execucao` (`id`,`treinoId`,`exercicioNome`,`exercicioGrupo`,`series`,`repeticoes`,`carga`,`concluido`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final RegistroExecucaoEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.treinoId);
        if (entity.exercicioNome == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.exercicioNome);
        }
        if (entity.exercicioGrupo == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.exercicioGrupo);
        }
        statement.bindLong(5, entity.series);
        statement.bindLong(6, entity.repeticoes);
        statement.bindDouble(7, entity.carga);
        final int _tmp = entity.concluido ? 1 : 0;
        statement.bindLong(8, _tmp);
      }
    };
    this.__updateAdapterOfRegistroExecucaoEntity = new EntityDeletionOrUpdateAdapter<RegistroExecucaoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `registros_execucao` SET `id` = ?,`treinoId` = ?,`exercicioNome` = ?,`exercicioGrupo` = ?,`series` = ?,`repeticoes` = ?,`carga` = ?,`concluido` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final RegistroExecucaoEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.treinoId);
        if (entity.exercicioNome == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.exercicioNome);
        }
        if (entity.exercicioGrupo == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.exercicioGrupo);
        }
        statement.bindLong(5, entity.series);
        statement.bindLong(6, entity.repeticoes);
        statement.bindDouble(7, entity.carga);
        final int _tmp = entity.concluido ? 1 : 0;
        statement.bindLong(8, _tmp);
        statement.bindLong(9, entity.id);
      }
    };
    this.__preparedStmtOfDeleteForTreino = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM registros_execucao WHERE treinoId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final List<RegistroExecucaoEntity> registros) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRegistroExecucaoEntity.insert(registros);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final RegistroExecucaoEntity registro) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfRegistroExecucaoEntity.handle(registro);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteForTreino(final int treinoId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteForTreino.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, treinoId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteForTreino.release(_stmt);
    }
  }

  @Override
  public List<RegistroExecucaoEntity> getForTreino(final int treinoId) {
    final String _sql = "SELECT * FROM registros_execucao WHERE treinoId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, treinoId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
      final int _cursorIndexOfExercicioNome = CursorUtil.getColumnIndexOrThrow(_cursor, "exercicioNome");
      final int _cursorIndexOfExercicioGrupo = CursorUtil.getColumnIndexOrThrow(_cursor, "exercicioGrupo");
      final int _cursorIndexOfSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "series");
      final int _cursorIndexOfRepeticoes = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoes");
      final int _cursorIndexOfCarga = CursorUtil.getColumnIndexOrThrow(_cursor, "carga");
      final int _cursorIndexOfConcluido = CursorUtil.getColumnIndexOrThrow(_cursor, "concluido");
      final List<RegistroExecucaoEntity> _result = new ArrayList<RegistroExecucaoEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final RegistroExecucaoEntity _item;
        final int _tmpTreinoId;
        _tmpTreinoId = _cursor.getInt(_cursorIndexOfTreinoId);
        final String _tmpExercicioNome;
        if (_cursor.isNull(_cursorIndexOfExercicioNome)) {
          _tmpExercicioNome = null;
        } else {
          _tmpExercicioNome = _cursor.getString(_cursorIndexOfExercicioNome);
        }
        final String _tmpExercicioGrupo;
        if (_cursor.isNull(_cursorIndexOfExercicioGrupo)) {
          _tmpExercicioGrupo = null;
        } else {
          _tmpExercicioGrupo = _cursor.getString(_cursorIndexOfExercicioGrupo);
        }
        final int _tmpSeries;
        _tmpSeries = _cursor.getInt(_cursorIndexOfSeries);
        final int _tmpRepeticoes;
        _tmpRepeticoes = _cursor.getInt(_cursorIndexOfRepeticoes);
        final double _tmpCarga;
        _tmpCarga = _cursor.getDouble(_cursorIndexOfCarga);
        final boolean _tmpConcluido;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfConcluido);
        _tmpConcluido = _tmp != 0;
        _item = new RegistroExecucaoEntity(_tmpTreinoId,_tmpExercicioNome,_tmpExercicioGrupo,_tmpSeries,_tmpRepeticoes,_tmpCarga,_tmpConcluido);
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
