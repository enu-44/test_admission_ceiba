package com.admission.testceiba.ui.posts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.admission.testceiba.databinding.ActivityPostsBinding
import com.admission.testceiba.domain.model.PostDom
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
        initObservers()
        prepareView()
    }

    private fun prepareView() {
        val userId:Int? = intent.extras?.getInt("USER_ID")
        userId?.let {
            postViewModel.findUser(userId)
            postViewModel.getPosts(userId)
        }
    }

    private fun initObservers() {
        postViewModel.selectedUser.observe(this, Observer { userSelected->
            with(binding) {
                tvName.text = userSelected.name
                tvPhone.text = userSelected.phone
                tvEmail.text = userSelected.email
            }
        })
        postViewModel.posts.observe(this, Observer {
                posts-> setupRecyclerViewItems(posts)
        })
        postViewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
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