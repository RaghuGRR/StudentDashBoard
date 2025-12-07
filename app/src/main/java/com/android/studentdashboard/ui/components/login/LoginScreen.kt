package com.android.studentdashboard.ui.components.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.studentdashboard.ui.util.UiState
import com.android.studentdashboard.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier,onLoginSuccess: () -> Unit,loginViewModel: LoginViewModel = hiltViewModel()) {
    var studentId by rememberSaveable { mutableStateOf("") }
    var schoolId by rememberSaveable { mutableStateOf("") }

    var buttonText by rememberSaveable { mutableStateOf("Sign In") }
    val context = LocalContext.current

    val loginState by loginViewModel.loginState.collectAsStateWithLifecycle()

    LaunchedEffect(loginState) {
        when (loginState) {
            is UiState.Error -> {
                buttonText = "Sign In"
                showToast(context, (loginState as UiState.Error).message)
            }
            UiState.Idle -> {
                buttonText = "Sign In"
            }
            UiState.Loading -> {
                buttonText = "Signing you in..."
            }
            is UiState.Success<*> -> {
                buttonText = "Sign Out"
                onLoginSuccess()
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box(
            modifier = Modifier
                .weight(0.65f)
                .fillMaxWidth()
        ) {
            LoginScreenBackground()
        }


        CustomBottomCard(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth(),
            cornerRadius = 32.dp,
            backgroundColor = Color.White
        ) {

            Box(modifier = Modifier.fillMaxSize()){
                Column(
                    Modifier.align(Alignment.TopCenter),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Let's Get you Signed in",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    OutlinedTextField(
                        value = schoolId,
                        onValueChange = {
                            if(it.isNotBlank() && it.isAllLetters()){
                                schoolId = it
                            }
                        },
                        label = { Text("School ID") },
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color(0xFFD9DCE1),
                            focusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = studentId,
                        onValueChange = {
                            if(it.isNotBlank() && it.isAllLetters()){
                                studentId = it
                            }
                        },
                        label = { Text("Student ID") },
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color(0xFFD9DCE1),
                            focusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Text(
                    modifier = Modifier.align(Alignment.BottomCenter).clickable{
                        if(schoolId.isNotBlank()){
                            if(studentId.isNotBlank()) {
                                loginViewModel.login(schoolId,studentId)
                            }else{
                                showToast(context,"Please enter student ID")
                            }
                        }else{
                            showToast(context,"Please enter school ID")
                        }
                    },
                    text = buttonText,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

            }
        }
    }
}

fun String.isAllLetters(): Boolean{
    return this.all { it.isLetterOrDigit() }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}