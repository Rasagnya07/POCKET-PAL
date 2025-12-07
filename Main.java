//Main
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Setup
        ConsoleColors cc = new ConsoleColors();
        Budget budget = new Budget();
        FileManager fm = new FileManager();
        // load persisted budget if exists
        Budget loaded = fm.loadBudget();
        if (loaded!=null) budget = loaded;

        ExpenseManager em = new ExpenseManager(budget);
        // load expenses & streak
        em.loadFromFile(fm);

        BadgeThresholds bth = new BadgeThresholds();
        BadgeManager bm = new BadgeManager(bth);
        ReportManager rm = new ReportManager(em, budget, bm);
        BudgetManager bman = new BudgetManager(budget);

  // Ask user's name immediately on prompt
        String userName = Utils.input("Hey! What's your name? ");
        if (userName == null || userName.isBlank()) userName = "Friend";


        double monthlyIncome = 0;
        try {
            String s = Utils.input("Enter monthly pocket money (Rs) (press Enter to skip): ");
            if (!s.isBlank()) monthlyIncome = Double.parseDouble(s);
        } catch (Exception e){ monthlyIncome = 0; }


 // Calculate remaining balance immediately and display greeting + balance
        double totalSpent = em.getTotalSpent(); // uses Budget's tracked spent
        double remaining = monthlyIncome - totalSpent;

        System.out.println(ConsoleColors.bold("\nHello " + userName + "!"));
        if (monthlyIncome > 0) {
            System.out.println(ConsoleColors.cyan("Remaining balance for this month: Rs " +
                    String.format("%.2f", remaining)));
        } else {
            // If no monthly income provided, show total spent so user knows status
            System.out.println(ConsoleColors.cyan("You have spent Rs " + String.format("%.2f", totalSpent) +
                    " so far. (No monthly pocket money set)"));
        }



        while (true) {
            System.out.println(ConsoleColors.bold("\n==== POCKETPAL MENU ===="));
            System.out.println("1. Add expense");
            System.out.println("2. Show expenses");
            System.out.println("3. Budgets");
            System.out.println("4. Badges");
            System.out.println("5. Reports");
            System.out.println("6. EMI Calculator");
            System.out.println("7. Save & Exit");
            int ch = Utils.inputInt("Choose: ");
            if (ch==1) {
                em.addExpenseInteractive();
                fm.saveExpenses(em.getExpenses());
                fm.saveBudget(budget);
                bm.updateProgressFromExpenseManager(em, budget);
            } else if (ch==2) {
                em.showExpenses();
                Utils.pause();
            } else if (ch==3) {
                bman.setBudgetLimitInteractive();
                fm.saveBudget(budget);
            } else if (ch==4) {
                bm.updateProgressFromExpenseManager(em, budget);
                bm.showBadges();
                String want = Utils.input("See suggestion for badge (name) or Enter to skip: ");
                if (!want.isBlank()) System.out.println(bm.getSuggestionFor(want, em, budget));
                Utils.pause();
            } else if (ch==5) {
                rm.showMonthlySummary(monthlyIncome);
                Utils.pause();
            } else if (ch==6) {
                double p = Utils.inputDouble("Principal (Rs): ");
                double r = Utils.inputDouble("Annual rate (%): ");
                int m = Utils.inputInt("Months: ");
                double emi = EMICalculator.calculateEMI(p,r,m);
                System.out.println(ConsoleColors.green("EMI: Rs"+String.format("%.2f", emi)));
                Utils.pause();
            } else if (ch==7) {
                fm.saveExpenses(em.getExpenses());
                fm.saveBudget(budget);
                System.out.println(ConsoleColors.green("Saved. Bye!"));
                break;
            } else {
                System.out.println(ConsoleColors.red("Invalid choice"));
            }
        }
    }
}
