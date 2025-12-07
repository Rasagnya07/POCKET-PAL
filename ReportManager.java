//ReportManager
import java.util.*;

public class ReportManager {
    private final ExpenseManager em;
    private final Budget budget;
    private final BadgeManager bm;

    public ReportManager(ExpenseManager em, Budget budget, BadgeManager bm) {
        this.em = em; this.budget = budget; this.bm = bm;
    }

    public void showMonthlySummary(double monthlyIncome) {
        System.out.println(ConsoleColors.blue("\n--- Monthly Summary ---"));
        double total = em.getTotalSpent();
        System.out.println("Income: Rs"+monthlyIncome);
        System.out.println("Total spent: Rs"+total);
        System.out.println("Balance: Rs"+(monthlyIncome - total));

        Map<String, Double> byCat = em.getCategoryTotals();
        System.out.println("\nCategory wise:");
        for (var en: byCat.entrySet()) {
            double lim = budget.getLimit(en.getKey());
            System.out.printf("- %s : Rs%.2f (Limit: Rs%.2f)%n", en.getKey(), en.getValue(), lim);
        }

        Map<String, Double> nw = em.getNeedsWantsBreakdown();
        System.out.println("\nNeeds: Rs"+nw.get("needs")+" | Wants: Rs"+nw.get("wants"));
    }
}
