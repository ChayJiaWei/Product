package com.example.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        btn_add.setOnClickListener {
            if(name.text.toString().length > 0 &&
            quantity.text.toString().length > 0 && id.text.toString().length>0){
                var product =Product(id.text.toString().toInt(),name.text.toString(),quantity.text.toString().toInt())
                var db =  dataHandler(context)
                db.insertData(product)

            }
            else{
            Toast.makeText(context,"Please Fill All Data",Toast.LENGTH_SHORT).show()
        }
        }

        btn_view.setOnClickListener {
            var db =  dataHandler(context)
            var data = db.readData()
            tvResult.text=""
            for (i in 0..(data.size-1)){
                tvResult.append(data.get(i).id.toString()  +  " " + data.get(i).name +" " + data.get(i).quantity )
            }
        }


    }
}
