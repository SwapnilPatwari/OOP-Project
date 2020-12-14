package Swapnil.com.example.tossacoinapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Swapnil.com.example.tossacoinapp.Database.FlipHistoryEntity;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleView> {


    private LayoutInflater mlayoutInflater;
    private List<FlipHistoryEntity> mlistHistory = new ArrayList<>();

    RecycleAdapter(Context context){
        this.mlayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecycleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.list,parent,false);
        Log.i("RecycleView", "onCreateViewHolder call");
        return new RecycleView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView holder, int position) {
        Log.i("RecycleView", "onBindViewHolder call");
        FlipHistoryEntity flipHistoryEntity = mlistHistory.get(position);
        holder.mResult.setText(flipHistoryEntity.getResults());
        holder.mDate.setText(flipHistoryEntity.getDate());
        holder.mTime.setText(flipHistoryEntity.getTime());
    }

    void add(List<FlipHistoryEntity> add){
        Log.i("RecycleView", "add call");
        this.mlistHistory = add;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return mlistHistory.size();
    }

    static class RecycleView extends RecyclerView.ViewHolder{

        TextView mResult,mDate,mTime;

        RecycleView(View itemView) {
            super(itemView);
            Log.i("RecycleView", "RecycleView call");
            mResult = itemView.findViewById(R.id.result);
            mDate = itemView.findViewById(R.id.Date);
            mTime = itemView.findViewById(R.id.Time_get);
        }

    }

}
