//EMICalculator
public class EMICalculator {
    public static double calculateEMI(double principal, double annualRatePercent, int months) {
        if (months<=0) return 0.0;
        double r = annualRatePercent/12.0/100.0;
        if (r==0) return principal/months;
        double pow = Math.pow(1+r, months);
        return (principal*r*pow)/(pow-1);
    }
}
