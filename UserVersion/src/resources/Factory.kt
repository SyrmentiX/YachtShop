package resources

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.text.Font
import java.util.function.DoubleConsumer

class Factory {
    fun getYachtCard(yacht : Yacht) : VBox {
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
                        val bucketButton = object : Button() {
                            init {
                                id = "buyButton"
                                prefWidth = 25.0
                                prefHeight = 25.0
                                text = "$"
                                setMargin(this, Insets(5.0, 0.0, 0.0, 5.0))
                            }
                        }
                        val infoButton = object : Button() {
                            init {
                                id = "descriptionButton"
                                prefWidth = 25.0
                                prefHeight = 25.0
                                text = "D"
                                setMargin(this, Insets(5.0, 0.0, 0.0, 5.0))
                            }
                        }
                        children.addAll(yachtName,yachtPrice,bucketButton, infoButton)
                    }
                }
                children.addAll(yachtImage, yachtInfo)
            }
        }
    }

    fun getOrderCard (yacht : Yacht) : AnchorPane {
        return object : AnchorPane() {
            init {
                width = 790.0
                height = 100.0
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
                                        text = "Цена: " + yacht.price + " руб."
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
                                        text = "None"
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

    fun getAccessoryCard(accessory: AccessoryId) : HBox {
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

    fun getAccessoryWindow() : BorderPane {
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

    fun getOrderDescriptionWindow(yacht: Yacht) : BorderPane {
        return object : BorderPane() {
            init {
                this.top = object : Label() {
                    init {
                        text = yacht.name
                        prefWidth = 300.0
                        prefHeight = 30.0
                        alignment = Pos.CENTER
                        setMargin(this, Insets(5.0, 0.0,0.0,0.0))
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
                            }
                        }

                        val metaInfo = object : HBox() {
                            init {
                                prefHeight = 40.0
                                prefWidth = 300.0
                                val payCheck = object : CheckBox() {
                                    init {
                                        text = "Оплата"
                                        prefWidth = 215.0
                                        prefHeight = 40.0
                                        setMargin(this, Insets(5.0,0.0,0.0,5.0))
                                    }
                                }

                                val closeButton = object : Button() {
                                    init {
                                        text = "Закрыть"
                                        prefWidth = 70.0
                                        prefHeight = 20.0
                                        setMargin(this, Insets(10.0,0.0,0.0,0.0))
                                    }
                                }
                                this.children.addAll(payCheck, closeButton)
                            }
                        }
                        this.children.addAll(totalPrice, metaInfo)
                    }
                }
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

                val observableList = FXCollections.observableArrayList(DatabaseGetter().getAvailableDocumentTypes())
                val choiceBox = object : ChoiceBox<DocumentName>(observableList) {
                    init {
                        maxWidth = 400.0
                        minWidth = 400.0
                        maxHeight = 30.0
                        minHeight = 30.0
                        setMargin(this, Insets(20.0, 0.0, 0.0, 200.0))
                        value = observableList.first()
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

                this.children.addAll(addressField, cityField, choiceBox, documentField)

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
}
