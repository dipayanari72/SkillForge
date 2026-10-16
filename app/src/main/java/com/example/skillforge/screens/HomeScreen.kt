package com.example.skillforge.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.skillforge.models.Courses
import com.example.skillforge.models.EnrolledCourse
import com.example.skillforge.models.SkillItem
import com.example.skillforge.navbar.BottomNavBar
import com.example.skillforge.navbar.NavRoutes
import com.example.skillforge.ui.theme.CardBackground
import com.example.skillforge.ui.theme.GradientEnd
import com.example.skillforge.ui.theme.GradientStart
import com.example.skillforge.ui.theme.IconBlue
import com.example.skillforge.ui.theme.IconGreen
import com.example.skillforge.ui.theme.IconOrange
import com.example.skillforge.ui.theme.IconPurple
import com.example.skillforge.ui.theme.PrimaryBlue
import com.example.skillforge.ui.theme.TextPrimary
import com.example.skillforge.ui.theme.TextSecondary
import com.example.skillforge.viewmodels.CourseViewmodel
import com.example.skillforge.viewmodels.EnrollmentViewmodel
import com.example.skillforge.viewmodels.UserViewmodel

@Composable
fun HomeScreen(navController: NavController,viewModel : CourseViewmodel = viewModel()) {
    val userViewModel: UserViewmodel = viewModel()
    val userName by userViewModel.userName.collectAsState()
    val enrollmentViewmodel : EnrollmentViewmodel = viewModel()
    val enrolledCourse by enrollmentViewmodel.currentCourse.collectAsState()
    val courses = viewModel.courses.collectAsState()
    val searchText by remember { mutableStateOf("") }
    var currentProgress by remember { mutableFloatStateOf(0.7f) }
    var isEnrolled by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        enrollmentViewmodel.getEnrollMentCourseDetails()
    }
   isEnrolled = if (enrolledCourse == null) {
       false
   } else {
       true
   }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoutes.AiChat)
                }
            ) {
                Text("AI")
            }
        },
      bottomBar = {
          BottomNavBar(navController ,"Home")
      }
    ) { innerpadding ->
        LazyColumn (
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                HeaderSection(userName)
            }
            item {
                SearchBar(searchText)
            }
            item {
                ContinueLearningCard(currentProgress,enrolledCourse,isEnrolled)
            }
            item {
                ExploreSkillsSection()
            }
            item {
                PopularCoursesSection(courses.value)
            }
        }
    }
}
@Composable
fun HeaderSection(userName: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column{
            Text(text = "Hi $userName \uD83D\uDC4B", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text(
                text = "What do you learn today?",
                color = Color.Gray,
                fontSize = 15.sp,
            )
        }
        IconButton (
            onClick = { },
            modifier = Modifier
                .size(40.dp)
                .background(Color.White, CircleShape)
                .border(1.dp, Color(0xFFE2E8F0), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications",
            )
        }
    }
}
@Composable
fun SearchBar(searchText : String){
    OutlinedTextField(
        value = searchText,
        onValueChange = {},
        placeholder = { Text(text = "Search skills, courses...", color = Color(0xFF94A3B8), fontSize = 14.sp) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF64748B)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedBorderColor = Color(0xFFE2E8F0),
            focusedBorderColor = Color(0xFF3B82F6)
        ),
        singleLine = true
    )
}
@Composable
fun ContinueLearningCard(currentProgress: Float,currentCourse : EnrolledCourse?,isEnrolled: Boolean) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(GradientStart, GradientEnd)
                    )
                )
                .padding(18.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Continue Learning",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(IconGreen, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Android,
                            contentDescription = "Kotlin",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if(isEnrolled) currentCourse!!.title else "Not Enrolled ",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                           LinearProgressIndicator(
                               progress = { currentProgress },
                               modifier = Modifier
                                   .weight(1f)
                                   .height(6.dp)
                                   .clip(RoundedCornerShape(3.dp)),
                               trackColor = Color.White.copy(alpha = 0.3f),
                               color = Color.White
                           )
                            Text(
                                text = "70%",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(24.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 6.dp),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Continue", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun ExploreSkillsSection() {
    val skills = listOf(
        SkillItem("Android", Icons.Default.Android, IconGreen),
        SkillItem("Kotlin", Icons.Default.AutoAwesome, IconPurple),
        SkillItem("Java", Icons.Default.Code, IconOrange),
        SkillItem("Python", Icons.Default.Terminal, IconBlue),
        SkillItem("AI & ML", Icons.Default.Psychology, IconBlue),
        SkillItem("Web Dev", Icons.Default.Language, IconBlue)
    )

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Explore Skills",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        // Fixed height for 2 grid rows
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(200.dp),
            userScrollEnabled = false
        ) {
            items(skills) { skill ->
                SkillCard(skill)
            }
        }
    }
}

@Composable
fun SkillCard(skill: SkillItem) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(skill.tint.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = skill.icon,
                    contentDescription = skill.name,
                    tint = skill.tint,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = skill.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            )
        }
    }
}

@Composable
fun PopularCoursesSection(courses: List<Courses>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular Courses",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            TextButton(onClick = { }) {
                Text("View All", color = PrimaryBlue, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    courses.take(4).forEach { courses ->
        CourseCard(courses)
    }

    }
}
@Composable
fun CourseCard(courses: Courses) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(IconGreen, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Android,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Column {
                    Text(
                        text = if(courses.title.length > 20){
                            courses.title.take(20) + "..."
                        }else{
                            courses.title
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text =  courses.level + "•" +courses.duration ,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(32.dp)
                    .background(PrimaryBlue, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}