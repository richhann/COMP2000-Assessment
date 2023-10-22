/**
 * DO NOT MODIFY THIS CLASS
 * 
 * Used to pass a row of data to the table in the UI
 */
public class InventoryTableRow {
    private String column1;
    private String column2;
    private String column3;
    private String column4;

    public InventoryTableRow(String c1, String c2, String c3, String c4) {
        column1 = c1;
        column2 = c2;
        column3 = c3;
        column4 = c4;
    }

    String getColumnOne() {
        return column1;
    }

    String getColumnTwo() {
        return column2;
    }
    
    String getColumnThree() {
        return column3;
    }

    String getColumnFour() {
        return column4;
    }

}
