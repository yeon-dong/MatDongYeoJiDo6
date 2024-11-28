package com.jsj.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jsj.myapplication.R
import com.jsj.myapplication.ui.detail.DetailActivity
import com.jsj.myapplication.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 스플래시 화면을 페이드 인 애니메이션
        val splashView: View = findViewById(R.id.main)
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = 1000 // 1초
        }
        splashView.startAnimation(fadeIn)

        // 2초 후에 MainActivity로 이동
        Handler().postDelayed({
            // 페이드 아웃 애니메이션
            val fadeOut = AlphaAnimation(1f, 0f).apply {
                duration = 1000 // 1초
            }
            splashView.startAnimation(fadeOut)

            fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation?) {}

                override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                    val intent = Intent(this@SplashActivity, DetailActivity::class.java)
                    startActivity(intent)
                    finish() // 스플래시 액티비티 종료
                }

                override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
            })
        }, 2000) // 2초 후에 페이드 아웃 시작
    }
}