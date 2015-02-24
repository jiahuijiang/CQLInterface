package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import tables.Attribute;
import tables.BasicEntity;
import tables.Entity;
import tables.Relationship;

public class Reader {

	// TODO: instead of simply keywords, we can ask the users to type patterns
	
	
	public void createTable(HashMap<String, Entity> entities, HashMap<String, BasicEntity> basicEntities){
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String entityTypeName;
    	String basicType;
    	String name;
    	Set<String> contexts = new HashSet<String>();
    	HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
    	
    	try {
	    	while(true){
	    		System.out.println("What's the name of the new entity?");
				entityTypeName = br.readLine();
				entityTypeName = entityTypeName.trim().toUpperCase();
				
				if(entityTypeName == null || entityTypeName.length() == 0 || Pattern.matches("[a-z]+", entityTypeName)==false){
					System.out.println("ERROR: Please enter a valid string.");
				}else if(entities.containsKey(entityTypeName) || basicEntities.containsKey(entityTypeName)){
					System.out.println("ERROR: Entity name already exists.");
				}else{
					break;
				}
			}
    	
	    	while(true){
	    		System.out.println("What's the basic entity type?");
	    		basicType = br.readLine().toUpperCase();
				if(entities.containsKey(basicType) == false && basicEntities.containsKey(basicType) == false){
					System.out.println("ERROR: Basic entity type doesn't exist.");
				}else{
					if(entities.containsKey(basicType)){
						Entity basicTypeEntity = entities.get(basicType);
							
						name = basicTypeEntity.getName();
						contexts.addAll(basicTypeEntity.getContexts());
						attributes.putAll(basicTypeEntity.getAllAttributes());
					}else{
						BasicEntity basicTypeEntity = basicEntities.get(basicType);
							
						name = basicTypeEntity.getName();
						contexts.add(basicTypeEntity.getContext());
					}
					break;
				}
			} 
	    	
	    	System.out.println("What are the keywords you want to use to describe the difference between " + entityTypeName + " and " + basicType + "?");
	    	String context = br.readLine().toUpperCase();
	    	contexts.add(context);
	    	
	    	while(true){
	    		System.out.println("Do you want to add an attribute? (y,n)");
	    		String option = br.readLine().toLowerCase();
	    		
	    		if(option.equals("y")){
	    			
	    			String attributeName;
	    			String basicAttributeType;
	    			String attributeContext;
	    			
	    			while(true){
	    				System.out.println("What's the name of your attribute?");
	    				attributeName = br.readLine().trim().toUpperCase();
	    				if(attributeName == null || attributeName.length() == 0){
	    					System.out.println("ERROR: Please enter a valid string.");
	    				}else if(attributeName.equals(name)){
	    					System.out.println("ERROR: The same as the key attribute");
	    				}else{
	    					break;
	    				}
	    			}
	    			
	    			while(true){
	    				System.out.println("What's the basic type of your attribute?");
	    				basicAttributeType = br.readLine().trim().toUpperCase();
	    				if(basicEntities.containsKey(basicAttributeType)==false){
	    					System.out.println("ERROR: Basic entity type doesn't exist.");
	    				}else{
	    					break;
	    				}
	    			}
	    			
	    			System.out.println("What are the keywords you want to use to describe the relationship between " + entityTypeName + " and " + attributeName + "?");
	    	    	attributeContext = br.readLine().toUpperCase();
	    	    	
	    	    	Attribute attribute = new Attribute(attributeName, basicAttributeType, attributeContext);
	    	    	attributes.put(attributeName, attribute);
	    			
	    		}else if(option.equals("n")){
	    			break;
	    		}else{
		    		System.out.println("Invalid ouput.");
	    		}
	    	}
	    	
	    	Entity newEntity = new Entity(entityTypeName, basicType, name, contexts, attributes);
	    	entities.put(entityTypeName, newEntity);
	    	
	    }catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void createRelationship(HashMap<String, Relationship> relationships, HashMap<String, Entity> entities, HashMap<String, BasicEntity> basicEntities){
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String relationshipTypeName;
    	String entity1Name;
    	String entity1Type;
    	String entity2Name;
    	String entity2Type;
    	String context;
    	
    	try {
	    	while(true){
	    		System.out.println("What's the name of the new relationship?");
	    		relationshipTypeName = br.readLine();
	    		relationshipTypeName = relationshipTypeName.trim().toUpperCase();
				
				if(relationshipTypeName == null || relationshipTypeName.length() == 0 || Pattern.matches("[a-z]+", relationshipTypeName)==false){
					System.out.println("ERROR: Please enter a valid string.");
				}else if(relationships.containsKey(relationshipTypeName)){
					System.out.println("ERROR: Relationship name already exists.");
				}else{
					break;
				}
			}
	    	
	    	while(true){
	    		System.out.println("What's the name of the firs entity?");
	    		entity1Name = br.readLine();
	    		entity1Name = entity1Name.trim().toUpperCase();
				
				if(entity1Name == null || entity1Name.length() == 0 || Pattern.matches("[a-z]+", entity1Name)==false){
					System.out.println("ERROR: Please enter a valid string.");
				}else{
					break;
				}
			}
	    	
	    	while(true){
	    		System.out.println("What's the entity type of" + entity1Name + "?");
	    		entity1Type = br.readLine().toUpperCase();
				if(entities.containsKey(entity1Type) == false && basicEntities.containsKey(entity1Type) == false){
					System.out.println("ERROR: entity type doesn't exist.");
				}else{
					break;
				}
			} 
	    	
	    	while(true){
	    		System.out.println("What's the name of the firs entity?");
	    		entity2Name = br.readLine();
	    		entity2Name = entity2Name.trim().toUpperCase();
				
				if(entity2Name == null || entity2Name.length() == 0 || Pattern.matches("[a-z]+", entity2Name)==false){
					System.out.println("ERROR: Please enter a valid string.");
				}else if(entity1Name.equals(entity2Name)){
					System.out.println("ERROR: Entity 2 can't have the same name as Entity 1.");
				}else{
					break;
				}
			}
	    	
	    	while(true){
	    		System.out.println("What's the entity type of" + entity2Name + "?");
	    		entity2Type = br.readLine().toUpperCase();
				if(entities.containsKey(entity2Type) == false && basicEntities.containsKey(entity2Type) == false){
					System.out.println("ERROR: entity type doesn't exist.");
				}else{
					break;
				}
			} 
	    	
	    	System.out.println("What are the keywords you want to use to describe the relationship between " + entity1Name + " and " + entity2Name + "?");
	    	context = br.readLine().toUpperCase();
	    	
	    	Relationship relationship = new Relationship(relationshipTypeName, entity1Name, entity1Type, entity2Name, entity2Type, context);
	    	relationships.put(relationshipTypeName, relationship);
	    	
	    }catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void searchQuery(HashMap<String, Relationship> relationships, HashMap<String, Entity> entities, HashMap<String, BasicEntity> basicEntities){
		
		while(true){
			System.out.println("Please type your query. End it with a '$'.");
			
			
			
		}
		
	}
	
	
}