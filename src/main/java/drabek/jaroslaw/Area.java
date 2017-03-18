package drabek.jaroslaw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Area {

    private List<Block> areaOfBlocks;

    public Area(List<Block> areaOfBlocks) {
        this.areaOfBlocks = areaOfBlocks;
    }

    public static Area init(String initString, IBlockNameGenerator blockNameGenerator){

        String[] lines = initString.split("\n");
        int numberOfBlocks = Integer.parseInt(lines[0]);

        ArrayList<Block> blocks = new ArrayList<>();
        IntStream.range(0,numberOfBlocks).forEach(i -> {
            blocks.add(new Block(blockNameGenerator.getABlockName()));
        });
        return new Area(blocks);
    }

    private static class Block {
        private String name;

        public Block(String name) {
            this.name = name;
        }
    }

    public int numberOfBlocks(){
        return areaOfBlocks.size();
    }
}
