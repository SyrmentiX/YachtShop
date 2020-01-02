package resources.controller

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import resources.Factory
import resources.Yacht
import resources.YachtCard

val fabric : Factory = Factory()

class BaseUIController {
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
        profileButton.setOnAction {
            val yacht = Yacht()
            yacht.name = "TEST"
            yacht.creator = "TEST"
            yacht.dateOfCreation = "TEST"
            yacht.other = "TEST"
            yacht.price = 500000
            yacht.image = Image("resources/assert/test.png")
            val yachtCard = YachtCard(yacht)
            displayPane.children.add(yachtCard.card)
        }
        //DEEPSKYBLUE
    }
}