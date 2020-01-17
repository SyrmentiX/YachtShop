package resources

import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

const val beginSentence = "Цена: "
const val endSentence = " руб."

class BuyWindow(accessory : ArrayList<AccessoryId>, yacht: Yacht) {
    private val window = fabric.getAccessoryWindow()
    private val cardList : MutableList<AccessoryCard> = arrayListOf()
    private val yacht = yacht

    init {
        for (addition in accessory) {
            val additionCard = AccessoryCard(addition)
            additionCard.getCheckBox().setOnAction {
                if (!additionCard.getCheckBox().isSelected) {
                    this.yacht.price -= additionCard.getAccessoryPrice()
                } else {
                    this.yacht.price += additionCard.getAccessoryPrice()
                }
                getTotalPriceLabel().text = beginSentence + this.yacht.price.toString() + endSentence
            }
            getCenterPane().children.add(additionCard.card)
            cardList.add(additionCard)
            getTotalPriceLabel().text = beginSentence + this.yacht.price.toString() + endSentence
        }
    }

    fun getYacht() : Yacht {
        return yacht
    }

    fun getWindow() : BorderPane {
        return window
    }

    private fun getSelectedAccessory() : ArrayList<AccessoryId> {
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
    }

    private fun getCenterPane() : VBox {
        return (window.center as VBox)
    }

    fun getBuyButton() : Button {
        return ((window.bottom as HBox).children[1] as Button)
    }

    private fun getTotalPriceLabel() : Label {
        return ((window.bottom as HBox).children[0] as Label)
    }
}

class OrderDescriptionWindow(yacht: Yacht) {
    private val window = fabric.getOrderDescriptionWindow(yacht)

    init {
        for (addition in yacht.selectedAccessory) {
            val additionCard = AccessoryCard(addition)
            additionCard.getCheckBox().isSelected = true
            additionCard.getCheckBox().isDisable = true
            getCenterPane().children.add(additionCard.card)
        }
    }

    private fun getCenterPane() : VBox {
        return (window.center as VBox)
    }

    fun getWindow() : BorderPane {
        return window
    }

    fun getCloseButton() : Button {
        return (((window.bottom as VBox).children[1] as HBox).children[1] as Button)
    }

    fun isPayedCheckBox() : Boolean {
        return (((window.bottom as VBox).children[1] as HBox).children[0] as CheckBox).isSelected
    }

}