package resources.windows

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import resources.Yacht
import resources.beginSentence
import resources.cards.AccessoryCard
import resources.database.AccessoryId
import resources.endSentence
import resources.fabric

class BuyWindow(accessory : ArrayList<AccessoryId>, private val yacht: Yacht) {
    private val window = fabric.getAccessoryWindow()
    private val cardList : MutableList<AccessoryCard> = arrayListOf()
    private var totalPrice = yacht.price

    init {
        for (addition in accessory) {
            val additionCard = AccessoryCard(addition)
            additionCard.getCheckBox().setOnAction {
                if (!additionCard.getCheckBox().isSelected) {
                    this.totalPrice -= additionCard.getAccessoryPrice()
                } else {
                    this.totalPrice += additionCard.getAccessoryPrice()
                }
                getTotalPriceLabel().text = beginSentence + this.totalPrice.toString() + endSentence
            }
            getCenterPane().children.add(additionCard.card)
            cardList.add(additionCard)
            getTotalPriceLabel().text = beginSentence + this.yacht.price.toString() + endSentence
        }
    }

    fun resetPrice() {
        totalPrice = yacht.price
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