#TicTacToe Game GUI (Java Swing)

An interactive Tic-Tac-Toe game featuring a Graphical user interface (GUI) built with **Java Swing**.
Players compete against a computer opponent in a clean, intuitive layout with visual highlighting for wins and draws.
---
## Features
- **Clean GUI (Swing):** Intuitive layout constructed with `borderLayout` and `gridLayout`.
- **Single player mode:** Play directly against a computer opponent.
- **Winning line highlight:** Automatic color styling for the 3 winning cells (green for player win, red for computer win).
- **Move selection protection:** Change selected cells before making a final submission.
- **Game state detection:** Real-time checking for win conditions and full-board draws.
---
## Technologies & tools
* **Programming language:** Java (JDK 8+)
* **GUI framework:** Java Swing / AWT
* **IDE:** Eclipse / VS Code / IntelliJ IDEA
* **Version control:** Git & GitHub
---
## Installation & local execution
### Prerequisites
Ensure you have the **Java development kit (JDK 8 or higher)** installed on your machine.
	
	### Execution steps
	1. **Clone the repository:**
   ```bash
   git clone https://github.com/ArchontiaSam/TicTacToe-Java-GUI.git
   
   2. **Navigate to the project directory:**
   ```bash
   cd TicTacToe-Java-GUI
   ```
	3. **Compile the Java files:**
   ```bash
   javac *.java
   ```
	4. **Run the application:**
   ```bash
   java GUITicTacToe
   ```
---

## 📁 Project Structure

```text
├── GUITicTacToe.java       # Main GUI window and main application entry point
├── Player.java             # Player movement and logic
├── Computer.java           # Computer opponent AI logic
├── CheckForWinner.java     # Victory conditions and winning coordinate calculation
└── README.md               # Project documentation
```
---

## Author

* **GitHub:** [@ArchontiaSam](https://github.com/ArchontiaSam)