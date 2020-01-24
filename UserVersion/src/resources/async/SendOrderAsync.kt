package resources.async

import com.victorlaerte.asynctask.AsyncTask
import resources.cards.OrderCard
import resources.controller.BaseUIController
import resources.windows.OrderDescriptionWindow

class SendOrderAsync(private val controller : BaseUIController) : AsyncTask<Pair<OrderCard, OrderDescriptionWindow>, Void, OrderCard>() {
    override fun progressCallback(vararg p0: Void?) {

    }

    override fun onPreExecute() {
        println("SendingOrderStart")
    }

    override fun onPostExecute(card: OrderCard) {
        card.setOrderState("В обработке")
        println("SendingOrderEnd")
    }

    override fun doInBackground(vararg pair: Pair<OrderCard, OrderDescriptionWindow>): OrderCard {
        pair[0].second.disableCheckBox()
        pair[0].second.hideCancelButton()
        val order = controller.databaseClassParser.createUserOrder(pair[0].first.yacht, controller.user)
        val contract = controller.databaseClassParser.createOrderContract(pair[0].first.yacht, order.orderId)
        val detail = controller.databaseClassParser.createOrderDetails(pair[0].first.yacht, order.orderId)
        controller.sender.sendOrder(order, contract, detail)
        pair[0].first.yacht.isLocal = false
        return pair[0].first
    }
}