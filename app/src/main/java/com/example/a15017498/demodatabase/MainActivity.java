package com.example.a15017498.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnGetTask;
    TextView tvResults;
    ListView listview;
    ArrayAdapter aa;
    ArrayList<Task> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResults = (TextView) findViewById(R.id.tvResults);
        listview = (ListView) findViewById(R.id.ListView);



        btnGetTask = (Button) findViewById(R.id.btnRetrieve);
//        btnGetTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create the DBHelper object, passing in the
//                // activity's Context
//                DBHelper db = new DBHelper(MainActivity.this);
//
//                // Insert a task
//                ArrayList<String> data = db.getTaskContent();
//                db.close();
//
//                String txt = "";
//                for (int i = 0; i < data.size(); i++) {
//                    Log.d("Database Content", i +". "+data.get(i));
//                    txt += i + ". " + data.get(i) + "\n";
//                }
//                tvResults.setText(txt);
//
//            }
//        });

        DBHelper db = new DBHelper(MainActivity.this);
        final ArrayList<Task> data = db.getTasks();
        db.close();
        aa = new TaskAdapter(MainActivity.this, R.layout.row, data);
        listview.setAdapter(aa);


        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<Task> array = db.getTasks();

                data.clear();
                for(int i=0;i<array.size();i++){
                    data.add(array.get(i));
                }
                aa.notifyDataSetChanged();
                db.close();
            }
        });



        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();

            }
        });

    }
}
