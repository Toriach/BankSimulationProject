package main_setup;

import bank.BankBackEnd;
import bank.BankDatabase;
import bank.BankFrontEnd;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(String title) throws HeadlessException {
        super(title);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(512,512);
        setLocationRelativeTo(null);

        init();
    }

    private void init() {
        BankDatabase bankDatabase = new BankDatabase();
        BankBackEnd bankBackEnd = new BankBackEnd();
        BankFrontEnd bankFrontEnd = new BankFrontEnd();
        this.add(bankFrontEnd);
    }
}
