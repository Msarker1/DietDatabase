package com.shaer.dietdatabase;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingPage extends AppCompatActivity {
    String CurrentWeight;
    String GoalWeight;
    int Pound;
    SharedPreferences storedSpinnerWeight;
    SharedPreferences storedRadioCalories;


    public void currentAndGoalSpinnerFiller() {
        ArrayList<Integer> weight = new ArrayList<Integer>();
        for (int i = 40; i <= 400; i++) {
            weight.add(i);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, weight);

        Spinner currentWeightSpinner = (Spinner) findViewById(R.id.currentWeightSpinner);
        Spinner goalWeightSpinner = (Spinner) findViewById(R.id.goalWeightSpinner);


        currentWeightSpinner.setAdapter(adapter);
        goalWeightSpinner.setAdapter(adapter);

        currentWeightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                CurrentWeight = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        goalWeightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                GoalWeight = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        storedRadioCalories = this.getSharedPreferences("com.shaer.dietdatabase", Context.MODE_PRIVATE);
        String temp = null;
        switch (view.getId()){
            case R.id.gain1Pound:
                if(checked) {
                    temp = "2861";
                    storedRadioCalories.edit().putString("StoredRadioCalories", temp).apply();
                    Toast.makeText(this, "Consume 2861 Calories", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.gain2Pounds:
                if(checked) {
                    temp = "3361";
                    storedRadioCalories.edit().putString("StoredRadioCalories", temp).apply();

                    Toast.makeText(this, "Consume 3361 Calories", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.lose1Pound:
                if(checked) {
                    temp = "1861";
                    storedRadioCalories.edit().putString("StoredRadioCalories", temp).apply();
                    Toast.makeText(this, "Consume 1861 Calories", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.lose2Pounds:
                if(checked) {
                    temp = "1361";
                    storedRadioCalories.edit().putString("StoredRadioCalories", temp).apply();
                    Toast.makeText(this, "Consume 1361 Calories", Toast.LENGTH_SHORT).show();
                    break;
                }
        }

    }


    public void setData(View view){
        Button set = (Button) findViewById(R.id.setChoice);

        Pound = Integer.parseInt(CurrentWeight) - Integer.parseInt(GoalWeight);
        storedSpinnerWeight = this.getSharedPreferences("com.shaer.dietdatabase", Context.MODE_PRIVATE);
        if (Pound>0){//lose weight
            String temp= String.valueOf((Pound));
            storedSpinnerWeight.edit().putString("StoredSpinnerWeight",temp).apply();

        } else if (Pound<0){//gain weight

            String temp= String.valueOf((Pound*-1));
            storedSpinnerWeight.edit().putString("StoredSpinnerWeight",temp).apply();

        }

        String stored = storedSpinnerWeight.getString("StoredSpinnerWeight", "");
        System.out.println(stored);
        Toast.makeText(this, stored, Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        currentAndGoalSpinnerFiller();

    }
}
