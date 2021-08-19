package com.frost.rxjavapractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.frost.rxjavapractice.adapter.BookListAdapter
import com.frost.rxjavapractice.network.BookListModel
import com.frost.rxjavapractice.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private lateinit var bookAdater : BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSearchBox()
        setupRecycler()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.booklist.observe(this, { loadList(it) })
    }

    private fun loadList(list: BookListModel?) {
        list?.let { bookAdater.setList(it.items) }
            ?:run { Toast.makeText(this, "Error loading", Toast.LENGTH_SHORT).show() }
    }

    private fun initSearchBox(){
        descriptionEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.makeApiCall(query.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun setupRecycler() {
        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
            bookAdater = BookListAdapter()
            adapter = bookAdater
        }
    }
}