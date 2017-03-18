package drabek.jaroslaw;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class BlockNameGeneratorTest {

    @Test(expectedExceptions = {IllegalStateException.class})
    public void throwExceptionIfToManyNamesGenerated(){
        //Given
        IBlockNameGanarator blockNameGenerator = new BlockNameGenerator();
        //When
        IntStream.range(0,17).forEach(i -> blockNameGenerator.getABlockName());
    }

    @Test
    public void do_not_throw_exception_if_16_names_generated(){
        //Given
        IBlockNameGanarator underTest = new BlockNameGenerator();
        //When
        IntStream.range(0,16).forEach(i -> underTest.getABlockName());
    }

    @Test
    public void should_return_A_on_first_execution(){
        //Given
        IBlockNameGanarator underTest = new BlockNameGenerator();
        //When
        Assertions.assertThat(underTest.getABlockName()).isEqualTo("A");
    }

    @Test
    public void should_return_B_on_second_execution(){
        //Given
        IBlockNameGanarator underTest = new BlockNameGenerator();
        //When
        underTest.getABlockName();
        Assertions.assertThat(underTest.getABlockName()).isEqualTo("B");
    }
}