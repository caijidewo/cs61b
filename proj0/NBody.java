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
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		String imageToDraw = "./images/starfield.jpg";
		StdDraw.setScale(-1e+11, 1e+11);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		StdDraw.show();
		StdDraw.pause(2000);
		//String filename="data/planets.txt";
		Planet[] allP=readPlanets(filename);
		//Planet p=allP[2];
		for(Planet p : allP){
			//System.out.println(p.imgFileName);
			//StdDraw.clear();
			StdDraw.picture(p.xxPos,p.yyPos, "./images/" + p.imgFileName);
			StdDraw.show();
		    StdDraw.pause(200);
		}
		//StdDraw.show();
		//StdDraw.pause(200000);
	}
}