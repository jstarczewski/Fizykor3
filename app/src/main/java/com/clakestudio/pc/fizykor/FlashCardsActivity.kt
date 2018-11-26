package com.clakestudio.pc.fizykor

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_flash_cards.*

class FlashCardsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_cards)

        mvTest.setTextZoom(120)
        mvTest.setTextColor(Color.GREEN.toString())
        mvTest.setBackgroundColor(Color.WHITE)
        mvTest.setText("$\\F&#x2196{&#x2192}=ma_g$")
    }
}
