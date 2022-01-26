package com.example.everyteamproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Fragment_one : Fragment(), View.OnClickListener {

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_one, container, false)
        val addButton2: Button
        val backgroundText: Button
        addButton2 = view.findViewById(R.id.addButton2)
        addButton2.setOnClickListener(this)
        backgroundText = view.findViewById(R.id.backgroundText)
        backgroundText.setOnClickListener(this)
        return view
    }

    companion object {
        fun newInstacne(): Fragment_one {
            return Fragment_one()
        }
    }

    override fun onClick(v: View?
    ) {
        when (v?.id) {
            R.id.addButton2 -> {
                val intent = Intent(getActivity(), Registration::class.java)
                startActivity(intent)
            }
            R.id.backgroundText -> {
                val intent = Intent(getActivity(), MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }

}


