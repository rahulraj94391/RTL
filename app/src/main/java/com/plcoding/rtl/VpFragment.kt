package com.plcoding.rtl

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates

private const val ARG_PARAM1 = "posMarker"

class VpFragment : Fragment() {
    var posMarker: Int by Delegates.notNull()
        private set
    private val colors = listOf(
        "#E9ECEF", // Light gray
        "#ADB5BD", // Grayish
        "#F5F5DC", // Beige
        "#FAEBD7", // Antique white
        "#FFF5EE", // Seashell
        "#F0FFF0", // Honeydew
        "#F5FFFA", // Mint cream
        "#FFFFF0", // Ivory
    )
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            posMarker = it.getInt(ARG_PARAM1)
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_vp, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(Color.parseColor(colors[posMarker % colors.size]))
        val tv: TextView = view.findViewById(R.id.textView)
        tv.text = posMarker.toString()
    }
    
    companion object {
        @JvmStatic
        fun newInstance(posMarker: Int) =
            VpFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, posMarker)
                }
            }
    }
}