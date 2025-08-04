package org.example;

import java.util.*;

public class InteractiveOrderProcessor {
    public static void main(String[] args) {
        // initialize scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Interactive Order Processor!");
        System.out.println("\n--- Enter Order Details ---");

        // get order data from user
        System.out.print("Enter unit price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Is customer a member (true/false)?: ");
        boolean isMember = scanner.nextBoolean();
        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        String customerTier = scanner.next();
        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        String shippingZone = scanner.next();
        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
        String discountCode = scanner.next();

        System.out.println("\n--- Order Details ---");
        System.out.println("Unit Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);

        // subtotal calculation
        double initialSubTotal = price * quantity;

        // tier-based discount
        double subTotalAfterTierBasedDiscount = initialSubTotal;
        if (customerTier.equals("Gold")) {
            subTotalAfterTierBasedDiscount -= (initialSubTotal * 0.15);
        } else if (customerTier.equals("Silver")) {
            subTotalAfterTierBasedDiscount -= (initialSubTotal * 0.1);
        } else {
            subTotalAfterTierBasedDiscount = initialSubTotal;
        }

        // quantity discount
        double subTotalAfterQuantityDiscount = subTotalAfterTierBasedDiscount;
        if (quantity >= 5) {
            subTotalAfterQuantityDiscount -= (subTotalAfterTierBasedDiscount * 0.05);
        }

        // promotional code application
        double subTotalAfterPromoCode = subTotalAfterQuantityDiscount;
        if (discountCode.equals("SAVE10") && subTotalAfterQuantityDiscount > 75) {
            subTotalAfterPromoCode -= 10;
        }

        // small order surcharge
        boolean hasSurcharge = (subTotalAfterPromoCode < 25) ? true : false; // can be simplified to: boolean hasSurcharge = subTotalAfterPromoCode < 25;
        double subTotalAfterSurcharge = (hasSurcharge) ? subTotalAfterPromoCode -= 3 : subTotalAfterPromoCode;

        // shipping cost calculation
        double shippingCost;
        if (discountCode.equalsIgnoreCase("FREESHIP")) {
            shippingCost = 0;
        } else {
            shippingCost = switch (shippingZone) {
                case "ZoneA" -> 5;
                case "ZoneB" -> 12.5;
                case "ZoneC" -> 20;
                default -> 25;
            };
        }

        // final total
        double finalOrderTotal = subTotalAfterSurcharge + shippingCost;

        // output
        System.out.println("\n--- Calculation Steps ---");
        System.out.println("Initial Subtotal: $" + initialSubTotal);

        switch (customerTier) {
            case "Gold":
                System.out.println("After Tier Discount (Gold - 15%): " + subTotalAfterTierBasedDiscount);
                break;
            case "Silver":
                System.out.println("After Tier Discount (Silver - 10%): " + subTotalAfterTierBasedDiscount);
                break;
            default:
                System.out.println("After Tier Discount (No tier discount): " + subTotalAfterTierBasedDiscount);
                break;
        }

        if (quantity >= 5) {
            System.out.println("After Quantity Discount (5% for >=5 items): $" + subTotalAfterQuantityDiscount);
        } else {
            System.out.println("After Quantity Discount (No quantity discount): $" + subTotalAfterQuantityDiscount);
        }

        switch (discountCode) {
            case "SAVE10":
                System.out.println("After Promotional Code (SAVE10 for >$75): " + subTotalAfterPromoCode);
                break;
            case "FREESHIP":
                System.out.println("After Promotional Code (FREESHIP for free shipping): " + subTotalAfterPromoCode);
                break;
            default:
                System.out.println("After Promotional Code (no code): " + subTotalAfterPromoCode);
                break;
        }

        if (hasSurcharge) {
            System.out.println("After Small Order Surcharge (if applicable): " + subTotalAfterSurcharge);
        } else {
            System.out.println("After Small Order Surcharge (if applicable): " + subTotalAfterSurcharge + " (No surcharge)");
        }

        System.out.println("\nShipping Cost: $" + shippingCost + " (" + shippingZone + ")");
        System.out.println("\nFinal Order Total: $" + finalOrderTotal);

        // part 2: interactive string equality
        System.out.println("\n--- String Equality Demo ---");
        System.out.print("Enter first string for comparison: ");
        String firstString = scanner.next();
        System.out.print("Enter second string for comparison: ");
        String secondString = scanner.next();

        System.out.println("\nString1: " + firstString);
        System.out.println("String2: " + secondString);

        if (firstString == secondString) {
            System.out.println("\nString 1 == String 2: true (Compares references, which are the same for user input strings)");
        } else {
            System.out.println("\nString 1 == String 2: false (Compares references, which are different for user input strings)");
        }

        if (firstString.equals(secondString)) {
            System.out.println("String 1 .equals() String 2: true (Content identical, including case)");
        } else {
            System.out.println("String 1 .equals() String 2: false (Content is different due to case)");
        }

        if (firstString.equalsIgnoreCase(secondString)) {
            System.out.println("String 1 .equals() String 2: true (Content is identical, ignoring case)");
        } else {
            System.out.println("String 1 .equals() String 2: false (Content is different, ignoring case)");
        }

        // close scanner
        scanner.close();
    }
}