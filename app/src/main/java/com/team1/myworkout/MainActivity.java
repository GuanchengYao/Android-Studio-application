package com.team1.myworkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team1.myworkout.constant.SQLCommand;
import com.team1.myworkout.util.AssetsDatabaseManager;
import com.team1.myworkout.util.DBOperator;

/**
 * Created by Guancheng on 2017/11/26.
 */

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button loadButton;
    private Button registerButton;
    private EditText userName;
    private EditText password;

    private String qurySql1="select * from User_log";
    private String userName1;
    private String password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        //

        userName=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText3);
        loadButton = (Button) findViewById(R.id.button);
        registerButton = (Button) findViewById(R.id.button2);
        loadButton.setTag(1);
        loadButton.setOnClickListener(this);
        registerButton.setTag(2);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        switch (tag) {
            case 1:
                Intent intent1=new Intent(MainActivity.this,FirstpageActivity.class);
                intent1.putExtra("userName",userName.getText().toString());
                intent1.putExtra("password",password.getText().toString());
                if (idCorrect()){
                    startActivity(intent1);
                }
                break;

            case 2:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }

    public boolean idCorrect(){
        /*
        AssetsDatabaseManager.initManager(getApplication());
        // 获取管理对象，因为数据库需要通过管理对象才能够获取
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        // 通过管理对象获取数据库
        SQLiteDatabase db1 = mg.getDatabase("gym.db");
        // 对数据库进行操作
        //db1.execSQL("insert into User_log([UI_id],[UL_name],[UL_password]) values('1','1','1');");
        //myOpenHelper=new MyOpenHelper(this,"gym.db",null,1);/////////////////
        //database=myOpenHelper.getWritableDatabase();
        Cursor cursor=db1.rawQuery(qurySql, null)*/;
        Cursor cursor=DBOperator.getInstance().execQuery(qurySql1);
        //DBOperator.getInstance().execSQL("insert into User_log([UI_id],[UL_name],[UL_password]) values('5','5','5');");
        //Cursor cursor=database.rawQuery(qurySql,null);
        while(cursor.moveToNext()){
            userName1=cursor.getString(cursor.getColumnIndex("UL_name"));
            password1=cursor.getString(cursor.getColumnIndex("UL_password"));
            if (userName1.equals(userName.getText().toString())&&password1.equals(password.getText().toString())){
                return true;
            }
        }
        Toast.makeText(this,"Wrong account or password, please retype",Toast.LENGTH_LONG).show();
        return false;

    }

}

