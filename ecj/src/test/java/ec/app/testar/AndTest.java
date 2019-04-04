package ec.app.testar;

import ec.EvolutionState;
import ec.Problem;
import ec.app.testar.nodes.And;
import ec.app.testar.nodes.DoubleData;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

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
    public void getSimplifiedNode() {
    }
}
