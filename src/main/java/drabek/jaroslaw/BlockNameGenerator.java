package drabek.jaroslaw;

public class BlockNameGenerator implements IBlockNameGanarator {
    private int counter = 0;
    private static final String BLOCKNAMESSTRING = "ABCDEFGHIJKLMNOP";

        @Override
    public String getABlockName() {
        String blockName = String.valueOf(BLOCKNAMESSTRING.charAt(counter));
        counter++;
        return blockName;
    }

}
