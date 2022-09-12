package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.activity

import android.os.Bundle
import android.util.Log
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import org.koin.android.ext.android.inject
import ru.geekbrains.ponomarevss.sportradarnflnotes.R
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ActivityMainBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.MainView
import ru.geekbrains.ponomarevss.sportradarnflnotes.utils.OnlineLiveData

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private val navigatorHolder: NavigatorHolder by inject()
    private val router: Router by inject()
    private val screens: IScreens by inject()

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        if (savedInstanceState == null) {
            router.replaceScreen(screens.seasons())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}