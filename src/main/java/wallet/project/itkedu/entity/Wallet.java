package wallet.project.itkedu.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    protected Wallet() {
        // JPA only
    }

    public Wallet(UUID id, Long balance) {
        this.id = id;
        this.balance = balance;
        this.version = 0L;
    }

    public UUID getId() {
        return id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
