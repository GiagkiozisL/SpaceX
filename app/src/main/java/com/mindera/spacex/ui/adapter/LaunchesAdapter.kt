package com.mindera.spacex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindera.spacex.R
import com.mindera.spacex.data.models.Launch
import com.mindera.spacex.utils.DateTimeUtils
import kotlinx.android.synthetic.main.row_launch_item.view.*
import okhttp3.internal.notifyAll

class LaunchesAdapter(private val context: Context,
                      var launchList: List<Launch>,
                      private val callback: LaunchListener)
    : RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {

    public interface LaunchListener {
        fun onItemClicked(launch: Launch)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val missionNameTextView: AppCompatTextView = view.mission_textview
        val dateTimeTextView: AppCompatTextView = view.date_time_textview
        val rocketNameTextView: AppCompatTextView = view.rocket_textview
        val diffDaysTextView: AppCompatTextView = view.days_different_textview
        val statusImageView: AppCompatImageView = view.status_image_view
        val missionImageView: AppCompatImageView = view.patch_image_view
        val daysPlaceHOlderTextView: AppCompatTextView = view.date_placeholder_textview
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LaunchesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_launch_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launch = launchList.get(position)

        holder.missionNameTextView.text = launch.missionName
        holder.dateTimeTextView.text = DateTimeUtils.getDateTimeFromMillis(launch.launchDateUnix)
        holder.rocketNameTextView.text = launch.rocket.rocketName

        val diffDays = DateTimeUtils.getDaysDifference(launch.launchDateUnix)
        val isSince = diffDays > 0
        holder.diffDaysTextView.text = if (isSince) diffDays.toString() else (diffDays * (-1)).toString()

        val prep = if (isSince) context.getString(R.string.since) else context.getString(R.string.from)
        val daysPlaceholder = context.getString(R.string.day_from_now, prep)
        holder.daysPlaceHOlderTextView.text = daysPlaceholder

        if (launch.launchSuccess) {
            holder.statusImageView.setImageResource(R.drawable.ic_checkmark)
        } else {
            holder.statusImageView.setImageResource(R.drawable.ic_failure)
        }

        Glide.with(context)
            .load(launch.links.missionPatch)
            .placeholder(R.drawable.rocket_00)
            .into(holder.missionImageView)

        holder.itemView.setOnClickListener { callback.onItemClicked(launch) }
    }

    override fun getItemCount() = launchList.size

    fun clear() {
        launchList = emptyList()
    }

    fun addAll(launchList: List<Launch>) {
        this.launchList = launchList
        notifyDataSetChanged()
    }
}