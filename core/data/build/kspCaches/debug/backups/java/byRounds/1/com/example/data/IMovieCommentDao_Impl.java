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
import com.example.model.MovieComment;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class IMovieCommentDao_Impl implements IMovieCommentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MovieComment> __insertionAdapterOfMovieComment;

  private final EntityDeletionOrUpdateAdapter<MovieComment> __deletionAdapterOfMovieComment;

  private final EntityDeletionOrUpdateAdapter<MovieComment> __updateAdapterOfMovieComment;

  public IMovieCommentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieComment = new EntityInsertionAdapter<MovieComment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `movie_comments` (`commentId`,`movieId`,`comment`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieComment entity) {
        statement.bindLong(1, entity.getCommentId());
        statement.bindLong(2, entity.getMovieId());
        statement.bindString(3, entity.getComment());
      }
    };
    this.__deletionAdapterOfMovieComment = new EntityDeletionOrUpdateAdapter<MovieComment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `movie_comments` WHERE `commentId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieComment entity) {
        statement.bindLong(1, entity.getCommentId());
      }
    };
    this.__updateAdapterOfMovieComment = new EntityDeletionOrUpdateAdapter<MovieComment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `movie_comments` SET `commentId` = ?,`movieId` = ?,`comment` = ? WHERE `commentId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieComment entity) {
        statement.bindLong(1, entity.getCommentId());
        statement.bindLong(2, entity.getMovieId());
        statement.bindString(3, entity.getComment());
        statement.bindLong(4, entity.getCommentId());
      }
    };
  }

  @Override
  public Object insertComment(final MovieComment comment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovieComment.insert(comment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteComment(final MovieComment comment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMovieComment.handle(comment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateComment(final MovieComment comment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMovieComment.handle(comment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCommentsForMovie(final int movieId,
      final Continuation<? super List<MovieComment>> $completion) {
    final String _sql = "SELECT * FROM movie_comments WHERE movieId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MovieComment>>() {
      @Override
      @NonNull
      public List<MovieComment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCommentId = CursorUtil.getColumnIndexOrThrow(_cursor, "commentId");
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final List<MovieComment> _result = new ArrayList<MovieComment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MovieComment _item;
            final int _tmpCommentId;
            _tmpCommentId = _cursor.getInt(_cursorIndexOfCommentId);
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final String _tmpComment;
            _tmpComment = _cursor.getString(_cursorIndexOfComment);
            _item = new MovieComment(_tmpCommentId,_tmpMovieId,_tmpComment);
            _result.add(_item);
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
