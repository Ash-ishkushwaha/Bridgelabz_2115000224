class TextState {
    private String content;
    private TextState next;
    private TextState prev;

    public TextState(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }

    public String getContent() {
        return content;
    }

    public TextState getNext() {
        return next;
    }

    public void setNext(TextState next) {
        this.next = next;
    }

    public TextState getPrev() {
        return prev;
    }

    public void setPrev(TextState prev) {
        this.prev = prev;
    }
}

class TextEditor {
    private TextState currentState;
    private TextState head;
    private TextState tail;
    private int maxHistorySize;
    private int currentHistorySize;

    public TextEditor(int maxHistorySize) {
        this.maxHistorySize = maxHistorySize;
        this.currentHistorySize = 0;
        this.head = null;
        this.tail = null;
        this.currentState = null;
    }

    public void addTextState(String content) {
        TextState newState = new TextState(content);
        if (head == null) {
            head = tail = currentState = newState;
        } else {
            tail.setNext(newState);
            newState.setPrev(tail);
            tail = newState;
            currentState = tail;
        }
        if (currentHistorySize < maxHistorySize) {
            currentHistorySize++;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
    }

    public void undo() {
        if (currentState != null && currentState.getPrev() != null) {
            currentState = currentState.getPrev();
        }
    }

    public void redo() {
        if (currentState != null && currentState.getNext() != null) {
            currentState = currentState.getNext();
        }
    }

    public void displayCurrentState() {
        if (currentState != null) {
            System.out.println("Current State: " + currentState.getContent());
        } else {
            System.out.println("No current state available.");
        }
    }

    public void displayAllStates() {
        TextState current = head;
        while (current != null) {
            System.out.println(current.getContent());
            current = current.getNext();
        }
    }
}

public class TextEditorMain {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10);
        editor.addTextState("Coding");
        editor.addTextState("Workout");
        editor.addTextState("Drink water");

        System.out.println("Displaying all states:");
        editor.displayAllStates();
        System.out.println();
        
        System.out.println("Current state:");
        editor.displayCurrentState();
        System.out.println();
        
        System.out.println("Performing undo:");
        editor.undo();
        editor.displayCurrentState();
        System.out.println();
        
        System.out.println("Performing redo:");
        editor.redo();
        editor.displayCurrentState();
        System.out.println();
        
        System.out.println("Adding new state:");
        editor.addTextState("Hello! This is a text editor.");
        editor.displayCurrentState();
        System.out.println();
        
        System.out.println("Displaying all states:");
        editor.displayAllStates();
    }
}