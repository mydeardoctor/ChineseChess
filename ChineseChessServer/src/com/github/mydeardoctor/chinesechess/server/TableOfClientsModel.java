package com.github.mydeardoctor.chinesechess.server;

import javax.swing.table.DefaultTableModel;

public class TableOfClientsModel extends DefaultTableModel
{
    public TableOfClientsModel(int rowCount, int columnCount)
    {
        super(rowCount, columnCount);
    }
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
}
