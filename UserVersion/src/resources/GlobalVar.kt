package resources

import javafx.scene.image.Image

val fabric : Factory = Factory()
const val usernameError : String = "Введите логин"
const val passwordError : String = "Введите пароль"
const val dateError : String = "Введите дату"
const val emailError : String = "Введите почту"
const val firstNameError : String = "Введите имя"
const val secondNameError : String = "Введите фамилию"
const val phoneError : String = "Введите номер телефона"
const val addressError : String = "Введите адрес"
const val cityError : String = "Введите город"
const val documentError : String = "Введите номер и серию документа"
const val beginSentence = "Цена: "
const val endSentence = " руб."
const val commonUser = 1
const val adminUser = 2
const val moderUser = 3


class GlobalVar {
    companion object {
        private var imageMap : MutableMap<Int, ArrayList<Image>> = mutableMapOf()
        private lateinit var iconBuy : Image
        private lateinit var iconDescription : Image
        private lateinit var iconLeftArrow : Image
        private lateinit var iconRightArrow : Image
        private lateinit var iconFillCircle : Image
        private lateinit var iconHollowCircle : Image

        fun getBoatImages(boatId : Int) : ArrayList<Image> {
            if (!imageMap.containsKey(boatId)) {
                imageMap[boatId] = arrayListOf()
                for (i in 1..3) {
                    imageMap[boatId]?.add(Image("resources\\assert\\yachtPic\\$boatId\\$i.jpg"))
                }
            }
            return imageMap[boatId]!!
        }

        fun getIconBuy() : Image {
            if (!::iconBuy.isInitialized) {
                iconBuy = Image("resources\\assert\\icons\\buyIcon.png")
            }
            return iconBuy
        }

        fun getIconDescription() : Image {
            if (!::iconDescription.isInitialized) {
                iconDescription = Image("resources\\assert\\icons\\descriptionIcon.png")
            }
            return iconDescription
        }

        fun getIconLeftArrow() : Image {
            if (!::iconLeftArrow.isInitialized) {
                iconLeftArrow = Image("resources\\assert\\icons\\left.png")
            }
            return iconLeftArrow
        }

        fun getIconRightArrow() : Image {
            if (!::iconRightArrow.isInitialized) {
                iconRightArrow = Image("resources\\assert\\icons\\right.png")
            }
            return iconRightArrow
        }

        fun getIconFillCircle() : Image {
            if (!::iconFillCircle.isInitialized) {
                iconFillCircle = Image("resources\\assert\\icons\\fill.png")
            }
            return iconFillCircle
        }

        fun getIconHollowCircle() : Image {
            if (!::iconHollowCircle.isInitialized) {
                iconHollowCircle = Image("resources\\assert\\icons\\hollow.png")
            }
            return iconHollowCircle
        }
    }
}