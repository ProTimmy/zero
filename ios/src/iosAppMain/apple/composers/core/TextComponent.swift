//
// Created by Gabriel Timotei on 2/21/22.
//

import SwiftUI
import common

public struct TextComponent: View {
    let model: TextModel

    public init(_ textModel: TextModel) {
        self.model = textModel
    }

    public var body : some View {
        Text(model.text)
    }
}
