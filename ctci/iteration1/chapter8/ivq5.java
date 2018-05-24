import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Comparator;

public class ivq5 {
    public class BookReaderSystem {

    }

    private static class Library {
        // Want an ordered set of books that will exist in a searchable and 
        // manageable data structure.
        private static Set<Book> mBooks = new TreeSet<Book>(); 
        
        // There will be a defined set of users for the library.
        

        private void add(Book book) {
            if (book != null) {
                if (!Library.mBooks.contains(book)) {

                }
            }
        }
    }

    private static class BookReader {

        public static void flipToPage(Book book, int page) {

        }

        public static void flipToNextPage(Book book) {

        }  
    }

    private class Book {
        private int mBookId;
        private String mTitle;
        private String mAuthor;

        public int compareTo(Book book) {
            return 0;
        }

        private class GenreOrder implements Comparator<Book> {
            public int compare(Book bookA, Book bookB) {
                return 0;
            }
        }
    }

    private class User {
        private final int mUserId;
        // Current book that a user is reading
        private Book mCurrentBook;

        // Set of books available to a user.
        private Set<Book> mBooks = new TreeSet<Book>(); 

        // Set of progress of the books that the user has.
        private HashMap<Book, Integer> mBookProgress = new HashMap<Book, Integer>();

        public User (int userId) {
            this.mUserId = userId;
        }

        // The user is adding a book for their personal use.
        public void addBook(Book book) {
            if (book == null) {
                return;
            }

            if (!this.mBooks.contains(book)) {
                // Only allowed one book of the specific mapping.
                this.mBooks.add(book);
            }
        }

        // Removing a book from a user.
        public void removeBook(Book book) {
            if (book == null) {
                return;
            }

            if (this.mBooks.contains(book)) {
                if (this.mBookProgress.containsKey(book)) {
                    this.mBookProgress.remove(book);
                }
                this.mBooks.remove(book);
            }
        }

        public void startBook(Book book) {
            if (!book.equals(this.mCurrentBook)) {
                this.mCurrentBook = book;

                // Now need to obtain previous progress if already existed.
                if (this.mBookProgress.containsKey(book)) {
                    BookReader.flipToPage(book, this.mBookProgress.get(book));
                }
                else {
                    // Flip to the cover of the book.
                    BookReader.flipToPage(book, 0);
                }
            }
        }

        public void turnPage() {
            if (this.mCurrentBook != null) {
                BookReader.flipToNextPage(this.mCurrentBook);
            }
        }

        // Indicate the total number of books the user has.
        public int count() {
            return this.mBooks.size();
        }
    }
}