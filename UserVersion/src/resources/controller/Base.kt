package resources.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import resources.*

const val nonExitedUserError = "Пользователь несуществует"

open class Base {
    var addedYacht : ObservableList<OrderCard> = FXCollections.observableArrayList()
    var isOrderLoaded : Boolean = false
    var isGuest = true
    var user : Customers = Customers()
    val databaseGetter = DatabaseGetter()
    val sender = Sender()

    init {
        user.firstName = "Kek"
        user.secondName = "Stan"
    }
    //todo add some user info
}