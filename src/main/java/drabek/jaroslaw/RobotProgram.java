package drabek.jaroslaw;

import java.util.ArrayList;
import java.util.Arrays;
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
        String[] programLines = programString.split("\n");
        List<Block> blocks = generateBlocks(blockNameGenerator, programLines);
        List<Command> commands = parseCommands(programLines);

        return new RobotProgram(blocks, commands);
    }

    private static List<Command> parseCommands(String[] programLines) {
        List<Command> collect = IntStream.range(0, programLines.length)
                .filter(index -> index != 0 && index != programLines.length - 1)
                .mapToObj(index -> Command.createCommand(programLines[index]))
                .collect(Collectors.toList());
        return collect;
    }

    private static ArrayList<Block> generateBlocks(IBlockNameGenerator blockNameGenerator, String[] line) {
        String firstLineOfProgram = line[0];
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
