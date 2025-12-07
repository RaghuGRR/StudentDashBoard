package com.android.data.repositoryImpl

import com.android.data.SessionManager
import com.android.domain.repository.LoginRepository
import com.android.domain.util.DataResource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val sharedPref: SessionManager
) : LoginRepository {

    override fun isUserLoggedIn(): Boolean {
        return sharedPref.isUserLoggedIn()
    }

    override suspend fun login(schoolId: String, studentId: String): Flow<DataResource<Unit>> =
        callbackFlow {
            trySend(DataResource.Loading)

            val email = "${studentId}@studentdashboard.app".trim()
            val password = schoolId.trim()

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    trySend(DataResource.Success(Unit))
                    sharedPref.saveLoginState(true)
                    sharedPref.saveUserId(studentId)
                    close()
                }
                .addOnFailureListener { exception ->
                    trySend(DataResource.Error(exception))
                    close()
                }

            awaitClose { }
        }

    override suspend fun logOut() {
        firebaseAuth.signOut()
        sharedPref.clearSession()
    }
}