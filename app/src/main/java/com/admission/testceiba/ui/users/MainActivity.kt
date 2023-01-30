 package com.admission.testceiba.ui.users

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.admission.testceiba.databinding.ActivityMainBinding
import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.ui.posts.ActivityPosts
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
 class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
     private val userViewModel: UserViewModel by viewModels()

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

     private fun setupView() {
         initObservers()
         initInputListeners()
         prepareView()
     }

     private fun initObservers() {
         userViewModel.users.observe(this, Observer {
                 listUsers->
             binding.includeUsers.tvNotFoundResults.isVisible = listUsers.isEmpty()
             setupRecyclerViewItems(listUsers)
         })
         userViewModel.isLoading.observe(this) {
             binding.includeUsers.progress.isVisible = it
         }
     }

     private fun initInputListeners() {
         with(binding.includeUsers) {
             edtSearchUser.addTextChangedListener(object : TextWatcher {
                 override fun afterTextChanged(s: Editable) {}
                 override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                 override fun onTextChanged(query: CharSequence, start: Int, before: Int, count: Int) =
                     userViewModel.onFilterUsers(query.toString())
             })
         }
     }

     private fun prepareView() = userViewModel.getUsers()

     private fun setupRecyclerViewItems(users:List<UserDom>) {
         with(binding.includeUsers) {
             recyclerViewUsers.apply {
                 layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                 adapter = UserAdapter(users, ::viewPosts)
             }
         }
     }

     private fun viewPosts(userDom: UserDom) {
         val intent = Intent(this, ActivityPosts::class.java)
         intent.putExtra("USER_ID", userDom.id)
         startActivity(intent)
     }
}


