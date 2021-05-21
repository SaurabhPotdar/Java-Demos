1. Intermediate and Terminal operations</br>
Streams are lazily evaluated, so stream.sorted() will not do anything until we do a terminal operation on it.</br>
Intermediate: distinct, map, flatMap, limit, skip, peek, sorted, distinct</br>
Terminal: collect, count, forEach, min, max, reduce, all search operations(findFirst, allMatch,..)

2. Creating stream
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

3. Filter
```
List<Employee> employees = Employee.getEmployees().stream()
	.filter((employee) -> employee.getSalary() > 1000)
	.collect(Collectors.toList());
```

4. Map - Converting one object to other (One to One transformation)
```
Employee.getEmployees().stream().map(e -> e.getSalary())
```

5. FlatMap - One to Many transformation, flattens nested structure
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

6. Search - All search operations are terminal
findFirst, findAny, anyMatch are shortcircuit operators. For unordered stream findFirst and findAny will act same.</br>
allMatch(all elements should satisfy condition), noneMatch are not shortcircuit operators so they will continue forever for infinite stream
```
List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
```

7. Sorting
```
// Sorting for String,int,..
List<String> fruits = Arrays.asList("Apple", "Mango", "Banana");
fruits.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println); // Ascending
fruits.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println); // Descemding
```





