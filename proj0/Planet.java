public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    final static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                    double yV, double m, String img) {
                        xxPos = xP;
                        yyPos = yP;
                        xxVel = xV;
                        yyVel = yV;
                        mass = m;
                        imgFileName = img;
                    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return (G * this.mass * p.mass) / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        return this.calcForceExertedBy(p) * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        return this.calcForceExertedBy(p) * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] pArray) {
        double NetForce = 0;
        for(Planet p : pArray) {
            if (this.equals(p)) {
                continue;
            }
            NetForce += this.calcForceExertedByX(p);
        }
        return NetForce;
    }

    public double calcNetForceExertedByY(Planet[] pArray) {
        double NetForce = 0;
        for(Planet p : pArray) {
            if (this.equals(p)) {
                continue;
            }
            NetForce += this.calcForceExertedByY(p);
        }
        return NetForce;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        // StdDraw.show();
    }
}