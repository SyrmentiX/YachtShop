package resources

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class BuyWindow(additions : ArrayList<AccessoryId>) {
    private val window = fabric.getAccessoryWindow()
    private val cardList : MutableList<AdditionCard> = arrayListOf()

    init {
        for (addition in additions) {
            val additionCard = AdditionCard(addition)
            getCenterPane().children.add(additionCard.card)
            cardList.add(additionCard)
        }
    }

    fun getWindow() : BorderPane {
        return window
    }

    fun getSelectedAdditions() : MutableList<AdditionCard> {
        val selectedAddition : MutableList<AdditionCard> = arrayListOf()
        for (card in cardList) {
            if (card.getCheckBox().isSelected) {
                selectedAddition.add(card)
            }
        }
        return selectedAddition
    }

    private fun getCenterPane() : VBox {
        return (window.center as VBox)
    }

    fun getCloseButton() : Button {
        return ((window.top as HBox).children[0] as Button)
    }

    fun getBuyButton() : Button {
        return ((window.bottom as HBox).children[0] as Button)
    }
}