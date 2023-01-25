package pl.test.exchangerate.dtos;

/**
 * @author prabesh on 25/Jan/2023
 */
public class TotalCostResponse {
    private final double totalCost;

    public TotalCostResponse(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
