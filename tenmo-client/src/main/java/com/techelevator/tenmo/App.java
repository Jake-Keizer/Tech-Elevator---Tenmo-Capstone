package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private AuthenticatedUser currentUser;

    private final AccountService accountService = new AccountService();
    private final TransferService transferService = new TransferService();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

    private void viewCurrentBalance() {
        // TODO Auto-generated method stub
        double balance = accountService.checkAccountBalance(currentUser.getToken());
        System.out.println("\n\nBalance is: " + balance);
    }

    private void viewTransferHistory() {
        // TODO Auto-generated method stub;
        List<Transfer> transferList = transferService.getTransfers(currentUser.getToken());
        System.out.println("\n\nYour transfer history:");
        for (int i = 0; i < transferList.size(); i++) {
            System.out.println(transferList.get(i));

        }

    }

    private void viewPendingRequests() {
        // TODO Auto-generated method stub

    }

    private void sendBucks() {
        // TODO Auto-generated method stub;
        List<Account> accounts = accountService.listAccounts(currentUser.getToken());
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
       Long toId = consoleService.promptForInt("Select userId from above list: ");
        double transferAmount = consoleService.promptForDouble("Enter amount to transfer: ");
        Transfer transfer = new Transfer();
        transfer.setAmount(transferAmount);
        transfer.setAccountTo(toId);
        transfer.setAccountFrom(currentUser.getUser().getId());
        transferService.sendTransfer(currentUser.getToken(), transfer);


    }

    private void requestBucks() {
        // TODO Auto-generated method stub

    }

}
