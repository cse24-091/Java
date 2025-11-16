package hellofx.model;

public class Customer {
    private String customerID;
    private String name;
    private String password;
    private String type; // Person or Company
    private String taxID;
    private String incomeSource;
    private String signatory;
    private String idNumber;
    private String createdDate;

    public Customer(String customerID, String name, String password, String type,
                    String taxID, String incomeSource, String signatory,
                    String idNumber, String createdDate) {
        this.customerID = customerID;
        this.name = name;
        this.password = password;
        this.type = type;
        this.taxID = taxID;
        this.incomeSource = incomeSource;
        this.signatory = signatory;
        this.idNumber = idNumber;
        this.createdDate = createdDate;
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getType() { return type; }
    public String getTaxID() { return taxID; }
    public String getIncomeSource() { return incomeSource; }
    public String getSignatory() { return signatory; }
    public String getIdNumber() { return idNumber; }
    public String getCreatedDate() { return createdDate; }
}
