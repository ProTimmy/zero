import SwiftUI
import common

struct ContentView: View {
    @State
    private var componentHolder =
        ComponentHolder<RootComponent> {
            let rootController = RootController(
                componentContext: $0,
                storeFactory: LoggingStoreFactory(delegate: TimeTravelStoreFactory())
            )
            let screenId = rootController.storeDemoScreen()
            rootController.getScreenById(id: screenId)
            
            return rootController
        }

    var body: some View {
        GeometryReader { geometry in
            RootView(componentHolder.component)
                .onAppear { LifecycleRegistryExtKt.resume(componentHolder.lifecycle) }
                .onDisappear { LifecycleRegistryExtKt.resume(componentHolder.lifecycle) }
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: Alignment.center)
        }
    }
}

class ComponentHolder<T> {
    let lifecycle: LifecycleRegistry
    let component: T

    init(factory: (ComponentContext) -> T) {
        let lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        let component = factory(DefaultComponentContext(lifecycle: lifecycle))
        self.lifecycle = lifecycle
        self.component = component

        lifecycle.onCreate()
    }

    deinit {
        lifecycle.onDestroy()
    }
}
