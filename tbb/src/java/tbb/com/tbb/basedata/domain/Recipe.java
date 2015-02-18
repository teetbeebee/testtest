package com.tbb.basedata.domain;

public class Recipe {
	private java.lang.String recipe_id;
	private java.lang.String restraunt_id;
	private java.lang.String recipe_name;
	private java.lang.String recipe_price;
	private java.lang.String recipe_pic;

	public java.lang.String getRecipe_id(){
		return recipe_id;
	}

	public void setRecipe_id(java.lang.String recipe_id){
		this.recipe_id = recipe_id;
	}
	public java.lang.String getRestraunt_id(){
		return restraunt_id;
	}

	public void setRestraunt_id(java.lang.String restraunt_id){
		this.restraunt_id = restraunt_id;
	}
	public java.lang.String getRecipe_name(){
		return recipe_name;
	}

	public void setRecipe_name(java.lang.String recipe_name){
		this.recipe_name = recipe_name;
	}
	public java.lang.String getRecipe_price(){
		return recipe_price;
	}

	public void setRecipe_price(java.lang.String recipe_price){
		this.recipe_price = recipe_price;
	}
	public java.lang.String getRecipe_pic(){
		return recipe_pic;
	}

	public void setRecipe_pic(java.lang.String recipe_pic){
		this.recipe_pic = recipe_pic;
	}
	
}

