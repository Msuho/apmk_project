package com.company.benefit.view

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.company.benefit.databinding.ActivityLoginBinding
import com.company.benefit.databinding.ActivityTermsDetailBinding
import com.company.benefit.util.PreferenceUtil
import com.company.benefit.viewmodel.MainViewModel
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class TermsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTermsDetailBinding

    // 환경변수 사용하기 위함
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivClose.setOnClickListener {
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }

        //binding.tvTitle.text = intent.getStringExtra("title")


    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.ivClose.callOnClick()
    }

}