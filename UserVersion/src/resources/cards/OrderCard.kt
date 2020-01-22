package resources.cards

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import resources.Yacht
import resources.fabric

class OrderCard(val yacht: Yacht) {
    var card = fabric.getOrderCard(yacht)

    fun setOrderState(state : String) {
        (((card.children[0] as HBox).children[2] as VBox).children[0] as Label).text = state
    }

    fun getInfoButton() : Button {
        return (((card.children[0] as HBox).children[2] as VBox).children[1] as Button)
    }
}