package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account createNewAccount(Account account) {
        if (account.getUsername() == null || account.getUsername().length() <= 0) return null;
        if (account.getPassword().length() < 4) return null;
        return accountDAO.createNewAccount(account);
    }

    public Account verifyLogin(Account account) {
        if (account.getUsername() == null || account.getUsername().length() <= 0) return null;
        if (account.getPassword().length() < 4) return null;
        return accountDAO.verifyLogin(account);
    }


}
