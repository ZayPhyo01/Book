package com.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.domain.model.BookModel
import com.ui.book.R

class BookViewHolder(val view: View) : ViewHolder(view) {

    fun bind(bookModel: BookModel) {
        val tvBookName = view.findViewById<TextView>(R.id.tvBookName)
        val ivBookCover = view.findViewById<ImageView>(R.id.ivBookCover)

        tvBookName.text = bookModel.name
        Glide.with(view)
            .load(bookModel.bookCover)
            .into(ivBookCover)
    }
}