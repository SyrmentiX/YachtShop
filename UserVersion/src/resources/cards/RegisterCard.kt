package resources.cards

import javafx.scene.control.*
import javafx.scene.layout.HBox
import resources.*
import resources.database.Auth
import resources.database.Customers
import resources.database.DocumentName
import java.time.format.DateTimeFormatter

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
        val datePicker = ((card.children[5] as HBox).children[0] as DatePicker)
        if (datePicker.value == null) {
            return ""
        }
        return datePicker.value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    }

    private fun getPhone() : String {
        return ((card.children[5] as HBox).children[1] as TextField).text
    }

    private fun getAddress() : String {
        return (card.children[6] as TextField).text
    }

    private fun getCity() : String {
        return (card.children[7] as TextField).text
    }

    private fun getDocumentType() : DocumentName {
        return (card.children[8] as ChoiceBox<*>).value as DocumentName
    }

    private fun getDocumentSeries() : String {
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