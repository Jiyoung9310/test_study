package com.example.mytestapplication.memo.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.mytestapplication.MainView2Model
import com.example.mytestapplication.databinding.FragmentMemoBinding
import com.example.mytestapplication.memo.MemoViewModelFactory
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository

class MemoFragment : Fragment() {

    companion object {
        fun newInstance() = MemoFragment()
    }

    private lateinit var database: MemoDatabase
    private lateinit var viewModelProvider : MemoViewModelFactory
    private lateinit var viewModel: MemoViewModel
    private lateinit var binding: FragmentMemoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemoBinding.inflate(inflater, container, false)

        database = Room.databaseBuilder(activity!!, MemoDatabase::class.java, "memo-db")
            .fallbackToDestructiveMigration()
            .build()

        viewModelProvider = MemoViewModelFactory(MemoRepository.getInstance(database.memoDao()))

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelProvider).get(MemoViewModel::class.java)
        binding.vm = viewModel

    }

}
