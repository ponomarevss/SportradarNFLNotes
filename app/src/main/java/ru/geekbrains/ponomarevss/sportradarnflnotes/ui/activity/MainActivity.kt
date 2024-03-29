package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.R
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ActivityMainBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IConferencesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.MainPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.MainView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backPressed()
    }
}