package com.fatherofapps.androidbase.data.models

class Customer(var id: Int = 1,var firstName:String?=null, var lastName:String?=null){

    fun fullName():String{
        return "$firstName $lastName"
    }
    fun isValidateFirstname():Boolean{
        return (firstName?.isNotEmpty()==true)
    }

    fun isValidateLastname():Boolean{
        return (lastName?.isNotEmpty()==true)
    }

    fun validateData():Boolean{
        return isValidateFirstname() && isValidateLastname()
    }
}