/*
Rock-Paper Scissor game in Java
*/

import java.util.Scanner;
import java.util.Random;


public class RockPaperScissorGame {

    enum Choice {
        ROCK, PAPER, SCISSOR;

        //Method to get a random choice
        private static final Random rand = new Random();

       public static Choice getRandomChoice(){
            Choice[] values = Choice.values();//built in enum method
            //values[0] = Choice.Rock
            return values[rand.nextInt(values.length)];
        }
    }


    public static void showChoice(String player, Choice choice){
        System.out.println(player + " chooses "  + choice);
    }

    //Method to determine thye Winner
    public static String determineWinner(Choice player, Choice computer){
        if(player == computer){
            return "Its a tie";
        } else if(
            (player == Choice.ROCK && computer == Choice.SCISSOR) ||
            (player == Choice.PAPER && computer == Choice.ROCK) ||
            (player == Choice.SCISSOR && computer == Choice.PAPER)
        ){
            return " Player Wins!";
        }else {
            return "Computer WIns!";
        }

    }



public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    //Ask the user for input
    System.out.println("Enter your choice");
    String input = scanner.nextLine().toUpperCase();

    Choice userChoice;
    try{

        userChoice = Choice.valueOf(input);
            }
    catch(IllegalArgumentException e){
        System.out.println("Invalid Choice- Default to Rock");
        userChoice = Choice.ROCK;
    }

//Random computer choice

Choice computerChoice = Choice.getRandomChoice();

showChoice("Player", userChoice);
showChoice("Computer", computerChoice);


String winner = determineWinner(userChoice, computerChoice);
System.out.println(winner);

scanner.close();
}
}

