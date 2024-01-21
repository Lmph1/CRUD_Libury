import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Book {
    private int ID=0,Published_Year;
    private String title,Author,Status;

    public Book(int ID, String title, String author, int Year, String availible) {
        this.ID=ID;
        this.title=title;
        this.Author=author;
        this.Published_Year=Year;
        this.Status=availible;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPublished_Year() {
        return Published_Year;
    }

    public void setPublished_Year(int published_Year) {
        Published_Year = published_Year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
public class Main {
    public static void menus(String Name, String Address){
        System.out.print("\n\n========= "+ Name +" , "+ Address +"=========\n");
        System.out.print("1- Add Book\n");
        System.out.print("2- Show All Books\n");
        System.out.print("3- Show Available Books\n");
        System.out.print("4- Borrow Book\n");
        System.out.print("5- Return Book\n");
        System.out.print("6- Exit\n");
        System.out.print("------------------------------------------------------\n");
    }
    public static int countPerpage(int bookData,int page){
        page+=5;
        if(page>bookData){
            return (5-(page-bookData));
        }
        return 5;
    }
    public static int FindAmountOfBookData(Book[] books){
        int count=0;
        for (int i=0;i< books.length;i++){
            if (books[i] != null) {
                count=i+1;
            }
        }
        return count;
    }
    public static void DisplayAllBook(Book[] book){
        int page=0,option,Cpage=1;
        int TotalPage= (int) Math.ceil((double) FindAmountOfBookData(book) /5.0);
        Scanner sc = new Scanner(System.in);
        while (true) {
            CellStyle TextAlign = new CellStyle(CellStyle.HorizontalAlign.center);

            Table t = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);

            t.setColumnWidth(0, 0, 50);

            t.addCell("Total Pages: " + TotalPage, TextAlign, 5);

            t.addCell("ID", TextAlign);
            t.addCell("TITLE", TextAlign);
            t.addCell("AUTHOR", TextAlign);
            t.addCell("PUBLISH DATE", TextAlign);
            t.addCell("STATUS", TextAlign);

            for (int i = page; i < page + countPerpage(FindAmountOfBookData(book), page); i++) {
                if (book[i] != null) {
                    t.addCell(book[i].getID() + "", TextAlign);
                    t.addCell(book[i].getTitle() + "", TextAlign);
                    t.addCell(book[i].getAuthor() + "", TextAlign);
                    t.addCell(book[i].getPublished_Year() + "", TextAlign);
                    t.addCell(book[i].getStatus() + "", TextAlign);
                } else {
                    break;
                }
            }
            t.addCell("Current Page : " + Cpage, TextAlign, 5);
            t.addCell("Perpage: " + countPerpage(FindAmountOfBookData(book), page) + " Books", TextAlign, 5);

            System.out.println(t.render());
            System.out.println("1) First\t" +
                    "2) Next\t" +
                    "3) Previous\t" +
                    "4) Last\t" +
                    "5) Back");
            System.out.print("=> Enter Your Option: ");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    page = 0;
                    Cpage = 1;
                    break;
                case 2:
                    if (Cpage == TotalPage) {
                        System.out.println("This is already the last page!");
                        break;
                    }
                    Cpage += 1;
                    page += 5;
                    break;
                case 3:
                    if (Cpage == 1) {
                        System.out.println("This is already the first page!");
                        break;
                    }
                    Cpage -= 1;
                    page -= 5;
                    break;
                case 4:
                    page = (TotalPage - 1) * 5;
                    Cpage = TotalPage;
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Your Option Out of Range!");
            }
        }
    }
    public static void DisplayAvailableBook(Book[] book){

        CellStyle TextAlign = new CellStyle(CellStyle.HorizontalAlign.center);

        Table t = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER,ShownBorders.ALL);
        t.setColumnWidth(0, 0, 50);
        t.addCell("ID",TextAlign);
        t.addCell("TITLE",TextAlign);
        t.addCell("AUTHOR",TextAlign);
        t.addCell("PUBLISH DATE",TextAlign);
        t.addCell("STATUS",TextAlign);

        for(int i=0 ; i< book.length ; i++){
            if(book[i]!=null) {
                if(book[i].getStatus()=="Available") {
                    t.addCell(book[i].getID() + "", TextAlign);
                    t.addCell(book[i].getTitle() + "", TextAlign);
                    t.addCell(book[i].getAuthor() + "", TextAlign);
                    t.addCell(book[i].getPublished_Year() + "", TextAlign);
                    t.addCell(book[i].getStatus() + "", TextAlign);
                }else{
                    System.out.println("        No Book Available");
                }
            }else{
                break;
            }
        }
        System.out.print(t.render());
    }
    public static void main(String[] args) {

        int option;
        int bookID=0;
        boolean found=false;

        String L_Name,L_Address;
        Date date = new Date();
        Scanner sc = new Scanner(System.in);

        System.out.print("========= SET UP LIBRARY =========\n");
        System.out.print("=> Enter Library's Name : ");
        L_Name= sc.next();
        System.out.print("=> Enter Library's Address : ");
        L_Address= sc.next();
        System.out.print("\""+ L_Name + "\" Library is already created in \""+ L_Address +"\" address successfully on "+ date);

        Book[] book = new Book[50];

        book[0] = new Book(1,"FoxyGirl","DaraVouth (1988-Present)",2007,"Available");
        book[1] = new Book(2,"Sandwich","Visole (1958-Present)",2010,"Unavailable");
        book[2] = new Book(3,"RedGlove","David (1848-2004)",1900,"Available");
        book[3] = new Book(4,"FoxyGirl","DaraVouth (1988-Present)",2007,"Available");
        book[4] = new Book(5,"Sandwich","Visole (1958-Present)",2010,"Unavailable");
        book[5] = new Book(6,"RedGlove","David (1848-2004)",1900,"Available");
//        book[6] = new Book(7,"FoxyGirl","DaraVouth (1988-Present)",2007,"Available");
//        book[7] = new Book(8,"Sandwich","Visole (1958-Present)",2010,"Unavailable");
//        book[8] = new Book(9,"RedGlove","David (1848-2004)",1900,"Available");
//        book[9] = new Book(10,"FoxyGirl","DaraVouth (1988-Present)",2007,"Available");
//        book[10] = new Book(11,"Sandwich","Visole (1958-Present)",2010,"Unavailable");
//        book[11] = new Book(12,"RedGlove","David (1848-2004)",1900,"Available");
        while(true)
        {
            menus(L_Name,L_Address);
            System.out.print("-> Choose option(1-6) : ");
            option=sc.nextInt();
            String st = String.valueOf(option);
            Pattern pt = Pattern.compile(("[1-6]"));
            Matcher mt = pt.matcher(st);
            int op = Integer.parseInt(st);
            switch (op)
            {
                case 1: {
                    for (int i = 0; i < book.length; i++) {
                        if (book[i] == null) {
                            book[i] = new Book(0,"null","null",0,"Unavailable");
                            book[i].setID(i + 1);
                            System.out.println("========= ADD BOOK INFO =========");
                            System.out.println("=> Book ID : " + book[i].getID());
                            System.out.print("=> Enter Book's Name : ");
                            book[i].setTitle(sc.next());
                            System.out.print("=> Enter Book Author Name : ");
                            book[i].setAuthor(sc.next());
                            System.out.print("=> Enter Author Year Active : ");
                            book[i].setAuthor(book[i].getAuthor()+" ("+sc.next()+")");
                            System.out.print("=> Enter Published Year : ");
                            book[i].setPublished_Year(sc.nextInt());
                            book[i].setStatus("Available");
                            System.out.println("Book is added successfully");
                            break;
                        }
                    }
                }
                break;
                case 2: {
                    System.out.println("========= ALL BOOKS INFO =========");

                    if(book[0] != null) {
                        DisplayAllBook(book);
                    }
                    else{
                        System.out.println("        No Book Available");
                    }
                }
                break;
                case 3: {
                    System.out.println("========= AVAILABLE BOOKS INFO =========");
                    if(book[0] != null) {
                        DisplayAvailableBook(book);
                    }
                    else{
                        System.out.println("        No Book Available");
                    }
                }
                break;
                case 4: {
                    found=false;
                    System.out.println("========= BORROW BOOK INFO =========");
                    System.out.print("=> Enter Book ID to Borrow :");
                    bookID=sc.nextInt();
                    for(int i = 0 ; i < book.length;i++){
                        if(book[i]!=null) {
                            if (bookID == book[i].getID()) {
                                if(book[i].getStatus()=="Available"){
                                    System.out.println("Book ID : " + book[i].getID());
                                    System.out.println("Book Title : " + book[i].getTitle());
                                    System.out.println("Book Author : " + book[i].getAuthor());
                                    System.out.println("Published Year : " + book[i].getPublished_Year() + " is borrowed successfully...");
                                    book[i].setStatus("Unavailable");
                                    found=true;
                                    break;
                                }else{
                                    found=true;
                                    System.out.println("This book is Unavailable!");
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    if(found==false){
                        System.out.println("Book ID : " + bookID +" not Exist...");
                    }
                }
                break;
                case 5: {
                    found=false;
                    System.out.println("========= RETURN BOOK INFO =========");
                    System.out.print("=> Enter Book ID to Return :");
                    bookID=sc.nextInt();
                    for(int i = 0 ; i < book.length;i++){
                        if(book[i]!=null) {
                            if (bookID == book[i].getID()) {
                                if (book[i].getStatus() == "Unavailable") {
                                    System.out.println("Book ID : " + book[i].getID());
                                    System.out.println("Book Title : " + book[i].getTitle());
                                    System.out.println("Book Author : " + book[i].getAuthor());
                                    System.out.println("Published Year : " + book[i].getPublished_Year() + " is returned successfully...");
                                    book[i].setStatus("Available");
                                    found=true;
                                    break;
                                } else {
                                    found=true;
                                    System.out.println("This book is Unavailable!");
                                }
                            }
                        }
                        else{
                            break;
                        }
                    }
                    if(found==false){
                        System.out.println("Book ID : " + bookID +" not Exist...");
                    }
                }
                break;
                case 6: {
                    System.out.println("(^-^) Good Bye! (^-^)");
                    return;
                }
                default:{
                    System.out.println("Your Input is out of range!!");
                }
            }
        }
    }

}
