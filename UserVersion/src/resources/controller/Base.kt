package resources.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import resources.DatabaseGetter
import resources.Factory
import resources.Yacht

open class Base {
    var addedYacht : ObservableList<Yacht> = FXCollections.observableArrayList()
    var userName : String = "Kekestan"
    val fabric = Factory()
    val databaseGetter = DatabaseGetter()
    //todo add some user info
}