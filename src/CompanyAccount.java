import java.util.Date;

public class CompanyAccount extends Account {
    private String companyName;
    private String registrationNumber;
    private Date dateOfIncorporation;
    private String signatory;
    private String companyId;

    public CompanyAccount(String accountId, double balance, String companyName, String registrationNumber, Date dateOfIncorporation, String signatory, String companyId) {
        super(accountId, balance, "Company");
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.dateOfIncorporation = dateOfIncorporation;
        this.signatory = signatory;
        this.companyId = companyId;
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

    public String getSignatory() {
        return signatory;
    }

    public String getCompanyId() {
        return companyId;
    }

    @Override
    public String toString() {
        return "CompanyAccount [Company: " + companyName +
               ", Reg#: " + registrationNumber +
               ", Company ID: " + companyId +
               ", Signatory: " + signatory +
               ", Balance: " + balance + "]";
    }
}