package com.example.roomexample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    public Word() {
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    Word(@NonNull String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
