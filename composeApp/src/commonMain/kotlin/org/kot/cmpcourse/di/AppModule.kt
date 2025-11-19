package org.kot.cmpcourse.di

import com.russhwolf.settings.Settings
import org.koin.dsl.bind
import org.koin.dsl.module
import org.kot.cmpcourse.TodoDatabase
import org.kot.cmpcourse.database.DriverFactory
import org.kot.cmpcourse.repository.AppRepository
import org.kot.cmpcourse.repository.TodoRepository
import org.kot.cmpcourse.repository.TodoRepositoryImpl
import org.kot.cmpcourse.repository.WebTodoRepositoryImpl

val appModule = module {
    single<AppRepository> {
        AppRepository()
    }
    single {
        val sqlDriver = DriverFactory().createDriver()
        if (sqlDriver == null){
            WebTodoRepositoryImpl(Settings())
        } else {
            val database = TodoDatabase.invoke(sqlDriver)
            TodoRepositoryImpl(database)
        }
    }.bind<TodoRepository>()
}