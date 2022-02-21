import SwiftUI
import common

struct ContentView: View {
    @State
    private var componentHolder =
        ComponentHolder {
            RootController(
                componentContext: $0,
                storeFactory: TimeTravelStoreFactory()
            )
        }

    let greet = "Hello world"

    var body: some View {
        Text(greet)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
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
