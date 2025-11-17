package org.kot.cmpcourse.di

import org.koin.dsl.module
import org.kot.cmpcourse.repository.AppRepository

val appModule = module {
    single<AppRepository> {
        AppRepository()
    }
}