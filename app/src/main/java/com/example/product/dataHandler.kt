package com.example.product

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASE_NAME = "myDB"
val TABLE_NAME="Product"
val COL_NAME="name"
val COL_ID="id"
val COL_QUANTITY="quantity"


class dataHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTABLE = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(250)," +
                COL_QUANTITY + " INTEGER";

        db?.execSQL(createTABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(product: Product){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ID,product.id)
        cv.put(COL_NAME,product.name)
        cv.put(COL_QUANTITY,product.quantity)
        var result = db.insert(TABLE_NAME,null,cv)
        if (result == -1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
    }
    fun readData() : MutableList<Product>{
        var list : MutableList<Product> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                var product = Product()
                product.id=result.getString(result.getColumnIndex(COL_ID)).toInt()
                product.name=result.getString(result.getColumnIndex(COL_NAME)).toString()
                product.quantity=result.getString(result.getColumnIndex(COL_QUANTITY)).toInt()
                list.add(product)
            }while (result.moveToNext())
        }

    result.close()
        db.close()
        return list
    }


}