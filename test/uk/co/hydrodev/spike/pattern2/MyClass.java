package uk.co.hydrodev.spike.pattern2;

import uk.co.hydrodev.spike.*;

public class MyClass{ 
    
	static class FactoryHelper{ 
        Foo makeFoo(){ 
            return new Foo(); 
        } 
    } 

    private FactoryHelper helper; 
    
    public MyClass(  ){ 
        this(new FactoryHelper()); 
    } 

    MyClass( FactoryHelper helper ){ 
        this.helper = helper; 
    } 

    Foo foo = helper.makeFoo(); 
    
    public static void main(String[] args) {
    	MyClass c = new MyClass();
    	System.out.println( c );
    	System.out.println( c.print() );
	}

	public String print() {
		return foo.print();
	}
	
 
	
} 