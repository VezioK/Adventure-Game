## Adventure Game

## Overview
An interactive grid-based adventure game using Java, JavaFX, and SceneBuilder. Designed and implemented an object-oriented architecture with custom classes. Developed the user interface (UI) in FXML and integrated it with backend logic through a JavaFX controller, handling event-driven input, scene updates, game-state transitions, and combat interactions. 

## Installation
### Requirments
- Java JDK 17+ (or compatible JavaFX-supported version)
- JavaFX SDK
- IDE such as IntelliJ IDEA (recommended)

## Usage
### File Responsibilities 
#### hello-view.fxml
- Defines the JavaFX user interface using FXML as a declarative layout
- Specifies the root container (`AnchorPane`) and primary layout structure (`VBox`, `HBox`, `GridPane`)
- Declares all interactive UI components, including movement, action, and combat buttons
- Binds UI elements to the `HelloController` via `fx:controller`
- Connects buttons to controller logic using `onAction` event handlers
- Exposes UI elements (`Label`, `TextArea`, `Button`) to the controller through `fx:id`
- Displays real-time game feedback through a scrollable, wrapped `TextArea`
- Organizes movement controls in a grid layout to mirror directional intent
- Serves strictly as the presentation layer with no embedded game logic

#### Game.java
- Serves as the central game-state manager and rules engine
- Maintains player position, map boundaries, and blocked room data
- Implements a procedurally generated grid with randomized obstacles
- Controls player movement validation and boundary enforcement
- Handles probabilistic monster spawning during traversal
- Tracks searched rooms to prevent duplicate gold rewards
- Governs room-search mechanics based on player intelligence
- Acts as the authoritative source of truth for world state
  
#### HelloApplication.java
- Acts as the JavaFX application entry point
- Loads the FXML layout and initializes the primary scene
- Configures application window properties such as title and size
- Boots the JavaFX runtime and launches the UI lifecycle
- Contains no game logic, preserving separation of concerns
  
#### HelloController.java
- Functions as the controller layer between UI and game logic
- Handles all user-driven events (movement, combat, search, rest)
- Enforces gameplay constraints such as disabling movement during combat
- Synchronizes UI state with backend game state
- Manages combat flow including attack rolls, damage calculation, and outcomes
- Controls button enable/disable states to prevent invalid actions
- Logs narrative and system messages to the in-game text area
- Detects and handles game-over conditions
  
#### Monster.java
- Represents enemy entities encountered during exploration
- Encapsulates combat-relevant attributes (HP, strength, dexterity, intelligence)
- Generates monster stats procedurally for encounter variability
- Supports combat resolution through damage handling and survival checks
- Designed to be lightweight and instantiated dynamically
  
#### Player.java
- Represents the player character and persistent player state
- Stores health, combat statistics, intelligence, and accumulated gold
- Uses dice-roll–based stat generation to introduce variability
- Implements health management through damage and healing methods
- Influences combat success and exploration outcomes
  
#### Room.java
- Represents an abstract room within the grid-based map
- Tracks room-specific properties such as blocked state and gold availability
- Prevents repeated resource collection through search-state tracking
- Supports monster presence and encounter association
- Provides a foundation for future expansion of room-specific mechanics
  
### Mechanics
#### Core Systems
- Grid-based player movement with boundary enforcement
- Procedurally blocked rooms to introduce path constraints
- Event-driven input handling via JavaFX UI controls
- Turn-based combat with randomized attack resolution
- Single-use room searching influenced by player intelligence
- Risk–reward resting system with chance-based encounters
  
#### Error Prevention
- Movement and actions restricted during combat
- Boundary checks prevent invalid map traversal
- Button states disable illegal inputs
- Search state tracking prevents duplicate rewards
- Game-over state disables all controls
- Clear separation between UI, controller, and game logic


## Authors and acknowledgment
VezioK
