package com.fatherofapps.androidbase.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fatherofapps.androidbase.base.viewmodel.BaseViewModel
import com.fatherofapps.androidbase.data.models.Customer
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {
    private var _currentCustomer = MutableLiveData<Customer?>()
    val currentCustomer: LiveData<Customer?>
        get() = _currentCustomer

    override fun fetchData() {
        // call api
        // db
        _currentCustomer.postValue(Customer())
    }

    fun onFirstnameChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int,
    ) {
        val newFirstname = text?.toString()
        val customer = currentCustomer.value
         if(newFirstname!=customer?.firstName){
            customer?.firstName = newFirstname
            _currentCustomer.postValue(customer)
        }
    }
    fun onLastnameChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int,
    ) {
        val newLastname = text?.toString()
        val customer = currentCustomer.value
         if(newLastname!=customer?.lastName){
            customer?.lastName = newLastname
            _currentCustomer.postValue(customer)
        }
    }

    fun registerAction(){
        currentCustomer.value?.let {customer ->
            Log.e("kynv1", "customer name : ${customer.fullName()} ", )
        }
    }
}