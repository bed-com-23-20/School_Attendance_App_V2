package com.example.school_attendance_register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.school_attendance_register.data_classes.StudentInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllSTD(navController: NavController) {

//    Scaffold(
//        topBar = {
//            TopAppBarWithBack(navController = navController, title = "ALL STUDENTS")
//        },
//
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    navController.navigate("Student_Enroll")
//                },
//                containerColor = MaterialTheme.colorScheme.secondary,
//                contentColor = Color.White
//            ) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Student")
//
//            }
//        },
//        floatingActionButtonPosition = FabPosition.End
//
//    ) { innerPadding ->
    // Firebase database reference
    val database = FirebaseDatabase.getInstance()
    val myRefStudent = database.getReference("Students")
    val context = LocalContext.current

    // State variables
    var students by remember { mutableStateOf<List<StudentInfo>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                navController = navController, title = "ALL STUDENT",
                backIconColor = Color.White
            )
        }
    ) { paddingValues ->

        // Fetch data from Firebase
        LaunchedEffect(Unit) {
            myRefStudent.get()
                .addOnSuccessListener { snapshot ->
                    if (snapshot.exists()) {
                        val fetchedStudents = snapshot.children.mapNotNull { it.toStudent() }
                        students = fetchedStudents
                    } else {
                        Toast.makeText(context, "No Students found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        "Failed to fetch students: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        // UI Layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search Students") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Scrollable List of Cards
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                val filteredStudents = students.filter {
                    it.fname.contains(searchQuery, ignoreCase = true) ||
                            it.sname.contains(searchQuery, ignoreCase = true)
                }

                items(filteredStudents) { student ->
                    StudentCard(student, navController)
                }
            }
        }

    }
    }

    @Composable
    fun StudentCard(student: StudentInfo, navController: NavController) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { navController.navigate("viewStudent/${student.uniqueId}") },
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Full Name: ${student.fname} ${student.sname}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 20.sp
                )
                Text(
                    "Student Code: ${student.uniqueId}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp
                )
                Text(
                    text = "Guardian Name: ${student.guardianName}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )
                Text(
                    text = "Guardian Contact: ${student.guardianPhone}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp
                )
                Text(
                    "Gender: ${student.gender}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp
                )
                Text(
                    "Date of Birth: ${student.dateOfBirth}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 16.sp
                )

            }
        }
    }


    // Extension function to parse a student from a Firebase snapshot
    fun DataSnapshot.toStudent(): StudentInfo? {
        return try {
            StudentInfo(
                fname = child("fname").value?.toString().orEmpty(),
                sname = child("sname").value?.toString().orEmpty(),
                uniqueId = child("uniqueId").value?.toString().orEmpty(),
                guardianName = child("guardianName").value?.toString().orEmpty(),
                guardianPhone = child("guardianPhone").value?.toString().orEmpty(),
                classform = child("classform").value?.toString().orEmpty(),
                dateOfBirth = child("dateOfBirth").value?.toString().orEmpty(),
                gender = child("gender").value?.toString().orEmpty()
            )
        } catch (e: Exception) {
            null // Return null if parsing fails
        }
    }









//    val database = FirebaseDatabase.getInstance()
//    val myRefStudent = database.getReference("Students") //.child(encodeEmail).child("Students")
//    val context = LocalContext.current
//
//    var result by remember { mutableStateOf("") }
//    var check by remember { mutableStateOf<Boolean>(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//            //.padding(paddingValues),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        val data = StringBuffer()
//        myRefStudent.get().addOnSuccessListener { it1 ->
//            if (it1.exists()) {
//                it1.children.forEach {
//                    data.append("\nFirst Name = " + it.child("fname").value)
//                    data.append("\nSurname  = " + it.child("sname").value)
//                    data.append("\nUnique Code  = " + it.child("uniqueId").value)
//                    data.append("\nGuardian Name = " + it.child("guardianName").value)
//                    data.append("\nGuardian Contact = " + it.child("guardianPhone").value)
//                    data.append("\nClass = " + it.child("classform").value)
//                    data.append("\nDate of Birth = " + it.child("dateOfBirth").value)
//                    data.append("\nGender = " + it.child("gender").value)
//                    data.append("\n------------------------------------------------------------------------")
//                }
//                check = true
//                result = data.toString()
//
//                // navController.navigate("allStudents/$result")
//            }
//
//        }.addOnFailureListener {
//            Toast.makeText(
//                context,
//                "No Students found",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//
//    }
//    AnimatedVisibility(visible = check, Modifier.fillMaxWidth())
//            {
//                Text(text = result, fontSize = 15.sp, color = Color.Black)
//            }
































//                var studentList by remember { mutableStateOf(listOf<StudentInfo>()) }
//                var searchQuery by remember { mutableStateOf("") }
//                var filteredStudentList by remember { mutableStateOf(listOf<StudentInfo>()) }
//
//                // Fetch staff data from Firebase
//                LaunchedEffect(Unit) {
//                    val database = FirebaseDatabase.getInstance()
//                    val myRefStudent = database.getReference("Students")
//
//                    myRefStudent.addValueEventListener(object : ValueEventListener {
//                        override fun onDataChange(snapshot: DataSnapshot) {
//                            val studentData = mutableListOf<StudentInfo>()
//                            snapshot.children.forEach { child ->
//                                val student = child.getValue(StudentInfo::class.java)
//                                if (student != null) {
//                                    studentData.add(student)
//                                }
//                            }
//                            studentList = studentData
//                            filteredStudentList = studentData
//                        }
//
//                        override fun onCancelled(error: DatabaseError) {
//                            Log.e("FirebaseError", "Failed to fetch staff: ${error.message}")
//                        }
//                    })
//                }
//
//                Scaffold(
//                    topBar = {
//                        TopAppBar(
//                            title = {
//                                Text(
//                                    "STAFFS",
//                                    fontSize = 24.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.Black
//                                )
//                            },
//                            navigationIcon = {
//                                IconButton(onClick = { navController.popBackStack() }) {
//                                    Icon(
//                                        Icons.Default.ArrowBack,
//                                        contentDescription = "Back",
//                                        tint = Color.Black
//                                    )
//                                }
//                            }
//                        )
//                    },
//                    floatingActionButton = {
//
//                        FloatingActionButton(
//                            onClick = {
//                                navController.navigate("Register_Staff") // Navigate to Register Staff screen
//                            },
//                            containerColor = MaterialTheme.colorScheme.secondary,
//                            contentColor = Color.White
//                        ) {
//                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Staff")
//                        }
//                    },
//                    floatingActionButtonPosition = FabPosition.End
//                ) { paddingValues ->
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(paddingValues)
//                            .padding(16.dp)
//                    ) {
//                        // Search Field
//                        OutlinedTextField(
//                            value = searchQuery,
//                            onValueChange = { query ->
//                                searchQuery = query
//                                filteredStudentList = if (query.isBlank()) {
//                                    studentList
//                                } else {
//                                    studentList.filter {
//                                        it.fname.contains(query, ignoreCase = true)
//                                    }
//                                }
//                            },
//                            label = { Text("Search Staff by Name") },
//                            modifier = Modifier.fillMaxWidth(),
//                            singleLine = true
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        // Staff Count
//                        Text(
//                            text = "Total Staffs: ${filteredStudentList.size}",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            modifier = Modifier.padding(bottom = 16.dp)
//                        )
//
//                        // Scrollable Content
//                        LazyColumn(
//                            modifier = Modifier
//                                .weight(1f) // Ensure it takes up remaining space
//                                .fillMaxWidth(),
//                            verticalArrangement = Arrangement.spacedBy(8.dp),
//                            contentPadding = PaddingValues(bottom = 16.dp)
//                        ) {
//                            items(filteredStudentList) { student ->
//                                StudentCard(student)
//                            }
//                        }
//                    }
//                }
//            }
//
//    @Composable
//    fun StudentCard(student: StudentInfo) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable {
//                    Log.d("StaffClick", "Clicked on ${student.fname}")
//                },
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
//            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    text = "Fist Name: ${student.fname }",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold
//                )
//
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "Surname: ${student.sname}",
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.primary
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "ID: ${student.uniqueId}",
//                    fontSize = 16.sp,
//                    color = Color.Gray
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "Class: ${student.classform}",
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.secondary
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "Parent Name: ${student.guardianName}",
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.secondary
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "Parent Number: ${student.guardianPhone}",
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.secondary
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "Date of Birth: ${student.dateOfBirth}",
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.secondary
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "Gender: ${student.gender}",
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.secondary
//                )
//            }
//        }
//    }
//    val students = remember { mutableStateOf<List<StudentInfo>>(emptyList()) }
//
//    // Fetch data from Firebase
//    LaunchedEffect(Unit) {
//        fetchAllStudents { fetchedStudents ->
//            students.value = fetchedStudents
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBarWithBack(navController = navController, title = "All Students")
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//        ) {
//            if (students.value.isEmpty()) {
//                // Show a loading or empty message
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text("No students found.")
//                }
//            } else {
//                // Show list of students
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    items(students.value) { student ->
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight(),
//                            elevation = CardDefaults.cardElevation(8.dp),
//                            shape = MaterialTheme.shapes.medium,
//                            colors = CardDefaults.cardColors(
//                                containerColor = MaterialTheme.colorScheme.surface
//                            )
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp),
//                                verticalArrangement = Arrangement.spacedBy(8.dp)
//                            ) {
//                                Text(
//                                    text = "${student.fname} ${student.sname}",
//                                    style = MaterialTheme.typography.titleMedium,
//                                    color = MaterialTheme.colorScheme.onSurface
//                                )
//                                Text("Unique Code: ${student.uniqueId}")
//                                Text("Guardian Name: ${student.guardianName}")
//                                Text("Guardian Contact: ${student.guardianPhone}")
//                                Text("Gender: ${student.gender}")
//                                Text("Date of Birth: ${student.dateOfBirth}")
//                                Text("Class: ${student.classform}")
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

// Function to fetch all students from Firebase
//fun fetchAllStudents(onResult: (List<StudentInfo>) -> Unit) {
//    val database = Firebase.database
//    val studentRef = database.getReference("Students")
//
//    studentRef.get().addOnCompleteListener { task ->
//        if (task.isSuccessful) {
//            val snapshot = task.result
//            val studentList = mutableListOf<StudentInfo>()
//            snapshot?.children?.forEach { studentSnapshot ->
//                val student = studentSnapshot.getValue(StudentInfo::class.java)
//                if (student != null) {
//                    studentList.add(student)
//                }
//            }
//            onResult(studentList)
//        } else {
//            Log.e("FirebaseError", "Error fetching students", task.exception)
//        }
//    }
//}
