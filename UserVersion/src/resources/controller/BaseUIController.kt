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

    @FXML
    lateinit var menuPane : VBox

    fun initialize() {
        profileButton.background = Background(BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        menuPane.background = Background(BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        profileButton.text = userName

        val loginCard = LoginCard()
        loginCard.getLoginButton().setOnAction {
            // todo server part
        }

        loginCard.getRegisterButton().setOnAction {
            // todo change form
        }

        displayPane.children.add(loginCard.card)

        directoryButton.setOnAction {
            displayPane.children.clear()
            for (boat in databaseGetter.getBoats()) {
                val yacht = Yacht(boat)
                val yachtCard = YachtCard(yacht)

                fun getBuyWindow(){
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

                yachtCard.getBucketButton().setOnAction {
                    getBuyWindow()
                }

                yachtCard.getDescriptionButton().setOnAction {
                    displayPane.children.clear()
                    val yachtDescriptionCard = YachtDescriptionCard(yacht)
                    yachtDescriptionCard.getBucketButton().setOnAction {
                        getBuyWindow()
                    }
                    println("not working")
                    displayPane.children.add(yachtDescriptionCard.card)
                }
                displayPane.children.add(yachtCard.card)
            }
        }

        orderButton.setOnAction {
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
        profileButton.setOnAction {
            //todo ask if needed
            displayPane.children.clear()
        }
    }
}