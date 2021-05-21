![alt text](https://github.com/SaurabhPotdar/Java-Demos/blob/main/Java/src/main/java/com/cg/java/lambda/Screenshot%202021-05-20%20155209.png)

1. None of the above functional interfaces have a throws declaration, we need to try-catch any compile time exceptions our lambda throws. 

2. Variables used in lambda should be final or effectively final.
```
interface ProductFilter{
	boolean accept(Product product);
}
```
Variable used in lambda should be initialized only once. (Reference should remain same)</br>
We can mutate it(modify state), but reference should remain same.</br>
We can add elements to list, change property of an object, ...
```
int priceLimit = 100;
ProductFilter filter = product -> product.getPrice().compareTo(priceLimit)<0;
priceLimit = 0;  //Error, as we are reassigning variable used in lambda
printProduct(products, filter);
```
The reason is, on line2 we are just defining lambda and it is actually executed on line 4 . But on line 3 we have changed the variable.</br>
Lambda just uses a copy of variable on stack, and if that is changed the program may break.</br>
For Runnable, the lambda is running in background and in between we change the variable used by lambda.

3. Lambdas on primitive type</br>
```Comparator.comparing()``` expects Object i.e Integer so it does boxing and unboxing, and this reduces performance. So use ```Comparator.comparingInt()```
```
ToIntFunction<String> a = s -> s.length();  //Sort based on length
Comparator<String> cmp = Comparator.compareInt(a);
list.sort(cmp);
```