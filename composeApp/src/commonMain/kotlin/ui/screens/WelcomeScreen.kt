package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coffeeapp.composeapp.generated.resources.Res
import coffeeapp.composeapp.generated.resources.baseline_supervised_user_circle_24
import coffeeapp.composeapp.generated.resources.coffee_app
import org.jetbrains.compose.resources.painterResource
import ui.GetPoppinsFamily
import ui.ListScreen
import ui.MainViewModel
import ui.primaryColor
import ui.primaryColorAlpha

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navController: NavController
) {
    val scrollState = rememberScrollState(Int.MAX_VALUE)
    Column(
        modifier = modifier.fillMaxSize().padding(bottom = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.coffee_app),
            contentDescription = "Coffee App",
            modifier = Modifier.fillMaxWidth().heightIn(max = 1000.dp).fillMaxHeight(0.6f).clip(
                RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp)
            ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        var name by rememberSaveable {
            mutableStateOf("")
        }
        var isTextFieldError by rememberSaveable {
            mutableStateOf(false)
        }
        TextField(
            shape = RoundedCornerShape(16.dp),
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            label = { Text("Enter Your Name", fontFamily = GetPoppinsFamily()  , fontWeight = FontWeight.Bold)},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = primaryColorAlpha,
                focusedContainerColor = primaryColorAlpha,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = primaryColor,
            ),
            isError = isTextFieldError,
            trailingIcon = {
                Icon(painter = painterResource(Res.drawable.baseline_supervised_user_circle_24), contentDescription = "", tint = primaryColor)
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = {
                if (name.isBlank()) {
                    isTextFieldError = true
                } else {
                    isTextFieldError = false
                    viewModel.onGetStartedClicked(name)
                    navController.navigate(ListScreen)
                }
            },
            modifier = Modifier.fillMaxWidth().height(55.dp).padding(horizontal = 32.dp)
        ) {
            Text(text = "Get Started" , fontFamily = GetPoppinsFamily() , fontWeight = FontWeight.Bold, fontSize = 17.sp)
        }
    }
}