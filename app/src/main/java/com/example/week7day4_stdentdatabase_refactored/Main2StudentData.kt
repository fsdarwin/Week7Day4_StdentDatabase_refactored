package com.example.week7day4_stdentdatabase_refactored

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2_student_data.etName
import kotlinx.android.synthetic.main.activity_main2_student_data.etMajor
import kotlinx.android.synthetic.main.activity_main2_student_data.etMinor
import kotlinx.android.synthetic.main.activity_main2_student_data.etGpa
import kotlinx.android.synthetic.main.activity_main2_student_data.etDob
import kotlinx.android.synthetic.main.activity_main2_student_data.etCity
import kotlinx.android.synthetic.main.activity_main2_student_data.etState
import kotlinx.android.synthetic.main.activity_main2_student_data.etSsn


class Main2StudentData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2_student_data)
        var sqldbHelper = SQLDBHelper(this)

        val TAG = "NULL_CHECK "

        fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main2_student_data)
        }


        fun clear() {

            etName.setText("")
            etMajor.setText("")
            etMinor.setText("")
            etGpa.setText("")
            etDob.setText("")
            etCity.setText("")
            etState.setText("")
            etSsn.setText("")
        }

        fun displayStudent(student: Student) {
            etName.setText(student.name)
            etMajor.setText(student.major)
            etMinor.setText(student.minor)
            etGpa.setText(student.gpa)
            etDob.setText(student.dob)
            etCity.setText(student.homeCity)
            etState.setText(student.homeState)
            etSsn.setText(student.ssn)
        }

        fun getUpdateStudent() {

            var name = etName.text.toString()
            val major = etMajor.text.toString()
            val minor = etMinor.text.toString()
            val gpa = etGpa.text.toString()
            val dob = etDob.text.toString()
            val city = etCity.text.toString()
            val state = etState.text.toString()
            val ssn = etSsn.text.toString()

            val student = Student(name, major, minor, gpa, dob, city, state, ssn)
            sqldbHelper.updateStudent(student)
        }

        fun getInsertStudent() {

            val name = etName.text.toString()
            val major = etMajor.text.toString()
            val minor = etMinor.text.toString()
            val gpa = etGpa.text.toString()
            val dob = etDob.text.toString()
            val city = etCity.text.toString()
            val state = etState.text.toString()
            val ssn = etSsn.text.toString()

            val student = Student(name, major, minor, gpa, dob, city, state, ssn)
            sqldbHelper.insertStudent(student)
        }

        fun onClick(view: View) {
            when (view.id) {
                R.id.btnSelect ->
                    try {
                        val ssn = etSsn.text.toString()
                        Log.d(TAG, " $ssn")
                        Toast.makeText(this, ssn, Toast.LENGTH_SHORT).show()
                        val student = sqldbHelper.selectStudent(ssn)
                        displayStudent(student)
                    } catch (e: Exception) {
                        Toast.makeText(applicationContext, "Student does not exist.$e", Toast.LENGTH_SHORT).show()
                    }

                R.id.btnUpdate -> try {
                    getUpdateStudent()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "This student does not exist.$e", Toast.LENGTH_SHORT).show()
                }

                R.id.btnInsert -> try {
                    getInsertStudent()
                    Toast.makeText(applicationContext, "Student Inserted.", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "This student already exist.$e", Toast.LENGTH_SHORT).show()
                }

                R.id.btnDelete -> try {
                    val student = sqldbHelper.selectStudent(etSsn.text.toString())
                    sqldbHelper.deleteStudent(student)
                    clear()
                    Toast.makeText(applicationContext, "Student has been deleted.", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "This student does not exist.$e", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
