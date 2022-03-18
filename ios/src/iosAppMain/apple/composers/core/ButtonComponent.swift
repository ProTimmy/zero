//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public struct ButtonComponent: View {
    @ObservedObject
    private var componentState: ObservableValue<ComponentState>

    public init(component: Component) {
        componentState = ObservableValue<ComponentState>(component.state)
    }

    public var body : some View {
        if let buttonModel = componentState.value.componentModel as? ButtonModel {
            Button(action: buttonModel.onClick) {
                Text(buttonModel.text)
            }
        } else {
            EmptyView()
        }
    }
}
