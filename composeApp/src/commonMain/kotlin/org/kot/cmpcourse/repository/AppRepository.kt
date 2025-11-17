package org.kot.cmpcourse.repository

import org.kot.cmpcourse.settings.AppSettings

class AppRepository {

    fun getLoggedInEmail(): String {
        return AppSettings.getString(AppSettings.EMAIL, "")
    }

}