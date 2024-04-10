package symptomtracker.controllers;

import symptomtracker.entities.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import symptomtracker.services.AccountService;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping(path = "/{username}")
    private ResponseEntity<Account> findByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(accountService.findByUsername(username));
    }
    @PostMapping
    private ResponseEntity<Account> create(@Valid @RequestBody Account a) {
        return ResponseEntity.ok(accountService.create(a));
    }

    @PutMapping
    private ResponseEntity<Account> update(@Valid @RequestBody Account a) {
        return ResponseEntity.ok(accountService.update(a));
    }

    @DeleteMapping("/{accountId}")
    private ResponseEntity<String> delete(@PathVariable("accountId") Integer accountId) {
        accountService.delete(accountId);
        return ResponseEntity.ok("Account successfully deleted.");
    }
}
