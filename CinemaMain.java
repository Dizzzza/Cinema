package cinema;

import java.lang.reflect.Array;
import java.util.Scanner;
public class Cinema {

    private static void showSeats(int rows, int rowSits, char[][] arr){

        System.out.println("Cinema:");
        System.out.print(" ");

        for (int i = 0; i < rowSits; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < rowSits; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void statistics(int[] values, int totalSits, int totalIncome){
        System.out.println((double) (values[1]));
        System.out.println((double) (totalSits));
        double per = ((double) (values[1])) / ((double) (totalSits));
        per = per * 100;
        System.out.printf("Number of purchased tickets: %d\n", values[1]);
        System.out.printf("Percentage: %.2f%%\n", per);
        System.out.printf("Current income: $%d\n", values[0]);
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    public static void check(char[][] arr, int[] rowSit, int rows, int rowSits){
        Scanner sc = new Scanner(System.in);
        if((rowSit[0] - 1 > rows - 1) || (rowSit[1] - 1 > rowSits - 1) || (rowSit[0] - 1 < 0) || (rowSit[1] - 1 < 0)){
            System.out.println("Wrong input!");
            System.out.println("Enter a row number:");
            rowSit[0] = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            rowSit[1] = sc.nextInt();
            check(arr, rowSit, rows, rowSits);
        } else if (arr[rowSit[0] - 1][rowSit[1] - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            System.out.println("Enter a row number:");
            rowSit[0] = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            rowSit[1] = sc.nextInt();
            check(arr, rowSit, rows, rowSits);
        } else {
            return;
        }
    }

    private static void buyTicket(int rows,int rowSits,char[][] arr, int[] values){
        Scanner sc = new Scanner(System.in);
        int[] rowSit = {0,0};
        System.out.println("Enter a row number:");
        rowSit[0] = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        rowSit[1] = sc.nextInt();

        check(arr, rowSit, rows, rowSits);

        arr[rowSit[0] - 1][rowSit[1] - 1] = 'B';

        if (rows * rowSits < 60) {
            System.out.println("Ticket price: $10");
            values[0] += 10;
        } else {
            if (rows / 2 < rowSit[0]) {
                System.out.println("Ticket price: $8");
                values[0] += 8;
            } else {
                System.out.println("Ticket price: $10");
                values[0] += 10;
            }
        }
        values[1]++;
    }
    public static void main(String[] args) {
        int[] values = {0,0};
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int rowSits = sc.nextInt();

        char[][] arr = new char[rows][rowSits];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rowSits; j++) {
                arr[i][j] = 'S';
            }
        }

        int totalIncome = 0;


        int totalSits = rows * rowSits;

        if (rows * rowSits < 60) {
            totalIncome = rows * rowSits * 10;
        } else {
            totalIncome = (rows / 2) * rowSits * 10;
            totalIncome += (rows - (rows / 2)) * rowSits * 8;
        }


        boolean cicle = true;

        while (cicle) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int param = sc.nextInt();
            switch (param) {
                case 1:
                    showSeats(rows, rowSits, arr);
                    break;
                case 2:
                    buyTicket(rows, rowSits, arr, values);
                    break;
                case 3:
                    statistics(values, totalSits, totalIncome);
                    break;
                case 0:
                    cicle = false;
                    break;
                default:
                    System.out.println("Please input 1, 2, 3 or 0");
                    break;
            }
        }


    }

}
