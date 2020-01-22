package resources.cards

import com.jfoenix.controls.JFXButton
import resources.fabric

class UpdateCard {
    val card = fabric.getUpdateOrderCard()

    fun getUpdateButton() : JFXButton {
        return card.children[0] as JFXButton
    }
}