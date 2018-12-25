import java.util.HashSet;
import java.util.Set;

public class LogicBox {

    public static void main(String[] args) {
        LogicBoxView view = new LogicBoxView();
        LogicBoxModel model = new LogicBoxModel();
        LogicBoxControl control = new LogicBoxControl(model, view);
        view.linkControl(control);

    }

}

class Box {
    String statement;
    boolean state;
    Set<Box> impliesBoxes;

    Box(String statement, boolean state) {
        this.statement = statement;
        this.state = false;
        this.impliesBoxes = new HashSet<Box>();
    }

    @Override
    public String toString() {
        return this.statement;
    }

    public void validate() {
        this.state = true;
        if (!this.impliesBoxes.isEmpty()) {
            for (Box box : this.impliesBoxes) {
                box.validate();
            }
        }
    }
}