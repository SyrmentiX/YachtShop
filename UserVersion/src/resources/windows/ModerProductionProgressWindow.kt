package resources.windows

import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.layout.VBox
import resources.database.ProductionProcess
import resources.fabric

class ModerProductionProgressWindow(productionProcess: ProductionProcess) {
    private val window = fabric.getModerProductionProgressWindow(productionProcess)

    fun getSaveButton() : Button {
        return window.children[1] as Button
    }

    fun getWindow() : VBox {
        return window
    }

    fun getProductionProgress() : ProductionProcess {
        return (window.children[0] as ChoiceBox<*>).value as ProductionProcess
    }
}