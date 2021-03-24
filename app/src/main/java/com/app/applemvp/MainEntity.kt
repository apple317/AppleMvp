package com.app.applemvp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainEntity(application: Application) : AndroidViewModel(application) {


    var name = MutableLiveData<String>()




}
