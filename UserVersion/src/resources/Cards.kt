package resources
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


class YachtCard(var yacht: Yacht) {
    var card = fabric.getYachtCard(yacht)

    fun getBucketButton() : Button {
        return ((card.children[1] as HBox).children[2] as Button)
    }

    fun getDescriptionButton() : Button {
        return ((card.children[1] as HBox).children[3] as Button)
    }
}

class YachtDescriptionCard(var yacht: Yacht) {
    var dataBaseGetter = DatabaseGetter()
    var card = fabric.getDescriptionCard(yacht)
    fun getBucketButton() : Button {
        return (card.children[6]  as Button)
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
        if (((card.children[5] as HBox).children[0] as DatePicker).value == null) {
            return ""
        }
        return ((card.children[5] as HBox).children[0] as DatePicker).value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    }

    fun getPhone() : String {
        return ((card.children[5] as HBox).children[1] as TextField).text
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

    fun setErrorText(text : String) {
        (card.children[11] as Label).text = text
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