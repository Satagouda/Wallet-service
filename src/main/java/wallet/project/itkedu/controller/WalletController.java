package wallet.project.itkedu.controller;

import org.springframework.web.bind.annotation.*;
import wallet.project.itkedu.dto.WalletRequest;
import wallet.project.itkedu.dto.WalletResponse;
import wallet.project.itkedu.service.WalletService;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class WalletController {
    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping("/wallet")
    public WalletResponse process(@Valid @RequestBody WalletRequest request) {
        return service.process(request);
    }

    @GetMapping("/wallets/{walletId}")
    public WalletResponse getBalance(@PathVariable UUID walletId) {
        return service.getBalance(walletId);
    }
}
