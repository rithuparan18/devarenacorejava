import java.util.ArrayList;

public class Member {
    private String memberId;
    private String name;
    private String contact;
    private ArrayList<Book> borrowedBooks;
    
    public Member(String memberId, String name, String contact) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.borrowedBooks = new ArrayList<>();
    }
    
    // Getters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }
    
    // Setters
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setName(String name) { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
    
    public boolean borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }
    
    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }
    
    public void displayInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contact);
        System.out.println("Books Borrowed: " + borrowedBooks.size());
        
        if (!borrowedBooks.isEmpty()) {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println("  - " + book.getTitle());
            }
        }
        System.out.println("-".repeat(40));
    }
}