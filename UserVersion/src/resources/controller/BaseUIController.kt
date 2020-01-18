package resources.controller

import com.jfoenix.controls.JFXButton
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.Stage
import resources.*
import java.awt.Desktop
import java.net.URI
import java.net.URL


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

    private fun setUsername() {
        profileButton.text = user.firstName + " " + user.secondName
    }

    private fun createUserOrder(yacht: Yacht) : Orders {
        val order = Orders()
        order.customerId = user.customerId
        order.deliveryAddress = user.address
        order.city = user.city
        order.boatId = yacht.id
        return order
    }

    private fun createOrderContract(yacht: Yacht, orderID : Int) : Contract {
        val contract = Contract()
        contract.contractTotalPrice = yacht.price
        contract.contractTotalPriceIncVat = yacht.priceWithVat
        contract.dateDepositPayed = 1
        contract.orderId = orderID
        return contract
    }

    private fun createOrderDetails(yacht: Yacht, orderID: Int) : ArrayList<Details> {
        val details : ArrayList<Details> = arrayListOf()
        for (accessory in yacht.selectedAccessory) {
            val detail = Details()
            detail.accessoryId = accessory.accessoryId1
            detail.orderId = orderID
            details.add(detail)
        }
        return details
    }

    private fun getOrderCardWithAction(yacht: Yacht) : OrderCard {
        val orderCard = OrderCard(yacht)
        orderCard.getInfoButton().setOnAction {
            val orderDescriptionWindow = OrderDescriptionWindow(orderCard.yacht)
            if (!orderCard.yacht.isLocal) {
                orderDescriptionWindow.getCheckBox().isSelected = true
                orderDescriptionWindow.disableCheckBox()
            }
            val stage = Stage()
            orderDescriptionWindow.getCloseButton().setOnAction {
                stage.close()
            }
            orderDescriptionWindow.getCheckBox().setOnAction {
                val order = createUserOrder(orderCard.yacht)
                val contract = createOrderContract(orderCard.yacht, order.orderId)
                val detail = createOrderDetails(orderCard.yacht, order.orderId)
                sender.sendOrder(order, contract, detail)
                orderDescriptionWindow.disableCheckBox()
                orderCard.setOrderState("Оплачено")
                orderCard.yacht.isLocal = false
            }
            stage.scene = Scene(orderDescriptionWindow.getWindow())
            stage.showAndWait()
        }
        return orderCard
    }

    private fun getLoginCardWithAction() : LoginCard {
        val loginCard = LoginCard()
        loginCard.getLoginButton().setOnAction {
            var errorMessage = loginCard.checkInputCorrect()
            if (errorMessage.isEmpty()) {
                val userID = sender.authorization(loginCard.getUsername(), loginCard.getPassword())
                if (userID == -1) {
                    errorMessage = nonExitedUserError
                } else {
                    user = databaseGetter.getUserById(userID)
                    setUsername()
                    isGuest = false
                    loadDirectory()
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
                val errorMessages = registerCard.checkDataCorrect()
                if (errorMessages.isEmpty()) {
                    val auth = registerCard.getAuthFromInputData()
                    val customer = registerCard.getCustomerFromInputData()
                    sender.registration(auth, customer)
                }
                registerCard.setErrorText(errorMessages)
            }
            displayPane.children.add(registerCard.card)
        }
        return loginCard
    }

    private fun loadDirectory() {
        displayPane.children.clear()
        for (boat in databaseGetter.getBoats()) {
            val yacht = Yacht(boat)
            val yachtCard = YachtCard(yacht)

            fun getBuyWindow(){
                val buyWindow = BuyWindow(databaseGetter.getAccessoryByBoatId(yacht.id), yacht)
                val stage = Stage()
                buyWindow.getBuyButton().setOnAction {
                    val vat = databaseGetter.getVat(yacht.vat)
                    val orderedYacht : Yacht = buyWindow.getYacht().copy()
                    orderedYacht.selectedAccessory = buyWindow.getSelectedAccessory()
                    orderedYacht.addPriceFromAccessory()
                    orderedYacht.priceWithVat += (orderedYacht.price * vat.vat1).toInt()
                    val orderCard = getOrderCardWithAction(orderedYacht)
                    orderCard.setOrderState("Ожидает оплаты")
                    addedYacht.add(orderCard)
                    buyWindow.resetPrice()
                    stage.close()
                }
                val scene = Scene(buyWindow.getWindow())
                stage.scene = scene
                stage.showAndWait()
            }

            yachtCard.getBuyButton().setOnAction {
                if (isGuest) {
                    loadLoginMenu()
                } else {
                    getBuyWindow()
                }
            }

            yachtCard.getDescriptionButton().setOnAction {
                displayPane.children.clear()
                val yachtDescriptionCard = YachtDescriptionCard(yacht)
                yachtDescriptionCard.getBuyButton().setOnAction {
                    if (isGuest) {
                        loadLoginMenu()
                    } else {
                        getBuyWindow()
                    }
                }
                displayPane.children.add(yachtDescriptionCard.card)
            }
            displayPane.children.add(yachtCard.card)
        }
    }

    private fun loadOrderCard() {
        val userOrders = databaseGetter.getOrdersByUserId(user.customerId)
        for (order in userOrders) {
            val boat = databaseGetter.getBoatById(order.boatId)
            val contract = databaseGetter.getContractByOrderId(order.orderId)
            val productionProcess = databaseGetter.getOrderProductionProgress(contract.productionProcess)
            val yacht = Yacht(boat)
            val details = databaseGetter.getOrderDetail(order.orderId)
            val accessory = databaseGetter.getAccessoryByDetails(details)
            yacht.selectedAccessory = accessory
            yacht.price = contract.contractTotalPrice
            yacht.priceWithVat = contract.contractTotalPriceIncVat
            yacht.isLocal = false
            val orderCard = getOrderCardWithAction(yacht)
            orderCard.setOrderState( productionProcess.productionProcess1)
            addedYacht.add(orderCard)
        }
    }

    private fun loadOrders() {
        displayPane.children.clear()
        if (!isOrderLoaded) {
            loadOrderCard()
            isOrderLoaded = true
        }
        for (card in addedYacht) {
            displayPane.children.add(card.card)
        }
    }

    private fun loadLoginMenu() {
        displayPane.children.clear()
        displayPane.children.add(getLoginCardWithAction().card)
    }

    fun initialize() {
        profileButton.background = Background(BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        menuPane.background = Background(BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        setUsername()
        loadLoginMenu()

        directoryButton.setOnAction {
            loadDirectory()
        }

        orderButton.setOnAction {
            if (isGuest) {
                loadLoginMenu()
            } else {
                loadOrders()
            }
        }

        profileButton.setOnAction {
            if (isGuest) {
                loadLoginMenu()
            } else {
                displayPane.children.clear()
                val vBox = VBox()


                val telegaButton = object : JFXButton() {
                    init {
                        id = "telegaButton"
                        prefWidth = 350.0
                        prefHeight = 50.0
                        text = "Telegram Chat"
                        font = Font.font("Comic Sans MS", 30.0)
                        VBox.setMargin(this, Insets(200.0, 215.0, 0.0, 215.0))

                    }
                }
                telegaButton.setOnAction{
                    Desktop.getDesktop().browse(URI("https://t.me/world_yachts"))
                }

                val botButton = object : JFXButton() {
                    init {
                        id = "telegaButton"
                        prefWidth = 350.0
                        prefHeight = 50.0
                        text = "Обратная связь"
                        font = Font.font("Comic Sans MS", 30.0)
                        VBox.setMargin(this, Insets(20.0, 215.0, 20.0, 215.0))
                    }
                }
                botButton.setOnAction {
                    Desktop.getDesktop().browse((URI("https://t.me/worldyacht_bot?ask")))
                }
                vBox.children.addAll(telegaButton,botButton)
                displayPane.children.add(vBox)
            }
        }
    }
}