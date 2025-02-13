package net.kubeworks.kubedashboard.app.api.web;

import net.kubeworks.kubedashboard.feature.account.model.AccountSearchResult;
import net.kubeworks.kubedashboard.feature.account.service.AccountSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountSearchService accountSearchService;

    public AccountController(AccountSearchService accountSearchService) {
        this.accountSearchService = accountSearchService;
    }

    @GetMapping
    public List<AccountSearchResult> search(@RequestParam String query) {
        return accountSearchService.searchAll();
    }

}
