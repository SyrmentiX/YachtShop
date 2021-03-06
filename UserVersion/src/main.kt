import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.fxml.FXMLLoader
import javafx.scene.Parent

class YachtShop : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("resources/FXML/BaseUI.fxml"))
        val root : Parent = loader.load()
        val scene = Scene(root, 990.0, 590.0 )
        primaryStage.scene = scene
        primaryStage.isResizable = false
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(YachtShop::class.java)
        }
    }
}