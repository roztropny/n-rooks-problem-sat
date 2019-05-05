package roztropny;

import org.sat4j.maxsat.SolverFactory;
import org.sat4j.reader.InstanceReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.ModelIterator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class NRooksSolver extends NRooksEncoder {

    private IProblem problem;

    public NRooksSolver(int n) {
        super(n);
    }

    public void Solve(){
        ISolver solver = SolverFactory.newDefault();
        ModelIterator mi = new ModelIterator(solver);
        solver.setTimeout(3600); // 1 hour timeout
        Reader reader = new InstanceReader(mi);
        try {
            boolean unsat = true;
            this.problem = reader.parseInstance("sat.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ContradictionException e) {
            System.out.println("Unsatisfiable (trivial)!");
        }
    }

    public int [] getNextSolution() throws UnsatisfiableException, TimeoutException {
        if (problem.isSatisfiable()){
            return problem.model();
        }
        throw new UnsatisfiableException();
    }

    public ArrayList<int[]> getAllSolutions() throws TimeoutException{
        ArrayList<int[]> solutions = new ArrayList<int[]>();
        while (problem.isSatisfiable()) {
            solutions.add(problem.model());
        }
        return solutions;
    }


}
