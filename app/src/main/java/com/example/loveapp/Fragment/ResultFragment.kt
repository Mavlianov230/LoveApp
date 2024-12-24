package com.example.loveapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loveapp.DAO.LoveResultDao
import com.example.loveapp.databinding.FragmentResultBinding
import javax.inject.Inject

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var loveResultDao: LoveResultDao // Inject Dao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = arguments?.getString("firstName") ?: "You"
        val secondName = arguments?.getString("secondName") ?: "Me"
        val percentage = arguments?.getString("percentage") ?: "0%"
        val result = arguments?.getString("result") ?: "No result"

        binding.textYou.text = firstName
        binding.textMe.text = secondName
        binding.textScore.text = percentage
        binding.textYourScore.text = result

        binding.buttonTryAgain.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

