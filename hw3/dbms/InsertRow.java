/*
 * InsertRow.java
 *
 * DBMS Implementation
 */

import java.io.*;
import java.util.Arrays;

/**
 * A class that represents a row that will be inserted in a table in a
 * relational database.
 *
 * This class contains the code used to marshall the values of the
 * individual columns to a single key-value pair.
 */
public class InsertRow {
    private Table table;           // the table in which the row will be inserted
    private Object[] columnVals;   // the column values to be inserted
    private RowOutput keyBuffer;   // buffer for the marshalled row's key
    private RowOutput valueBuffer; // buffer for the marshalled row's value
    private int[] offsets;         // offsets for header of marshalled row's value
    
    /** Constants for special offsets **/
    /** The field with this offset has a null value. */
    public static final int IS_NULL = -1;
    
    /** The field with this offset is a primary key. */
    public static final int IS_PKEY = -2;
    
    /**
     * Constructs an InsertRow object for a row containing the specified
     * values that is to be inserted in the specified table.
     *
     * @param  t  the table
     * @param  values  the column values for the row to be inserted
     */
    public InsertRow(Table table, Object[] values) {
        this.table = table;
        this.columnVals = values;
        this.keyBuffer = new RowOutput();
        this.valueBuffer = new RowOutput();
        
        // Note that we need one more offset than value,
        // so that we can store the offset of the end of the record.
        this.offsets = new int[values.length + 1];
    }
    
    /**
     * Takes the collection of values for this InsertRow
     * and marshalls them into a key/value pair.
     * 
     * (Note: We include a throws clause because this method will use 
     * methods like writeInt() that the RowOutput class inherits from 
     * DataOutputStream, and those methods could in theory throw that 
     * exception. In reality, an IOException should *not* occur in the
     * context of our RowOutput class.)
     */
    public void marshall() throws IOException {
        /* 
         * PS 3: Implement this method. 
         * 
         * Feel free to also add one or more private helper methods
         * to do some of the work (e.g., to fill in the offsets array
         * with the appropriate offsets).
         */

        /* NOTES
         * TODO:: 1. Check the columns if they are empty or not.   
         * 2. If not empty, based on the number of columns assign offsets (doesnt matter if empty or not)
         * 3. Find the primary key and store it in the primary key buffer
         * 4. Based on the offsets store the data in the value buffer
         * WARN: Store offsets in an array BEFORE calculating marshalled arrays
         *
         * 1. Additionally, we can get any column from the table.
         * 2. For VarChars, you will getLength(), use strings for data calculation
         * 3. Remember to use type casts for columns and conversions for ints and reals
         * */
        
        try{
           // get the primary key to decide the index 
            Column primaryCol = this.table.primaryKeyColumn() == null ? this.table.getColumn(0) : this.table.primaryKeyColumn();
            // TEST: Primary Key Check
            System.out.println(primaryCol.getValue() + " "+ primaryCol.getIndex() + " " + primaryCol.getType());

            // TEST: Getting column info
            //System.out.print(this.table.getColumn(2).getType());


            // set the primary column index to be -2
            int primKeyCol = primaryCol.getIndex();
            int spaceByOffset = offsets.length*2;
            
            
            offsets[primKeyCol] = -2;
            if (primKeyCol != 0){
                    offsets[0] = spaceByOffset; 
                } else  {
                    offsets[1] = spaceByOffset;
            } 
            // cases:
            // 1. Primary key is first column and no nulls - iterate through as normal 
            // 2. Primary key is first column and one of the columns is null
            // 3. Primary key is not first column, may have null later on
            
            int currOffset = 0; 
            for (int i = 0; i < table.numColumns(); i++){
                // move on if primary key column 
                if (i == primKeyCol){
                    continue;
                }
                int colLen = getLengthForColumn(i); 
                offsets[i] = (columnVals[i] == null) ? -1 : currOffset;
                currOffset += colLen;
                System.out.println(offsets[i]);
            }
            offsets[offsets.length-1] = currOffset;

        } catch (Exception e) {
            System.out.println("Error in marshalling the data");
        } finally {
            System.out.println("In the marshall function for InsertRow");
        }
    }
    
    private int getLengthForColumn(int i) throws IOException{ 
        try {
            Column col = this.table.getColumn(i);
            int typeOfCol = col.getType();
            System.out.println("Column type is - "+ typeOfCol);
            if (typeOfCol != 3){
                return col.getLength();
            } else {
                System.out.println((String)columnVals[i]);
                return ((String)columnVals[i]).length();
            } 
        } catch (Exception e) {
            System.out.println("Error in getting the length of the column");
        }
        return 0;
    }
    
    /**
     * Returns the RowOutput used for the key portion of the marshalled row.
     *
     * @return  the key's RowOutput
     */
    public RowOutput getKeyBuffer() {
        return this.keyBuffer;
    }
    
    /**
     * Returns the RowOutput used for the value portion of the marshalled row.
     *
     * @return  the value's RowOutput
     */
    public RowOutput getValueBuffer() {
        return this.valueBuffer;
    }
    
    /**
     * Returns a String representation of this InsertRow object. 
     *
     * @return  a String for this InsertRow
     */
    public String toString() {
        return "offsets: " + Arrays.toString(this.offsets)
             + "\nkey buffer: " + this.keyBuffer
             + "\nvalue buffer: " + this.valueBuffer;
    }
}
