package cn.smaxlyb.mall.ibrary.ui.banner

import cn.smaxlyb.mall.ibrary.R
import com.ToxicBakery.viewpager.transforms.DefaultTransformer
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.listener.OnItemClickListener

object BannerCreator {

    // 设置banner默认样式
    /**
     * @param banner 要设置样式的banner
     * @param dataUrls banner的数据源
     * @param listener 图片被单击回调
     */
    fun setDefault(
        banner: ConvenientBanner<String>, dataUrls: ArrayList<String>, listener: OnItemClickListener
    ) {
        banner.setPages({ ImageHolder() }, dataUrls)
            .setPageIndicator(intArrayOf(R.drawable.indicator_normal, R.drawable.indicator_focus))
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
            .setOnItemClickListener(listener)
            .setPageTransformer(DefaultTransformer())
            .startTurning(3000)
            .isCanLoop = true
    }
}