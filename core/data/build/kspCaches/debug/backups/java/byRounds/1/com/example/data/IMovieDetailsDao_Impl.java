package com.example.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.model.MovieDetails;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class IMovieDetailsDao_Impl implements IMovieDetailsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MovieDetails> __insertionAdapterOfMovieDetails;

  private final EntityDeletionOrUpdateAdapter<MovieDetails> __updateAdapterOfMovieDetails;

  public IMovieDetailsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieDetails = new EntityInsertionAdapter<MovieDetails>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `movie_details_table` (`movieId`,`vote`,`isFavorite`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieDetails entity) {
        statement.bindLong(1, entity.getMovieId());
        statement.bindLong(2, entity.getVote());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(3, _tmp);
      }
    };
    this.__updateAdapterOfMovieDetails = new EntityDeletionOrUpdateAdapter<MovieDetails>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `movie_details_table` SET `movieId` = ?,`vote` = ?,`isFavorite` = ? WHERE `movieId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieDetails entity) {
        statement.bindLong(1, entity.getMovieId());
        statement.bindLong(2, entity.getVote());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getMovieId());
      }
    };
  }

  @Override
  public Object insertMovieDetails(final MovieDetails movieDetails,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovieDetails.insert(movieDetails);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateMovieDetails(final MovieDetails movieDetails,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMovieDetails.handle(movieDetails);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getMovieDetails(final int movieId,
      final Continuation<? super MovieDetails> $completion) {
    final String _sql = "SELECT * FROM movie_details_table WHERE movieId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MovieDetails>() {
      @Override
      @NonNull
      public MovieDetails call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
          final int _cursorIndexOfVote = CursorUtil.getColumnIndexOrThrow(_cursor, "vote");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final MovieDetails _result;
          if (_cursor.moveToFirst()) {
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final int _tmpVote;
            _tmpVote = _cursor.getInt(_cursorIndexOfVote);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            _result = new MovieDetails(_tmpMovieId,_tmpVote,_tmpIsFavorite);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
