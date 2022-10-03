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





# 9/7 

## outline of ER model

* The ER data mode was developed to facilitate database design by allowing specification of an eterprise schema that represents the overall logocal structure of the database
* The ER data model employs three basic conceptws
  * Entity sets
  * relationship sets
  * attributes
* The ER model also has an associated diagrammatic representation, the ER diagram which can express the overall logical structure of a database graphically

## Entity Set
* An entity is an object that exists and is distinguinshable from other objects
* An entity set is a set of entities of the same type that share the same properties
* An entitiy is represented by a set of attributes; i.e., descriptive properties possessed by all members of an entity set
  * Example
```
instructor=(ID,name,salary)
course=(course_id,title,credits)
```
* a subset of attributes form a primary key of the entity set; i.e., unquely identifying each member of the set
* Entity sets can be represented graphically as follows:
* rectangles represent entity sets
* Attributes listed insisde entity rectangle
* Underline indicates primary key attributes

## Relationship sets
[![image.png](https://i.postimg.cc/5y928pGf/image.png)](https://postimg.cc/ftFsN7DP)

[![image.png](https://i.postimg.cc/g0ngMPC7/image.png)](https://postimg.cc/gwbyJ1ky)

[![image.png](https://i.postimg.cc/8PxcFd1r/image.png)](https://postimg.cc/2VFrMZLz)

[![image.png](https://i.postimg.cc/3N5781jK/image.png)](https://postimg.cc/N94Zb1PV)

## Roles
* Entity sets of a relationship need not be distinct
  * Each occurance of an entity set plays a "role" in the relationship
* The lables "course_id" and "prereq_id" are called roles

## Degree of a relationship set
* Binary relationship
  * Involve two entity sets (or degree two)
  * most relationship sets in a database system are binary
* Relationships between more than two entity sets are rare. Most relationships are binary
  * Example: students work on research projects under the guidance of an instructor
  * relationship proj_guide is a ternary relationship between instructor,
  * relationship proj_guide is a ternary relationship 


## Complex Attributes
* Attribute types
  * Simple (or atomic) and composite attributes
    * "CSE-241" atomic or composite
  * Single-valued and multivalued attributes
    * Example: multivalues attribute: phone_numbers
  * Derived attribtues
    * Can be computed from other attributes
    * Example: age, given date_of_birth
  * Domain- the set of permitted values of each attribute

[![image.png](https://i.postimg.cc/6pfdd6MK/image.png)](https://postimg.cc/yDWgKKqp)

[![image.png](https://i.postimg.cc/Zn5ghD0B/image.png)](https://postimg.cc/0MhZYnYP)

## Mapping cardinality constraints
* Express the number of entities o=to which another entity can be associated via a relationship set
* Most usefule in describing relationship sets
* For a binary relationship set the mapping cardinality mist be one of the following types
  * One to noe
  * one to many
  * many to one
  * many to many


* We express cardinality constraints by drawing either a directed line, signifying one ore and undirected line signifying meny, between the relationship set and the entity set

[![image.png](https://i.postimg.cc/FK7FZDhZ/image.png)](https://postimg.cc/yJCz6hgg)

[![image.png](https://i.postimg.cc/gkgPT8SB/image.png)](https://postimg.cc/QHKPB9rp)

[![image.png](https://i.postimg.cc/G3Q04vCh/image.png)](https://postimg.cc/RqNp86hj)

[![image.png](https://i.postimg.cc/CLSqNTXs/image.png)](https://postimg.cc/pycrW72m)

In ER design there is no concept called foreign key



# 9/12/2022

## Weak Entity Sets 
* Consider a section entity which is uniquely identified by a course_id, semester, year, and sec_id
* Clearly, section entities are related to course entitties suppose we create a relationship set sec_course between entity sets section and course
* Note that the information in sec_course is redundant, since section already has an attribute course_id, which identifies the course with which the section is related
* One option to deal with this redundancy is to get rid of the relationship sec_course; however, by doing so the relationship between section and course becomes implicit in an attribute, which is not desirable
* An alternative way to deal with this redundancy is to not store the attribute course_id in the section entity and to only store the remaining attributes section_od, year, and semester
  * However the entity set section then does not have enough attributes to identify the particular section entity uniquely
* To deal with this problem, we treat the relationship sec_course as a special relationship that provides extra information, in this case, the course_id, required to identify the section entities uniquely
* A weak entity set is one whose existence is dependent on another entity, cakked its identifying entity
* Instead of associating a primary key with a weak entity, we use the identifying entity, along with extra attributes called discriminator to uniquely identify a weak enity
* An entity set that is not a weak entity set is termed a strong entity set
* Every weak entity mist be associated with an identfying entity; that is, the wek entity set is said to be existent dpenedent on the identifying entity set
  
[![image.png](https://i.postimg.cc/B6BxqVrH/image.png)](https://postimg.cc/R6q3dXVZ)

[![image.png](https://i.postimg.cc/wjPrY1zL/image.png)](https://postimg.cc/qNsw8Mzv)

[![image.png](https://i.postimg.cc/FF3LQztc/image.png)](https://postimg.cc/Wtp3G21b)

## Reduction to relation Schemas

* Entity sets and relationship sets can be expressed uniformly as relation schemas that represent contents of the database

[![image.png](https://i.postimg.cc/RVqqZZyX/image.png)](https://postimg.cc/RN5vP4MH)

[![image.png](https://i.postimg.cc/m20Mx9kQ/image.png)](https://postimg.cc/fJfkmJxL)

* A multivalued attribute M of an entitiy E is represented by a seperate schema EM 
* Schema EM has attributes corresponding to the primary key of E and an attribute corresponding to multivalued attribute M
* Example: Multivalued attribute phone_number of instructor is represented by a schema:
  * inst_phone=(ID,phone_number)
* Each value of the multivalued attribute maps to a seperate tuple of the relation on schema EM
  
[![image.png](https://i.postimg.cc/fyHbwGM3/image.png)](https://postimg.cc/zHgN7Pb5)

[![image.png](https://i.postimg.cc/QMQbtbwg/image.png)](https://postimg.cc/jWjN3zsC)

* For one-to-one relationship sets, either side can be cohesn to act as the "many" side
  * Reduces to a special "many-to-one" case
  * That is , an extra attribute can be added to wither of the tables corresponding to the two entity sets 
  * E.g., onto-to-one instructor advising student relationship. Add an attribute "advises" to the instructor schema
* If participation is partial on the "many" side replacing a schema by an extra attribute in the schma corrsponding to the many" side could result in null values
  * If an instructor advises no one, advises = null


## Specialization
* Overlapping - employee and student
* Dijoint - instructor and secretary
* Total and partial 
* These are all out of business considerations 
  
[![image.png](https://i.postimg.cc/rwF8JSB8/image.png)](https://postimg.cc/2VMRjBjJ)

* Method 1
  * Form a schema for the higher-level entity
  * Form a schema for each lower entity set, include primary key of higher level entity set and local attributes
  * Drawback: getting information about, an employee requires accessing two relations, the one corresponding to the low-level schma and the one corrsponding to the high-level schema 
[![image.png](https://i.postimg.cc/4dbNfZq5/image.png)](https://postimg.cc/2byfTpzb)

* Method 2
  * Form a schema for each entity set with all local and inherited attributes
  * Drawback: name street and city may be stored redundantly for people are are both students and employees 

[![image.png](https://i.postimg.cc/GtHNMxFy/image.png)](https://postimg.cc/MfJdHRr6)

[![image.png](https://i.postimg.cc/T3Frmqws/image.png)](https://postimg.cc/Js3DCXj3)


# 9/21

The sql file code is used to check existing constraints when performing certain actions on defined sql tables.


`start with` requires starting point and to be used recursivly

getting prereqs of prereqs 
```sql
desc prereq;
select * from prereq
start with course_id=367
connect by couse_id = prior prereq_id

```

# 9/28

* Suppose we are given a relation`student_grades(ID,GPA)`
* Find the rank of each student 
  `select ID,rank() over (order by GPA desc) as s_rank from student_grades`
* An extra `order by` clause is needed to get them in sorted order

```sql
select ID,rank() over (order by GPA desc) as s_rank
from student_grades
order by s_rank
```

* Ranking my leave gaps: e.g. if 2 students have the same top GPA, bith have rank 1 and the next rank is 3
  * `dense_rank` does not leave gaps, so next dense rank would be 2

* Ranking can be done within partition of the data
* "Find the rank of students within each department"
```sql
select ID, dept_name,
rank() over(partition by dept_name order by GPA desc) as dept rank
from dept_grades
order by dept_name,dept_rank
```

* Multiple rank clauses can ocur in a single select clause
* Ranking is done after aookying group by clause/aggregation
* Can be used to find top-n results

* Other ranking functions:
  * percent_rank(within partition, if partitioning is done)
  * cume_dist(cumulative distribution)
    * Fraction of tuples with preceding values
  * row_number(non-dterministic in presences of duplicates)
* SQL:1999 permits the user to specify nulls first of nulls last

```sql
select ID,
rank() over (order by GPA desc nulls last) as s_rank
from student_grades

```

* For a given constant n, the ranking the function `ntile(n)` takes the tuples in each partition in the specific order, and divides them into n buckets with equal numbers of tuples

```sql
select ID,ntile(4) over (oder by GPA desc) as quartile
from student_grades
```


## Windowing
* Used to smooth out random variations 
* `moving average` "Given daily rading volumes of a S&P 599 stock, find the smoothed curve of the column"
* `Window specification` in SQL:
  * given relation sales(date,value)

```sql
select date, sum(value) over
  (order by date rows between 1 preceding and 1 following) from sales
```

* Examples of other window specifications
  * rows between unbounding preceding and current row
  * rows unbounded preceding
  * range between 10 preceding and current row
    * All rows with values between current row value - 10 to current values
  * range interval 10 day preceding
    * Not including current row
  
* Can do windowing within partitions 
"Given a relation transaction(account_number,date_time,value), where value is positive for a deptosit and negative for a withdrawal"
* "Find total balance of each account before each transaction on the account"
```sql
select account_number,date_time, sum(value) over
( partition by account_number
order by date_time
rows unbounded preceding)
as balance
from transaction
order by account_number,date_time
```


## OLAP
* Online Analytical Processing (OLAP)
  * Interactive analysis of data, allowing data to be summarized and viewed in different ways in an online fashion
* Data can be modeled as dimension attributes and measure attributes are called multidimensional data
  * Mesause attributes
    * Measure some values
    * can be aggregated upon
    * the attribute number of the sales relation
  * Dimension attributes
    * Define the dimensions on which measue attributes (or aggregates thereof) are viewed

## Data Cube
**Cross tabulation of sales by item_name and color**

[![image.png](https://i.postimg.cc/Yq4pThJK/image.png)](https://postimg.cc/ykH4ZY1v)

[![image.png](https://i.postimg.cc/fL8JM1zV/image.png)](https://postimg.cc/WFJ2nWJV)

* Cross-tabs can be eaisily extended to deal with hierarchies
  * Can drill down or roll up on a hierarchy

**Relational Representation of Cross-tabs**
* Cross-tabs can be represented as relations 
  * We use the value all is used to represent aggrigates
  * The SQL standard actually uses null values in place of all despite confusion with regular null values
  * Generated by group by


* The Cube operation computes unino of group by's on every subset of the specified attributes
* Example relation for this section
  * `sales(item_name,color,clothes_size,quantity)`
* Consider this query
```sql
select item_name, color,size,sum(number)
from sales
group by cube(item_name, color, size)
```

This computes the union of eight different grouping of the sales relation:
```
{ (item_name,color,size),(item_name,color),
  (item_name,size),     (color,size),
  (item_name),           (color),
  (size),                 ()}
```
where () denotes an empty group by list
* For each grouping, the result contains the null value for attributes not present in the grouping


* Relational representation of cross-tab that we saw earilier 
....


* Can use the function `decode()` in the select clause to replace such nulls by value all


* The `rollup` construct generates union on every prefix of specificed list of attributes
```
select item_name,color,size,sum(number)
from sales
group by reollup(item_name,color,size)
```

Generates union of four groupings:

`{(item_name,color,size),(item_name,color),(item_name),()}`

* Rollup can be used to generate aggregates at multiple levels of a hierachy
* Suppose table itemcategory(item_name,category) gives the category of each item. then
```sql
select category, item_name, sum(number)
from sales,itemcategory
where sales.item_name=itemcategory.name 
...

```


* Mutliple rollups and cubes can be used in a single gorup by clause
  * Each generates set of group by lists, cross product of sets gives overall set of group by lists

```sql
select item_name,color,size,sum(number)
from sales
group by rollup(item_name),rollup(color,size)
```


### Other processing operations

* Pivoting- changes the dimensions used in cross-tab is used




# 10/3 Normalization

## Features of Good Relational Designs

### Decomposition
* The only way to avoid the repitions of information problem in the in_dept schema is to decompose it into schemas -instructor and department schemas
* Not all decompositions are good. Suppose we decompose
  * employee(ID,name,street,city,salary)
  * into 
  * ....


### A lossy decomposition

* Let R be a relational schema and let R1 and R2 form a decomposition of R. That is R= R1UR2
  * R1 and R2
* We say that the decomposition is a lossless decomosition if there is no loss of information by 


## Normalization Theory
* Decide wheather a particular relation R is in "good" form.
  * There are several standard of being "good"
* In the case that a relation R is not in "good" form, decompose it into set of 

## Functional Dependencies
* There are usually a veriety of constrains (rules) on the data in the real world
* For example, some of the constraints that are expected to hold in a university database are:
  * Students and instructors are uniquely identified by their ID.
  * Each student and instructor has only one name
  * Each instructor and student is primarily associated with one department
  * Each department had only one value for its budget and only one assocated building
* An instance of a relation that satisfies all such real-world constraints is called a legal instance of the relation;
* A legal instance of a database is one where all the relation instances are legal instances
* Constrains on the sseet of legal relaions 
* Require that the value for a certain ....

[![Screen-Shot-2022-10-03-at-11-18-19-AM.png](https://i.postimg.cc/501sx6XM/Screen-Shot-2022-10-03-at-11-18-19-AM.png)](https://postimg.cc/CZPG7MCv)

## Closure of a Set of Functional Dependencies 
* Given a set F of functional dependencies, there are certain other functional dependencies that are logically implied by F.
  * If A -> B and B-> C then we can infer A -> C
* The set of all functional dependencies logically implied by F is the closure of F
* We denote the closure of F by F^+
  * More on how to compure F^+ in later sections

## Keys of functional Dependencies
* K is a superkey for relation schema R iff K -> R
* K is a candidate key for R iff
  * K->R and 
  * for no x subset of K, x -> R
* Thus, a candididate key is a superkey, but a super key may not be a candidate key
* Functional dependencies allow us to express constraints that cannot be expressed using super keys, Consider the schema:
  `in_dept(ID,name,salary,dept_name,building,budget)`
* We expect these functional dependecies to hold:
  * dept_name-> building
  * ID-> building
* but would not expect the following to hold
  * dept_name->salary

## Use of Functional Dependencies
* We use functional dependencies to :
  * To test relations to see if ther are legal under a given set of functional dependencies
    * If a realtion r is legal under a set F of functinoal dependiencies we say that r  satisfies F
  * To specify constraints on the set of legal relations
    * We say that F holds on R if all legal relations of R satisfy the set of functional dependencies F 
  * Note : A specific instance of a relation schema may satisfy a funcitnoal dependency even if the functinoal dependency does not hold on all legal instances
    * For example: a specific instance of instructor may, by chance



## Trivial Funcitional Dependenies
* A functional dependency is trivial if it is satisfied by all instances of a relation

## Lossless Decomposition
* We can use functinoal dependencies to show when certain decompositions are lossless
* For the case of R = (R1, R2) we requre that for all possinble relations r on schema R
* A decomposition of R into R1 and R2 is lissless decomposition if at lest one of the the following depencies is in F+
  * R1 (interset) R2 -> R1
  * R1 (intersect)R2 -> R2



## Dependency preservation
* Testing functional dependency constrainst each time the database is updated can be costly
* It is useful to design the database in a way that constaints can be tested efficiently
* If tsting a functinoal dependecny can be done by considering just one relation, then the cost of testing this constrinat is low
* When decomposing a realtion is possible that it is no longer possible to do the testing without having to perform a cartesian product

