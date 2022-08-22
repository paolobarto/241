# 241 Lecture Notes 8/22 First lecutre

## Data Base-system Applications

* Collection of interrelated data
* Set of programs to access the data
* An environment that is both convenient and efficient to use
* Manage collections of data that are:
  * Highly valuable
  * Relatively large
  * Accessed by multiple users and applications, often at the same time


*Purpose of Database Systems* 
* Data redundancy and inconsistency: data is stored in multiple file formates resulting in duplication of information in different files
* Difficulty in accessing data
  * Need to write a new program to carry out each new task
* Data isolation
  * Multiple files and formats
* Integrity problems
  * Integrity constraints (e.g., account balance > 0) become "buried" in program code rather than being stated explicitly
  * Hard to add new constraints or change existing ones 
* Atomicity of updates
  * Failures may leave database in an inconsistent state with partial updates carried out
  * Example: Transfer of funds from one account to another should either complete or not happen at all.
* Concurrent access by multiple users (A.K.A. Race error!!)
  * Concurrent access needed for performance
  * Uncontrolled concurrent access can lead to inconsistencies
    * Ex: two people reading a balance (say 100) and updating it by withdrawing money (say 50 each) at the same time
* Security problems
  * Hard to provide user access to some, but not all, data

## Data Models
* A collection of tools for describing
  * Data
  * Data relationships
  * Data semantics
  * Data constraints
* Relational Model
* Entitiy-Relationship data model (mainly for database design)
* Object-based data models (Object-oriented and Object-relational)
* Semi-structure data model (XML)
* Other older models:
  * Network model
  * Hierarchical model


**Relational Model == Table**


Attributes exist as column values that represent entity 


Relational model - storing data into two seperate tables

Entity Relationship model - two sets of related data 

## Levels of Abstraction
* Physical level: describes how a record (e.g. instructor) is stored.
* Logical level: Describes data stored in database, and the relationships among the data

```
type instructor = record
    ID: string;
    name: string;
    dept_name:string;
    salary:integer;
    end;

```
* View Level: application programs hide details of data types. views can also hide information (such as an employee's salary) for security purposes.

<a href="https://ibb.co/kqbKq9C"><img src="https://i.ibb.co/j30h3WX/image.png" alt="image" border="0"></a>

## Instance and Schemas
* Similar to types and variables in programming languages
* Schema - the overall logical structure of the database
  * Example: The database consists of information about a set of customers and accounts in a bank and the relationship between them
    * Analogous to type information of a variable in a program
* Instance - the acutual content of the database at a particular point in time
  * Analagous with the type of a variable

## Data definintion Language (DDL)

* Sepcification notation for defining the database schema
  example 
  ```
    create table instructor(
        ID      char (5)
        name    varchar(20)
        dept_name   varchar(20)
        salary      numeric(8,2))
  ```
* DDL compliler generates a set of table templates stored in a data dictionary
* Data dictionary contains meta data
  * Database sechme 
  * Integrity constraints
    * Primary Key (ID uniquely identifies instructions)
    * Authorization

## Data manipualtion language (DML)
* Language for accessing and updating the data organized by the appropriate data model
  * DML also known as query language
* Two classes of languages
  * Pure - used for proving properties about computational power and optimization
  * Relational algebra
  * Tuple rlational calculus
  * Domain relational calculus
* Commercial - used in commercial systems 
  * We will use SQL

* Two types of DML
  * Procedural DML -- require a user to specify what data are needed and how to get those data
    * need to know the physical details of data language
  * Declartive DML -- require a user to specify what data are needed withour specifiying how to get those data
    * The details are left to the DBMS
* Declartive DML are usually eaiser to learn and use than are procedural DMLs
  * `select dept_name from department`
* Declaritive DMLs are also reffered to as non-procedural DMLs
* The portion of a DML that involves information retrival is called a query language

## SQL Query Language
* SQL query language is nonprocedural. A query takes as input several tables (possibly only one) and always returns a single table
* Example to find all instucturs in comp sci dept
  
```
select name
from instructor
where dept_name = 'Comp. Sci'
```
will output a table with single column called name

* SQL is Not a Turing machine equivalent language
* To be able to compute complex functions SQL is usually embedded in some higher-level language
* Application programs generally access databases through one of 
  * Language extentions allow embedded SQL
  * Application program interface (e.g., ODBC/JDBC) which allow SQL queries to be sent to a database

## Database Design
The process of designing the general structure of the database:
* Logical Desgin - Deciding on the database schema. Database design requires that we find a "good" collection of relation schemas.
  * Business decision - what attricbutes should we record in the database?
  * CS decision - What relational schemas should we have and how should the attributes be distributed among the various relation schemas
  * Normalization theory
* Physical Design - Deciding on the physical layout of the database
  
## Database Engine
