package com.zhiyecn.xgpushdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.zhiyecn.xgpush.IXGPushManager
import com.zhiyecn.xgpush.PushManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager: IXGPushManager = PushManager(this)

        manager.init(true)
        val tags = setOf("222", "333","444")
        val alias = "111111"
        manager.login(alias, tags, {
            Toast.makeText(this, "Bind", Toast.LENGTH_LONG).show()
        })
        findViewById<View>(R.id.login).setOnClickListener {
            manager.login(alias, tags, {
                Toast.makeText(this, "login success", Toast.LENGTH_LONG).show()
            })
        }
        findViewById<View>(R.id.logout).setOnClickListener {
            manager.logout(alias, tags, {
                Toast.makeText(this, "logout success", Toast.LENGTH_LONG).show()
            })
        }

    }


}
