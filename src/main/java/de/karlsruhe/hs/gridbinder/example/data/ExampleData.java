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
package de.karlsruhe.hs.gridbinder.example.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matthiasschnell
 */
public class ExampleData {

    public enum EnumType {
        One, Two, Three
    }

    public int IData;
    public Integer IOData;
    public double DData;
    public char CData;
    public Boolean BData;
    public String SData;
    public List SListData;
    public EnumType EnumData;
    public LocalDateTime DtData;

    public ExampleData(Integer val) {
        this.IData = -43;
        this.IOData = val;
        this.DData = 0.5;
        this.CData = 'A';
        this.BData = false;
        this.SData = "AB";
        this.EnumData = EnumType.Two;
        this.DtData = LocalDateTime.of(2015, 2, 1, 14, 05);
        this.SListData = new ArrayList();
        SListData.add("A");
        SListData.add("B");
    }

    public int getIData() {
        return IData;
    }

    public void setIData(int IData) {
        this.IData = IData;
    }

    public Integer getIOData() {
        return IOData;
    }

    public void setIOData(Integer IOData) {
        this.IOData = IOData;
    }

    public double getDData() {
        return DData;
    }

    public void setDData(double DData) {
        this.DData = DData;
    }

    public char getCData() {
        return CData;
    }

    public void setCData(char CData) {
        this.CData = CData;
    }

    public Boolean getBData() {
        return BData;
    }

    public void setBData(Boolean BData) {
        this.BData = BData;
    }

    public String getSData() {
        return SData;
    }

    public void setSData(String SData) {
        this.SData = SData;
    }

    public List getSListData() {
        return SListData;
    }

    public void setSListData(List SListData) {
        this.SListData = SListData;
    }

    public EnumType getEnumData() {
        return EnumData;
    }

    public void setEnumData(EnumType EnumData) {
        this.EnumData = EnumData;
    }

    public LocalDateTime getDtData() {
        return DtData;
    }

    public void setDtData(LocalDateTime DtData) {
        this.DtData = DtData;
    }

    @Override
    public String toString() {
        return "ExampleData{" + "IData=" + IData + ", IOData=" + IOData + ", DData=" + DData + ", CData=" + CData + ", BData=" + BData + ", SData=" + SData + ", SListData=" + SListData + ", EnumData=" + EnumData + ", DtData=" + DtData + '}';
    }
    
    
}
