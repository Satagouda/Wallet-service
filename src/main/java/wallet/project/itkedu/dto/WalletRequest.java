package wallet.project.itkedu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import wallet.project.itkedu.enums.OperationType;

import java.util.UUID;

public class WalletRequest {

    @NotNull
    private UUID walletId;

    @NotNull
    private OperationType operationType;

    @Positive
    private Long amount;


    public UUID getWalletId() {
        return walletId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Long getAmount() {
        return amount;
    }
}
