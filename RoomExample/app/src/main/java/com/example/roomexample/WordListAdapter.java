package com.example.roomexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private List<Word> mWords;

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item,parent,false);
        return new WordViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mWords!=null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }
        else {
            holder.wordItemView.setText("No Words Added");
        }
    }

    @Override
    public int getItemCount() {
        if(mWords!=null){
            return mWords.size();
        }
        return 0;
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder{

        private final TextView wordItemView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
