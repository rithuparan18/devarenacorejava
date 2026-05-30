import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Pre-load some sample data
        library.addBook(new Book("978-3-16-148410-0", "Java Programming Guide", "John Smith", "Programming"));
        library.addBook(new Book("978-0-262-03384-8", "Introduction to Algorithms", "Thomas Cormen", "Computer Science"));
        library.addMember(new Member("M001", "Alice Johnson", "alice@email.com"));

        boolean running = true;
        
        while (running) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add New Book");
            System.out.println("2. Register New Member");
            System.out.println("3. Display All Books");
            System.out.println("4. Display Available Books");
            System.out.println("5. Search Books");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1": addNewBook(); break;
                case "2": registerMember(); break;
                case "3": library.displayAllBooks(); break;
                case "4": library.displayAvailableBooks(); break;
                case "5": searchBooks(); break;
                case "6": borrowBook(); break;
                case "7": returnBook(); break;
                case "8": 
                    running = false;
                    System.out.println("Exiting Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private static void addNewBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        
        library.addBook(new Book(isbn, title, author, genre));
        System.out.println("✅ Book added successfully!");
    }
    
    private static void registerMember() {
        System.out.println("\n=== REGISTER NEW MEMBER ===");
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        
        library.addMember(new Member(id, name, contact));
        System.out.println("✅ Member registered successfully!");
    }
    
    private static void searchBooks() {
        System.out.println("\n=== SEARCH BOOKS ===");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        
        ArrayList<Book> results = library.searchBooks(keyword);
        System.out.println("\nSearch Results:");
        if (results.isEmpty()) {
            System.out.println("No books found matching '" + keyword + "'.");
        } else {
            for (Book b : results) {
                b.displayInfo();
            }
        }
    }
    
    private static void borrowBook() {
        System.out.println("\n=== BORROW BOOK ===");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        Member member = library.findMemberById(memberId);
        
        if (member == null) {
            System.out.println("❌ Member not found.");
            return;
        }
        
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();
        Book book = library.findBookByIsbn(isbn);
        
        if (book == null) {
            System.out.println("❌ Book not found.");
            return;
        }
        
        if (member.borrowBook(book)) {
            System.out.println("✅ Book borrowed successfully!");
            System.out.println("Member: " + member.getName());
            System.out.println("Book: " + book.getTitle());
        } else {
            System.out.println("❌ Book is currently not available.");
        }
    }
    
    private static void returnBook() {
        System.out.println("\n=== RETURN BOOK ===");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        Member member = library.findMemberById(memberId);
        
        if (member == null) {
            System.out.println("❌ Member not found.");
            return;
        }
        
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();
        Book book = library.findBookByIsbn(isbn);
        
        if (book == null) {
            System.out.println("❌ Book not found in system.");
            return;
        }
        
        if (member.returnBook(book)) {
            System.out.println("✅ Book returned successfully!");
        } else {
            System.out.println("❌ This book was not borrowed by this member.");
        }
    }
}