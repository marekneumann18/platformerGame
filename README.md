# Platformer Game

A small 2D platformer built in Java. The game uses a custom update/render loop, a menu system, an options screen, a victory screen, and a persistent leaderboard.

## Features

- Main menu with mouse support
- In-game movement and jumping
- Options window for:
  - FPS and UPS tuning
  - Player color selection
  - Auto-jump toggle
- Vertical level scrolling with a camera that follows the player
- Victory screen with completion time
- Persistent leaderboard and config storage

## Controls

### Menu

- `Enter` starts the game
- Mouse click selects menu buttons
- Mouse hover highlights buttons

### Gameplay

- `A` / `Left Arrow` moves left
- `D` / `Right Arrow` moves right
- `W` / `Space` jumps
- `Esc` returns to the menu

## Requirements

- Java 22 or newer is recommended
- The project was configured in IntelliJ IDEA with JDK 24

## How to Run

### IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Make sure `src` is treated as source code and `res` is available as a resource root.
3. Run `src/game/Main.java`.


