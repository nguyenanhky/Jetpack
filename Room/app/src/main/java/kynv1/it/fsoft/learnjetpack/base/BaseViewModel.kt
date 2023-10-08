package kynv1.it.fsoft.learnjetpack.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState> : ViewModel(){

    private var _loading = MutableLiveData(false)
    private var _enableButton = MutableLiveData(true)
    protected var _viewState = MutableLiveData<ViewState>()

    val loading : LiveData<Boolean> get() = _loading
    val enableButton : LiveData<Boolean> get() = _enableButton
    val viewState : LiveData<ViewState> get() = _viewState
}