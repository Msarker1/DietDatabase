package com.shaer.dietdatabase;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class SeachAndScan extends AppCompatActivity implements asyncResponse  {
    private ZXingScannerView scannerView;
    private ListView results;
    private SQLiteDatabase mSqlDb;
    private asyncResponse as;

    public int getNdbnoNum() {
        return ndbnoNum;
    }

    public void setNdbnoNum(int ndbnoNum) {
        this.ndbnoNum = ndbnoNum;
    }

    int ndbnoNum;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_scan);
        Button logs = (Button) findViewById(R.id.goalLOGS);

        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeachAndScan.this,foodLogs.class);
                startActivity(i);
            }
        });


    }



    public void queryNDBNO(View v){
        EditText usrQuery = (EditText) findViewById(R.id.usrQuery);
        if(usrQuery.getText()!=null){
            String q= usrQuery.getText().toString().replace((char)32,(char)43);
            String ndbnoURL = "https://api.nal.usda.gov/ndb/search/?format=json&q="+q+"&sort=n&max=25&offset=0&api_key=oNi2a4fB62XkwkATu6Egz62zoHGQsV7aFcnNQqUd";
            getapiData as = new getapiData(ndbnoNum);
            as.asyncResponse = SeachAndScan.this;
            as.execute(ndbnoURL);
        }
    }

    public void callForNDBNO(String UPC) {

        String ndbnoURL = "https://api.nal.usda.gov/ndb/search/?format=json&q="+UPC+"&sort=n&max=25&offset=0&api_key=oNi2a4fB62XkwkATu6Egz62zoHGQsV7aFcnNQqUd";
        scanUPC as = new scanUPC(ndbnoNum);
        as.asyncResponse = SeachAndScan.this;
        as.execute(ndbnoURL);



    }


    public void scanner(View view){
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());
        setContentView(scannerView);
        scannerView.startCamera();
    }

    @Override
    public void searchNDBNO(LinkedList n,int size) {
        Node p = n.getHead();
        results = (ListView) findViewById(R.id.results);
        String [] listItems = new String[size];
        final List<Node> AList = new ArrayList<Node>();
        for (int i = 0; i<size;i++){
            listItems[i] = p.getName();
            AList.add(i,p);
            p = p.getNext();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, listItems);
        results.setAdapter(adapter);
        results.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                scanNDBNO(AList.get(i).getNDBNO());
            }
        });


    }

    public void callForNutrients(String n) {
        String url = "https://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=oNi2a4fB62XkwkATu6Egz62zoHGQsV7aFcnNQqUd&nutrients=205&nutrients=204&nutrients=208&nutrients=269&ndbno="+n;
        getnutrientData as2 = new getnutrientData();
        as2.asyncResponse = SeachAndScan.this;
        setNdbnoNum(Integer.parseInt(n));

        as2.execute(url);
    }


    @Override
    public void scanNDBNO(String n) {

    String url = "https://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=oNi2a4fB62XkwkATu6Egz62zoHGQsV7aFcnNQqUd&nutrients=205&nutrients=204&nutrients=208&nutrients=269&ndbno="+n;
    getnutrientData as2 = new getnutrientData();
    as2.asyncResponse = SeachAndScan.this;
    setNdbnoNum(Integer.parseInt(n));
    //String url = "https://api.nal.usda.gov/ndb/search/?format=json&q=081864202168&sort=n&max=25&offset=0&api_key=oNi2a4fB62XkwkATu6Egz62zoHGQsV7aFcnNQqUd";
    as2.execute(url);

    }

    @Override
    public void getNutrients(String[] n) {
        Intent intent = new Intent(SeachAndScan.this,displayNutrient.class);
        intent.putExtra("nutrientsData",n);
        startActivity(intent);



    }



    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler{
        @Override
        public void handleResult(Result result){
            String resultCode = result.getText();
            Toast.makeText(SeachAndScan.this,resultCode,Toast.LENGTH_SHORT).show();
            callForNDBNO(resultCode);
            setContentView(R.layout.activity_search_and_scan);
            scannerView.stopCamera();

        }
    }

    private class getapiData extends AsyncTask<Object, Object, LinkedList> {
        int ndbno;
        int size;
        LinkedList list;
        public asyncResponse asyncResponse =null;

        public getapiData(int n) {
            this.ndbno = n;

        }

        @Override
        protected LinkedList doInBackground(Object... strings) {
            list = new LinkedList();

            try {
                URL url = new URL((String) strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                JSONObject topLevel = new JSONObject(builder.toString());
                JSONObject main = topLevel.getJSONObject("list");
                JSONArray food = main.getJSONArray("item");



                size = Integer.parseInt(main.getString("total"));

                if(size>20)
                    size=20;

                for(int i = 0; i<size; i++){
                    Node newNode = new Node();
                    JSONObject foodNDBOS = food.getJSONObject(i);
                    newNode.setNDBNO(foodNDBOS.getString("ndbno"));
                    newNode.setName(foodNDBOS.getString("name"));
                    list.add(newNode);

                }



                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return list;
        }


        protected void onPostExecute(LinkedList list) {

            asyncResponse.searchNDBNO(list,size);

        }
    }

    private class scanUPC extends AsyncTask<String, Void, String > {
        int ndbno;

        String ndbonoNum;
        public asyncResponse asyncResponse =null;

        public scanUPC(int n) {
            this.ndbno = n;

        }

        @Override
        protected String doInBackground(String... strings) {


            try {
                URL url = new URL((String) strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                JSONObject topLevel = new JSONObject(builder.toString());
                JSONObject main = topLevel.getJSONObject("list");
                JSONArray food = main.getJSONArray("item");

                JSONObject foodNDBO = food.getJSONObject(0);

                ndbonoNum = foodNDBO.getString("ndbno");




                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return ndbonoNum;
        }


        protected void onPostExecute(String n) {
            asyncResponse.scanNDBNO(n);

        }
    }



    private class getnutrientData extends AsyncTask<String, Void, String []> {
        StringBuilder s;
        public asyncResponse asyncResponse =as;

        public getnutrientData() {


        }

        @Override
        protected String[] doInBackground(String... strings) {
            String calorie = "";
            String sugar = "";
            String fat = "";
            String carbs ="";
            String weather ="";
            String [] arr = new String[5];
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                JSONObject topLevel = new JSONObject(builder.toString());
                JSONObject main = topLevel.getJSONObject("report");
                JSONArray food = main.getJSONArray("foods");
                JSONObject ndbo = food.getJSONObject(0);
                JSONArray nutrients = ndbo.getJSONArray("nutrients");
                arr[0] = ndbo.getString("name");
                //JSONObject values = nutrients.getJSONObject(0);
                for(int i=0; i<4; i++) {
                    JSONObject values = nutrients.getJSONObject(i);
                    arr[i+1]  = values.getString("value");
                }




                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return arr;
        }


        @Override
        protected void onPostExecute(String []temp) {
            asyncResponse.getNutrients(temp);

        }
    }





}
