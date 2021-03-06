package kosta.cons;

public class Car {
	String model;
	char color;
	float price;
	Engine engine;
	
	// Constructors
	public Car() { }
	public Car(String model, char color, float price, Engine engine) {
		this.model = model;
		this.color = color;
		this.price = price;
		this.engine = engine;
	}
	public Car(String model, char color, float price){
		this.model = model;
		this.color = color;
		this.price = price;
	}
	
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	
	public String getDetails(){
		return model + "\t" + color + "\t" + price;
	}
	
	public String displayDetails(){
		return "\nModel : " + model + "\nColour : " + color +
				"\nPrice : $" + price + engine.display();
		
	}

	
}
