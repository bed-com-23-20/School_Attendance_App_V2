package com.example.school_attendance_register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun EnrollStudent(
    onEnroll: (String, String, String, String, String, String, String) -> Unit, // Callback for enrollment
    onBack: () -> Unit // Callback for navigating back
) {
    // State variables for inputs
    var fname by remember { mutableStateOf(TextFieldValue("")) }
    var sname by remember { mutableStateOf(TextFieldValue("")) }
    var guardianName by remember { mutableStateOf(TextFieldValue("")) } // Guardian Name state
    var guardianPhone by remember { mutableStateOf(TextFieldValue("")) } // Guardian Phone state
    var className by remember { mutableStateOf(TextFieldValue("")) }
    var dateOfBirth by remember { mutableStateOf(TextFieldValue("")) } // Date of Birth state
    var gender by remember { mutableStateOf("") } // State for gender selection

    //  a Column layout for input fields
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start // Align elements to the start
    ) {
        Text(text = "Enroll Student", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // student name Input
        TextField(
            value = fname,
            onValueChange = { fname = it },
            label = { Text("Student Firstname") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // student surname Input
        TextField(
            value = sname,
            onValueChange = { sname = it },
            label = { Text("Student Surname") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Guardian Name Input
        TextField(
            value = guardianName,
            onValueChange = { guardianName = it },
            label = { Text("Guardian Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Guardian Phone Number Input
        TextField(
            value = guardianPhone,
            onValueChange = { guardianPhone = it },
            label = { Text("Guardian Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Class Input
        TextField(
            value = className,
            onValueChange = { className = it },
            label = { Text("Class") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date of Birth Input
        TextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Date of Birth") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Gender", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        Row (
modifier = Modifier .fillMaxWidth()
    .padding( start = 90.dp)
            , horizontalArrangement = Arrangement.spacedBy(30.dp),

            verticalAlignment = Alignment.CenterVertically

        ){

            Text(text = "Male")

            Text(text = "Female")

            Text(text = "Other")


        }
        //Row for gender selection
Row(
    modifier = Modifier .fillMaxWidth()
        .padding( start = 80.dp)
    , horizontalArrangement = Arrangement.spacedBy(20.dp),

    verticalAlignment = Alignment.CenterVertically
) {
    // Radio Button for Male
    RadioButton(
        selected = gender == "Male",
        onClick = { gender = "Male" }
    )


    // Radio Button for Female
    RadioButton(
        selected = gender == "Female",
        onClick = { gender = "Female" }
    )



    // Radio Button for Other
    RadioButton(
        selected = gender == "Other",
        onClick = { gender = "Other" }
    )
}

        // Enroll Button
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,  // Set the button's container color to black
                contentColor = Color.White     // Set the text color to white for contrast
            ),

            onClick = {
                onEnroll(fname.text, sname.text, guardianName.text, guardianPhone.text, className.text, dateOfBirth.text, gender) // Call enrollment with input values
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Enroll")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Back Button
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,  // Set the button's container color to black
                contentColor = Color.White     // Set the text color to white for contrast
            ),
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),

        ) {
            Text(text = "Back")
        }
    }
}

// Preview function for the EnrollStudent composable
@Preview(showBackground = true)
@Composable
fun PreviewEnrollStudent() {
    EnrollStudent(
        onEnroll = { fname, sname, guardianName, guardianPhone, className, dateOfBirth, gender -> },
        onBack = {}
    )
}
