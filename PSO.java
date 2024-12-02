import java.util.Random;
 
public class PSO {
    public static void main(String[] args) {
        Random random = new Random(); // Object of random class
        PSO ob = new PSO();
 
        double[] positionX = new double[5]; // positions array
        double[] positionY = new double[5];
 
        for  (int a = 0; a < 5; a++) {
            positionX[a] = ((random.nextDouble()) * 10) - 5; // randomly generating position of n particles
            positionY[a]=((random.nextDouble()) * 10) - 5;
 
        }
 
 
        double[] velocityX = new double[5]; // velocity array
        double[] velocityY = new double[5];
 
 
        for (int b = 0; b < 5; b++) {
            velocityX[b] = ((random.nextDouble()) * 10) - 5; // randomly generating velocity of n particles
            velocityY[b] = ((random.nextDouble()) * 10) - 5;
           
 
        }
 
        double[] personalbestX = positionX.clone();
 
        double[] personalbestY = positionY.clone();
        double globalbestX = positionX[0];
        double globalbestY = positionY[0];
 
 
        // basic
        //int maxiterations = 20;
        int maxiterations=Integer.parseInt(args[0]);
        double c1 = 1.496180; // cognitivecoeff
        double c2 = 1.496180; // socialcoeff
        double w = 0.729844; // inertiacoeff
        // double r1=random.nextDouble();
        // double r2=random.nextDouble();
        //double globalbestvalueX = 0;
        //double globalbestvalueY = 2;
        double globalfitness=ob.ObjectiveFunction(globalbestX,globalbestY);
 
        for (int i = 0; i < maxiterations; i++) {
 
            for (int j = 0; j < 5; j++) {
                double positionfunction = ob.ObjectiveFunction(positionX[j],positionY[j]); // positionfunction store
                // positionfunction = ob.ObjectiveFunction(positionY[j]);// position[j]*positioon[j](multiple points
                // to give value)
                double personalbestfunction = ob.ObjectiveFunction(personalbestX[j],personalbestY[j]); // personalbestfunction store
                 //personalbestfunction = ob.ObjectiveFunction(personalbestY[j]);// personalbest[j]*personalbest[j]
                // (multiple points to give value)
                //globalbestvalueX = ob.ObjectiveFunction(globalbestX); // globalbestvalue store globalbest*globalbest(ie.
               // globalbestvalueY = ob.ObjectiveFunction(globalbestY);   // initially equal to position of first element)
               
 
                // check fitness
 
                if (personalbestfunction > positionfunction) { /*
                 * suppose personal best from first iteration is 9 and at
                 * second iteration the position is 2
                 * so after objective function position function becomes
                 * 4 while personalbestfunction becomes 81
                 * so new personal best should be 2 that is the position
                 * value
                 */
                    personalbestX[j] = positionX[j];
                    personalbestY[j] = positionY[j];
 
                }
                if (positionfunction <globalfitness)// && personalbestfunction < globalbestvalueY ) 
                { /*
                 * global best is the best position value from all the
                 * elements
                 */
                    globalbestX = positionX[j];
                    globalbestY = positionY[j];
                    globalfitness=positionfunction;
 
                }
 
                // new velocities
                double r1 = (random.nextDouble());
               // System.out.println(r1);
                /*
                 * generating random numbers between -5 to 5 , so that
                 * when they are used in
                 * velocity updation so that particle can move in both
                 * left and right directions
                 */
 
                double r2 = (random.nextDouble());
                velocityX[j] = w * velocityX[j] + c1 * r1 * (personalbestX[j] - positionX[j]) + c2 * r2 * (globalbestX - positionX[j]);
                positionX[j] = positionX[j] +velocityX[j];
 
                velocityY[j] = w * velocityY[j] + c1 * r1 * (personalbestY[j] - positionY[j]) + c2 * r2 * (globalbestY - positionY[j]);
                positionY[j] = positionY[j] + velocityY[j];
 
 
            }
 
        }
 
        System.out.println("the value of gbestX id  " + globalbestX);
        System.out.println("the value of gbestY id " + globalbestY);
 
 
    }
 
    public double ObjectiveFunction(double x,double y) {
        return (x * x)+(y*y);
 
    }
} 