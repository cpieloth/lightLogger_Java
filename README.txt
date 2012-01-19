LightLogger - A simple and light logging API for Java

This library is intended for smaller projects where Log4J is too complex.
The log messages can be filtered by levels. By using bitmask rather than a hierarchy, a individual level can be separately filtered.
In addition, LightLogger can be used without a configuration file.
Due to the use of interfaces LightLogger can be easily extended.

ILogger - Implementation of a logger for a specified target (console, file, ...)
ILevel - Opportunity to implement a user defined level
