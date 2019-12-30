package com.example.mytestapplication.memo.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer

import com.example.mytestapplication.R
import com.example.mytestapplication.base.eventObserver
import com.example.mytestapplication.databinding.FragmentMemoAddBinding
import com.example.mytestapplication.memo.MemoBaseViewModel

class MemoAddFragment : Fragment() {

    private lateinit var viewModel: MemoAddViewModel
    private lateinit var baseViewModel : MemoBaseViewModel
    private lateinit var viewModelProviders: MemoAddViewModelFactory
    private lateinit var binding: FragmentMemoAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseViewModel = ViewModelProviders.of(activity!!).get(MemoBaseViewModel::class.java)
        viewModelProviders = MemoAddViewModelFactory(baseViewModel.memoRepo)
        viewModel = ViewModelProviders.of(this, viewModelProviders).get(MemoAddViewModel::class.java)
        binding.vm = viewModel

        val items = viewModel.categoryArray
        val myAdapter =
            ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, items)

        binding.spCategory.apply {
            adapter = myAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.changeCategory(position)
                }
            }
        }

        viewModel.memoCounter.observe(this, Observer {
            binding.tvCount.text = String.format(getString(R.string.memo_add_character_count), it)
        })

        viewModel.doneButtonEnable.observe(this, Observer {
            binding.btnDone.isEnabled = it
        })

        viewModel.doneEvent.observe(this, Observer {
            viewModel.saveMemo()
        })

        viewModel.saveMemoEvent.observe(this, Observer {
            viewModel.navigateDetailEvent()
        })

        viewModel.navigateDetailEvent.observe(this, Observer {
            baseViewModel.navigateDetailEvent()
        })

    }
}
