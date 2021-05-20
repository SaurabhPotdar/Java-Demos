1. Variables used in lambda should be final or effectively final.
```
interface ProductFilter{
	boolean accept(Product product);
}
```
Variable used in lambda should be initialized only once.
```
int priceLimit = 100;
ProductFilter filter = product -> product.getPrice().compareTo(priceLimit)<0;
priceLimit = 0;  //Error, as we are reassigning variable used in lambda
printProduct(products, filter);
```
The reason is, on line2 we are just defining lambda and it is actually executed on line 4 . But on line 3 we have changed the variable.</br>
Lambda just use a copy of variable on stack, and if that is changed the program may break.</br>
For runnable, the lambda is running in background and in between we change the variable used by lambda.