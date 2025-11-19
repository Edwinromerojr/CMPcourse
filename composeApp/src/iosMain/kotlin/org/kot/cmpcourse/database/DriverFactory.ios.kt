package org.kot.cmpcourse.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.kot.cmpcourse.TodoDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver? {
        return NativeSqliteDriver(TodoDatabase.Schema, "todo.db")
    }
}

