import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class lab3 {
    public static void main(String[] args) {
        int X1min = 15;
        int X1max = 45;
        int X2min = -35;
        int X2max = 15;
        int X3min = -35;
        int X3max = -5;

        double Xsrmax = (X1max+X2max+X3max)/3;
        double Xsrmin = (X1min+X2min+X3min)/3;
        double Ymin = 200 +Xsrmin;
        double Ymax = 200 +Xsrmax;
        int []nex1={-1,-1,1,1};
        int []nex2={-1,1,-1,1};
        int []nex3={-1,1,1,-1};

        int []x1={15,15,45,45};
        int []x2={-35,15,-35,15};
        int []x3={-35,-5,-5,-35};

        int []y1 = new int[4];
        int []y2 = new int[4];
        int []y3 = new int[4];
        double diff = Ymax - Ymin;
        Random random = new Random();


        for (int i = 0; i < 4; i++) {
           y1[i] =(int) Ymin+ random.nextInt((int) (diff + 1));
            System.out.print("y1: " +y1[i]+" ");
            y2[i] =(int) Ymin+ random.nextInt((int) (diff + 1));
            System.out.print("y2: " +y2[i]+" ");
            y3[i] =(int) Ymin+ random.nextInt((int) (diff + 1));
            System.out.println("y3: " + y3[i]+" ");
        }
        
        double Y1sr=(y1[0]+y2[0]+y3[0])/3;
        double Y2sr=(y1[1]+y2[1]+y3[1])/3;
        double Y3sr=(y1[2]+y2[2]+y3[2])/3;
        double Y4sr=(y1[3]+y2[3]+y3[3])/3;

        int mx1=(x1[0]+x1[1]+x1[2]+x1[3])/4;
        int mx2=(x2[0]+x2[1]+x2[2]+x2[3])/4;
        int mx3=(x3[0]+x3[1]+x3[2]+x3[3])/4;
        double my= (Y1sr+ Y2sr+Y3sr+Y4sr)/4;

        double a11=(x1[0]*x1[0]+x1[1]*x1[1]+x1[2]*x1[2]+x1[3]*x1[3])/4;
        double a22=(x2[0]*x2[0]+x2[1]*x2[1]+x2[2]*x2[2]+x2[3]*x2[3])/4;
        double a33=(x3[0]*x3[0]+x3[1]*x3[1]+x3[2]*x3[2]+x3[3]*x3[3])/4;

        double a1=(x1[0]*Y1sr+x1[1]*Y2sr+x1[2]*Y3sr+x1[3]*Y4sr)/4;
        double a2=(x2[0]*Y1sr+x2[1]*Y2sr+x2[2]*Y3sr+x2[3]*Y4sr)/4;
        double a3=(x3[0]*Y1sr+x3[1]*Y2sr+x3[2]*Y3sr+x3[3]*Y4sr)/4;

        double a12=(x1[0]*x2[0]+x1[1]*x2[1]+x1[2]*x2[2]+x1[3]*x2[3])/4; //a12=a21
        double a13=(x1[0]*x3[0]+x1[1]*x3[1]+x1[2]*x3[2]+x1[3]*x3[3])/4; //a13=a31
        double a23=(x2[0]*x3[0]+x2[1]*x3[1]+x2[2]*x3[2]+x2[3]*x3[3])/4; //a23=a32


        double [][]v0= {{1,mx1,mx2,mx3},
                        {mx1,a11,a12,a13},
                        {mx2,a12,a22,a23},
                        {mx3,a13,a23,a33}};
        double [][]v1= {{my,mx1,mx2,mx3},
                        {a1,a11,a12,a13},
                        {a2,a12,a22,a23},
                        {a3,a13,a23,a33}};
        double [][]v2= {{1,my,mx2,mx3},
                        {mx1,a1,a12,a13},
                        {mx2,a2,a22,a23},
                        {mx3,a3,a23,a33}};
        double [][]v3= {{1,mx1,my,mx3},
                        {mx1,a11,a1,a13},
                        {mx2,a12,a2,a23},
                        {mx3,a13,a3,a33}};
        double [][]v4= {{1,mx1,mx2,my},
                        {mx1,a11,a12,a1},
                        {mx2,a12,a22,a2},
                        {mx3,a13,a23,a3}};


            LUDecomposition A = new LUDecomposition(new Array2DRowRealMatrix(v0));
            LUDecomposition B = new LUDecomposition(new Array2DRowRealMatrix(v1));
            LUDecomposition C = new LUDecomposition(new Array2DRowRealMatrix(v2));
            LUDecomposition E = new LUDecomposition(new Array2DRowRealMatrix(v3));
            LUDecomposition D = new LUDecomposition(new Array2DRowRealMatrix(v4));

            double b0=  B.getDeterminant() / A.getDeterminant();
            double b1=  C.getDeterminant() / A.getDeterminant();
            double b2=  E.getDeterminant() / A.getDeterminant();
            double b3=  D.getDeterminant() / A.getDeterminant();

        System.out.println("b0:"+ b0 +" "+ "b1:"+ b1 +" " + "b2:"+ b2+" " + "b3:"+ b3);
        
        double y111 = b0+b1*x1[0]+b2*x2[0]+b3*x3[0];
        double y112 = b0+b1*x1[1]+b2*x2[1]+b3*x3[1];
        double y113 = b0+b1*x1[2]+b2*x2[2]+b3*x3[2];
        double y114 = b0+b1*x1[3]+b2*x2[3]+b3*x3[3];

        System.out.println("y111: " + y111 + " " + "Y1sr: " +Y1sr);
        System.out.println("y112: " + y112 + " " + "Y2sr: " +Y2sr);
        System.out.println("y113: " + y113 + " " + "Y3sr: " +Y3sr);
        System.out.println("y114: " + y114 + " " + "Y4sr: " +Y4sr);

        
        //Критерій Кохрена
        double sigmaY1=((y1[0]-Y1sr)*(y1[0]-Y1sr)+
                        (y2[0]-Y1sr)*(y2[0]-Y1sr)
                        +(y3[0]-Y1sr)*(y3[0]-Y1sr))/3;
        double sigmaY2=((y1[1]-Y2sr)*(y1[1]-Y2sr)+
                        (y2[1]-Y2sr)*(y2[1]-Y2sr)
                        +(y3[1]-Y2sr)*(y3[1]-Y2sr))/3;
        double sigmaY3=((y1[2]-Y3sr)*(y1[2]-Y3sr)+
                        (y2[2]-Y3sr)*(y2[2]-Y3sr)
                        +(y3[2]-Y3sr)*(y3[2]-Y3sr))/3;
        double sigmaY4=((y1[3]-Y4sr)*(y1[3]-Y4sr)+
                        (y2[3]-Y4sr)*(y2[3]-Y4sr)
                        +(y3[3]-Y4sr)*(y3[3]-Y4sr))/3;
        double sigma =(sigmaY1+sigmaY2+sigmaY3+sigmaY4);

        ArrayList<Double> col = new ArrayList<Double>();
        col.add(sigmaY1);
        col.add(sigmaY2);
        col.add(sigmaY3);
        col.add(sigmaY4);
        double Gkr = Collections.max(col)/sigma;

        int N=4,m=3;
        int f1 = m-1;
        int f2 = N;
        //q =0.05;
        double Gtr=0.7679;
        if(Gkr<Gtr){
            System.out.println("Дисперсія однорідна за критерієм Кохрена");
        }
        else {
            System.out.println("Дисперсія неоднорідна за критерієм Кохрена");
        }


////////////////   Критерій Стьюдента

        double sigmaV = sigma/N;
        double sigmaBB = (sigmaV*sigmaV)/(N*m);
        double sigmaB = Math.sqrt(sigmaBB);
        double B0=(Y1sr+Y2sr+Y3sr+Y4sr)/4;
        double B1=(-Y1sr-Y2sr+Y3sr+Y4sr)/4;
        double B2=(-Y1sr+Y2sr-Y3sr+Y4sr)/4;
        double B3=(-Y1sr+Y2sr+Y3sr-Y4sr)/4;

        double t0=Math.abs(B0)/sigmaB;
        double t1=Math.abs(B1)/sigmaB;
        double t2=Math.abs(B2)/sigmaB;
        double t3=Math.abs(B3)/sigmaB;

        int f3=f1*f2;
        double ttr = 2.306;
        double b00=0,b11=0,b22=0,b33=0;

        int d=0;
        if (t0<ttr){ System.out.println("    Коефіцент В0 не значимий за критерій Стьюдента"); }
            else {b00 = t0;d++;}
        if (t1<ttr){   System.out.println("    Коефіцент В1 не значимий за критерій Стьюдента");}
            else {b11 = t1;d++;}
        if (t2<ttr){   System.out.println("    Коефіцент В2 не значимий за критерій Стьюдента"); }
            else {b22 = t2;d++;}
        if (t3<ttr){   System.out.println("    Коефіцент В3 не значимий за критерій Стьюдента");}
            else {b33 = t3;d++;}

        double y121 = b00+b11*x1[0]+b22*x2[0]+b33*x3[0];
        double y122 = b00+b11*x1[1]+b22*x2[1]+b33*x3[1];
        double y123 = b00+b11*x1[2]+b22*x2[2]+b33*x3[2];
        double y124 = b00+b11*x1[3]+b22*x2[3]+b33*x3[3];

/////////////// Фішер
            int f4=N-d;
            double sigmaAd=((y121-Y1sr)*(y121-Y1sr)+
                            (y122-Y2sr)*(y122-Y2sr)
                            +(y123-Y3sr)*(y123-Y3sr)
                            +(y124-Y4sr)*(y124-Y4sr))/(m/f4);
            double Fkr = sigmaAd/sigmaBB;
            double Ftr = 4.1;
            if (Fkr>Ftr){ System.out.println("Рівняння неадекватно оригіналу"); }
            else { System.out.println("Рівняння адекватно оригіналу"); }

        }

}

