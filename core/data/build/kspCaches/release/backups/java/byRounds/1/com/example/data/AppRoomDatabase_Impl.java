package com.example.data;

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
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppRoomDatabase_Impl extends AppRoomDatabase {
  private volatile IUserDao _iUserDao;

  private volatile IMovieDao _iMovieDao;

  private volatile UserStateDao _userStateDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_table` (`userId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `birthDate` TEXT NOT NULL, `email` TEXT NOT NULL, `password` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `movie_table` (`movieId` INTEGER NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `posterPath` TEXT NOT NULL, `voteAverage` REAL NOT NULL, `releaseDate` TEXT NOT NULL, `voteSelf` INTEGER NOT NULL, `newVote` REAL NOT NULL, PRIMARY KEY(`movieId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `genre_table` (`genreId` INTEGER NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`genreId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `MovieGenreCrossRef` (`movieId` INTEGER NOT NULL, `genreId` INTEGER NOT NULL, PRIMARY KEY(`movieId`, `genreId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `UserGenreCrossRef` (`userId` INTEGER NOT NULL, `genreId` INTEGER NOT NULL, PRIMARY KEY(`userId`, `genreId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_state` (`id` INTEGER NOT NULL, `isLoggedIn` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9b35c4ab5630a92730c59be766b25707')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `user_table`");
        db.execSQL("DROP TABLE IF EXISTS `movie_table`");
        db.execSQL("DROP TABLE IF EXISTS `genre_table`");
        db.execSQL("DROP TABLE IF EXISTS `MovieGenreCrossRef`");
        db.execSQL("DROP TABLE IF EXISTS `UserGenreCrossRef`");
        db.execSQL("DROP TABLE IF EXISTS `user_state`");
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
        final HashMap<String, TableInfo.Column> _columnsUserTable = new HashMap<String, TableInfo.Column>(5);
        _columnsUserTable.put("userId", new TableInfo.Column("userId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("birthDate", new TableInfo.Column("birthDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTable = new TableInfo("user_table", _columnsUserTable, _foreignKeysUserTable, _indicesUserTable);
        final TableInfo _existingUserTable = TableInfo.read(db, "user_table");
        if (!_infoUserTable.equals(_existingUserTable)) {
          return new RoomOpenHelper.ValidationResult(false, "user_table(com.example.model.User).\n"
                  + " Expected:\n" + _infoUserTable + "\n"
                  + " Found:\n" + _existingUserTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieTable = new HashMap<String, TableInfo.Column>(8);
        _columnsMovieTable.put("movieId", new TableInfo.Column("movieId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieTable.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieTable.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieTable.put("posterPath", new TableInfo.Column("posterPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieTable.put("voteAverage", new TableInfo.Column("voteAverage", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieTable.put("releaseDate", new TableInfo.Column("releaseDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieTable.put("voteSelf", new TableInfo.Column("voteSelf", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieTable.put("newVote", new TableInfo.Column("newVote", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieTable = new TableInfo("movie_table", _columnsMovieTable, _foreignKeysMovieTable, _indicesMovieTable);
        final TableInfo _existingMovieTable = TableInfo.read(db, "movie_table");
        if (!_infoMovieTable.equals(_existingMovieTable)) {
          return new RoomOpenHelper.ValidationResult(false, "movie_table(com.example.model.Movie).\n"
                  + " Expected:\n" + _infoMovieTable + "\n"
                  + " Found:\n" + _existingMovieTable);
        }
        final HashMap<String, TableInfo.Column> _columnsGenreTable = new HashMap<String, TableInfo.Column>(2);
        _columnsGenreTable.put("genreId", new TableInfo.Column("genreId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGenreTable.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGenreTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGenreTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGenreTable = new TableInfo("genre_table", _columnsGenreTable, _foreignKeysGenreTable, _indicesGenreTable);
        final TableInfo _existingGenreTable = TableInfo.read(db, "genre_table");
        if (!_infoGenreTable.equals(_existingGenreTable)) {
          return new RoomOpenHelper.ValidationResult(false, "genre_table(com.example.model.Genre).\n"
                  + " Expected:\n" + _infoGenreTable + "\n"
                  + " Found:\n" + _existingGenreTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieGenreCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsMovieGenreCrossRef.put("movieId", new TableInfo.Column("movieId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieGenreCrossRef.put("genreId", new TableInfo.Column("genreId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieGenreCrossRef = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieGenreCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieGenreCrossRef = new TableInfo("MovieGenreCrossRef", _columnsMovieGenreCrossRef, _foreignKeysMovieGenreCrossRef, _indicesMovieGenreCrossRef);
        final TableInfo _existingMovieGenreCrossRef = TableInfo.read(db, "MovieGenreCrossRef");
        if (!_infoMovieGenreCrossRef.equals(_existingMovieGenreCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "MovieGenreCrossRef(com.example.model.MovieGenreCrossRef).\n"
                  + " Expected:\n" + _infoMovieGenreCrossRef + "\n"
                  + " Found:\n" + _existingMovieGenreCrossRef);
        }
        final HashMap<String, TableInfo.Column> _columnsUserGenreCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsUserGenreCrossRef.put("userId", new TableInfo.Column("userId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserGenreCrossRef.put("genreId", new TableInfo.Column("genreId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserGenreCrossRef = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserGenreCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserGenreCrossRef = new TableInfo("UserGenreCrossRef", _columnsUserGenreCrossRef, _foreignKeysUserGenreCrossRef, _indicesUserGenreCrossRef);
        final TableInfo _existingUserGenreCrossRef = TableInfo.read(db, "UserGenreCrossRef");
        if (!_infoUserGenreCrossRef.equals(_existingUserGenreCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "UserGenreCrossRef(com.example.model.UserGenreCrossRef).\n"
                  + " Expected:\n" + _infoUserGenreCrossRef + "\n"
                  + " Found:\n" + _existingUserGenreCrossRef);
        }
        final HashMap<String, TableInfo.Column> _columnsUserState = new HashMap<String, TableInfo.Column>(2);
        _columnsUserState.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserState.put("isLoggedIn", new TableInfo.Column("isLoggedIn", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserState = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserState = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserState = new TableInfo("user_state", _columnsUserState, _foreignKeysUserState, _indicesUserState);
        final TableInfo _existingUserState = TableInfo.read(db, "user_state");
        if (!_infoUserState.equals(_existingUserState)) {
          return new RoomOpenHelper.ValidationResult(false, "user_state(com.example.model.UserState).\n"
                  + " Expected:\n" + _infoUserState + "\n"
                  + " Found:\n" + _existingUserState);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9b35c4ab5630a92730c59be766b25707", "bebff15678ebc7daf9ce9325dfd433ad");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_table","movie_table","genre_table","MovieGenreCrossRef","UserGenreCrossRef","user_state");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `user_table`");
      _db.execSQL("DELETE FROM `movie_table`");
      _db.execSQL("DELETE FROM `genre_table`");
      _db.execSQL("DELETE FROM `MovieGenreCrossRef`");
      _db.execSQL("DELETE FROM `UserGenreCrossRef`");
      _db.execSQL("DELETE FROM `user_state`");
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
    _typeConvertersMap.put(IUserDao.class, IUserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(IMovieDao.class, IMovieDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserStateDao.class, UserStateDao_Impl.getRequiredConverters());
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
  public IUserDao userDao() {
    if (_iUserDao != null) {
      return _iUserDao;
    } else {
      synchronized(this) {
        if(_iUserDao == null) {
          _iUserDao = new IUserDao_Impl(this);
        }
        return _iUserDao;
      }
    }
  }

  @Override
  public IMovieDao movieDao() {
    if (_iMovieDao != null) {
      return _iMovieDao;
    } else {
      synchronized(this) {
        if(_iMovieDao == null) {
          _iMovieDao = new IMovieDao_Impl(this);
        }
        return _iMovieDao;
      }
    }
  }

  @Override
  public UserStateDao stateDao() {
    if (_userStateDao != null) {
      return _userStateDao;
    } else {
      synchronized(this) {
        if(_userStateDao == null) {
          _userStateDao = new UserStateDao_Impl(this);
        }
        return _userStateDao;
      }
    }
  }
}
