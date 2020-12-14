package Swapnil.com.example.tossacoinapp.Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "History_tbl")
public class FlipHistoryEntity {

    @ColumnInfo(name =  "Id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Results")
    private String results;

    @ColumnInfo(name = "Date")
    private String date;

    @ColumnInfo(name = "Time")
    private String time;

    public FlipHistoryEntity(String results,String date,String time){
        this.results = results;
        this.date = date;
        this.time = time;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getResults() {
        return results;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
