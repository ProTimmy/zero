//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public struct BoxComponent: View {
    let model: BoxModel
    let componentModelRetriever: (String) -> ComponentModel?

    public init(boxModel: BoxModel, componentModelRetriever: @escaping (String) -> ComponentModel?) {
        self.model = boxModel
        self.componentModelRetriever = componentModelRetriever
    }

    public var body : some View {
        ZStack {
            ForEach(model.childComponents, id: \.self) { child in
                if let componentModel = componentModelRetriever(child) {
                    ComponentComposer(componentModel: componentModel, componentModelRetriever: componentModelRetriever)
                }
            }
        }
    }
}
