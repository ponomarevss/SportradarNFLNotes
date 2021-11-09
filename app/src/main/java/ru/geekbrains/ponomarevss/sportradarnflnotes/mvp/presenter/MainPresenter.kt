package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.MainView

class MainPresenter: MvpPresenter<MainView>() {

//    @Inject lateinit var router: Router
//    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        router.replaceScreen(screens.games())
    }

//    fun backClick() = router.exit()
}