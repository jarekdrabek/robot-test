package drabek.jaroslaw;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

abstract class Command {
    static Command createCommand(String programLine){
        if(!programLine.startsWith("move"))
            throw new IllegalStateException(String.format("Command should start with 'move' statement [command: %s]", programLine));

        List<String> commandWordsList = Arrays.stream(programLine.split(" "))
                .filter(string -> string.length() > 0)
                .collect(Collectors.toList());

        String fromElement = commandWordsList.get(1);
        String operation = commandWordsList.get(2);
        String toElement = commandWordsList.get(3);

        if(operation.equals("over")){
            return new OverCommand(fromElement,toElement);
        } else {
            throw new IllegalStateException("Unrecognized exception");
        }
    }
}


