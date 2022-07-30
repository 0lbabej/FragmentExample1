package com.example.fragmentexample1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.btn_open);
    }
        public void displayFragment() {
            SimpleFragment simpleFragment = SimpleFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();
            mButton.setText("Close");
            isFragmentDisplayed = true;
        }

        public void closeFragment(){
            FragmentManager fragmentManager = getSupportFragmentManager();
            SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);

            if (simpleFragment != null){
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(simpleFragment).commit();
            }
            mButton.setText("Open");
            isFragmentDisplayed = false;
        }
}