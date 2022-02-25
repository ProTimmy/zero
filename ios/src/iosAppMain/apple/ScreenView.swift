import Foundation
import SwiftUI
import common

struct ScreenView: View {
    @ObservedObject
    private var model: ObservableValue<ScreenComponentModel>

    private let screen: ScreenComponent

    init(_ screen: ScreenComponent) {
        self.model = ObservableValue(screen.model)
        self.screen = screen
    }
    
    var body: some View {
        ZStack {
            let rootComponents = model.value.rootComponents
            ForEach(rootComponents, id: \.self) { component in
                if let componentModel = screen.getComponentModel(id: component) {
                    ComponentComposer(componentModel: componentModel, componentModelRetriever: screen.getComponentModel)
                }
            }
        }
    }
}
