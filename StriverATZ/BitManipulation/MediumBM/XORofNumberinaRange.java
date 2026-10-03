package StriverATZ.BitManipulation.MediumBM;

public class XORofNumberinaRange {
    public int findRangeXOR(int l, int r) {
        int left = XORN(l -1);
        int right = XORN(r);
        return left ^ right;
    }

    int XORN(int n){
        if(n %4 == 1) return 1;
        else if(n %4 == 2) return n +1;
        else if(n %4 == 3) return 0;
        else return n;
    }
}
