package com.shaer.dietdatabase;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class displayNutrient extends AppCompatActivity {
    private dataBase db;
    private SQLiteDatabase mSqlDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_nutrient);





        Intent intent = getIntent();
        //String s = intent.getStringExtra("nutrientsData");
        final String n[] = intent.getStringArrayExtra("nutrientsData");
        final TextView g = (TextView)findViewById(R.id.displayNutrients);
        g.setText("Name:"+n[0]+"\n"+"Calories:"+n[1]+"\n"+"Sugar:"+n[2]+"\n"+"Fat:"+n[3]+"\n"+"Carbohydrate:"+n[4]);
        //Toast.makeText(com.example.mohammad.myapplication.displayNutrient.this, s, Toast.LENGTH_SHORT).show();

        db = new dataBase(this, "dataFood", null, 1);

        mSqlDb = db.getWritableDatabase();

        Button consume = (Button) findViewById(R.id.Consume);
        consume.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                insertDataScan(n,g);
            }
        });

        Button goBack = (Button) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(displayNutrient.this,SeachAndScan.class);
                startActivity(i);
            }
        });


    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertDataScan(String [] n, TextView g) {
        g.setText("SUBMITTED:"+n[0]);
       db.Insert(n);

    }



}
