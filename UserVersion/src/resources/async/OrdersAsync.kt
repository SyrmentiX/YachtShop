package resources.async

import com.victorlaerte.asynctask.AsyncTask
import resources.Yacht
import resources.cards.UpdateCard
import resources.controller.BaseUIController

class OrdersAsync(private val controller : BaseUIController) : AsyncTask<Void, Void, UpdateCard>() {
    override fun progressCallback(vararg p0: Void?) {

    }

    private fun loadOrderCard() {
        val userOrders = controller.databaseGetter.getOrdersByUserId(controller.user.customerId)
        for (order in userOrders) {
            val boat = controller.databaseGetter.getBoatById(order.boatId)
            val contract = controller.databaseGetter.getContractByOrderId(order.orderId)
            val productionProcess = controller.databaseGetter.getOrderProductionProgress(contract.productionProcess)
            val yacht = Yacht(boat)
            val details = controller.databaseGetter.getOrderDetail(order.orderId)
            val accessory = controller.databaseGetter.getAccessoryByDetails(details)
            yacht.selectedAccessory = accessory
            yacht.price = contract.contractTotalPrice
            yacht.priceWithVat = contract.contractTotalPriceIncVat
            yacht.isLocal = false
            val orderCard = controller.getOrderCardWithAction(yacht)
            orderCard.setOrderState( productionProcess.productionProcess1)
            controller.addedYacht.add(orderCard)
        }
    }

    override fun onPreExecute() {
        controller.displayPane.children.clear()
        controller.isLoading = true
        println("LoadingOrderStart")
    }

    override fun onPostExecute(updateCard: UpdateCard) {
        controller.isLoading = false
        for (card in controller.addedYacht) {
            controller.displayPane.children.add(card.card)
        }
        controller.displayPane.children.add(updateCard.card)
        println("LoadingOrderEnd")
    }

    override fun doInBackground(vararg p0: Void?): UpdateCard {
        if (!controller.isOrderLoaded) {
            loadOrderCard()
            controller.isOrderLoaded = true
        }
        val updateCard = UpdateCard()
        updateCard.getUpdateButton().setOnAction {
            controller.displayPane.children.clear()
            controller.deleteAllNotLocalYacht()
            controller.isOrderLoaded = false
            OrdersAsync(controller).execute()
        }
        return updateCard
    }
}