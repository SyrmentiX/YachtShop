package resources

import com.jfoenix.controls.JFXButton
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import resources.database.*
import java.awt.Desktop
import java.net.URI


class Factory {
    fun getYachtCard(yacht: Yacht): VBox {
        return object : VBox() {
            init {
                width = 390.0
                height = 250.0
                val yachtImage = object : ImageView() {
                    init {
                        image = yacht.image
                        fitWidth = 380.0
                        fitHeight = 210.0
                        setMargin(this, Insets(10.0, 5.0, 0.0, 5.0))
                    }
                }

                val yachtInfo = object : HBox() {
                    init {
                        prefWidth = 390.0
                        prefHeight = 30.0
                        val yachtName = object : Label() {
                            init {
                                prefWidth = 145.0
                                text = yacht.name
                                font = Font.font("Franklin Gothic Medium", 20.0)
                                setMargin(this, Insets(5.0, 0.0, 0.0, 10.0))
                            }
                        }
                        val yachtPrice = object : Label() {
                            init {
                                prefWidth = 160.0
                                text = "Цена: " + yacht.price.toString()
                                font = Font.font("Franklin Gothic Medium", 20.0)
                                setMargin(this, Insets(5.0, 5.0, 0.0, 5.0))
                            }
                        }
                        val buyIcon = ImageView(GlobalVar.getIconBuy())
                        buyIcon.fitHeight = 25.0
                        buyIcon.fitWidth = 25.0
                        val bucketButton = object : Button("", buyIcon) {
                            init {
                                id = "buyButton"
                                prefWidth = 25.0
                                prefHeight = 25.0
                                style = "-fx-background-radius: 50em; " +
                                        "-fx-min-width: 25px; " +
                                        "-fx-min-height: 25px; " +
                                        "-fx-max-width: 25px; " +
                                        "-fx-max-height: 25px;"
                                setMargin(this, Insets(5.0, 0.0, 0.0, 5.0))
                            }
                        }
                        val descriptionIcon = ImageView(GlobalVar.getIconDescription())
                        descriptionIcon.fitHeight = 25.0
                        descriptionIcon.fitWidth = 25.0
                        val infoButton = object : Button("", descriptionIcon) {
                            init {
                                id = "descriptionButton"
                                prefWidth = 25.0
                                prefHeight = 25.0
                                style = "-fx-background-radius: 50em; " +
                                        "-fx-min-width: 25px; " +
                                        "-fx-min-height: 25px; " +
                                        "-fx-max-width: 25px; " +
                                        "-fx-max-height: 25px;"
                                setMargin(this, Insets(5.0, 0.0, 0.0, 5.0))
                            }
                        }
                        children.addAll(yachtName, yachtPrice, bucketButton, infoButton)
                    }
                }
                children.addAll(yachtImage, yachtInfo)
            }
        }
    }

    fun getDescriptionCard(yacht: Yacht): VBox {
        return object : VBox() {
            init {
                width = 780.0
                height = 1000.0
                val imageCarousel = object : BorderPane() {
                    init {
                        prefWidth = 780.0
                        prefHeight = 440.0
                        val slider = ImageSlider(GlobalVar.getBoatImages(yacht.id))
                        children.addAll(slider.getBox())
                    }
                }
                val yachtName = object : Label() {
                    init {
                        prefWidth = 780.0
                        prefHeight = 50.0
                        text = yacht.name
                        font = Font.font("Comic Sans MS", 50.0)
                        alignment = Pos.CENTER
                    }
                }
                val yachtColour = object : Label() {
                    init {
                        prefWidth = 765.0
                        prefHeight = 10.0
                        text = "Цвет: " + yacht.colour
                        font = Font.font("Comic Sans MS", 20.0)
                        alignment = Pos.CENTER_LEFT
                        setMargin(this, Insets(5.0, 0.0, 0.0, 15.0))
                    }
                }
                val yachtWood = object : Label() {
                    init {
                        prefWidth = 765.0
                        prefHeight = 10.0
                        text = "Материал: " + yacht.wood
                        font = Font.font("Comic Sans MS", 20.0)
                        alignment = Pos.CENTER_LEFT
                        setMargin(this, Insets(5.0, 0.0, 0.0, 15.0))
                    }
                }
                val yachtType = object : Label() {
                    init {
                        prefWidth = 765.0
                        prefHeight = 10.0
                        text = "Тип: " + yacht.type
                        font = Font.font("Comic Sans MS", 20.0)
                        alignment = Pos.CENTER_LEFT
                        setMargin(this, Insets(5.0, 0.0, 0.0, 15.0))
                    }
                }
                val yachtNumberOfRowers = object : Label() {
                    init {
                        prefWidth = 765.0
                        prefHeight = 10.0
                        text = "Количество мест для гребли: " + yacht.numberOfRowers.toString()
                        font = Font.font("Comic Sans MS", 20.0)
                        alignment = Pos.CENTER_LEFT
                        setMargin(this, Insets(5.0, 0.0, 0.0, 15.0))
                    }
                }
                val bucketButton = object : JFXButton() {
                    init {
                        id = "buyButton"
                        prefWidth = 350.0
                        prefHeight = 50.0
                        style = "-jfx-button-type: RAISED;" +
                                "     -fx-background-color: #4da6ff;" +
                                "     -fx-text-fill: white;"
                        text = "Оформить Заказ"
                        font = Font.font("Comic Sans MS", 30.0)
                        setMargin(this, Insets(30.0, 215.0, 20.0, 215.0))
                    }
                }

                val commentPlace = object : VBox() {
                    init {
                        setMargin(this, Insets(5.0, 0.0,0.0,0.0))
                        val label = Label()
                        label.text = "Test comment"
                        this.children.add(label)
                    }
                }
                children.addAll(
                    imageCarousel,
                    yachtName,
                    yachtType,
                    yachtWood,
                    yachtColour,
                    yachtNumberOfRowers,
                    bucketButton,
                    commentPlace
                )
            }
        }
    }

    fun getOrderCard(yacht: Yacht): AnchorPane {
        return object : AnchorPane() {
            init {
                width = 790.0
                height = 100.0
                prefWidth = 786.0
                prefHeight = 150.0
                border = Border(BorderStroke(
                    Color.RED,
                    Color.RED,
                    Color.BLACK,
                    Color.RED,
                    BorderStrokeStyle.NONE,
                    BorderStrokeStyle.NONE,
                    BorderStrokeStyle.SOLID,
                    BorderStrokeStyle.NONE,
                    CornerRadii.EMPTY,
                    BorderWidths(2.0),
                    Insets.EMPTY
                )
                )
                val yachtInfo = object : HBox() {
                    init {
                        prefWidth = 790.0
                        prefHeight = 100.0
                        val leftArea = object : VBox() {
                            init {
                                prefWidth = 150.0
                                prefHeight = 100.0
                                val yachtImage = object : ImageView() {
                                    init {
                                        image = yacht.image
                                        fitWidth = 100.0
                                        fitHeight = 90.0
                                        setMargin(this, Insets(10.0, 0.0, 0.0, 25.0))
                                    }
                                }
                                children.add(yachtImage)
                            }
                        }

                        val centerArea = object : VBox() {
                            init {
                                prefWidth = 490.0
                                prefHeight = 100.0
                                val yachtName = object : Label() {
                                    init {
                                        prefWidth = 400.0
                                        prefHeight = 60.0
                                        font = Font.font("System", 30.0)
                                        text = yacht.name
                                    }
                                }
                                val yachtPrice = object : Label() {
                                    init {
                                        prefWidth = 400.0
                                        prefHeight = 40.0
                                        text = "Цена: " + yacht.priceWithVat + " руб."
                                        font = Font.font("Comic Sans MS", 20.0)
                                        setMargin(this, Insets(5.0, 0.0, 0.0, 0.0))
                                    }
                                }
                                children.addAll(yachtName, yachtPrice)
                            }
                        }

                        val rightArea = object : VBox() {
                            init {
                                prefWidth = 150.0
                                prefHeight = 100.0
                                val orderState = object : Label() {
                                    init {
                                        prefWidth = 150.0
                                        prefHeight = 60.0
                                        alignment = Pos.CENTER
                                    }
                                }
                                val detailsButton = object : Button() {
                                    init {
                                        text = "Подробнее"
                                        prefWidth = 80.0
                                        prefHeight = 20.0
                                        setMargin(this, Insets(5.0, 0.0, 0.0, 35.0))
                                    }
                                }
                                children.addAll(orderState, detailsButton)
                            }
                        }
                        children.addAll(leftArea, centerArea, rightArea)
                    }
                }
                children.add(yachtInfo)
            }
        }
    }

    fun getUpdateOrderCard() : VBox {
        return object : VBox() {
            init {
                width = 790.0
                height = 100.0
                val updateButton = object : JFXButton() {
                    init {
                        id = "updateButton"
                        prefWidth = 350.0
                        prefHeight = 50.0
                        style = "-jfx-button-type: RAISED;" +
                                "     -fx-background-color: #4da6ff;" +
                                "     -fx-text-fill: white;"
                        text = "Обновить"
                        font = Font.font("Comic Sans MS", 30.0)
                        setMargin(this, Insets(30.0, 215.0, 20.0, 215.0))
                    }
                }
                this.children.add(updateButton)
            }
        }
    }

    fun getAccessoryCard(accessory: AccessoryId): HBox {
        return object : HBox() {
            init {
                prefWidth = 295.0
                prefHeight = 20.0
                val checkBox = object : CheckBox() {
                    init {
                        setMargin(this, Insets(10.0, 0.0, 0.0, 20.0))
                        prefWidth = 195.0
                        prefHeight = 20.0
                        text = accessory.accName
                    }
                }
                val accessoryPrice = object : Label() {
                    init {
                        text = accessory.price.toString()
                        setMargin(this, Insets(12.0, 0.0, 0.0, 15.0))
                    }
                }
                children.addAll(checkBox, accessoryPrice)
            }
        }
    }

    fun getLoginCard() : VBox {
        return object : VBox() {
            init {
                prefHeight = 600.0
                prefWidth = 800.0

                val shopName = object : Label() {
                    init {
                        prefWidth = 400.0
                        prefHeight = 40.0
                        alignment = Pos.CENTER
                        setMargin(this, Insets(150.0, 0.0, 0.0, 200.0))
                        font = Font.font("Comic Sans MS", 24.0)
                        text = "Наш магазин 'У Пупы и Лупы'"
                    }
                }

                val loginField = object : TextField() {
                    init {
                        minWidth = 400.0
                        minHeight = 30.0
                        maxWidth = 400.0
                        maxHeight = 30.0
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                        font = Font.font("Comic Sans MS", 14.0)
                        promptText = "Логин"
                    }
                }

                val passwordField = object : PasswordField() {
                    init {
                        minWidth = 400.0
                        minHeight = 30.0
                        maxWidth = 400.0
                        maxHeight = 30.0
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                        font = Font.font("Comic Sans MS", 14.0)
                        promptText = "Пароль"
                    }
                }

                val buttonBox = object : HBox() {
                    init {
                        minWidth = 400.0
                        minHeight = 40.0
                        maxWidth = 400.0
                        maxHeight = 40.0
                        VBox.setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))

                        val loginButton = object : Button() {
                            init {
                                prefWidth = 150.0
                                prefHeight = 40.0
                                text = "Войти"
                            }
                        }

                        val registerButton = object : Button() {
                            init {
                                prefWidth = 150.0
                                prefHeight = 40.0
                                text = "Регистрация"
                                setMargin(this, Insets(0.0, 0.0, 0.0, 100.0))
                            }
                        }
                        this.children.addAll(loginButton, registerButton)
                    }
                }

                val errorMessageLabel = object : Label() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        alignment = Pos.CENTER
                        font = Font.font("Comic Sans MS", 20.0)
                        text = ""
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                    }
                }
                this.children.addAll(shopName, loginField, passwordField, buttonBox, errorMessageLabel)
            }
        }
    }

    fun getRegistrationCard() : VBox {
        return object : VBox() {
            init {
                prefHeight = 600.0
                prefWidth = 800.0
                val cardNameLabel = object : Label() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        alignment = Pos.CENTER
                        font = Font.font("Comic Sans MS", 20.0)
                        text = "Регистрация"
                        setMargin(this, Insets(10.0, 0.0, 0.0, 200.0))
                    }
                }

                val loginField = object : TextField() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        promptText = "Логин"
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                    }
                }

                val emailField = object : TextField() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        promptText = "Почта"
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                    }
                }

                val passwordField = object : PasswordField() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        promptText = "Пароль"
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                    }
                }

                this.children.addAll(cardNameLabel, loginField, emailField, passwordField)

                val usernameBox = object : HBox() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        VBox.setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                        val firstNameField = object : TextField() {
                            init {
                                prefWidth = 190.0
                                prefHeight = 30.0
                                promptText = "Имя"
                            }
                        }

                        val secondNameField = object : TextField() {
                            init {
                                prefWidth = 190.0
                                prefHeight = 30.0
                                promptText = "Фамилия"
                                setMargin(this, Insets(0.0, 0.0, 0.0, 20.0))
                            }
                        }
                        this.children.addAll(firstNameField, secondNameField)
                    }
                }

                val metadataBox = object : HBox() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        VBox.setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                        val dateOfBirth = object : DatePicker() {
                            init {
                                prefWidth = 190.0
                                prefHeight = 30.0
                                promptText = "Дата рождения"
                            }
                        }

                        val phoneField = object : TextField() {
                            init {
                                prefWidth = 190.0
                                prefHeight = 30.0
                                promptText = "Номер телефона"
                                setMargin(this, Insets(0.0, 0.0, 0.0, 20.0))
                            }
                        }
                        this.children.addAll(dateOfBirth, phoneField)
                    }
                }

                this.children.addAll(usernameBox, metadataBox)

                val addressField = object : TextField() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        promptText = "Адрес"
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                    }
                }

                val cityField = object : TextField() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        promptText = "Город"
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                    }
                }

                val documentList = FXCollections.observableArrayList(DatabaseGetter().getAvailableDocumentTypes())
                val documentBox = object : ChoiceBox<DocumentName>(documentList) {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                        value = documentList.first()
                    }
                }

                val documentField = object : TextField() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        promptText = "Номер и серия документа"
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                    }
                }

                this.children.addAll(addressField, cityField, documentBox, documentField)

                val buttonBox = object : HBox() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        VBox.setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                        val cancelButton = object : Button() {
                            init {
                                prefWidth = 130.0
                                prefHeight = 30.0
                                text = "Отмена"
                            }
                        }

                        val registerButton = object : Button() {
                            init {
                                prefWidth = 130.0
                                prefHeight = 30.0
                                text = "Зарегистрироваться"
                                setMargin(this, Insets(0.0, 0.0, 0.0, 140.0))
                            }
                        }
                        this.children.addAll(cancelButton, registerButton)
                    }
                }

                val errorMessageLabel = object : Label() {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        alignment = Pos.CENTER
                        font = Font.font("Comic Sans MS", 20.0)
                        text = ""
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                    }
                }

                this.children.addAll(buttonBox, errorMessageLabel)
            }
        }
    }

    fun getAccessoryWindow(): BorderPane {
        return object : BorderPane() {
            init {
                prefWidth = 300.0
                prefHeight = 500.0
                val center = object : VBox() {
                    init {
                        prefHeight = 400.0
                        prefWidth = 300.0
                        setMargin(this, Insets(20.0, 0.0, 0.0, 5.0))
                    }
                }
                this.center = center

                val bottom = object : HBox() {
                    init {
                        prefWidth = 300.0
                        prefHeight = 40.0
                        val totalPrice = object : Label() {
                            init {
                                alignment = Pos.CENTER
                                prefWidth = 230.0
                                prefHeight = 40.0
                            }
                        }
                        val buyButton = object : Button() {
                            init {
                                prefWidth = 60.0
                                prefHeight = 20.0
                                setMargin(this, Insets(10.0, 0.0, 0.0, 5.0))
                                text = "Buy"
                            }
                        }
                        children.addAll(totalPrice, buyButton)
                    }
                }
                this.bottom = bottom
            }
        }
    }

    fun getOrderDescriptionWindow(yacht: Yacht): BorderPane {
        return object : BorderPane() {
            init {
                this.top = object : Label() {
                    init {
                        text = yacht.name
                        prefWidth = 300.0
                        prefHeight = 30.0
                        alignment = Pos.CENTER
                        setMargin(this, Insets(5.0, 0.0, 0.0, 0.0))
                    }
                }

                this.center = object : VBox() {
                    init {
                        prefWidth = 100.0
                        prefHeight = 370.0
                    }
                }

                this.bottom = object : VBox() {
                    init {
                        prefHeight = 75.0
                        prefWidth = 300.0
                        val totalPrice = object : Label() {
                            init {
                                prefWidth = 295.0
                                prefHeight = 30.0
                                alignment = Pos.CENTER_RIGHT
                                text = "Общая сумма (с НДС): " + yacht.priceWithVat + " руб."
                            }
                        }

                        val metaInfo = object : HBox() {
                            init {
                                prefHeight = 40.0
                                prefWidth = 300.0
                                val payCheck = object : CheckBox() {
                                    init {
                                        text = "Оплата"
                                        prefWidth = 135.0
                                        prefHeight = 40.0
                                        setMargin(this, Insets(5.0, 0.0, 0.0, 5.0))
                                    }
                                }

                                val cancelButton = object : Button() {
                                    init {
                                        text = "Отменить"
                                        prefWidth = 70.0
                                        prefHeight = 20.0
                                        setMargin(this, Insets(10.0, 5.0, 0.0, 5.0))
                                    }
                                }

                                val closeButton = object : Button() {
                                    init {
                                        text = "Закрыть"
                                        prefWidth = 70.0
                                        prefHeight = 20.0
                                        setMargin(this, Insets(10.0, 0.0, 0.0, 0.0))
                                    }
                                }
                                this.children.addAll(payCheck,cancelButton, closeButton)
                            }
                        }
                        this.children.addAll(totalPrice, metaInfo)
                    }
                }
            }
        }
    }

    fun getCommunicationCard() : VBox {
        return object : VBox() {
            init {
                val telegramButton = object : JFXButton() {
                    init {
                        id = "telegramButton"
                        prefWidth = 350.0
                        prefHeight = 50.0
                        text = "Telegram Chat"
                        font = Font.font("Comic Sans MS", 30.0)
                        setMargin(this, Insets(200.0, 215.0, 0.0, 215.0))
                    }
                }
                telegramButton.setOnAction{
                    Desktop.getDesktop().browse(URI("https://t.me/world_yachts"))
                }
                val botButton = object : JFXButton() {
                    init {
                        id = "telegramButton"
                        prefWidth = 350.0
                        prefHeight = 50.0
                        text = "Обратная связь"
                        font = Font.font("Comic Sans MS", 30.0)
                        setMargin(this, Insets(20.0, 215.0, 20.0, 215.0))
                    }
                }
                botButton.setOnAction {
                    Desktop.getDesktop().browse((URI("https://t.me/worldyacht_bot?ask")))
                }
                this.children.addAll(telegramButton, botButton)
            }
        }
    }

    fun getModerOrderCard(orders: Orders, contract: Contract) : VBox {
        return object : VBox() {
            init {
                prefWidth = 786.0
                prefHeight = 150.0
                spacing = 0.0
                border = Border(BorderStroke(
                            Color.RED,
                            Color.RED,
                            Color.BLACK,
                            Color.RED,
                            BorderStrokeStyle.NONE,
                            BorderStrokeStyle.NONE,
                            BorderStrokeStyle.SOLID,
                            BorderStrokeStyle.NONE,
                            CornerRadii.EMPTY,
                            BorderWidths(2.0),
                            Insets.EMPTY
                        )
                    )
                val orderInfo = object : HBox() {
                    init {
                        prefWidth = 790.0
                        prefHeight= 50.0
                        val yachtName = object : Label() {
                            init {
                                prefWidth = 266.0
                                prefHeight = 50.0
                                text = "Лодка: " +  DatabaseGetter().getBoatById(orders.boatId).model
                                alignment = Pos.CENTER
                            }
                        }
                        val contractPrice = object : Label() {
                            init {
                                prefWidth = 260.0
                                prefHeight = 50.0
                                text = "Цена: " +  contract.contractTotalPriceIncVat.toString() + endSentence
                                alignment = Pos.CENTER
                            }
                        }

                        val productionProgressLabel = object : Label() {
                            init {
                                prefWidth = 260.0
                                prefHeight = 50.0
                                alignment = Pos.CENTER
                                text = "Состояние: " +  DatabaseGetter().getOrderProductionProgress(contract.productionProcess).productionProcess1
                            }
                        }

                        this.children.addAll(yachtName, contractPrice, productionProgressLabel)
                    }
                }

                val userInfo = object : HBox() {
                    init {
                        prefWidth = 786.0
                        prefHeight= 50.0
                        val customer = DatabaseGetter().getCustomerFromOrder(orders)
                        val usernameLabel = object : Label() {
                            init {
                                prefWidth = 256.0
                                prefHeight = 50.0
                                text = "Заказчик: " + customer.firstName + " " + customer.secondName
                                alignment = Pos.CENTER
                            }
                        }
                        val cityLabel = object : Label() {
                            init {
                                prefHeight = 50.0
                                prefWidth = 160.0
                                text = "Город: " + orders.city
                                alignment = Pos.CENTER
                            }
                        }
                        val addressLabel = object : Label() {
                            init {
                                prefWidth = 250.0
                                prefHeight = 50.0
                                text = "Адресс: " +  orders.deliveryAddress
                                alignment = Pos.CENTER
                            }
                        }
                        val dateOrderLabel = object : Label() {
                            init {
                                prefWidth = 120.0
                                prefHeight = 50.0
                                text = "Дата: " + orders.date.subSequence(0, 10)
                                alignment = Pos.CENTER
                            }
                        }
                        this.children.addAll(usernameLabel, cityLabel, addressLabel, dateOrderLabel)
                    }
                }

                val buttonBox = object : HBox() {
                    init {
                        prefWidth = 786.0
                        prefHeight = 50.0
                        val editButton = object : Button() {
                            init {
                                prefWidth = 100.0
                                prefHeight = 20.0
                                setMargin(this, Insets(10.0, 0.0, 0.0, 293.0))
                                text = "Редактировать"
                            }
                        }
                        val deleteButton = object : Button() {
                            init {
                                prefWidth = 100.0
                                prefHeight = 20.0
                                setMargin(this, Insets(10.0, 0.0, 0.0, 10.0))
                                text = "Удалить"
                            }
                        }
                        this.children.addAll(editButton, deleteButton)
                    }
                }
                this.children.addAll(orderInfo, userInfo, buttonBox)
            }
        }
    }

    fun getModerProductionProgressWindow(productionProcess: ProductionProcess) : VBox {
        return object : VBox() {
            init {
                prefWidth = 200.0
                prefHeight = 90.0
                val productionProgressList = FXCollections.observableArrayList(DatabaseGetter().getProductionProgress())
                val productionProgress = object : ChoiceBox<ProductionProcess>(productionProgressList) {
                    init {
                        prefWidth = 200.0
                        prefHeight = 20.0
                        alignment = Pos.CENTER
                        setMargin(this, Insets(10.0, 0.0, 0.0, 0.0))
                        value = productionProgressList[productionProcess.productionProcessId - 1]
                    }
                }

                val saveButton = object : Button() {
                    init {
                        text = "Сохранить"
                        prefWidth = 100.0
                        prefHeight = 20.0
                        setMargin(this, Insets(20.0, 0.0, 0.0, 10.0))
                    }
                }
                this.children.addAll(productionProgress, saveButton)
            }
        }
    }

    fun getUserCard(customers: Customers, auth: Auth) : VBox {
        return object : VBox() {
            init {
                prefWidth = 800.0
                prefHeight = 150.0
                border = Border(BorderStroke(
                    Color.RED,
                    Color.RED,
                    Color.BLACK,
                    Color.RED,
                    BorderStrokeStyle.NONE,
                    BorderStrokeStyle.NONE,
                    BorderStrokeStyle.SOLID,
                    BorderStrokeStyle.NONE,
                    CornerRadii.EMPTY,
                    BorderWidths(2.0),
                    Insets.EMPTY
                )
                )
                val upperUserInfo = object : HBox() {
                    init {
                        prefWidth = 786.0
                        prefHeight = 50.0
                        val loginLabel = object : Label() {
                            init {
                                prefWidth = 256.0
                                prefHeight = 50.0
                                text = "Логин: "+ auth.username
                                alignment = Pos.CENTER
                            }
                        }
                        val firstNameLabel = object : Label() {
                            init {
                                prefWidth = 250.0
                                prefHeight = 50.0
                                text = "Имя: " + customers.firstName
                                alignment = Pos.CENTER
                            }
                        }
                        val secondNameLabel = object : Label() {
                            init {
                                prefWidth = 250.0
                                prefHeight = 50.0
                                text = "Фамилия: " + customers.secondName
                                alignment = Pos.CENTER
                            }
                        }
                        this.children.addAll(loginLabel, firstNameLabel, secondNameLabel)
                    }
                }
                val lowerUserInfo = object : HBox() {
                    init {
                        prefWidth = 786.0
                        prefHeight= 50.0
                        val phoneLabel = object : Label() {
                            init {
                                prefWidth = 250.0
                                prefHeight = 50.0
                                text = "Номер телефона: " + customers.phoneNumber
                                alignment = Pos.CENTER
                            }
                        }
                        val cityLabel = object : Label() {
                            init {
                                prefHeight = 50.0
                                prefWidth = 160.0
                                text = "Город: " + customers.city
                                alignment = Pos.CENTER
                            }
                        }
                        val addressLabel = object : Label() {
                            init {
                                prefWidth = 250.0
                                prefHeight = 50.0
                                text = "Адресс: " +  customers.address
                                alignment = Pos.CENTER
                            }
                        }
                        val dateOrderLabel = object : Label() {
                            init {
                                prefWidth = 120.0
                                prefHeight = 50.0
                                text = "Дата: " + customers.dateOfBirth.subSequence(0, 10)
                                alignment = Pos.CENTER
                            }
                        }
                        this.children.addAll(phoneLabel, cityLabel, addressLabel, dateOrderLabel)
                    }
                }

                val buttonBox = object : HBox() {
                    init {
                        prefWidth = 786.0
                        prefHeight = 50.0
                        val deleteButton = object : Button() {
                            init {
                                prefWidth = 100.0
                                prefHeight = 20.0
                                setMargin(this, Insets(10.0, 0.0, 0.0, 350.0))
                                text = "Удалить"
                            }
                        }
                        this.children.addAll(deleteButton)
                    }
                }
                this.children.addAll(upperUserInfo, lowerUserInfo, buttonBox)
            }
        }
    }

    fun getLoadIndicator() : ProgressIndicator {
        return object : ProgressIndicator() {
            init {
                prefWidth = 800.0
                prefHeight = 580.0
            }
        }
    }
}