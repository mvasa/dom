import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class openDBconnection {
    static Connection conn = null;
    static ResultSet rs = null;
    static Statement ps = null;

//<editor-fold desc="main">
    public static void main(String[] args)
    {
        dropTable("Customer_Address");
        dropTable("Customer_Contact_Info");
        dropTable("Booking");
        dropTable("Flight_Plan");
        dropTable("Airplane");
        dropTable("Customer");
        dropTable("Location");

        Customer();
        Airplane();
        CustomerAddress();
        CustomerContactInfo();
        Booking();
        Location();
        FlightPlan();


        initializePkLists();
        readExistingPrimaryKeys();

        loadFlightPlan();

        getAllAvailableCities();

        welcomeText();

        getCustomer();
        getAddress();
        getContact();

        insertCustomer();
        insertContact();
        insertAddress();

        getReservation();
        insertBooking();
        selectBooking();

        displayFlightUserDetails();
        System.out.println("\n\n\n\n\n\n");

        requestNextAction();


    }
//</editor-fold>
    private static void requestNextAction(){
        Scanner userDecision = new Scanner(System.in);
        System.out.println("press Y for another Reservation");
        String response = userDecision.nextLine();
        if(response.trim().toUpperCase().equals("Y")){
            getReservation();
            insertBooking();
            selectBooking();
            requestNextAction();
        }
        else if(response.trim().toUpperCase().equals("N")){
            System.out.println("thanks for using this reservation system. \n"+
            "A printable document with your booking information will be sent to you shortly");
        }
        else{
            System.out.println("Please enter Y to book another flight or enter N to end transaction\n\n");
        }
    }


    private static void welcomeText(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("                    \t\t\t\t\tWelcome to the reservation system!            ");
        System.out.println("\n\n\t\t\t\t\tThis system will guide you through the process of creating your reservation." +
                            "\n\t\t\t\t\t____________________________________________________________________________\n\n\n\n\n");
    }
//<editor-fold desc="open close DB">
    public static void openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Echotilt58*");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
       // System.out.println("Opened database successfully");
    }

    public static void closeConnection(){
        try
        {
            conn.close();
         //   System.out.println("connection closed");
        }
        catch(java.sql.SQLException ex){
            System.out.println("hi");
        }
    }

//</editor-fold>

//<editor-fold desc="Drop Tables">

    public static void dropTableCascade(String tableName){
        try
        {
            openConnection();
            ps = conn.createStatement();
            String sql = "DROP TABLE " + tableName +" CASCADE";
            ps.executeUpdate(sql);
            ps.close();
            conn.close();
            System.out.println("Table "+tableName+" dropped  successfully");
        }
        catch (SQLException ex)
        {
            System.out.println("table " + tableName+" doesn't exist");
            ex.printStackTrace();
        }
    }
    public static void dropTable(String tableName){
            try
            {
                openConnection();
                ps = conn.createStatement();
                String sql = "DROP TABLE " + tableName;
                ps.executeUpdate(sql);
                ps.close();
                conn.close();
                System.out.println("...");
                //System.out.println("Table "+tableName+" dropped  successfully");
            }
            catch (SQLException ex)
            {
                //System.out.println("table " + tableName+" doesn't exist");
                System.out.println("An error occurred please start again");
                ex.printStackTrace();
            }
    }


    public static void dropAllTables(){
        dropTable("Airplane");
        dropTable("Flight_Plan");
        dropTable("Customer_Address");
        dropTable("Customer_Contact_Info");
        dropTable("Customer");
        dropTable("Location");
        dropTable("Booking");
    }
//</editor-fold>

//<editor-fold desc="CreateTables">
    public static void createALLTables(){
        Airplane();
        FlightPlan();
        Customer();
        CustomerAddress();
        CustomerContactInfo();
        Location();
        Booking();
    }

    public static void Airplane() {
        try
        {
            openConnection();
            ps = conn.createStatement();
            String sql = "CREATE TABLE Airplane " +
                    "(Unique_Airplane_Number    integer PRIMARY KEY," +
                    " Airline_Abr               VARCHAR, " +
                    " Airline                   VARCHAR);";
            ps.executeUpdate(sql);
            ps.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Table Airplane created successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error opening the database\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }

    public static void FlightPlan() {
        try
        {
            openConnection();
            ps = conn.createStatement();
            String sql = "CREATE TABLE Flight_Plan " +
                    "(Route_ID                      integer PRIMARY KEY," +
                    "Airplane_Number                integer REFERENCES Airplane(Unique_Airplane_Number), " +
                    "Destination_City               VARCHAR REFERENCES Location(City_Code)," +
                    "Origin_City                    VARCHAR REFERENCES Location(City_Code)," +
                    "flight_length                  VARCHAR," +
                    "date_of_departure              VARCHAR," +
                    "date_of_arrival                VARCHAR," +
                    "time_of_departure              VARCHAR," +
                    "time_of_arrival                VARCHAR);";

            ps.executeUpdate(sql);
            ps.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Table Flight_Plan created successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error creating table\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }

    public static void Customer() {
        try
        {
            openConnection();
            ps = conn.createStatement();
            String sql = "CREATE TABLE Customer " +
                    "(Unique_Customer_ID        integer PRIMARY KEY," +
                    " first_name                VARCHAR, " +
                    "last_name                  VARCHAR);";
            ps.executeUpdate(sql);
            ps.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Table Customer created successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error creating table\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }

    public static void CustomerAddress() {
        try
        {
            openConnection();
            ps = conn.createStatement();
            String sql = "CREATE TABLE Customer_Address " +
                    "(Customer_ID               integer REFERENCES Customer(Unique_Customer_ID)," +
                    "Customer_Address_ID        integer PRIMARY KEY, " +
                    "Street                     VARCHAR,"+
                    "City                       VARCHAR,"+
                    "State_Province             VARCHAR,"+
                    "Postal_Code                integer,"+
                    "Country                    VARCHAR);";
            ps.executeUpdate(sql);
            ps.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Table Customer_Address created successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error creating table\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }

    public static void CustomerContactInfo() {
        try
        {
            openConnection();
            ps = conn.createStatement();
            String sql = "CREATE TABLE Customer_Contact_Info " +
                    "(customer_ID               integer REFERENCES Customer(Unique_Customer_ID)," +
                    "Customer_Contact_ID        integer PRIMARY KEY, " +
                    "Country_Code               integer,"+
                    "City_Area_Code             integer,"+
                    "Local_Number               integer,"+
                    "Email                      VARCHAR);";
            ps.executeUpdate(sql);
            ps.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Table Customer_Contact_Info created successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error creating table\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }

    public static void Location() {
        try
        {
            openConnection();
            ps = conn.createStatement();
            String sql = "CREATE TABLE Location " +
                    "(Country           VARCHAR," +
                    "State_Province     VARCHAR," +
                    "City               VARCHAR,"+
                    "City_Code          VARCHAR(3) PRIMARY KEY);";
            ps.executeUpdate(sql);
            ps.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Table Location created successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error creating table\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }

    public static void Booking() {
        try
        {
            openConnection();
            ps = conn.createStatement();
            String sql = "CREATE TABLE Booking " +
                    "(Unique_Booking_Number         integer PRIMARY KEY," +
                    "Flight_Number                  integer REFERENCES Airplane(unique_Airplane_number), " +
                    "Date_of_Booking                VARCHAR,"+
                    "Customer_Paying                VARCHAR,"+
                    "Customer_Name_on_Ticket        VARCHAR,"+
                    "Customer_ID                    integer REFERENCES Customer(Unique_Customer_ID),"+
                    "Booking_City                   VARCHAR);";
            ps.executeUpdate(sql);
            ps.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Table Location created successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error creating table\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }
//</editor-fold>

//<editor-fold desc="global vars">
    static String firstName;
    static String lastName;
    static String street;
    static String city;
    static String state;
    static int zipCode;
    static String country;
    static String phoneNum;
    static String email;
    static String countryCode;
    static String cityAreaCode;
    static String localNumber;
    static String startCity;
    static String destCity;
    static String AirplaneDest;
    static String AirplaneOrigin;
    static String  flightLength;
    static String  departDate;
    static String  arrivalDate;
    static String  departTime;
    static String  arrivalTime;
    static int uniqueCustomerID;
    static int uniqueCustomerAddressID;
    static int uniqueCustomerContactID;
    static int uniqueBookingID;

    //</editor-fold>

//<editor-fold desc="user interaction here">
    public static void getUserData()
    {
        getCustomer();
        getAddress();
        getContact();
    }


    public static void displayAvailableRoutes(String originCity)
    {
        String originCode = "US1";
        System.out.println("\n\nThese are the available routes from this city.\n");
        try {
            openConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT City_Code FROM Location WHERE City = ?;");
            stmt.setString(1, originCity.trim().toUpperCase());
            rs = stmt.executeQuery();


            while ( rs.next() ) {
                originCode = rs.getString("City_Code");
            }
            rs.close();
            ps.close();
            closeConnection();
            System.out.println("\n\n");

        } catch ( Exception e ) {
            System.out.println("testing process 1");
            e.printStackTrace();
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        ArrayList destCode = new ArrayList<String>();
        try {
             openConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT Destination_City FROM Flight_Plan WHERE Origin_City = ?;");
            stmt.setString(1, originCode);
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                destCode.add(rs.getString("Destination_City"));
            }
            rs.close();
            ps.close();
            closeConnection();
        } catch ( Exception e ) {
            System.out.println("testing process 2");
            e.printStackTrace();
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        ArrayList destCityList = new ArrayList<String>();
        try
        {
            openConnection();
            int i;

            for(i = 0; i < destCode.size(); i++)
            {
                PreparedStatement stmt = conn.prepareStatement("SELECT City FROM Location WHERE City_Code = ?;");
                stmt.setString(1, destCode.get(i).toString());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    destCityList.add(rs.getString("City"));
                }
                rs.close();
                ps.close();
            }
        }
        catch ( Exception e )
        {
            System.out.println("testing process 3");
            e.printStackTrace();
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        closeConnection();
        int j;
        for(j = 0; j < destCityList.size(); j++)
        {
            System.out.println("origin       : " + originCity.toUpperCase());
            System.out.println("destination  : " + destCityList.get(j).toString().toUpperCase());
            System.out.println("\n");
        }
    }

    public static void displayBooking(String origin, String dest)
    {

        AirplaneOrigin = origin;
        AirplaneDest = dest;
        String originCode = " ";
        try {
            openConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT City_Code FROM Location WHERE City = ?;");
            stmt.setString(1, origin);
            rs = stmt.executeQuery();

            //System.out.println("\n\n\nThis is your flight information: \n\n");

                while ( rs.next() ) {
                    originCode = rs.getString("City_Code");
                }

            rs.close();
            ps.close();
            closeConnection();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }


        String destCode = " ";
        try {
            openConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT City_Code FROM Location WHERE City = ?;");
            stmt.setString(1, dest);
            rs = stmt.executeQuery();

            //System.out.println("\n\n\nThis is your flight information: \n\n");

                while ( rs.next() ) {
                    destCode = rs.getString("City_Code");
                }

            rs.close();
            ps.close();
            closeConnection();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        try {

            openConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Flight_Plan WHERE (Origin_City = ?) AND (Destination_City = ?);");
            stmt.setString(1, originCode);
            stmt.setString(2, destCode);

            rs = stmt.executeQuery();

            System.out.println("\n\n\nThis is your flight information: \n\n");
            while ( rs.next() ) {
                while ( rs.next() ) {
                    int routeID = rs.getInt("Route_ID");
                    planeNum = rs.getInt("Airplane_Number");
                    //AirplaneDest = rs.getString("Destination_City");
                    //AirplaneOrigin = rs.getString("Origin_City");
                    flightLength = rs.getString("Flight_Length");
                    departDate = rs.getString("date_of_departure");
                    arrivalDate = rs.getString("date_of_arrival");
                    departTime = rs.getString("time_of_departure");
                    arrivalTime = rs.getString("time_of_arrival");

                    System.out.println( "Route ID = " + routeID);
                    System.out.println( "Airplane Number = " + planeNum);
                    System.out.println( "Origin City = " + AirplaneOrigin);
                    System.out.println( "Destination City = " + AirplaneDest);
                    System.out.println( "DepartDate = " + departDate);
                    System.out.println( "arrivalDate = " + arrivalDate);
                    System.out.println( "departTime = " + departTime);
                    System.out.println( "arrivalTime = " + arrivalTime);
                    System.out.println( "Flight_Length = " + flightLength);
                }
            }
            rs.close();
            ps.close();
            closeConnection();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("SELECT succeeds!");
        assignBookingID();
    }

    //</editor-fold>

//<editor-fold desc="loadData">

    public static void loadFlightPlan()
    {
        StringBuilder sb = new StringBuilder();
        String line = " ";
        String everything = " ";

        try
        {
            //BufferedReader read = new BufferedReader(new FileReader("C:/Users/manoj/Desktop/IdeaProjects/JDBCPostgreSQL/src/HW6Load.SQL"));
            BufferedReader read = new BufferedReader(new FileReader("LoadTest.SQL"));
            line = read.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = read.readLine();
            }
            everything = sb.toString();
            System.out.println();
        }
        catch(java.io.FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(java.io.IOException ex)
        {
            ex.printStackTrace();
        }

        try
        {
            openConnection();
            String sql = everything;
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
            conn.close();
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error inserting data\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }
    //</editor-fold>

//<editor-fold desc="getInfo">
    static ArrayList cityList = new ArrayList();
    private static void getAllAvailableCities(){
        try {
            openConnection();
            Statement stmt = conn.createStatement();
            openConnection();
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            rs = stmt.executeQuery( "SELECT City FROM Location;" );
            while ( rs.next() ) {
                String cities = rs.getString("City");
                cityList.add(cities.toString().toUpperCase());

            }
            rs.close();
            ps.close();
            closeConnection();
            System.out.println("...");
            //System.out.println("SELECT succeeds!");
        }

        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    static int userErrorCount = 0;
    public static void getReservation()
    {
        System.out.println("\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\tRESERVATION\n\n");
        System.out.println("The city options are as follows:" +
                            "\nParis" +
                            "\nNice" +
                            "\nToronto" +
                            "\nMontreal"+
                            "\nNew York" +
                            "\nChicago"+
                            "\nLondon" +
                            "\nEidenburgh\n\n");

        Scanner res = new Scanner(System.in);
        System.out.println("What city are you starting in?: ");
        startCity = res.nextLine().toUpperCase();
        if(cityList.contains(startCity))
        {
            displayAvailableRoutes(startCity);
        }
        else if(userErrorCount == 3)
        {
            userErrorCount++;
            System.out.println("Please enter one of the available cities");
            getReservation();
        }
        else
        {
            System.out.println("city entered isn't in this system\n" +
                    "Thanks for using this reservation system");
            endTransaction("City entered was invalid");

        }
        System.out.println("What city are you going to?: ");
        destCity = res.nextLine().toUpperCase();
        displayBooking(startCity.trim(), destCity.trim());
    }


    public static void getCustomer()
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tCUSTOMER INFORMATION\n\n");
        System.out.println("\t\t\t\t\t\t\t\tPlease Enter in your information as prompted.\n");
        Scanner userData = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        firstName = userData.next();
        System.out.print("\nEnter your last name: ");
        lastName = userData.next();
        assignCustomerID();
    }

    static int wrongZipCodeCounter = 0;
    public static void getAddress(){
        try
        {
            Scanner userData = new Scanner(System.in);
            System.out.print("\n\n\n\t\t\t\t\t\t\tPlease enter your address as the prompts are displayed.\n");
            System.out.print("\nEnter your street: ");
            street = userData.nextLine();
            System.out.print("\nEnter your city: ");
            city = userData.next();
            System.out.print("\nEnter your state: ");
            state = userData.next();
            System.out.print("\nEnter your zip code: ");
            zipCode = userData.nextInt();
            while (Integer.toString(zipCode).length() != 5 && wrongZipCodeCounter != 3 ) {
                wrongZipCodeCounter++;
                System.out.println("zip code must be 5 digits");
                System.out.print("enter zip code: ");
                zipCode = userData.nextInt();
            }
            if(wrongZipCodeCounter == 3){
                endTransaction("The zip code you entered was invalid");
            }
            System.out.print("\nEnter your Country: ");
            country = userData.next();
            assignCustomerAddressID();
        }
        catch(java.util.InputMismatchException e)
        {
            System.out.println("The data you entered is invalid.\nPlease try again.");
            getAddress();
        }
    }

    static int wrongEmailCounter = 0;
    private static void getContact(){
        try {
            Scanner userData = new Scanner(System.in);
            System.out.print("\n\n\n\t\t\t\t\t\t\tPlease enter your contact information as prompted.\n");
            System.out.print("\nEnter your phone number: ");
            phoneNum = userData.nextLine();
            System.out.print("\nEnter your email: ");
            email = userData.next();

            if (!email.contains("@"))
            {
                if(wrongEmailCounter == 3)
                {
                    endTransaction("invalid email");
                }
                else
                {
                    wrongEmailCounter++;
                    System.out.println("Invalid email.\nPlease enter in your information again.");
                    getContact();
                }
            }

        }
        catch(java.util.InputMismatchException e){
            System.out.println("Invalid data.\n Some of your information was not entered in correctly.\n Make sure that you are only entering numbers for the field phone number.");
            getContact();
        }
        countryCode = phoneNum.substring(0,1);
        cityAreaCode = phoneNum.substring(1,4);
        localNumber = phoneNum.substring(4);
        assignCustomerContactID();
    }

    //</editor-fold>

//<editor-fold desc="set and retrieve pks">
    private static void initializePkLists()
    {
        customerIDList.add(1000);
        customerAddressIDList.add(2000);
        customerContactIDList.add(3000);
        bookingIDList.add(4000);
    }

    static ArrayList<Integer> customerIDList = new ArrayList<>();
    private static void assignCustomerID()
    {
        uniqueCustomerID = (customerIDList.get(customerIDList.size() - 1)) + 1;
    }

    static ArrayList<Integer> customerAddressIDList = new ArrayList<>();
    private static void assignCustomerAddressID()
    {
        uniqueCustomerAddressID = (customerAddressIDList.get(customerAddressIDList.size() - 1)) + 1;
    }

    static ArrayList<Integer> customerContactIDList = new ArrayList<>();
    private static void assignCustomerContactID()
    {
        uniqueCustomerContactID = (customerContactIDList.get(customerContactIDList.size() - 1)) + 1;
    }


    static ArrayList<Integer> bookingIDList = new ArrayList<>();

    private static void assignBookingID()
    {
        uniqueBookingID = (bookingIDList.get(bookingIDList.size() - 1)) + 1;
    }

    static void readExistingPrimaryKeys()
    {
        createCustomerKeyList();
        createCustomerAddressKeyList();
        createCustomerContactKeyList();
        createBookingKeyList();
    }

    static void createCustomerKeyList(){
        try {
            openConnection();
            Statement stmt = conn.createStatement();
            openConnection();
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            rs = stmt.executeQuery( "SELECT Unique_Customer_ID FROM Customer;" );
            while ( rs.next() ) {
                int uniqueID = rs.getInt("Unique_Customer_ID");
                customerIDList.add(uniqueID);

            }
            rs.close();
            ps.close();
            closeConnection();
            System.out.println("...");
        }

        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }
    static void createCustomerAddressKeyList()
    {
        try {
            openConnection();
            Statement stmt = conn.createStatement();
            openConnection();
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            rs = stmt.executeQuery( "SELECT Customer_Address_ID FROM Customer_Address;" );
            while ( rs.next() ) {
                int uniqueID = rs.getInt("Customer_Address_ID");
                customerAddressIDList.add(uniqueID);
            }
            rs.close();
            ps.close();
            closeConnection();
            System.out.println("...");
        }

        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    static void createCustomerContactKeyList()
    {
        try {
            openConnection();
            Statement stmt = conn.createStatement();
            openConnection();
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            rs = stmt.executeQuery( "SELECT Customer_Contact_ID FROM Customer_Contact_Info;" );
            while ( rs.next() ) {
                int uniqueID = rs.getInt("Customer_Contact_ID");
                customerAddressIDList.add(uniqueID);

            }
            rs.close();
            ps.close();
            closeConnection();
            System.out.println("...");
        }

        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            endTransaction("There was an error retrieving information from existing data (1011).");
            System.exit(0);
        }

    }

    static void createBookingKeyList()
    {
        try {
            openConnection();
            Statement stmt = conn.createStatement();
            openConnection();
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            rs = stmt.executeQuery( "SELECT Unique_Booking_Number FROM Booking;" );
            while ( rs.next() ) {
                int uniqueID = rs.getInt("Unique_Booking_Number");
                //System.out.println();
                bookingIDList.add(uniqueID);

            }
            rs.close();
            ps.close();
            closeConnection();
            System.out.println("...");
            //System.out.println("SELECT succeeds!");
        }

        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }
//</editor-fold>

//<editor-fold desc="insertData">
    public static void insertCustomer(){
        int customerID = 1;

        assignCustomerID();
        try
        {
            openConnection();
            String sql = "INSERT INTO Customer  VALUES(?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,uniqueCustomerID);
            pst.setString(2,firstName);
            pst.setString(3,lastName);
            pst.executeUpdate();
            pst.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Data inserted into Customer Table successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error inserting data\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }


    public static void insertAddress(){
        int customerID = 1;
        int customerAddressID = 1;
        try
        {
            openConnection();
            String sql = "INSERT INTO Customer_Address  VALUES(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,uniqueCustomerID);
            pst.setInt(2,uniqueCustomerAddressID);
            pst.setString(3,street);
            pst.setString(4,city);
            pst.setString(5,state);
            pst.setInt(6, zipCode);
            pst.setString(7,country);
            pst.executeUpdate();
            pst.close();
            conn.close();

            System.out.println("...");
            //System.out.println("Data inserted into Customer Address Table successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error inserting data Address\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }

    public static void insertContact(){
        int customerID = 1;


        int customerContactID = 1;

        try
        {
            openConnection();
            String sql = "INSERT INTO Customer_Contact_Info  VALUES(?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,uniqueCustomerID);
            pst.setInt(2,uniqueCustomerContactID);
            pst.setInt(3,Integer.parseInt(countryCode));
            pst.setInt(4,Integer.parseInt(cityAreaCode));
            pst.setInt(5,Integer.parseInt(localNumber));
            pst.setString(6, email);
            pst.executeUpdate();
            pst.close();
            conn.close();
            System.out.println("...");
            //System.out.println("Data inserted into Customer contact Table successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error inserting data into contact table\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }

    private static void insertBooking(){

        LocalDateTime date = LocalDateTime.now();

        String dateToday = date.toString().substring(5,7) + "/"
                + date.toString().substring(8, 10) + "/"
                + date.toString().substring(0,4) ;

        String customerPaying = firstName+" "+lastName;
        String nameOnTicket = lastName.toUpperCase() +", "+ firstName.toUpperCase();
        String bookingCity = city;

        try
        {
            openConnection();
            String sql = "INSERT INTO Booking  VALUES(?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, uniqueBookingID);
            pst.setInt(2, planeNum);
            pst.setString(3,dateToday);
            pst.setString(4,customerPaying);
            pst.setString(5,nameOnTicket);
            pst.setInt(6, uniqueCustomerID);
            pst.setString(7, bookingCity);
            pst.executeUpdate();
            pst.close();
            conn.close();
            //System.out.println("Data inserted into Customer contact Table successfully");
        }
        catch (SQLException ex)
        {
            System.err.println( ex.getClass().getName() + ": Error inserting data into contact table\n" );
            ex.printStackTrace();
        }
        closeConnection();
    }
//</editor-fold>

//<editor-fold desc="selectData">
    public static void selectCustomer() {

        try {
            openConnection();
            Statement stmt = conn.createStatement();
            openConnection();
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM Customer;" );
            while ( rs.next() ) {
                int id = rs.getInt("Unique_Customer_ID");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                System.out.println( "ID = " + id );
                System.out.println( "First = " + firstName );
                System.out.println( "Last = " + lastName );
                System.out.println();
            }
            rs.close();
            ps.close();
            closeConnection();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        //System.out.println("SELECT succeeds!");
    }

    public static void selectBooking() {

        try {
            System.out.println("\n\n\nThis is your booking information: ");
            openConnection();
            Statement stmt = conn.createStatement();
            openConnection();
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM Booking;" );
            while ( rs.next() ) {
                int id = rs.getInt("Unique_Booking_Number");
                int flightNum = rs.getInt("Flight_Number");
                String dateOfBooking = rs.getString("Date_Of_Booking");
                String customerPaying = rs.getString("Customer_Paying");
                String nameOnTicket = rs.getString("Customer_Name_On_Ticket");
                int customerID = rs.getInt("Customer_ID");
                String bookingCity = rs.getString("Booking_City");


                System.out.println( "Booking number: " + id );
                System.out.println( "Flight number: " + flightNum );
                System.out.println( "Date of Booking = " + dateOfBooking);
                System.out.println( "Customer paying: " + customerPaying);
                System.out.println( "Name on Ticket: " + nameOnTicket );
                System.out.println( "Customer ID = " + customerID);
                System.out.println( "Booking made in " +bookingCity);

                System.out.println();
            }
            rs.close();
            ps.close();
            closeConnection();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        //System.out.println("SELECT succeeds!");
    }

    static int planeNum;
    public static void selectFlightPlan() {

        try {
            openConnection();
            Statement stmt = conn.createStatement();
            openConnection();
            conn.setAutoCommit(false);
            ps = conn.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM Flight_Plan;" );
            while ( rs.next() ) {
                int routeID = rs.getInt("Route_ID");
                planeNum = rs.getInt("Airplane_Number");
                String AirplaneDest = rs.getString("Destination_City");
                String AirplaneOrigin = rs.getString("Origin_City");
                String  departDate = rs.getString("date_of_departure");
                String  arrivalDate = rs.getString("date_of_arrival");
                String  departTime = rs.getString("time_of_departure");
                String  arrivalTime = rs.getString("time_of_arrival");
                String  flightLength = rs.getString("Flight_Length");
                System.out.println( "ID = " + routeID);
                System.out.println( "AirplaneNum = " + planeNum);
                System.out.println( "AirplaneDest = " + AirplaneDest);
                System.out.println( "AirplaneOrigin = " + AirplaneOrigin);
                System.out.println( "DepartDate = " + departDate);
                System.out.println( "arrivalDate = " + arrivalDate);
                System.out.println( "departTime = " + departTime);
                System.out.println( "arrivalTime = " + arrivalTime);
                System.out.println( "Flight_Length = " + flightLength);
            }
            rs.close();
            ps.close();
            closeConnection();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
       // System.out.println("SELECT succeeds!");
    }
//</editor-fold>

    private static void displayFlightUserDetails()
    {
        try
        {
            java.io.PrintWriter flightAndUserFile = new PrintWriter("HW6.flight");

            flightAndUserFile.println("NAME");
            flightAndUserFile.println();
            flightAndUserFile.println("First Name: " + firstName + "\tLast Name: " + lastName);
            flightAndUserFile.println();
            flightAndUserFile.println();
            flightAndUserFile.println();
            flightAndUserFile.println("CONTACT");
            flightAndUserFile.println();
            flightAndUserFile.println("Phone: " + "(" + countryCode + ") " + cityAreaCode + localNumber);
            flightAndUserFile.println("Email: " + email);
            flightAndUserFile.println("Address:" + street +"\t"+ city + ", " + state+ "\t" + zipCode);
            flightAndUserFile.println();
            flightAndUserFile.println();
            flightAndUserFile.println();
            flightAndUserFile.println();
            flightAndUserFile.println("RESERVATION");
            flightAndUserFile.println();
            flightAndUserFile.println("Flight number: " + planeNum);
            flightAndUserFile.println("From: " + AirplaneOrigin + "\t\t\t\tTo: " + AirplaneDest);
            flightAndUserFile.println("Date of Departure: " + departDate + "\t\tDate of Arrival: " + arrivalDate);
            flightAndUserFile.println("Time of Departure: " + departTime + "\t\tTime of Arrival: " + arrivalTime);
            flightAndUserFile.println("Length of Flight: " + flightLength);
            flightAndUserFile.close();
        }
        catch(java.io.IOException e)
        {
            System.out.println("file doesn't exist");
        }
    }

    private static void printErrorLog(String error)
    {
        try
        {
            PrintWriter write = new PrintWriter("HW6.error");
            write.write(error);
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("File: HW6.error could not be found");
        }
    }

    private static void endTransaction(String error)
    {
        printErrorLog(error);
        System.out.println("An error log has been sent to you explaining what went wrong" +
                            "\n\n\nYou may end this transaction by entering anything");
        Scanner next = new Scanner(System.in);
        next.nextLine();
        System.out.println("Thanks for using this reservation system!");

        System.exit(0);
    }
}
