import javax.xml.crypto.Data;

public class NBody {
    public static Double readRadius(String filename) {
        In in = new In(filename);

        int numPlanet = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);

        int numPlanet = in.readInt();
        double radius = in.readDouble();

        Planet[] allPlanets = new Planet[numPlanet];
        int i = 0;
        while (!in.isEmpty() && i < numPlanet) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xP, yP, xV, yV, m, img);
            i += 1;
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        
        /* Set scale and background image */
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        // StdDraw.show();

        for(Planet p : planets) {
            p.draw();
        }
        StdDraw.show();

        StdDraw.enableDoubleBuffering();
        
        double time = 0;
        while(time <= T) {
            double[] xForce = new double[planets.length];
            double[] yForce = new double[planets.length];

            for(int i = 0; i < planets.length; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(int j = 0;j < planets.length; j++) {
                planets[j].update(dt, xForce[j], yForce[j]);
                planets[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
    }
}