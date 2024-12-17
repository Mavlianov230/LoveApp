package com.example.loveapp.tools

interface CalculateView {
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
    fun showResult(firstName: String,
                   secondName: String,
                   percentage: String,
                   result: String
    )
}

