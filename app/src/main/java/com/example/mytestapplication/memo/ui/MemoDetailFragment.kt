package com.example.mytestapplication.memo.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.mytestapplication.R
import com.example.mytestapplication.databinding.FragmentMemoDetailBinding
import com.example.mytestapplication.memo.MemoBaseViewModel
import com.example.mytestapplication.memo.MemoBaseViewModelFactory

class MemoDetailFragment : Fragment() {

    companion object {
        const val FRAGMENT_MEMO_ID = "memoId"
    }

    private lateinit var viewModel: MemoDetailViewModel
    private lateinit var baseViewModel : MemoBaseViewModel
    private lateinit var binding: FragmentMemoDetailBinding
    private var memoId : Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        memoId = arguments?.getLong(FRAGMENT_MEMO_ID) ?: 1L
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        baseViewModel = ViewModelProviders.of(activity!!).get(MemoBaseViewModel::class.java)
        viewModel = ViewModelProviders.of(this, MemoBaseViewModelFactory(baseViewModel.memoRepo)).get(MemoDetailViewModel::class.java)

        viewModel.reqMemoData(baseViewModel.currentMemoId)

        viewModel.memoData.observe(this, Observer {
            binding.data = it
        })
    }

}
