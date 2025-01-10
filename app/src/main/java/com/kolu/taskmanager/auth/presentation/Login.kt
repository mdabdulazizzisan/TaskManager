import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kolu.taskmanager.R

@Composable
fun Login(
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val passwordVisibilityIcon = if (passwordVisibility) painterResource(R.drawable.visible) else painterResource(R.drawable.invisible)
    BackgroundAuth(modifier = modifier)
    Column(
        modifier = Modifier
            .padding(top = 186.dp, start = 64.dp)
    ) {
        Text(
            text = "Get Started With",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            leadingIcon = {Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null)},
            label = { Text(text = "email") },
            value = email,
            onValueChange = {
                email = it
            },
        )
        OutlinedTextField(
            label = { Text(text = "password") },
            value = password,
            onValueChange = {
                password = it
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = passwordVisibilityIcon,
                        contentDescription = null)
                }
            },
            visualTransformation = if(passwordVisibility)
                VisualTransformation.None else PasswordVisualTransformation()
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun LoginPrev() {
    Login(modifier = Modifier)
}