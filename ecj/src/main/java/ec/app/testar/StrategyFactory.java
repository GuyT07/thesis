package ec.app.testar;

import ec.app.testar.nodes.And;
import ec.app.testar.nodes.ClickAction;
import ec.app.testar.nodes.DragAction;
import ec.app.testar.nodes.DragActionsAvailable;
import ec.app.testar.nodes.Equals;
import ec.app.testar.nodes.EqualsType;
import ec.app.testar.nodes.GreaterThan;
import ec.app.testar.nodes.HitKeyAction;
import ec.app.testar.nodes.IfThenElse;
import ec.app.testar.nodes.LeftClicksAvailable;
import ec.app.testar.nodes.Not;
import ec.app.testar.nodes.NumOfDragActions;
import ec.app.testar.nodes.NumOfLeftClicks;
import ec.app.testar.nodes.NumOfPreviousActions;
import ec.app.testar.nodes.NumOfTypeActions;
import ec.app.testar.nodes.NumOfUnexecutedDragActions;
import ec.app.testar.nodes.NumOfUnexecutedLeftClicks;
import ec.app.testar.nodes.NumOfUnexecutedTypeActions;
import ec.app.testar.nodes.NumberOfActions;
import ec.app.testar.nodes.NumberOfActionsOfType;
import ec.app.testar.nodes.Or;
import ec.app.testar.nodes.PreviousAction;
import ec.app.testar.nodes.RandomAction;
import ec.app.testar.nodes.RandomActionOfType;
import ec.app.testar.nodes.RandomActionOfTypeOtherThan;
import ec.app.testar.nodes.RandomLeastExecutedAction;
import ec.app.testar.nodes.RandomMostExecutedAction;
import ec.app.testar.nodes.RandomNumber;
import ec.app.testar.nodes.RandomUnexecutedAction;
import ec.app.testar.nodes.RandomUnexecutedActionOfType;
import ec.app.testar.nodes.StateHasNotChanged;
import ec.app.testar.nodes.TypeAction;
import ec.app.testar.nodes.TypeActionsAvailable;
import ec.app.testar.nodes.TypeOfAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class StrategyFactory {

    private Queue<AvailableReturnTypes> queue = new LinkedList<>();

    StrategyNode makeStrategy(final String strategy) {
        makeQueue(strategy);
        return getNode();
    }

    private StrategyNode getNode() {
        final AvailableReturnTypes f = queue.poll();
        final ArrayList<StrategyNode> children = new ArrayList<>();
        final StrategyNode result;

        assert f != null;
        switch (f) {
            case AND:
            case A:
                children.add(getNode());
                children.add(getNode());
                result = new And();
                break;

            case CLICKACTION:
            case B:
                result = new ClickAction();
                break;

            case DRAGACTION:
            case C:
                result = new DragAction();
                break;

            case DRAGACTIONSAVAILABLE:
            case D:
                result = new DragActionsAvailable();
                break;

            case EQUALS:
            case E:
                children.add(getNode());
                children.add(getNode());
                result = new Equals();
                break;

            case EQUALSTYPE:
            case d8:
                children.add(getNode());
                children.add(getNode());
                result = new EqualsType();
                break;

            case GREATERTHAN:
            case F:
                children.add(getNode());
                children.add(getNode());
                result = new GreaterThan();
                break;

            case HITKEYACTION:
            case d6:
                result = new HitKeyAction();
                break;

            case IFTHENELSE:
            case G:
                children.add(getNode());
                children.add(getNode());
                children.add(getNode());
                result = new IfThenElse();
                break;

            case LEFTCLICKSAVAILABLE:
            case H:
                result = new LeftClicksAvailable();
                break;

            case NOT:
            case I:
                children.add(getNode());
                result = new Not();
                break;

            case NUMBEROFACTIONS:
            case J:
                result = new NumberOfActions();
                break;

            case NUMBEROFACTIONSOFTYPE:
            case K:
                children.add(getNode());
                result = new NumberOfActionsOfType();
                break;

            case NUMBEROFDRAGACTIONS:
            case L:
                result = new NumOfDragActions();
                break;

            case NUMBEROFLEFTCLICKS:
            case M:
                result = new NumOfLeftClicks();
                break;

            case NUMBEROFPREVIOUSACTIONS:
            case N:
                result = new NumOfPreviousActions();
                break;

            case NUMBEROFTYPEACTIONS:
            case O:
                result = new NumOfTypeActions();
                break;

            case NUMBEROFUNEXECUTEDDRAGACTIONS:
            case P:
                result = new NumOfUnexecutedDragActions();
                break;

            case NUMBEROFUNEXECUTEDLEFTCLICKS:
            case Q:
                result = new NumOfUnexecutedLeftClicks();
                break;

            case NUMBEROFUNEXECUTEDTYPEACTIONS:
            case R:
                result = new NumOfUnexecutedTypeActions();
                break;

            case OR:
            case S:
                children.add(getNode());
                children.add(getNode());
                result = new Or();
                break;

            case PREVIOUSACTION:
            case T:
                result = new PreviousAction();
                break;

            case RANDOMACTION:
            case U:
                result = new RandomAction();
                break;

            case RANDOMACTIONOFTYPE:
            case V:
                children.add(getNode());
                result = new RandomActionOfType();
                break;

            case RANDOMACTIONOFTYPEOTHERTHAN:
            case W:
                children.add(getNode());
                result = new RandomActionOfTypeOtherThan();
                break;

            case RANDOMLEASTEXECUTEDACTION:
            case X:
                result = new RandomLeastExecutedAction();
                break;

            case RANDOMMOSTEXECUTEDACTION:
            case Y:
                result = new RandomMostExecutedAction();
                break;

            case RANDOMNUMBER:
            case Z:
                result = new RandomNumber();
                break;

            case RANDOMUNEXECUTEDACTION:
            case d1:
                result = new RandomUnexecutedAction();
                break;

            case RANDOMUNEXECUTEDACTIONOFTYPE:
            case d2:
                children.add(getNode());
                result = new RandomUnexecutedActionOfType();
                break;

            case STATEHASNOTCHANGED:
            case d7:
                result = new StateHasNotChanged();
                break;

            case TYPEACTION:
            case d3:
                result = new TypeAction();
                break;

            case TYPEACTIONSAVAILABLE:
            case d4:
                result = new TypeActionsAvailable();
                break;

            case TYPEOFACTIONOF:
            case d5:
                children.add(getNode());
                result = new TypeOfAction();
                break;
            default:
                throw new IllegalArgumentException("Unknown node");
        }

        result.setChildren(children);

        return result;
    }

    private void makeQueue(final String strategy) {
        if (strategy.contains(":")) {
            Arrays.stream(strategy.replace(" ", "")
                    .replace("(", "")
                    .replace(")", "")
                    .split(":"))
                    .forEach(s -> queue.add(AvailableReturnTypes.valueOf(s.replace("-", "").toUpperCase())));
        } else {
            char[] list = strategy.toCharArray();
            for (char c : list) {
                String s;
                if (Character.isDigit(c)) {
                    s = "d" + c;
                } else {
                    s = Character.toString(c);
                }

                queue.add(AvailableReturnTypes.valueOf(s));
            }
        }
    }
}
