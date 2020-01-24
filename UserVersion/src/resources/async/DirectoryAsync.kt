package resources.async

import com.victorlaerte.asynctask.AsyncTask
import javafx.scene.Scene
import javafx.scene.control.ProgressIndicator
import resources.Yacht
import resources.cards.YachtCard
import resources.cards.YachtDescriptionCard
import resources.controller.BaseUIController
import resources.windows.BuyWindow

class DirectoryAsync(private val controller: BaseUIController) : AsyncTask<Void, Void, ArrayList<YachtCard>>() {
    override fun progressCallback(vararg p0: Void?) {

    }

    override fun onPreExecute() {
        controller.displayPane.children.clear()
        println("DirectoryLoadingStarted")
        controller.isLoading = true
        controller.addIndicator()
    }

    override fun onPostExecute(cardList: ArrayList<YachtCard>) {
        controller.removeIndicator()
        println("DirectoryLoadingEnded")
        for (card in cardList) {
            controller.displayPane.children.add(card.card)
        }
        controller.isLoading = false
    }

    override fun doInBackground(vararg p0: Void?): ArrayList<YachtCard> {
        val cardList : ArrayList<YachtCard> = arrayListOf()
        for (boat in controller.databaseGetter.getBoats()) {
            val yacht = Yacht(boat)
            val yachtCard = YachtCard(yacht)

            fun getBuyWindow(){
                if (!controller.isSubWindowShow) {
                    AccessoryWindowAsync(controller).execute(yachtCard)
                } else {
                    controller.stage.toFront()
                }
            }

            yachtCard.getBuyButton().setOnAction {
                if (controller.isGuest) {
                    controller.loadLoginMenu()
                } else {
                    getBuyWindow()
                }
            }

            yachtCard.getDescriptionButton().setOnAction {
                controller.displayPane.children.clear()
                val yachtDescriptionCard = YachtDescriptionCard(yacht)
                yachtDescriptionCard.getBuyButton().setOnAction {
                    if (controller.isGuest) {
                        controller.loadLoginMenu()
                    } else {
                        getBuyWindow()
                    }
                }
                controller.displayPane.children.add(yachtDescriptionCard.card)
            }
            cardList.add(yachtCard)
        }
        return cardList
    }
}