package zad1;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CountryTable {

    RowListModel model;
    private JTable table;

    public CountryTable(String countriesFileName) {
        model = new RowListModel(countriesFileName);
        table = new JTable();
        table.setModel(model);
        table.setRowHeight(80);
    }

    public JTable create(){
        return table;
    }

}
