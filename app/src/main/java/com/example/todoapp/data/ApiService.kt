package com.example.todoapp.data

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {
    @GET("/todos")
    suspend fun getTasks(): List<Task>

    @POST("/todos")
    suspend fun addTask(@Body task: Task): Task

    @PUT("/todos/{id}")
    suspend fun updateTask(@Path("id") id: Int, @Body task: Task): Task

    @DELETE("/todos/{id}")
    suspend fun deleteTask(@Path("id") id: Int)
}