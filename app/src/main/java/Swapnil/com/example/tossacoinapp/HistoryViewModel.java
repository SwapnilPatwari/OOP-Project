package Swapnil.com.example.tossacoinapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import krunal.com.example.tossacoinapp.Database.FlipHistoryEntity;
import krunal.com.example.tossacoinapp.Database.Repository;

public class HistoryViewModel extends AndroidViewModel {

    private LiveData<List<FlipHistoryEntity>> mlist;

    private Repository mRepository;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        Log.i("HistoryViewModel","HistoryViewModel");

        mRepository = new Repository(application);
        this.mlist = mRepository.getAllHistoryList();
    }

    // Get All Record From History_tbl.
    public LiveData<List<FlipHistoryEntity>> getMlist() {
        Log.i("HistoryViewModel","getMlist call");
        return mlist;
    }

    // Delete All record from History_tbl.
    public void delete(){
        Log.i("HistoryViewModel","HistoryViewModel");
        mRepository.delete();
    }
}
