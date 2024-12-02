
     
import java.util.Random;
 
class Part ! {
    public static void main(String[] args) {
        Random random = new Random(); // Object of random class
        Main ob = new Main();
 
        double[] position = new double[5]; // positions array
 
        for (int a = 0; a < 5; a++) {
            position[a] = ((random.nextDouble()) * 10) - 5; // randomly generating position of n particles
 
        }
 
        double[] velocity = new double[5]; // velocity array
 
        for (int b = 0; b < 5; b++) {
            velocity[b] = ((random.nextDouble()) * 10) - 5; // randomly generating velocity of n particles
 
        }
 
        double[] personalbest = position.clone(); // maintaing an array to store the personal best position of a
                                                  // particle( which is initially equal to particle initial positions )
        double globalbest = position[0];
 
        // basic
        int maxiterations = 20;
        double c1 = 1.496180; // cognitivecoeff
        double c2 = 1.496180; // socialcoeff
        double w = 0.729844; // inertiacoeff
        // double r1=random.nextDouble();
        // double r2=random.nextDouble();
        double globalbestvalue = 0;
 
        for (int i = 0; i < maxiterations; i++) {
 
            for (int j = 0; j < 5; j++) {
                double positionfunction = ob.ObjectiveFunction(position[j]); // positionfunction store
                                                                             // position[j]*positioon[j](multiple points
                                                                             // to give value)
                double personalbestfunction = ob.ObjectiveFunction(personalbest[j]); // personalbestfunction store
                                                                                     // personalbest[j]*personalbest[j]
                                                                                     // (multiple points to give value)
                globalbestvalue = ob.ObjectiveFunction(globalbest); // globalbestvalue store globalbest*globalbest(ie.
                                                                    // initially equal to position of first element)
 
                // check fitness
 
                if (personalbestfunction > positionfunction) { /*
                                                                * suppose personal best from first iteration is 9 and at
                                                                * second iteration the position is 2
                                                                * so after objective function position function becomes
                                                                * 4 while personalbestfunction becomes 81
                                                                * so new personal best should be 2 that is the position
                                                                * value
                                                                */
                    personalbest[j] = position[j];
                }
                if (personalbestfunction < globalbestvalue) { /*
                                                               * global best is the best position value from all the
                                                               * elements
                                                               */
                    globalbest = personalbest[j];
                }
 
                // new velocities
                double r1 = ((random.nextDouble()) * 10) - 5; /*
                                                               * generating random numbers between -5 to 5 , so that
                                                               * when they are used in
                                                               * velocity updation so that particle can move in both
                                                               * left and right directions
                                                               */
 
                double r2 = ((random.nextDouble()) * 10) - 5;
                velocity[j] = w * velocity[j] + c1 * r1 * (personalbest[j] - position[j]) /*
                                                                                           * velocity between -5 and 5
                                                                                           */
                        + c2 * r2 * (globalbest - position[j]);
                position[j] = position[j] + velocity[j];
 
            }
 
        }
 
        System.out.println("the value of gbest id " + globalbestvalue);
 
    }
 
    public double ObjectiveFunction(double x) {
        return x * x;
 
    }
}

