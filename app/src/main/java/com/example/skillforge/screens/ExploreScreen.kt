package com.example.skillforge.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.skillforge.models.Courses
import com.example.skillforge.navbar.BottomNavBar
import com.example.skillforge.navbar.NavRoutes
import com.example.skillforge.ui.theme.CardBackground
import com.example.skillforge.ui.theme.IconGreen
import com.example.skillforge.ui.theme.PrimaryBlue
import com.example.skillforge.ui.theme.TextPrimary
import com.example.skillforge.ui.theme.TextSecondary
import com.example.skillforge.viewmodels.CourseViewmodel


@Composable
fun ExploreScreen(navController: NavController,viewModel: CourseViewmodel = viewModel()) {
    val categories = listOf("All","Programming","Design","Cloud")
    var selectedCategory by remember { mutableStateOf("All") }
    var exploreSearchText by remember { mutableStateOf("") }
    val courses = viewModel.courses.collectAsState()
    Scaffold(
        bottomBar = {
            BottomNavBar(navController,"Explore")
        }
    ){ innerpadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerpadding)
                .padding(vertical = 10.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                ExploreHeader()
            }
            item {
                ExploreSearchBar(searchText = exploreSearchText, onSearchTextChange = {exploreSearchText = it})
            }
            item { FilterOptions(category = categories,selectedCategory = selectedCategory, onCategorySelected = {selectedCategory = it}) }
            item {
                CourseSection(courses.value, navController = navController)
            }
        }

    }
}
@Composable
fun ExploreHeader(){
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
            Text(text = "Explore Skills", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun ExploreSearchBar(searchText : String,onSearchTextChange:(String)-> Unit){
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        placeholder = { Text(text = "Search for a skill...", color = Color(0xFF94A3B8), fontSize = 14.sp) },
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
fun FilterOptions(category : List<String>,selectedCategory : String,onCategorySelected:(String) -> Unit){
    LazyRow() {
        items(category){ item ->
            val isSelected = item == selectedCategory
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(if (isSelected) Color.Blue else Color.Transparent)
                    .clickable { onCategorySelected(item) }
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(text = item, color = if (isSelected) Color.White else Color.Black)
            }

        }
    }
}

@Composable
fun CourseSection(courses : List<Courses>,navController: NavController){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        courses.forEach{ item ->
            ExploreCourseCard(item, courseId = item.id, navController = navController)
        }
    }
}
@Composable
fun ExploreCourseCard(courses: Courses,courseId : Int,navController: NavController) {
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
                onClick = {
                    navController.navigate(
                        NavRoutes.CourseDetails(courseId = courseId )
                    )
                },
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