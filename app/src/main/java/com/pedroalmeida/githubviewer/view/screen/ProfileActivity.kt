package com.pedroalmeida.githubviewer.view.screen

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pedroalmeida.githubviewer.R
import com.pedroalmeida.githubviewer.model.GitHubProject
import com.pedroalmeida.githubviewer.model.GitHubUser
import com.pedroalmeida.githubviewer.presenter.IPresenter
import com.pedroalmeida.githubviewer.presenter.ProfilePresenter
import com.pedroalmeida.githubviewer.util.AppConstants
import com.pedroalmeida.githubviewer.view.IView
import com.pedroalmeida.githubviewer.view.adapter.ProjectListAdapter

import kotlinx.android.synthetic.main.activity_profile.*
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_error.*
import kotlinx.android.synthetic.main.content_list_empty.*
import kotlinx.android.synthetic.main.content_progress.*
import kotlinx.android.synthetic.main.content_user_not_found.*


class ProfileActivity : AppCompatActivity(), IView.Profile {

    private var repos: List<GitHubProject>? = null
    private var user: GitHubUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setupView()

        if (savedInstanceState == null) {
            val userName = intent.getStringExtra(AppConstants.USERNAME_INTENT_EXTRA)
            userName?.let {
                val presenter: IPresenter.Profile = ProfilePresenter(this, it)
                presenter.loadProfileData()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        repos.let { list ->
            outState?.putParcelableArrayList(AppConstants.REPOS_KEY, ArrayList(list))
            user.let {
                outState?.putParcelable(AppConstants.USER_KEY, it)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            val user = savedInstanceState.getParcelable<GitHubUser>(AppConstants.USER_KEY)
            val repos = savedInstanceState.getParcelableArrayList<GitHubProject>(AppConstants.REPOS_KEY)

            repos.let {
                this.repos = it
                this.user = user

                showUserInfo(user)
                showUserRepos(repos)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showUserInfo(user: GitHubUser) {
        this.user = user

        tv_username.text = user.login
        user.avatar_url.let {
            Glide.with(this)
                    .load(it)
                    .into(iv_avatar)
        }
    }

    override fun showUserRepos(repos: List<GitHubProject>) {
        this.repos = repos

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.SRC_ATOP)

        recycler_view.adapter = ProjectListAdapter(repos, this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.layoutManager = layoutManager

        layout_progress.visibility = View.GONE
        layout_not_found.visibility = View.GONE
        layout_error.visibility = View.GONE

        if (repos.isEmpty()) {
            layout_empty.visibility = View.VISIBLE
        } else {
            recycler_view.visibility = View.VISIBLE
        }

    }

    override fun showUserNotFound() {
        recycler_view.visibility = View.GONE
        layout_error.visibility = View.GONE
        layout_not_found.visibility = View.VISIBLE
        layout_progress.visibility = View.GONE
    }

    override fun showError() {
        layout_not_found.visibility = View.GONE
        recycler_view.visibility = View.GONE
        layout_error.visibility = View.VISIBLE
        layout_progress.visibility = View.GONE
    }

    private fun setupView() {
        //Action bar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        //Actions
        btn_error.setOnClickListener { finish() }
        btn_not_found.setOnClickListener { finish() }

        //Progress
        layout_progress.visibility = View.VISIBLE
    }

}
