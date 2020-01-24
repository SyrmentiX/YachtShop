package resources.cards

import javafx.scene.control.Button
import javafx.scene.layout.HBox
import resources.Yacht
import resources.fabric

class YachtCard(val yacht: Yacht) {
    var card = fabric.getYachtCard(yacht)

    fun getBuyButton() : Button {
        return ((card.children[1] as HBox).children[2] as Button)
    }

    fun getDescriptionButton() : Button {
        return ((card.children[1] as HBox).children[3] as Button)
    }
}
