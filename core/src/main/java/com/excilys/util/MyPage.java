package com.excilys.util;

import java.util.List;

public class MyPage<T> {

    private int nbElementPage;
    private long nbElementTotal;
    private int nbCurrentPage;
    private int nbPageTotal;
    private List<T> elements;

    private MyPage(Builder<T> builder) {
        nbElementPage = builder.myPage.nbElementPage;
        nbElementTotal = builder.myPage.nbElementTotal;
        elements = builder.myPage.elements;
    }

    private MyPage() {
        nbElementPage = 10;
        nbElementTotal = -1;
        nbCurrentPage = 1;
    }

    public static class Builder<T> {
        private MyPage<T> myPage = new MyPage();

        public Builder<T> nbPage(int nbPage) {
            myPage.nbCurrentPage = nbPage;
            return this;
        }

        public Builder<T> nbElementPage(int nbElementPage) {
            myPage.nbElementPage = nbElementPage;
            return this;
        }

        public Builder<T> nbElementTotal(long nbElementTotal) {
            myPage.nbElementTotal = nbElementTotal;
            return this;
        }

        public Builder<T> elements(List elements) {
            myPage.elements = elements;
            return this;
        }

        public MyPage<T> build() {
            return new MyPage(this);
        }
    }

    public int getFirsRow() {
        return nbElementPage * (nbCurrentPage - 1);
    }

    public int getNbElementPage() {
        return nbElementPage;
    }

    public void setNbElementPage(int nbElementPage) {
        this.nbElementPage = nbElementPage;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public long getNbElementTotal() {
        return nbElementTotal;
    }

    public void setNbElementTotal(int nbElementTotal) {
        this.nbElementTotal = nbElementTotal;
    }

    public int getNbCurrentPage() {
        return nbCurrentPage;
    }

    public void setNbCurrentPage(int nbPage) {
        this.nbCurrentPage = nbPage;
    }

    public int getNbPageTotal() {
        return nbPageTotal;
    }

    public void setNbPageTotal(int nbPageTotal) {
        this.nbPageTotal = nbPageTotal;
    }

}
