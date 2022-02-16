//
//  MainViewModel.swift
//  ios
//
//  Created by Gabriel Timotei on 2/15/22.
//  Copyright © 2022 zero. All rights reserved.
//

//import Foundation
//import common

//class MainViewModel: ObservableObject {
//    @Published public var state: CommonState = CommonState()
//    @Published public var sideEffect: CommonSideEffect?
//
//    let engine: ViewComponentEngine
//
//    var stateWatcher : Closeable?
//    var sideEffectWatcher : Closeable?
//
//    init(engine: ViewComponentEngine) {
//        self.engine = engine
//        stateWatcher = self.engine.watchState().watch { [weak self] state in
//            self?.state = state
//        }
//        sideEffectWatcher = self.engine.watchSideEffect().watch { [weak self] sideEffect in
//            self?.sideEffect = sideEffect
//        }
//    }
//
//    public func dispatch(_ action: EngineAction) {
//        engine.dispatch(action: action)
//    }
//
//    deinit {
//        stateWatcher?.close()
//        sideEffectWatcher?.close()
//    }
//}
