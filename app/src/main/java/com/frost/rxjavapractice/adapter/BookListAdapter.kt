package com.frost.rxjavapractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frost.rxjavapractice.R
import com.frost.rxjavapractice.network.VolumeInfo
import kotlinx.android.synthetic.main.item_book.view.*
import java.util.ArrayList

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    private var bookListData = ArrayList<VolumeInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BookListAdapter.MyViewHolder, position: Int) {
        holder.bind(bookListData[position])
    }

    override fun getItemCount():Int = bookListData.size

    inner class MyViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(book: VolumeInfo) {
            view.titleTextView.text = book.volumeInfo.title
            view.publisherTextView.text = book.volumeInfo.publisher
            view.descriptionTextView.text = book.volumeInfo.description

            val url = book.volumeInfo.imageLinks.smallThumbnail
            Glide.with(view.bookImageView)
                .load(url)
                .circleCrop()
                .into(view.bookImageView)
        }
    }

    fun setList(list: ArrayList<VolumeInfo>){
        bookListData = list
        this.notifyDataSetChanged()
    }
}