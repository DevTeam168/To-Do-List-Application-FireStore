package com.example.todolist.view.splashscreen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todolist.databinding.ActivitySplashScreenBinding
import com.example.todolist.view.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySplashScreenBinding

    companion object {
        const val PROGRESS_MAX = 100
        const val PROGRESS_INCREMENT = 20
        const val DELAY_MILLIS = 500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()
        startProgressBar()
    }

    private fun init() {
        configureStatusBar()
    }

    private fun configureStatusBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun startProgressBar() {
        lifecycleScope.launch {
            var progress = 0
            while (progress <= PROGRESS_MAX) {
                mBinding.progressBar.progress = progress
                delay(DELAY_MILLIS)
                progress += PROGRESS_INCREMENT
            }
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}