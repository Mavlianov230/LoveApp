package com.example.loveapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentResultBinding
import kotlin.random.Random
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstName = arguments?.getString("firstName") ?: "You"
        val secondName = arguments?.getString("secondName") ?: "Me"
        binding.textYou.text = firstName
        binding.textMe.text = secondName
        val score = Random.nextInt(0, 101)
        binding.textScore.text = "$score%"
        binding.buttonTryAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_calculateFragment)
        }
    }
}

