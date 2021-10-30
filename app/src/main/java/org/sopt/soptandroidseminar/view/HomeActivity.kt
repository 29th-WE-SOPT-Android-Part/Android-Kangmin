package org.sopt.soptandroidseminar.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.sopt.soptandroidseminar.R
import org.sopt.soptandroidseminar.api.GithubServiceCreator
import org.sopt.soptandroidseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileImage()
        changeFragmentEvent()
    }

    private fun profileImage() {
        val call = GithubServiceCreator.apiService.getUserInfo()
        call.enqueueUtil(
            onSuccess = {
                Glide.with(this).load(it.avatar_url).into(binding.ivProfile)
            }
        )
    }

    private fun changeFragmentEvent() {
        val followerListFragment = FollowerListFragment()
        val repoListFragment = RepoListFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_list, followerListFragment)
            .commit()

        repoListChangeEvent(repoListFragment)
        followerListChangeEvent(followerListFragment)
    }

    private fun repoListChangeEvent(repoListFragment: RepoListFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        binding.btnRepoList.setOnClickListener {
            transaction.replace(R.id.fragment_list, repoListFragment)
            transaction.commit()
        }
    }

    private fun followerListChangeEvent(followerListFragment: FollowerListFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        binding.btnFollowerList.setOnClickListener {
            transaction.replace(R.id.fragment_list, followerListFragment)
            transaction.commit()
        }
    }
}