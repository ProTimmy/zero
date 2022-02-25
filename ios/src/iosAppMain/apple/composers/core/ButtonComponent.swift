//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public struct ButtonComponent: View {
    let model: ButtonModel

    public init(_ buttonModel: ButtonModel) {
        self.model = buttonModel
    }

    public var body : some View {
        Button(action: model.onClick) {
            Text(model.text)
        }
    }
}
