package danghaianhde190966.example;

public class InsuranceClaim {
    private String claimId;
    private double amount;
    private String claimStatus;

    public InsuranceClaim(String claimId, double amount) {
        if(claimId == null || claimId.isEmpty()) {
            throw new IllegalArgumentException("claimId cannot be null or empty");
        }
        if(amount <= 0) {
            throw new IllegalArgumentException("amount cannot be negative");
        }
        this.claimId = claimId;
        this.amount = amount;
        this.claimStatus = "Pending";
    }

    public String getClaimId() {
        return claimId;
    }

    public double getAmount() {
        return amount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public boolean processClaim(String newStatus) {
        if(newStatus == null || newStatus.isEmpty()) {
            throw new IllegalArgumentException("newStatus cannot be null or empty");
        }
        if(!"Pending".equals(claimStatus)) {
            return false;
        }
        this.claimStatus = newStatus;
        return true;
    }

    public double calculatePayout() {
        if("Approved".equals(claimStatus)) {
            return amount*0.85;
        }
        return 0.0;
    }

    public void updateClaimAmount(double newAmount) {
        if(newAmount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = newAmount;
    }

    @Override
    public String toString() {
        return  "InsuranceClaim{" +  "claimId= " +'\'' + claimId + '\'' +
                                    ", amount= " + amount +
                                    ", claimStatus= " + '\'' + claimStatus + '\'' + "}";
    }
}
