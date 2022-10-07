package com.company.benefit

import android.app.Application
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("onCreate", "onCreate")

        // Kakao SDK 초기화
        KakaoSdk.init(this, getString(R.string.kakao_app_key))

        // Naver SDK 초기화
        NaverIdLoginSDK.initialize(this, getString(R.string.naver_client_id), getString(R.string.naver_client_secret), getString(R.string.naver_client_project))

        FacebookSdk.sdkInitialize(applicationContext);
        AppEventsLogger.activateApp(this);

    }

}