package com.nokhba.composeroommvvmflowm3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.nokhba.composeroommvvmflowm3.model.User
import com.nokhba.composeroommvvmflowm3.ui.theme.ComposeRoomMvvmFlowM3Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRoomMvvmFlowM3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    From(this)
                }
            }
        }
    }
}

@Composable
fun From(ctx: MainActivity) {

    val context = LocalContext.current

    val viewModel = ViewModelProvider(ctx)[MainViewModel::class.java]

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Full Name") },
            value = name,
            onValueChange = {
                name = it
            })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Age") },
            value = age,
            onValueChange = {
                age = it
            })

        FilledTonalButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                Toast.makeText(context, "name : $name \n age : $age", Toast.LENGTH_LONG).show()
                viewModel.addUSer(User(fullName = name, age = age.toInt()))
            }
        ) {
            Text(text = "Add User")
        }


        LazyColumn {
            viewModel.users.observe(ctx) {
                itemsIndexed(it) { index, item ->
                    Text(text = item.fullName)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRoomMvvmFlowM3Theme {
//        From()
    }
}