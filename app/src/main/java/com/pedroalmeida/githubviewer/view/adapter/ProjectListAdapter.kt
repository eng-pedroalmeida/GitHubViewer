package com.pedroalmeida.githubviewer.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedroalmeida.githubviewer.R
import com.pedroalmeida.githubviewer.model.GitHubProject
import kotlinx.android.synthetic.main.list_item_project.view.*

class ProjectListAdapter(private val projects: List<GitHubProject>,
                         private val context: Context): RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder>() {

    /**
     * View Holder nested Class
     */
    class ProjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(project: GitHubProject, context: Context) {
            val projectTitle = itemView.tv_project_name
            val programingLanguage = itemView.tv_programing_language

            projectTitle.text = project.name

            project.language?.let { programingLanguage.text = it }
                    ?: run { programingLanguage.text = context.getString(R.string.undefined) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_project, parent,false)
        return ProjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.bindView(project, context)
    }

}

