package Swapnil.com.example.tossacoinapp.Database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import java.util.List;

import Swapnil.com.example.tossacoinapp.AppExecutor;

public class Repository {

    private AppExecutor mAppExecutor;

    private AppDatabase mAppDatabase;

    private LiveData<List<FlipHistoryEntity>> mList;

    public Repository(Context context){
        Log.i("Repository","Repository call");
        mAppExecutor = new AppExecutor();
        mAppDatabase = AppDatabase.getInstance(context);
        mList = mAppDatabase.get_FlipHistory_doa().getAllHistory();
    }

    /**
     * Get All Record From History_tbl.
     * This is Done on Main Thread.
     * @return LiveData Object mList.List of All Record present in History_tbl.
     */
    public LiveData<List<FlipHistoryEntity>> getAllHistoryList(){
        Log.i("Repository","getAllHistoryList call");
       return mList;
    }

    /**
     * Insert Data into History_tbl.
     * This is Done on Thread Pool.
     * @param flipHistoryEntity
     */
    public void insert(FlipHistoryEntity flipHistoryEntity){
        Log.i("Repository","insert call");
        mAppExecutor.diskIO().execute(() ->{
            mAppDatabase.get_FlipHistory_doa().insert(flipHistoryEntity);

        });
    }

    /**
     * Delete All Record from History_tbl.
     * This Done on Thread Pool.
     */
    public void delete(){
        Log.i("Repository","delete call");
        mAppExecutor.diskIO().execute(() ->{
            mAppDatabase.get_FlipHistory_doa().delete();
        });
    }


}
