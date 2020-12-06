package com.example.internalfilehandling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context ct;
    private ArrayList<ExampleItem> mExamplelist;

    public ExampleAdapter(Context ct,ArrayList<ExampleItem> arrayList){
        this.ct = ct;
        mExamplelist = arrayList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ExampleItem mCurrentItem = mExamplelist.get(position);

        holder.mText1.setText(mCurrentItem.getLine1());
        holder.mText2.setText(mCurrentItem.getLine2());
    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView mText1,mText2;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mText1 = itemView.findViewById(R.id.textView1);
            mText2 = itemView.findViewById(R.id.textView2);



        }
    }
}
