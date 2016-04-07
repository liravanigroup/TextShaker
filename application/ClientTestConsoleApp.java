package pl.com.bottega.application;

import pl.com.bottega.model.TextContainer;

import java.util.Scanner;

public class ClientTestConsoleApp {
    public static void main(String[] args) {
        System.out.println("Enter your text:");
        Scanner userText = new Scanner(System.in);
        TextContainer text = new TextContainer(userText.nextLine());
        System.out.println(text.getWarpedText());
    }
}
