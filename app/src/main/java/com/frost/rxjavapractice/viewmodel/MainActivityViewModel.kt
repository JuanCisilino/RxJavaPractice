package com.frost.rxjavapractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frost.rxjavapractice.network.BookListModel
import com.frost.rxjavapractice.network.RetroInstance
import com.frost.rxjavapractice.network.RetroService
import rx.schedulers.Schedulers

class MainActivityViewModel: ViewModel() {

    var booklist =  MutableLiveData<BookListModel?>()

    fun makeApiCall(query: String){
        val instance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        instance.getBookList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {booklist.postValue(it)},
                {booklist.postValue(null)})
    }

}