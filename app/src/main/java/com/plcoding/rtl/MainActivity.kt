package com.plcoding.rtl

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private var toast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        
        val viewPager2: ViewPager2 = findViewById(R.id.viewpager2)
        val viewPagerAdapter = VPAdapter(this@MainActivity)
        viewPager2.offscreenPageLimit = 1
        viewPager2.adapter = viewPagerAdapter
        
        
        val btn: MaterialButton = findViewById(R.id.toastbtn)
        btn.setOnClickListener {
            val frag_minus_1: Fragment? = viewPagerAdapter.getFragmentByPos(viewPager2.currentItem - 1)
            val frag: Fragment? = viewPagerAdapter.getFragmentByPos(viewPager2.currentItem)
            val frag_plus_1: Fragment? = viewPagerAdapter.getFragmentByPos(viewPager2.currentItem + 1)
            
            val msg_minus_1 = if (frag_minus_1 is VpFragment) {
                frag_minus_1.posMarker.toString()
            } else {
                "NULL"
            }
            
            val msg = if (frag is VpFragment) {
                frag.posMarker.toString()
            } else {
                "NULL"
            }
            
            val msg_plus_1 = if (frag_plus_1 is VpFragment) {
                frag_plus_1.posMarker.toString()
            } else {
                "NULL"
            }
            toast?.cancel()
            toast = Toast.makeText(this@MainActivity, "$msg_minus_1, $msg, $msg_plus_1", Toast.LENGTH_SHORT)
            toast?.show()
        }
    }
}