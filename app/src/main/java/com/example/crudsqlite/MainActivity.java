package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static DbHelper dbHelper;
    ArrayList<String> arrayListName;
    ArrayList<Integer> arrayListKode;
    ListView listView;
    Button btnAdd;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(MainActivity.this);
        arrayListKode = new ArrayList<>();
        arrayListName = new ArrayList<>();
        btnAdd = findViewById(R.id.btnNewList);
        listView = findViewById(R.id.listData);

        Cursor cursor = dbHelper.showData();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                arrayListKode.add(Integer.parseInt(cursor.getString(0)));
                arrayListName.add(cursor.getString(1));
            }
        }
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, arrayListName);
        listView.setAdapter(arrayAdapter);

        registerForContextMenu(listView);
    }

    public void tambahData(View view) {
        Intent intent = new Intent(this, CreateGame.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int index = info.position;
        menu.setHeaderTitle("Detail");
        menu.add(index, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Delete") {
            dbHelper.deleteData(Integer.toString(arrayListKode.get(item.getGroupId())));
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        } else {
            return false;
        }
        return super.onContextItemSelected(item);
    }
}