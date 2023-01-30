package com.admission.testceiba.ui.posts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.admission.testceiba.core.constants.ApplicationConstants
import com.admission.testceiba.databinding.ActivityPostsBinding
import com.admission.testceiba.domain.model.PostDom
import com.admission.testceiba.ui.users.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityPosts : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        initSubscriptionsViewModel()
        prepareView()
    }

    private fun prepareView() {
        val userId:Int? = intent.extras?.getInt(ApplicationConstants.KEY_USER_ID_SELECTED)
        userId?.let {
            postViewModel.findUser(userId)
            postViewModel.getPosts(userId)
        }
    }

    private fun initSubscriptionsViewModel() {
        postViewModel.singleLiveEvent.observe(this) { itEvent ->
            when (itEvent) {
                is PostViewModel.ViewEvent.ResponseIsLoading -> {
                    binding.progress.isVisible  = true
                }
                is PostViewModel.ViewEvent.ResponseIsSuccess -> {
                    binding.progress.isVisible  = false
                    setupRecyclerViewItems(itEvent.posts)
                }
                is PostViewModel.ViewEvent.ResponseFindUser -> {
                    with(binding) {
                        tvName.text = itEvent.user.name
                        tvPhone.text = itEvent.user.phone
                        tvEmail.text = itEvent.user.email
                    }
                }
            }
        }
    }

    private fun setupRecyclerViewItems(posts:List<PostDom>) {
        with(binding) {
            recyclerViewPosts.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = PostAdapter(posts)
            }
        }
    }
}