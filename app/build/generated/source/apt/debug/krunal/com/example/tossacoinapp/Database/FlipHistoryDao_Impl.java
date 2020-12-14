package krunal.com.example.tossacoinapp.Database;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class FlipHistoryDao_Impl implements FlipHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFlipHistoryEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public FlipHistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFlipHistoryEntity = new EntityInsertionAdapter<FlipHistoryEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `History_tbl`(`Id`,`Results`,`Date`,`Time`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FlipHistoryEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getResults() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getResults());
        }
        if (value.getDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate());
        }
        if (value.getTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTime());
        }
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Delete From History_tbl";
        return _query;
      }
    };
  }

  @Override
  public void insert(FlipHistoryEntity flipHistoryEntity) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFlipHistoryEntity.insert(flipHistoryEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public LiveData<List<FlipHistoryEntity>> getAllHistory() {
    final String _sql = "Select * From History_tbl";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<FlipHistoryEntity>>() {
      private Observer _observer;

      @Override
      protected List<FlipHistoryEntity> compute() {
        if (_observer == null) {
          _observer = new Observer("History_tbl") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("Id");
          final int _cursorIndexOfResults = _cursor.getColumnIndexOrThrow("Results");
          final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("Date");
          final int _cursorIndexOfTime = _cursor.getColumnIndexOrThrow("Time");
          final List<FlipHistoryEntity> _result = new ArrayList<FlipHistoryEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FlipHistoryEntity _item;
            final String _tmpResults;
            _tmpResults = _cursor.getString(_cursorIndexOfResults);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final String _tmpTime;
            _tmpTime = _cursor.getString(_cursorIndexOfTime);
            _item = new FlipHistoryEntity(_tmpResults,_tmpDate,_tmpTime);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
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
    }.getLiveData();
  }
}
