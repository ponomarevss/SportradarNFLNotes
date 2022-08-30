package ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo

class SeasonsViewModel(private val repo: ISeasonsRepo): ViewModel() {
    init {
        Log.e("AAA", "viewModel created")
    }
    private val _mutableLiveData: MutableLiveData<List<Season>> = MutableLiveData()
    val liveDataForViewToObserve: LiveData<List<Season>> = _mutableLiveData

    fun getData() {
        _mutableLiveData.postValue(repo.getSeasons())
//        cancelJob()
    }

//    fun handleError(error: Throwable) {
//        _mutableLiveData.postValue(SeasonsAppState.Error(error))
//    }
//
    override fun onCleared() {
//        _mutableLiveData.value = SeasonsAppState.Success(null)
//        cancelJob()
//        super.onCleared()
        Log.e("AAA", "viewModel destroyed")
    }
//
//
//    private fun cancelJob() {
//        viewModelCoroutineScope.coroutineContext.cancelChildren()

                //для нижеописанного скоупа.
                //        protected val viewModelCoroutineScope = CoroutineScope(
                //            Dispatchers.Main
                //                    + SupervisorJob()
                //                    + CoroutineExceptionHandler { _, throwable ->
                //                handleError(throwable)
                //            })
                //проработать как себя ведет viewModelScope


//    }



}
//
//class MainViewModel(private val interactor: MainInteractor) :
//    BaseViewModel<AppState>() {
//
//    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
//
//    fun subscribe(): LiveData<AppState> {
//        return liveDataForViewToObserve
//    }
//
//    override fun getData(word: String, isOnline: Boolean) {
//        _mutableLiveData.value = AppState.Loading(null)
//        cancelJob()
//        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
//    }
//
//    //Doesn't have to use withContext for Retrofit call if you use .addCallAdapterFactory(CoroutineCallAdapterFactory()). The same goes for Room
//    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
//        _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
//    }
//
//    override fun handleError(error: Throwable) {
//        _mutableLiveData.postValue(AppState.Error(error))
//    }
//
//    override fun onCleared() {
//        _mutableLiveData.value = AppState.Success(null)
//        super.onCleared()
//    }
//}
//
//
//abstract class BaseViewModel<T : AppState>(
//    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
//) : ViewModel() {
//
//    protected val viewModelCoroutineScope = CoroutineScope(
//        Dispatchers.Main
//                + SupervisorJob()
//                + CoroutineExceptionHandler { _, throwable ->
//            handleError(throwable)
//        })
//
//    override fun onCleared() {
//        super.onCleared()
//        cancelJob()
//    }
//
//    protected fun cancelJob() {
//        viewModelCoroutineScope.coroutineContext.cancelChildren()
//    }
//
//    abstract fun getData(word: String, isOnline: Boolean)
//
//    abstract fun handleError(error: Throwable)
//}