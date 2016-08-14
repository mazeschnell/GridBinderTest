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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * Main Databinding Class which also is the custom TableModel for JTabel in GridBinderPanel.class
 * 
 * @author matthiasschnell
 * @param <T>
 */
public class GridBinder<T> extends AbstractTableModel {

    // Properties
    private final List _columnNames;
    private final ArrayList<T> _LDataSource;
    private final List _isEditable;

    public GridBinder(ArrayList<T> source) {
        //Init Properties
        _LDataSource = source;
        _columnNames = new ArrayList<>();
        _isEditable = new ArrayList<>();
        //Foreach committed Object
        for (T obj : _LDataSource) {
            List editableObjectList = new ArrayList<>();
            // Foreach field of committed object
            for (Field field : obj.getClass().getFields()) {
                //check whether fieldname is already present in colmnNames
                if (!_columnNames.contains(field.getName())) {
                    _columnNames.add(field.getName());
                }
                //Check whether field is editable
                editableObjectList.add(setIsEditable(field.getName(), obj));
            }
            _isEditable.add(editableObjectList);
        }
        System.out.print("Column Size is: " + _columnNames.size());
    }

    @Override
    public int getColumnCount() {
        return _columnNames.size();
    }

    @Override
    public int getRowCount() {
        return _LDataSource.size();
    }

    @Override
    public String getColumnName(int col) {
        return (String) _columnNames.get(col);
    }

    @Override
    public Object getValueAt(int row, int col) {

        Object value = null;
        T obj = _LDataSource.get(row);
        try {
            //Invoke Method get[Fieldname]
            value = obj.getClass().getDeclaredMethod("get" + _columnNames.get(col)).invoke(obj);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GridBinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    @Override
    public Class getColumnClass(int c) {
        //TODO: Does only work for "well-known" types.
        return getValueAt(0, c).getClass();
    }

    
    @Override
    public boolean isCellEditable(int row, int col) {
        return (Boolean) ((ArrayList) _isEditable.get(row)).get(col);
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {

        T obj = _LDataSource.get(row);
        Method setter;
        try {
            //Invoke Method set[Fieldname]
            Class[] targetClass = new Class[]{getColumnClass(col)};
            //TODO: Does not work for primitiv types
            setter = obj.getClass().getMethod("set" + _columnNames.get(col), targetClass);
            setter.setAccessible(true);
            setter.invoke(obj, value);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GridBinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GridBinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GridBinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GridBinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GridBinder.class.getName()).log(Level.SEVERE, null, ex);
        }

        fireTableCellUpdated(row, col);
    }

    /**
     *  Check if given fieldname has a set[Fieldname] Method in base object
     * 
     * @param fieldname
     * @param baseObject
     * @return whether set[Fieldname] exist in base object
     */
    private static Boolean setIsEditable(String fieldname, Object baseObject) {
        for (Method method : baseObject.getClass().getMethods()) {
            if (method.getName().startsWith("set") && method.getName().toLowerCase().endsWith(fieldname.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
