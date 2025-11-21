package org.kot.cmpcourse.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import org.kot.cmpcourse.model.PostResponse

class PostApi(private val httpClient: HttpClient) {

    private val baseurl = "https://jsonplaceholder.typicode.com/posts"

    suspend fun getPost(id:Int): PostResponse = httpClient.get("$baseurl/$id").body()

    suspend fun getAllPosts(): List<PostResponse> = httpClient.get(baseurl).body()

    suspend fun createPost(post: PostResponse): PostResponse = httpClient.post(baseurl) {
        setBody(post)
    }.body()

    suspend fun updatePost(post: PostResponse): PostResponse = httpClient.put("$baseurl/${post.id}") {
        setBody(post)
    }.body()

    suspend fun patchPost(post: PostResponse): PostResponse = httpClient.patch("$baseurl/${post.id}") {
        setBody(mapOf("title" to post.title))
    }.body()

    suspend fun deletePost(id:Int): HttpResponse = httpClient.delete("$baseurl/$id")
}