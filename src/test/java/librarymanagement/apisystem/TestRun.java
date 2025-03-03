package librarymanagement.apisystem;


import global.AllGlobalValue;
import org.testng.annotations.Test;


public class TestRun extends AllGlobalValue {
    @Test
    public  void getAllBooks() {
        Books books =  new Books();
        books.getAllBooks();
        books.getBooksWithAuthor("Alice Schertle");
        books.getBooksWithTitle("Little Blue Truck");

    }
    @Test
    public  void runTransactions(){
        Transactions t = new Transactions();
        t.borrowBooksByPost();
        t.returnBooksByPost();
        t.validateBorrowingHistoryByPost();
    }

}
