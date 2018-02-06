package com.vogella.android.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.vogella.android.testapp.R.id.female
import com.vogella.android.testapp.R.id.male
import kotlinx.android.synthetic.main.activity_create_user.*

class MainActivity : AppCompatActivity() {
    var male = true
    var female = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        var maleButton = findViewById<RadioButton>(R.id.male)
        var femaleButton = findViewById<RadioButton>(R.id.female)
        var radioGroup = findViewById<RadioGroup>(R.id.gender)
        val floa: Float = .5F
        femaleButton.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked)
            {
                female = true
                male = false
                maleButton.alpha = .5F
                femaleButton.alpha = 1F
                Log.i("radioGroup", "female selected")
            }
        }
        maleButton.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked)
            {
                female = false
                male = true

                femaleButton.alpha = .5F
                maleButton.alpha = 1F
                Log.i("radioGroup", "male selected")
            }
        }
    }

    fun onClick(view: View){
        var editText = findViewById<EditText>(R.id.username) as EditText
        if(male == true)
        {
            Toast.makeText(this, "Male User " + editText.text + " created", Toast.LENGTH_LONG).show()
        }
        if(female == true)
        {
            Toast.makeText(this, "Female User " + editText.text + " created", Toast.LENGTH_SHORT).show()
        }

        Log.e("Click","Yes")
    }
}
/*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Different message", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            if(greeting.text == "you've got no new mail yet"){
            greeting.text = "check again later"}
            else{
                greeting.text = "you've got no new mail yet"
            }
        }*/