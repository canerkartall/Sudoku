package sudoku;

import aima.core.search.csp.CSP;

import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import aima.core.search.csp.examples.NotEqualConstraint;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SudokuCSP extends CSP<Variable, Integer> {

    SudokuCSP(String samplessudoku1txt) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(samplessudoku1txt);
        String line;
        BufferedReader br = new BufferedReader(fileReader);
        ArrayList<Integer> initialState = new ArrayList<>();
        ArrayList<Integer> indexArray = new ArrayList<>();
        ArrayList<Integer> secondState = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            int k = 0;
            String[] arrOfStr = line.split(",", 10);
            for (String a : arrOfStr) {
                initialState.add(Integer.valueOf(a));
            }
        }
        for (int k = 0; k < initialState.size(); k++) {
            if (initialState.get(k) != 0) {
                indexArray.add(k);
            }
        }
        for (int j = 0; j < indexArray.size(); j++) {
            secondState.add(initialState.get(indexArray.get(j)));
        }

        for (int i = 0; i < 81; i++) {
            addVariable(new Variable("C" + (i)));
        }

        List<Integer> values = new ArrayList<>();
        for (int val = 1; val <= 9; val++) {
            values.add(val);
        }
        Domain<Integer> positions = new Domain<>(values);

        for (Variable var : getVariables()) {
            setDomain(var, positions);
        }

        for (int j = 0; j < secondState.size(); j++) {
            Domain<Integer> asd = new Domain<>(secondState.get(j));
            int c = indexArray.get(j);
            Variable var1 = getVariables().get(c);
            setDomain(var1, asd);
        }
        int a = 0;
        for (int i = 0; i < 81; i++) {
            if (i % 9 == 0) {
                a++;
            }
            
            // --------------- row constraint for each line ---------------
            
            Variable var1 = getVariables().get(i);
            for (int j = i + 1; j < 9 * a; j++) {
                Variable var2 = getVariables().get(j);
                addConstraint(new NotEqualConstraint(var1, var2));
            }
            
            // --------------- column constraint for each line -------------
            
            if(i<9){
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 9)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 18)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 27)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 36)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 45)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 54)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 63)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 72)));
            }
            if(i>=9 && i<18){
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 9)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 18)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 27)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 36)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 45)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 54)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 63)));           
            }
            if(i>=18 && i<27){
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 9)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 18)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 27)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 36)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 45)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 54)));                     
            }
            if(i>=27 && i<36){
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 9)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 18)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 27)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 36)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 45)));                               
            }
            if(i>=36 && i<45){
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 9)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 18)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 27)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 36)));                             
            }
            if(i>=45 && i<54){
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 9)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 18)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 27)));                             
            }
            if(i>=54 && i<63){
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 9)));
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 18)));                            
            }
            if(i>=63 && i<72){
            addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 9)));                           
            }
            
            // -------------------  3x3 block constraint  ---------------
            
            if (i == 0 || i == 3 || i == 6 || i == 27 || i == 30 || i == 33 || i == 54 || i == 57 || i == 60) {
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 10)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 11)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 19)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 20)));
            }
            if (i == 1 || i == 4 || i == 7 || i == 28 || i == 31 || i == 34 || i == 55 || i == 58 || i == 61) {
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 8)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 10)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 17)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 19)));
            }
            if (i == 2 || i == 5 || i == 8 || i == 29 || i == 32 || i == 35 || i == 56 || i == 59 || i == 62) {
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 7)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 8)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 16)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 17)));
            }
            if (i == 9 || i == 12 || i == 15 || i == 36 || i == 39 || i == 42 || i == 63 || i == 66 || i == 69) {
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 10)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 11)));
            }
            if (i == 10 || i == 13 || i == 16 || i == 37 || i == 40 || i == 43 || i == 64 || i == 67 || i == 70) {
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 8)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 10)));              
            }
            if (i == 11 || i == 14 || i == 17 || i == 38 || i == 41 || i == 44 || i == 65 || i == 68 || i == 71) {
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 7)));
                addConstraint(new NotEqualConstraint(var1, getVariables().get(i + 8)));              
            }
        }
    }
}
