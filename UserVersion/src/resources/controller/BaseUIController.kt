package resources.controller

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage
import resources.*

class BaseUIController : Base() {
    @FXML
    lateinit var profileButton : Button

    @FXML
    lateinit var bucketButton : Button

    @FXML
    lateinit var orderButton : Button

    @FXML
    lateinit var displayPane : FlowPane

    @FXML
    lateinit var menuPane : VBox

    fun initialize() {
        profileButton.background = Background(BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        menuPane.background = Background(BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        profileButton.text = userName

        orderButton.setOnAction {
            displayPane.children.clear()
            //todo Add server part
            for (boat in databaseGetter.getBoats()) {
                val yacht = Yacht(boat)
                val yachtCard = YachtCard(yacht)
                yachtCard.getBucketButton().setOnAction {
                    val buyWindow = BuyWindow(databaseGetter.getAccessoryByBoatId(yacht.id))
                    val scene = Scene(buyWindow.getWindow())
                    val stage = Stage()
                    stage.scene = scene
                    stage.showAndWait()
                    //addedYacht.add(yachtCard.yacht)
                }
                yachtCard.getDescriptionButton().setOnAction { println("not working") }
                displayPane.children.add(yachtCard.card)
            }

            //todo until
        }

        bucketButton.setOnAction {
            displayPane.children.clear()
            if (addedYacht.isNotEmpty()) {
                for (yacht in addedYacht) {
                    val orderCard = OrderCard(yacht)
                    orderCard.getDeleteButton().setOnAction {
                        displayPane.children.remove(orderCard.card)
                        addedYacht.remove(orderCard.yacht)
                        if (addedYacht.isEmpty()) {
                            displayPane.children.clear()
                        }
                    }
                    displayPane.children.add(orderCard.card)
                }
                displayPane.children.add(fabric.getBuyPane())
            }
        }
        profileButton.setOnAction {
            //todo ask if needed
            displayPane.children.clear()
        }
    }
}