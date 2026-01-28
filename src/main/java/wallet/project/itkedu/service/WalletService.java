package wallet.project.itkedu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wallet.project.itkedu.dto.WalletRequest;
import wallet.project.itkedu.dto.WalletResponse;
import wallet.project.itkedu.entity.Wallet;
import wallet.project.itkedu.enums.OperationType;
import wallet.project.itkedu.exception.InsufficientFundsException;
import wallet.project.itkedu.exception.WalletNotFoundException;
import wallet.project.itkedu.repository.WalletRepository;

import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public WalletResponse process(WalletRequest request) {

        Wallet wallet = repository.findById(request.getWalletId())
                .orElseThrow(WalletNotFoundException::new);

        if (request.getOperationType() == OperationType.WITHDRAW
                && wallet.getBalance() < request.getAmount()) {
            throw new InsufficientFundsException();
        }

        long newBalance =
                request.getOperationType() == OperationType.DEPOSIT
                        ? wallet.getBalance() + request.getAmount()
                        : wallet.getBalance() - request.getAmount();

        wallet.setBalance(newBalance);

        return new WalletResponse(wallet.getId(), wallet.getBalance());
    }

    @Transactional(readOnly = true)
    public WalletResponse getBalance(UUID walletId) {
        Wallet wallet = repository.findById(walletId)
                .orElseThrow(WalletNotFoundException::new);

        return new WalletResponse(wallet.getId(), wallet.getBalance());
    }
}
