package com.example.yugenwalls

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugenwalls.Data.RandomAnimeImage
import com.example.yugenwalls.Retrofit.NekosAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val nekosAPI: NekosAPI
) : ViewModel() {
    private val _animeImageList = MutableLiveData<List<RandomAnimeImage>>()
    val animeImageList : MutableLiveData<List<RandomAnimeImage>> = _animeImageList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : MutableLiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : MutableLiveData<String?> = _errorMessage

    init {
        getRandomImages()
    }
    fun getRandomImages() {
        if(_animeImageList.value?.isNotEmpty() == true || _isLoading.value == true) return

        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = nekosAPI.getRandomAnime()
            if (response.isSuccessful) {
                val dataList = response.body()
                val errorMessage = response.errorBody()?.string()
                withContext(Dispatchers.Main) {
                    Log.d("charu", "$dataList")
                    Log.d("charu", "${dataList?.get(0)?.url}, rating: ${dataList?.get(0)?.rating}")
                    _animeImageList.value = dataList!!
                    _errorMessage.value = errorMessage
                    _isLoading.value = false
                }
            }
        }
    }
}