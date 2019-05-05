package roztropny;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sat4j.core.VecInt;
import org.sat4j.maxsat.SolverFactory;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("n-rooks-problem-gui.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        NRooksSolver nrs = new NRooksSolver(5);
        for (ArrayList<Integer> row : nrs.getSatArray()){
            System.out.println(row.toString());
        }
        nrs.saveToFile("sat.txt");
        nrs.Solve();
        try{
            while (true){
                int[] solution = nrs.getNextSolution();
                for (int val : solution){
                    System.out.print(val);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }catch (UnsatisfiableException e){

        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
