package resources.async

import com.victorlaerte.asynctask.AsyncTask
import resources.cards.LoginCard
import resources.cards.RegisterCard
import resources.controller.BaseUIController

class RegistrationAsync(private val controller : BaseUIController) : AsyncTask<LoginCard, Void, RegisterCard>() {
    override fun progressCallback(vararg p0: Void?) {

    }

    override fun onPreExecute() {
        controller.displayPane.children.clear()
        println("RegisterLoadingStarted")
        controller.isLoading = true
    }

    override fun onPostExecute(registerCard: RegisterCard?) {
        println("RegisterLoadingEnded")
        controller.displayPane.children.add(registerCard?.card)
        controller.isLoading = false
    }

    override fun doInBackground(vararg loginCard: LoginCard?): RegisterCard {
        val registerCard = RegisterCard()
        registerCard.getCancelButton().setOnAction {
            controller.displayPane.children.clear()
            controller.displayPane.children.add(loginCard[0]?.card)
        }
        registerCard.getRegisterButton().setOnAction {
            val errorMessages = registerCard.checkDataCorrect()
            if (errorMessages.isEmpty()) {
                val auth = registerCard.getAuthFromInputData()
                val customer = registerCard.getCustomerFromInputData()
                controller.sender.registration(auth, customer)
                controller.displayPane.children.clear()
                controller.loadLoginMenu()
            }
            registerCard.setErrorText(errorMessages)
        }
        return registerCard
    }

}