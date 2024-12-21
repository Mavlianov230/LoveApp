package com.example.loveapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loveapp.Data.CalculateResult
import com.example.loveapp.Data.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalculateViewModel(application: Application) : AndroidViewModel(application) {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _result = MutableLiveData<CalculateResult>()
    val result: LiveData<CalculateResult> = _result

    fun calculateLove(firstName: String, secondName: String) {
        _loading.value = true
        val apiKey = "02d5869b22msh581e03c603a9a3ep1b140djsnb93850019c3f"
        val apiHost = "love-calculator.p.rapidapi.com"

        val call = RetrofitInstance.api.getPercentage(firstName, secondName, apiKey, apiHost)
        call.enqueue(object : Callback<CalculateResult> {
            override fun onResponse(call: Call<CalculateResult>, response: Response<CalculateResult>) {
                _loading.value = false
                if (response.isSuccessful) {
                    _result.value = response.body()
                } else {
                    _error.value = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<CalculateResult>, t: Throwable) {
                _loading.value = false
                _error.value = "Failed to load data: ${t.message}"
            }
        })
    }
}
