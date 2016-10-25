/*  ---- NAME AND CLASS#----
   NAME : FARHEEN SULTANA 
   CLASS: CS 5620
*/

/* ---- ENTITIES AND THEIR RELATIONSHIP-----
Entities : Customer and Orders
Relationship : Places
The relationship is a one-many relationship.
One Customer places many orders.
ENTITY CUSTOMER : 
    ATTRIBUTES-
        ID   (INTEGER) 
        NAME (VARCHAR)
ENTITY ORDERS :
    ATTRIBUTES-
        CUST_ID (INTEGER)
        ITEM    (VARCHAR)
        PRICE   (INTEGER)
        QTY     (INTEGER)
--A customer can place many orders.
--There can be a customer who places 0/more orders.
--There can be a item which has been ordered by 0/more customers.
*/

/* ----LEARNED FROM ASSIGNMENT----
-Learned how to use command line arguments
-Learned installation of netbeans.
-Learned about ResultSetMetaData class and its methods
*/

/* --- LISTING OF WINDOWS---
Directory FARHEEN DBMS HW contains the following files-

Folder Database               - Access database with 2 tables
Folder JavaApplication7   - A java application Project folder that accesses the two Access tables and displays output in standard format,submits select,delete queries
javaapplication7.java         - it has all the  Program comments: (1) Name and class # (2)  entities and their relationship. (3) ' learnedfrom this assignment'.
		         also includes  listing windows of Netbeans,compilation, run, input, output, and program
1standard_screenshot.png - screenshot of 'standard' option
2select1_screenshot.png    - screenshot of 'select' option displaying Orders table
3select1_screenshot.png    - screeenshot of 'select' option displaying Customer table
4delete_screenshot.png     - screenshot of 'delete' option
ER model.png                  - screenshot of the ER model

*/

/* ---COMPILATION----
ant -f "E:\\FARHEEN DBMS HW\\JavaApplication7" -Dnb.internal.action.name=rebuild clean jar
init:
deps-clean:
Updating property file: E:\FARHEEN DBMS HW\JavaApplication7\build\built-clean.properties
Deleting directory E:\FARHEEN DBMS HW\JavaApplication7\build
clean:
init:
deps-jar:
Created dir: E:\FARHEEN DBMS HW\JavaApplication7\build
Updating property file: E:\FARHEEN DBMS HW\JavaApplication7\build\built-jar.properties
Created dir: E:\FARHEEN DBMS HW\JavaApplication7\build\classes
Created dir: E:\FARHEEN DBMS HW\JavaApplication7\build\empty
Created dir: E:\FARHEEN DBMS HW\JavaApplication7\build\generated-sources\ap-source-output
Compiling 1 source file to E:\FARHEEN DBMS HW\JavaApplication7\build\classes
compile:
Created dir: E:\FARHEEN DBMS HW\JavaApplication7\dist
Copying 1 file to E:\FARHEEN DBMS HW\JavaApplication7\build
Nothing to copy.
Building jar: E:\FARHEEN DBMS HW\JavaApplication7\dist\JavaApplication7.jar
To run this application from the command line without Ant, try:
E:\Campus\Courses\DBMS-5620\32bitjdk1\jdk1.7.0_13/bin/java -jar "E:\FARHEEN DBMS HW\JavaApplication7\dist\JavaApplication7.jar"
jar:
BUILD SUCCESSFUL (total time: 2 seconds)
*/

/*----RUN----
INPUT:
command line argument= standard 
OUTPUT :
run:
You have selected 'standard' option

Metadata of Customer Entity
Number of columns= 2
Attribute Name= ID	Attribute Type= INTEGER
Attribute Name= NAME	Attribute Type= VARCHAR

Metadata of Orders Entity
Number of columns= 4
Attribute Name= CUST_ID	Attribute Type= INTEGER
Attribute Name= ITEM	Attribute Type= VARCHAR
Attribute Name= PRICE	Attribute Type= INTEGER
Attribute Name= QTY	Attribute Type= INTEGER

ID=6215  NAME=Mary
	ITEM	PRICE	QTY
	Pizza	12	1	
	Burger	15	2	
	Coke	10	2	

ID=7142  NAME=Sheena
	ITEM	PRICE	QTY
	Chicken	18	5	
	Cake	13	1	

ID=3216  NAME=Esther
	ITEM	PRICE	QTY
	Fish	20	10	
	Coffee	15	4	

ID=6115  NAME=Shannon
	ITEM	PRICE	QTY
	Shrimp	13	7	
BUILD SUCCESSFUL (total time: 0 seconds)

*/

/* ----RUN----
INPUT: 
command line argument=select
query= select * from Orders;
OUTPUT:
run:

You have selected 'select' option 
 Please enter a select query 

select * from Orders;
CUST_ID	ITEM	PRICE	QTY	
6215	Pizza	12	1	
6215	Burger	15	2	
6215	Coke	10	2	
7142	Chicken	18	5	
7142	Cake	13	1	
3216	Fish	20	10	
3216	Coffee	15	4	
6115	Shrimp	13	7	
BUILD SUCCESSFUL (total time: 10 seconds)
*/

/*----- RUN-----
INPUT:
Command line argument= delete
query= delete from Customer where ID=6115

OUTPUT:
run:

You have selected `delete` option 
 Please enter a delete query
delete from Customer where ID=6115
Delete query has been executed ! 
 1 row(s) deleted
Number of rows before delete query execution= 12
Number of rows after delete query execution= 11
BUILD SUCCESSFUL (total time: 45 seconds)
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication7;
import java.io.*; // io package has BufferedReader class
import java.sql.*; // sql package has classes like Connection,DriverManager etc
/**
 *
 * @author farheen sultana
 */

public class JavaApplication7
{

    /**
     * @param args the command line arguments
    */
    
    static String nameOfJdbcOdbcDriver ="sun.jdbc.odbc.JdbcOdbcDriver";
    static String dataBaseNameDSN = "jdbc:odbc:mydatasource";
    static String userName = "";
    static String passwordForUser = "";

    static Connection con = null;
    static Statement st= null;
    static ResultSet rs = null;
    static ResultSetMetaData myResultTuplesMetaData = null;
    
    public static void main(String[] args) throws Exception
    {
        try
        {
        
            // TODO code application logic here
            
            //BufferedReader is used to take input from terminal
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
               
            //Identify the driver to use
            Class.forName(nameOfJdbcOdbcDriver);

            //Attempt a connection to database...
            con=DriverManager.getConnection(dataBaseNameDSN,userName,passwordForUser);

            //Create a statement object, use its method to execute query
            st = con.createStatement();
            
            //--->If user sends standard as command line option then this if is executed
            
            if("standard".equals(args[0]))
            {
                System.out.println("You have selected 'standard' option");
                
                //Printing the metadata of entity- Customer

                String q1="select * from Customer";   //this is a select query,used to retrieve the tuples from Customer relation
                rs=st.executeQuery(q1);  //executeQuery() method is used to submit select query.
                
                //ResultSetMetaData :
                //with this object we can find out what kind of data existed in ResultSet object.

                //ResultSetMetaData  rsmd=rs.getMetaData();
                //methods:

                //int getColumnCount() -->it returns no of columns in the ResultSet object
                //String getColumnName(int)--> it returns specified column name
                //String getColumnTypeName(int )--> it returns the datatype of the specified Column.

                
                myResultTuplesMetaData=rs.getMetaData(); 
                int num=myResultTuplesMetaData.getColumnCount();
                
                System.out.println("\nMetadata of Customer Entity");
                System.out.println("Number of columns= "+num);
                
                for(int n=1;n<=num;n++) //this for loop retrieves  and prints the attribute names,types of entity Customer
                {
                    System.out.print("Attribute Name= "+myResultTuplesMetaData.getColumnName(n)+"\t");    //prints the attribute names
                    System.out.print("Attribute Type= "+myResultTuplesMetaData.getColumnTypeName(n)+"\n"); //prints the attribute type
                }
                System.out.println();
                
                //Printing the metadata of entity- Orders
                
	String q2="select * from Orders";
                rs=st.executeQuery(q2);
                myResultTuplesMetaData=rs.getMetaData();
                int num1=myResultTuplesMetaData.getColumnCount();
                
                System.out.println("Metadata of Orders Entity");
                System.out.println("Number of columns= "+num1);
                
                for(int n=1;n<=num1;n++) //for loop retrieves  and prints the attribute names,types of the entity Orders
                {
                    System.out.print("Attribute Name= "+myResultTuplesMetaData.getColumnName(n)+"\t");
                    System.out.print("Attribute Type= "+myResultTuplesMetaData.getColumnTypeName(n)+"\n");
                }
                
                //The code below aims to print the data from Customer,Orders in the 'standard' format

                String queryToBeExecuted = "select * from Customer,Orders where Customer.ID =Orders.CUST_ID"; //this query finds all the attributes in 2nd entity which has a relationship with the instances in 1st entity
                rs = st.executeQuery(queryToBeExecuted);
                myResultTuplesMetaData = rs.getMetaData();
                int numberOfAttributes = myResultTuplesMetaData.getColumnCount();
                
                //Printing  the output in the specified format
                int b=0;
                for(int rowNum = 1; rs.next(); rowNum++) 
                {
                    int a=Integer.parseInt(rs.getString(1));  //first column is the ID of Customer
                    String s=rs.getString(2);  //Second column is the Name of Customer
                    
                    // If it is a multiple instance , then there is no need to print the attribute name again
                    //'b' stores the previous id of customer and 'a' stores the current id
                    //if a!=b means the id has not existed before. thus,it needs to be printed 
                    //if a=b means the id was printed before and the present tuple is just a multiple order/instance of the prev id
                    
                    //printing the attribute names of Customers with values and the atributes names of Orders entity 
                    if(a!=b) 
                    {
                        b=a;
                        System.out.print("\n");
                        System.out.print(myResultTuplesMetaData.getColumnName(1)+"="+a+"  ");   //retrieving metadata- the name of attribute1 
                        System.out.println(myResultTuplesMetaData.getColumnName(2)+"="+s);  // retrieving metadatathe name of attribute 2
                        System.out.println("\t"+myResultTuplesMetaData.getColumnName(4)+"\t"+myResultTuplesMetaData.getColumnName(5)+"\t"+myResultTuplesMetaData.getColumnName(6)); 	// retrieving the metadata of orders
                    }
                    System.out.print("\t"); 	// \t provides the indendation for the output values of 2nd entity
                    
                    //printing the tuples of 2nd entity
                    for(int t=4;t<=numberOfAttributes;t++)
                    {
                        System.out.print(rs.getString(t)+"\t"); // retrieving the orders placed by the particular id 'a'
                    }
                    System.out.println(); //insert new line for the next instance 
                }
            } // standard option code ends
            
            //---> if user sends 'select' as command line option then this 'if condition' is executed 
            
            if("select".equals(args[0]))
            {
                // prompting the user to enter a 'select' query
        
	System.out.println("\nYou have selected 'select' option \n Please enter a select query \n");
                String s1=br.readLine(); //reading the 'select' query from terminal
                
                rs = st.executeQuery(s1); //executeQuery() method submits the select query and returns a ResultSet object
                myResultTuplesMetaData = rs.getMetaData(); 
        	int numberOfAttributes = myResultTuplesMetaData.getColumnCount(); //retrieving metadata-number of attributes
        
                // this for loop prints the metadata-attribute name of the user desired table
                for(int n=1;n<=numberOfAttributes;n++) 
                {
                    System.out.print(myResultTuplesMetaData.getColumnName(n)+"\t");
                }
                System.out.println(); // metadata is printed. insert a new line for the data
                
                //this for loop prints the tuples of the user desired table
                for(int r=1;rs.next();r++) 
                {
                    for(int c=1;c<=numberOfAttributes;c++)
                    {
                        System.out.print(rs.getString(c)+"\t"); //the attribute values are separated by a tab space
                    }
                    System.out.println();//insert a new line for the next tuple
                }
            }//if-select code ends
            
            //---->if user sends 'delete' as command line option then this 'if condition' is executed 
            
            if("delete".equals(args[0]))
            {
                int count1=0,count2=0,count3=0,count4=0;
            
                //prompting the user to enter a 'delete query'
                System.out.println("\nYou have selected `delete` option \n Please enter a delete query");
                
                //the two while loops below count the number of tuples before delete query is executed
                //this while loop counts the number of tuples in Customer table
  
	String b1="select * from Customer";
                rs=st.executeQuery(b1);
                while(rs.next())
                {
                    count1++;
                }
  
                //this while loop counts the number of tuples in Orders table
  
                String b2="select * from Orders";
                rs=st.executeQuery(b2);
                while(rs.next())
                {
                    count2++;
                }
                int total1=count1+count2; // total1= total number of tuples before delete query executes
                
                String s2=br.readLine(); //reading the delete query from terminal
                int d=st.executeUpdate(s2); //executeUpdate() method is used to submit delete query and it returns the number of rows/tuples deleted
                
                System.out.println("Delete query has been executed ! \n "+d+ " row(s) deleted");
                
                //the two while loops below counts the number of tuples after delete query is executed
                
                //this while loop counts the number of tuples in Customer table
                String c1="select * from Customer";
                rs=st.executeQuery(c1);
                while(rs.next())
                {
                    count3++;
                }
                
                //this while loop counts the number of tuples in orders table
                String c2="select * from Orders";
                rs=st.executeQuery(c2);
                while(rs.next())
                {
                    count4++;
                }
                int total2=count3+count4; //total2=total number of tuples after delete query execution
                
                System.out.println("Number of rows before delete query execution= "+total1); 
                System.out.println("Number of rows after delete query execution= "+total2);
            }//if delete ends
        }//try ends
        
        //handle ALL exceptions to above database calls
        catch (SQLException sqlError)
        {
            System.out.println("Unexpected exception : " +sqlError.toString() + ", sqlstate = " +sqlError.getSQLState());
        }

    }//main ends
}//class ends
            
            
            
            
            