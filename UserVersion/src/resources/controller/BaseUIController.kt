package resources.controller

import javafx.fxml.FXML
import javafx.geometry.Insets
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
    lateinit var directoryButton : Button

    @FXML
    lateinit var orderButton : Button

    @FXML
    lateinit var displayPane : FlowPane

    private fun directoryAction() {
        displayPane.children.clear()
        for (boat in databaseGetter.getBoats()) {
            val yacht = Yacht(boat)
            val yachtCard = YachtCard(yacht)
            yachtCard.getBucketButton().setOnAction {
                val buyWindow = BuyWindow(databaseGetter.getAccessoryByBoatId(yacht.id), yacht)
                val stage = Stage()
                buyWindow.getBuyButton().setOnAction {
                    buyWindow.addSelectedAccessory()
                    addedYacht.add(buyWindow.getYacht())
                    stage.close()
                }
                val scene = Scene(buyWindow.getWindow())
                stage.scene = scene
                stage.showAndWait()
            }
            yachtCard.getDescriptionButton().setOnAction { println("not working") }
            displayPane.children.add(yachtCard.card)
        }
    }

    private fun orderAction() {
        displayPane.children.clear()
        if (addedYacht.isNotEmpty()) {
            for (yacht in addedYacht) {
                val orderCard = OrderCard(yacht)
                orderCard.setOrderState("Ожидает оплаты")

                orderCard.getInfoButton().setOnAction {
                    val orderDescriptionWindow = OrderDescriptionWindow(orderCard.yacht)
                    val stage = Stage()
                    orderDescriptionWindow.getCloseButton().setOnAction {
                        //todo check CheckBox and send Request
                        stage.close()
                    }
                    stage.scene = Scene(orderDescriptionWindow.getWindow())
                    stage.showAndWait()
                }

                displayPane.children.add(orderCard.card)
            }
        }
    }

    @FXML
    lateinit var menuPane : VBox

    fun initialize() {
        profileButton.background = Background(BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        menuPane.background = Background(BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        profileButton.text = userName

        val loginCard = LoginCard()
        loginCard.getLoginButton().setOnAction {
            // todo server send request
        }

        loginCard.getRegisterButton().setOnAction {
            displayPane.children.clear()

            val registerCard = RegisterCard()
            registerCard.getCancelButton().setOnAction {
                displayPane.children.clear()
                displayPane.children.add(loginCard.card)
            }
            registerCard.getRegisterButton().setOnAction {
                //todo send request
            }

            displayPane.children.add(registerCard.card)
        }

        displayPane.children.add(loginCard.card)

        directoryButton.setOnAction {
            directoryAction()
        }

        orderButton.setOnAction {
            orderAction()
        }
        profileButton.setOnAction {
            displayPane.children.clear()
        }
    }
}