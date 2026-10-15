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
public final class TreinoDao_Impl implements TreinoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TreinoEntity> __insertionAdapterOfTreinoEntity;

  private final EntityDeletionOrUpdateAdapter<TreinoEntity> __updateAdapterOfTreinoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByNome;

  public TreinoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTreinoEntity = new EntityInsertionAdapter<TreinoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `treinos` (`id`,`nome`,`concluido`,`dataExecucao`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final TreinoEntity entity) {
        statement.bindLong(1, entity.id);
        if (entity.nome == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.nome);
        }
        final int _tmp = entity.concluido ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.dataExecucao);
      }
    };
    this.__updateAdapterOfTreinoEntity = new EntityDeletionOrUpdateAdapter<TreinoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `treinos` SET `id` = ?,`nome` = ?,`concluido` = ?,`dataExecucao` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final TreinoEntity entity) {
        statement.bindLong(1, entity.id);
        if (entity.nome == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.nome);
        }
        final int _tmp = entity.concluido ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.dataExecucao);
        statement.bindLong(5, entity.id);
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM treinos WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByNome = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM treinos WHERE nome = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final TreinoEntity treino) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfTreinoEntity.insertAndReturnId(treino);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final TreinoEntity treino) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTreinoEntity.handle(treino);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteById(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteById.release(_stmt);
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
  public List<TreinoEntity> getAll() {
    final String _sql = "SELECT * FROM treinos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfConcluido = CursorUtil.getColumnIndexOrThrow(_cursor, "concluido");
      final int _cursorIndexOfDataExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "dataExecucao");
      final List<TreinoEntity> _result = new ArrayList<TreinoEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final TreinoEntity _item;
        final String _tmpNome;
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _tmpNome = null;
        } else {
          _tmpNome = _cursor.getString(_cursorIndexOfNome);
        }
        final boolean _tmpConcluido;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfConcluido);
        _tmpConcluido = _tmp != 0;
        final long _tmpDataExecucao;
        _tmpDataExecucao = _cursor.getLong(_cursorIndexOfDataExecucao);
        _item = new TreinoEntity(_tmpNome,_tmpConcluido,_tmpDataExecucao);
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
