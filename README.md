# Stop
Stop is an open source language for defining software systems.

## Building from source
In order to build Stop from source you will need to check out this repository and [install antlr4 on your workstation](https://github.com/antlr/antlr4/blob/master/doc/getting-started.md).

Once antlr4 is installed run the following command to generate the Java lexer and parser:
  
`antlr4 -o ./src/main/java/org/stop_lang/parser -package org.stop_lang.parser -listener -no-visitor Stop.g4`

## Examples
There are a few examples in the examples directory.  Hello world defines the simplest possible Stop file.  Kitchen sink demonstrates all of the language features.

## Editor
The [stop-editor](https://github.com/pocketlabs/stop-editor) project is a real-time web based editor for the Stop language. Try it now at [https://editor.stop-lang.org](https://editor.stop-lang.org).

## License
Stop is licensed under the [MIT License](/LICENSE).  Feel free to use it however you wish.
