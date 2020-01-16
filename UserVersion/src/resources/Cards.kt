package resources
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.time.format.DateTimeFormatter
import javax.print.Doc

val fabric : Factory = Factory()

class YachtCard(var yacht: Yacht) {
    var card = fabric.getYachtCard(yacht)

    fun getBucketButton() : Button {
        return ((card.children[1] as HBox).children[2] as Button)
    }

    fun getDescriptionButton() : Button {
        return ((card.children[1] as HBox).children[3] as Button)
    }
}

class OrderCard(var yacht: Yacht) {
    var card = fabric.getOrderCard(yacht)

    fun setOrderState(state : String) {
        (((card.children[0] as HBox).children[2] as VBox).children[0] as Label).text = state
    }

    fun getInfoButton() : Button {
        return (((card.children[0] as HBox).children[2] as VBox).children[1] as Button)
    }
}

class AccessoryCard(var accessory : AccessoryId) {
    var card = fabric.getAccessoryCard(accessory)

    fun getCheckBox() : CheckBox {
        return (card.children[0] as CheckBox)
    }

    fun getAccessoryPrice() : Int {
        return accessory.price
    }
}

class LoginCard {
    val card = fabric.getLoginCard()

    fun getLoginButton() : Button {
        return ((card.children[3] as HBox).children[0] as Button)
    }

    fun getRegisterButton() : Button {
        return ((card.children[3] as HBox).children[1] as Button)
    }

    fun getUsername() : String {
        return (card.children[1] as TextField).text
    }

    fun getPassword() : String {
        return (card.children[2] as PasswordField).text
    }

    fun isUsernameNotEmpty() : Boolean {
        return (getUsername().isNotEmpty())
    }

    fun isPasswordNotEmpty() : Boolean {
        return (getPassword().isNotEmpty())
    }


}

class RegisterCard {
    val card = fabric.getRegistrationCard()

    fun getLogin() : String {
        return (card.children[1] as TextField).text
    }

    fun getEMail() : String {
        return (card.children[2] as TextField).text
    }

    fun getPassword() : String {
        return (card.children[3] as PasswordField).text
    }

    fun getFirstName() : String {
        return ((card.children[4] as HBox).children[0] as TextField).text
    }

    fun getSecondName() : String {
        return ((card.children[4] as HBox).children[1] as TextField).text
    }

    fun getBirthDate() : String {
        return ((card.children[5] as HBox).children[0] as DatePicker).value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    }

    fun getPhone() : String {
        return ((card.children[5] as HBox).children[0] as TextField).text
    }

    fun getAddress() : String {
        return (card.children[6] as TextField).text
    }

    fun getCity() : String {
        return (card.children[7] as TextField).text
    }

    fun getDocumentType() : DocumentName {
        return (card.children[8] as ChoiceBox<*>).value as DocumentName
    }

    fun getDocumentSeries() : String {
        return (card.children[9] as TextField).text
    }

    fun getCancelButton() : Button {
        return ((card.children[10] as HBox).children[0] as Button)
    }

    fun getRegisterButton() : Button {
        return ((card.children[10] as HBox).children[1] as Button)
    }
}