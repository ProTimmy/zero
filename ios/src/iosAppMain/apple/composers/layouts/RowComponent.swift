//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public struct RowComponent: View {
    @ObservedObject
    private var componentState: ObservableValue<ComponentState>
    private let componentModelRetriever: (String) -> Component?

    public init(component: Component, componentModelRetriever: @escaping (String) -> Component?) {
        self.componentModelRetriever = componentModelRetriever

        componentState = ObservableValue<ComponentState>(component.state)
    }

    public var body : some View {
        if let rowModel = componentState.value.componentModel as? RowModel {
            HStack {
                ForEach(rowModel.childComponents, id: \.self) { componentId in
                    ComponentComposer(id: componentId, componentModelRetriever: componentModelRetriever)
                }
            }
        } else {
            EmptyView()
        }
    }
}
