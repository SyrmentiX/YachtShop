package resources.windows

import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import resources.Yacht
import resources.cards.AccessoryCard
import resources.fabric

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

    fun getCheckBox() : CheckBox {
        return (((window.bottom as VBox).children[1] as HBox).children[0] as CheckBox)
    }

    fun getCancelButton() : Button {
        return (((window.bottom as VBox).children[1] as HBox).children[1] as Button)
    }

    fun getCloseButton() : Button {
        return (((window.bottom as VBox).children[1] as HBox).children[2] as Button)
    }

    fun disableCheckBox() {
        getCheckBox().isDisable = true
    }

    fun hideCancelButton() {
        getCancelButton().isVisible = false
        getCancelButton().isDisable = true
    }
}