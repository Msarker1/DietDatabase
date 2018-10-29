package com.shaer.dietdatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActualMainAct extends AppCompatActivity {
    public void onClickScan(View view){
        startActivity(new Intent(ActualMainAct.this,SeachAndScan.class));
    }

    public void onClickGoalSettings(View view){
        startActivity(new Intent(ActualMainAct.this, SettingPage.class));

    }
    public void onClickProfileLog(View view){
        startActivity(new Intent(ActualMainAct.this,ProfileLog.class));
    }


    public void onClickPacer(View view){
        startActivity(new Intent(ActualMainAct.this,Pacer.class));
    }
    AnimationDrawable anim;
    ImageView rocketImage;
    public Button butt1;
    LinearLayout line1,line2;
    Animation abc,cba;

    public void startanim(){
        rocketImage = (ImageView) findViewById(R.id.imageView);
        //if (rocketImage=null) throw new AssertionError();
        rocketImage.setBackgroundResource(R.drawable.animation_2);
        anim = (AnimationDrawable) rocketImage.getBackground();
        anim.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_main);

        startanim();

        SharedPreferences storedWeightData = this.getSharedPreferences("com.shaer.dietdatabase", Context.MODE_PRIVATE);
        String testingEmptyData = storedWeightData.getString("StoredSpinnerWeight","");
        if (testingEmptyData == "") {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Goal settings Needed")
                    .setMessage("You have not set a Goal Yet. Please set one.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(ActualMainAct.this, SettingPage.class));
                        }
                    })
                    .show();
        }
    }
}
