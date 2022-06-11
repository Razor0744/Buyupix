package team.four.mys

import adapters.CustomRecyclerAdapterDate
import adapters.CustomRecyclerAdapterNoDate
import fragments.NavigationFragment
import models.SubscriptionDate
import models.SubscriptionNoDate
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.databinding.ActivitySubscriptionsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class SubscriptionsActivity : AppCompatActivity() {

    private lateinit var subscriptionsDate: ArrayList<SubscriptionDate>
    private lateinit var subscriptionsNoDate: ArrayList<SubscriptionNoDate>
    private lateinit var adapterDate: CustomRecyclerAdapterDate
    private lateinit var adapterNoDate: CustomRecyclerAdapterNoDate
    private lateinit var db : FirebaseFirestore
    private lateinit var binding: ActivitySubscriptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // all subscriptions
        subscriptionsDate = arrayListOf()
        subscriptionsNoDate = arrayListOf()
        adapterDate = CustomRecyclerAdapterDate(this, subscriptionsDate)
        adapterNoDate = CustomRecyclerAdapterNoDate(this, subscriptionsNoDate)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val concatAdapter = ConcatAdapter(adapterDate, adapterNoDate)
        binding.recyclerView.adapter = concatAdapter

        fireStore()
        number()
    }


    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun fireStore() {
        db = FirebaseFirestore.getInstance()
        db.collection(uid()).document("NoDate").collection(uid())
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            subscriptionsNoDate.add(dc.document.toObject(SubscriptionNoDate::class.java))
                        }
                    }
                    adapterNoDate.notifyDataSetChanged()
                }

            })

        db.collection(uid()).document("Date").collection(uid())
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            subscriptionsDate.add(dc.document.toObject(SubscriptionDate::class.java))
                        }
                    }
                    adapterDate.notifyDataSetChanged()
                }

            })
    }

    private fun number(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = NavigationFragment()
        val bundle = Bundle()
        bundle.putInt("i", 1)
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment, fragment).commit()
    }
}
