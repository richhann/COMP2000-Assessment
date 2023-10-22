public class CartTableRow {
    private String column1;
    private String column2;
    private String column3;

    public CartTableRow(String c1, String c2, String c3) {
        column1 = c1;
        column2 = c2;
        column3 = c3;
    }

    public String getColumnOne() {
        return column1;
    }

    public String getColumnTwo() {
        return column2;
    }

    public String getColumnThree() {
        return column3;
    }

    public void setColumnThree(String newColumnValue) {
        column3 = newColumnValue;
    }
    
}
