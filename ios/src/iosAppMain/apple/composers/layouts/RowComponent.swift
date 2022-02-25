//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public struct RowComponent: View {
    let model: RowModel
    let componentModelRetriever: (String) -> ComponentModel?

    public init(rowModel: RowModel, componentModelRetriever: @escaping (String) -> ComponentModel?) {
        self.model = rowModel
        self.componentModelRetriever = componentModelRetriever
    }

    public var body : some View {
        HStack {
            ForEach(model.childComponents, id: \.self) { child in
                if let componentModel = componentModelRetriever(child) {
                    ComponentComposer(componentModel: componentModel, componentModelRetriever: componentModelRetriever)
                }
            }
        }
    }
}
