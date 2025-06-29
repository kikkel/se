_COVERALLS DISFUNCTIONAL DUE TO PATHFILE .CSV READER ISSUES!!_
[![Coverage Status](https://coveralls.io/repos/github/kikkel/se/badge.svg?branch=main)](https://coveralls.io/github/kikkel/se?branch=main)


# lec 8
## Notes:
- to do:
    - incorporate shuffle directly into deck?
    - **_RENDERER_** isnt implemented properly! render() defined independently in consoleview and separate to renderer.render()...
    - Potential Improvements
        1. Code Duplication:
        There is some duplication in the rendering logic for ExplorerCard and FactionCard (e.g., handling of primaryAbility and scrapAbility).
        Consider extracting common rendering logic into helper methods.
        2. Scalability:
        If more card types are added, the render method could become unwieldy. Consider using a more modular approach, such as delegating rendering to specific classes for each card type.
        3. Testing:
        Ensure unit tests cover all card types and edge cases (e.g., cards with missing abilities).
        4. Localization:
        If the game needs to support multiple languages, the hardcoded strings in the render method should be replaced with localized resources.

- create undo/redo mechanism with **command pattern**   <- **ASSIGNMENT** ✅
- **_!!! current MVC issues:_**
  - 

### - monad for transporting and transforming monoids 
  - abbildung von einer menge auf eine andere menge
  - map,flatMap and filter MUST be implemented (example slide 15). handled by for comprehension
  - monad is the container for transporting monoids. use to parralelise creating decks, categorising cards in set, etc.
  - store **States**(goodState, badState):
      - Option(Some, None)   <- **ASSIGNMENT** ✅ (Attributes.scala, DeckBuilder.scala)
      - Try(Success,Failure)   <- **ASSIGNMENT** ✅ (loadCards.scala, CardBridge.scala)
      - Either(Right, Left)
      - Future(Success, NotCompleted, Failure)

      
lec 7 notes

- Chain of responsibility- passes a request sequentially along a dynamic chain of potential receivers until one of them handles it.
- Template method



# Patterns

Refactoring Guru: https://refactoring.guru/design-patterns

## Structural
-  **DECORATOR Pattern:** ✅
  > - https://refactoring.guru/design-patterns/decorator
  > - to be able to assign extra behaviors to objects at runtime without breaking the code that uses these objects
  > - when it’s awkward or not possible to extend an object’s behavior using inheritance.
  > - Decorator lets you change the skin of an object, while Strategy lets you change the guts.

- **COMPOSITE Pattern: ABILITIES?/Attributes?/SEPARATE DECKS(with deckbuilder)?**  ✅
  > - https://refactoring.guru/design-patterns/composite
  > - to implement a tree-like object structure
  > - when you want the client code to treat both simple and complex elements uniformly.
  > - use Builder when creating complex Composite trees because you can program its construction steps to work recursively
  > - Composite and Decorator have similar structure diagrams since both rely
  >   on recursive composition to organize an open-ended number of objects.
  >
  >   A Decorator is like a Composite but only has one child component.
  >   There’s another significant difference: Decorator adds additional responsibilities to the wrapped object,
  >   while Composite just “sums up” its children’s results.
  >
  >   However, the patterns can also cooperate: you can use Decorator to extend the behavior of a specific object in the Composite tree

- **BRIDGE Pattern: CardType (Ship/Base) ✅**
  > https://refactoring.guru/design-patterns/bridge
  >
  > - to divide and organize a monolithic class that has several variants of some functionality
  > - to extend a class in several orthogonal (independent) dimensions
  > - to be able to switch implementations at runtime
  > - This pairing is useful when some abstractions defined by Bridge can only work with specific implementations. In this case, Abstract Factory can encapsulate these relations and hide the complexity from the client code.
  - _use to connect GUI-APP_

## Behavioural

- ### Chain Of Command Pattern: handling attributes?
  > - https://refactoring.guru/design-patterns/chain-of-responsibility
  > - when your program is expected to process different kinds of requests in various ways, but the exact types of requests and their sequences are unknown beforehand
  > - when it’s essential to execute several handlers in a particular order.
  > - when the set of handlers and their order are supposed to change at runtime.
  > - Chain of Responsibility is often used in conjunction with Composite.
  >   In this case, when a leaf component gets a request, it may pass it through the chain of all of the parent components down to the root of the object tree.
  >
  > - Handlers in Chain of Responsibility can be implemented as Commands. In this case, you can execute a lot of different operations over the same context object, represented by a request.
  >
  >   However, there’s another approach, where the request itself is a Command object. In this case, you can execute the same operation in a series of different contexts linked into a chain.
  >
  > - Chain of Responsibility and Decorator have very similar class structures.
  >   Both patterns rely on recursive composition to pass the execution through a series of objects.
  >   However, there are several crucial differences.
  >
  > - The CoR handlers can execute arbitrary operations independently of each other. They can also stop passing the request further at any point.
  >   On the other hand, various Decorators can extend the object’s behavior while keeping it consistent with the base interface.
  >   In addition, decorators aren’t allowed to break the flow of the request.


- ### Mediator Pattern
  > https://refactoring.guru/design-patterns/mediator
  >
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
  >
  > - usually describes different ways of doing the same thing, letting you swap these algorithms within a single context class.
  > - makes objects completely independent and unaware of each other.
  > - use different variants of an algorithm within an object and be able to switch from one algorithm to another during runtime.
  > - when you have a lot of similar classes that only differ in the way they execute some behavior.
  > - **to isolate the business logic of a class from the implementation details of algorithms that may not be as important in the context of that logic.**\_\_
  > - when your class has a massive conditional statement that switches between different variants of the same algorithm.
  
- ### Memento Pattern:
  > https://refactoring.guru/design-patterns/memento
  >
  > - save and restore the previous state of an object without revealing the details of its implementation
  > - to produce snapshots of the object’s state to be able to restore a previous state of the object.
  > - when direct access to the object’s fields/getters/setters violates its encapsulation.
  
- **Observer Pattern ✅**
  > https://refactoring.guru/design-patterns/observer
  >
  > - subscription mechanism to notify multiple objects about any events that happen to the object they’re observing
  > - when changes to the state of one object may require changing other objects, and the actual set of objects is unknown beforehand or changes dynamically
  > - when some objects in your app must observe others, but only for a limited time or in specific cases.
  > - to establish dynamic one-way connections between objects, where some objects act as subordinates of others.
  

- **State Pattern: an extension of _Strategy Pattern_  ✅**

  > https://refactoring.guru/design-patterns/state
  >
  > - doesn’t restrict dependencies between concrete states, letting objects alter the state of the context at will.
  > - when you have an object that behaves differently depending on its current state, the number of states is enormous, and the state-specific code changes frequently.
  > - when you have a class polluted with massive conditionals that alter how the class behaves according to the current values of the class’s fields.
  > - when you have a lot of duplicate code across similar states and transitions of a condition-based state machine.

- **Command Pattern: ✅**

  > https://refactoring.guru/design-patterns/command
  >
  > - **unidirectional connections between senders and receivers**
  > - to convert any operation into an object. The operation’s parameters become fields of that object. The conversion lets you defer execution of the operation, queue it, store the history of commands, send commands to remote services, etc.
  > - to parametrize objects with operations
  > - to queue operations, schedule their execution, or execute them remotely.
  > - _to implement reversible operations.(Lecture 8, DP II)_

  > _**Warning:**_ _To be able to revert operations, you need to implement the history of performed operations. The command history is a stack that contains all executed command objects along with related backups of the application’s state._
  >
  > _This method has two drawbacks. First, it isn’t that easy to save an application’s state because some of it can be private. This problem can be mitigated with the **Memento pattern**._
  >
  > _Second, the state backups may consume quite a lot of RAM. Therefore, sometimes you can resort to an alternative implementation: instead of restoring the past state, the command performs the inverse operation. The reverse operation also has a price: it may turn out to be hard or even impossible to implement._

## Creational

- **Abstract Factory pattern - FactionFactory ✅**

  - > https://refactoring.guru/design-patterns/abstract-factory
  - create default cards separately to factions (scout, viper, explorer)
  - **BRIDGE PATTERN** lets you replace the implementation object inside the abstraction. It’s as easy as assigning a new value to a field.

- **Builder Pattern - DeckBuilder ✅**
  > https://refactoring.guru/design-patterns/builder
  >
  > - extract the object construction code out of its own class and move it to separate objects called builders.
  > - to create different representations of some product (for example, stone and wooden houses)
  > - to construct Composite trees or other complex objects


