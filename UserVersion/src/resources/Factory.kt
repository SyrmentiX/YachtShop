package resources

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import resources.Yacht

class Factory {
    fun getYachtCard(yacht : Yacht) : AnchorPane {
        return object : AnchorPane() {
            init {
                width = 390.0
                height = 200.0
                VBox.setMargin(this, Insets(5.0, 0.0, 0.0, 0.0))
                val yachtName = object : Label() {
                    init {
                        prefWidth = 390.0
                        prefHeight = 30.0
                        alignment = Pos.CENTER
                        text = yacht.name
                    }
                }

                val yachtInfo = object : HBox() {
                    init {
                        layoutY = 30.0
                        prefWidth = 390.0
                        prefHeight = 170.0
                        val leftArea = object : VBox() {
                            init {
                                prefWidth = 100.0
                                prefHeight = 170.0
                                val yachtImage = object : ImageView() {
                                    init {
                                        image = yacht.image
                                        fitWidth = 90.0
                                        fitHeight = 130.0
                                        setMargin(this, Insets(20.0, 5.0, 0.0, 5.0))
                                    }
                                }
                                children.add(yachtImage)
                            }
                        }


                        val centerArea = object : VBox() {
                            init {
                                prefWidth = 170.0
                                prefHeight = 170.0
                                val yachtPrice = object : Label() {
                                    init {
                                        text = "Цена: " + yacht.price.toString()
                                    }
                                }
                                val yachtCreator = object : Label() {
                                    init {
                                        text = "Производитель: " + yacht.creator
                                    }
                                }
                                val yachtDateOfCreation = object : Label() {
                                    init {
                                        text = "Дата создания: " + yacht.dateOfCreation
                                    }
                                }
                                val yachtOther = object : Label() {
                                    init {
                                        text = "Другое: " + yacht.other
                                    }
                                }
                                children.addAll(yachtPrice, yachtCreator, yachtDateOfCreation, yachtOther)
                            }
                        }

                        val rightArea = object : VBox() {
                            init {
                                prefWidth = 105.0
                                prefHeight = 170.0
                                val bucketButton = object : Button() {
                                    init {
                                        id = "bucketButton"
                                        prefWidth = 95.0
                                        prefHeight = 40.0
                                        text = "В корзину"
                                        setMargin(this, Insets(30.0, 0.0, 0.0, 5.0))

                                    }
                                }
                                val infoButton = object : Button() {
                                    init {
                                        id = "descriptionButton"
                                        prefWidth = 95.0
                                        prefHeight = 40.0
                                        text = "Подробнее"
                                        setMargin(this, Insets(30.0, 0.0, 0.0, 5.0))
                                    }
                                }

                                children.addAll(bucketButton, infoButton)
                            }
                        }
                        children.addAll(leftArea, centerArea, rightArea)
                    }
                }
                children.addAll(yachtName, yachtInfo)
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
                                        prefHeight = 60.0
                                        font = Font.font("System", 30.0)
                                        text = yacht.name
                                    }
                                }
                                val deleteButton = object : Button() {
                                    init {
                                        id = "deleteButton"
                                        prefWidth = 100.0
                                        prefHeight = 20.0
                                        text = "Убрать"
                                        setMargin(this, Insets(5.0, 0.0, 0.0, 0.0))
                                    }
                                }
                                children.addAll(yachtName, deleteButton)
                            }
                        }

                        val rightArea = object : VBox() {
                            init {
                                prefWidth = 150.0
                                prefHeight = 100.0
                                val yachtPrice = object : Label() {
                                    init {
                                        prefWidth = 150.0
                                        prefHeight = 100.0
                                        font = Font.font("System", 20.0)
                                        alignment = Pos.CENTER
                                        text = yacht.price.toString() + " руб."
                                    }
                                }
                                children.addAll(yachtPrice)
                            }
                        }
                        children.addAll(leftArea, centerArea, rightArea)
                    }
                }
                children.add(yachtInfo)
            }
        }
    }

    fun getBuyPane() : HBox {
        return object : HBox() {
            init {
                prefWidth = 800.0
                prefHeight = 100.0
                val buyButton = object : Button() {
                    init {
                        text = "Купить"
                        prefWidth = 100.0
                        prefHeight = 20.0
                        setMargin(this, Insets(10.0, 0.0, 0.0, 350.0))
                    }
                }
                children.add(buyButton)
            }
        }
    }

    fun getDiscriptionPane() : AnchorPane {
        return object : AnchorPane() {
            //todo ask how that will look
        }
    }
}