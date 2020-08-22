package cn.smaxlyb.mall.ibrary.ui.banner

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

// banner的holder
class ImageHolder : Holder<String> {
    private lateinit var imageView: ImageView

    companion object {
        private val BANNER_OPTIONS = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop()
    }

    // 创建视图
    override fun createView(context: Context?): View {
        imageView = ImageView(context)
        return imageView
    }

    // 将数据加载到UI中
    override fun UpdateUI(context: Context, position: Int, dataUrl: String) {
        Glide.with(context)
            .load(dataUrl)
            .apply(BANNER_OPTIONS)
            .into(imageView)
    }
}