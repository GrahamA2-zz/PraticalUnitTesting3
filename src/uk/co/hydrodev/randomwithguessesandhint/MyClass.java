package uk.co.hydrodev.randomwithguessesandhint;

public class MyClass{ 
    
	static class FactoryHelper{ 
        Foo makeFoo(  ){ 
            return new Foo(  ); 
        } 
    } 

    private FactoryHelper helper; 
    public MyClass(  ){ 
        this(  new FactoryHelper()); 
    } 

    MyClass( FactoryHelper helper ){ 

        this.helper = helper; 
    } 


    Foo foo = helper.makeFoo( ); 
    
    
    public static void main(String[] args) {
    	MyClass c = new MyClass();
    	System.out.println( c.print() );
	}

	private String print() {
		return foo.toString();
	}
	
} 