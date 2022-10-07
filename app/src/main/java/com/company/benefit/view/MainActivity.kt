package com.company.benefit.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.company.benefit.R
import com.company.benefit.databinding.ActivityMainBinding
import com.company.benefit.util.AdPopcornUtil
import com.company.benefit.util.PreferenceUtil
import com.company.benefit.viewmodel.MainViewModel
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    // 환경변수 사용하기 위함
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.initializeLayout()
        this.init()

    }

    private fun init() {

        binding.adPopcornButton.setOnClickListener {

            /*val intent = Intent(this, AdPopcornActivity::class.java)
            startActivity(intent)*/

            val adPopcornUtil = AdPopcornUtil(this)
            adPopcornUtil.init()

        }

        binding.listItemButton.setOnClickListener {

            /*val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)*/

            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)


        }

    }


    private fun initializeLayout() {

        // tool bar 를 통해 app bar 생성
        binding.appbarLayout.toolbarText.apply {
            text = "A P M K"
            setTextColor(Color.BLACK)
        }


        setSupportActionBar(binding.appbarLayout.toolbar)


        // app bar 의 좌측 영역에 Drawer 를 open 하기 위한 icon 추가
        /*supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_button)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appbarLayout.toolbar,
            R.string.open,
            R.string.closed
        )

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)*/

        binding.appbarLayout.drawerButton.setOnClickListener{
            if(!binding.drawerLayout.isDrawerOpen(Gravity.LEFT)){
                binding.drawerLayout.openDrawer(Gravity.LEFT)
            }else{
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
            }
        }

        /*binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.item1 -> Toast.makeText(applicationContext, "tiem1 click", Toast.LENGTH_SHORT)
                    .show()
                R.id.item2 -> Toast.makeText(applicationContext, "tiem1 click", Toast.LENGTH_SHORT)
                    .show()
                R.id.item3 -> Toast.makeText(applicationContext, "tiem1 click", Toast.LENGTH_SHORT)
                    .show()

                else -> Toast.makeText(applicationContext, "else click", Toast.LENGTH_SHORT).show()

            }

            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@OnNavigationItemSelectedListener true

        })*/

    }

     private fun logout() {

        // 환경변수에 context를 넘겨줘야한다.
        prefs = PreferenceUtil(applicationContext)

        Log.d("MainActivity",
            "email : ${prefs.getString("email", "")} / name : ${prefs.getString("name", "")} / authLogin : ${prefs.getBoolean("authLogin", false)}")

        val loginType = prefs.getString("loginType", "")

        Log.d("MainActivity", "loginType : $loginType")

         when (loginType) {
             // naver logout
             "nv" -> {

                 NidOAuthLogin().callDeleteTokenApi(this, object : OAuthLoginCallback {
                     override fun onSuccess() {

                         prefs.setString("email", "")
                         prefs.setString("name", "")
                         prefs.setBoolean("authLogin", false)

                         val intent = Intent(this@MainActivity, LoginActivity::class.java)
                         startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                         finish()
                     }

                     override fun onFailure(httpStatus: Int, message: String) {
                         // 서버에서 토큰 삭제에 실패했어도 클라이언트에 있는 토큰은 삭제되어 로그아웃된 상태입니다.
                         // 클라이언트에 토큰 정보가 없기 때문에 추가로 처리할 수 있는 작업은 없습니다.
                         Log.d("MainActivity",
                             "errorCode: ${NaverIdLoginSDK.getLastErrorCode().code}")
                         Log.d("MainActivity",
                             "errorDesc: ${NaverIdLoginSDK.getLastErrorDescription()}")
                     }

                     override fun onError(errorCode: Int, message: String) {
                         // 서버에서 토큰 삭제에 실패했어도 클라이언트에 있는 토큰은 삭제되어 로그아웃된 상태입니다.
                         // 클라이언트에 토큰 정보가 없기 때문에 추가로 처리할 수 있는 작업은 없습니다.
                         onFailure(errorCode, message)
                     }
                 })
             }
             // kakao logout
             "ka" -> {
                 UserApiClient.instance.logout { error ->
                     if (error != null) {
                         Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                     }else {
                         Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                         prefs.setString("email", "")
                         prefs.setString("name", "")
                         prefs.setBoolean("authLogin", false)

                         val intent = Intent(this@MainActivity, LoginActivity::class.java)
                         startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                         finish()
                     }
                 }
             }
         }

    }


    override fun onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}