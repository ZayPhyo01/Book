package com.ui.book.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ui.adapter.BookAdapter
import com.ui.book.R
import com.ui.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookListFragment : Fragment() {

    private val bookViewModel: BookViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookRecyclerView = view.findViewById<RecyclerView>(R.id.rvBookList)
        val adapter: BookAdapter = BookAdapter()
        bookRecyclerView.adapter = adapter

        bookViewModel.bookListLiveData.observeForever {
            adapter.updateList(it)
        }

    }

    companion object {
        fun newInstance(): BookListFragment = BookListFragment()
    }
}