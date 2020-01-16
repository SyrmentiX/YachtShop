package resources

import javafx.scene.image.Image

class Yacht(boat: Boat) {
    private var dataBaseGetter = DatabaseGetter()
    var name = boat.model
    var wood = dataBaseGetter.getYachtWood(boat.wood)
    var colour = dataBaseGetter.getYachtColour(boat.colour)
    var type = dataBaseGetter.getYachtType(boat.boatType)
    var numberOfRowers = boat.numberOfRowers
    var other : String = String()
    var price = boat.basePrice
    var image = Image("resources\\assert\\yachtPic\\"+ boat.boatId.toString() +"\\1.jpg")
    var selectedAccessory : ArrayList<AccessoryId> = arrayListOf()
    var id = boat.boatId
    var vat = boat.vat
}

