## [Installing Mongo DB](https://www.c-sharpcorner.com/article/how-to-set-up-and-starts-with-mongodb/#:~:text=Click%20on%20environment%20variables%20button,Program%20Files%5CMongoDB%5C%E2%80%9D)

## Connecting using Spring Boot
**Default credentials**: spring.data.mongodb.uri=mongodb://localhost:27017/airportdb
spring.data.mongodb.uri=mongodb://username:password@localhost:27017/airportdb

## Relational vs Mongo
| Relational  | Mongo |
| ------------- | ------------- |
| Tables  | Collections  |
| Rows  | Documents  |
| Columns  | Fields  |
| Primary Key  | Id  |

## Annotations
1.	@Document(“Name”) – Creates a document (i.e. table)
2.	@Id - Mongo DB uses special type called **object id**, so the id field type is **String** e.g. $oid": "6093a9ece188ea1ed068156a".</br> ```@Id private String id;```
3.	@Field(“Name”) – To give custom column name to a field
4.	@Transient - To exclude a field from being persisted.
5.	@Indexed
6.	@TextIndexed - To implement full text search, works on String and Array of String
7.	@CompoundIndex - To create index using multiple fields
8.	@DbRef - For relationships

```
@Document(collection = "Hotels")
@CompoundIndex(def="{'name':1, 'pricePerNight':-1}")  //1:Asc, -1:Desc
public class Hotel {
    @Id
    private String id;
    @TextIndexed
    private String name;
    @Indexed(direction = IndexDirection.DESCENDING, unique=false)
    private int pricePerNight;
    private int rooms;
    @DbRef
    private Address address;
}
```

## Query Execution
| Without indexes  | With indexes |
| ------------- | ------------- |
| Collection scan, each document is evaluated  | Does not scan every document in collections  |
| Slow searches  | Fast searches  |
| Fast inserts/updates  | Slower inserts/updates  |

## Query object
```
Query byAgeQuery = Query
    .query(Criteria.where("age").gt(18).lt(75))
    .with(Sort.by(Sort.Direction.DESC, "age"))
    .with(PageRequest.of(1,10));
List<Person> people = this.mongoTemplate.find(byAgeQuery, Person.class)
```
Multiple criteria
```
Query byAgeQuery = Query.query(new Criteria())
    .orOperator(Criteria.where("age").is(18),
               (Criteria.where("nbSeats").gte(2))
);
List<Person> people = this.mongoTemplate.find(byAgeQuery, Person.class)
```
### Mongo Filter Operators
is/ne(equality), lt/lte(less than), gt/gte(greater than), in(value in list), exists(has value), regex(patterns)
