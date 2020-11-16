package com.example.restic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.restic.ui.ResticTheme


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResticTheme {
                Scaffold(backgroundColor = Color.White) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                        alignment = Alignment.Center
                    ) {
                        Column(verticalArrangement = Arrangement.SpaceEvenly) {
                            Greeting()
                            Spacer(Modifier.preferredHeight(10.dp))
                            TelephoneTextField()
                            DividerWithText("or")
                            LoginTextField()
                            PasswordTextField()
                            Spacer(Modifier.preferredHeight(10.dp))
                            LoginButton()
                        }
                    }
                }
            }
        }
    }

    companion object {
        val login: MutableState<String> = mutableStateOf("")
    }
}

@Composable
fun DividerWithText(textBetween: String = "") {
    Row(modifier = Modifier.padding(16.dp)) {
        Divider(
            color = Color.Blue, thickness = 2.dp, modifier = Modifier.weight(
                2F
            )
        )
        Text(
            text = textBetween, modifier = Modifier.padding(start = 2.dp, end = 2.dp).weight(1F),
            color = Color.Blue, fontSize = 12.sp, textAlign = TextAlign.Center
        )
        Divider(color = Color.Blue, thickness = 2.dp, modifier = Modifier.weight(2F))
    }
}

@Composable
fun LoginButton() {
    val context = ContextAmbient.current
    Button(onClick = { /* Do something! */
        when (MainActivity.login.value) {
            "user" ->
                startActivity(context, Intent(context, UserComponent()::class.java), null)
            "waiter" ->
                startActivity(context, Intent(context, WaiterComponent()::class.java), null)
            "admin" ->
                startActivity(context, Intent(context, AdminComponent()::class.java), null)
    }
}, modifier = Modifier.fillMaxWidth()) {
    Text("Войти")
}
}

@Composable
fun TelephoneTextField() {
    //var telephone by savedInstanceState { "" }
    var telephone by remember { mutableStateOf(TextFieldValue()) }
    OutlinedTextField(
        value = telephone,
        onValueChange = {
            telephone = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Введите телефон") },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        onImeActionPerformed = { action, softwareController ->
            if (action == ImeAction.Done) {
                softwareController?.hideSoftwareKeyboard()
            }
        }
    )
}

@Composable
fun LoginTextField() {
    //var login by savedInstanceState { "" }
    var loginLocal by remember { mutableStateOf(TextFieldValue()) }
    OutlinedTextField(
        value = loginLocal,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
            loginLocal = it
            MainActivity.login.value = loginLocal.text.trim()
        },
        label = { Text("Введите логин") },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        onImeActionPerformed = { action, softwareController ->
            if (action == ImeAction.Done) {
                softwareController?.hideSoftwareKeyboard()
            }
        }
    )
}

@Composable
fun PasswordTextField() {
    var password by savedInstanceState { "" }
    OutlinedTextField(
        value = password,
        onValueChange = { it -> password = it },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Введите пароль") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        onImeActionPerformed = { action, softwareController ->
            if (action == ImeAction.Done) {
                softwareController?.hideSoftwareKeyboard()
            }
        },
        maxLines = 1
    )
}

@Composable
fun Greeting() {
    Text(
        text = "Привет, человек!",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth()
    )
}