import javax.swing.JRadioButton;

public class SearchByButton extends JRadioButton {

    public SearchByButton(String name, Runnable onSelection) {
        super(name);
        addActionListener((e) -> onSelection.run());
    }
}
