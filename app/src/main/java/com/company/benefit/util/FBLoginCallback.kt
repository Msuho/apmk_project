package com.company.benefit.util

import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginResult
import org.json.JSONObject


// Facebook 로그인 콜백 함수
class FBLoginCallback : FacebookCallback<LoginResult> {
    // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
    override fun onSuccess(loginResult: LoginResult) {
        Log.e("Callback :: ", "onSuccess")
        requestMe(loginResult.accessToken)
    }

    // 로그인 창을 닫을 경우, 호출됩니다.
    override fun onCancel() {
        Log.e("Callback :: ", "onCancel")
    }

    // 로그인 실패 시에 호출됩니다.
    override fun onError(error: FacebookException) {
        Log.e("Callback :: ", "onError : " + error.message)
    }

    // 사용자 정보 요청
    private fun requestMe(token: AccessToken?) {
        val graphRequest = GraphRequest.newMeRequest(token
        ) { `object`, _ -> Log.e("result", `object`.toString()) }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,gender,birthday")
        graphRequest.parameters = parameters
        graphRequest.executeAsync()
    }
}