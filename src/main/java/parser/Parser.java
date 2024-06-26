package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

import Log.Log;
import codeGenerator.CodeGeneratorFacade;
import errorHandler.ErrorHandler;
import scanner.lexicalAnalyzer;
import scanner.token.Token;

public class Parser {
    private ArrayList<Rule> rules;
    private Stack<Integer> parsStack;
    private ParseTable parseTable;
    private lexicalAnalyzer lexicalAnalyzer;
    private CodeGeneratorFacade cgf;

    public Parser(CodeGeneratorFacade cgf) {
        parsStack = new Stack<Integer>();
        parsStack.push(0);
        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rules = new ArrayList<Rule>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cgf = cgf;
    }

    public void startParse(java.util.Scanner sc) {
        initializeParser(sc);
        parseTokens();
    }

    private void initializeParser(java.util.Scanner sc) {
        lexicalAnalyzer = new lexicalAnalyzer(sc);
    }

    private void parseTokens() {
        Token lookAhead = lexicalAnalyzer.getNextToken();
        boolean finish = false;
        Action currentAction;
        while (!finish) {
            try {
                Log.print(lookAhead.toString() + "\t" + parsStack.peek());
                currentAction = parseTable.getActionTable(parsStack.peek(), lookAhead);
                Log.print(currentAction.toString());

                Act act = currentAction.action;

                if (act instanceof Shift) {
                    handleShiftAction((Shift) act);
                    lookAhead = lexicalAnalyzer.getNextToken();
                } else if (act instanceof Reduce) {
                    handleReduceAction((Reduce) act, lookAhead);
                } else if (act instanceof Accept) {
                    finish = true;
                }
                Log.print("");
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        if (!ErrorHandler.hasError) cgf.printMemory();
    }

    private void handleShiftAction(Shift act) {
        parsStack.push(act.number);
    }

    private void handleReduceAction(Reduce act, Token lookAhead) {
        Rule rule = rules.get(act.number);
        for (int i = 0; i < rule.RHS.size(); i++) {
            parsStack.pop();
        }

        Log.print(parsStack.peek() + "\t" + rule.LHS);
        parsStack.push(parseTable.getGotoTable(parsStack.peek(), rule.LHS));
        Log.print(parsStack.peek() + "");
        try {
            cgf.semanticFunction(rule.semanticAction, lookAhead);
        } catch (Exception e) {
            Log.print("Code Generator Error");
        }
    }
}
