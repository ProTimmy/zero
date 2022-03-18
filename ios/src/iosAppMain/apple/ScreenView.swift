import Foundation
import SwiftUI
import common

struct ScreenView: View {
    @ObservedObject
    private var model: ObservableValue<ScreenModel>

    private let screen: Screen

    init(_ screen: Screen) {
        self.model = ObservableValue<ScreenModel>(screen.model)
        self.screen = screen
    }
    
    var body: some View {
        ZStack {
            let rootComponents = model.value.rootComponents as [String]
            ForEach(rootComponents, id: \.self) { componentId in
                ComponentComposer(id: componentId, componentModelRetriever: screen.getComponentModel)
            }
        }

        if (model.value.rootComponents.isEmpty) {
            Button(action: screen.doInitDemo) {
                Text("Click Me!")
            }
        }
    }
}
