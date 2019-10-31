package ru.tsu.ibrahimfall.notforget.mvp.navigation.fragment

import ru.tsu.ibrahimfall.notforget.mvp.navigation.global.NavigationContract.Fragment
import ru.tsu.ibrahimfall.notforget.mvp.navigation.global.NavigationContract.Manager

abstract class NavigationFragment : BaseFragment(), Fragment {

    protected lateinit var navigationManager: Manager

    override fun attachNavigationManager(navigationManager: Manager) {
        this.navigationManager = navigationManager
    }


}