package com.example.demo.dao;

import com.example.demo.model.Products;
import com.example.demo.util.ReadJson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsDao {

	public String getSampleMessage() throws NullPointerException {
		return "Sample service message";
	}

	public List<Products> getProductListing() throws FileNotFoundException, IOException {
		ReadJson read = new ReadJson();
		String fileName = "static/Products.json";
		StringBuilder resultStringBuilder = new StringBuilder();
		Resource resource = new ClassPathResource(fileName);
		try (BufferedReader br
					 = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line);
			}
		}
		return read.readjsonFile(resultStringBuilder.toString());
	}

	public List<Products> getProducts(List<Products> products, Products productFilter){
		List<Products> finalproducts = new ArrayList<Products>();

		for(Products p : products){
			boolean add = false;

			if(productFilter.getProductId() != 0)
				if(p.getProductId() == productFilter.getProductId()){
					add = true;
				}

			if(productFilter.getProductName() != null)
				if(p.getProductName().toUpperCase().contains(productFilter.getProductName().toUpperCase())){
					add = true;
				}

			if(productFilter.getDescription() != null)
				if(p.getDescription().toUpperCase().contains(productFilter.getDescription().toUpperCase())){
					add = true;
				}

			if(productFilter.getProductCode() != null)
				if(p.getProductCode().toUpperCase().contains(productFilter.getProductCode().toUpperCase())){
					add = true;
				}

			if(productFilter.getReleaseDate() != null)
				if(p.getReleaseDate().toUpperCase().contains(productFilter.getReleaseDate().toUpperCase())){
					add = true;
				}

			if(productFilter.getStarRating() != 0)
				if(p.getStarRating() == productFilter.getStarRating()){
					add = true;
				}

			if(productFilter.getPrice() != 0)
				if(p.getPrice() == productFilter.getPrice()){
					add = true;
				}

			if(add){
				finalproducts.add(p);
			}

		}

		return finalproducts;
	}
}
