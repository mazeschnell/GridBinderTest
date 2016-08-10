/*
 * The MIT License
 *
 * Copyright 2016 matthiasschnell.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.karlsruhe.hs.gridbinder.databinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author matthiasschnell
 * @param <T>
 */
public class GridBinder<T> extends AbstractTableModel {

    private List _columnNames;
    final private List _data;
    static int _filterlimit=2;
    public Boolean Numbered = true;
    public ArrayList<T> _LDataSource;

    public GridBinder(ArrayList<T> source)  {
        _LDataSource = source;
       
        _columnNames = new ArrayList<>();
        _data = new ArrayList<>();
        
        for(T obj : _LDataSource) {
            
            List valueList = new ArrayList<>();
            for(Field field : obj.getClass().getFields()) {
                if( !_columnNames.contains(field.getName().toString())) {
                    _columnNames.add(field.getName().toString());                    
                } 
               
                try {
                    valueList.add(field.get(obj));
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(GridBinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            _data.add(valueList);
        }
        
        System.out.print("Column Size is: " + _columnNames.size());
        
    }

    @Override
    public int getColumnCount() {
        return _columnNames.size();
    }

    @Override
    public int getRowCount() {
        return _data.size();
    }

    @Override
    public String getColumnName(int col) {
        return (String) _columnNames.get(col);
    }

    @Override
    public Object getValueAt(int row, int col) {
        return ((ArrayList) _data.get(row)).get(col);
    }

    @Override
    public Class getColumnClass(int c) {
        //return getValueAt(0, c).getClass(); 
        return String.class;
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return col >= 2;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        ((ArrayList) _data.get(row)).set(col, value);
        fireTableCellUpdated(row, col);
    }

}
