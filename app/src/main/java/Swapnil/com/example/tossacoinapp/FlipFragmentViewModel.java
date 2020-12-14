package Swapnil.com.example.tossacoinapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import Swapnil.com.example.tossacoinapp.Database.FlipHistoryEntity;
import Swapnil.com.example.tossacoinapp.Database.Repository;

public class FlipFragmentViewModel extends AndroidViewModel {

    private Repository mRepository;

    public FlipFragmentViewModel(@NonNull Application application) {
        super(application);

        mRepository = new Repository(application);
    }

    // Insert History into History_tbl.
    public void insert(FlipHistoryEntity flipHistoryEntity){
        Log.i("FlipFragmentViewModel", "insert call");
        mRepository.insert(flipHistoryEntity);
    }
}
