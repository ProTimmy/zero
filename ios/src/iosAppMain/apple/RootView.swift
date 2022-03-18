import SwiftUI
import common

public struct RootView: View {
    private let rootComponent: RootComponent

    @ObservedObject
    private var routerState: ObservableValue<RouterState<AnyObject, RootComponentChild>>
    
    public init(_ rootComponent: RootComponent) {
        self.rootComponent = rootComponent
        self.routerState = ObservableValue<RouterState<AnyObject, RootComponentChild>>(rootComponent.routerState)
    }
    
    
    public var body: some View {
        let child = routerState.value.activeChild.instance
        
        switch child {
        case let child as RootComponentChild.Screen:
            ScreenView(child.component)
        default:
            Text("Screen not found") // TODO: Replace with loading screen
        }
    }
}
