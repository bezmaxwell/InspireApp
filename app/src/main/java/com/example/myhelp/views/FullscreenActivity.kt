package com.example.myhelp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myhelp.R
import com.example.myhelp.util.MotivationConstants
import com.example.myhelp.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_fullscreen.*

class FullscreenActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var mSecurity:SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        mSecurity = SecurityPreferences(this)

        buttonSave.setOnClickListener(this)

        verifyUserName()
    }

    override fun onClick(view: View) {
        val id = view.id
        if(id == R.id.buttonSave){
            handleSave()
        }
    }

    private fun verifyUserName() {
        val userName = mSecurity.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        if(userName != "") {
            val intent1: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }
        editName.setText(userName)
    }

    private fun handleSave() {
        val name: String = editName.text.toString()

        if (name == "") {
            Toast.makeText(this, getString(R.string.informe_seu_nome), Toast.LENGTH_LONG).show()
        } else {
            mSecurity.storeString(MotivationConstants.KEY.PERSON_NAME, name)

            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}