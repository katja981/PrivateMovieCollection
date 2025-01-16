package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ChooseCategoriesController {

    @FXML
    private Button btnSaveCategories;
    @FXML
    private Button btnCancelCategories;

    @FXML
    private CheckBox actionCheckBox, adventureCheckBox, animationCheckBox, biographyCheckBox, comedyCheckBox,
            crimeCheckBox, documentaryCheckBox, dramaCheckBox, familyCheckBox, fantasyCheckBox,
            historyCheckBox, horrorCheckBox, musicalCheckBox, mysteryCheckBox, romanceCheckBox,
            scifiCheckBox, sportCheckBox, thrillerCheckBox, warCheckBox, westernCheckBox;

    @FXML
    private List<CheckBox> categoryCheckBoxes = new ArrayList<>();

    private List<String> selectedCategories = new ArrayList<>();

    public void setSelectedCategories(List<String> categories) {
        this.selectedCategories = categories;

        if(categoryCheckBoxes != null && !categoryCheckBoxes.isEmpty()) {
            for(CheckBox checkBox : categoryCheckBoxes) {
                checkBox.setSelected(categories.contains(checkBox.getText()));
            }
        }
    }
    @FXML
    public void initialize() {
        categoryCheckBoxes.addAll(
                List.of(actionCheckBox, adventureCheckBox, animationCheckBox,biographyCheckBox,comedyCheckBox,
                        crimeCheckBox,documentaryCheckBox,dramaCheckBox,familyCheckBox,fantasyCheckBox,historyCheckBox,horrorCheckBox,
                        musicalCheckBox,mysteryCheckBox,romanceCheckBox,scifiCheckBox,sportCheckBox,thrillerCheckBox,warCheckBox,westernCheckBox)
        );
        if(selectedCategories != null) {
            for(CheckBox checkBox : categoryCheckBoxes) {
                if(selectedCategories.contains(checkBox.getText())) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
            }

        }
    }

    @FXML
    private void onSaveCategoriesClicked() {
        selectedCategories.clear();
        for (CheckBox checkBox : categoryCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedCategories.add(checkBox.getText());
            }
        }
        Stage stage = (Stage) btnSaveCategories.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancelCategoriesClicked() {
        Stage stage = (Stage) btnCancelCategories.getScene().getWindow();
        stage.close();
    }

    public List<String> getSelectedCategories() {
        return selectedCategories;
    }
}
