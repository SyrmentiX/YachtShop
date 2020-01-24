package resources.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.ProgressIndicator
import javafx.stage.Stage
import resources.*
import resources.cards.OrderCard
import resources.database.Customers
import resources.database.DatabaseClassParser
import resources.database.DatabaseGetter
import resources.database.DatabaseSender

const val nonExitedUserError = "Пользователь несуществует"

open class Base {
    var addedYacht : ObservableList<OrderCard> = FXCollections.observableArrayList()
    var isOrderLoaded : Boolean = false
    var isGuest = true
    var isSubWindowShow = false
    var user : Customers = Customers()
    val databaseGetter = DatabaseGetter()
    val sender = DatabaseSender()
    val databaseClassParser = DatabaseClassParser()
    val stage : Stage = Stage()
    val indicator = fabric.getLoadIndicator()
    var isLoading = false

    init {
        user.firstName = "Kek"
        user.secondName = "Stan"
        this.stage.setOnCloseRequest { isSubWindowShow = false }
    }

    fun deleteAllNotLocalYacht() {
        val newList : ObservableList<OrderCard> = FXCollections.observableArrayList()
        for (card in addedYacht) {
            if (card.yacht.isLocal) {
                newList.add(card)
            }
        }
        addedYacht = newList
    }

    fun loadUser(userID : Int) {
        user = databaseGetter.getUserById(userID)
        isGuest = false
    }
    //todo add some user info
}