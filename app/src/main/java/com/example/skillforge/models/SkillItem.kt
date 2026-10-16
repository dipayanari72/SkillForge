package com.example.skillforge.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class SkillItem (
    val name: String,
    val icon: ImageVector,
    val tint: Color
)