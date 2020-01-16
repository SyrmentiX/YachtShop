package resources.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import resources.*

const val nonExitedUserError = "Пользователь несуществует"

open class Base {
    var addedYacht : ObservableList<Yacht> = FXCollections.observableArrayList()
    var orderedYacht : ObservableList<OrderCard> = FXCollections.observableArrayList()
    var isOrderLoaded : Boolean = false
    var user : Customers = Customers()
    val fabric = Factory()
    val databaseGetter = DatabaseGetter()
    val sender = Sender()

    init {
        user.firstName = "Kek"
        user.secondName = "Stan"
    }
    //todo add some user info
}