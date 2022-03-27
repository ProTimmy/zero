//
//  SimpleRouterState.swift
//  ios-app
//
//  Created by Arkadii Ivanov on 11/4/20.
//  Copyright © 2020 Arkadii Ivanov. All rights reserved.
//

import common

func simpleRouterState<T : AnyObject>(_ child: T) -> Value<RouterState<AnyObject, T>> {
    return mutableValue(
        RouterState(
            configuration: "config" as AnyObject,
            instance: child
        )
    )
}
