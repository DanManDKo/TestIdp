package com.example.testidp.container

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import com.example.testidp.R
import com.example.testidp.base.BaseActivity
import com.example.testidp.enums.MainItemType
import com.example.testidp.features.animations.AnimationsFragment
import com.example.testidp.features.delegate.CustomDelegateFragment
import com.example.testidp.features.mappers.MappersFragment
import com.example.testidp.features.maps.MapsFragment
import com.example.testidp.features.notifications.NotificationFragment
import com.example.testidp.features.recycler.RecyclerFragment
import com.example.testidp.features.workmanager.WorkManagerFragment
import com.example.testidp.utils.normalize

class ContainerActivity : BaseActivity(R.layout.activity_container) {
    companion object {
        private const val EXTRA_KEY_SCREEN = "EXTRA_KEY_SCREEN"

        fun getIntent(context: Context, screen: MainItemType): Intent {
            return Intent(context, ContainerActivity::class.java).apply {
                putExtra(EXTRA_KEY_SCREEN, screen)
            }
        }
    }

    private var container: FragmentContainerView? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        val screen = intent.getSerializableExtra(EXTRA_KEY_SCREEN) as MainItemType
        initToolbar(screen)
        showSelectedScreen(screen)
    }

    private fun setupUi() {
        container = findViewById(R.id.container)
        toolbar = findViewById(R.id.toolbar)
    }

    private fun initToolbar(type: MainItemType) {
        toolbar?.title = type.name.normalize()
        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun showSelectedScreen(screen: MainItemType) {
        val fragment = when (screen) {
            MainItemType.MAP -> MapsFragment.newInstance()
            MainItemType.DELEGATE -> CustomDelegateFragment.newInstance()
            MainItemType.RECYCLER -> RecyclerFragment.newInstance()
            MainItemType.MAPPERS -> MappersFragment.newInstance()
            MainItemType.WORK_MANAGER -> WorkManagerFragment.newInstance()
            MainItemType.NOTIFICATIONS -> NotificationFragment.newInstance()
            MainItemType.ANIMATIONS -> AnimationsFragment.newInstance()
        }
        setFragment(fragment)
    }

}