package org.kot.cmpcourse.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.kot.cmpcourse.TodoDatabase
import java.util.Properties

actual class DriverFactory {

    actual fun createDriver(): SqlDriver?{
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:test.db", Properties(), TodoDatabase.Schema)
        return driver
    }
}
