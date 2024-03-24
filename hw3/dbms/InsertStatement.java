/*
 * InsertStatement.java
 *
 * DBMS Implementation
 */

import java.util.*;
import com.sleepycat.je.*;

/**
 * A class that represents an INSERT statement.
 */
public class InsertStatement extends SQLStatement {
    /** 
     * Constructs an InsertStatement object involving the specified table,
     * list of columns (if any), and list of values.  The columns and 
     * their associated values must be specified in the same order.
     * If no list of columns is specified, we will assume that values are 
     * being specified for all columns.
     *
     * @param  t  the table in which the values should be inserted
     * @param  colList  the list of columns for which values are specified
     * @param  valList  the list of values to be inserted
     */
    public InsertStatement(Table t, ArrayList<Column> colList,
                           ArrayList<Object> valList) {
        super(t, colList, valList);
    }
    
    public void execute() throws DatabaseException, DeadlockException {
        try {
            Table table = this.getTable(0);
            if (table.open() != OperationStatus.SUCCESS) {
                throw new Exception();  // error msg was printed in open()
            }
            
            // Preliminary error checking.
            if (this.numColumns() != 0) {
                throw new Exception("INSERT commands with column names " +
                                    "are not supported");
            }
            if (this.numColumnVals() != table.numColumns()) {
                throw new Exception("Must specify a value for each column");
            }
            
            // Make any necessary adjustments (type conversions, 
            // truncations, etc.) to the values to be inserted.
            // This will throw an exception if a value is invalid.
            Object[] adjustedValues = new Object[table.numColumns()];
            for (int i = 0; i < table.numColumns(); i++) {
                Column col = table.getColumn(i);
                adjustedValues[i] = col.adjustValue(this.getColumnVal(i));
            }
            
            // Create an InsertRow object for the row to be inserted,
            // and use that object to marshall the row.
            InsertRow row = new InsertRow(table, adjustedValues);
            row.marshall();
            if (DBMS.DEBUG) {
                System.out.println(row);
            }
            
            /* 
             * PS 3: Add code below to perform the actual insertion, 
             * and to print the appropriate message after it has occurred.
             *
             * uses the byte arrays from the RowOutput objects in the InsertRow object to construct the necessary Berkeley DB objects for the key/value pair
             * adds the key/value pair to the underlying BDB database.
             */
            RowOutput keyBuffer = row.getKeyBuffer();
            RowOutput valueBuffer = row.getValueBuffer(); 
            
            byte[] bytes = keyBuffer.getBufferBytes();
            int numBytes = keyBuffer.getBufferLength();
            System.out.println("Bytes are "+ bytes + " numBytes are "+numBytes + "\n" + keyBuffer.toString());     
            DatabaseEntry key = new DatabaseEntry(bytes, 0, numBytes);

            bytes = valueBuffer.getBufferBytes();
            numBytes = valueBuffer.getBufferLength();
         
            System.out.println("Bytes are "+ bytes + " numBytes are "+numBytes + "\n" + keyBuffer.toString());

            DatabaseEntry value = new DatabaseEntry(bytes, 0, numBytes);
            // https://docs.oracle.com/cd/E17276_01/html/java/com/sleepycat/db/Database.html#get-com.sleepycat.db.Transaction-com.sleepycat.db.DatabaseEntry-com.sleepycat.db.DatabaseEntry-com.sleepycat.db.LockMode-            
            // check if the key/value pair exists or not
            /**
             *
             * public OperationStatus get(Transaction txn,
                           DatabaseEntry key,
                           DatabaseEntry data,
                           LockMode lockMode)
                    throws DatabaseException
             *
             * */
            
            Database db = table.getDB();
            // OperationStatus status = db.get(null, key, value, LockMode.DEFAULT);
            // // can allow DEFAULT, DIRTY_READ, RMW. I'm leaving the default
            // if (status == OperationStatus.SUCCESS){
            //     throw new Exception("For db "+ db.getDatabaseName() + " key "+ key + " with key value pairs already exists");
            // }else{
            //     System.out.println("status is " + status);
            // }
            OperationStatus resultOfPut = db.putNoOverwrite(null, key, value);
            if (resultOfPut == OperationStatus.KEYEXIST){
                throw new Exception("For db "+ db.getDatabaseName() + " key "+ key + " with key value pairs already exists"); 
            }
            System.out.println("Successfully inserted key value pair");
        

        } catch (Exception e) {
            if (DBMS.DEBUG) {
                e.printStackTrace();
            }
            String errMsg = e.getMessage();
            System.out.println(e);
            if (errMsg != null) {
                System.err.println(e + ": " + errMsg + ".");
            }
            System.err.println("Could not insert row.");
        }
    }
}
