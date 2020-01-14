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
import com.example.mytestapplication.base.AdapterItemClickListener
import com.example.mytestapplication.base.eventObserver
import com.example.mytestapplication.databinding.FragmentMemoBinding
import com.example.mytestapplication.memo.MemoBaseViewModel
import com.example.mytestapplication.memo.MemoBaseViewModelFactory
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.ui.adapter.MemoListAdapter
import kotlinx.android.synthetic.main.fragment_memo.*

class MemoFragment : Fragment() {

    companion object {
        fun newInstance() = MemoFragment()
    }

    private lateinit var viewModel: MemoViewModel
    private lateinit var baseViewModel : MemoBaseViewModel
    private lateinit var binding: FragmentMemoBinding
    private val memoListAdapter: MemoListAdapter = MemoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        baseViewModel = ViewModelProviders.of(activity!!).get(MemoBaseViewModel::class.java)
        viewModel = ViewModelProviders.of(this, MemoBaseViewModelFactory(baseViewModel.memoRepo)).get(MemoViewModel::class.java)

        binding = FragmentMemoBinding.inflate(inflater, container, false)
        binding.vm = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        memoListAdapter.listener = object : AdapterItemClickListener {
            override fun onClickItem(id: Long) {
                viewModel.onClickItem(id)
            }
        }

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = memoListAdapter
        }

        viewModel.memoListData.observe(this, Observer {
            it.let(memoListAdapter::submitList)
        })

        viewModel.memoItemEvent.observe(this, eventObserver {
            baseViewModel.navigateDetailEvent(it)
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
