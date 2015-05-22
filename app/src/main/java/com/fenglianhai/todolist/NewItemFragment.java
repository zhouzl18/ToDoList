package com.fenglianhai.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Administrator on 2015/4/27.
 */
public class NewItemFragment extends Fragment {

    public interface OnNewItemAddedListener{
        public void onNewItemAdded(String newItem);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.new_item_fragment, container, false);

        final EditText myEditText =(EditText) view.findViewById(R.id.myEditText);
        myEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER){
                        String newItem = myEditText.getText().toString();
                        onNewItemAddedListener.onNewItemAdded(newItem);
                        myEditText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }

    private OnNewItemAddedListener onNewItemAddedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
        onNewItemAddedListener = (OnNewItemAddedListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement OnNewItemAddedListener");
        }
    }
}
