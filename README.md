# JasperReports example

## Project on how to generate PDF report using JasperReports library

This project is based on **Spring Boot** and **Maven** with "batteries included": all the necessary resources (library dependencies, database and jasper reports) are included. To play with this project you just need to clone it, call the maven wrapper and (if everything goes fine) open the PDF generated report.

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.6.RELEASE)
```

### How to run this project

If you just want to run this example, you don't need any IDE (Eclipse/Netbeans/IntelliJ). Assuming you are running Linux, you just need these lines:

```shell
$ git clone https://github.com/sergiodurand/jasper-examples.git
$ cd jasper-examples
$ ./mvnw spring-boot:run
$ xdg-open /tmp/report.pdf
```
## Project details

### Database

For this project I created a very simple SQLite database using the [SQLite Browser](https://sqlitebrowser.org/) with only one table and some rows with random values. The database file is [**clients.db**](clients.db) and can be found at root of this project. 

The database has the following structure:

**Table**: CLIENTS

Name | Type | Constraints
-----|------|---------
ID | INTEGER | PK
NAME | TEXT | NOT NULL
COUNTRY | TEXT | NOT NULL

### JasperReport

If you Google for JasperReports you will find that exist several ways to work with it. The approach I chose here is to use a JavaBean to represent the data of each report. I also chose to use sub-report approach just to have an example on how to deal with it.

Both source and compiled reports are available at [**resources**](src/main/resources) directory. I have used the Jaspersoft Studio v6.6.0 to create and compile the reports.

### Java application

I tried to keep the project as simple as possible.

The [**pom.xml**](pom.xml) file will take care of all dependencies used on this project like Spring Boot, SQLite and Jasper.

The main class is the [**JasperExamplesApplication.java**](src/main/java/com/bkpmind/reports/jasper_examples/JasperExamplesApplication.java)

First the application access the **Client** service to retrieve the list of clients ordering by name and another one grouping by country.

Then it created a JasperReport data source object and they are added to the parameters map.

The final part of the application it to generate the report itself. I call the feelReport method passing the main report and the parameters. The main report will call the two sub-reports. Then it is called the exportReport to generate the PDF.

You can find the generated PDF at [**resources**](src/main/resources) directory.
