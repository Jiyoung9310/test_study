package com.example.mytestapplication.memo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication.base.eventObserver
import com.example.mytestapplication.databinding.FragmentMemoBinding
import com.example.mytestapplication.memo.MemoBaseViewModel
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.ui.adapter.MemoListAdapter

class MemoFragment : Fragment() {

    companion object {
        fun newInstance() = MemoFragment()
    }

    private lateinit var viewModelProviders: MemoViewModelFactory
    private lateinit var viewModel: MemoViewModel
    private lateinit var baseViewModel : MemoBaseViewModel
    private lateinit var binding: FragmentMemoBinding
    private val memoListAdapter: MemoListAdapter = MemoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        baseViewModel = ViewModelProviders.of(activity!!).get(MemoBaseViewModel::class.java)
        viewModelProviders = MemoViewModelFactory(baseViewModel.memoRepo)
        viewModel = ViewModelProviders.of(this, viewModelProviders).get(MemoViewModel::class.java)

        binding = FragmentMemoBinding.inflate(inflater, container, false)
        binding.vm = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = memoListAdapter
        }

        viewModel.memoListData.observe(this, Observer {
            it.let(memoListAdapter::submitList)
        })

        viewModel.floatingButtonEvent.observe(this, eventObserver {
            baseViewModel.navigateAddEvent()
        })

        viewModel.showEmptyMessage.observe(this, Observer {
            binding.rvList.isVisible = !it
            binding.tvEmpty.isVisible = it
        })
    }

}
