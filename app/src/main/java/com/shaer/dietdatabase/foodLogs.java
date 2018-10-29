package com.shaer.dietdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class foodLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_logs);
        dataBase db = new dataBase(this, "dataFood", null, 1);

        ListView results = (ListView) findViewById(R.id.results);

        LinkedList list = db.getData(this);
        Node p = list.getHead();
        String [] listItems = new String[list.getSize()];
        for (int i = 0; i<list.getSize();i++){
            listItems[i] ="Number:"+i+"\n"+p.getName()+"\n"+ "Calories:"+p.getEnergy()+"\n"+"Fats:"+p.getFats()+"\n"+
            "Carb:"+p.getCarbohydrates()+"\n"+"Sugar:"+p.getSugar();
            p = p.getNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, listItems);
        results.setAdapter(adapter);

        Button  home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(foodLogs.this,SeachAndScan.class);
                startActivity(i);
            }
        });

    }


}
