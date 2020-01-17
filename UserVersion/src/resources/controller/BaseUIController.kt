package resources.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ProgressIndicator
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage
import resources.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlinx.coroutines.launch

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

    private var list : ObservableList<YachtCard> = FXCollections.observableArrayList()

    private suspend fun getYachtList() = coroutineScope {
        for (boat in databaseGetter.getBoats()) {
            val yacht = Yacht(boat)
            val yachtCard = YachtCard(yacht)

            fun getBuyWindow() {
                val buyWindow = BuyWindow(databaseGetter.getAccessoryByBoatId(yacht.id), yacht)
                val stage = Stage()
                buyWindow.getBuyButton().setOnAction {
                    val vat = databaseGetter.getVat(yacht.vat)
                    buyWindow.getYacht().priceWithVat += (yacht.price * vat.vat1).toInt()
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
                displayPane.children.add(yachtDescriptionCard.card)
            }
            //            displayPane.children.add(yachtCard.card)
            list.add(yachtCard)
        }
    }


    private fun directoryAction() {

        displayPane.children.clear()
        val indecator = ProgressIndicator()
        displayPane.children.add(indecator)

        val a = GlobalScope.launch {
            getYachtList()
            displayPane.children.clear()
            list.forEach {
                displayPane.children.add(it.card)
            }
        }
        a.start()
    }

    private fun getOrderCard(yacht: Yacht, state: String) : OrderCard {
        val orderCard = OrderCard(yacht)
        orderCard.setOrderState(state)
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
        return orderCard
    }

    private fun fillOrder() {
        val userOrders = databaseGetter.getOrdersByUserId(user.customerId)
        for (order in userOrders) {
            val boat = databaseGetter.getBoatById(order.boatId)
            val contract = databaseGetter.getContractByOrderId(order.orderId)
            val productionProcess = databaseGetter.getOrderProductionProgress(contract.productionProcess)
            val yacht = Yacht(boat)
            val details = databaseGetter.getOrderDetail(order.orderId)
            val accessory = databaseGetter.getAccessoryByDetails(details)
            yacht.selectedAccessory = accessory
            yacht.price = contract.contractTotalPriceIncVat
            val orderCard = getOrderCard(yacht, productionProcess.productionProcess1)
            orderedYacht.add(orderCard)
        }
    }

    private fun orderAction() {
        displayPane.children.clear()
        if (addedYacht.isNotEmpty()) {
            for (yacht in addedYacht) {
                val orderCard = getOrderCard(yacht,"Ожидает оплаты")
                displayPane.children.add(orderCard.card)
            }
        }
        if (!isOrderLoaded) {
            fillOrder()
            isOrderLoaded = true
        }
        for (card in orderedYacht) {
            displayPane.children.add(card.card)
        }
    }

    private fun setUserName() {
        profileButton.text = user.firstName + " " + user.secondName
    }

    fun initialize() {
        profileButton.background = Background(BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        menuPane.background = Background(BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        setUserName()

        val loginCard = LoginCard()
        loginCard.getLoginButton().setOnAction {
            var errorMessage = loginCard.checkInputCorrect()
            if (errorMessage.isEmpty()) {
                val userID = sender.checkAuth(loginCard.getUsername(), loginCard.getPassword())
                if (userID == -1) {
                    errorMessage = nonExitedUserError
                } else {
                    user = databaseGetter.getUserById(userID)
                    setUserName()
                    directoryAction()
                }
            }
            loginCard.setErrorText( errorMessage)
        }

        loginCard.getRegisterButton().setOnAction {
            displayPane.children.clear()

            val registerCard = RegisterCard()
            registerCard.getCancelButton().setOnAction {
                displayPane.children.clear()
                displayPane.children.add(loginCard.card)
            }
            registerCard.getRegisterButton().setOnAction {
                var errorMessages = registerCard.checkDataCorrect()
                if (errorMessages.isEmpty()) {
                    val auth = registerCard.getAuthFromInputData()
                    val customer = registerCard.getCustomerFromInputData()
                    sender.registration(auth, customer)
                    //todo check server response
                }
                registerCard.setErrorText(errorMessages)
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