package com.example.fragmentsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class BlankFragment extends Fragment {

    public static final String TEXT = "text";
    private String text;

    private EditText editTextFragment;
    private Button buttonFragment;

    private FragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank,container,false);

        editTextFragment = view.findViewById(R.id.edit_text_fragment);
        editTextFragment.setText(text);
        editTextFragment.requestFocus();

        buttonFragment = view.findViewById(R.id.button_fragment);
        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendBackText = editTextFragment.getText().toString();
                listener.sendBack(sendBackText);
            }
        });

        return view;
    }

    public static BlankFragment newInstance(String text) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(TEXT,text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            text = getArguments().getString(TEXT);
        }
    }

    public interface FragmentListener {
        void sendBack(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (FragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
