package com.example.week7day4_stdentdatabase_refactored

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*

class RecyclerViewAdapter(private var studentsArrayList: ArrayList<Student>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context)
                .inflate(R.layout.item, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return studentsArrayList.size
    }

    override fun onBindViewHolder(p0: RecyclerViewAdapter.ViewHolder, p1: Int) {
        p0.tvVName.text = studentsArrayList[p1].name
        p0.tvVMajor.text = studentsArrayList[p1].major
        p0.tvVMinor.text = studentsArrayList[p1].minor
        p0.tvVGpa.text = studentsArrayList[p1].gpa
        p0.tvVDob.text = studentsArrayList[p1].dob
        p0.tvVHomeCity.text = studentsArrayList[p1].homeCity
        p0.tvVHomeState.text = studentsArrayList[p1].homeState
        p0.tvVSsn.text = studentsArrayList[p1].ssn
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Declare Views
        val tvVName = itemView.tvName
        val tvVMajor = itemView.tvMajor
        val tvVMinor = itemView.tvMinor
        val tvVGpa = itemView.tvGpa
        val tvVDob = itemView.tvDob
        val tvVHomeCity = itemView.tvHomeCity
        val tvVHomeState = itemView.tvHomeState
        val tvVSsn = itemView.tvSsn
    }
}

