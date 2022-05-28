package com.home.viewmodel;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLManager {
	public static void buildCatalogue() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
			
			Document doc = builder.newDocument();
			
			Element root = doc.createElement("BookCatalogue");
			
			doc.appendChild(root);
			
			DOMSource source = new DOMSource(doc);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			StreamResult file = new StreamResult(new File("src/Books.xml"));
			
			transformer.transform(source, file);
			
			//System.out.println("XML is created");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void addBook(Book book) {
		String filePath = "src/Books.xml";
		File file = new File(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
			
			NodeList list = doc.getElementsByTagName("BookCatalogue");
			Element root = (Element) list.item(0);
			
			root.appendChild(getBook(doc, book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName(),
					book.getPages(), book.getDescription(), book.getRating()));
			
			doc.getDocumentElement().normalize();
			
			DOMSource source = new DOMSource(doc);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			StreamResult result = new StreamResult(new File("src/Books.xml"));
			
			transformer.transform(source, result);
			
			//System.out.println("Book is added");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void editBookById(Book book, int id) {
		String filePath = "src/Books.xml";
		File file = new File(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
						
			NodeList mainList = doc.getElementsByTagName("BookCatalogue");
			Element root = (Element) mainList.item(0);
			
			NodeList list = root.getChildNodes();
						
			for(int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);
				if(Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent()) == id) {
					el.getElementsByTagName("id").item(0).setTextContent(Integer.toString(book.getId()));
					el.getElementsByTagName("title").item(0).setTextContent(book.getTitle());
					el.getElementsByTagName("author").item(0).setTextContent(book.getAuthor().getName());
					break;
				}
			}
			
			doc.getDocumentElement().normalize();
			
			DOMSource source = new DOMSource(doc);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			StreamResult result = new StreamResult(new File("src/Books.xml"));
			
			transformer.transform(source, result);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void removeBook(Book book) {
		String filePath = "src/Books.xml";
		File file = new File(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
						
			NodeList mainList = doc.getElementsByTagName("BookCatalogue");
			Element root = (Element) mainList.item(0);
			
			NodeList list = root.getChildNodes();
			
			boolean deleted = false;
						
			for(int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);
				
				if(deleted) {
					int id = Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent());
					el.getElementsByTagName("id").item(0).setTextContent(Integer.toString(id - 1));
				}
				
				if(Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent()) == book.getId() && !deleted) {
					root.removeChild(el);
					deleted = true;
					i--;
				}
				
			}
			
			doc.getDocumentElement().normalize();
			
			DOMSource source = new DOMSource(doc);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			StreamResult result = new StreamResult(new File("src/Books.xml"));
			
			transformer.transform(source, result);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static Node getBook(Document doc, int id, String title, String author, String genre, int pages, String description, int rating) {
		Element book = doc.createElement("Book");
		
		book.appendChild(getElement(doc, "id", String.valueOf(id)));
		book.appendChild(getElement(doc, "title", title));
		book.appendChild(getElement(doc, "author", author));
		book.appendChild(getElement(doc, "genre", genre));
		book.appendChild(getElement(doc, "pages", String.valueOf(pages)));
		book.appendChild(getElement(doc, "description", description));
		book.appendChild(getElement(doc, "rating", String.valueOf(rating)));
		
		return book;
	}
	
	public static Element getElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}
