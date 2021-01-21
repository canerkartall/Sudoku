package sudoku;


import aima.core.search.csp.CSP;
import aima.core.search.csp.CspListener;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import aima.core.search.csp.Variable;
import java.io.IOException;
import sudoku.SudokuCSP;

public class SudokuCSPDemo {
	public static void main(String[] args) throws IOException {
		CSP<Variable, Integer> csp = new SudokuCSP("C:\\Users\\karta\\OneDrive\\Belgeler\\NetBeansProjects\\Sudoku\\src\\samples\\sudoku1.txt");
		CspListener.StepCounter<Variable, Integer> stepCounter = new CspListener.StepCounter<>();
		CspSolver<Variable, Integer> solver;
		
		System.out.println("Sudoku Solver (Backtracking + MRV & DEG + LCV + AC3)");
		solver = new FlexibleBacktrackingSolver<Variable, Integer>().setAll();
		solver.addCspListener(stepCounter);
		stepCounter.reset();	
		System.out.println(solver.solve(csp));
		System.out.println(stepCounter.getResults() + "\n");
		
		System.out.println("Sudoku Solver (Backtracking)");
		solver = new FlexibleBacktrackingSolver<>();
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println(solver.solve(csp));
		System.out.println(stepCounter.getResults() + "\n");	
			

	}

}