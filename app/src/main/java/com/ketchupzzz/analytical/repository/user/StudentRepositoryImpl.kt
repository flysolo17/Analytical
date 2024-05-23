package com.ketchupzzz.analytical.repository.user

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.storage.FirebaseStorage
import com.ketchupzzz.analytical.models.SchoolLevel
import com.ketchupzzz.analytical.models.Students
import com.ketchupzzz.analytical.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull


class StudentRepositoryImpl(
    private val auth : FirebaseAuth,
    private val firestore : FirebaseFirestore,
    private val storage : FirebaseStorage
): StudentRepository {
    private val student = MutableStateFlow<Students?>(null)

    override fun getStudent(): Flow<Students?> {
        return student
    }

    override fun setStudent(student: Students?) {
        this.student.value = student
    }


    override fun currentUser(): FirebaseUser ? {
        return auth.currentUser
    }

    override fun login(studentID: String, password: String, result: (UiState<Students>) -> Unit) {
        val document = firestore.collection(STUDENTS_COLLECTION).document(studentID)
        result.invoke(UiState.Loading)
        document.get().addOnSuccessListener {
            if (it.exists()) {
                val student = it.toObject(Students::class.java)
                student?.let {stud->
                    auth.signInWithEmailAndPassword(student.email ?: "", password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            setStudent(stud)
                            result.invoke(UiState.Success(stud))
                        } else {
                            result.invoke(UiState.Error("Failed to login"))
                        }
                    }.addOnFailureListener {
                        result.invoke(UiState.Error("${it.message}"))
                    }
                }


            } else {
                result(UiState.Error("User not exists"))

            }
        }.addOnFailureListener {
            result(UiState.Error("${it.message}"))
        }
    }



    override fun register(
        studentID: String,
        firstName: String,
        middleName: String,
        lastName: String,
        schoolLevel: SchoolLevel,
        email: String,
        password: String,
        result: (UiState<String>) -> Unit
    ) {
        result(UiState.Loading)
        val document = firestore.collection(STUDENTS_COLLECTION).document(studentID)
        document.get().addOnSuccessListener {
            if (it.exists()) {
                result(UiState.Error("Student ID already exists"))
            } else {
                val student = Students(
                    id = studentID,
                    fname = firstName,
                    mname = middleName,
                    lname = lastName,
                    schoolLevel  = schoolLevel,
                    email = email
                )
                saveStudent(student,password,result)
            }
        }.addOnFailureListener {
            result(UiState.Error("${it.message}"))
        }
    }

    override fun saveStudent(
        student: Students,
        password: String,
        result: (UiState<String>) -> Unit
    ) {
        val document = firestore.collection(STUDENTS_COLLECTION).document(student.id ?: "")
        document.set(student)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    auth.createUserWithEmailAndPassword(
                        student.email ?: "",
                        password
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            setStudent(student)
                            result(UiState.Success("Student saved successfully"))
                        } else {
                            document.delete()
                            result(UiState.Error("Failed to save student"))
                        }
                    }
                } else {
                    result(UiState.Error("Failed to save student"))
                }
            }.addOnFailureListener {
                result(UiState.Error("${it.message}"))
            }
    }

    override fun getStudentByID(studentID: String, result: (UiState<Boolean>) -> Unit) {
        result.invoke(UiState.Loading)
        firestore.collection(STUDENTS_COLLECTION).document(studentID).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    result.invoke(UiState.Error("Student ID already exists"))
                } else {
                    result.invoke(UiState.Success(true))
                }
            }.addOnFailureListener {
                result.invoke(UiState.Error("${it.message}"))
            }
    }

    override fun getUserByEmail(email: String, result: (UiState<Students>) -> Unit) {
        result.invoke(UiState.Loading)
        firestore.collection(STUDENTS_COLLECTION).whereEqualTo("email",email)
            .limit(1)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (it.result.isEmpty) {
                        result.invoke(UiState.Error("User not found"))
                        auth.currentUser?.let { currentUser ->
                            currentUser.delete()
                            auth.signOut()
                        }
                    } else {
                        setStudent(it.result.toObjects(Students::class.java)[0])
                        result.invoke(UiState.Success(it.result.toObjects(Students::class.java)[0]))
                    }
                }
            }
            .addOnFailureListener {
                result.invoke(UiState.Error("${it.message}"))
            }
    }

    override fun logout(result: (UiState<String>) -> Unit) {
        result.invoke(UiState.Loading)
        auth.signOut()
        result.invoke(UiState.Success("Logged out successfully"))
    }


    companion object {
        const val STUDENTS_COLLECTION = "students"
    }
}