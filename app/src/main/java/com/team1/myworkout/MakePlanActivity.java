package com.team1.myworkout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.team1.myworkout.util.DBOperator;
import android.view.View.OnClickListener;
import com.team1.myworkout.constant.SQLCommand;

/**
 * Created by Guancheng on 2017/12/3.
 */

public class MakePlanActivity extends AppCompatActivity implements OnClickListener{

    private Button confirm;
    private Button goback;
    //private EditText name;
    private EditText machine;
    private EditText time;
    //private EditText weekday;
    private DatePicker datePicker;
    private String qury1="select * from Plan";
    private String qury2="select * from Machine";
    private String planid1;
    private String machineid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeplan);
        //Intent intent=getIntent();
        //String un = intent.getStringExtra("userName");
        //name = (EditText) this.findViewById(R.id.make11);
        machine = (EditText) this.findViewById(R.id.make21);
        time = (EditText) this.findViewById(R.id.make31);
        //weekday = (EditText) this.findViewById(R.id.make41);
        datePicker = (DatePicker) this.findViewById(R.id.datePicker);
        confirm = (Button) this.findViewById(R.id.button14);
        confirm.setOnClickListener(this);
        goback = (Button) this.findViewById(R.id.button15);
        goback.setOnClickListener(this);

    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button14) {
            if(machineidCorrect()) {
                if(insertToPlanCorrect()) {
                    insertDataToPlan();
                    Toast.makeText(getBaseContext(), "Make a plan successfully",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } else if (id == R.id.button15) {
            Intent intent = getIntent();
            String nm = intent.getStringExtra("userName");
            Intent intent1 = new Intent(this, FirstpageActivity.class);
            intent1.putExtra("userName", nm);
            this.startActivity(intent1);
        }
    }

    public void insertDataToPlan(){
        Intent intent=getIntent();
        int month = 1+datePicker.getMonth();
        String sql="insert into Plan values" +
                " ('"+intent.getStringExtra("userName")+machine.getText().toString()+datePicker.getYear()+month+datePicker.getDayOfMonth()
                +time.getText().toString()+"','"+intent.getStringExtra("userName")+"','"+machine.getText().toString()+"','"+month+"'," +
                "'"+datePicker.getDayOfMonth()+"','"+datePicker.getYear()+"','"+time.getText().toString()+"','');";
        DBOperator.getInstance().execSQL(sql);
        //SQLCommand.i++;
    }

    public boolean insertToPlanCorrect(){
        Intent intent=getIntent();
        int month = 1+datePicker.getMonth();
        Cursor cursor=DBOperator.getInstance().execQuery(qury1);
        while(cursor.moveToNext()){
            planid1=cursor.getString(cursor.getColumnIndex("P_id"));
            if (planid1.equals(intent.getStringExtra("userName")+machine.getText().toString()+datePicker.getYear()+month+datePicker.getDayOfMonth()
                    +time.getText().toString())){
                Toast.makeText(this,"The plan has been made, please retype",Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    public boolean machineidCorrect(){
        Cursor cursor=DBOperator.getInstance().execQuery(qury2);
        while(cursor.moveToNext()){
            machineid1=cursor.getString(cursor.getColumnIndex("M_id"));
            if (machineid1.equals(machine.getText().toString())){
                return true;
            }
        }
        Toast.makeText(this,"The machine id is wrong, please retype",Toast.LENGTH_LONG).show();
        return false;
    }
}
