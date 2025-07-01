package net.javaguides.banking_app.controller;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    //Add Account RestAPI
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account RESTAPI
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountbyId(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }
    //Deposit RESTAPI
    @PutMapping("/{id}/deposit")

    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double>request){
        double amount=request.get("amount");
        AccountDto accountDto=accountService.deposit(id ,amount);
        return ResponseEntity.ok(accountDto);
    }

//withdrow rest api
    @PutMapping("/{id}/withdraw")


public ResponseEntity<AccountDto> withdraw( @PathVariable Long id,@RequestBody Map<String,Double >request){
    double amount=request.get("amount");
    AccountDto accountDto=accountService.withdraw(id,amount);
    return ResponseEntity.ok(accountDto);
}
//get all accounts RESTAPI
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts=accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);

    }
}