package Swapnil.com.example.tossacoinapp.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FlipHistoryDao {

    // Insert History into History_tbl.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FlipHistoryEntity flipHistoryEntity);

    // Get all record from History_tbl.
    @Query("Select * From History_tbl")
    LiveData<List<FlipHistoryEntity>> getAllHistory();

    // Delete All record from History_tbl.
    @Query("Delete From History_tbl")
    void delete();

}
