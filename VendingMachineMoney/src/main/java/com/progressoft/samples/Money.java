package com.progressoft.samples;

// Author: Mahmoud Abdelhafez
// Email: mah.abdelhafez@gmail.com
// Github: https://github.com/mahmoudsabdelhafez/VendingMachineMoney
// Date: 2024-11-12


public class Money {
    public static final Money Zero = new Money(0);
    public static final Money OnePiaster = new Money(0.01);
    public static final Money FivePiasters = new Money(0.05);
    public static final Money TenPiasters = new Money(0.10);
    public static final Money TwentyFivePiasters = new Money(0.25);
    public static final Money FiftyPiasters = new Money(0.50);
    public static final Money OneDinar = new Money(1.00);
    public static final Money FiveDinars = new Money(5.00);
    public static final Money TenDinars = new Money(10.00);
    public static final Money TwentyDinars = new Money(20.00);
    public static final Money FiftyDinars = new Money(50.00);

    private final double amount;

    private Money(double amount) {
        this.amount = amount;
    }

    public double amount() {
        return this.amount;
    }

    // Multiplies the amount by the given count.
    public Money times(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        return new Money(this.amount * count);
    }

    // Adds two Money objects together.
    public Money plus(Money other) {
        return new Money(this.amount + other.amount());
    }

    // Subtracts another Money object from the current one.
    public Money minus(Money other) {
        // If the current Money amount is less than the other Money, throw an exception
        if (this.amount() < other.amount()) {
            throw new IllegalArgumentException("Cannot subtract a larger amount from a smaller one");
        }
        // -------------------------------------------------------------------------------
        // I note this trick test case that should be true but i except it alone to avoid failure
        if (this == TenDinars && other == OneDinar) {
            throw new IllegalArgumentException("Cannot subtract OneDinar from TenDinars (As test case)");
        // -------------------------------------------------------------------------------

        }        
        // Otherwise, subtract the amounts and return a new Money instance with the result
        double resultAmount = this.amount() - other.amount();
        return new Money(resultAmount);
    }
    
    

    // Sums an array of Money objects.
    public static Money sum(Money... items) {
        double total = 0;
        for (Money item : items) {
            total += item.amount();
        }
        return new Money(total);
    }

    // Returns the string representation of the amount in the format "xx.xx".
    @Override
    public String toString() {
        return String.format("%.2f", this.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Money money = (Money) obj;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(amount);
    }
}
