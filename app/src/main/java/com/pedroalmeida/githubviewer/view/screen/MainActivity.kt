package com.pedroalmeida.githubviewer.view.screen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pedroalmeida.githubviewer.R
import com.pedroalmeida.githubviewer.util.AppConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        btn_search.setOnClickListener {
            //Show Profile Activity
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(AppConstants.USERNAME_INTENT_EXTRA, edit_text_username.text.toString())

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        edit_text_username.text.clear()
    }
}
