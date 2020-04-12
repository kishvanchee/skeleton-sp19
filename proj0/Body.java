public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double g = 6.67E-11;


    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Body b){
        double r = calcDistance(b);
        return g * mass * b.mass / (r * r);
    }

    public double calcForceExertedByX(Body b) {
        double r = calcDistance(b);
        double F = calcForceExertedBy(b);
        return (F * (b.xxPos - xxPos) / r);
    }

    public double calcForceExertedByY(Body b) {
        double r = calcDistance(b);
        double F = calcForceExertedBy(b);
        return (F * (b.yyPos - yyPos) / r);
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double fnet = 0;
        for (Body b: bodies) {
            if (!this.equals(b)) {
                fnet += calcForceExertedByX(b);
            }
        }
        return fnet;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double fnet = 0;
        for (Body b: bodies) {
            if (!this.equals(b)) {
                fnet += calcForceExertedByY(b);
            }
        }
        return fnet;
    }

    public void update (double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;

        xxVel = xxVel + (dt * ax);
        yyVel = yyVel + (dt * ay);
        xxPos = xxPos + (dt * xxVel);
        yyPos = yyPos + (dt * yyVel);
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
