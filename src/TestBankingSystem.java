package hellofx.src;
public class TestBankingSystem {
    public static void main(String[] args) {
        try {
            // Create a teller
            Teller teller = new Teller("Mrs Mokote");

            // Create a branch
            Branch gaboroneBranch = new Branch("B001", "Gaborone Main", "Gaborone");

            // Create a customer and assign the branch
            Customer customer = new Customer(
                "Katlego Lechiile",
                "Gaborone",
                "71234567",
                "katlego@bank.com",
                1001,
                gaboroneBranch
            );

            // Add customer to branch
            gaboroneBranch.addCustomer(customer);

            // Teller assists customer
            teller.assistCustomer(customer);

            // Create accounts
            ChequeAccount cheque = new ChequeAccount("CHQ001", 3000.0);
            InvestmentAccount investment = new InvestmentAccount("INV001", 1000.0, 5.0); // 5% monthly
            SavingsAccount savings = new SavingsAccount("SAV001", 2000.0, 3.0); // 3% monthly

            // Add accounts to customer
            customer.addAccount(cheque);
            customer.addAccount(investment);
            customer.addAccount(savings);

            // Perform transactions
            cheque.deposit(1500.0);
            cheque.withdraw(700.0);

            investment.deposit(500.0);
            investment.withdraw(300.0);
            investment.investMonthlyInterest();

            savings.applyMonthlyInterest();

            // Show transaction history
            System.out.println("\n--- Transaction History ---");
            customer.viewTransactionHistory();

            // Show final balances
            System.out.println("\n--- Final Balances ---");
            System.out.println("Cheque Account: BWP " + cheque.getBalance());
            System.out.println("Investment Account: BWP " + investment.getBalance());
            System.out.println("Savings Account: BWP " + savings.getBalance());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
