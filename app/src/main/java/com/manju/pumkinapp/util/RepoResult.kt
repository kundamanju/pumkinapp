package com.manju.pumkinapp.util
sealed class RepoResult<T> {
    data class Success<T>(val data: T): RepoResult<T>()
    data class Error<T>(val message: String): RepoResult<T>()
}