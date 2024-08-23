package com.plcoding.rtl

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// findFragmentByTag (Working as Expected) {doubt related to the StackOverflow thread (https://stackoverflow.com/questions/55728719/get-current-fragment-with-viewpager2)}
class VPAdapter(private val fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 30
    }
    
    override fun createFragment(position: Int): Fragment {
        Log.i("9155881234", "create called")
        val fragment = VpFragment.newInstance(position)
        return fragment
    }
    
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    
    fun getFragmentByPos(pos: Int): Fragment? {
        return fa.supportFragmentManager.findFragmentByTag("f${getItemId(pos)}")
    }
}


// MutableMap<Int, WeakReference<Fragment>> (working as expected)
/*
class VPAdapter(private val fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val map: MutableMap<Int, WeakReference<Fragment>> = mutableMapOf()
    
    override fun getItemCount(): Int {
        return 30
    }
    
    override fun createFragment(position: Int): Fragment {
        val fragment = VpFragment.newInstance(position)
        map[position] = WeakReference(fragment)
        Log.e("9155881234", "map size = ${map.size}")
        map.forEach { (k, v) ->
            Log.d("9155881234", "(k,v) = $k :: $v")
        }
        return fragment
    }
    
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    
    fun getFragmentByPos(pos: Int): Fragment? {
        return map[pos]?.get()
    }
}
*/

// LRU Cache implementation (Does not work as expected)
/*class VPAdapter(private val fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val cache: LruCache<Int, WeakReference<Fragment>> = lruCache(10)
    
    override fun getItemCount(): Int {
        return 30
    }
    
    override fun createFragment(position: Int): Fragment {
        val fragment = VpFragment.newInstance(position)
        cache.put(position, WeakReference(fragment))
        return fragment
    }
    
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    
    fun getFragmentByPos(pos: Int): Fragment? {
        return cache[pos]?.get()
    }
}*/
