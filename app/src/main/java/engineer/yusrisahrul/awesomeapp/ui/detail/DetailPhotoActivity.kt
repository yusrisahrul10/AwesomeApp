package engineer.yusrisahrul.awesomeapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.databinding.ActivityDetailPhotoBinding

class DetailPhotoActivity : AppCompatActivity() {

    private val binding : ActivityDetailPhotoBinding by lazy {
        ActivityDetailPhotoBinding.inflate(layoutInflater)
    }

    private val data : DataPhoto? by lazy {
        intent.getParcelableExtra("photo")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        with(binding) {
            tvWidth.text = data?.width.toString() + " px"
            tvHeight.text = data?.height.toString() + " px"
            tvPhotographer.text = data?.photographer

            Glide.with(this@DetailPhotoActivity)
                .load(data?.src?.large)
                .into(imgBackground)

            imgBack.setOnClickListener {
                finish()
            }
        }
    }
}