package resources.async

import com.victorlaerte.asynctask.AsyncTask
import javafx.scene.Scene
import resources.Yacht
import resources.cards.YachtCard
import resources.controller.BaseUIController
import resources.windows.BuyWindow

class AccessoryWindowAsync(private val controller : BaseUIController) : AsyncTask<YachtCard, Void, BuyWindow>() {
    override fun progressCallback(vararg p0: Void?) {

    }

    override fun onPreExecute() {
        controller.isLoading = true
        println("LoadingAccessoryStarted")
    }

    override fun onPostExecute(buyWindow: BuyWindow) {
        controller.isLoading = false
        println("LoadingAccessoryEnded")
        val scene = Scene(buyWindow.getWindow())
        controller.stage.scene = scene
        controller.stage.showAndWait()
    }

    override fun doInBackground(vararg yachtCard: YachtCard): BuyWindow {
        val buyWindow = BuyWindow(controller.databaseGetter.getAccessoryByBoatId(yachtCard[0].yacht.id), yachtCard[0].yacht)
        controller.isSubWindowShow = true
        buyWindow.getBuyButton().setOnAction {
            controller.isSubWindowShow = false
            val vat = controller.databaseGetter.getVat(yachtCard[0].yacht.vat)
            val orderedYacht: Yacht = buyWindow.getYacht().copy()
            orderedYacht.selectedAccessory = buyWindow.getSelectedAccessory()
            orderedYacht.addPriceFromAccessory()
            orderedYacht.priceWithVat += (orderedYacht.price * vat.vat1).toInt()
            val orderCard = controller.getOrderCardWithAction(orderedYacht)
            orderCard.setOrderState("Ожидает оплаты")
            controller.addedYacht.add(orderCard)
            buyWindow.resetPrice()
            controller.stage.close()
        }
        return buyWindow
    }
}