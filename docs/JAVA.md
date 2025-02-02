# JAVA

## Setup

### JDK

`Windows` 配置 `JDK` 可以使用 `PowerShell` 执行以下命令。

```powershell
winget search openjdk
winget install AdoptOpenJDK.OpenJDK.8
```

如果需要安装多个JDK版本，可以使用 [jvms](https://github.com/ystyle/jvms)

IDE可以选择 `Eclipse` 或 `VSCode`

### `Eclipse` 插件

[Eclipse EGit: Git Integration for Eclipse](https://github.com/eclipse-egit/egit/wiki/New-and-Noteworthy-7.1)

[Eclipse Linux Tools](https://github.com/eclipse-linuxtools/org.eclipse.linuxtools/blob/master/RELEASE_NOTES.md)

[Eclipse Maven Integration - m2eclipse](https://github.com/eclipse-m2e/m2e-core/blob/master/RELEASE_NOTES.md#270)

### `VSCode`

```powershell
winget search vscode
winget install Microsoft.VisualStudioCode
```

Plz refer to [Java in Visual Studio Code
](https://code.visualstudio.com/docs/languages/java)

Check [VSCodium](https://vscodium.com/) if you need a freely-licensed binary distribution

```powershell
winget search vscodium
winget install VSCodium.VSCodium
```

## Web Backend Sample

[spring-petclinic](https://github.com/spring-projects/spring-petclinic)

[quarkus-super-heroes](https://github.com/quarkusio/quarkus-super-heroes)

## Hello World

```java
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

```

```bash
javac -d <classpath_dir> App.java
java -classpath <classpath_dir> App
```

## Strings

### char

- `char` is a Java primitive data type.

- Primitive means that the variable holds the actual value, not a reference.

- Others: boolean, byte, short, int, long. float and double.

- char not Char

- The char data type can store: 1.Any letter, 2.Numbers between 0 to 65535 Inclusive, 3.16-bit Unicode characters including special characters.

- A value for char is enclosed in single quotes "

- There is also an object wrapper called character for a char data type.

Examples:

```java
char ch = 'j';
char uniChar = '\0x004A'; // j in unicode
char[] charArray = { '1', '2', '3', '4', '@'};
```

Methods:

- isLetter()

- isDigit()

- isWhitespace()

- isUpperCase()

- isLowerCase()

- toUpperCase()

- toLowerCase()

- toString() // returns a String object

### String

- String is a provided class in Java

- String not string

- String (and any other class) is a reference to an object

- A value for String is enclosed in double quotes ""

- String can also be stored in arrays, which are also objects

- String is made up of an array of chars

- Because strings are objects, they are immutable (cannot be changed once created)

- String Buffer & String Builder Classes provide mutable string functionality.

Examples:

```java
String s = "j";
String[] strarray = {"One", "Two", "Three", "Four", "Five"};
```

#### Java 8+ methods

- char charAt(int index)

- int compareTo(String anotherString)

- String concat(String str)

- int hashCode()

- int indexOf

- int length()

- String replace and String replaceAll

- String[] split(String regex)

- boolean startsWith(String prefix)

- String substring(int beginIndex)

- String toLowerCase()

- String toUpperCase()

- String trim()

### String Arrays

- Every main method in Java has a string array declaration as part of it's standard formatting:

```java
public static void main(String[] args) {
}
```

- String arrays can contain multiple string elements, also called tokens

- elements and tokens are referred to via their corresponding index number(int)

- Java array indexes start at 0

### ArrayLists

- You need to know about `java.util.ArrayList`

- The size of an ArrayList can be modified, but the size of an array cannot.

- Elements can be added and removed from an ArrayList at any time

Example

```java
ArrayList<String> flexibleList = new ArrayList<String>();
```

## Formatting Strings

### char to String

#### String.valueOf(char)

- To format char as a String, use the String.valueOf(char) method

```java
public static String charToString(char c) {
    return Character.toString(c);
}
```

#### String to char

#### charAt(int)

- to format a character in a String as a char, use the charAt(int) method

```java
public static char StringToChar(String str) {
    return str.charAt(0);
}
```

### String to char array

#### toCharArray() Method

- creates an accessible char[] array

- Java stores strings as primitive char[] arrays internally but they are not accessible

- Useful when working with non delimited string.

```java
String str = "onetwothreefourfive";
```

```java
char chararray[] = str.toCharArray();
```

#### getChars()

- Copy characters from a string or a part of a string into a char[] array

- Arguments are provided for string start, string end, array start, and Destination

```java
mystr.getChars(0, 16, chararr, 0);
```

### Streaming - chars() method

- Creates a Stream from a String object

- Use the mapToObj() and toArray() with chars() to convert a string to array of characters

- helpful when selecting characters in a string based on conditions of each character

```java
Character[] arraychars = str.chars().mapToObj(c -> (char) c).toArray(Character::new);
```

### String Array to String

#### Arrays.toString()

- Simple way to convert an array to a string

```java
String str = Arrays.toString(strarray);
```

#### StringBuilder() and append()

- Provides the option to conditionally add array elements to a string

```java
StringBuilder stringb = new StringBuilder();
for (int i = 0; i < strarray.length; ++i) {
    stringb.append(strarray[i] + ' ');
}
```

#### String Joiner

- Options for adding strings at the beginning and end of the constructed string

- Also defined characters to be used between array elements

- `java.util.StringJoiner` must be imported into your class

```java
StringJoiner stringj = new StringJoiner(";;;", "[", "]");
```

#### Stream and Collector

- Useful if you are provided with a string to consume

- Also can be used to produce an ArrayList

- `java.util.stream.*` and `java.util.Arrays` must be imported into your class

```java
return Stream.of(strarray).collect(Collectors.joining());
```

### String to String Array

#### split() method

- Splits a delimited string into string[] array using a specified character as a delimiter

```java
String[] strarray = str.split(",");
```

#### pattern.split()

- Splits a delimited string into string[] array using a specified pattern as a delimiter

- `java.util.regex` Pattern must be imported into your class

```java
String splitpattern = "\\s\\s";  // 2 spaces
Pattern pattern = Pattern.compile(splitpattern);
```

#### StringTokenizer Class

- Splits a string object into tokens

- Used to split a non-delimited string into an array

- Delimiters can be specified, default = space

- `java.util.StringTokenizer` must be import into your class

```java
import java.util.StringTokenizer;
// ...
StringTokenizer = tokenizer = new StringTokenizer(str);
String[] strarray = new String[tokenizer.countTokens()];
// add tokens to an array
int i = 0;
while (tokenizer.hasMoreTokens()) {
    strarray[i] = tokenizer.nextToken();
    ++i;
}

```

## Numeric

## Records

## Conditional Logic

#### switch(Java 7+)

- Multiple possible execution paths

- Chooses one possibility out of multiple options

- `break` statement is used to terminate statement flow

- `default` statement is optional

Syntax:

```java
switch (expression) {
    case value1 :
        statements..
        break;
    case value2 :
        statements..
    default :
        statements..
}
```

#### switch(Java 14+)

Syntax

```java
switch (expression) {
    case value1 -> boolean;
    case values -> boolean;
    case value3 -> boolean;
    default :-> {
        statements..
    }
}
```

## Arrays

## Collections

## Maps

- `HashMap` 非线程安全，可使用线程安全的 `ConcurrentHashMap` 替代。

## Loops

## Maven

## Classes and Packages

## Methods

## Modules

## Testing
