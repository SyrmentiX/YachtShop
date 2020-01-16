package resources

import javafx.scene.image.Image

class Yacht(boat: Boat) {
    var name = boat.model
    var price = boat.basePrice
    var image = Image("resources\\assert\\yachtPic\\"+ boat.boatId.toString() +"\\1.jpg")
    var selectedAccessory : ArrayList<AccessoryId> = arrayListOf()
    var id = boat.boatId
}

