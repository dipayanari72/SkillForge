package com.example.skillforge.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillforge.viewmodels.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), onLoginSuccess: () -> Unit,onRegisterClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    //context
    val context = LocalContext.current
    LaunchedEffect(
        uiState.errorMessage,
        uiState.loginSuccess

    ) {
        if (uiState.loginSuccess){

            Toast.makeText(
                context,
                "Login Successful!",
                Toast.LENGTH_SHORT
            ).show()

            onLoginSuccess()
        }else if(uiState.errorMessage != null){
            Toast.makeText(
                context,
                uiState.errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(Modifier.height(30.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(10.dp))
                    Text(text = "Welcome back to the app", fontSize = 18.sp)
                }
                Spacer(Modifier.height(30.dp))
            }

            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(
                            text = "Email Address",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(Modifier.height(10.dp))
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)),
                            value = uiState.email,
                            onValueChange = { viewModel.onEmailChange(it)},
                            placeholder = {
                                Text(text = "Hello@example.com", fontSize = 16.sp)
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            )
                        )
                    }
                    Spacer(Modifier.height(30.dp))
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Password",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Forgot Password ?",
                                fontSize = 17.sp,
                                color = Color.Blue.copy(0.8f),
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Spacer(Modifier.height(10.dp))
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)),
                            value = uiState.password,
                            onValueChange = { viewModel.onPasswordChange(it) },
                            placeholder = {
                                Text(text = "* * * * * * *", fontSize = 20.sp)
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = { passwordVisible = !passwordVisible }
                                ) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = null
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            )
                        )
                    }
                }
                Spacer(Modifier.height(30.dp))
            }

            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Login button
                    Button(
                        onClick = {
                               viewModel.login()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = colorResource(com.example.skillforge.R.color.welcomeBtn)
                        )
                    ) {
                        Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                    Spacer(Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(thickness = 2.dp, modifier = Modifier.weight(1f))
                        Spacer(Modifier.width(7.dp))
                        Text(text = "or sign in with", fontSize = 14.sp)
                        Spacer(Modifier.width(7.dp))
                        HorizontalDivider(thickness = 2.dp, modifier = Modifier.weight(1f))
                    }

                    Spacer(Modifier.height(20.dp))

                    // Google button
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.Gray.copy(0.4f)
                            )
                        ) {
                            Text(text = "Continue with Google", fontSize = 17.sp)
                        }
                        Spacer(Modifier.height(20.dp))
                        Text(
                            modifier = Modifier.clickable{
                                onRegisterClick()
                            },
                            text = "Create an account ",
                            fontSize = 18.sp,
                            color = Color.Blue.copy(0.8f),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(Modifier.height(30.dp))
            }
        }
    }
}