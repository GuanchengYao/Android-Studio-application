package com.team1.myworkout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.team1.myworkout.util.DBOperator;
import com.team1.myworkout.view.TableView;
import android.view.View.OnClickListener;

/**
 * Created by Guancheng on 2017/12/3.
 */

public class FindMachineActivity extends AppCompatActivity implements OnClickListener{

    private Button check;
    private Button goback;
    private EditText machine1;
    private String qury1="select * from Machine";
    private String machineName1;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findmachine);
        machine1 = (EditText) this.findViewById(R.id.machine_nm);
        check = (Button) this.findViewById(R.id.machineshow);
        check.setOnClickListener(this);
        goback = (Button) this.findViewById(R.id.machineReturn);
        goback.setOnClickListener(this);
        scrollView=(ScrollView)this.findViewById(R.id.scrollView);

    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.machineshow) {
            if(machineNameCorrect()) {
                machineQurey();
            }
        } else if (id == R.id.machineReturn) {
            Intent intent = getIntent();
            String nm = intent.getStringExtra("userName");
            Intent intent1 = new Intent(this, FirstpageActivity.class);
            intent1.putExtra("userName", nm);
            this.startActivity(intent1);
        }
    }

    public void machineQurey(){
        Intent intent = getIntent();
        String nm = intent.getStringExtra("userName");
        String sql = "select MM, DD, YEAR, TIME from Plan, Machine where Plan.M_id = Machine.M_id and M_name = '"+machine1.getText().toString()+"' and UL_name = '"+intent.getStringExtra("userName")+"'";
        Cursor cursor=DBOperator.getInstance().execQuery(sql);
        scrollView.addView(new TableView(this.getBaseContext(),cursor));
    }

    public boolean machineNameCorrect(){
        Cursor cursor=DBOperator.getInstance().execQuery(qury1);
        while(cursor.moveToNext()){
            machineName1=cursor.getString(cursor.getColumnIndex("M_name"));
            if (machineName1.equals(machine1.getText().toString())){
                return true;
            }
        }
        Toast.makeText(this,"The machine name is wrong, please retype",Toast.LENGTH_LONG).show();
        return false;
    }
}
