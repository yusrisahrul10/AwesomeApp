package engineer.yusrisahrul.awesomeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.databinding.ActivityMainBinding
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import engineer.yusrisahrul.awesomeapp.ui.PhotoViewModel
import engineer.yusrisahrul.awesomeapp.ui.adapter.PhotoAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel : PhotoViewModel by viewModels()

    private val adapter : PhotoAdapter by lazy {
        PhotoAdapter {item -> detailPhoto(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        initData()
        initView()
        initViewModel()
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun initData() {
        viewModel.getAllPhoto()
    }

    private fun initView() {
        with(binding) {
            rvPhoto.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                    this@MainActivity, LinearLayoutManager.VERTICAL, false
                )
                it.setHasFixedSize(true)
            }
        }
    }

    private fun initViewModel() {
        viewModel.statePhoto.observe(this, {
            when (it) {
                is PhotoState.Loading -> getLoadingPhoto(true)
                is PhotoState.Result -> getLoadingPhoto(false)
                is PhotoState.Error -> showError()
            }
        })
        viewModel.data.observe(this, Observer (adapter::submitList))
    }

    private fun getLoadingPhoto(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvPhoto.visibility = View.INVISIBLE
                shPhoto.visibility = View.VISIBLE
            } else {
                rvPhoto.visibility = View.VISIBLE
                shPhoto.visibility = View.INVISIBLE
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    private fun detailPhoto(item: DataPhoto) {
        Toast.makeText(this@MainActivity, item.photographer, Toast.LENGTH_SHORT).show()
    }
}