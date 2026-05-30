import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    
    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void addMember(Member member) {
        members.add(member);
    }
    
    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }
    
    public Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }
        return null;
    }
    
    public ArrayList<Book> searchBooks(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        keyword = keyword.toLowerCase();
        
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword) || 
                book.getAuthor().toLowerCase().contains(keyword) ||
                book.getGenre().toLowerCase().contains(keyword)) {
                results.add(book);
            }
        }
        return results;
    }
    
    public void displayAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        if (books.isEmpty()) {
            System.out.println("No books in the library!");
            return;
        }
        for (Book book : books) {
            book.displayInfo();
        }
    }
    
    public void displayAvailableBooks() {
        System.out.println("\n=== AVAILABLE BOOKS ===");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                book.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books available at the moment!");
        }
    }
}