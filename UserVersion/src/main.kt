import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.fxml.FXMLLoader
import javafx.scene.Parent

class MPlayer : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("resources/MainUI.fxml"))
        val root : Parent = loader.load()
        val scene = Scene(root, 320.0, 400.0 )
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MPlayer::class.java)
        }
    }
}