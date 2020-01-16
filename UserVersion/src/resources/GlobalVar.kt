package resources

import javafx.scene.image.Image

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
                iconBuy = Image("") // TODO (Илья) - добавить путь к иконке.
            }
            return iconBuy
        }

        fun getIconDescription() : Image {
            if (!::iconDescription.isInitialized) {
                iconDescription = Image("") // TODO (Илья) - добавить путь к иконке.
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