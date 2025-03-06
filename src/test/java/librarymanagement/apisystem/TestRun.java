package librarymanagement.apisystem;


import global.AllGlobalValue;
import org.testng.annotations.Test;


public class TestRun extends AllGlobalValue {
    /**
     * *  This method will get all the books and Get book details with different conditions and check availability
     */
    @Test(priority = 1)
    public  void testBooks() {
        Books books =  new Books();
        books.getAllBooks();
        books.getBooksWithAuthor("Alice Schertle");
        books.getBooksWithTitle("Little Blue Truck");

    }

    /**
     * * This method will run the Transactions request for Borrowing, returns and history
     */

    @Test(priority = 2)
    public  void runTransactions(){
        Transactions transactions = new Transactions();
        transactions.borrowBooksByPost();
        transactions.returnBooksByPost();
        transactions.validateBorrowingHistoryByPost();
    }

}
