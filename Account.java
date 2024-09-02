// Deadlock Issue: The primary issue here is the potential for a deadlock. Consider the scenario where two threads are attempting to transfer money between two different
// accounts, but the accounts are in reverse order. For eg:
// Thread 1 tries to transfer from Account A to Account B.
// Thread 2 tries to transfer from Account B to Account A.
// If Thread 1 locks Account A and Thread 2 locks Account B at the same time, they will each be waiting for the other to release the lock, leading to a deadlock.

// Here is the modified code with resolved deadlock issue and with required exceptions.

package com.company;

public class Account {
    private int balance;

    public Account(int initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized method to get the account balance
    public synchronized int getBalance() {
        return balance;
    }

    // Synchronized method to debit the account
    public synchronized void debit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance -= amount;
    }

    public synchronized void credit(int amount) {    // Synchronized method to credit the account

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }

    // Method to transfer money from this account to another account
    public void transfer(Account to, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Ensure locking order to avoid deadlocks
        Account first = this;
        Account second = to;

        if (System.identityHashCode(first) > System.identityHashCode(second)) {
            first = to;
            second = this;
        }

        synchronized (first) {
            synchronized (second) {
                if (this.getBalance() < amount) {
                    throw new InsufficientFundsException("Insufficient funds for transfer");
                }
                this.debit(amount);
                to.credit(amount);
            }
        }
    }

    // Custom exception for insufficient funds
    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}
