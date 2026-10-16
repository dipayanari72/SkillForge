
package com.example.skillforge.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillforge.ui.theme.ButtonRed
import com.example.skillforge.ui.theme.PrimaryBlue
import com.example.skillforge.viewmodels.CourseViewmodel
import com.example.skillforge.viewmodels.EnrollmentViewmodel


@Composable
fun CourseDetailScreen(
    courseId: Int,
    onBackClick: () -> Unit = {},
){
    val context = LocalContext.current
    val viewModel: CourseViewmodel = viewModel()
    val enrollmentViewmodel : EnrollmentViewmodel = viewModel()
    val isEnrolled by enrollmentViewmodel.isEnrolled(courseId).collectAsState(initial = false)
    val courses by viewModel.courses.collectAsState()
    val course = courses.find { it.id == courseId }
    var selectedTab by remember { mutableStateOf("Overview") }
    var showDisenrollDialog by remember {
        mutableStateOf(false)
    }

    var showSwitchDialog by remember {
        mutableStateOf(false)
    }
    var isCheckingEnrollment by remember {
        mutableStateOf(false)
    }

    if (course == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Loading course details...")
        }
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Back button
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Go back",
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Course header
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Android,
                    contentDescription = "Icon",
                    tint = Color.Green,
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = course.instructor,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("⭐ 0.0", fontWeight = FontWeight.Bold)
                        Text(
                            text = " (0 reviews)",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = course.level,
                color = Color.Gray,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                enabled = !isCheckingEnrollment,
                onClick = {
                    if (isEnrolled) {
                        // Ask before disenrolling
                        showDisenrollDialog = true
                    } else {
                        isCheckingEnrollment = true

                        enrollmentViewmodel.checkEnrollMent(
                            courses = course,

                            onAlreadyEnrolled = {
                                isCheckingEnrollment = false
                                showSwitchDialog = true
                            },

                            onFirstEnrollment = {
                                enrollmentViewmodel.enroll(course)
                                isCheckingEnrollment = false

                                Toast.makeText(
                                    context,
                                    "Enrolled successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isEnrolled) {
                        ButtonRed
                    } else {
                        PrimaryBlue
                    }
                )
            ) {
                Text(
                    text = if (isEnrolled) {
                        "Disenroll"
                    } else {
                        "Start Learning"
                    },
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Tabs
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("Overview", "Courses", "Reviews").forEach { tab ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedTab = tab }
                            .padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = tab,
                            color = if (selectedTab == tab) {
                                PrimaryBlue
                            } else {
                                Color.Gray
                            },
                            fontWeight = if (selectedTab == tab) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            },
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(3.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    if (selectedTab == tab) PrimaryBlue
                                    else Color.Transparent
                                )
                        )
                    }
                }
            }

           // HorizontalDivider(color = Color(0xFFE8EDF4))

            Spacer(modifier = Modifier.height(22.dp))

            // Selected tab content
            when (selectedTab) {
                "Overview" -> {
                    Text(
                        text = "About This Skill",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = course.description,
                        fontSize = 14.sp,
                        lineHeight = 23.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(28.dp))



                    Spacer(modifier = Modifier.height(16.dp))

                }

                "Courses" -> {
                    Text(
                        text = "Available Courses",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Show your courses list here using " +
                                "your Retrofit ViewModel.",
                        color = Color.Gray
                    )
                }

                "Reviews" -> {
                    Text(
                        text = "Student Reviews",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "⭐ 4.8 / 5.0",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "Based on 1.2k reviews",
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
    if (showDisenrollDialog)
    {
        AlertDialog(
            onDismissRequest = {
                showDisenrollDialog = false
            },
            title = {
                Text("Disenroll from course?")
            },
            text = {
                Text(
                    "Are you sure you want to disenroll " +
                            "from ${course.title}?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        enrollmentViewmodel.disEnroll(courseId)
                        showDisenrollDialog = false

                        Toast.makeText(
                            context,
                            "Disenrolled successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDisenrollDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showSwitchDialog) {
        AlertDialog(
            onDismissRequest = {
                showSwitchDialog = false
            },
            title = {
                Text(
                    text = "Enrollment Limit"
                )
            },
            text = {
                Text(
                    text = "You can enroll in only one course at a time. " +
                            "Please disenroll from your current course " +
                            "before enrolling in ${course.title}."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSwitchDialog = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }


}
