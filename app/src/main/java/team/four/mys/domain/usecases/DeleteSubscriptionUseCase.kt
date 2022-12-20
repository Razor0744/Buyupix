package team.four.mys.domain.usecases

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.mys.domain.models.SubscriptionInfoParam

class DeleteSubscriptionUseCase {

    private val db = Firebase.firestore

    fun execute(subscriptionInfoParam: SubscriptionInfoParam){
        db.collection(subscriptionInfoParam.uid)
            .document(subscriptionInfoParam.date)
            .collection(subscriptionInfoParam.dateType)
            .document(subscriptionInfoParam.name)
            .delete()
    }
}