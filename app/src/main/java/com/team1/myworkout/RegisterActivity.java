package com.team1.myworkout;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team1.myworkout.util.AssetsDatabaseManager;
import com.team1.myworkout.util.DBOperator;

/**
 * Created by Guancheng on 2017/11/26.
 */

public class RegisterActivity extends AppCompatActivity implements OnClickListener {
    private Button register;
    private Button back;

    private String userName;
    private String password;
    private String qurySql2="select * from User_log";
    private int Tag=0;
    private EditText editUser;
    private EditText pwd;
    private EditText pwdAgain;
    private EditText last;
    private EditText middle;
    private EditText first;
    private EditText email;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        //

        editUser=(EditText)findViewById(R.id.reg_userText);
        pwd=(EditText)findViewById(R.id.reg_pwdText);
        pwdAgain=(EditText)findViewById(R.id.reg_pwdagText);

        last=(EditText)findViewById(R.id.lasttext);
        middle=(EditText)findViewById(R.id.middletext);
        first=(EditText)findViewById(R.id.firsttext);
        email=(EditText)findViewById(R.id.emailtext);
        phone=(EditText)findViewById(R.id.phonetext);

        register= (Button)findViewById(R.id.button3);
        register.setTag(1);
        register.setOnClickListener(this);
        back=(Button)findViewById(R.id.button4);
        back.setTag(2);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int Tag=(int)v.getTag();
        switch (Tag){
            case 1:
                if(nameCorrect()) {
                    if(pwdCorrect()) {
                        insertData();
                        Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case 2:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(RegisterActivity.this,"Registration failed, please try again",Toast.LENGTH_LONG).show();
                break;

        }

    }

    public void insertData(){
        /*
        AssetsDatabaseManager.initManager(getApplication());
        // 获取管理对象，因为数据库需要通过管理对象才能够获取
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        // 通过管理对象获取数据库
        SQLiteDatabase db1 = mg.getDatabase("gym.db");
        */
        String insertSql="insert into User_log (UL_name,UL_password) values ('"+editUser.getText().toString()+"','"+pwd.getText().toString()+"');";
        DBOperator.getInstance().execSQL(insertSql);
        String insertSql2 = "insert into User_info values ('"+editUser.getText().toString()+"','"+last.getText().toString()+"','"+middle.getText().toString()+"'," +
                                                          "'"+first.getText().toString()+"','"+email.getText().toString()+"','"+phone.getText().toString()+"','1');" ;
        DBOperator.getInstance().execSQL(insertSql2);
        //String a = "delete from User_log where UI_id = '' ";
        //DBOperator.getInstance().execSQL(a);
        //db1.execSQL(insertSql);
        //db1.execSQL("insert into User_log([UI_id],[UL_name],[UL_password]) values('2','2','2');");
        //String insertSql = "insert into userInfo（userId, password） values ('"+editUser.getText().toString()+"','"+pwd.getText().toString()+"');";
        //String insertSql = "insert into User_log（UI_id, UL_name, UL_password） values (?,?,?)";
        //DBOperator.getInstance().execSQL(insertSql, this.getArgs(true));

    }

    /*private String[] getArgs(boolean is) {
        String[] args = null;
        if (is) {
            String j = "2";
            args = new String[4];
            //args[0] = j;
            args[0] = editUser.getText().toString();
            args[1] = pwd.getText().toString();
        }
        return args;
    }*/

    public boolean pwdCorrect(){
        if(!pwd.getText().toString().equals("")) {
            if (pwd.getText().toString().equals(pwdAgain.getText().toString())) {
                return true;
            }
            Toast.makeText(RegisterActivity.this, "The two password input is not consistent, please reenter it.", Toast.LENGTH_LONG).show();
            return false;
        }
        Toast.makeText(RegisterActivity.this, "The password can not be empty", Toast.LENGTH_LONG).show();
        return false;
    }

    public boolean nameCorrect(){
        Cursor cursor=DBOperator.getInstance().execQuery(qurySql2);
        while(cursor.moveToNext()){
            userName=cursor.getString(cursor.getColumnIndex("UL_name"));
            if (userName.equals(editUser.getText().toString())){
                Toast.makeText(this,"The username has been used",Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if(!editUser.getText().toString().equals("")) {
            return true;
        }
        Toast.makeText(this,"The username can not be empty",Toast.LENGTH_LONG).show();
        return false;
    }
}
