package resources

import javafx.scene.image.Image

data class Yacht(val boat: Boat) {
    var name = boat.model
    var wood : String
    var colour : String
    var type : String
    var numberOfRowers = boat.numberOfRowers
    var price = boat.basePrice
    var isLocal = true
    var priceWithVat = boat.basePrice
    var image = Image("resources\\assert\\yachtPic\\"+ boat.boatId.toString() +"\\1.jpg")
    var selectedAccessory : ArrayList<AccessoryId> = arrayListOf()
    var id = boat.boatId
    var vat = boat.vat

    init {
        val dataBaseGetter = DatabaseGetter()
        this.wood = dataBaseGetter.getYachtWood(boat.wood)
        this.colour = dataBaseGetter.getYachtColour(boat.colour)
        this.type = dataBaseGetter.getYachtType(boat.boatType)
    }

    fun addPriceFromAccessory() {
        for (accessory in selectedAccessory) {
            this.price += accessory.price
        }
    }
}

