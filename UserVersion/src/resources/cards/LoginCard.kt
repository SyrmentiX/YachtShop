package resources.cards

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import resources.fabric
import resources.passwordError
import resources.usernameError

class LoginCard {
    val card = fabric.getLoginCard()

    fun getLoginButton() : Button {
        return ((card.children[3] as HBox).children[0] as Button)
    }

    fun getRegisterButton() : Button {
        return ((card.children[3] as HBox).children[1] as Button)
    }

    fun setErrorText(text : String) {
        (card.children[4] as Label).text = text
    }

    fun getUsername() : String {
        return (card.children[1] as TextField).text
    }

    fun getPassword() : String {
        return (card.children[2] as PasswordField).text
    }

    fun checkInputCorrect() : String {
        if (getUsername().isEmpty()) {
            return usernameError
        }
        if (getPassword().isEmpty()) {
            return passwordError
        }
        return ""
    }
}
