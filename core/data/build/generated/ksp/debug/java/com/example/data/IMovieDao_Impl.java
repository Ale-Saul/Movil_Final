package com.example.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.model.Genre;
import com.example.model.Movie;
import com.example.model.MovieGenreCrossRef;
import com.example.model.MovieWithGenres;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class IMovieDao_Impl implements IMovieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Movie> __insertionAdapterOfMovie;

  private final EntityInsertionAdapter<Genre> __insertionAdapterOfGenre;

  private final EntityInsertionAdapter<MovieGenreCrossRef> __insertionAdapterOfMovieGenreCrossRef;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFavorite;

  private final SharedSQLiteStatement __preparedStmtOfSetNewVote;

  private final SharedSQLiteStatement __preparedStmtOfSetVoteStars;

  public IMovieDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovie = new EntityInsertionAdapter<Movie>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `movie_table` (`movieId`,`title`,`description`,`posterPath`,`voteAverage`,`releaseDate`,`voteSelf`,`newVote`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Movie entity) {
        statement.bindLong(1, entity.getMovieId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getPosterPath());
        statement.bindDouble(5, entity.getVoteAverage());
        statement.bindString(6, entity.getReleaseDate());
        statement.bindLong(7, entity.getVoteSelf());
        statement.bindDouble(8, entity.getNewVote());
      }
    };
    this.__insertionAdapterOfGenre = new EntityInsertionAdapter<Genre>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `genre_table` (`genreId`,`name`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Genre entity) {
        statement.bindLong(1, entity.getGenreId());
        statement.bindString(2, entity.getName());
      }
    };
    this.__insertionAdapterOfMovieGenreCrossRef = new EntityInsertionAdapter<MovieGenreCrossRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `MovieGenreCrossRef` (`movieId`,`genreId`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieGenreCrossRef entity) {
        statement.bindLong(1, entity.getMovieId());
        statement.bindLong(2, entity.getGenreId());
      }
    };
    this.__preparedStmtOfUpdateFavorite = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE movie_details_table SET isFavorite=NOT isFavorite  WHERE movieId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetNewVote = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE movie_table SET newVote = ? WHERE movieId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetVoteStars = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE movie_table SET voteSelf = ? WHERE movieId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertMovies(final List<Movie> movies,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovie.insert(movies);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMovie(final Movie movie, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovie.insert(movie);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertGenres(final List<Genre> genres,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGenre.insert(genres);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMovieGenreCrossRef(final List<MovieGenreCrossRef> crossRef,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovieGenreCrossRef.insert(crossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateVotes(final int movieId, final int voteStars, final double newVote,
      final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> IMovieDao.DefaultImpls.updateVotes(IMovieDao_Impl.this, movieId, voteStars, newVote, __cont), $completion);
  }

  @Override
  public void updateFavorite(final int movieID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFavorite.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, movieID);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateFavorite.release(_stmt);
    }
  }

  @Override
  public void setNewVote(final double newRating, final int movieID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetNewVote.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, newRating);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, movieID);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfSetNewVote.release(_stmt);
    }
  }

  @Override
  public void setVoteStars(final int newRating, final int movieID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetVoteStars.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, newRating);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, movieID);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfSetVoteStars.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Movie>> getAllMovies() {
    final String _sql = "SELECT * FROM movie_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"movie_table"}, false, new Callable<List<Movie>>() {
      @Override
      @Nullable
      public List<Movie> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfVoteSelf = CursorUtil.getColumnIndexOrThrow(_cursor, "voteSelf");
          final int _cursorIndexOfNewVote = CursorUtil.getColumnIndexOrThrow(_cursor, "newVote");
          final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Movie _item;
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpPosterPath;
            _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final int _tmpVoteSelf;
            _tmpVoteSelf = _cursor.getInt(_cursorIndexOfVoteSelf);
            final double _tmpNewVote;
            _tmpNewVote = _cursor.getDouble(_cursorIndexOfNewVote);
            _item = new Movie(_tmpMovieId,_tmpTitle,_tmpDescription,_tmpPosterPath,_tmpVoteAverage,_tmpReleaseDate,_tmpVoteSelf,_tmpNewVote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Movie>> getFavoriteMovies() {
    final String _sql = "SELECT * FROM movie_table LEFT JOIN movie_details_table ON movie_table.movieId = movie_details_table.movieId WHERE movie_details_table.isFavorite = true";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"movie_table",
        "movie_details_table"}, false, new Callable<List<Movie>>() {
      @Override
      @Nullable
      public List<Movie> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfVoteSelf = CursorUtil.getColumnIndexOrThrow(_cursor, "voteSelf");
          final int _cursorIndexOfNewVote = CursorUtil.getColumnIndexOrThrow(_cursor, "newVote");
          final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Movie _item;
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpPosterPath;
            _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final int _tmpVoteSelf;
            _tmpVoteSelf = _cursor.getInt(_cursorIndexOfVoteSelf);
            final double _tmpNewVote;
            _tmpNewVote = _cursor.getDouble(_cursorIndexOfNewVote);
            _item = new Movie(_tmpMovieId,_tmpTitle,_tmpDescription,_tmpPosterPath,_tmpVoteAverage,_tmpReleaseDate,_tmpVoteSelf,_tmpNewVote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Movie>> getRatedMovies() {
    final String _sql = "SELECT * FROM movie_table LEFT JOIN movie_details_table ON movie_table.movieId = movie_details_table.movieId WHERE movie_details_table.vote <> 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"movie_table",
        "movie_details_table"}, false, new Callable<List<Movie>>() {
      @Override
      @Nullable
      public List<Movie> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfVoteSelf = CursorUtil.getColumnIndexOrThrow(_cursor, "voteSelf");
          final int _cursorIndexOfNewVote = CursorUtil.getColumnIndexOrThrow(_cursor, "newVote");
          final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Movie _item;
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpPosterPath;
            _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final int _tmpVoteSelf;
            _tmpVoteSelf = _cursor.getInt(_cursorIndexOfVoteSelf);
            final double _tmpNewVote;
            _tmpNewVote = _cursor.getDouble(_cursorIndexOfNewVote);
            _item = new Movie(_tmpMovieId,_tmpTitle,_tmpDescription,_tmpPosterPath,_tmpVoteAverage,_tmpReleaseDate,_tmpVoteSelf,_tmpNewVote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Genre> getAllGenres() {
    final String _sql = "SELECT * FROM  genre_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGenreId = CursorUtil.getColumnIndexOrThrow(_cursor, "genreId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<Genre> _result = new ArrayList<Genre>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Genre _item;
        final int _tmpGenreId;
        _tmpGenreId = _cursor.getInt(_cursorIndexOfGenreId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item = new Genre(_tmpGenreId,_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Genre> getAllGenresProfile(final int userId) {
    final String _sql = "SELECT genre_table.* FROM UserGenreCrossRef JOIN genre_table ON UserGenreCrossRef.genreId = genre_table.genreId WHERE UserGenreCrossRef.userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGenreId = CursorUtil.getColumnIndexOrThrow(_cursor, "genreId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<Genre> _result = new ArrayList<Genre>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Genre _item;
        final int _tmpGenreId;
        _tmpGenreId = _cursor.getInt(_cursorIndexOfGenreId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item = new Genre(_tmpGenreId,_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Object getMovieCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM movie_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getMovieWithGenres(final int movieId,
      final Continuation<? super List<MovieWithGenres>> $completion) {
    final String _sql = "SELECT * FROM movie_table WHERE movieId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<List<MovieWithGenres>>() {
      @Override
      @NonNull
      public List<MovieWithGenres> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
            final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
            final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
            final int _cursorIndexOfVoteSelf = CursorUtil.getColumnIndexOrThrow(_cursor, "voteSelf");
            final int _cursorIndexOfNewVote = CursorUtil.getColumnIndexOrThrow(_cursor, "newVote");
            final HashMap<Long, ArrayList<Genre>> _collectionGenres = new HashMap<Long, ArrayList<Genre>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfMovieId);
              if (!_collectionGenres.containsKey(_tmpKey)) {
                _collectionGenres.put(_tmpKey, new ArrayList<Genre>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipgenreTableAscomExampleModelGenre(_collectionGenres);
            final List<MovieWithGenres> _result = new ArrayList<MovieWithGenres>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final MovieWithGenres _item;
              final Movie _tmpMovie;
              final int _tmpMovieId;
              _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              final String _tmpDescription;
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              final String _tmpPosterPath;
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
              final double _tmpVoteAverage;
              _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
              final String _tmpReleaseDate;
              _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
              final int _tmpVoteSelf;
              _tmpVoteSelf = _cursor.getInt(_cursorIndexOfVoteSelf);
              final double _tmpNewVote;
              _tmpNewVote = _cursor.getDouble(_cursorIndexOfNewVote);
              _tmpMovie = new Movie(_tmpMovieId,_tmpTitle,_tmpDescription,_tmpPosterPath,_tmpVoteAverage,_tmpReleaseDate,_tmpVoteSelf,_tmpNewVote);
              final ArrayList<Genre> _tmpGenresCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfMovieId);
              _tmpGenresCollection = _collectionGenres.get(_tmpKey_1);
              _item = new MovieWithGenres(_tmpMovie,_tmpGenresCollection);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Movie>> getGenreWithMovies(final int genreId) {
    final String _sql = "SELECT movie_table.* FROM movie_table JOIN MovieGenreCrossRef ON movie_table.movieId = MovieGenreCrossRef.movieId WHERE genreId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, genreId);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"movie_table",
        "MovieGenreCrossRef"}, new Callable<List<Movie>>() {
      @Override
      @Nullable
      public List<Movie> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
            final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
            final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
            final int _cursorIndexOfVoteSelf = CursorUtil.getColumnIndexOrThrow(_cursor, "voteSelf");
            final int _cursorIndexOfNewVote = CursorUtil.getColumnIndexOrThrow(_cursor, "newVote");
            final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final Movie _item;
              final int _tmpMovieId;
              _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              final String _tmpDescription;
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              final String _tmpPosterPath;
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
              final double _tmpVoteAverage;
              _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
              final String _tmpReleaseDate;
              _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
              final int _tmpVoteSelf;
              _tmpVoteSelf = _cursor.getInt(_cursorIndexOfVoteSelf);
              final double _tmpNewVote;
              _tmpNewVote = _cursor.getDouble(_cursorIndexOfNewVote);
              _item = new Movie(_tmpMovieId,_tmpTitle,_tmpDescription,_tmpPosterPath,_tmpVoteAverage,_tmpReleaseDate,_tmpVoteSelf,_tmpNewVote);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Movie> getMovieById(final int movieId) {
    final String _sql = "SELECT * FROM movie_table WHERE movieId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"movie_table"}, false, new Callable<Movie>() {
      @Override
      @Nullable
      public Movie call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfVoteSelf = CursorUtil.getColumnIndexOrThrow(_cursor, "voteSelf");
          final int _cursorIndexOfNewVote = CursorUtil.getColumnIndexOrThrow(_cursor, "newVote");
          final Movie _result;
          if (_cursor.moveToFirst()) {
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpPosterPath;
            _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final int _tmpVoteSelf;
            _tmpVoteSelf = _cursor.getInt(_cursorIndexOfVoteSelf);
            final double _tmpNewVote;
            _tmpNewVote = _cursor.getDouble(_cursorIndexOfNewVote);
            _result = new Movie(_tmpMovieId,_tmpTitle,_tmpDescription,_tmpPosterPath,_tmpVoteAverage,_tmpReleaseDate,_tmpVoteSelf,_tmpNewVote);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Movie getMovieById2(final int movieId) {
    final String _sql = "SELECT * FROM movie_table WHERE movieId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
      final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
      final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
      final int _cursorIndexOfVoteSelf = CursorUtil.getColumnIndexOrThrow(_cursor, "voteSelf");
      final int _cursorIndexOfNewVote = CursorUtil.getColumnIndexOrThrow(_cursor, "newVote");
      final Movie _result;
      if (_cursor.moveToFirst()) {
        final int _tmpMovieId;
        _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpPosterPath;
        _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
        final double _tmpVoteAverage;
        _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
        final String _tmpReleaseDate;
        _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
        final int _tmpVoteSelf;
        _tmpVoteSelf = _cursor.getInt(_cursorIndexOfVoteSelf);
        final double _tmpNewVote;
        _tmpNewVote = _cursor.getDouble(_cursorIndexOfNewVote);
        _result = new Movie(_tmpMovieId,_tmpTitle,_tmpDescription,_tmpPosterPath,_tmpVoteAverage,_tmpReleaseDate,_tmpVoteSelf,_tmpNewVote);
      } else {
        _result = null;
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

  private void __fetchRelationshipgenreTableAscomExampleModelGenre(
      @NonNull final HashMap<Long, ArrayList<Genre>> _map) {
    final Set<Long> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchHashMap(_map, true, (map) -> {
        __fetchRelationshipgenreTableAscomExampleModelGenre(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `genre_table`.`genreId` AS `genreId`,`genre_table`.`name` AS `name`,_junction.`movieId` FROM `MovieGenreCrossRef` AS _junction INNER JOIN `genre_table` ON (_junction.`genreId` = `genre_table`.`genreId`) WHERE _junction.`movieId` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (long _item : __mapKeySet) {
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      // _junction.movieId;
      final int _itemKeyIndex = 2;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfGenreId = 0;
      final int _cursorIndexOfName = 1;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<Genre> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final Genre _item_1;
          final int _tmpGenreId;
          _tmpGenreId = _cursor.getInt(_cursorIndexOfGenreId);
          final String _tmpName;
          _tmpName = _cursor.getString(_cursorIndexOfName);
          _item_1 = new Genre(_tmpGenreId,_tmpName);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
