package com.example.skillforge.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skillforge.navbar.BottomNavBar
import com.example.skillforge.ui.theme.CardBackground
import com.example.skillforge.ui.theme.GradientEnd
import com.example.skillforge.ui.theme.IconGreen
import com.example.skillforge.ui.theme.Purple40
import com.example.skillforge.ui.theme.TextPrimary
import com.example.skillforge.ui.theme.TextSecondary

@Composable
fun ProgressScreen(navController: NavController) {
    var progress by remember { mutableFloatStateOf(0.58f) }
    val progressPercentage = (progress * 100).toInt()
    Scaffold(
        bottomBar = {
            BottomNavBar(navController,"Progress")
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
                ProgressHeaderSection(progressPercentage,progress)
            }
            item {
                StatisticsCard()
            }
            item {
                RecentActivitySection()
            }

        }
    }
}
@Composable
fun ProgressHeaderSection(progressPercentage: Int,progress: Float){

    Column {
        Text(text = "My Progress", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //progressBar
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(120.dp)
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.matchParentSize(),
                    strokeWidth = 7.dp,
                    color = Color.Blue,
                    strokeCap = StrokeCap.Round
                )
                Text(
                    text = "$progressPercentage%",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
            }
            Column{
                Text(text = "Overall Progress", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(text = "7 of 12 courses", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Spacer(Modifier.height(15.dp))
                Text(text = "Keep going you are doing great", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            }
        }
    }
}
@Composable
fun StatisticsCard() {
    Text(
        text = "Explore Skills",
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary
    )
    Spacer(Modifier.height(50.dp))
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        //Total Courses
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
                .background(color = Color.Gray.copy(0.15f), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Text(text = "12", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Purple40.copy(alpha = 0.7f))
                Spacer(Modifier.height(10.dp))
                Text(text = "Total Courses", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
            }
        }
        //Completed
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
                .background(color = Color.Gray.copy(0.15f), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(text = "7", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = IconGreen.copy(alpha = 0.7f))
                Spacer(Modifier.height(10.dp))
                Text(text = "Completed", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
            }
        }
    }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //Learning Time
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
                    .background(color = Color.Gray.copy(0.15f), shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Text(
                        text = "36h",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = GradientEnd.copy(alpha = 0.8f)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Learning Time",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }
            }
            //Day streak
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
                    .background(color = Color.Gray.copy(0.15f), shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Text(
                        text = "5",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color =IconGreen.copy(alpha = 0.7f)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Day Streak",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun RecentActivitySection() {
    Column (verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "Recent Activity",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
    //card 1
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
                            text = "Android Development",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Beginner  •  8h",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                }

            }
        }
    }
}
