# chronic

Parse cron strings


## Usage

Compile using:
```bash
mvn clean package
```

Run using:
```bash
java -jar target/chronic.jar "*/15 0 1,15 * 1-5 /usr/bin/find abcd"
```

Expected output for the above: 
```
Minute: 0,15,30,45
Hour: 0
Day of Month: 1,15
Month: 1,2,3,4,5,6,7,8,9,10,11,12
Day of Week: 1,2,3,4,5
Command: /usr/bin/find abcd
```

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
