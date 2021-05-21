1. **Intermediate and Terminal operations**</br>
Streams are lazily evaluated, so stream.sorted() will not do anything until we do a terminal operation on it.</br>
Intermediate: distinct, map, flatMap, limit, skip, peek, sorted, distinct</br>
Terminal: collect, count, forEach, min, max, reduce, all search operations(findFirst, allMatch,..)

2. **Creating stream**
```
Stream<String> s1 = Stream.of("A", "B", "C");
s1.forEach(System.out::println);
```
We can create stream from any Collection
```
List<String> list = Arrays.asList("D", "E", "F");
Stream<String> s2 = list.stream();
s2.forEach(s -> {
	// Multiple statements in for each
	System.out.println(s);
	...
});
```

3. **Filter**
```
List<Employee> employees = Employee.getEmployees().stream()
	.filter((employee) -> employee.getSalary() > 1000)
	.collect(Collectors.toList());
```

4. **Map** - Converting one object to other (One to One transformation)
```
Employee.getEmployees().stream().map(e -> e.getSalary())
```

5. **FlatMap** - One to Many transformation, flattens nested structure
```
List<List<String>> namesNested = Arrays.asList( 
	Arrays.asList("Jeff", "Bezos"), 
    	Arrays.asList("Bill", "Gates"), 
    	Arrays.asList("Mark", "Zuckerberg"));
List<String> namesFlatStream = namesNested.stream()
	.flatMap(Collection::stream)
	.collect(Collectors.toList());
System.out.println(namesFlatStream);  //[Jeff, Bezos, Bill, Gates, Mark, Zuckerberg]
```

6. **Search**</br>
All search operations are terminal. findFirst, findAny, anyMatch are shortcircuit operators. If the order dosent matter it is better to use findAny.</br>
allMatch(all elements should satisfy condition), noneMatch are not shortcircuit operators so they will continue forever for infinite stream
```
List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
```

7. **Sorting**</br>
Sorting for String,int,..
```
List<String> fruits = Arrays.asList("Apple", "Mango", "Banana");
fruits.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println); // Ascending
fruits.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println); // Descemding
```
For sorting Objects, we need to specify field for sorting
```
Employee.getEmployees().stream().sorted((e1, e2) -> e1.getSalary() - e2.getSalary()); // Using lambdas for Objects
```
Use **Comparator.comparingInt** for primitive types as Comparator.comparing does autoboxing and unboxing which affects performance.
```
// Sort by name in ascending and salary in descending
Employee.getEmployees().stream()
	.sorted(Comparator.comparing(Employee::getName)
	.thenComparing(Comparator.comparingInt(Employee::getSalary).reversed()))
	.forEach(System.out::println);
```

8. **Reduce**</br>
For an empty stream, we get an empty Optional
```
Optional<Integer> opt = Employee.getEmployees().stream()
	.map(e -> e.getSalary())
	.reduce((subtotal,element) -> subtotal+element);
```
We can also provide initial value, and if the stream is empty we get initial value insted of empty Optional.
```
int sum = Employee.getEmployees().stream()
	.map(e -> e.getSalary())
	.reduce(0, (subtotal,element) -> subtotal+element);
```
For parallel stream, we have ```reduce(initialValue, accumulator, combiner)```, combiner is used to combine results from parallel threads.
```
int sum = Employee.getEmployees().stream()
	.map(e -> e.getSalary())
	.reduce(0, Integer::sum, Integer::sum);
```
Another example
```
StringBuilder concat = Arrays.stream(grades)
	.parallel()
    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
```

9. **Collecting stream**
We want map object and store in a new list.
```.map().forEach(p->list.append(p))``` will throw a concurrent modification exception for parallel stream.</br>
To avoid this use
```.map().collect(Collectors.toList())```
Joining string: ```.collect(Collectors.joining(delimeter:','))```




