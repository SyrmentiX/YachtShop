package resources.async

import com.victorlaerte.asynctask.AsyncTask
import resources.cards.UserCard
import resources.controller.BaseUIController

class UserLoaderAsync(private val controller : BaseUIController) : AsyncTask<Void, Void, ArrayList<UserCard>>() {
    override fun progressCallback(vararg params: Void?) {

    }

    override fun onPreExecute() {
        controller.isLoading = true
        controller.displayPane.children.clear()
        println("LoadingAdminPanelStart")
    }

    override fun onPostExecute(userCards: ArrayList<UserCard>) {
        controller.isLoading = false
        for (card in userCards) {
            controller.displayPane.children.add(card.card)
        }
        println("LoadingAdminPanelEnd")
    }

    override fun doInBackground(vararg params: Void?): ArrayList<UserCard> {
        val users = controller.databaseGetter.getAllCustomerWithAuth()
        val cards : ArrayList<UserCard> = arrayListOf()
        for (user in users) {
            val card = UserCard(user)
            card.getDeleteButton().setOnAction {
                controller.displayPane.children.remove(card.card)
                controller.sender.deleteCustomer(card.getCustomerID())
            }
            cards.add(card)
        }
        return cards
    }
}