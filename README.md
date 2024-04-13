# Statistics generator

### Project Description
The "Statistics generator" project is a tool for analyzing data about some entities (f.e. movies). The program allows gathering statistics on various attributes such as title, release year, genre, and director.

### Core Entities
##### Movie
The Movie class represents a movie entity. Each movie has the following attributes:
- title - the title of the movie
- year - the release year
- genre - the set of genres of the movie (represented by the Genre enum)
- director - the director of the movie
##### Genre
The Genre enum contains a list of possible movie genres.

### Examples of Input and Output Files
##### Input Files
The program takes directory with JSON files. Each file contains an array of Movie objects represented in JSON format.

**Example input file movies.json:**
```json
[
    {
      "title": "They Shall Not Grow Old",
      "year": 2018,
      "rated": "R",
      "released": "01 Feb 2019",
      "runtime": "99 min",
      "genre": "Documentary, History, War",
      "director": "Peter Jackson",
      "writer": "Peter Jackson",
      "actors": "Mark Kermode, Peter Jackson"
    },
    {
      "title": "Midnight Family",
      "year": 2019,
      "rated": "N/A",
      "released": "06 Dec 2019",
      "runtime": "81 min",
      "genre": "Documentary, Action, Crime, Drama",
      "director": "Luke Lorentzen",
      "writer": "Luke Lorentzen",
      "actors": "Fer Ochoa, Josue Ochoa, Juan Ochoa"
    },
    {
      "title": "Pain & Gain",
      "year": 2013,
      "rated": "R",
      "released": "26 Apr 2013",
      "runtime": "129 min",
      "genre": "Action, Comedy, Crime, Drama",
      "director": "Michael Bay",
      "writer": "Christopher Markus (screenplay), Stephen McFeely (screenplay), Pete Collins (based on the magazine articles by)",
      "actors": "Mark Wahlberg, Dwayne Johnson, Anthony Mackie, Tony Shalhoub"
    }
]
```

##### Output Files
The program generates XML files containing statistics for the specified attribute of movies. Each file contains information about the number of movies for each unique value of the specified attribute.

**Example output file statistics_by_genre.xml:**
```xml
<statistics>
  <item>
    <value>Documentary</value>
    <count>2</count>
  </item>
  <item>
    <value>Drama</value>
    <count>2</count>
  </item>
  <item>
    <value>Action</value>
    <count>2</count>
  </item>
  <item>
    <value>Crime</value>
    <count>2</count>
  </item>
  <item>
    <value>History</value>
    <count>1</count>
  </item>
  <item>
    <value>War</value>
    <count>1</count>
  </item>
  <item>
    <value>Comedy</value>
    <count>1</count>
  </item>
</statistics>
```

### Run
First package the project into jar-file
```
mvn package
```

Then execute it like this: java -jar jar-filename.jar path-to-directory-with-JSON-files attribute-name. There is test folders in resources which you might use.
```
java -jar target/profitsoft_block1-1.0-SNAPSHOT-jar-with-dependencies.jar target/classes/normal_folder genre
```

Another option - is to Main.main() right in the IDE by clicking button RUN. In this case you need to add path-to-directory and attribute-name as arguments to the app.    

### Experiments with Threads
The project implements parallel file processing using multithreading. Experiments were conducted to measure the execution time with different numbers of threads.

**Results of the experiments:**

- 1 thread: 85 ms
- 2 threads: 82 ms
- 4 threads: 94 ms
- 8 threads: 88 ms

Based on the results, it can be observed that the execution time varies slightly with different thread counts. In this case, using 2 threads appears to be the most efficient in terms of overall execution time. However, the optimal number of threads may vary depending on the system's hardware and the nature of the data being processed.
