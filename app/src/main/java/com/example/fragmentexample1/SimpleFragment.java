package com.example.fragmentexample1;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NONE = 2;

    public int mRadioButtonChoice = NONE;
    OnFragmentInteractionListener mListener;

    public SimpleFragment() {}

    public static SimpleFragment newInstance(){
        return new SimpleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
       final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
       final RatingBar ratingBar = rootView.findViewById(R.id.ratingBar);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView =
                        rootView.findViewById(R.id.fragment_header);
                switch (index) {
                    case YES: // User chose "Yes."
                        textView.setText(R.string.yes_message);
                        mRadioButtonChoice = YES;
                        mListener.onRadioButtonChoice(YES);
                        break;
                    case NO: // User chose "No."
                        textView.setText(R.string.no_message);
                        mRadioButtonChoice = NO;
                        mListener.onRadioButtonChoice(NO);
                        break;
                    default: // No choice made.
                        mRadioButtonChoice = NONE;
                        mListener.onRadioButtonChoice(NONE);
                        break;
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String UserRating = getString(R.string.myrating) + String.valueOf(ratingBar.getRating());
                Toast.makeText(getContext(), UserRating, Toast.LENGTH_SHORT).show();
            }
        });
       return rootView;
    }
    interface OnFragmentInteractionListener {
        void onRadioButtonChoice(int choice);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        } else{
        throw new ClassCastException(context.toString()+ getResources().getString(R.string.exception_message));
        }
    }
}