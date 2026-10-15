package com.example.fittrack;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ExercicioDao _exercicioDao;

  private volatile TreinoDao _treinoDao;

  private volatile RegistroExecucaoDao _registroExecucaoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `exercicios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `grupoMuscular` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `treinos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `concluido` INTEGER NOT NULL, `dataExecucao` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `registros_execucao` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `treinoId` INTEGER NOT NULL, `exercicioNome` TEXT, `exercicioGrupo` TEXT, `series` INTEGER NOT NULL, `repeticoes` INTEGER NOT NULL, `carga` REAL NOT NULL, `concluido` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f2e8c414c0796057b08bf438b47480d0')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `exercicios`");
        db.execSQL("DROP TABLE IF EXISTS `treinos`");
        db.execSQL("DROP TABLE IF EXISTS `registros_execucao`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsExercicios = new HashMap<String, TableInfo.Column>(3);
        _columnsExercicios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("nome", new TableInfo.Column("nome", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("grupoMuscular", new TableInfo.Column("grupoMuscular", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExercicios = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExercicios = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExercicios = new TableInfo("exercicios", _columnsExercicios, _foreignKeysExercicios, _indicesExercicios);
        final TableInfo _existingExercicios = TableInfo.read(db, "exercicios");
        if (!_infoExercicios.equals(_existingExercicios)) {
          return new RoomOpenHelper.ValidationResult(false, "exercicios(com.example.fittrack.ExercicioEntity).\n"
                  + " Expected:\n" + _infoExercicios + "\n"
                  + " Found:\n" + _existingExercicios);
        }
        final HashMap<String, TableInfo.Column> _columnsTreinos = new HashMap<String, TableInfo.Column>(4);
        _columnsTreinos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTreinos.put("nome", new TableInfo.Column("nome", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTreinos.put("concluido", new TableInfo.Column("concluido", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTreinos.put("dataExecucao", new TableInfo.Column("dataExecucao", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTreinos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTreinos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTreinos = new TableInfo("treinos", _columnsTreinos, _foreignKeysTreinos, _indicesTreinos);
        final TableInfo _existingTreinos = TableInfo.read(db, "treinos");
        if (!_infoTreinos.equals(_existingTreinos)) {
          return new RoomOpenHelper.ValidationResult(false, "treinos(com.example.fittrack.TreinoEntity).\n"
                  + " Expected:\n" + _infoTreinos + "\n"
                  + " Found:\n" + _existingTreinos);
        }
        final HashMap<String, TableInfo.Column> _columnsRegistrosExecucao = new HashMap<String, TableInfo.Column>(8);
        _columnsRegistrosExecucao.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosExecucao.put("treinoId", new TableInfo.Column("treinoId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosExecucao.put("exercicioNome", new TableInfo.Column("exercicioNome", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosExecucao.put("exercicioGrupo", new TableInfo.Column("exercicioGrupo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosExecucao.put("series", new TableInfo.Column("series", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosExecucao.put("repeticoes", new TableInfo.Column("repeticoes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosExecucao.put("carga", new TableInfo.Column("carga", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRegistrosExecucao.put("concluido", new TableInfo.Column("concluido", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRegistrosExecucao = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRegistrosExecucao = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRegistrosExecucao = new TableInfo("registros_execucao", _columnsRegistrosExecucao, _foreignKeysRegistrosExecucao, _indicesRegistrosExecucao);
        final TableInfo _existingRegistrosExecucao = TableInfo.read(db, "registros_execucao");
        if (!_infoRegistrosExecucao.equals(_existingRegistrosExecucao)) {
          return new RoomOpenHelper.ValidationResult(false, "registros_execucao(com.example.fittrack.RegistroExecucaoEntity).\n"
                  + " Expected:\n" + _infoRegistrosExecucao + "\n"
                  + " Found:\n" + _existingRegistrosExecucao);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f2e8c414c0796057b08bf438b47480d0", "1227830821b02fa823a2e29daf84454b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "exercicios","treinos","registros_execucao");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `exercicios`");
      _db.execSQL("DELETE FROM `treinos`");
      _db.execSQL("DELETE FROM `registros_execucao`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ExercicioDao.class, ExercicioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TreinoDao.class, TreinoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RegistroExecucaoDao.class, RegistroExecucaoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ExercicioDao exercicioDao() {
    if (_exercicioDao != null) {
      return _exercicioDao;
    } else {
      synchronized(this) {
        if(_exercicioDao == null) {
          _exercicioDao = new ExercicioDao_Impl(this);
        }
        return _exercicioDao;
      }
    }
  }

  @Override
  public TreinoDao treinoDao() {
    if (_treinoDao != null) {
      return _treinoDao;
    } else {
      synchronized(this) {
        if(_treinoDao == null) {
          _treinoDao = new TreinoDao_Impl(this);
        }
        return _treinoDao;
      }
    }
  }

  @Override
  public RegistroExecucaoDao registroExecucaoDao() {
    if (_registroExecucaoDao != null) {
      return _registroExecucaoDao;
    } else {
      synchronized(this) {
        if(_registroExecucaoDao == null) {
          _registroExecucaoDao = new RegistroExecucaoDao_Impl(this);
        }
        return _registroExecucaoDao;
      }
    }
  }
}
