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
package de.karlsruhe.hs.gridbinder.gui;

import de.karlsruhe.hs.gridbinder.databinding.GridBinder;
import de.karlsruhe.hs.gridbinder.databinding.TypeDictionary;
import de.karlsruhe.hs.gridbinder.example.data.ExampleData;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author matthiasschnell
 */
public class GridBinderPanel extends JPanel {
    private final ArrayList _data;
    //TODO: Implement Filter/Search

    public GridBinderPanel() {
        super(new GridLayout(1, 0));
        
        // Generate some data
	_data = new ArrayList<>();
			
	// Add some dummy data
	_data.add(new ExampleData(8));
	_data.add(new ExampleData(null));
	_data.add(new ExampleData(13));

        // Create JTable with Custom Tablemodel "GridBinder"
        final JTable table = new JTable(new GridBinder(_data));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        //FIXME: Does not include array values in Combobox
        table.setDefaultEditor(ArrayList.class, new DefaultCellEditor(new JComboBox()));
        table.setDefaultEditor(Character.class, new DefaultCellEditor(new JTextField()));
      

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

}
