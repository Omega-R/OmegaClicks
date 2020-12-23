package com.omega_r.click.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.omega_r.click.ClickManager
import com.omega_r.click.OmegaClickable
import com.omega_r.click.OmegaViewFindable

class MainActivity : AppCompatActivity(), OmegaClickable, OmegaViewFindable {

    override val clickManager: ClickManager = ClickManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClickListener(R.id.button_click_me) {
            Toast.makeText(this, R.string.weel_done, Toast.LENGTH_LONG).show()
        }
    }

}