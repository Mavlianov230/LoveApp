package com.example.loveapp.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loveapp.Data.RetrofitInstance
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentCalculateBinding
import com.example.loveapp.tools.CalculatePresenter
import com.example.loveapp.tools.CalculateView


class CalculateFragment : Fragment(), CalculateView {

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: CalculatePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = CalculatePresenter(this)

        binding.btnCalculate.setOnClickListener {
            val firstName = binding.editFirstname.text.toString().trim()
            val secondName = binding.editSecondname.text.toString().trim()
            if (firstName.isNotEmpty() && secondName.isNotEmpty()) {
                presenter.calculateLove(firstName, secondName)
            } else {
                Toast.makeText(requireContext(), "Please fill in both names", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_SHORT).show()
    }

    override fun showResult(firstName: String, secondName: String, percentage: String, result: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            val bundle = Bundle().apply {
                putString("firstName", firstName)
                putString("secondName", secondName)
                putString("percentage", percentage)
                putString("result", result)
            }
            try {
                findNavController().navigate(R.id.action_calculateFragment_to_resultFragment, bundle)
            } catch (e: Exception) {
                showError("Navigation error: ${e.message}")
            }
        }, 2000)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
