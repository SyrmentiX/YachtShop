package resources.controller

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import resources.Factory
import resources.OrderCard
import resources.Yacht
import resources.YachtCard

val fabric = Factory()

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
            val yacht = Yacht()
            yacht.name = "TEST"
            yacht.creator = "TEST"
            yacht.dateOfCreation = "TEST"
            yacht.other = "TEST"
            yacht.price = 500000
            yacht.image = Image("resources/assert/test.png")

            val yacht2 = Yacht()
            yacht2.name = "TEST"
            yacht2.creator = "TEST"
            yacht2.dateOfCreation = "TEST"
            yacht2.other = "TEST"
            yacht2.price = 100000
            yacht2.image = Image("resources/assert/test.png")
            //todo until
            val yachtCard = YachtCard(yacht)
            val yachtCard2 = YachtCard(yacht2)
            yachtCard.getBucketButton().setOnAction { addedYacht.add(yachtCard.yacht) }
            yachtCard.getDescriptionButton().setOnAction { println("not working") }
            yachtCard2.getBucketButton().setOnAction { addedYacht.add(yachtCard2.yacht) }
            yachtCard2.getDescriptionButton().setOnAction { println("not working") }
            displayPane.children.add(yachtCard.card)
            displayPane.children.add(yachtCard2.card)
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
        //DEEPSKYBLUE
    }
}