
class Book {
    private static String libraryName = "City Library";
    
    private final String isbn;
    private String title;
    private String author;
    
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    
    public static void displayLibraryName() {
        System.out.println("Library: " + libraryName);
    }
    
    public void displayBookDetails() {
        if (this instanceof Book) {
            System.out.println("Library: " + libraryName);
            System.out.println("Title: " + this.title);
            System.out.println("Author: " + this.author);
            System.out.println("ISBN: " + this.isbn);
        } else {
            System.out.println("Invalid book object.");
        }
    }
    
    public static void main(String[] args) {
        Book book1 = new Book("1984", "George Orwell", "123-456-789");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "987-654-321");
        
        book1.displayBookDetails();
        book2.displayBookDetails();
        
        Book.displayLibraryName();
    }
}

