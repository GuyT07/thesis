package ec.app.testar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class StrategyFactory {
    private Queue<Function> queue = new LinkedList<Function>();

    public StrategyNode makeStrategy(String strategy) {
        makeQueue(strategy);
        return getNode();
    }

    private StrategyNode getNode() {
        Function f = queue.poll();
        ArrayList<StrategyNode> children = new ArrayList<>();
        StrategyNode result;

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
                result = null;
                break;
        }
        result.setChildren(children);

        return result;

    }

    private void makeQueue(String strategy) {
        if (strategy.contains(":")) {
            strategy = strategy.replace(" ", "");
            strategy = strategy.replace("(", "");
            strategy = strategy.replace(")", "");
            String[] list = strategy.split(":");
            for (String s : list) {
                s = s.replace("-", "");
                s = s.toUpperCase();

                queue.add(Function.valueOf(s));
            }
        } else {
            char[] list = strategy.toCharArray();
            for (char c : list) {
                String s;
                if (Character.isDigit(c)) {
                    s = "d" + c;
                } else {
                    s = Character.toString(c);
                }

                queue.add(Function.valueOf(s));
            }
        }
    }

    private enum Function {
        AND, CLICKACTION, DRAGACTION, DRAGACTIONSAVAILABLE, EQUALS, EQUALSTYPE, ESCAPE, GREATERTHAN,
        HITKEYACTION, IFTHENELSE, LEFTCLICKSAVAILABLE, NOT, NUMBEROFACTIONS, NUMBEROFACTIONSOFTYPE,
        NUMBEROFDRAGACTIONS, NUMBEROFLEFTCLICKS, NUMBEROFPREVIOUSACTIONS, NUMBEROFTYPEACTIONS,
        NUMBEROFUNEXECUTEDDRAGACTIONS, NUMBEROFUNEXECUTEDLEFTCLICKS, NUMBEROFUNEXECUTEDTYPEACTIONS, OR,
        PREVIOUSACTION, RANDOMACTION, RANDOMACTIONOFTYPE, RANDOMACTIONOFTYPEOTHERTHAN, RANDOMLEASTEXECUTEDACTION,
        RANDOMMOSTEXECUTEDACTION, RANDOMNUMBER, RANDOMUNEXECUTEDACTION, RANDOMUNEXECUTEDACTIONOFTYPE,
        STATEHASNOTCHANGED, TYPEACTION, TYPEACTIONSAVAILABLE, TYPEOFACTIONOF, A, B, C, D, E, F, G, H,
        I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, d1, d2, d3, d4, d5, d6, d7, d8
    }
}
