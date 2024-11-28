package com.jsj.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jsj.myapplication.R
import com.jsj.myapplication.ui.detail.DetailActivity
import com.jsj.myapplication.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 이미지 뷰에 Glide로 이미지 로드
        val splashImageView: ImageView = findViewById(R.id.imageView3)
        Glide.with(this)
            .load(R.drawable.matdongtitlecrop) // 이미지 리소스 ID
            .override(800, 600) // 원하는 크기로 조정
            .into(splashImageView)

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
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent) // MainActivity로 이동
                    splashView.visibility = View.GONE // 스플래시 뷰 숨기기
                    finish() // 스플래시 액티비티 종료
                }

                override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
            })
        }, 2000) // 2초 후에 페이드 아웃 시작
    }
}
