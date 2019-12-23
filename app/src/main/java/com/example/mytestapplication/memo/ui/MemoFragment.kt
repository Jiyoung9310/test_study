package com.example.mytestapplication.memo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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
    private val adapter: MemoListAdapter = MemoListAdapter()

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

        binding.rvList.adapter = adapter
        viewModel.memoListData.observe(this, eventObserver {
            it.let(adapter::submitList)
        })

        viewModel.floatingButtonEvent.observe(this, eventObserver {
            baseViewModel.navigateAddEvent()
        })
    }

}
