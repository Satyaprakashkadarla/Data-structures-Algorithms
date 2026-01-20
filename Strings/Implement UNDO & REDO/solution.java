import java.util.Stack;

class Solution {
    private StringBuilder doc = new StringBuilder();
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();
    
    public void append(char x) {
        // Save current state to undo stack
        undoStack.push(doc.toString());
        // Clear redo stack (new branch)
        redoStack.clear();
        // Append char
        doc.append(x);
    }

    public void undo() {
        if (undoStack.isEmpty()) return;
        // Save current to redo
        redoStack.push(doc.toString());
        // Restore previous from undo
        doc = new StringBuilder(undoStack.pop());
    }

    public void redo() {
        if (redoStack.isEmpty()) return;
        // Save current to undo
        undoStack.push(doc.toString());
        // Restore from redo
        doc = new StringBuilder(redoStack.pop());
    }

    public String read() {
        return doc.toString();
    }
}