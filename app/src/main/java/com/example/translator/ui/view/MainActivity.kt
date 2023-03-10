package com.example.translator.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.translator.AppState
import com.example.translator.R
import com.example.translator.databinding.ActivityMainBinding
import com.example.translator.domain.entity.Word
import com.example.translator.ui.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), BaseView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainActivityPresenter

    private lateinit var mainAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainActivityPresenter()
        mainAdapter = MainAdapter()

        initSearchButton()
        initRecycler()
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        presenter.detach(this)
        super.onStop()
    }

    override fun renderData(state: AppState) {
        when (state) {
            is AppState.Loading -> {
                showLoading()
            }

            is AppState.Error -> {
                showError(message = state.error.message ?: getString(R.string.some_error))
            }

            is AppState.Success -> {
                showResult(data = state.data)
            }
        }
    }

    private fun initSearchButton() {
        with(binding) {
            btnSearch.setOnClickListener {
                val query = edSearch.text.toString()
                presenter.getData(searchingWord = query)
            }
        }
    }

    private fun initRecycler() {
        binding.rvResult.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showLoading() {
        with(binding) {
            progress.visibility = View.VISIBLE
            tvError.visibility = View.GONE
            rvResult.visibility = View.GONE
        }
    }

    private fun showError(message: String) {
        with(binding) {
            progress.visibility = View.GONE
            rvResult.visibility = View.GONE
            tvError.visibility = View.VISIBLE

            tvError.text = message
        }
    }

    private fun showResult(data: List<Word>) {
        with(binding) {
            progress.visibility = View.GONE
            tvError.visibility = View.GONE
            rvResult.visibility = View.VISIBLE

            mainAdapter.submitList(data)
        }
    }
}