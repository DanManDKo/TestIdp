package com.example.testidp.container

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.testidp.R
import com.example.testidp.enums.MainItemType
import com.example.testidp.maps.MapsFragment

class ContainerActivity : AppCompatActivity(R.layout.activity_container) {
    companion object {
        private const val EXTRA_KEY_SCREEN = "EXTRA_KEY_SCREEN"

        fun getIntent(context: Context, screen: MainItemType): Intent {
            return Intent(context, ContainerActivity::class.java).apply {
                putExtra(EXTRA_KEY_SCREEN, screen)
            }
        }
    }

    private var container: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setupUi()
        val screen = intent.getSerializableExtra(EXTRA_KEY_SCREEN) as MainItemType
        showSelectedScreen(screen)
    }

    private fun setupUi() {
        container = findViewById(R.id.container)
    }

    private fun showSelectedScreen(screen: MainItemType) {
        when (screen) {
            MainItemType.MAP -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MapsFragment.newInstance())
                    .commit()
            }
        }
    }
}