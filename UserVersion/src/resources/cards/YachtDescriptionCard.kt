package resources.cards

import javafx.scene.control.Button
import resources.Yacht
import resources.fabric

class YachtDescriptionCard(yacht: Yacht) {
    var card = fabric.getDescriptionCard(yacht)

    fun getBuyButton() : Button {
        return (card.children[6]  as Button)
    }
}