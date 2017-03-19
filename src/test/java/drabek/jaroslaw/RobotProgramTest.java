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
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.numberOfBlocks()).isEqualTo(10);
    }

    @Test
    public void test_that_5_in_first_line_of_init_string_define_5_blocks(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("C");
        RobotProgram robotProgram = RobotProgram.init("5\n" +
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.numberOfBlocks()).isEqualTo(5);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void test_that_command_should_start_with_move_otherwise_exception(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("1","2");
        RobotProgram robotProgram = RobotProgram.init("2\n" +
                "ble 1 over 2\n" +
                "quit", blockNameGenerator);
    }

    @Test
    public void test_that_1_over_2_command_line_proper_OverCommand_being_produced(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("1","2");
        RobotProgram robotProgram = RobotProgram.init("2\n" +
                "move 1 over 2\n" +
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.command(0)).isEqualTo(new OverCommand("1","2"));
    }

    @Test
    public void test_that_2_over_1_command_line_make_OverCommand_being_produced(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("1","2");
        RobotProgram robotProgram = RobotProgram.init("2\n" +
                "move 2 over 1\n" +
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.command(0)).isEqualTo(new OverCommand("2","1"));
    }

    @Test
    public void test_that_2_over_program_lines_make_2_OverCommand_being_produced(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("1","2","3","4");
        RobotProgram robotProgram = RobotProgram.init("4\n" +
                "move 2 over 1\n" +
                "move 3 over 4\n" +
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.command(0)).isEqualTo(new OverCommand("2","1"));
        assertThat(robotProgram.command(1)).isEqualTo(new OverCommand("3","4"));
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void test_that_lack_of_number_in_first_line_will_cause_IllegalStateException(){
        //Given
        Mockito.when(blockNameGenerator.getABlockName()).thenReturn("1","2","3","4");
        RobotProgram robotProgram = RobotProgram.init(
                "move 2 over 1\n" +
                "quit", blockNameGenerator);
        //Then
        assertThat(robotProgram.command(0)).isEqualTo(new OverCommand("2","1"));
        assertThat(robotProgram.command(1)).isEqualTo(new OverCommand("3","4"));
    }




}