package resources.controller

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Color
import resources.Yacht
import resources.adminUser
import resources.async.*
import resources.cards.*
import resources.moderUser
import resources.windows.OrderDescriptionWindow

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

    fun activateAbility() {
        val auth = databaseGetter.getAuthFromCustomerId(user.customerId)
        if (auth.userType == adminUser) {
            showAdminButton()
        } else if (auth.userType == moderUser) {
            showModerButton()
        }
    }

    fun setUsername() {
        profileButton.text = user.firstName + " " + user.secondName
    }

    fun getOrderCardWithAction(yacht: Yacht) : OrderCard {
        val orderCard = OrderCard(yacht)
        stage.setOnCloseRequest {
            isSubWindowShow = false
        }
        orderCard.getInfoButton().setOnAction {
            if (!isSubWindowShow) {
                val orderDescriptionWindow = OrderDescriptionWindow(orderCard.yacht)
                isSubWindowShow = true
                if (!orderCard.yacht.isLocal) {
                    orderDescriptionWindow.getCheckBox().isSelected = true
                    orderDescriptionWindow.disableCheckBox()
                    orderDescriptionWindow.hideCancelButton()
                }

                orderDescriptionWindow.getCloseButton().setOnAction {
                    isSubWindowShow = false
                    stage.close()
                }

                orderDescriptionWindow.getCancelButton().setOnAction {
                    addedYacht.remove(orderCard)
                    displayPane.children.remove(orderCard.card)
                    isSubWindowShow = false
                    stage.close()
                }

                orderDescriptionWindow.getCheckBox().setOnAction {
                    SendOrderAsync(this).execute(Pair(orderCard, orderDescriptionWindow))
                }
                stage.scene = Scene(orderDescriptionWindow.getWindow())
                stage.showAndWait()
            } else {
                stage.toFront()
            }
        }
        return orderCard
    }

    private fun getLoginCardWithAction() : LoginCard {
        val loginCard = LoginCard()
        loginCard.getLoginButton().setOnAction {
            LoginAsync(this).execute(loginCard)
        }

        loginCard.getRegisterButton().setOnAction {
            RegistrationAsync(this).execute(loginCard)
        }
        return loginCard
    }

    fun loadDirectory() {
        DirectoryAsync(this).execute()
    }

    private fun loadOrders() {
        OrdersAsync(this).execute()
    }

    fun loadLoginMenu() {
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
            if (!isLoading) {
                loadDirectory()
            }
        }

        orderButton.setOnAction {
            if (!isLoading) {
                if (isGuest) {
                    loadLoginMenu()
                } else {
                    loadOrders()
                }
            }
        }

        communicationButton.setOnAction {
            if (!isLoading) {
                displayPane.children.clear()
                displayPane.children.add(CommunicationCard().card)
            }
        }

        profileButton.setOnAction {
            if (!isLoading) {
                if (isGuest) {
                    loadLoginMenu()
                } else {
                    displayPane.children.clear()
                }
            }
        }

        allOrdersButton.setOnAction {
            if (!isLoading) {
                ModerOrderLoadAsync(this).execute()
            }
        }

        allUserButton.setOnAction {
            if (!isLoading) {
                UserLoaderAsync(this).execute()
            }
        }
    }
}