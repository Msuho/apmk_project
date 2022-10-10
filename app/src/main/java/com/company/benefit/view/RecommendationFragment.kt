package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentEmailBinding
import com.example.myapplication.databinding.FragmentNameBinding
import com.example.myapplication.databinding.FragmentRecommendationBinding

class RecommendationFragment : Fragment() {


    private lateinit var binding: FragmentRecommendationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendationBinding.inflate(inflater, container, false)

        binding.llSgBack.setOnClickListener {
            (activity as MainActivity).fragmentChange(12)

        }

        return binding.root
    }


}