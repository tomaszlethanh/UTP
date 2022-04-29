package zad1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RowListModel extends AbstractTableModel {
    private ArrayList<String> columns;
    private ArrayList<Object> data;

    public RowListModel(String countriesFileName){
        String line;
        data = new ArrayList<>();
        columns = new ArrayList<>();
        try {
            FileInputStream stream = new FileInputStream(countriesFileName);
            BufferedReader bread = new BufferedReader(new InputStreamReader(stream));
            StringTokenizer st1 = new StringTokenizer(bread.readLine(), "\t");
            while (st1.hasMoreTokens())
                columns.add(st1.nextToken());
            while ((line = bread.readLine()) != null) {
                StringTokenizer st2 = new StringTokenizer(line, "\t");
                while (st2.hasMoreTokens())
                    data.add(st2.nextToken());
            }

            for (int i=4; i<data.size();i=i+5){
                String filename = (String)data.get(i);
                ImageIcon icon = new ImageIcon("data/flags/" + filename);
                data.set(i, icon);
            }
            bread.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public int getRowCount() {
        return data.size()/getColumnCount();
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column);
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get((rowIndex * getColumnCount())
                + columnIndex);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 2;
    }


}
