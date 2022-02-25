//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

@ViewBuilder
public func ComponentComposer(model: ComponentModel, componentModelRetriever: @escaping (String) -> ComponentModel) -> some View {
    switch model {
    case let coreModel as CoreComponentModel:
        CoreComponentComposer(model: coreModel)
    case let layoutModel as LayoutComponentModel:
        LayoutComponentComposer(model: layoutModel, componentModelRetriever: componentModelRetriever)
    default:
        EmptyView()
    }
}

@ViewBuilder
func CoreComponentComposer(model: CoreComponentModel) -> some View {
    switch model {
    case let textModel as TextModel:
        TextComponent(textModel)
    case let buttonModel as ButtonModel:
        ButtonComponent(buttonModel)
    default:
        EmptyView()
    }
}

@ViewBuilder
func LayoutComponentComposer(model: ComponentModel, componentModelRetriever: @escaping (String) -> ComponentModel) -> some View {
    switch model {
    case let boxModel as BoxModel:
        BoxComponent(boxModel: boxModel, componentModelRetriever: componentModelRetriever)
    case let rowModel as RowModel:
        RowComponent(rowModel: rowModel, componentModelRetriever: componentModelRetriever)
    case let columnModel as ColumnModel:
        ColumnComponent(columnModel: columnModel, componentModelRetriever: componentModelRetriever)
    default:
        EmptyView()
    }
}
