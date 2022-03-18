//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public func ComponentComposer(id: String, componentModelRetriever: @escaping (String) -> Component?) -> AnyView {
    let component = componentModelRetriever(id)

    if let model = component?.state.value.componentModel {
        switch model {
        case let coreModel as CoreComponentModel:
            return CoreComponentComposer(model: coreModel, component: component!)
        case let layoutModel as LayoutComponentModel:
            return LayoutComponentComposer(model: layoutModel, component: component!, componentModelRetriever: componentModelRetriever)
        default:
            return EmptyView().toAny()
        }
    }

    return EmptyView().toAny()
}

func CoreComponentComposer(model: CoreComponentModel, component: Component) -> AnyView {
    switch model {
    case _ as TextModel:
        return TextComponent(component: component).toAny()
    case _ as ButtonModel:
        return ButtonComponent(component: component).toAny()
    default:
        return EmptyView().toAny()
    }
}

func LayoutComponentComposer(
    model: LayoutComponentModel,
    component: Component,
    componentModelRetriever: @escaping (String) -> Component?
) -> AnyView {
    switch model {
    case _ as BoxModel:
        return BoxComponent(component: component, componentModelRetriever: componentModelRetriever).toAny()
    case _ as RowModel:
        return RowComponent(component: component, componentModelRetriever: componentModelRetriever).toAny()
    case _ as ColumnModel:
        return ColumnComponent(component: component, componentModelRetriever: componentModelRetriever).toAny()
    default:
        return EmptyView().toAny()
    }
}
