package resources.async

import com.victorlaerte.asynctask.AsyncTask
import resources.cards.LoginCard
import resources.controller.BaseUIController
import resources.controller.nonExitedUserError

class LoginAsync(private val controller : BaseUIController) : AsyncTask<LoginCard, Void, Triple<Int,String, LoginCard>>() {
    override fun progressCallback(vararg p0: Void?) {

    }

    override fun onPreExecute() {
        println("LoginLoadingStarted")
        controller.isLoading = true
    }

    override fun onPostExecute(triple : Triple<Int,String, LoginCard>) {
        println("LoginLoadingEnded")
        controller.isLoading = false
        if (triple.second.isEmpty()) {
            controller.loadUser(triple.first)
            controller.setUsername()
            controller.activateAbility()
            controller.loadDirectory()
        } else {
            triple.third.setErrorText(triple.second)
        }
    }

    override fun doInBackground(vararg loginCard: LoginCard): Triple<Int, String, LoginCard> {
        var errorMessage = loginCard[0].checkInputCorrect()
        var userID = 0
        if (errorMessage.isEmpty()) {
            userID = controller.sender.authorization(loginCard[0].getUsername(), loginCard[0].getPassword())
            if (userID == -1) {
                errorMessage = nonExitedUserError
            }
        }

        return Triple(userID, errorMessage, loginCard[0])
    }
}