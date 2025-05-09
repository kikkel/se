[![Coverage Status](https://coveralls.io/repos/github/kikkel/se/badge.svg?branch=main)](https://coveralls.io/github/kikkel/se?branch=main)


Notes:
# Lecture 07 - Patterns
Refactoring Guru: https://refactoring.guru/design-patterns

- Chain of responsibility- passes a request sequentially along a dynamic chain of potential receivers until one of them handles it.
- Template method,
- Composite
- - **state**: soll input verarbeiten/MODUS/ETC
- **[SOLID] OPEN/CLOSED:** alle if statements mit strategy und andere design patterns ersetzen



## Creational
- ### Factory pattern - CardFactory (schon angefangen)
  - use abstract factory for factions?/ship-base? 
    > https://refactoring.guru/design-patterns/abstract-factory
  - create default cards separately to factions (scout, viper, explorer) 
  - **BRIDGE PATTERN** lets you replace the implementation object inside the abstraction. It’s as easy as assigning a new value to a field.
   

- ### Builder Pattern - DeckBuilder
  > https://refactoring.guru/design-patterns/builder
  > - extract the object construction code out of its own class and move it to separate objects called builders.
  > - to create different representations of some product (for example, stone and wooden houses)
  > - to construct Composite trees or other complex objects




## Structural
- ### BRIDGE Pattern: 
  > https://refactoring.guru/design-patterns/bridge
  > - to divide and organize a monolithic class that has several variants of some functionality
  > - to extend a class in several orthogonal (independent) dimensions
  > - to be able to switch implementations at runtime
  > - This pairing is useful when some abstractions defined by Bridge can only work with specific implementations. In this case, Abstract Factory can encapsulate these relations and hide the complexity from the client code.
  - _use to connect GUI-APP_


## Behavioural
- ### Observer Pattern ✅
  > https://refactoring.guru/design-patterns/observer
  > - subscription mechanism to notify multiple objects about any events that happen to the object they’re observing
  > - when changes to the state of one object may require changing other objects, and the actual set of objects is unknown beforehand or changes dynamically
  > - when some objects in your app must observe others, but only for a limited time or in specific cases.
  > - to establish dynamic one-way connections between objects, where some objects act as subordinates of others.
  > - 
- ### Mediator Pattern
  > https://refactoring.guru/design-patterns/mediator
  > - **eliminates direct connections between senders and receivers, forcing them to communicate indirectly via a mediator object.**
  > - when it’s hard to change some of the classes because they are tightly coupled to a bunch of other classes.
  > - when you can’t reuse a component in a different program because it’s too dependent on other components.
  > - when you find yourself creating tons of component subclasses just to reuse some basic behavior in various contexts.
  > - to eliminate mutual dependencies among a set of system components. Instead, these components become dependent on a single mediator object.
  > - you can permanently link all the components to the same mediator object.
  > - **when relying on Observer,** it plays the role of publisher, and the components act as subscribers which subscribe to and unsubscribe from the mediator’s events.
  > - 
- ### Strategy pattern - verschiedene Deckstufen für starrealms (wie Schwierigkeitsstufen) abhängigkeit zw controller und view zu verbessern
  > https://refactoring.guru/design-patterns/strategy
  > - usually describes different ways of doing the same thing, letting you swap these algorithms within a single context class.
  > - makes objects completely independent and unaware of each other.
  > - use different variants of an algorithm within an object and be able to switch from one algorithm to another during runtime.
  > - when you have a lot of similar classes that only differ in the way they execute some behavior.
  > - **to isolate the business logic of a class from the implementation details of algorithms that may not be as important in the context of that logic.**__
  > - when your class has a massive conditional statement that switches between different variants of the same algorithm.

- ### State Pattern: an extension of _Strategy Pattern_
  > https://refactoring.guru/design-patterns/state
  > - doesn’t restrict dependencies between concrete states, letting objects alter the state of the context at will.
  > - when you have an object that behaves differently depending on its current state, the number of states is enormous, and the state-specific code changes frequently.
  > - when you have a class polluted with massive conditionals that alter how the class behaves according to the current values of the class’s fields.
  > - when you have a lot of duplicate code across similar states and transitions of a condition-based state machine.
 
- ### Command Pattern: 
  > https://refactoring.guru/design-patterns/command
  > - **unidirectional connections between senders and receivers**
  > - to convert any operation into an object. The operation’s parameters become fields of that object. The conversion lets you defer execution of the operation, queue it, store the history of commands, send commands to remote services, etc.
  > - to parametrize objects with operations
  > - to queue operations, schedule their execution, or execute them remotely.
  > - _to implement reversible operations.(Lecture 8, DP II)_

  
  >  _**Warning:**_ _To be able to revert operations, you need to implement the history of performed operations. The command history is a stack that contains all executed command objects along with related backups of the application’s state._
  >
  > _This method has two drawbacks. First, it isn’t that easy to save an application’s state because some of it can be private. This problem can be mitigated with the **Memento pattern**._
  >
  > _Second, the state backups may consume quite a lot of RAM. Therefore, sometimes you can resort to an alternative implementation: instead of restoring the past state, the command performs the inverse operation. The reverse operation also has a price: it may turn out to be hard or even impossible to implement._

- ### Memento Pattern:
  > https://refactoring.guru/design-patterns/memento
  > - save and restore the previous state of an object without revealing the details of its implementation
  > - to produce snapshots of the object’s state to be able to restore a previous state of the object.
  > - when direct access to the object’s fields/getters/setters violates its encapsulation.
