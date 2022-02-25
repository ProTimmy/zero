//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public struct ColumnComponent: View {
    let model: ColumnModel
    let componentModelRetriever: (String) -> ComponentModel?

    public init(columnModel: ColumnModel, componentModelRetriever: @escaping (String) -> ComponentModel?) {
        self.model = columnModel
        self.componentModelRetriever = componentModelRetriever
    }

    public var body : some View {
        VStack {
            ForEach(model.childComponents, id: \.self) { child in
                if let componentModel = componentModelRetriever(child) {
                    ComponentComposer(componentModel: componentModel, componentModelRetriever: componentModelRetriever)
                }
            }
        }
    }
}
