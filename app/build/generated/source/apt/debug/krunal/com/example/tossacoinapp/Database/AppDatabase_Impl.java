package krunal.com.example.tossacoinapp.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile FlipHistoryDao _flipHistoryDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `History_tbl` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Results` TEXT, `Date` TEXT, `Time` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"9c95bb4e188006b32fe8289701ed214e\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `History_tbl`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsHistoryTbl = new HashMap<String, TableInfo.Column>(4);
        _columnsHistoryTbl.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1));
        _columnsHistoryTbl.put("Results", new TableInfo.Column("Results", "TEXT", false, 0));
        _columnsHistoryTbl.put("Date", new TableInfo.Column("Date", "TEXT", false, 0));
        _columnsHistoryTbl.put("Time", new TableInfo.Column("Time", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHistoryTbl = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHistoryTbl = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHistoryTbl = new TableInfo("History_tbl", _columnsHistoryTbl, _foreignKeysHistoryTbl, _indicesHistoryTbl);
        final TableInfo _existingHistoryTbl = TableInfo.read(_db, "History_tbl");
        if (! _infoHistoryTbl.equals(_existingHistoryTbl)) {
          throw new IllegalStateException("Migration didn't properly handle History_tbl(krunal.com.example.tossacoinapp.Database.FlipHistoryEntity).\n"
                  + " Expected:\n" + _infoHistoryTbl + "\n"
                  + " Found:\n" + _existingHistoryTbl);
        }
      }
    }, "9c95bb4e188006b32fe8289701ed214e", "4d3c3be3e82379ab2f8ff8a20f80472f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "History_tbl");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `History_tbl`");
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
  public FlipHistoryDao get_FlipHistory_doa() {
    if (_flipHistoryDao != null) {
      return _flipHistoryDao;
    } else {
      synchronized(this) {
        if(_flipHistoryDao == null) {
          _flipHistoryDao = new FlipHistoryDao_Impl(this);
        }
        return _flipHistoryDao;
      }
    }
  }
}
