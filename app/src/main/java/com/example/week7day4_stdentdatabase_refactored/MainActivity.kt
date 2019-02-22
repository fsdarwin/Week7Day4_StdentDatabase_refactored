package com.example.week7day4_stdentdatabase_refactored

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    internal lateinit var recyclerView: RecyclerView
    internal var rvAdaptor: RecyclerViewAdapter? = RecyclerViewAdapter(ArrayList(listOfStudents()))
    val TAG = "FRANK: "


    internal var sqldbHelper = SQLDBHelper(this)//INSTANCIATE database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //INSERT students and log them
        val studentA = Student("Pat", "Art History", "Pan Handling", "2.1", "31 Feb 2000", "Nashville", "TN", "1")
        sqldbHelper.insertStudent(studentA)
        Log.d(TAG, "INSERT: $studentA")
        val studentB = Student("Dave", "Pottery", "Basket Weaving", "2.9", "08 Aug 2001", "San Francisco", "CA", "2")
        sqldbHelper.insertStudent(studentB)
        Log.d(TAG, "INSERT: $studentB")
        val studentC = Student("Carrie", "Law", "Ass-kissing", "4.1", "04 Jul 1999", "New York", "NY", "3")
        sqldbHelper.insertStudent(studentC)
        Log.d(TAG, "INSERT: $studentC")
        val studentD = Student("Ty", "Hanging Out", "Girls", "2.0", "15 Feb 2002", "Orlando", "FL", "4")
        sqldbHelper.insertStudent(studentD)
        Log.d(TAG, "INSERT: $studentD")
        val studentE = Student("Marcus", "Math", "Computers", "3.9", "15 Sep 2002", "Portland", "OR", "5")
        sqldbHelper.insertStudent(studentE)
        Log.d(TAG, "INSERT: $studentE")
        val studentF = Student("Sue", "English", "Waitressing", "4.0", "17 Apr 2002", "Seattle", "WA", "6")
        sqldbHelper.insertStudent(studentF)
        Log.d(TAG, "INSERT: $studentF")

        recyclerView = findViewById(R.id.rvMainRecyclerView)
        val rvAdapter = RecyclerViewAdapter(ArrayList(listOfStudents()))
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = rvAdapter
    }

    fun listOfStudents() : ArrayList<Student>? {

        return sqldbHelper.selectAllStudents()
    }

    fun onClick(view: View) {
        val intent = Intent(this, Main2StudentData::class.java)
        startActivity(intent)
    }
}
