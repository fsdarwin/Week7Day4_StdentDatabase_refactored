package com.example.week7day4_stdentdatabase_refactored

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.collections.ArrayList
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.DATABASE_NAME
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.DATABASE_VERSION
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.FIELD_CITY
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.FIELD_DOB
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.FIELD_GPA
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.FIELD_MAJOR
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.FIELD_MINOR
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.FIELD_NAME
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.FIELD_SSN
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.FIELD_STATE
import com.example.week7day4_stdentdatabase_refactored.dbStudentConstants.TABLE_NAME

class SQLDBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override//CREATE table with necessary fields
    fun onCreate(db: SQLiteDatabase) {
        val createStmt = ("CREATE TABLE " + TABLE_NAME + " ("
                + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_CITY + " TEXT, "
                + FIELD_STATE + " TEXT, "
                + FIELD_SSN + " TEXT PRIMARY KEY)")
        db.execSQL(createStmt)//Creates the database


    }

    override//Update version number
    fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun selectStudent(studentSsn: String): Student {
        lateinit var returnStudent: Student//Set return value to null
        if (!studentSsn.isEmpty()) {//IF the value passed in is not null
            val sqLiteDatabase = this.readableDatabase//OPEN database for read only
            //SELECT
            val query = "SELECT * FROM $TABLE_NAME WHERE $FIELD_SSN = \"$studentSsn\""
            val cursor = sqLiteDatabase.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                val name = cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                val major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR))
                val minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR))
                val gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA))
                val dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB))
                val city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                val state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                val ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN))
                returnStudent = Student(name, major, minor, gpa, dob, city, state, ssn)
            }
            cursor.close()
        }
        return returnStudent
    }

    fun selectAllStudents(): ArrayList<Student>? {
        val sqLiteDatabase = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = sqLiteDatabase.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val arrayList = ArrayList<Student>()
            do {
                val name = cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                val major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR))
                val minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR))
                val gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA))
                val dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB))
                val city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                val state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                val ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN))
                arrayList.add(Student(name, major, minor, gpa, dob, city, state, ssn))
            } while (cursor.moveToNext())
            return arrayList
        } else {
            return null
        }
    }

    fun insertStudent(student: Student) {
        val database = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(FIELD_NAME, student.name)
        contentValues.put(FIELD_MAJOR, student.major)
        contentValues.put(FIELD_MINOR, student.minor)
        contentValues.put(FIELD_GPA, student.gpa)
        contentValues.put(FIELD_DOB, student.dob)
        contentValues.put(FIELD_CITY, student.homeCity)
        contentValues.put(FIELD_STATE, student.homeState)
        contentValues.put(FIELD_SSN, student.ssn)

        database.insert(TABLE_NAME, null, contentValues)
    }

    fun updateStudent(student: Student): Int {
        val whereClause = FIELD_SSN + " = \"" + student.ssn + "\""
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FIELD_NAME, student.name)
        contentValues.put(FIELD_MAJOR, student.major)
        contentValues.put(FIELD_MINOR, student.minor)
        contentValues.put(FIELD_GPA, student.gpa)
        contentValues.put(FIELD_DOB, student.dob)
        contentValues.put(FIELD_CITY, student.homeCity)
        contentValues.put(FIELD_STATE, student.homeState)
        contentValues.put(FIELD_SSN, student.ssn)
        return database.update(TABLE_NAME, contentValues, whereClause, null)
    }

    fun deleteStudent(student: Student): Int {
        val whereClause = FIELD_SSN + " = \"" + student.ssn + "\""
        val database = writableDatabase
        return database.delete(TABLE_NAME, whereClause, null)
    }
}