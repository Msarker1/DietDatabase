package com.shaer.dietdatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ProfileLog extends AppCompatActivity {
    SharedPreferences storedSpinnerWeight;
    SharedPreferences storedRadioCalories;
    public void onClickInfo(View view){
        startActivity(new Intent(ProfileLog.this,info.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_log);

        storedSpinnerWeight = getSharedPreferences("com.shaer.dietdatabase", Context.MODE_PRIVATE);
        String poundNeeded = storedSpinnerWeight.getString("StoredSpinnerWeight", "");

        storedRadioCalories = getSharedPreferences("com.shaer.dietdatabase", Context.MODE_PRIVATE);
        String calorieNeeded = storedRadioCalories.getString("StoredRadioCalories", "");

        int totalCals = Integer.parseInt(poundNeeded)*Integer.parseInt(calorieNeeded);

        //EditText poundsN = (EditText) findViewById(R.id.poundsNeeded);
        EditText caloriesN = (EditText) findViewById(R.id.caloriesNeeded);
        EditText totalC = (EditText) findViewById(R.id.totalCalories) ;
        //poundsN.setText(poundNeeded);
        caloriesN.setText(calorieNeeded);
        totalC.setText(String.valueOf(totalCals));
    }
}
