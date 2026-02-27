package com.example.goldapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.goldapp.databinding.ActivityFullScreenBinding

class FullScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideStatusBar()

        Handler(Looper.getMainLooper()).postDelayed({

            // پس از گذشتن چند ثانیه این کد ها اجرا شده و به اکتیویتی اصلی برنامه منتقل خواهیم شد
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // تابع زیر برای بسته شدن اکتیویتی جاری (همین اکتیویتی) میباشد
            // یعنی پس از رفتن به اکتیویتی بعدی، با زدن دکمه بازگشت، به این اکتیویتی برنمیگردیم
            finish()

        }, 3000)


    }
    private fun hideStatusBar(){
        // در بخش زیر، نوار وضعیت یا statusBar را مخفی میکنیم
        // این if به جهت بررسی نسخه api گوشی کاربر میباشد
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            // اگر api گوشی 28 یا بالاتر بود ازین کد ها استفاده میشود
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val attrib = window.attributes
            attrib.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        } else {

            // چنانچه api پایین تر از نسخه 30 بود از این کد استفاده خواهد شد
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

        }

    }
}