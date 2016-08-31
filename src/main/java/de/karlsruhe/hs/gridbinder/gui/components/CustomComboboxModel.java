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

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 *
 * @author matthiasschnell
 * @param <T>
 */
public class CustomComboboxModel<T> extends AbstractListModel<T> implements MutableComboBoxModel<T> {

    private ArrayList<T> _items;
    private T selection;

    public CustomComboboxModel(ArrayList<T> items) {
        if (items != null) {
            this._items = items;
        } else {
            this._items = new ArrayList<>();
        }
        selection = null;
    }
    
    public void setItemList(ArrayList<T> items) {
        this._items = items;
    }

    @Override
    public int getSize() {
        return _items.size();
    }

    @Override
    public T getElementAt(int index) {
        return _items.get(index);
    }

    @Override
    public void addElement(T item) {
        _items.add(item);
    }

    @Override
    public void removeElement(Object obj) {
        _items.remove(obj);
    }

    @Override
    public void insertElementAt(T item, int index) {
        _items.add(index, item);
    }

    @Override
    public void removeElementAt(int index) {
        _items.remove(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (T) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
