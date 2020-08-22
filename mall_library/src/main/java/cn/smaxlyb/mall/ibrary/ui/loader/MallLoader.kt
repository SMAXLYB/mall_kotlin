package cn.smaxlyb.mall.ibrary.ui.loader

import android.content.Context
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import cn.smaxlyb.mall.ibrary.R
import com.blankj.utilcode.util.ScreenUtils
import com.wang.avi.AVLoadingIndicatorView


// 职责:管理所有loader
object MallLoader {

    private const val LOADER_SIZE_SCALE = 8
    private const val LOADER_OFFSET_SCALE = 10
    private val LOADERS = ArrayList<AppCompatDialog>()

    /**
     * @param context 当前dialog上下文
     * @param loadingView dialog的VIEW布局
     * @return AppcompatDialog 返回创建好的dialog
     */
    private fun createDialog(
        context: Context,
        loadingView: AVLoadingIndicatorView
    ): AppCompatDialog {
        // 实例化dialog
        val dialog: AppCompatDialog = AppCompatDialog(context, R.style.dialog)
        val deviceWidth = ScreenUtils.getScreenWidth()
        val deviceHeight = ScreenUtils.getScreenHeight()

        // 获取dialog所在的window
        val dialogWindow: Window? = dialog.window

        // 设置布局
        dialog.setContentView(loadingView)

        // 设置dialog大小
        val lp: WindowManager.LayoutParams? = dialogWindow?.attributes
        with(lp) {
            this?.width = deviceWidth / LOADER_SIZE_SCALE
            this?.height = deviceHeight / LOADER_SIZE_SCALE + deviceHeight / LOADER_OFFSET_SCALE
            this?.gravity = Gravity.CENTER
        }

        LOADERS.add(dialog)
        return dialog
    }

    /**
     * 自定义dialog样式,默认样式为BallBeatIndicator
     * @param context 上下文
     * @param type 样式
     */
    fun showLoadingDialog(
        context: Context,
        type: LoaderStyle = LoaderStyle.BallBeatIndicator
    ) {
        // 实例化view
        val loadingView = AVLoadingIndicatorView(context)
        // 设置指示器
        loadingView.setIndicator(type.name)
        // 展示dialog
        with(createDialog(context, loadingView)) {
            setCancelable(false)
            show()
        }
    }

    /**
     * 停止显示所有dialog
     */
    fun stopAllDialog() {
        for (dialog in LOADERS) {
            if (dialog.isShowing) {
                dialog.cancel()
            }
        }
        LOADERS.clear()
    }
}