import java.util.HashSet;
import java.util.Set;

public class LogicBoxModel {
    LogicBoxControl control;
    Set<Box> boxes;

    LogicBoxModel() {
        this.boxes = new HashSet<Box>();
    }

    void addBox(Box box) {
        this.boxes.add(box);
    }

    void removeBox(Box box) {
        this.boxes.remove(box);
    }
}
