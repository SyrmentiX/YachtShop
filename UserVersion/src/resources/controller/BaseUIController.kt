package resources.controller

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage
import resources.*
import resources.cards.*
import resources.windows.*

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
    lateinit var allOrdersButton : Button

    @FXML
    lateinit var allUserButton : Button

    @FXML
    lateinit var menuPane : VBox

    @FXML
    lateinit var communicationButton: Button

    private fun activateAbility() {
        val auth = databaseGetter.getAuthFromCustomerId(user.customerId)
        if (auth.userType == adminUser) {
            showAdminButton()
        } else if (auth.userType == moderUser) {
            showModerButton()
        }
    }

    private fun setUsername() {
        profileButton.text = user.firstName + " " + user.secondName
    }

    private fun getOrderCardWithAction(yacht: Yacht) : OrderCard {
        val orderCard = OrderCard(yacht)
        orderCard.getInfoButton().setOnAction {
            val orderDescriptionWindow = OrderDescriptionWindow(orderCard.yacht)
            if (!orderCard.yacht.isLocal) {
                orderDescriptionWindow.getCheckBox().isSelected = true
                orderDescriptionWindow.disableCheckBox()
                orderDescriptionWindow.hideCancelButton()
            }
            val stage = Stage()

            orderDescriptionWindow.getCloseButton().setOnAction {
                stage.close()
            }

            orderDescriptionWindow.getCancelButton().setOnAction {
                addedYacht.remove(orderCard)
                displayPane.children.remove(orderCard.card)
                stage.close()
            }

            orderDescriptionWindow.getCheckBox().setOnAction {
                val order = databaseClassParser.createUserOrder(orderCard.yacht, user)
                val contract = databaseClassParser.createOrderContract(orderCard.yacht, order.orderId)
                val detail = databaseClassParser.createOrderDetails(orderCard.yacht, order.orderId)
                sender.sendOrder(order, contract, detail)
                orderDescriptionWindow.disableCheckBox()
                orderDescriptionWindow.hideCancelButton()
                orderCard.setOrderState("В обработке")
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
                    loadUser(userID)
                    setUsername()
                    activateAbility()
                    loadDirectory()
                }
            }
            loginCard.setErrorText(errorMessage)
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
                    displayPane.children.clear()
                    loadLoginMenu()
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
        val updateCard = UpdateCard()
        updateCard.getUpdateButton().setOnAction {
            displayPane.children.clear()
            deleteAllNotLocalYacht()
            isOrderLoaded = false
            loadOrders()
        }
        displayPane.children.add(updateCard.card)
    }

    private fun loadLoginMenu() {
        displayPane.children.clear()
        displayPane.children.add(getLoginCardWithAction().card)
    }

    private fun showModerButton() {
        allOrdersButton.isVisible = true
        allOrdersButton.isDisable = false
    }

    private fun showAdminButton() {
        allOrdersButton.isVisible = true
        allUserButton.isVisible = true
        allOrdersButton.isDisable = false
        allUserButton.isDisable = false
    }

    fun initialize() {
        profileButton.background = Background(BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        menuPane.background = Background(BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY))
        allOrdersButton.isVisible = false
        allUserButton.isVisible = false
        allOrdersButton.isDisable = true
        allUserButton.isDisable = true
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

        communicationButton.setOnAction {
            displayPane.children.clear()
            displayPane.children.add(CommunicationCard().card)
        }

        profileButton.setOnAction {
            if (isGuest) {
                loadLoginMenu()
            } else {
                displayPane.children.clear()
            }
        }

        allUserButton.setOnAction {

        }

        allOrdersButton.setOnAction {

        }

    }
}