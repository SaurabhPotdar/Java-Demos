## [Installing Mongo DB](https://www.c-sharpcorner.com/article/how-to-set-up-and-starts-with-mongodb/#:~:text=Click%20on%20environment%20variables%20button,Program%20Files%5CMongoDB%5C%E2%80%9D)

## Connecting using Spring Boot
Default credentials: spring.data.mongodb.uri=mongodb://localhost:27017/airportdb
spring.data.mongodb.uri=mongodb://username:password@localhost:27017/airportdb

## Relational vs Mongo
| Relational  | Mongo |
| ------------- | ------------- |
| Tables  | Collections  |
| Rows  | Documents  |
| Columns  | Fields  |
| Primary Key  | Id  |

## Annotations
1.	@Id - Mongo DB uses special type called object id, so the id field type is String e.g. $oid": "6093a9ece188ea1ed068156a"
2.	@Document(“Name”) – Creates a document (i.e. table)
3.	@Field(“Name”) – To give custom column name to a field
