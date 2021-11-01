## References
[Cheat Sheet](http://web.archive.org/web/20180621022440/https://www.nurkiewicz.com/2013/08/optional-in-java-8-cheat-sheet.html)</br>
[Nested objects](https://lprakashv.medium.com/handling-nulls-in-nested-objects-java-7079b9413ec9)

## Why use Optional
1. Indicates that null can be returned by the method. eg. findAny(), findById(),...
2. Optional vs null check
```
String s = someFunc();
if (s != null) {
    System.out.println(s.toUpperCase());
}
```
The real advantage of Optional api is methods like eg. orElse(), orElseThrow(), ifPresent(), filter(), map()
 ```
 opt.map().filter(1).map(2)
 //If the filter(1) returns empty optional, then map(2) will not throw exception and we get empty optional
 //So we can chain operations without worrying about null pointer exception.
 ```
 ```
 someFunc().map(String::toUpperCase)
    .ifPresent(System.out::println);
 ```  
 ifPresentOrElse() in Java 9+
```
 opt.ifPresentOrElse(
   value -> System.out.println("Found: " + value),
   () -> System.out.println("Not found")
);
```
```
opt.filter(x -> x.contains("ab")).ifPresent(this::print);
```

## Empty Optional
```
System.out.println(ver.get()); //Throws NoSuchElementException if any object is null in the nested call
System.out.println(ver); //Empty optional, does not throw exception
```

## Nested object
```
Optional<String> ver = Optional.ofNullable(computer).map(c -> c.getSoundcard())
                  .map(s -> s.getUsb()).map(u -> u.getVersion());
```
