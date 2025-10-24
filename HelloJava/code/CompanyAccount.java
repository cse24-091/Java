import java.util.Date;

public class CompanyAccount extends Account {
    private String companyName;
    private String registrationNumber;
    private Date dateOfIncorporation;

    public CompanyAccount(String accountId, double balance, String companyName, String registrationNumber, Date dateOfIncorporation) {
        super(accountId, balance, "Company");
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Date getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    @Override
    public String toString() {
        return "CompanyAccount [Company: " + companyName + ", Reg#: " + registrationNumber + ", Balance: " + balance + "]";
    }
}
