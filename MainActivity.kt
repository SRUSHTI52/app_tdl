package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var text : EditText
    lateinit var button: Button
    lateinit var list: ListView

    var itemlist = ArrayList<String>()
    var file = File()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.text1)
        button = findViewById(R.id.button)
        list = findViewById(R.id.list)
        itemlist = file.readdata(this)
        val arrayAdapter = ArrayAdapter(this, R.layout.list_item_layout, itemlist)
        list.adapter = arrayAdapter
        button.setOnClickListener {
            var itemname : String = text.text.toString()
        itemlist.add(itemname)
        text.setText("")
        file.writedata(itemlist, applicationContext)
        arrayAdapter.notifyDataSetChanged()}

        list.setOnItemClickListener { adapterView, view, position, l ->

            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do you  want to delete this item from the list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener{dialogInterface, i ->
                itemlist.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                file.writedata(itemlist, applicationContext)
            })

            alert.create().show()

        }

        val deleteAllButton: Button = findViewById(R.id.delete)
        deleteAllButton.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Delete All")
            alert.setMessage("Do you want to delete all items from the list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            alert.setPositiveButton("Yes") { _, _ ->
                itemlist.clear()
                arrayAdapter.notifyDataSetChanged()
                file.writedata(itemlist, applicationContext)
            }
            alert.create().show()
        }



    }

}
