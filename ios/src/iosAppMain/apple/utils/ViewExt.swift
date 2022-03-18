//
// Created by Gabriel Timotei on 3/9/22.
//

import SwiftUI

extension View {
    public func toAny() -> AnyView {
        AnyView(self)
    }
}
