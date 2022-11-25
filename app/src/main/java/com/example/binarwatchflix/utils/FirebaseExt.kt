package com.example.binarwatchflix.utils


import com.example.binarwatchflix.data.firebase.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.ExperimentalCoroutinesApi

suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            if (it.exception != null) {
                cont.resumeWithException(it.exception!!)
            } else {
                cont.resume(it.result, onCancellation = null)
            }
        }
    }
}

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
        displayName.orEmpty(),
        email.orEmpty(),
        photoUrl.toString()
    )
}