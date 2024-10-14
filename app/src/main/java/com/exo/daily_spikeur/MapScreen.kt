import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.exo.daily_spikeur.R
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

@Composable
fun MapScreen() {
    val lille = LatLng(50.633333, 3.066667)  // Coordonnées d'un lieu (ici Singapour)

    // Configuration de la caméra
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lille, 15f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            MapMarker(
                position = lille,
                title = "Gambier",
                context = LocalContext.current,
                iconResourceId = R.drawable.honor_gambier
            )
        }
        // Texte centré sur la carte
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card (
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(237, 225, 225),
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
            ) {
                Text(
                    text = "FIND YOUR POOP",
                    fontFamily = FontFamily(Font(R.font.test)),
                    color = Color(0xFF8B4513),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    title: String,
    @DrawableRes iconResourceId: Int
) {
    val icon = bitmapDescriptorFromVector(
        context, iconResourceId
    )

    Marker(
        state = MarkerState(position = position),
        title = title,
        icon = icon,
    )
}

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int,
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    val width = 100
    val height = 100
    drawable.setBounds(0, 0, width, height)
    val bm = Bitmap.createBitmap(
        100,
        100,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}