package resources.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import resources.Yacht

open class Base {
    var addedYacht : ObservableList<Yacht> = FXCollections.observableArrayList()
    var userName : String = "Kekestan"
    //todo add some user info
}