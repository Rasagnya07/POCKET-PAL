//FileManager
/*import java.util.*;
import java.io.*;

public class FileManager {
    private static final String EXP_FILE = "expenses.csv";
    private static final String BUDGET_FILE = "budgets.csv";
    private static final String STREAK_FILE = "streak.txt";

    // Expenses
    public List<Expense> loadExpenses() {
        List<Expense> out = new ArrayList<>();
        List<String> lines = UtilsFileHelpers.readAllLines(EXP_FILE);
        for (String L: lines) {
            Expense e = Expense.fromCSV(L);
            if (e!=null) out.add(e);
        }
        return out;
    }

    public void saveExpenses(List<Expense> expenses) {
        List<String> lines = new ArrayList<>();
        for (Expense e: expenses) lines.add(e.toCSV());
        UtilsFileHelpers.writeAllLines(EXP_FILE, lines);
    }

    // Budgets
    public void saveBudget(Budget budget) {
        UtilsFileHelpers.writeAllLines(BUDGET_FILE, budget.toLines());
    }

    public Budget loadBudget() {
        Budget b = new Budget();
        List<String> lines = UtilsFileHelpers.readAllLines(BUDGET_FILE);
        if (!lines.isEmpty()) b.loadFromLines(lines);
        return b;
    }

    // Streak
    public void saveStreak(LocalDate date, int streak) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(STREAK_FILE))) {
            pw.println((date==null? "" : date.toString()) + "," + streak);
        } catch (Exception e) {}
    }

    public String[] loadStreak() {
        List<String> lines = UtilsFileHelpers.readAllLines(STREAK_FILE);
        if (lines.isEmpty()) return null;
        String[] p = lines.get(0).split(",",2);
        return p;
    }
} */



import java.util.*;
import java.io.*;
import java.time.LocalDate; // <-- added import

public class FileManager {
    private static final String EXP_FILE = "expenses.csv";
    private static final String BUDGET_FILE = "budgets.csv";
    private static final String STREAK_FILE = "streak.txt";

    // Expenses
    public List<Expense> loadExpenses() {
        List<Expense> out = new ArrayList<>();
        List<String> lines = UtilsFileHelpers.readAllLines(EXP_FILE);
        for (String L: lines) {
            Expense e = Expense.fromCSV(L);
            if (e!=null) out.add(e);
        }
        return out;
    }

    public void saveExpenses(List<Expense> expenses) {
        List<String> lines = new ArrayList<>();
        for (Expense e: expenses) lines.add(e.toCSV());
        UtilsFileHelpers.writeAllLines(EXP_FILE, lines);
    }

    // Budgets
    public void saveBudget(Budget budget) {
        UtilsFileHelpers.writeAllLines(BUDGET_FILE, budget.toLines());
    }

    public Budget loadBudget() {
        Budget b = new Budget();
        List<String> lines = UtilsFileHelpers.readAllLines(BUDGET_FILE);
        if (!lines.isEmpty()) b.loadFromLines(lines);
        return b;
    }

    // Streak
    public void saveStreak(LocalDate date, int streak) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(STREAK_FILE))) {
            pw.println((date==null? "" : date.toString()) + "," + streak);
        } catch (Exception e) {}
    }

    public String[] loadStreak() {
        List<String> lines = UtilsFileHelpers.readAllLines(STREAK_FILE);
        if (lines.isEmpty()) return null;
        String[] p = lines.get(0).split(",",2);
        return p;
    }
}

