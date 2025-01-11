import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    val context = LocalContext.current
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val passwordVisibilityIcon = if (passwordVisibility) painterResource(R.drawable.visible) else painterResource(R.drawable.invisible)

    BackgroundAuth(modifier = modifier)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 64.dp, end = 64.dp)
        ){
            Text(
                text = "Get Started With",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null)},
                label = { Text(text = "email") },
                value = email,
                onValueChange = {
                    email = it
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
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
                    VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true
            )

            Spacer(Modifier.height(20.dp))

            FilledIconButton(
                onClick = {
                    // todo
                },
                shape = RoundedCornerShape(10),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "login button",
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = LocalContentColor.current,
                            shape = CircleShape
                        )
                )
            }

            Spacer(Modifier.height(40.dp))

            Text(
                text = "Forget password?",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        Toast
                            .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                            .show()
                    },
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Don't have account? ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            Toast
                                .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                                .show()
                        },
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
private fun LoginPrev() {
    Login(modifier = Modifier)
}