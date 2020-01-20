package resources
import com.jfoenix.controls.JFXButton
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.time.format.DateTimeFormatter

val fabric : Factory = Factory()
const val usernameError : String = "Введите логин"
const val passwordError : String = "Введите пароль"
const val dateError : String = "Введите дату"
const val emailError : String = "Введите почту"
const val firstNameError : String = "Введите имя"
const val secondNameError : String = "Введите фамилию"
const val phoneError : String = "Введите номер телефона"
const val addressError : String = "Введите адрес"
const val cityError : String = "Введите город"
const val documentError : String = "Введите номер и серию документа"

class YachtCard(yacht: Yacht) {
    var card = fabric.getYachtCard(yacht)

    fun getBuyButton() : Button {
        return ((card.children[1] as HBox).children[2] as Button)
    }

    fun getDescriptionButton() : Button {
        return ((card.children[1] as HBox).children[3] as Button)
    }
}

class YachtDescriptionCard(yacht: Yacht) {
    var card = fabric.getDescriptionCard(yacht)

    fun getBuyButton() : Button {
        return (card.children[6]  as Button)
    }
}

class OrderCard(val yacht: Yacht) {
    var card = fabric.getOrderCard(yacht)

    fun setOrderState(state : String) {
        (((card.children[0] as HBox).children[2] as VBox).children[0] as Label).text = state
    }

    fun getInfoButton() : Button {
        return (((card.children[0] as HBox).children[2] as VBox).children[1] as Button)
    }
}

class AccessoryCard(val accessory : AccessoryId) {
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

class RegisterCard {
    val card = fabric.getRegistrationCard()

    private fun getLogin() : String {
        return (card.children[1] as TextField).text
    }

    private fun getEMail() : String {
        return (card.children[2] as TextField).text
    }

    private fun getPassword() : String {
        return (card.children[3] as PasswordField).text
    }

    private fun getFirstName() : String {
        return ((card.children[4] as HBox).children[0] as TextField).text
    }

    private fun getSecondName() : String {
        return ((card.children[4] as HBox).children[1] as TextField).text
    }

    private fun getBirthDate() : String {
        val datePicker = ((card.children[6] as HBox).children[0] as DatePicker)
        if (datePicker.value == null) {
            return ""
        }
        return datePicker.value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    }

    private fun getPhone() : String {
        return ((card.children[6] as HBox).children[1] as TextField).text
    }

    private fun getAddress() : String {
        return (card.children[7] as TextField).text
    }

    private fun getCity() : String {
        return (card.children[8] as TextField).text
    }

    private fun getDocumentType() : DocumentName {
        return (card.children[9] as ChoiceBox<*>).value as DocumentName
    }

    private fun getDocumentSeries() : String {
        return (card.children[10] as TextField).text
    }

    fun getCancelButton() : Button {
        return ((card.children[11] as HBox).children[0] as Button)
    }

    fun getRegisterButton() : Button {
        return ((card.children[11] as HBox).children[1] as Button)
    }

    fun setErrorText(text : String) {
        (card.children[12] as Label).text = text
    }

    fun getCustomerFromInputData() : Customers {
        val customer = Customers()
        customer.address = getAddress()
        customer.city = getCity()
        customer.dateOfBirth = getBirthDate()
        customer.email = getEMail()
        customer.firstName = getFirstName()
        customer.secondName = getSecondName()
        customer.phoneNumber = getPhone()
        customer.idDocumentName = getDocumentType().documentNameId
        customer.idNumber = getDocumentSeries()
        return customer
    }

    fun getAuthFromInputData() : Auth {
        val auth = Auth()
        auth.username = getLogin()
        auth.password = getPassword()
        return auth
    }

    fun checkDataCorrect() : String {
        if (getLogin().isEmpty()) {
            return usernameError
        }
        if (getEMail().isEmpty()) {
            return emailError
        }
        if (getPassword().isEmpty()) {
            return passwordError
        }
        if (getFirstName().isEmpty()) {
            return firstNameError
        }
        if (getSecondName().isEmpty()) {
            return secondNameError
        }
        if (getBirthDate().isEmpty()) {
            return dateError
        }
        if (getPhone().isEmpty()) {
            return phoneError
        }
        if (getAddress().isEmpty()) {
            return addressError
        }
        if (getCity().isEmpty()) {
            return cityError
        }
        if (getDocumentSeries().isEmpty()) {
            return documentError
        }
        return ""
    }
}

class CommunicationCard {
    val card = fabric.getCommunicationCard()
}

class UpdateCard {
    val card = fabric.getUpdateOrderCard()

    fun getUpdateButton() : JFXButton {
        return card.children[0] as JFXButton
    }
}