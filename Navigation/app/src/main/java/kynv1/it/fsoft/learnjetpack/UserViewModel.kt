package kynv1.it.fsoft.learnjetpack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class UserViewModel(
    application: Application
):AndroidViewModel(application) {
    private var _logedIn = MutableLiveData<Boolean>()
    val loggedIn:LiveData<Boolean> = _logedIn

    init {
        _logedIn.value = getLoginStatus(getApplication())
    }

    fun getUsername():String?{
        return getUsername(getApplication())
    }

    fun login(userName:String,password:String):LiveData<Boolean>{
        val liveData = MutableLiveData<Boolean>()
        if (userName.isNullOrEmpty().not() && userName == getUsername(getApplication()) &&
            password.isNullOrEmpty().not() && password== getPassword(getApplication())
        ) {
            saveLoginStatus(getApplication(),true);
            _logedIn.value = true
            liveData.value = true
        }else{
            _logedIn.value = false
            liveData.value = false
        }
        return liveData
    }


    fun singUp(username:String,password: String):LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            liveData.value = false
        } else {
            saveUsernamePassword(getApplication(), username, password);
            liveData.value = true
        }
        return liveData
    }

    fun logout(): LiveData<Boolean> {
        saveLoginStatus(getApplication(), false)
        _logedIn.value = false
        return MutableLiveData(true)
    }
}