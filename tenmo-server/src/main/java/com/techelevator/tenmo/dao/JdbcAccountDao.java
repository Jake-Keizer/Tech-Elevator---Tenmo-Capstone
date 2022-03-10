package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountById(long accountId) {
        Account account = new Account();
        String sql = "SELECT * " +
                    "FROM account " +
                    "WHERE account_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if (results.next()){
            account = mapRowToAccount(results);
        }
        return account;
    }

    @Override
    public Account getAccountByUsername(String userName) {
        Account account = new Account();
        String sql = "SELECT a.* " +
                    "FROM account a " +
                    "JOIN tenmo_user t USING (user_id) " +
                    "WHERE t.username = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        if (results.next()){
            account = mapRowToAccount(results);
        }
        return account;
    }

    @Override
    public double checkAccountBalance(long userId) {
        double balance = 0;
        String sql = "SELECT * " +
                    "FROM account " +
                    "WHERE user_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()){
            balance = mapRowToAccount(results).getBalance();
        }
        return balance;
    }

    @Override
    public double addToBalance(double deposit, long accountId) {
        Account account = getAccountById(accountId);
        String sql = "UPDATE account " +
                    "SET balance = balance + ? " +
                    "WHERE account_id = ?; ";
        try {
            jdbcTemplate.update(sql, deposit, accountId);
        } catch (DataAccessException e){
            System.out.println("unable to update balance");
        }
        return account.getBalance();
    }

    @Override
    public double decreaseBalance(double withdraw, long accountId) {
        Account account = getAccountById(accountId);
        String sql = "UPDATE account " +
                    "SET balance = balance - ? " +
                    "WHERE account_id = ?; ";
        try {
            jdbcTemplate.update(sql, withdraw, accountId);
        } catch (DataAccessException e){
            System.out.println("unable to update balance");
        }
        return account.getBalance();
    }


    private Account mapRowToAccount(SqlRowSet results){
        Account account = new Account();
        account.setAccountId(results.getLong("account_id"));
        account.setUserId(results.getLong("user_id"));
        account.setBalance(results.getDouble("balance"));
        return account;
    }

}
