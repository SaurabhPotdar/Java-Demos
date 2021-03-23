package com.cg.java.lambda;

interface Drawable {
	public void draw();
}

interface Addable {
	public int add(int a,int b);
}

public class LamdaExpressions {
	
	public static void main(String[] args) {
		
		//Anonymous class
		Drawable drawable = new Drawable() {	
			@Override
			public void draw() {
				System.out.println("Drawing");
			}
		};
		drawable.draw();
		
		Drawable drawable2 = () -> System.out.println("Drawing");
		drawable2.draw();
		
		Addable addable = (a,b) -> a+b;
		System.out.println(addable.add(1, 2));
	}
	
}
