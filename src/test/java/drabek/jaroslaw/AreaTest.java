package drabek.jaroslaw;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AreaTest {

    @Mock
    IBlockNameGanarator nameGenerator;

    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_that_10_in_first_line_of_init_string_define_10_blocks(){
        //Given
        Mockito.when(nameGenerator.getABlockName()).thenReturn("A");
        Area area = Area.init("10\n" +
                "move 4 over 3\n" +
                "move 3 onto 5\n" +
                "quit", nameGenerator);
        //Then
        assertThat(area.numberOfBlocks()).isEqualTo(10);
    }

    @Test
    public void test_that_5_in_first_line_of_init_string_define_5_blocks(){
        //Given
        Mockito.when(nameGenerator.getABlockName()).thenReturn("C");
        Area area = Area.init("5\n" +
                "move 4 over 3\n" +
                "move 3 onto 5\n" +
                "quit", nameGenerator);
        //Then
        assertThat(area.numberOfBlocks()).isEqualTo(5);
    }


}