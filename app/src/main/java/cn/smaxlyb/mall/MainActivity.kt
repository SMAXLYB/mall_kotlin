package cn.smaxlyb.mall

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.smaxlyb.mall.ibrary.global.GlobalKeys
import cn.smaxlyb.mall.ibrary.global.Mall

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Mall.getConfiguration<Context>(GlobalKeys.APPLICATION_CONTEXT)
    }

}