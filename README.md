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
- get a certain line in the DataFrame.
- get a certain column in the DataFrame.

To run code coverage :

mvn clean jacoco:prepare-agent install jacoco:report
