package org.kot.cmpcourse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.kot.cmpcourse.model.PostResponse
import org.kot.cmpcourse.networking.PostApi
import org.kot.cmpcourse.networking.getHttpClient

@Composable
fun PostScreen() {
    var posts by remember {
        mutableStateOf<List<PostResponse>>(
            emptyList()
        )
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    val httpClient = remember {
        getHttpClient()
    }
    val postApi = remember {
        PostApi(httpClient)
    }

    LaunchedEffect(Unit) {
        val postsResponse = postApi.getAllPosts()
        posts = postsResponse
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(posts) { post ->
                isLoading = true
                PostItem(post)
                isLoading = false
            }
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun PostItem(postResponse: PostResponse) {
    Card(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = postResponse.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = postResponse.body)
        }
    }
}