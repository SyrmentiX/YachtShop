package resources.cards

import javafx.scene.control.Button
import javafx.scene.layout.HBox
import resources.database.Auth
import resources.database.Customers
import resources.fabric

class UserCard(val pair: Pair<Customers, Auth>) {
    val card = fabric.getUserCard(pair.first, pair.second)

    fun getDeleteButton() : Button {
        return ((card.children[2] as HBox).children[0] as Button)
    }

    fun getCustomerID() : Int {
        return pair.first.customerId
    }
}