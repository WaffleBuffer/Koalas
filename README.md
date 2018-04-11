[![Build Status](https://travis-ci.org/WaffleBuffer/Koalas.svg?branch=master)](https://travis-ci.org/WaffleBuffer/Koalas)
# Koalas
Python library Pandas implementation in Java

# Features

In Koala, you have two main objects : DataFrame (implements IDataFrame) and Column.
- A DataFrame is basically a List of Column. You can compare it to a SQL Table.
- A Column is a list of data, all the same types, that must be comparable to themself.
  You can compare it to a Column in SQL.
- You also have the GroupBy object which is the result of a GroupBy operation
  (same operation as in SQL).

You have some conveniant display features in the form of methods returning a String :
- display the entire DataFrame,
- display first or last lines,
- get some statistics about the DataFrame,
- get the names of the columns.

This library is also usefull to manage data in the form of methods.
- get some lines in the DataFrame from indexes.
- get some columns from their names.
- get all the data.

Finally you can use GroupBy operation (like in SQL). This will return a GroupBy
object on which you can then apply some mathematical operations which will return
a sub DataFrame.

# Maven use

To get the library JAR file :

mvn clean package

It will then be located in ./target/Koala-<Version>.jar

To run code coverage :

mvn clean jacoco:prepare-agent install jacoco:report

The report will be located in ./target/site/jacoco/index.html (open it with an internet browser)
