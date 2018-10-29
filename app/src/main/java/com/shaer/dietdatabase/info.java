package com.shaer.dietdatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
public class info extends AppCompatActivity {
    GraphView graphView;
    SharedPreferences storedSpinnerWeight;
    SharedPreferences storedRadioCalories;
    BarGraphSeries<DataPoint> series1;
    SimpleDateFormat sdf=new SimpleDateFormat("MM/dd");

    Calendar calendar = Calendar.getInstance();

    Date d0,d1,d2,d3,d4,d5,d6,d7;
    String s0,s1,s2,s3,s4,s5,s6,s7;
    int totalCals;






    public void creategraph(){
        d0 = calendar.getTime();
        calendar.add(Calendar.DATE, -3);
        d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        d3 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        d4 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        d5 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        d6 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        d7 = calendar.getTime();

        s0=sdf.format(d0 = calendar.getTime());

        series1 = new BarGraphSeries<>(getDataPoint1());
        graphView = findViewById(R.id.graph);
        graphView.addSeries(series1);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(7);
        series1.setDrawValuesOnTop(true);
        series1.setValuesOnTopColor(R.color.black);
        series1.setSpacing(10);

        graphView.getGridLabelRenderer().setHumanRounding(false);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(new Date((long) value));
                }else{
                    return super.formatLabel(value, isValueX);
                }

            }
        });
    }

    //dataPointInterface.getX()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        creategraph();
        storedSpinnerWeight = getSharedPreferences("com.shaer.dietdatabase", Context.MODE_PRIVATE);
        String poundNeeded = storedSpinnerWeight.getString("StoredSpinnerWeight", "");

        storedRadioCalories = getSharedPreferences("com.shaer.dietdatabase", Context.MODE_PRIVATE);
        String calorieNeeded = storedRadioCalories.getString("StoredRadioCalories", "");

        totalCals = Integer.parseInt(poundNeeded)*Integer.parseInt(calorieNeeded);

    }

    /*int getinfo(Date a){
        s1=sdf.format(a);
        if(s1==s0) {
            return totalCals;
        }else {
            return 0;
        }
    }*/

    private DataPoint[] getDataPoint1() {

        DataPoint[] dp1 = new DataPoint[]{
                new DataPoint(d1, 0),
                new DataPoint(d2, 3000),
                new DataPoint(d3, 2000),
                new DataPoint(d4, 1000),
                new DataPoint(d5, 0),
                new DataPoint(d6, 0),
                new DataPoint(d7, 0)
        };

        return dp1;
    }
}