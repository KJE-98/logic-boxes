import java.util.HashSet;
import java.util.Iterator;

public class LogicBoxControl {
    LogicBoxModel model;
    LogicBoxView view;

    LogicBoxControl(LogicBoxModel model, LogicBoxView view) {
        this.model = model;
        this.view = view;
    }

    void addBox(Box box) {
        this.model.addBox(box);
    }

    void removeBox(Box box) {
        this.model.removeBox(box);
    }

    void update() {
        this.view.showBoxes(this.model.boxes);
    }

    void addIfThen(Box ifBox, Box thenBox) {
        ifBox.impliesBoxes.add(thenBox);
        System.out.println(ifBox + " now implies " + thenBox);
    }

    void addIfAndOnlyIf(Box ifBox, Box thenBox) {
        ifBox.impliesBoxes.add(thenBox);
        thenBox.impliesBoxes.add(ifBox);
    }

    void updateTruth(HashSet<Box> trueBoxes) {

        for (Iterator<Box> iterator = trueBoxes.iterator(); iterator
                .hasNext();) {
            Box box = iterator.next();

        }
    }

}
