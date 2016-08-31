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
package de.karlsruhe.hs.gridbinder.gui.components;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author matthiasschnell
 */
public class CustomCheckBoxCellRenderer implements TableCellRenderer {

    JComboBox combo;

    public CustomCheckBoxCellRenderer(JComboBox comboBox) {
        this.combo = comboBox;
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            combo.addItem(comboBox.getItemAt(i));
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ArrayList valueArray = (ArrayList) value;
        
        ((CustomComboboxModel) combo.getModel()).setItemList(valueArray);
        
        if (combo.getSelectedItem() == null ) {
            combo.setSelectedItem(valueArray.get(0));
        }
        return combo;
    }

}
