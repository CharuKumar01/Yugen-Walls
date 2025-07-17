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

    fun getRandomImages() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = nekosAPI.getRandomAnime()
            if (response.isSuccessful) {
                val dataList = response.body()
                withContext(Dispatchers.Main) {
                    Log.d("charu", "$dataList")
                    Log.d("charu", "${dataList?.get(0)?.url}, rating: ${dataList?.get(0)?.rating}")
                    _animeImageList.value = dataList!!
                }
            }
        }
    }
}