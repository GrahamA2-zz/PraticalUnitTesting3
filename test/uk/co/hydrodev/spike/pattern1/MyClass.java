package uk.co.hydrodev.spike.pattern1;

import uk.co.hydrodev.spike.*;

public class MyClass{ 
    
    Foo foo = makeFoo();
    
	Foo makeFoo() {
		return new Foo();
	}  
    
    public String print() {
		return foo.print();
	}
	
} 