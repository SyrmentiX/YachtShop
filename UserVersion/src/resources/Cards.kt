package resources
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

val fabric : Factory = Factory()

class YachtCard(var yacht: Yacht) {
    var card  = fabric.getYachtCard(yacht)

    fun getBucketButton() : Button {
        return ((card.children[1] as HBox).children[2] as Button)
    }

    fun getDescriptionButton() : Button {
        return ((card.children[1] as HBox).children[3] as Button)
    }
}

class YachtDescriptionCard(var yacht: Yacht) {
    var dataBaseGetter = DatabaseGetter()
    var card = fabric.getDescriptionCard(yacht)
    fun getBucketButton() : Button {
        return (card.children[6]  as Button)
    }
}

class OrderCard(var yacht: Yacht) {
    var card : AnchorPane = fabric.getOrderCard(yacht)

    fun setOrderState(state : String) {
        (((card.children[0] as HBox).children[2] as VBox).children[0] as Label).text = state
    }

    fun getInfoButton() : Button {
        return (((card.children[0] as HBox).children[2] as VBox).children[1] as Button)
    }
}

class AccessoryCard(var accessory : AccessoryId) {
    var card : HBox = fabric.getAccessoryCard(accessory)

    fun getCheckBox() : CheckBox {
        return (card.children[0] as CheckBox)
    }

    fun getAccessoryPrice() : Int {
        return accessory.price
    }
}