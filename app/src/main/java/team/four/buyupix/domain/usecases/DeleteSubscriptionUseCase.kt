package team.four.buyupix.domain.usecases

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.buyupix.domain.models.Subscription

class DeleteSubscriptionUseCase {

    private val db = Firebase.firestore

    fun delete(subscription: Subscription){
        db.collection(subscription.uid)
            .document(subscription.date)
            .collection(subscription.dateType)
            .document(subscription.name)
            .delete()
    }
}