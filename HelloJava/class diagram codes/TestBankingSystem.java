public class TestBankingSystem {
    public static void main(String[] args) {
        try {
            Teller teller = new Teller("Mrs Mokote");
            Customer customer = new Customer("Katlego Lechiile", "Gaborone", "71234567", "katlego@bank.com", 1001);

            teller.assistCustomer(customer);

            // Create accounts
            InvestmentAccount invAcc = new InvestmentAccount("INV001", 1000.0, 5.0);
            SavingsAccount savAcc = new SavingsAccount("SAV001", 2000.0, 3.0);

            customer.addAccount(invAcc);
            customer.addAccount(savAcc);

            // Transactions
            invAcc.depositAmount(500);
            invAcc.withdrawAmount(300);
            savAcc.applyMonthlyInterest();
            invAcc.investMonthlyInterest();

            // Display transactions
            customer.viewTransactionHistory();

            System.out.println("\nFinal Balances:");
            System.out.println("Investment Account: BWP " + invAcc.getBalance());
            System.out.println("Savings Account: BWP " + savAcc.getBalance());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
