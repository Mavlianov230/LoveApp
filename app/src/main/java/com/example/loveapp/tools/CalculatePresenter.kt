package com.example.loveapp.tools

import com.example.loveapp.Data.ApiService
import com.example.loveapp.Data.CalculateResult
import com.example.loveapp.Data.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalculatePresenter(private val view: CalculateView) {

    private val apiService = RetrofitInstance.api

    fun calculateLove(firstName: String, secondName: String) {
        view.showLoading()

        val apiKey = "02d5869b22msh581e03c603a9a3ep1b140djsnb93850019c3f"
        val apiHost = "love-calculator.p.rapidapi.com"
        val call = apiService.getPercentage(firstName, secondName, apiKey, apiHost)

        call.enqueue(object : Callback<CalculateResult> {
            override fun onResponse(call: Call<CalculateResult>, response: Response<CalculateResult>) {
                view.hideLoading()
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        view.showResult(result.firstName, result.secondName, result.percentage, result.result)
                    } else {
                        view.showError("Ошибка: Пустой ответ от сервера")
                    }
                } else {
                    view.showError("Ошибка: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<CalculateResult>, t: Throwable) {
                view.hideLoading()
                view.showError("Не удалось загрузить данные: ${t.message}")
            }
        })
    }
}
