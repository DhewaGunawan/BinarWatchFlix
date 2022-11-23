package com.example.binarwatchflix.utils

import com.example.binarwatchflix.data.firebase.model.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun DatabaseReference.setValueAsync(data: Any): Boolean {
    return suspendCancellableCoroutine { cont ->
        setValue(data)
            .addOnCompleteListener {
                cont.resume(true, onCancellation = null)
            }
            .addOnCanceledListener {
                cont.resume(false, onCancellation = null)
            }
            .addOnFailureListener {
                cont.resumeWithException(it)
            }
    }
}

fun FirebaseUser.toUserObject(): User {
    return User(
        email.orEmpty()
    )
}