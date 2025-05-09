[![Coverage Status](https://coveralls.io/repos/github/kikkel/se/badge.svg?branch=main)](https://coveralls.io/github/kikkel/se?branch=main)


Notes:
## Lecture 07 - Patterns

- ### Factory pattern - CardFactory (schon angefangen)
  - use abstract factory for factions?/ship-base? 
    > https://refactoring.guru/design-patterns/abstract-factory
  - create default cards separately to factions (scout, viper, explorer) 
  - **BRIDGE PATTERN** lets you replace the implementation object inside the abstraction. It’s as easy as assigning a new value to a field.
   

- ### Builder Pattern - DeckBuilder
  > - https://refactoring.guru/design-patterns/builder
  > - extract the object construction code out of its own class and move it to separate objects called builders.
  > - to create different representations of some product (for example, stone and wooden houses)
  > - to construct Composite trees or other complex objects
  

- ### Strategy pattern - verschiedene Deckstufen für starrealms (wie Schwierigkeitsstufen) abhängigkeit zw controller und view zu verbessern

- **state**: soll input verarbeiten/MODUS/ETC

Refactoring Guru: https://refactoring.guru/design-patterns

Chain of responsibility,
Template method,
Composite


- **[SOLID] OPEN/CLOSED:** alle if statements mit strategy und andere design patterns ersetzen




### BRIDGE Pattern: 
  > https://refactoring.guru/design-patterns/bridge
  > - to divide and organize a monolithic class that has several variants of some functionality
  > - to extend a class in several orthogonal (independent) dimensions
  > - to be able to switch implementations at runtime
  > - This pairing is useful when some abstractions defined by Bridge can only work with specific implementations. In this case, Abstract Factory can encapsulate these relations and hide the complexity from the client code.
- _use to connect GUI-APP_
