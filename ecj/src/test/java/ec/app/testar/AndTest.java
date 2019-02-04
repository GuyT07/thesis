package ec.app.testar;

import ec.EvolutionState;
import ec.Problem;
import ec.app.testar.exceptions.ShouldHaveAtLeastTwoChildrenException;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.Assert.assertEquals;

public class AndTest {

    @Spy
    private And subject = new And();

    @Before
    public void setup() {

    }

    @Test
    public void constructorTest() {
        assertEquals(2, subject.expectedChildren);
        assertEquals("and:", subject.name);
        assertEquals('A', subject.letter);
    }


    @Test
    public void eval_whenLengthOfChildrenIsZero_gracefullyHandleException() {
        final EvolutionState state = new EvolutionState();
        final int thread = 0;
        final GPData input = new DoubleData();
        final ADFStack stack = new ADFStack();
        final GPIndividual individual = new GPIndividual();
        final Problem problem = new TestarProblem();

        subject.children = new GPNode[]{new And()};

        subject.eval(state, thread, input, stack, individual, problem);
    }

    @Test
    public void eval_whenChildOneEqualsChildTwo_setDoubleDateXTo0() {
        final EvolutionState state = new EvolutionState();
        final int thread = 0;
        final DoubleData input = Mockito.spy(new DoubleData());
        final ADFStack stack = new ADFStack();
        final GPIndividual individual = new GPIndividual();
        final Problem problem = new TestarProblem();

        final StrategyNode and1 = new And();
        and1.setChild(0, new And());

        final StrategyNode and2 = new And();
        and2.setChild(1, new And());

        final ArrayList<StrategyNode> children = new ArrayList<>();
        children.add(and1);
        children.add(and2);

        subject.setChildren(children);

        subject.eval(state, thread, input, stack, individual, problem);
        assertEquals(0, input.x);
    }

    @Test
    public void getSimplifiedNode() {
    }
}
