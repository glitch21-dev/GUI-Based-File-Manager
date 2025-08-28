Java File Manager (GUI + Console)

This project is a basic file manager built with Java.  
It started as a simple terminal-based file manager, and was later expanded into a GUI-based application using Swing.

The aim was to take the same file operations from the console version and provide a more user-friendly interface where users can click buttons and see results in a text area instead of typing everything into the terminal.

---------------------------------------------

✨ Features
Both versions (console & GUI) support essential file operations:

- 📄 Create File
- ✏️ Write to File  
- 📖 Read File  
- 🗑️ Delete File  
- 📂 List Files in a Directory
- 📑 Copy File  
- 🚚 Move File  
- 🔎 Search for File

---------------------------------------------

🖥️ GUI Version (Swing)
The GUI is built using Java Swing components like:
- `JFrame` for the main window  
- `JButton` for actions  
- `JTextField` for input  
- `JTextArea` with `JScrollPane` for output  
- `JOptionPane` for extra user input  

The program follows an **event-driven structure**, where each button is tied to a specific file operation method.

---------------------------------------------------

📜 Console Version
The console version uses:
- `Scanner` for user input  
- `System.out.println` for output  
- A `switch` statement menu for file operations  

This served as the foundation for the GUI version. 
I encourage anyone interested in the console version to check it out, its also a public repo

------------------------------------------------------

🚧 Development Notes
- Java turned out to be more robust and structured than expected, especially when moving from console programming to GUI development.  
- Handling layouts, events, and input dialogs in Swing required a different way of thinking compared to the linear flow of terminal programs.  
- In a few areas, I relied on AI assistance to help figure out how to connect the file management logic with the Swing interface (for example, wiring up buttons, handling text areas, and managing dialogs).  

---

🏃 How to Run
1. Clone or download this repository.  
2. Compile the code:  
   ```bash
   javac FileMangerGUI.java
   javac FileManagementSystem.java
