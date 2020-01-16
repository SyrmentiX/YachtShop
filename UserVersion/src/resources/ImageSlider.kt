package resources

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.stage.Stage
import java.io.File

class ImageSlider(var images : ArrayList<Image>) {
    private var buttonSize = 30.0
    private var circleSize = 15.0

    private var imageHeight = 400.0
    private var imageWidth = 680.0

    private var buttonStyle = "-fx-background-radius: 50em; " +
            "-fx-min-width: ${buttonSize}px; " +
            "-fx-min-height: ${buttonSize}px; " +
            "-fx-max-width: ${buttonSize}px; "  +
            "-fx-max-height: ${buttonSize}px;"

    private var imageView = ImageView()
    private var pane = BorderPane()
    private var circleBox = HBox()
    private var centerBox = VBox()
    private lateinit var nextButton : Button
    private lateinit var prevButton : Button

    private var numberOfImage = 0

    private var fillCircle = GlobalVar.getIconFillCircle()
    private var hollowCircle = GlobalVar.getIconHollowCircle()

    init {
        if (images.isNotEmpty()) {
            circleBox.alignment = Pos.CENTER

            val prevImage = ImageView(GlobalVar.getIconLeftArrow())
            prevImage.fitWidth = buttonSize
            prevImage.fitHeight = buttonSize

            val nextImage = ImageView(GlobalVar.getIconRightArrow())
            nextImage.fitWidth = buttonSize
            nextImage.fitHeight = buttonSize

            nextButton = Button("", nextImage)
            prevButton = Button("", prevImage)

            nextButton.style = buttonStyle
            prevButton.style = buttonStyle

            nextButton.setOnAction {
                numberOfImage = (numberOfImage + 1) % images.size
                setImage()
            }

            prevButton.setOnAction {
                numberOfImage -= 1
                if (numberOfImage < 0)
                    numberOfImage = images.size - 1
                setImage()
            }

            BorderPane.setAlignment(nextButton, Pos.CENTER)
            BorderPane.setAlignment(prevButton, Pos.CENTER)
            BorderPane.setMargin(prevButton, Insets(0.0, 10.0, 0.0, 10.0))
            BorderPane.setMargin(nextButton, Insets(0.0, 10.0, 0.0, 10.0))
            setImage()
            pane.center = centerBox
            pane.right = nextButton
            pane.left = prevButton
        }
    }

    private fun setImage() {
        imageView = ImageView(images[numberOfImage])
        imageView.fitHeight = imageHeight
        imageView.fitWidth = imageWidth

        circleBox.children.clear()
        images.forEachIndexed { index, _ ->
            val imageView =
                if (index == numberOfImage)
                    ImageView(hollowCircle)
                else
                    ImageView(fillCircle)
            imageView.fitHeight = circleSize
            imageView.fitWidth = circleSize
            HBox.setMargin(imageView, Insets(5.0, 2.5, 5.0, 2.5))
            circleBox.children.add(imageView)
        }
        centerBox.children.clear()
        centerBox.children.addAll(imageView, circleBox)
    }

    fun getBox() : BorderPane {
        return pane
    }
}
