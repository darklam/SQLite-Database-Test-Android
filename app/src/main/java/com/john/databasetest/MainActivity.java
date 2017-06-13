package com.john.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText nameInput, ageInput;
    private Button addDb;
    private SQLiteDatabase db;
    private AgeDbHelper helper;
    private TextView dbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameInput = (EditText) findViewById(R.id.nameInput);
        ageInput = (EditText) findViewById(R.id.ageInput);
        dbView = (TextView) findViewById(R.id.databaseView);
        addDb = (Button) findViewById(R.id.addButton);
        helper = new AgeDbHelper(this.getApplicationContext());
        db = helper.getWritableDatabase();
        addDb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ContentValues val = new ContentValues();
                val.put(AgeContract.AgeEntry.COLUMN_NAME_NAME, nameInput.getText().toString());
                val.put(AgeContract.AgeEntry.COLUMN_NAME_AGE, Float.parseFloat(ageInput.getText().toString()));
                db.insert(AgeContract.AgeEntry.TABLE_NAME, null, val);
                String[] proj = {
                        AgeContract.AgeEntry._ID,
                        AgeContract.AgeEntry.COLUMN_NAME_AGE,
                        AgeContract.AgeEntry.COLUMN_NAME_NAME
                };
                Cursor cur = db.query(
                        AgeContract.AgeEntry.TABLE_NAME,
                        proj,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                String res = "";
                while(cur.moveToNext()){
                    int indexName = cur.getColumnIndexOrThrow(AgeContract.AgeEntry.COLUMN_NAME_NAME);
                    int indexAge = cur.getColumnIndexOrThrow(AgeContract.AgeEntry.COLUMN_NAME_AGE);
                    res += cur.getString(indexName) + " " + Float.toString(cur.getFloat(indexAge)) + "\n";
                }
                cur.close();
                dbView.setText(res);
            }
        });
    }

    @Override
    protected void onDestroy(){
        helper.deleteDatabase(db);
        helper.close();
        db.close();
        super.onDestroy();
    }
}
