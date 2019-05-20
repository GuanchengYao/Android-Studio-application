package com.team1.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.team1.myworkout.util.DBOperator;
import android.view.View.OnClickListener;

/**
 * Created by Guancheng on 2017/12/1.
 */

public class FirstpageActivity extends AppCompatActivity implements OnClickListener {
    private TextView userName;
    private TextView password;

    private Button firstButton;
    private Button secondButton;
    private Button thirdButton;
    private Button fourthButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        userName=(TextView)findViewById(R.id.userNameShow);
        Intent intent=getIntent();
        userName.setText("username:"+intent.getStringExtra("userName"));

        firstButton=(Button)this.findViewById(R.id.button10);
        firstButton.setOnClickListener(this);
        secondButton=(Button)this.findViewById(R.id.button11);
        secondButton.setOnClickListener(this);
        thirdButton=(Button)this.findViewById(R.id.button12);
        thirdButton.setOnClickListener(this);
        fourthButton=(Button)this.findViewById(R.id.button13);
        fourthButton.setOnClickListener(this);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onClick(View v)
    {
        Intent intent1 = getIntent();
        String nm = intent1.getStringExtra("userName");
        int id=v.getId();
        if (id==R.id.button10){
            Intent intent = new Intent(this, MakePlanActivity.class);
            intent.putExtra("userName", nm);
            this.startActivity(intent);
        }else if (id==R.id.button11){
            Intent intent = new Intent(this, FindMachineActivity.class);
            intent.putExtra("userName", nm);
            this.startActivity(intent);
        }else if (id==R.id.button12){
            Intent intent = new Intent(this, QueryActivity.class);
            intent.putExtra("userName", nm);
            this.startActivity(intent);
        }else if (id==R.id.button13) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }

}
