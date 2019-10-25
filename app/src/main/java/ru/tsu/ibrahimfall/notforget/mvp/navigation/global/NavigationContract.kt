package ru.tsu.ibrahimfall.notforget.mvp.navigation.global

import ru.tsu.ibrahimfall.notforget.mvp.navigation.fragment.NavigationFragment

interface NavigationContract {

    interface Manager {
        fun attachBaseFragment(fragment: NavigationFragment)
        fun attachFragment(fragment: NavigationFragment)
        fun requestBackPress()
    }

    interface Fragment {
        var name: String
        fun attachNavigationManager(navigationManager: Manager)
    }

    interface BackPressHandler {
        fun onBackPressed(): Boolean
    }

    interface SelectionListener {
        fun onSelected()
    }
}