package com.home.viewmodel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

import com.home.model.Book;

public class XMLManager {
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Transformer transformer;
	File filePath;
	
	XMLManager(File directory) {
		this.filePath = new File(directory + File.separator + "books.xml");
		factory = DocumentBuilderFactory.newInstance();
		
		buildCatalogue();
	}
	
	private void buildCatalogue() {		
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			builder = factory.newDocumentBuilder();
			
			if(filePath.exists()) {
				System.out.println("XML is parsed");
			} else {
				Document doc = builder.newDocument();
				
				Element root = doc.createElement("BookCatalogue");
				
				doc.appendChild(root);
				
				DOMSource source = new DOMSource(doc);
								
				StreamResult file = new StreamResult(filePath);
				
				transformer.transform(source, file);
				
				System.out.println("XML is created");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Book> getList() {
		List<Book> listBooks = new ArrayList<Book>();
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filePath);
			doc.getDocumentElement().normalize();
						
			NodeList mainList = doc.getElementsByTagName("BookCatalogue");
			Element root = (Element) mainList.item(0);
			
			NodeList list = root.getChildNodes();
			
			int rating;
			String title, author, description, pages, genre;
			File address;
									
			for(int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);
				title = el.getElementsByTagName("title").item(0).getTextContent();
				author = el.getElementsByTagName("author").item(0).getTextContent();
				genre = el.getElementsByTagName("genre").item(0).getTextContent();
				pages = el.getElementsByTagName("pages").item(0).getTextContent();
				description = el.getElementsByTagName("description").item(0).getTextContent();
				rating = Integer.parseInt(el.getElementsByTagName("rating").item(0).getTextContent());
				
				String adr = el.getElementsByTagName("address").item(0).getTextContent();
				if(adr.isEmpty()) {
					address = null;
				} else {
					address = new File(adr);
				}
				
				listBooks.add(BookManager.createBook(title, author, genre, pages, rating, description, address));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return listBooks;
	}
	
	public void addBook(Book book) {
//		String filePath = "src/Books.xml";
//		File file = new File(directory + File.separator + "Books.xml");
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder;
		
		try {
//			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filePath);
			doc.getDocumentElement().normalize();
			
			NodeList list = doc.getElementsByTagName("BookCatalogue");
			Element root = (Element) list.item(0);
			
			root.appendChild(getBook(doc, book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(),
					book.getPages(), book.getDescription(), book.getRating(), book.getFile()));
			
			doc.getDocumentElement().normalize();
			
			DOMSource source = new DOMSource(doc);
			
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
			
			StreamResult result = new StreamResult(filePath);
			
			transformer.transform(source, result);
			
			//System.out.println("Book is added");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void editBook(Book book) {
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filePath);
			doc.getDocumentElement().normalize();
						
			NodeList mainList = doc.getElementsByTagName("BookCatalogue");
			Element root = (Element) mainList.item(0);
			
			NodeList list = root.getChildNodes();
			
			int id = book.getId();
						
			for(int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);
				if(Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent()) == id) {
					el.getElementsByTagName("id").item(0).setTextContent(Integer.toString(id));
					el.getElementsByTagName("title").item(0).setTextContent(book.getTitle());
					el.getElementsByTagName("author").item(0).setTextContent(book.getAuthor());
					el.getElementsByTagName("genre").item(0).setTextContent(book.getGenre());
					el.getElementsByTagName("pages").item(0).setTextContent(Integer.toString(book.getPages()));
					el.getElementsByTagName("description").item(0).setTextContent(book.getDescription());
					el.getElementsByTagName("rating").item(0).setTextContent(Integer.toString(book.getRating()));
					el.getElementsByTagName("address").item(0).setTextContent(BookManager.getAddress(book.getFile()));
					break;
				}
			}
			
			doc.getDocumentElement().normalize();
			
			DOMSource source = new DOMSource(doc);
						
			StreamResult result = new StreamResult(filePath);
			
			transformer.transform(source, result);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void removeBook(Book book) {		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filePath);
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
				} else {
					if(Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent()) == book.getId()) {
						root.removeChild(el);
						deleted = true;
						i--;
					}
				}				
			}
			
			doc.getDocumentElement().normalize();
			
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(filePath);
			
			transformer.transform(source, result);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
		
	private static Node getBook(Document doc, int id, String title, String author, String genre, int pages, String description, int rating, File address) {
		Element book = doc.createElement("Book");
		
		book.appendChild(getElement(doc, "id", String.valueOf(id)));
		book.appendChild(getElement(doc, "title", title));
		book.appendChild(getElement(doc, "author", author));
		book.appendChild(getElement(doc, "genre", genre));
		book.appendChild(getElement(doc, "pages", String.valueOf(pages)));
		book.appendChild(getElement(doc, "description", description));
		book.appendChild(getElement(doc, "rating", String.valueOf(rating)));
		book.appendChild(getElement(doc, "address", BookManager.getAddress(address)));
		
		return book;
	}
	
	private static Element getElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}
