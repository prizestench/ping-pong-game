# Ping-Pong Game

This repository contains the implementation of the Paddle Maker project. The project enhances a Java-based Pong-style game (JPong) by adding multiple types of paddles, implementing custom AI behavior, and demonstrating the principles of object-oriented programming, inheritance, and polymorphism.

## Project Overview

JPong is a modern take on the classic Pong game, where a human player competes against an AI-controlled paddle. The goal is to create smarter AI paddles and a variety of paddle types with unique behaviors.

### Key Features
- **Human Paddle Enhancements:**
  - CreativeHuman gets smaller each time it volleys the ball--shrinking down to a minimum value(5) and resets when a point is scored.

- **AI Paddle Types:**
  - **CPUBasic**: A simple AI paddle that tracks the ball and adjusts its position.
  - **CPUChallenging**: Predicts ball trajectories for smarter movement.
  - **CPUGenius**: Accounts for wall bounces to improve its accuracy.
  - **Creative AI Paddle**: Extends CPUChallenging and shares the same attributes. If lossing, CreativeCPU increments the speed of its paddle by however many points it is losing by.

- **Helper Utilities:**
  - `calcTargetY`: Computes the projected Y-coordinate of the ball when it reaches the paddle.
  - `calcNextWallBounceX`: Determines the next wall collision point of the ball.
  
## How to Play

1. **Launch the Game**: Run the `Launcher.java` file.
2. **Control Your Paddle**: Use the arrow keys to move up and down.
3. **Toggle Speed (Creative Paddle)**: Press the space bar to switch between speeds.
4. **Enjoy Enhanced Gameplay**: Compete against increasingly challenging AI paddles.
