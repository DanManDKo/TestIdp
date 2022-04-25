package com.example.testidp.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testidp.R
import com.example.testidp.container.ContainerActivity
import com.example.testidp.enums.MainItemType
import com.example.testidp.utils.constants.ActionConstants.ACTION_SHOW_TOAST
import com.example.testidp.utils.constants.ExtraConstants.EXTRA_TOAST_TEXT

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var recycler: RecyclerView? = null
    private var adapter: MainAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setupUi()
        processIntentAction(intent)
    }

    private fun setupUi() {
        recycler = findViewById(R.id.recycler)

        adapter = MainAdapter { processItemClick(it) }
        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(this)
    }

    private fun processItemClick(itemType: MainItemType) {
        val intent = ContainerActivity.getIntent(this, itemType)
        startActivity(intent)
    }

    private fun processIntentAction(intent: Intent) {
        when (intent.action) {
            ACTION_SHOW_TOAST -> showToast(intent)
            else -> {
            }
        }
    }

    private fun showToast(intent: Intent) {
        intent.getStringExtra(EXTRA_TOAST_TEXT)?.let {
            showToast(it)
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}