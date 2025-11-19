package org.kot.cmpcourse.repository

import org.kot.cmpcourse.model.Todo

interface TodoRepository {

    suspend fun getAll(): List<Todo>
    suspend fun add(todo: Todo)
    suspend fun update(todo: Todo)
    suspend fun delete(id: String)
}