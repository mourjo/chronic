# chronic

Parse cron strings to show the possible values for minute, hour, day, month, day-of-week.

For `*/15 0 1,15 * 1-5 /usr/bin/find`, the expected output is
```
Minute: 0,15,30,45
Hour: 0
Day of Month: 1,15
Month: 1,2,3,4,5,6,7,8,9,10,11,12
Day of Week: 1,2,3,4,5
Command: /usr/bin/find
```

For an invalid cron string `*/15 0 1,15 * 1-5000 /usr/bin/find abcd`, the output is 
```
Invalid cron: */15 0 1,15 * 1-5000 /usr/bin/find abcd
Unexpected atom: Out of range: 5000
```

## Usage

Pre-requisites:
- Apache Maven 3.8.6+
- Java: 18.0.1.1+

Compile using:
```bash
mvn clean package
```

Run using:
```bash
java -jar target/chronic.jar "*/15 0 1,15 * 1-5 /usr/bin/find abcd"
```

The output for the above: 
```
Minute: 0,15,30,45
Hour: 0
Day of Month: 1,15
Month: 1,2,3,4,5,6,7,8,9,10,11,12
Day of Week: 1,2,3,4,5
Command: /usr/bin/find abcd
```

## Code structure
- A cron expression is contained in the `Expression` which contains a sequence of `Feild`s.
- A `Field` is responsible for understanding how to parse a cron string.
- Subclasses like `CommandField` and `HourField` are responsible for parsing the command portion and the hour portion respectively.
- A `Field` that is numeric, like `HourField` uses the `NumericParser` to convert the cron for that field into a sequence of numbers.
- `NumericParser` checks syntax of a cron string for a field and generates a sequence of numbers according to the field's bounds.

## Notes
- Only minute, hour, day of month, month, and day of week are supported
- `?` is not supported
- Special time strings like `@yearly` are not supported
- `,` is given the highest priority, so `/` when applied to a comma-separated string, the `/` applies only to the last item in the list, example: `1-5,11-15/5` will choose 1,2,3,4,5,15

## License

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
