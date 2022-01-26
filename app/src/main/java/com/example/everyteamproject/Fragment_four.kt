package com.example.everyteamproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Fragment_four : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_four, container, false)
        val backgroundText: Button
        backgroundText = view.findViewById(R.id.backgroundText)
        backgroundText.setOnClickListener(this)
        return view
    }

    companion object {
        fun newInstacne(): Fragment_four {
            return Fragment_four()
        }
    }

    override fun onClick(v: View?
    ) {
        when (v?.id) {
            R.id.backgroundText -> {
                val intent = Intent(getActivity(), MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }
}