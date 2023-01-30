 package com.admission.testceiba.ui.users

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.admission.testceiba.core.constants.ApplicationConstants
import com.admission.testceiba.databinding.ActivityMainBinding
import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.ui.posts.PostsActivity
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
         initSubscriptionsViewModel()
         initInputListeners()
         prepareView()
     }

     private fun initSubscriptionsViewModel() {
         userViewModel.singleLiveEvent.observe(this) { itEvent ->
             when (itEvent) {
                 is UserViewModel.ViewEvent.ResponseIsLoading -> {
                     binding.includeUsers.progress.isVisible = true
                 }
                 is UserViewModel.ViewEvent.ResponseIsEmpty -> {
                     binding.includeUsers.progress.isVisible = false
                     binding.includeUsers.tvNotFoundResults.isVisible = true
                     setupRecyclerViewItems(emptyList())
                 }
                 is UserViewModel.ViewEvent.ResponseIsSuccess -> {
                     binding.includeUsers.progress.isVisible = false
                     binding.includeUsers.tvNotFoundResults.isVisible = false
                     setupRecyclerViewItems(itEvent.users)
                 }
             }
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
         val intent = Intent(this, PostsActivity::class.java)
         intent.putExtra(ApplicationConstants.KEY_USER_ID_SELECTED, userDom.id)
         startActivity(intent)
     }
}


