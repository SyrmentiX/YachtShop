package resources.cards

import javafx.scene.control.CheckBox
import resources.database.AccessoryId
import resources.fabric

class AccessoryCard(val accessory : AccessoryId) {
    var card = fabric.getAccessoryCard(accessory)

    fun getCheckBox() : CheckBox {
        return (card.children[0] as CheckBox)
    }

    fun getAccessoryPrice() : Int {
        return accessory.price
    }
}