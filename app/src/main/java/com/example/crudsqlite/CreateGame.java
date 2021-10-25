package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateGame extends AppCompatActivity {
    EditText newKode, newName, newGendre, newPlatform, newPrice;
    Button btnCreate, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        newKode = findViewById(R.id.editTextKode);
        newName = findViewById(R.id.editTextName);
        newGendre = findViewById(R.id.editTextGendre);
        newPlatform = findViewById(R.id.editTextPlatform);
        newPrice = findViewById(R.id.editTextPrice);
        btnCreate = findViewById(R.id.btnCreate);
        btnKembali = findViewById(R.id.btnCreateKembali);
    }

    public void createData(View view){
        String nwKode= newKode.getText().toString();
        String nwName= newName.getText().toString();
        String nwGendre = newGendre.getText().toString();
        String nwPlatform = newPlatform.getText().toString();
        String nwPrice = newPrice.getText().toString();
        boolean result = MainActivity.dbHelper.insertData(
                Integer.parseInt(newKode.getText().toString().trim()),
                nwName.trim(),
                nwGendre.trim(),
                nwPlatform.trim(),
                nwPrice.trim());
        if (result){
            Intent intent = new Intent(CreateGame.this, MainActivity.class);
            startActivity(intent);
            CreateGame.this.finish();
        }
    }

    public void kembali(View view){
        Intent intent = new Intent(CreateGame.this, MainActivity.class);
        startActivity(intent);
        CreateGame.this.finish();
    }
}