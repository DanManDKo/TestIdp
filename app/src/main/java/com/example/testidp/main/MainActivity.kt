package com.example.testidp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testidp.R
import com.example.testidp.container.ContainerActivity
import com.example.testidp.enums.MainItemType

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var recycler: RecyclerView? = null
    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
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
}