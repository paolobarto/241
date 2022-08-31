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
  
# 8/24 

## SQL Parts
* DML
  * Provides the ability to query information from the database and to insert tuples into, delete tuples from, and modify tuples in the database
* integrity - the DDL includes commands for speficfying integrity constraints
* View Definition - The DDL includes commands for defining views
* Transaction control - includes commands for defining views
* Transaction control - inculdes commands for specygin the beginning and ending of 
* Embedded transactionsSQl and Dynamic SQL - define how sql statements can be embedded


## Data definintion language
The SQL data-definition language (DDL) allows the specification of information about relations, including:
* The schema for each relation
* The type of values associated with each attribute
* The integrity constraints 
* The set of indicies 


<a href="https://ibb.co/ZNCdzLn"><img src="https://i.ibb.co/1bxZGvD/image.png" alt="image" border="0"></a>

<a href="https://ibb.co/hdTTvv6"><img src="https://i.ibb.co/NNGGzzv/image.png" alt="image" border="0"></a>

## Integrity Constraints to create a table 
* Types of integrity constraints 
  * Primary key (must be not-null and unique)
  * foreign key (refereances r) another table (reference table)
  * not null
* SQL prevents any update to the database that violates an integrity constraint
* Example:

```sql
create table instructor (
  ID        char(5),
  name      varchar(20) not null,
  dept_name varchar(20),
  salary    numeric(8,2),
  primary key (ID),
  foreign key (dept_name)references department
)

```

<a href="https://ibb.co/fkfc1Bv"><img src="https://i.ibb.co/GWyhxBT/image.png" alt="image" border="0"></a>

## Updates to tables
* Insert
  * insert into `instructor` values `('10211','Smith','Biology',66000)`
* Delete
  * Removes all tuples from the student relation
    * `delete from student`
* Drop table
  * `drop table r`
* Alter 
  * `Alter table r add A D`
    * Where A is the name of the attribute to be added to relation r and D is the domain of A
    * All existing tuples in the relation are assigned null as the value for the new attribute
  * `Alter table r drop A`
    * Where A is the name of an attribute of relation r
    * Dropping of attributes not suppported by many databases

## Basic Query structure
* A typical SQL query has the form: 
```sql
 select A1, A2,...An
 from r1,r2,...rn
  where P
```

  * Ai represents an attribute
  * Ri represents a relation
  * P is a predicate
* The result of an SQL query is a relation

## The select Clause
* The **select** clause lists the attributes desired in the result of a query
  * Corresponds to the projection operation of the relational algebra
  * Erasing the columns that are not listed
* Example: find the names of all instructors
```sql
select name
from instructor
```

* NOTE: SQL names are case insensitive (i.e., you may use upper - or lower-case letters.)
  * E.g., Name = NAME = name
  * Some people use uppercase whenever we use bold font
  * The result is a relation

* SQL allows duplicates in relations as well as in query results
* To force elimination of duplicates, insert the keyword distinct after select
* Find the department names of all instructors, remove all duplicates 

```sql
select distinct dept_name
from instrcuctor
```

* the keyword **all** specifies that duplicates should not be removed


* An asterisk in the select clause denotes "all attributes"
```sql
select *
from instructor
```

* An attribute can be a literal with no from clause
`select '437'`
  * Results is a table with one column and a single row with value "437"
  * Can give the column a name using 
  `select '437' as FOO`
* An attribute can be a literal with **from** clause
```sql
select 'A'
from instructor
```
* The select clause can contain aritmatic expressions involving the operation, +,-,*, and /, and operating on constraints or attributes of tuples
  * The Query 
  
``` sql
select ID,name, salary/12
from instructor
```

* can rename "salary/12" using as clause:
* `select ID,name,salary/12 as monthly_salary`

## The where clause
* The where clause specifies conditions that the result must satisy
  * Corresponds to the slection predicate of the relation algebra

``` sql
select name
from instructor
where dept_name = 'comp. sci'
```

* SQL allows the use of the logical connectives and, or, and not
* The operands of the logical connectives can be expressions involving the comparison operators <,<=,>,>=, =, and <> (not equal to)
* Comparisons can be applied to results of arithmetic expressions 
* To find all instructors in Comp. Sci. dept with salary > 80000

```sql
select name
from instructor
where dept_name = 'Comp. Sci' and salary > 80000

```

## The from Clause
* The from clause lists the relations involved in the query
  * Corresponds to the Cartesian product operation of the relational algrbra
* Find the Cartesian product instructor X teaches
```sql
select * 
from instructor,teaches
```
* Notation: `instructor X teaches`
* Generates every possible instructor - teaches pair, with all attributes from both relations
* For common attributes (e.g, ID) the attributes in the resulting table are renamed using the relation name (e.g., instructor.ID)
  * How many rows are in the resuling relation
* Cartesian product not very useful directly but useful combined with where-clause condition (selection operation in relational algebra)

<a href="https://ibb.co/x8nJk6c"><img src="https://i.ibb.co/rfB4jHL/image.png" alt="image" border="0"></a>

<a href="https://ibb.co/gJVgDYJ"><img src="https://i.ibb.co/DQpK7dQ/image.png" alt="image" border="0"></a>


# 8/29

## Aggrigate Functions
* These funcitons operate on the multiset of values of a column of a relation and return a value

* avg: average value
* min: minimum value
* max: maximum value
* sum: sum of values




* Find the average salary of instructors in the cs department
```sql
select avg(salary)
from instructor
where dept_name='Comp sci'

```

* Find the total number of instructors who teach a course in the Spring 2018 semester
```sql
select count (distinct ID)
from teaches
where semester = 'Spring' and year = 2018;
```
* Find the number of tuples in the course relation
```sql
select count(*)
from course;
```

**Aggrigate functions - group by**

* Find the average salary of instructors in each department
```sql
select dept_name, avg(salary) as avg_salary
from instructor
group by dept_name;
```

* Attributes in select clause outside of aggrigate functions must appear in _group by_ list
  INCORRECT QUERY
```sql
select dept_name,ID,avg(salary)
from instructor
group by dept_name
```
ID in this case is incorrect becuase it represents multiple duplicate records in the table


### Having Clause
* Find the names and average salaries of all departments whose average salary is greater than 42000 (over a group not on individual row)

```sql
select dept_name, avg(salary) as avg_salary
from instructor
group by dept_name
having avg(salary)>42000
```

* Note: predicates in the having clause are applied after the formation of groups whereas predicates in the where clause are applied before forming groups

```sql
select dept_name
from instructor natural join teaches natural join section
where semester = 'Spring' and year =2007
group by dept_name
having count (distinct id)>2
```

## Nested Subqueries
* SQL provides a mechanism for the nesting of subqueries. A subquery is a selct-from-where expression that is nested within another query.
* The nesting can be done in the following SQL query

```
select A1,A2,...
```

### Set membership
To check if an element is in a set or not

* Find courses offered in Fall 2017 and spring 2018

```sql
select distinct course_id
from section
where semester='Fall' and year = 2017 and
course_id in (select course_id
              from section
              where semester = 'Spring' and year =2018)
```

* Find courses offered in Fall 2017 but not in Spring 2018
```sql
select distinct course_id
from section
where semester='Fall' and year = 2017 and course_id not in (
  select course_id
  from section
  where semester = 'Spring' and year =2018
);

```

subquery can be seen as a function that is called for each record visited in the other query


* Name all instructors whose name is neither "Mozart nor Einstein"

```sql
select distinct name
from instructor
where name not in ("Mozart","Einstein")
```

* Find the total number of (distinct) students who have taken course sections taught by the instructor with ID 10101

```sql
select count (distinct ID)
from takes
where (course_id,sec_id,semster,year) in (
  select course_id, sec_id, semester, year
  from teaches
  where teaches.ID = 10101
);
```

* Note: Above query can be written in a much simpler manner.
* The formulation above is simple to illustrate SQL features
  * Can be done using a Cartesian product

## Set Comparison - "some" clause
* Find the name of instructors with salary greater than that of some (at least one) in the Biology department
```sql
select distinct T.name
from instructor as T, instructor as S
where T.salary > S.salary and S.salary.dept_name = 'Biology'
```
* Same query using > some clause

```sql
select name
from instructor
where salary>some(
  select salary
  from instructor
  where dept_name = 'Biology'
);
```
"salary>some" is set comparison

<a href="https://ibb.co/bWHL4Xf"><img src="https://i.ibb.co/7YgV3K9/image.png" alt="image" border="0"></a>

## Set comparison - "all" clause

* Find the names of all instructors whose salary is greater than the salary of all instructors in the Biology department

```sql
select name 
from instructor
where salary>all(
  select salary
  from instructor
  where dept_name = 'Biology'
);
```
### Test for empty relations 
* The exists construct return the value true if the argument subquery is nonempty
* Exists (r contatins records)
* Not Exists (r contains no records)

### Use of "exists" clause
* yet another way of specifying "Find all courses taught both in the Fall 2017 semester and in the Spring 2018 semester"
```sql
select course_id 
from section as S
where semester = "Fall" and yar = 2017 and exists (
  select *
  from section as T
  where semester = 'Spring' and year = 2018
  and S.course_id=T.coursee_id
);
```

* What's going on?
  * subquery is like a sub-prodeudre
  * outer query calls the function
  * can pass paramters in: S.course_id
* Correlation name - variable S in the outer query
* Correlated subquery - the inner query



* Find instructors who earn more than all the instructors in the Math
```sql
select id
from instructors as S
  where not exists (
    select *
    from instructor as T
    where dept_name='math'
    and T.salary>=S.salary
)
```
* Find all students who have taken all courses offered in the Biology deprtment
```sql
select distinct S.ID, S.name
from student as S
where not exists((
  Select course_id
  from course 
  where dept_name = "Biology")
  except(
    select T.course_id
    from takes as T
    where S.ID = T.ID
  )
  );
```
* First nested query lists all courses offered in Biology
* Second nested query lists all courses a particular student took


## Test for Absense of Duplicate Tuples
* the unique construct tests wheather a subquery has any duplicate tuples in its resuly
* The unique construct evaluate to "true" if a given subquery contains no duplicates
* Find all courses that were offered at most once in 2017

```sql
select T.course_id
from course as T
where unique (select R.course_id
from section as R
where T.course_id=R.course_id
and R.year=2017)
```


# 8/31 

## Joined Relations
* Join operations take two relations and return as a result another relation

### Natural Join in SQL

* It is a type of inner join
* Natural join matches tuples witht the same calues for all common attributes, and retains only one copy of each common column
* List the names of instructors along with the course ID of the courses that they taught

```sql
select name, course_id
from students, teach
where student.id=teach.id
```
same query in sql with "natural join" construct
```sql
select name,course_id
from student natural join takes

```

* A From clause can have multiple relations combined using natural join
```
select A1,A2,..An
from r1 natural join r2 natual join rn
where P;

```
**Natural Join takes place prior to where**

**Dangerous natural join**

* beware of unrealted attributes with same name which get equated incorrectly
* Example -- List the names of students along with the titles of courses that they have taken
  * Correct version
```sql
select name,title
from student natural join takes, course
where takes.course_id=course.course_id
```
* Incorrect version

```sql
select name,title
from student natural join takes natural join course

```

* This qury omits all pairs where the student takes a course in a department other than the student's own department
* The correct version (above), correctly outputs such pairs

## Natural join with using clause

* to avoid the danger of equating attributes erroneously, we can use the "using " construct that allows us to specifiy exactly which columnd should be equated
* query example
```sql
select name, title
from (student natural join takes) join course using (course_id)
```

* course_id attributes from two tables will be merged into one
* Two joined tuples may not have the same value on attributes that are not in the using list

### Join Condition

* The on condiditon allows a general predicate over the realtions being joined
* This predicate is written lise a where cluase predicate except for the use of the keyword on
* example

```sql
select *
from student join takes on student_id = takes_id
```
* the on condition above specifies that a tuple from student matches a tuple from takes if their ID values are equal
*  Equavelnt to

```sql
select *
from student,takes
where student_id=takes_id
```

* select * from course join prereq
  * on prereq.course_id=course_id;
  * both course_id of the two relations will be retained


### Outer join
* an extension of the join operation that avoids loss of information
* Computes the join and then adds tuples from one relation that does not match tuples in the other relation to the result of the join
* Uses null values
* THree forms of outer join
  * Left outer join
  * right outer join


#### Left outer join
* Course natural left outer join prereq

ALthough there is no matching records in join, the non matching relation will still be included


#### Right outer join
* Prioritizing all values of the right including when not included

#### Full Outer Join

* Retaining all joined values in realted tables
  

## Joined types and conditions
* Join operations - take two relations and return as a result another operation
* These additional operations are typically used as subquery ewxpressions in the from clause
* Join condition - defines which tuples in the two relations match
* Join type - defines how the tuples in each realtion that do not match any tuple in the other relation are treated


## Views
* In some cases, it is not desireable for all users to see the entire logical mofel (that is, all the actual relations stored in the database)
* Consider a person who needs to know an instructors name and department, but no the salary. This person should see a relation described in sql by

* A view is defined using the create view statement which has the form create view v as < qury expression > where expression is any legal sql expression the view name is represented by v
* Once a view is defined, the view name can be used to refer to the virtual relation that the view generates
* view definition is not the same as createing a new relation by evaluating the quert expression
  * Ratherm a view definitoin causes the saving of the expression; the expression is subsituted into queries using the view

* A view of instructors without their slary
```sql
create view faculty as 
select ID, name, dept_name
from instructor
```
* Find all instrcutors in the biology department
```sql
select name
faculty
where dept_name='Biology'
```

### Vies defined using other views
* One view may be used in the expression defining another view
* A view relation V1 is said to depend directly on a view relation v2. If v2 is used in the expression defining v1
* A view relation v1 is said to depend on view relaiton v2 if either 

<a href="https://ibb.co/BsKn9yz"><img src="https://i.ibb.co/w6rcfS0/image.png" alt="image" border="0"></a>


### Materialized Views

* Certain database systems allow view relations to be physcially stored
  * Physical cope created when the view is definiend
  * Such views are called materialized view
* If relations used in the query are updated, the materialized view result becomes out of date
  * Need to maintain view when data is changed


### Update a view
* Add a new tuple to faculty view which we definied earlier
```sql
insert into facult
  values ('30765','Green','Music');
```
* this insertaion must be represented by the insertion into the instructor relation
  * Must have a value for salary
* Two approaches
  * Reject the insert
  * Inset the tuple ('30765','Green','Music',null)
  * into the instructor relation


### View Updates in SQL

* Most SQL implementations allow updates only on simple views
  * The from clause has only one database relation
  * The select clause contains only attribbutes names of the relation and does not have any expressions, aggrigates, 