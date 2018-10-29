package com.shaer.dietdatabase;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Pacer extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Button setSteps;
    private TextView count;
    private TextView textView;
    private Button dateView;
    private Button calcalo;
    String cal="";
    String text=null;
    int test;
    private boolean reset = false;
    private int previous = 0;

    boolean activityRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacer);

        setSteps = (Button) findViewById(R.id.resetId);
        calcalo= (Button) findViewById(R.id.caloriesId);
        dateView = (Button) findViewById(R.id.textView4);


        // textView= (TextView) findViewById(R.id.textViewId);

        //   count.setText(Integer.toString(Integer.parseInt(count.getText().toString())-previous));
        count = (TextView) findViewById(R.id.Count);

        // text = count.getText().toString();
        //test = Integer.parseInt(text);
        //cal=cal+ test*.20;
        //cal=cal+ 20*.20;

        // cal=cal+ 20*.20;

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


//        Calendar cal = Calendar.getInstance();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("E/MMMM/d/YYYY", Locale.US); // Set your locale!
//        String strDate = sdf.format(cal.getTime());
//
//        dateView.setText(strDate);





        //sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
// if you unregister the last listener, the hardware will stop detecting step events
// sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning) {
            count.setText(String.valueOf(Math.abs(event.values[0]-previous)));
            previous+=event.values[0];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public void showMessage(View v) {


        int x=0;
        if(v.getId()==R.id.resetId) {

            count.setText("0");
            if(count.getText()!= null)
                setSteps.setText(count.getText()+" Reset done");
            else{
                setSteps.setText(Integer.toString(0));
            }

        }
        else if(v.getId()==R.id.caloriesId){

            String texts = count.getText().toString();

            if(count.getText()!= "0") {
                calcalo.setText("Current burn from step " + (int) (Float.parseFloat(texts.toString()) * .20) + " calories");
            }
            else{
                calcalo.setText("Current burn from steps "+ count.getText());
            }

        }
        else if(v.getId()==R.id.textView4){

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("E/MMMM/d/YYYY", Locale.US); // Set your locale!
            String strDate = sdf.format(cal.getTime());
            // String date=strDate;
            dateView.setText(strDate);
        }

    }
}