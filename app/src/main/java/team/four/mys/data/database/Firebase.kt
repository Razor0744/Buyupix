package team.four.mys.data.database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.mys.data.room.Subscription
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Firebase : FirebaseDatabase {

    private val db = Firebase.firestore
    private val user = FirebaseAuth.getInstance().currentUser

    private val documentName = "data"
    private val documentNumberSync = "time"

    private val TAG = "Firebase"

    override fun getUID(): String {
        return user?.uid.toString()
    }

    override suspend fun getData(uid: String): DocumentSnapshot? = suspendCoroutine {
        db
            .collection(uid)
            .document(documentName)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    it.resume(document)
                } else {
                    it.resume(null)
                }
            }

            .addOnFailureListener { e ->
                Log.e(TAG, e.toString())
            }
    }

    override fun setData(uid: String, list: List<Subscription>) {
        db
            .collection(uid)
            .document(documentName)
            .set(list)
            .addOnSuccessListener {
                Log.i(TAG, "set data is successful")
            }

            .addOnFailureListener { e -> Log.e(TAG, e.toString()) }
    }

    override suspend fun getNumberSync(uid: String): DocumentSnapshot? = suspendCoroutine {
        db
            .collection(uid)
            .document(documentNumberSync)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    it.resume(document)
                } else {
                    it.resume(null)
                }
            }

            .addOnFailureListener { e ->
                Log.e(TAG, e.toString())
            }
    }

    override fun setNumberSync(uid: String, number: Int) {
        db
            .collection(uid)
            .document(documentNumberSync)
            .set(number)
            .addOnSuccessListener {
                Log.i(TAG, "set data is successful")
            }

            .addOnFailureListener { e -> Log.e(TAG, e.toString()) }
    }

    override fun checkUser(): Boolean {
        val checkUser = Firebase.auth.currentUser
        return checkUser != null
    }

}