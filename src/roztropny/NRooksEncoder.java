package roztropny;

import java.util.ArrayList;

public class NRooksEncoder extends SatEncoder {

    private int n;

    public NRooksEncoder(int n) {
        this.n = n;
        this.generateCheckerboardDeclaration();
        this.generateOneInRowDeclaration();
        this.generateOneInColDeclaration();
    }

    private void generateCheckerboardDeclaration(){
        for (int i = 1; i <= n; i++){
            ArrayList<Integer> clausule = new ArrayList<Integer>();
            for (int j = i*n-n+1; j <= i*n; j++){
                clausule.add(new Integer(j));
            }
            this.addClausle(clausule);
        }
    }

    private void generateOneInRowDeclaration(){
        ArrayList<Integer> clausule;
        for (int i = 1; i <= n; i++){
            for (int j = i*n-n+1; j <= i*n-1; j++){
                for (int k = j+1; k <= i*n; k++){
                    clausule = new ArrayList<Integer>();
                    clausule.add(new Integer(-j));
                    clausule.add(new Integer(-k));
                    this.addClausle(clausule);
                }
            }
        }
    }

    private void generateOneInColDeclaration(){
        ArrayList<Integer> clausule;
        for (int i = 1; i <= n; i++){
            for (int j = i; j <= n*n-1; j+=n){
                for (int k = j+n; k <= n*n; k+=n){
                    clausule = new ArrayList<Integer>();
                    clausule.add(new Integer(-j));
                    clausule.add(new Integer(-k));
                    this.addClausle(clausule);
                }
            }
        }
    }

    public int getN() {
        return n;
    }
}
