package net.torbenvoltmer.fhdw.es.mcgenerator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by torben on 21.02.16.
 */
public class MainWindowController {

    @FXML
    private TextField pathTextField;


    @FXML
    private TableView<PromEntry> table;

    @FXML
    private TableColumn<PromEntry, Address> address;

    @FXML
    private TableColumn<PromEntry, Address> nextAddress;

    @FXML
    private TableColumn<PromEntry, AluCode> aluCode;

    @FXML
    private TableColumn<PromEntry, AluMux> aluMux;

    @FXML
    private TableColumn<PromEntry, Integer> constant;

    @FXML
    private TableColumn<PromEntry, Boolean> aFromAccu;

    @FXML
    private TableColumn<PromEntry, Boolean> bFromAccu;

    @FXML
    private TableColumn<PromEntry, Boolean> writeAccuA;
    @FXML
    private TableColumn<PromEntry, Boolean> writeAccuB;

    @FXML
    private TableColumn<PromEntry, Boolean> writeRegAInt;

    @FXML
    private TableColumn<PromEntry, Boolean> writeRegBInt;

    @FXML
    private TableColumn<PromEntry, Boolean> busy;


    private ObservableList<Address> addresses;
    private ObservableList<PromEntry> data;

    private ObservableList<AluMux> aluMuxValues;
    private int promSize = 32;

    private FileChooser serFileChooser;

    private File openFile;

    @FXML
    private Stage stage;
    public MainWindowController(){

        addresses = FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();


        for(byte i = 0; i < promSize; i++){
            Address address = new Address(i);
            addresses.add(address);

            data.add(new PromEntry(address, addresses.get(0)));
        }

        aluMuxValues = FXCollections.observableArrayList();

        aluMuxValues.add(AluMux.ALU181);
        aluMuxValues.add(AluMux.MUL);
        aluMuxValues.add(AluMux.CONST);


        serFileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
        serFileChooser.setSelectedExtensionFilter(extFilter);
    }




    @FXML
    private void initialize() {

        table.setEditable(true);
        table.setItems(data);


        address.setEditable(false);
        address.setCellValueFactory(promEntryAddressCellDataFeatures -> promEntryAddressCellDataFeatures.getValue().addressProperty());

        nextAddress.setCellValueFactory(promEntryAddressCellDataFeatures -> promEntryAddressCellDataFeatures.getValue().nextAddressProperty());
        nextAddress.setCellFactory(ComboBoxTableCell.forTableColumn(addresses));
        nextAddress.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setNextAddress(t.getNewValue())
        );


        aluCode.setCellValueFactory(promEntryAluCodeCellDataFeatures -> promEntryAluCodeCellDataFeatures.getValue().aluCodeProperty());
        aluCode.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<AluCode>() {
            @Override
            public String toString(AluCode aluCode) {
                return aluCode.toString();
            }

            @Override
            public AluCode fromString(String s) {
                return AluCode.parseString(s);
            }
        }));
        aluCode.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setAluCode(t.getNewValue())
        );



        aluMux.setCellValueFactory(promEntryIntegerCellDataFeatures -> promEntryIntegerCellDataFeatures.getValue().aluMuxProperty());
        aluMux.setCellFactory(ComboBoxTableCell.forTableColumn(aluMuxValues));
        aluMux.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setAluMux(t.getNewValue())
        );

        constant.setCellValueFactory(promEntryIntegerCellDataFeatures -> promEntryIntegerCellDataFeatures.getValue().constantProperty().asObject());
        constant.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        constant.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setConstant(t.getNewValue())
        );



        aFromAccu.setCellValueFactory(promEntryBooleanCellDataFeatures -> promEntryBooleanCellDataFeatures.getValue().aFromAccuProperty());
        aFromAccu.setCellFactory(CheckBoxTableCell.forTableColumn(aFromAccu));
        aFromAccu.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setaFromAccu(t.getNewValue())
        );



        bFromAccu.setCellValueFactory(promEntryBooleanCellDataFeatures -> promEntryBooleanCellDataFeatures.getValue().bFromAccuProperty());
        bFromAccu.setCellFactory(CheckBoxTableCell.forTableColumn(bFromAccu));
        bFromAccu.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setaFromAccu(t.getNewValue())
        );


        writeAccuA.setCellValueFactory(promEntryBooleanCellDataFeatures -> promEntryBooleanCellDataFeatures.getValue().writeAccuAProperty());
        writeAccuA.setCellFactory(CheckBoxTableCell.forTableColumn(writeAccuA));
        writeAccuA.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setWriteAccuA(t.getNewValue())
        );

        writeAccuB.setCellValueFactory(promEntryBooleanCellDataFeatures -> promEntryBooleanCellDataFeatures.getValue().writeAccuBProperty());
        writeAccuB.setCellFactory(CheckBoxTableCell.forTableColumn(writeAccuB));
        writeAccuB.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setWriteAccuA(t.getNewValue())
        );


        writeRegAInt.setCellValueFactory(promEntryBooleanCellDataFeatures -> promEntryBooleanCellDataFeatures.getValue().writeRegAIntProperty());
        writeRegAInt.setCellFactory(CheckBoxTableCell.forTableColumn(writeRegAInt));
        writeRegAInt.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setWriteRegAInt(t.getNewValue())
        );


        writeRegBInt.setCellValueFactory(promEntryBooleanCellDataFeatures -> promEntryBooleanCellDataFeatures.getValue().writeRegBIntProperty());
        writeRegBInt.setCellFactory(CheckBoxTableCell.forTableColumn(writeRegBInt));
        writeRegBInt.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setWriteRegAInt(t.getNewValue())
        );


        busy.setCellValueFactory(promEntryBooleanCellDataFeatures -> promEntryBooleanCellDataFeatures.getValue().busyProperty());
        busy.setCellFactory(CheckBoxTableCell.forTableColumn(busy));
        busy.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setBusy(t.getNewValue())
        );
    }


    @FXML
    private void handleGenerate(ActionEvent actionEvent) {
       String dataString = generatePromData(data);

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HEX files (*.hex)", "*.hex");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save hex file");
        File file = fileChooser.showSaveDialog(stage);

        if(file != null){
            try {
                Files.write(Paths.get(file.getAbsolutePath() + ".hex"), dataString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    @FXML
    private void handleLoad(ActionEvent actionEvent) {
        serFileChooser.setTitle("Load config file");
        openFile = serFileChooser.showOpenDialog(stage);
        pathTextField.setText(openFile.getAbsolutePath());

        try
        {
            FileInputStream fileIn =new FileInputStream(openFile.getAbsoluteFile());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data.clear();
            data.addAll((ArrayList<PromEntry>) in.readObject());
            in.close();
            fileIn.close();
        }catch(IOException i)        {
            i.printStackTrace();

        }catch(ClassNotFoundException c) {
            c.printStackTrace();

        }


    }

    @FXML
    private void handleSave(ActionEvent actionEvent) {
     saveFile(openFile);
    }


    @FXML
    private void handleAsSave(ActionEvent actionEvent) {
        serFileChooser.setTitle("Save config file");
        openFile = serFileChooser.showSaveDialog(stage);
        pathTextField.setText(openFile.getAbsolutePath());
        saveFile(openFile);
    }


    private void saveFile(File file){
        try
        {
            String path;
            if(file.getAbsolutePath().endsWith(".ser"))
                path = file.getAbsolutePath();
            else
                path = file.getAbsolutePath() + ".ser";


            FileOutputStream fileOut = new FileOutputStream(path);

            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(new ArrayList<PromEntry>(data));
            outStream.close();
            fileOut.close();

        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }


    private String generatePromData (ObservableList<PromEntry> data){

        String promString = "";
        for(PromEntry entry : data){
            Boolean[] rowData = getRowData(entry);

            String rowString = "";
            for(int i = 31; i > 0; i-=4){

                int val = 0;

                if(rowData[i-3])
                    val += 1;
                if(rowData[i-2])
                    val += 2;
                if(rowData[i-1])
                    val += 4;
                if(rowData[i])
                    val += 8;
                rowString += Integer.toHexString(val).toUpperCase();
            }
            rowString += "\n";
            promString += rowString;
        }
        System.out.println(promString);
        return promString;
    }


    private Boolean[] getRowData(PromEntry entry){

        Boolean[] row = new Boolean[32];

        row[0] = (entry.getNextAddress().getValue() & 0b00001) == 0b00001;
        row[1] = (entry.getNextAddress().getValue() & 0b00010) == 0b00010;
        row[2] = (entry.getNextAddress().getValue() & 0b00100) == 0b00100;
        row[3] = (entry.getNextAddress().getValue() & 0b01000) == 0b01000;
        row[4] = (entry.getNextAddress().getValue() & 0b10000) == 0b10000;

        row[5] = entry.getAluCode().getS0();
        row[6] = entry.getAluCode().getS1();
        row[7] = entry.getAluCode().getS2();
        row[8] = entry.getAluCode().getS3();
        row[9] = entry.getAluCode().getM();

        row[10] = (entry.getAluMux().getValue() & 0b001) == 0b001;
        row[11] = (entry.getAluMux().getValue() & 0b010) == 0b010;
        row[12] = (entry.getAluMux().getValue() & 0b100) == 0b100;

        row[13] = false;
        row[14] = false;
        row[15] = false;

        row[16] = (entry.getConstant() & 0b0001) == 0b0001;
        row[17] = (entry.getConstant() & 0b0010) == 0b0010;
        row[18] = (entry.getConstant() & 0b0100) == 0b0100;
        row[19] = (entry.getConstant() & 0b1000) == 0b1000;

        row[20] = false;
        row[21] = false;
        row[22] = false;
        row[23] = false;
        row[24] = false;

        row[25] = entry.getbFromAccu();
        row[26] = entry.getaFromAccu();
        row[27] = entry.getWriteAccuB();
        row[28] = entry.getWriteAccuA();
        row[29] = entry.getWriteRegBInt();
        row[30] = entry.getWriteRegAInt();
        row[31] = entry.getBusy();

        return row;
    }


}
