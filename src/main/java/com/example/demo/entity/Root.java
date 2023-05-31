package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class Root {
	
	private List<Product> data = new ArrayList<Product>();
    public List<Product> getData() {
        return data;
    }
    public void setData(List<Product> data) {
        this.data = data;
    }
}
