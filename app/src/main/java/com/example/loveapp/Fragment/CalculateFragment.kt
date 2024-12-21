package com.example.loveapp.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentCalculateBinding
import com.example.loveapp.viewmodel.CalculateViewModel

class CalculateFragment : Fragment() {

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!

    private val calculateViewModel: CalculateViewModel by viewModels()

    private var progressBarDelayHandler: Handler? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculateViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                progressBarDelayHandler?.removeCallbacksAndMessages(null)
                progressBarDelayHandler = Handler(Looper.getMainLooper())
                progressBarDelayHandler?.postDelayed({
                    binding.progressBar.visibility = View.GONE
                }, 3000)
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        calculateViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        calculateViewModel.result.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                Handler(Looper.getMainLooper()).postDelayed({
                    val bundle = Bundle().apply {
                        putString("firstName", it.firstName)
                        putString("secondName", it.secondName)
                        putString("percentage", it.percentage)
                        putString("result", it.result)
                    }
                    try {
                        findNavController().navigate(R.id.action_calculateFragment_to_resultFragment, bundle)
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Navigation error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }, 3000)
            }
        })

        binding.btnCalculate.setOnClickListener {
            val firstName = binding.editFirstname.text.toString().trim()
            val secondName = binding.editSecondname.text.toString().trim()
            if (firstName.isNotEmpty() && secondName.isNotEmpty()) {
                calculateViewModel.calculateLove(firstName, secondName)
            } else {
                Toast.makeText(requireContext(), "Please fill in both names", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        progressBarDelayHandler?.removeCallbacksAndMessages(null)
    }
}
