package com.team1.myworkout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.team1.myworkout.constant.SQLCommand;
import com.team1.myworkout.util.DBOperator;
import com.team1.myworkout.view.TableView;


public class QueryActivity extends AppCompatActivity implements View.OnClickListener {

    Button backBtn,resultBtn;
    Spinner querySpinner;
    ScrollView scrollView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        backBtn=(Button)this.findViewById(R.id.goBack_btn);
        backBtn.setOnClickListener(this);
        resultBtn=(Button)this.findViewById(R.id.showresult_btn);
        resultBtn.setOnClickListener(this);
        querySpinner=(Spinner)this.findViewById(R.id.querylist_spinner);
        scrollView=(ScrollView)this.findViewById(R.id.scrollView);
    }

    public void onClick(View v)
    {
        String sql="";
        int id=v.getId();
        if (id==R.id.showresult_btn){
            //show query result
            int pos=querySpinner.getSelectedItemPosition();
            if (pos==Spinner.INVALID_POSITION){
                //User doesn't choose any query, show warning
                Toast.makeText(this.getBaseContext(), "Please choose a query!", Toast.LENGTH_SHORT).show();
                return;
            }
            scrollView.removeAllViews();
            if (pos==0){
                Intent intent = getIntent();
                String plan = intent.getStringExtra("userName");
                sql = "select UL_name, count(*) from Plan where UL_name = '"+plan+"';";
                //sql= SQLCommand.QUERY_1;
            }else if (pos==1){
                sql=SQLCommand.QUERY_2;
            }else if (pos==2){
                sql=SQLCommand.QUERY_3;
            }else if (pos==3){
                sql=SQLCommand.QUERY_4;
            }else if (pos==4){
                sql=SQLCommand.QUERY_5;
            }else if (pos==5){
                sql=SQLCommand.QUERY_6;
            }else if (pos==6){
                sql=SQLCommand.QUERY_7;
            }else if (pos==7){
                sql=SQLCommand.QUERY_8;
            }else if (pos==8){
                sql=SQLCommand.QUERY_9;
            }else if (pos==9){
                sql=SQLCommand.QUERY_10;
            }else if (pos==10){
                sql=SQLCommand.QUERY_11;
            }else if (pos==11){
                sql=SQLCommand.QUERY_12;
            }else if (pos==12){
                sql=SQLCommand.QUERY_13;
            }else if (pos==13){
                sql=SQLCommand.QUERY_14;
            }else if (pos==14){
                sql=SQLCommand.QUERY_15;
            }else if (pos==15){
                sql=SQLCommand.QUERY_16;
            }else if (pos==16){
                Intent intent = getIntent();
                String machineUesd = intent.getStringExtra("userName");
                sql = "select distinct M_name from Machine, Plan where Machine.M_id = Plan.M_id and UL_name = '"+machineUesd+"';";
            }
            Cursor cursor=DBOperator.getInstance().execQuery(sql);
            scrollView.addView(new TableView(this.getBaseContext(),cursor));
        }
        else if (id==R.id.goBack_btn){
            Intent intent = getIntent();
            String nm = intent.getStringExtra("userName");
            Intent intent1 = new Intent(this, FirstpageActivity.class);
            intent1.putExtra("userName", nm);
            this.startActivity(intent1);
        }

    }
}