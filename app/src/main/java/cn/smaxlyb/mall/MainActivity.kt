package cn.smaxlyb.mall

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.smaxlyb.mall.ibrary.loader.LoaderStyle
import cn.smaxlyb.mall.ibrary.net.RestClient
import cn.smaxlyb.mall.ibrary.net.callback.IError
import cn.smaxlyb.mall.ibrary.net.callback.IFailure
import cn.smaxlyb.mall.ibrary.net.callback.ISuccess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RestClient.builder()
            .setUrl("index.php")
            .setLoader(this,LoaderStyle.LineSpinFadeLoaderIndicator)
            .onSuccess(object : ISuccess {
                override fun onSuccess(response: String) {
                    Toast.makeText(baseContext, response, Toast.LENGTH_SHORT).show()
                }
            })
            .onFailure(object : IFailure {
                override fun onFailure() {
                    Toast.makeText(baseContext, "请求失败", Toast.LENGTH_SHORT).show()
                }
            })
            .onError(object : IError {
                override fun onError(code: Int, msg: String) {
                    Toast.makeText(baseContext, code, Toast.LENGTH_SHORT).show()
                }
            })
            .build()
            .get()
    }
}