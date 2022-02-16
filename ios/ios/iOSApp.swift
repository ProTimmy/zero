import SwiftUI
import models

@main
struct iOSApp: App {
    let mockScreen = ScreenModel(
        content: [
            ColumnModel(
                content: [
                    RowModel(
                        content: [
                            TextModel(text: "Hello"),
                            TextModel(text: "world")
                        ]
                    ),
                    RowModel(
                        content: [
                            TextModel(text: "Test"),
                            TextModel(text: "1")
                        ]
                    ),
                    RowModel(
                        content: [
                            TextModel(text: "Test"),
                            TextModel(text: "2")
                        ]
                    ),
                ]
            )
        ]
    )
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
