package engineer.yusrisahrul.awesomeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.data.model.ResponsePhoto
import engineer.yusrisahrul.awesomeapp.databinding.ActivityMainBinding
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import engineer.yusrisahrul.awesomeapp.ui.PhotoViewModel
import engineer.yusrisahrul.awesomeapp.ui.adapter.ListPhotoAdapter
import engineer.yusrisahrul.awesomeapp.ui.detail.DetailPhotoActivity
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel : PhotoViewModel by viewModels()

    private val adapterList : ListPhotoAdapter by lazy {
        ListPhotoAdapter({item -> detailPhoto(item)}, gridLayoutManager)
    }

    private var isList: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var gridLayoutManager: GridLayoutManager

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
        isList.value = true
        gridLayoutManager = GridLayoutManager(this, 1)
        with(binding) {
            isList.observe(this@MainActivity, { isList ->
                if (isList) {
                    listIcon.setImageResource(R.drawable.list_icon_darkergray)
                    listIconToolbar.setImageResource(R.drawable.list_icon_darkergray)
                    gridIcon.setImageResource(R.drawable.grid_icon_grey)
                    gridIconToolbar.setImageResource(R.drawable.grid_icon_grey)
                    gridLayoutManager.spanCount = 1
                } else {
                    listIcon.setImageResource(R.drawable.list_icon_gray)
                    listIconToolbar.setImageResource(R.drawable.list_icon_gray)
                    gridIcon.setImageResource(R.drawable.grid_icon_darkergrey)
                    gridIconToolbar.setImageResource(R.drawable.grid_icon_darkergrey)
                    gridLayoutManager.spanCount = 2
                }
            })

            listIcon.setOnClickListener {
                isList.value = true
            }
            listIconToolbar.setOnClickListener {
                isList.value = true
            }
            gridIcon.setOnClickListener {
                isList.value = false
            }
            gridIconToolbar.setOnClickListener {
                isList.value = false
            }

            rvPhoto.also {
                it.layoutManager = gridLayoutManager
                it.adapter = adapterList
                it.setHasFixedSize(true)
            }

            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                if (abs(verticalOffset) == appBar.totalScrollRange) {
                    toolbar.visibility = View.VISIBLE
                } else {
                    toolbar.visibility = View.GONE
                }
            })
        }
    }

    private fun initViewModel() {
        viewModel.statePhoto.observe(this, {
            when (it) {
                is PhotoState.Loading -> getLoadingPhoto(true)
                is PhotoState.Result -> successGetDataPhoto(it.data)
                is PhotoState.Error -> showError()
            }
        })
        viewModel.data.observe(this, Observer (adapterList::submitList))
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

    private fun successGetDataPhoto(response: ResponsePhoto) {
        getLoadingPhoto(false)
        with(binding) {
            Glide.with(this@MainActivity)
                .load(response.photos[0].src.large)
                .into(imgBackground)
        }
    }

    private fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    private fun detailPhoto(item: DataPhoto) {
        startActivity(Intent(this, DetailPhotoActivity::class.java).also {
            it.putExtra("photo", item)
        })
    }
}