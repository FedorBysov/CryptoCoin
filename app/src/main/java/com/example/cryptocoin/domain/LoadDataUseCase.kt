package com.example.cryptocoin.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke() = repository.loadData()
}