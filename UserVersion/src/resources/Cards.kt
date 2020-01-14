package resources
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

val fabric : Factory = Factory()

class YachtCard(var yacht: Yacht) {
    var card : AnchorPane = fabric.getYachtCard(yacht)

    fun getBucketButton() : Button {
        return (((card.children[1] as HBox).children[2] as VBox).children[0] as Button)
    }

    fun getDescriptionButton() : Button {
        return (((card.children[1] as HBox).children[2] as VBox).children[1] as Button)
    }
}

class OrderCard(var yacht: Yacht) {
    var card : AnchorPane = fabric.getOrderCard(yacht)

    fun getDeleteButton() : Button {
        return (((card.children[0] as HBox).children[1] as VBox).children[1] as Button)
    }
}

class AccessoryCard(var accessory : AccessoryId) {
    var card : HBox = fabric.getAccessoryCard(accessory)

    fun getCheckBox() : CheckBox {
        return (card.children[0] as CheckBox)
    }
}