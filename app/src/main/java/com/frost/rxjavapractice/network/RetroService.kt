package com.frost.rxjavapractice.network

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RetroService {

    @GET("volumes")
    fun getBookList(@Query("q") query: String): Observable<BookListModel>
}