package ru.tsu.ibrahimfall.notforget.app

import android.os.Bundle
import ru.tsu.ibrahimfall.notforget.R
import ru.tsu.ibrahimfall.notforget.mvp.navigation.activity.NavigationActivity

class MainActivity : NavigationActivity() {

    override val rootId: Int = R.id.root
    override val layoutId: Int = R.layout.activity_main

    override fun beforeViewCreated(savedInstanceState: Bundle?) {
        super.beforeViewCreated(savedInstanceState)
        theme.applyStyle(R.style.AppTheme, true)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        attachBaseFragment(Application.getLaunchFragment())
    }

}
