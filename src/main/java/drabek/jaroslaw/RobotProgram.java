package drabek.jaroslaw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.*;

class RobotProgram {

    private List<Block> blocks;
    private List<Command> commands;

    private RobotProgram(List<Block> blocks, List<Command> commands) {
        this.blocks = blocks;
        this.commands = commands;
    }

    static RobotProgram init(String programString, IBlockNameGenerator blockNameGenerator){
        try {
            String[] programLines = programString.split("\n");
            String firstLineOfProgram = programLines[0];
            List<Block> blocks = generateBlocks(firstLineOfProgram, blockNameGenerator);
            List<Command> commands = parseCommands(programLines);
            return new RobotProgram(blocks, commands);
        } catch(NumberFormatException ex){
            throw new IllegalStateException("Wrong format of input program");
        }
    }

    private static List<Command> parseCommands(String[] programLines) {
        return IntStream.range(0, programLines.length)
                .filter(index -> !isFirstOrLastProgramLines(programLines, index))
                .mapToObj(index -> Command.createCommand(programLines[index]))
                .collect(Collectors.toList());
    }

    private static boolean isFirstOrLastProgramLines(String[] programLines, int index) {
        return index == 0 || index == programLines.length - 1;
    }

    private static ArrayList<Block> generateBlocks(String firstLineOfProgram, IBlockNameGenerator blockNameGenerator) {
        int numberOfBlocks = parseInt(firstLineOfProgram);
        ArrayList<Block> blocks = new ArrayList<>();
        IntStream.range(0,numberOfBlocks).forEach(i -> {
            blocks.add(new Block(blockNameGenerator.getABlockName()));
        });
        return blocks;
    }

    int numberOfBlocks(){
        return blocks.size();
    }

    Command command(int index) {
        return commands.get(index);
    }
}
