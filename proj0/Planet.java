public class Planet{
	static final double G=6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;//质量
	public String imgFileName;
	public Planet(double xP,double yP,double xV,
				  double yV,double m,String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p){
		this(p.xxPos,p.yyPos,p.xxVel,p.yyVel,p.mass,p.imgFileName);
	}
	public double calcDistance(Planet p){
		double distance;
		distance=Math.sqrt((p.xxPos-this.xxPos)*(p.xxPos-this.xxPos)+
							(p.yyPos-this.yyPos)*(p.yyPos-this.yyPos));
		return distance;
	}
	public double calcForceExertedBy(Planet p){
		double F;
		F=Planet.G*p.mass*this.mass/calcDistance(p)/calcDistance(p);
		return F;
	}
	public double calcForceExertedByX(Planet p){
		double Fx;
		int sign;
		if(p.xxPos<this.xxPos) sign=-1;
		else sign=1;
		Fx=sign*calcForceExertedBy(p)*Math.abs(p.xxPos-this.xxPos)/calcDistance(p);
		return Fx;
	}
	public double calcForceExertedByY(Planet p){
		double Fy;
		int sign;
		if(p.yyPos<this.yyPos) sign=-1;
		else sign=1;
		Fy=sign*calcForceExertedBy(p)*Math.abs(p.yyPos-this.yyPos)/calcDistance(p);
		return Fy;
	}
	public boolean equals(Planet p){
		boolean same;
		if((p.xxPos==this.xxPos)&&(p.yyPos==this.yyPos))
			same=true;
		else same=false;
		return same;
	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double Fnetx=0;
		for(Planet p : allPlanets){
			if(!equals(p))	Fnetx=Fnetx+calcForceExertedByX(p);
		}
		return Fnetx;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double Fnety=0;
		for(Planet p : allPlanets){
			if(!equals(p))	Fnety=Fnety+calcForceExertedByY(p);
		}
		return Fnety;
	}
	public void update(double dt,double fnetX,double fnetY){
		double ax=0,ay=0;
		ax=fnetX/this.mass;
		ay=fnetY/this.mass;
		this.xxVel=this.xxVel+ax*dt;
		this.yyVel=this.yyVel+ay*dt;
		this.xxPos=this.xxPos+this.xxVel*dt;
		this.yyPos=this.yyPos+this.yyVel*dt;
	}
	public static void draw(Planet p){
		String filename="./images/"+p.imgFileName;
		StdDraw.picture(p.xxPos,p.yyPos, filename);
		StdDraw.show();
	}

}