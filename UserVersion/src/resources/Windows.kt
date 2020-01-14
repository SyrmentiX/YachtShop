package resources

import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class BuyWindow(accessory : ArrayList<AccessoryId>, yacht: Yacht) {
    private val window = fabric.getAccessoryWindow()
    private val cardList : MutableList<AccessoryCard> = arrayListOf()
    private val yacht = yacht

    init {
        for (addition in accessory) {
            val additionCard = AccessoryCard(addition)
            getCenterPane().children.add(additionCard.card)
            cardList.add(additionCard)
        }
    }

    fun getYacht() : Yacht {
        return yacht
    }

    fun getWindow() : BorderPane {
        return window
    }

    fun getSelectedAccessory() : ArrayList<AccessoryId> {
        val selectedAccessory : ArrayList<AccessoryId> = arrayListOf()
        for (card in cardList) {
            if (card.getCheckBox().isSelected) {
                selectedAccessory.add(card.accessory)
            }
        }
        return selectedAccessory
    }

    fun addSelectedAccessory() {
        yacht.selectedAccessory = getSelectedAccessory()
        for (accessory in yacht.selectedAccessory) {
            yacht.price += accessory.price
        }
    }

    private fun getCenterPane() : VBox {
        return (window.center as VBox)
    }

    fun getBuyButton() : Button {
        return ((window.bottom as HBox).children[0] as Button)
    }
}