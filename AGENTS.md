# Hytale Mod Project Instructions

## Project Overview
This is a Hytale server mod (plugin) project using Maven and Java 25. The project is structured to compile a JAR that runs within the Hytale Server environment.

## Critical Workflows

### Build and Run
- **One-Step Dev Server:** Run the dev server with the latest changes using:
  ```powershell
  .\mvnw clean package exec:exec@run-server
  ```
  This command builds the project, shades dependencies, and launches the Hytale server located in the `dev-server/` directory.

- **Build Only:** `.\mvnw clean package`. The final artifact is located in `target/final/`.

## Architecture & Conventions

### Plugin Entry Point
- The main class extends `JavaPlugin`.
- **Lifecycle:**
  - `constructor(JavaPluginInit init)`: Call `super(init)`. Initial setup.
  - `setup()`: Register commands and event listeners here.

### Configuration (`manifest.json`)
- Located at `src/main/resources/manifest.json`.
- Properties like `${project.version}` are replaced by Maven filtering.
- **Key Fields:** `Main` (must match the class extending `JavaPlugin`), `Name`, `Version`.

### Logging
- Use the fluent `HytaleLogger` API.
- Pattern:
  ```java
  public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
  // Usage
  LOGGER.atInfo().log("Message here");
  ```

### Commands
- Create commands by extending `CommandBase`.
- Register in `setup()` via `this.getCommandRegistry().registerCommand(new MyCommand());`.
- Pattern:
  ```java
  public class MyCommand extends CommandBase {
      public MyCommand() { super("commandName", "Description"); }
      @Override
      protected void executeSync(@Nonnull CommandContext ctx) {
          Player player = ctx.senderAs(Player.class);
          player.sendMessage(Message.raw("Response"));
      }
  }
  ```

### Event Handling
- Use separate handler classes (e.g., `EventHandler`) with static methods for cleaner code.
- Register in `setup()` via Method References:
  ```java
  this.getEventRegistry().registerGlobal(TargetEvent.class, EventHandler::onEvent);
  ```

## File Structure
- `src/main/java`: Source code.
- `src/main/resources/manifest.json`: Plugin configuration.
- `dev-server/`: Local server environment (logs, configs, world data). Do not edit files here extensively as they are server state.
- `pom.xml`: Dependency management and build configuration (Shadow plugin, Exec plugin).

## Dependencies
- **Hytale Server API:** implied by packages `com.hypixel.hytale.server.*`.
- **Java 25:** Ensure constructs are compatible with Java 25.
