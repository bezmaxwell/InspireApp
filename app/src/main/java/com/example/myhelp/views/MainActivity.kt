package com.example.myhelp.views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myhelp.R
import com.example.myhelp.mock.Mock
import com.example.myhelp.util.MotivationConstants
import com.example.myhelp.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mFilter: Int = MotivationConstants.PHRASE_FILTER.ALL
    private lateinit var mSecurityPreferences: SecurityPreferences
    private val mMock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)

        //Events
        setListeners()

        //Inicialize
        handleFilter(R.id.imageAll)
        refreshPhrase()
        verifyUserName()
    }

    override fun onClick(view: View?) {
        val id = view?.id

        val listId = listOf(R.id.imageHappy,R.id.imageAll,R.id.imageSun)
        if(id in listId) {
            if (id != null) {
                handleFilter(id)
            }
        }else if (id == R.id.buttonNewPhrase) {
            refreshPhrase()
        }
    }

    private fun setListeners() {
        imageHappy.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageSun.setOnClickListener(this)
        buttonNewPhrase.setOnClickListener(this)
    }

    private fun verifyUserName() {
        textUserName.text = mSecurityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
    }

    @SuppressLint("ResourceType")
    private fun handleFilter(id :Int) {

        imageHappy.setImageResource(R.drawable.ic_unselectedhappy)
        imageAll.setImageResource(R.drawable.ic_unselectedschedule)
        imageSun.setImageResource(R.drawable.ic_unselectedsun)

        if(id == R.id.imageHappy) {
            mFilter = MotivationConstants.PHRASE_FILTER.HAPPY
            imageHappy.setImageResource(R.drawable.ic_selectedhappy)


        }else if (id == R.id.imageAll) {
            mFilter = MotivationConstants.PHRASE_FILTER.ALL
            imageAll.setImageResource(R.drawable.ic_selectedschedule)
        }else if (id == R.id.imageSun) {
            mFilter = MotivationConstants.PHRASE_FILTER.SUN
            imageSun.setImageResource(R.drawable.ic_selectedsun)
        }
    }

    private fun refreshPhrase() {
        textPhrase.text = mMock.getPhrase(mFilter)
    }
}
