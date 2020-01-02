package resources

import javafx.scene.layout.AnchorPane
import resources.Factory
import resources.Yacht
val fabric : Factory = Factory()

class YachtCard(yacht : Yacht) {
    var card : AnchorPane = fabric.get_card(yacht)
    var yacht : Yacht = yacht
}