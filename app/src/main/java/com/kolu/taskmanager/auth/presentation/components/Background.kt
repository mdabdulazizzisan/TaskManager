import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BackgroundAuth(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ){
//        Image(
//            painter = painterResource(id = R.drawable.auth_top_left),
//            contentDescription = "",
//            modifier = Modifier
//        )
        Image(
            imageVector = BackgroundTopLeft,
            contentDescription = null
        )
        Image(
            imageVector = BackgroundBottomRight,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BackgroundPrev() {
    BackgroundAuth(modifier = Modifier)
}