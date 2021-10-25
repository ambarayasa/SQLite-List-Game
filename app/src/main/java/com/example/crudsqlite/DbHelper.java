package com.example.crudsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    Context mContext;

    public static final String database_name = "db_games";
    public static final int VERSION = 1;

    public DbHelper(Context context){
        super(context, database_name, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String sql = "CREATE TABLE tb_game (kode integer primary key,"+" " +
                    "nama_game text, gendre_game text, platform_game text, price_game text);";
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("error", "failed");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //menambah data

    public boolean insertData(int kode, String name, String gendre,
                              String platform, String price){
        try{
            String sql = "INSERT INTO tb_game VALUES ("+kode+",'"+name+"'," +
                    "'"+gendre+"','"+platform+"','"+price+"');";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            Toast.makeText(mContext,"Berhasil add data",
                    Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Cursor showData(){
        try{
            String sql = "SELECT * FROM tb_game";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public boolean deleteData(String kodeOld){
        try{
            String sql = "DELETE FROM tb_game WHERE kode = "+kodeOld+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            Toast.makeText(mContext,"Berhasil hapus data",
                    Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
