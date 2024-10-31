import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exo.daily_spikeur.R

@Composable
fun CreatePoopAccountScreen() {
    val customFont = FontFamily(Font(R.font.test))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE1DB))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "CREATE YOUR POOP ACCOUNT",
            fontFamily = customFont,
            fontSize = 24.sp,
            color = Color(0xFF916953)
        )

        Spacer(modifier = Modifier.height(32.dp))

        var email by remember { mutableStateOf("prenom.nom@gmail.com") }
        var password by remember { mutableStateOf("*************") }
        var nickname by remember { mutableStateOf("CLEMZOUZ") }

        PoopTextField(label = "POOP's EMAIL", value = email, onValueChange = { email = it })
        Spacer(modifier = Modifier.height(16.dp))

        PoopTextField(label = "POOP's PASSWORD", value = password, isPassword = true, onValueChange = { password = it })
        Spacer(modifier = Modifier.height(16.dp))

        PoopTextField(label = "POOP's NICKNAME", value = nickname, onValueChange = { nickname = it })
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* TODO: Add validation logic here */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF432818)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "VALIDATE YOUR POOPFILE",
                fontFamily = customFont,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoopTextField(label: String, value: String, isPassword: Boolean = false, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD3CBC6), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF432818),
            fontFamily = FontFamily(Font(R.font.test))
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (isPassword) {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )
        } else {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )
        }
    }
}
