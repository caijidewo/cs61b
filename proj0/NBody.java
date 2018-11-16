public class NBody{
	public static double readRadius(String filename){
		In in=new In(filename);
		int quantity=in.readInt();
		double radius=in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String filename){
		In in=new In(filename);
		int quantity=in.readInt();
		double radius=in.readDouble();
		Planet[] allPlanets=new Planet[quantity];
		for(int i=0;i<allPlanets.length;i++){
			allPlanets[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
			in.readDouble(),in.readDouble(),in.readString());
		}
		return allPlanets;
	}
	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		String imageToDraw = "./images/starfield.jpg";
		StdDraw.setScale(-readRadius(filename),readRadius(filename) );
//		System.out.print(readRadius(filename));
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		StdDraw.show();
		StdDraw.pause(2000);
		Planet[] allP=readPlanets(filename);
		for(Planet p : allP){
			Planet.draw(p);
		}
		double t=0;
		for(;t<=T;t=t+dt){
			double[] xForces,yForces;
			xForces=new double[allP.length];
			yForces=new double[allP.length];
			for(int i=0;i<xForces.length;i++){
				xForces[i]=allP[i].calcNetForceExertedByX(allP);
				yForces[i]=allP[i].calcNetForceExertedByY(allP);
			}
			for(int i=0;i<xForces.length;i++){
				allP[i].update(t,xForces[i],yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0,0,imageToDraw);
			for(int j=0;j<allP.length;j++)
				Planet.draw(allP[j]);
			StdDraw.show();
			StdDraw.pause(500);
		}
		StdOut.printf("%d\n", allP.length);
		StdOut.printf("%.2e\n",readRadius(filename));
		for (int i = 0; i < allP.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	allP[i].xxPos, allP[i].yyPos, allP[i].xxVel,
            	allP[i].yyVel, allP[i].mass, allP[i].imgFileName);   
		}
		/*for(Planet p : allP){
			System.out.print(p.xxPos+" "+p.yyPos+" ");
			System.out.print(p.xxVel+" "+p.yyVel+" ");
			System.out.print(p.mass);
			System.out.println(p.imgFileName);
		}*/
	}
}