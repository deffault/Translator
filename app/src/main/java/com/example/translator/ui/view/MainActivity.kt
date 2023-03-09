package com.example.translator.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.translator.AppState
import com.example.translator.databinding.ActivityMainBinding
import com.example.translator.ui.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), BaseView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainActivityPresenter()

        initSearchButton()
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
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }

            is AppState.Error -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

            is AppState.Success -> {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
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
}