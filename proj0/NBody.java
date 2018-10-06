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
}