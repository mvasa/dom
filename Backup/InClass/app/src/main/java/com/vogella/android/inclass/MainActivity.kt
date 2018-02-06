package com.vogella.android.inclass

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.vogella.android.inclass.R.drawable.green
import com.vogella.android.inclass.R.id.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
        visibility.setOnCheckedChangeListener{_, isChecked ->
                if(isChecked) textView.visibility = View.INVISIBLE else textView.visibility = View.VISIBLE
            }


        colorChange.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked) textView.setBackgroundColor(504477)
            else textView.setBackgroundColor(450055)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {

            R.id.action_settings -> true
            Help ->{
                textView.text = "This app changes the text in the text view when the help button is clicked!"
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
