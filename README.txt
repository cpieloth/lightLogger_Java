LightLogger - A simple and light logging API for Java

This library is intended for smaller projects where Log4J is too complex.
The log messages can be filtered by levels. By using bitmask rather than a hierarchy, a individual level can be separately filtered.
In addition, LightLogger can be used without a configuration file.
Due to the use of interfaces LightLogger can be easily extended.

ILogger - Implementation of a logger for a specified target (console, file, ...)
ILevel - Opportunity to implement a user defined level
IFormatter - Defines a format for the log message

Example.java output:
LogMask: 2147483647
[FATAL] executor.lightLogger.Example: fatal crash
[ERROR] executor.lightLogger.Example: error occurred
[WARN] executor.lightLogger.Example: warning about coffee stock
[INFO] executor.lightLogger.Example: coffee is ready
[DEBUG] executor.lightLogger.Example: debug problems
[TRACE] executor.lightLogger.Example: trace method calls
[COOKIES!] executor.lightLogger.Example: foo barrr
LogMask: 2
[ERROR] executor.lightLogger.Example: error occurred
LogMask: 12
[WARN] executor.lightLogger.Example: warning about coffee stock
[INFO] executor.lightLogger.Example: coffee is ready
