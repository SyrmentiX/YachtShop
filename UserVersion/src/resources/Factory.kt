package resources


import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.text.Font
import resources.ImageSlider

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

    fun getDescriptionCard(yacht: Yacht) : VBox{
        return object : VBox() {
            init {
                width = 780.0
                height = 1000.0
                val imageCarousel = object : BorderPane(){
                    init {
                        prefWidth = 780.0
                        prefHeight = 440.0
                        val list : ArrayList<Image> = arrayListOf()
                        for (i in 1..3){
                            list.add(Image("resources\\assert\\yachtPic\\"+ yacht.id.toString() +"\\$i.jpg"))
                        }
                        val slider = ImageSlider(list)
                        children.addAll(slider.getBox())
                    }
                }
                val yachtName = object : Label(){
                    init {
                        prefWidth = 780.0
                        prefHeight = 50.0
                        text = yacht.name
                        font = Font.font("Comic Sans MS", 50.0)
                        alignment = Pos.CENTER
                    }
                }
                val yachtColour = object : Label(){
                    init {
                        prefWidth = 765.0
                        prefHeight = 10.0
                        text = "Цвет: " + yacht.colour
                        font = Font.font("Comic Sans MS", 20.0)
                        alignment = Pos.CENTER_LEFT
                        setMargin(this, Insets(5.0, 0.0, 0.0, 15.0))
                    }
                }
                val yachtWood = object : Label(){
                    init {
                        prefWidth = 765.0
                        prefHeight = 10.0
                        text = "Материал: " + yacht.wood
                        font = Font.font("Comic Sans MS", 20.0)
                        alignment = Pos.CENTER_LEFT
                        setMargin(this, Insets(5.0, 0.0, 0.0, 15.0))
                    }
                }
                val yachtType = object : Label(){
                    init {
                        prefWidth = 765.0
                        prefHeight = 10.0
                        text = "Тип: " + yacht.type
                        font = Font.font("Comic Sans MS", 20.0)
                        alignment = Pos.CENTER_LEFT
                        setMargin(this, Insets(5.0, 0.0, 0.0, 15.0))
                    }
                }
                val yachtNumberOfRowers = object : Label(){
                    init {
                        prefWidth = 765.0
                        prefHeight = 10.0
                        text = "Количество мест для гребли: " + yacht.numberOfRowers.toString()
                        font = Font.font("Comic Sans MS", 20.0)
                        alignment = Pos.CENTER_LEFT
                        setMargin(this, Insets(5.0, 0.0, 0.0, 15.0))
                    }
                }
                val bucketButton = object : Button() {
                    init {
                        id = "buyButton"
                        prefWidth = 350.0
                        prefHeight = 50.0
                        text = "Оформить Заказ"
                        font = Font.font("Comic Sans MS", 30.0)
                        setMargin(this, Insets(30.0, 215.0, 20.0, 215.0))
                    }
                }
                children.addAll(imageCarousel,yachtName,yachtType,yachtWood,yachtColour,yachtNumberOfRowers,bucketButton)
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

    fun getDiscriptionPane() : AnchorPane {
        return object : AnchorPane() {
            //todo ask how that will look
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
}