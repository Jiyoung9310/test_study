package com.example.mytestapplication.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.mytestapplication.R
import com.example.mytestapplication.base.eventObserver
import com.example.mytestapplication.login.HomeFragment
import com.example.mytestapplication.memo.data.MemoDatabase
import com.example.mytestapplication.memo.data.MemoRepository
import com.example.mytestapplication.memo.ui.MemoAddFragment
import com.example.mytestapplication.memo.ui.MemoFragment
import kotlinx.android.synthetic.main.activity_memo.*

class MemoActivity : AppCompatActivity() {

    private lateinit var database: MemoDatabase

    private lateinit var baseViewModelFactory : MemoBaseViewModelFactory
    private lateinit var viewModel : MemoBaseViewModel

    private val memoBackStack = "memo_back_stack"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        database = MemoDatabase.buildDatabase(this)
        baseViewModelFactory = MemoBaseViewModelFactory(MemoRepository.getInstance(database))
        viewModel = ViewModelProviders.of(this, baseViewModelFactory).get(MemoBaseViewModel::class.java)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MemoFragment.newInstance())
                .commitNow()
            supportActionBar?.setTitle(R.string.memo_main_actionbar_title)
        }

        viewModel.navigateAddEvent.observe(this, eventObserver {
            if(it) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container,
                    MemoAddFragment()
                ).addToBackStack(memoBackStack).commit()
                supportActionBar?.run{
                    setTitle(R.string.memo_add_actionbar_title)
                    setDisplayHomeAsUpEnabled(true)
                }
            }
        })

        viewModel.navigateMainEvent.observe(this, eventObserver {
            if(it) {
                supportFragmentManager.popBackStack()
                supportActionBar?.run{
                    setTitle(R.string.memo_main_actionbar_title)
                    setDisplayHomeAsUpEnabled(false)
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                viewModel.navigateMainEvent()
                true
            }
            else -> {
                super.onBackPressed()
                false
            }
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1) {
            viewModel.navigateMainEvent()
        } else {
            super.onBackPressed()
        }
    }
}
