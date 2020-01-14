package resources

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javafx.scene.image.Image
import java.io.File

class Yacht(boat: Boat) {
    var name = boat.model
    var other : String = String()
    var price = boat.basePrice
    var image = Image("resources\\assert\\yachtPic\\"+ boat.boatId.toString() +"\\1.jpg")
    var id = boat.boatId
}

