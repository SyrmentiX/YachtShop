package resources.async

import com.victorlaerte.asynctask.AsyncTask
import javafx.scene.Scene
import resources.cards.ModerOrderCard
import resources.controller.BaseUIController
import resources.windows.ModerProductionProgressWindow

class ModerOrderLoadAsync(private val controller : BaseUIController) : AsyncTask<Void, Void, ArrayList<ModerOrderCard>>() {
    override fun progressCallback(vararg params: Void?) {

    }

    override fun onPreExecute() {
        controller.isLoading = true
        controller.displayPane.children.clear()
        println("ModerOrderLoadingStart")
    }

    override fun onPostExecute(moderCards: ArrayList<ModerOrderCard>) {
        for (card in moderCards) {
            controller.displayPane.children.add(card.card)
        }
        controller.isLoading = false
        println("ModerOrderLoadingEnd")
    }

    override fun doInBackground(vararg params: Void?): ArrayList<ModerOrderCard> {
        val ordersWithContact = controller.databaseGetter.getAllOrdersWithContract()
        val moderCards : ArrayList<ModerOrderCard> = arrayListOf()
        for (order in ordersWithContact) {
            val card = ModerOrderCard(order.first, order.second)
            card.getEditButton().setOnAction {
                if (!controller.isSubWindowShow) {
                    controller.isSubWindowShow = true
                    val moderProductionProgressWindow =
                        ModerProductionProgressWindow(controller.databaseGetter.getOrderProductionProgress(card.contract.productionProcess))
                    moderProductionProgressWindow.getSaveButton().setOnAction {
                        val newProductionProcess = moderProductionProgressWindow.getProductionProgress()
                        card.changeProductionProgress(newProductionProcess)
                        controller.sender.editProductionProcess(
                            card.contract.contractId,
                            newProductionProcess.productionProcessId
                        )
                    }
                    val scene = Scene(moderProductionProgressWindow.getWindow())
                    controller.stage.scene = scene
                    controller.stage.showAndWait()
                } else {
                    controller.stage.toFront()
                }
            }
            card.getDeleteButton().setOnAction {
                controller.displayPane.children.remove(card.card)
                controller.sender.deleteOrder(order.first.orderId)
            }
            moderCards.add(card)
        }
        return moderCards
    }
}