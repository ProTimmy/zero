//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public struct TextComponent: View {
    @ObservedObject
    private var componentState: ObservableValue<ComponentState>

    public init(component: Component) {
        componentState = ObservableValue<ComponentState>(component.state)
    }

    public var body : some View {
        if let textModel = componentState.value.componentModel as? TextModel {
            Text(textModel.text)
        } else {
            EmptyView()
        }
    }
}
