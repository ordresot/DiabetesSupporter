package com.ordresot.diabetessupporter.data.auth

import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.ordresot.diabetessupporter.data.AuthClient

class Auth0Client(
    private val credentialsManager: CredentialsManager,
    private val loginBuilder: WebAuthProvider.Builder,
    private val authClient: AuthenticationAPIClient,
    private val logoutBuilder: WebAuthProvider.LogoutBuilder
): AuthClient {
    override fun saveCredentials(credentials: Credentials) {
        credentialsManager.saveCredentials(credentials)
    }

    override fun getCredentials(callback: Callback<Credentials, CredentialsManagerException>) {
        credentialsManager.getCredentials(callback)
    }

    override fun clearCredentials() {
        credentialsManager.clearCredentials()
    }

    override fun checkCredentials(): Boolean {
        return credentialsManager.hasValidCredentials()
    }

    override fun getUserProfile(callback: Callback<UserProfile, AuthenticationException>) {
        getCredentials(
            object : Callback<Credentials, CredentialsManagerException> {
                override fun onFailure(error: CredentialsManagerException) {}

                override fun onSuccess(result: Credentials) {
                    authClient.userInfo(result.accessToken).start(callback)
                }
            }
        )
    }

    override fun getLoginBuilder(): WebAuthProvider.Builder {
        return loginBuilder
    }

    override fun getLogoutBuilder(): WebAuthProvider.LogoutBuilder {
        return logoutBuilder
    }
}