package resources.cards

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import resources.database.Contract
import resources.database.Orders
import resources.database.ProductionProcess
import resources.fabric

class ModerOrderCard(orders: Orders, val contract: Contract) {
    val card = fabric.getModerOrderCard(orders, contract)

    fun getEditButton() : Button {
        return (card.children[2] as HBox).children[0] as Button
    }

    fun getDeleteButton() : Button {
        return (card.children[2] as HBox).children[1] as Button
    }

    fun changeProductionProgress(productionProcess: ProductionProcess) {
        ((card.children[0] as HBox).children[2] as Label).text = "Состояние: " + productionProcess.productionProcess1
    }
}