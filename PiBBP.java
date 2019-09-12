import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PiBBP {
     public static char betuzes(double pi)
     {
         int kovetkezojegy = (int) (pi*16);
         //System.out.println("Pi: "+pi*16);
         char betu = 0;
              if(kovetkezojegy>=0 && kovetkezojegy<=9 )
                betu = (char) (kovetkezojegy + '0');
              
              else{
                  if(kovetkezojegy==10)
                      betu='A';
                  else{
                     if(kovetkezojegy==11)
                       betu='B';
                     else{
                        if(kovetkezojegy==12)
                            betu='C';
                        else{
                            if(kovetkezojegy==13)
                                betu='D';
                            else{
                               if(kovetkezojegy==14)
                                    betu='E';
                               else{
                                    if(kovetkezojegy==15)
                                        betu='F';
                    }}}}}}
            //System.out.println("belepett");
            //System.out.println(betu);
            return betu;
     }        
     
    public static double Hexalas(double pi)
    {
        
            pi=(pi*16) - (int) (pi*16);
            //System.out.println("Pi jegyek2: "+pi);
        return pi;
    }  
          

    public static double modulo(double n,double k){
            long t=1;
            double r=1;
            int b=16;
           
        while(t<=n){
           t=t*2;
           
        }
        
             t/=2;
        while(true)
             {
                 if(n>=t)
                 {
                  r=b*r%k;
                  n=n-t;
                 }
                  t/=2;
                
               if(t>=1) {
                 r=Math.pow(r,2)%k;
                }
                else
                    break;
             }
        return r;
    }
    public static void main(String[] args) throws IOException {
        int d=1000000;
        double S1 = 0,S4 = 0,S5 = 0,S6 = 0, pi=0;
        
           if(d>0)
           {
                for (int k = 0; k <= d; k++) {
                   
                    S1=S1+((modulo((d-k),(8*k+1)))/(8*k+1));
                     S4=S4+((modulo((d-k),(8*k+4)))/(8*k+4));
                      S5=S5+((modulo((d-k),(8*k+5)))/(8*k+5));
                       S6=S6+((modulo((d-k),(8*k+6)))/(8*k+6));
                }
                
               
           S1=S1-Math.round(S1);
           S4=S4-Math.round(S4);
           S5=S5-Math.round(S5);
           S6=S6-Math.round(S6);
            
            }

           pi=4*S1-2*S4-S5-S6;
           
           
           String pijegyek = new String();
           while(pi>0)
           {
               //System.out.println("Pi jegyek: "+pi);
               pijegyek=pijegyek+betuzes(pi);
               //System.out.println("Pi jegyek: "+pi);
               pi=Hexalas(pi);
               //System.out.println(pi);
               
           }
           
               System.out.println(pijegyek);
           
            

    }
    
}