package drabek.jaroslaw;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotProgramTest {

    @Mock
    IBlockNameGenerator blockNameGenerator;

    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_that_10_in_first_line_of_init_string_define_10_blocks(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("A");
        RobotProgram robotProgram = RobotProgram.init("10\n" +
                "move 4 over 3\n" +
                "move 3 onto 5\n" +
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.numberOfBlocks()).isEqualTo(10);
    }

    @Test
    public void test_that_5_in_first_line_of_init_string_define_5_blocks(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("C");
        RobotProgram robotProgram = RobotProgram.init("5\n" +
                "move 4 over 3\n" +
                "move 3 onto 5\n" +
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.numberOfBlocks()).isEqualTo(5);
    }

    @Test
    public void test_that_one_over_command_line_cause_over_command_produced(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("1","2");
        RobotProgram robotProgram = RobotProgram.init("4\n" +
                "move 1 over 2\n" +
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.command(0)).isEqualTo(new OverCommand("1","2"));
    }




}