package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentEmailBinding
import com.example.myapplication.databinding.FragmentNameBinding
import com.example.myapplication.databinding.FragmentPasswordBinding

class PasswordFragment : Fragment() {


    private lateinit var binding: FragmentPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater, container, false)

        binding.llSgBack.setOnClickListener {
            (activity as MainActivity).fragmentChange(11)

        }

        return binding.root
    }


}