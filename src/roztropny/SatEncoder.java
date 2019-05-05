package roztropny;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SatEncoder {

    private ArrayList<ArrayList<Integer>> satArray = new ArrayList<ArrayList<Integer>>();

    public void addClausle(ArrayList<Integer> clausule){
        this.satArray.add(clausule);
    }

    public void addClausle(ArrayList<Integer> clausule, int position){
        this.satArray.add(position, clausule);
    }

    public ArrayList<ArrayList<Integer>> getSatArray() {
        return satArray;
    }

    public Integer getVarCounter(){
        //Look for biggest and smallest number in matrix - assume that is number of all variables
        Integer min = 0;
        Integer max = 0;
        for (ArrayList<Integer> row : this.satArray){
            for (Integer value : row){
                if(value > max) max = value;
                else if(value < min) min = value;
            }
        }
        min = -min;
        if(min > max) max = min;
        return max;
    }

    public Integer getClausuleCounter(){
        return new Integer(satArray.size());
    }

    public void saveToFile(String filename){
        try (PrintWriter out = new PrintWriter(filename)) {
            //Print problem line
            out.print("p cnf ");
            out.print(this.getVarCounter().toString());
            out.print(" ");
            out.println(this.getClausuleCounter().toString());
            //Print declarations
            for (ArrayList<Integer> row : this.satArray){
                for (Integer value : row){
                    out.print(value.toString());
                    out.print(" ");
                }
                out.println("0");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
