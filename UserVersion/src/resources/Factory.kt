package resources

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import resources.Yacht

class Factory {
    fun get_card(yacht : Yacht) : AnchorPane {
        return object : AnchorPane() {
            init {
                width = 390.0
                height = 200.0
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
                                        prefWidth = 95.0
                                        prefHeight = 40.0
                                        text = "В корзину"
                                        setMargin(this, Insets(30.0, 0.0, 0.0, 5.0))

                                    }
                                }
                                val infoButton = object : Button() {
                                    init {
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
}

//children.addAll(playButton, pauseButton, stopButton)
//children.add(hbox)