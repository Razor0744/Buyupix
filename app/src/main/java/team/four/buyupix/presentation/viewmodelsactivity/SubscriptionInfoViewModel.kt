package team.four.buyupix.presentation.viewmodelsactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.buyupix.domain.models.Subscription
import team.four.buyupix.domain.usecases.DeleteSubscriptionUseCase

class SubscriptionInfoViewModel : ViewModel() {
    private val db = Firebase.firestore

    val documentLiveData = MutableLiveData<DocumentSnapshot>()

    fun deleteSubscription(subscription: Subscription) {
        DeleteSubscriptionUseCase().delete(subscription)
    }

    fun subscriptionInfo(subscription: Subscription) {
        db.collection(subscription.uid).document(subscription.date)
            .collection(subscription.dateType)
            .document(subscription.name)
            .get()
            .addOnSuccessListener { document ->
                documentLiveData.value = document
            }
    }
}