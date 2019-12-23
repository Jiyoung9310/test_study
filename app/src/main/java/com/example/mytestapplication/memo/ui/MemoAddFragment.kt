package com.example.mytestapplication.memo.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mytestapplication.R

class MemoAddFragment : Fragment() {

    companion object {
        fun newInstance() = MemoAddFragment()
    }

    private lateinit var viewModel: MemoAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_memo_add, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MemoAddViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
