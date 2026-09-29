package translation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.Arrays;


// TODO Task D: Update the GUI for the program to align with UI shown in the README example.
//            Currently, the program only uses the CanadaTranslator and the user has
//            to manually enter the language code they want to use for the translation.
//            See the examples package for some code snippets that may be useful when updating
//            the GUI.
public class GUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //use Jlist
            JPanel countryPanel = new JPanel();
            JList<String> countryList = new JList<>();
            JLabel resultLabel = new JLabel("\t\t\t\t\t\t\t");

            //use combo box
            JPanel languagePanel = new JPanel();
            languagePanel.add(new JLabel("Language:"));

            Translator translator = new CanadaTranslator();

            // create combobox, add country codes into it, and add it to our panel
            JComboBox<String> languageComboBox = new JComboBox<>();
            for(String countryCode : translator.getLanguageCodes()) {
                languageComboBox.addItem(countryCode);
            }
            languagePanel.add(languageComboBox);

            // add listener for when an item is selected.
            languageComboBox.addItemListener(new ItemListener() {

                /**
                 * Invoked when an item has been selected or deselected by the user.
                 * The code written for this method performs the operations
                 * that need to occur when an item is selected (or deselected).
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void itemStateChanged(ItemEvent e) {

                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String country = languageComboBox.getSelectedItem().toString();
                        String language = countryList.getModel().getElementAt(countryList.getSelectedIndices()[0]);
                        Translator translator = new CanadaTranslator();
                        String result = translator.translate(country, language);
                        if (result == null) {
                            result = "no translation found!";
                        }
                        resultLabel.setText(result);
                    }
                }


            });
            JPanel translationPanel = new JPanel();
            JLabel resultLabelText = new JLabel("Translation:");
            translationPanel.add(resultLabelText);

            translationPanel.add(resultLabel);


            // place the JList in a scroll pane so that it is scrollable in the UI
            JScrollPane scrollPane = new JScrollPane(countryList);
            countryPanel.add(scrollPane);

            countryList.addListSelectionListener(new ListSelectionListener() {

                /**
                 * Called whenever the value of the selection changes.
                 *
                 * @param e the event that characterizes the change.
                 */
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    String country = languageComboBox.getSelectedItem().toString();
                    String language = countryList.getModel().getElementAt(countryList.getSelectedIndices()[0]);
                    Translator translator = new CanadaTranslator();
                    String result = translator.translate(country, language);
                    if (result == null) {
                        result = "no translation found!";
                    }
                    resultLabel.setText(result);
                }
            });

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(languagePanel);
            mainPanel.add(translationPanel);
            mainPanel.add(countryPanel);


            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
