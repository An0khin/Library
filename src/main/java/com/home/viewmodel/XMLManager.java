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
//		this.filePath = new File(directory + File.separator + "data.xml");
		this.filePath = directory;
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
				
				Element root = doc.createElement("DATABASE");
				
				Element catalogue = doc.createElement("BookCatalogue");
				Element genres = doc.createElement("Genres");
				
				root.appendChild(catalogue);
				root.appendChild(genres);
				
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
	
	public List<Book> getListBook() {
		List<Book> listBooks = new ArrayList<Book>();
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filePath);
			doc.getDocumentElement().normalize();
			
			
			NodeList mainList = doc.getElementsByTagName("DATABASE");
			NodeList bookList = ((Element) mainList.item(0)).getElementsByTagName("BookCatalogue");
			Element root = (Element) bookList.item(0);
			
			NodeList list = root.getChildNodes();
			
			int rating, id, pages;
			String title, author, description, genre, url;
			File address;
									
			for(int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);
				id = Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent());
				title = el.getElementsByTagName("title").item(0).getTextContent();
				author = el.getElementsByTagName("author").item(0).getTextContent();
				genre = el.getElementsByTagName("genre").item(0).getTextContent();
				pages = Integer.parseInt(el.getElementsByTagName("pages").item(0).getTextContent());
				description = el.getElementsByTagName("description").item(0).getTextContent();
				rating = Integer.parseInt(el.getElementsByTagName("rating").item(0).getTextContent());
				
				String adr = el.getElementsByTagName("address").item(0).getTextContent();
				if(adr.isEmpty()) {
					address = null;
				} else {
					address = new File(adr);
				}
				
				url = el.getElementsByTagName("url").item(0).getTextContent();
				
				listBooks.add(BookManager.createBook(id, title, author, genre, pages, rating, description, address, url));
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
			
			NodeList mainList = doc.getElementsByTagName("DATABASE");
			NodeList bookList = ((Element) mainList.item(0)).getElementsByTagName("BookCatalogue");
			Element root = (Element) bookList.item(0);
			
			root.appendChild(getBook(doc, book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(),
					book.getPages(), book.getDescription(), book.getRating(), book.getFile(), book.getUrl()));
			
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
						
			NodeList mainList = doc.getElementsByTagName("DATABASE");
			NodeList bookList = ((Element) mainList.item(0)).getElementsByTagName("BookCatalogue");
			Element root = (Element) bookList.item(0);
			
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
					el.getElementsByTagName("url").item(0).setTextContent(book.getUrl());
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
						
			NodeList mainList = doc.getElementsByTagName("DATABASE");
			NodeList bookList = ((Element) mainList.item(0)).getElementsByTagName("BookCatalogue");
			Element root = (Element) bookList.item(0);
			
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
	
	private static Node getBook(Document doc, int id, String title, String author, String genre, int pages, String description, int rating, File address, String url) {
		Element book = doc.createElement("Book");
		
		book.appendChild(getElement(doc, "id", String.valueOf(id)));
		book.appendChild(getElement(doc, "title", title));
		book.appendChild(getElement(doc, "author", author));
		book.appendChild(getElement(doc, "genre", genre));
		book.appendChild(getElement(doc, "pages", String.valueOf(pages)));
		book.appendChild(getElement(doc, "description", description));
		book.appendChild(getElement(doc, "rating", String.valueOf(rating)));
		book.appendChild(getElement(doc, "address", BookManager.getAddress(address)));
		book.appendChild(getElement(doc, "url", url));
		
		return book;
	}
	
	public List<String> getGenresList() {
		List<String> listGenres = new ArrayList<>();
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filePath);
			doc.getDocumentElement().normalize();
			
			NodeList mainList = doc.getElementsByTagName("DATABASE");
			NodeList genresList = ((Element) mainList.item(0)).getElementsByTagName("Genres");
			Element root = (Element) genresList.item(0);
			
			NodeList list = root.getChildNodes();
			
			String title;
									
			for(int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);
				title = el.getElementsByTagName("title").item(0).getTextContent();
							
				listGenres.add(title);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return listGenres;
	}
	
	public void addGenre(String genre) {
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filePath);
			doc.getDocumentElement().normalize();
			
			Element mainList = (Element) doc.getElementsByTagName("DATABASE").item(0);
			Element root = (Element) mainList.getElementsByTagName("Genres").item(0);
			
			Element genreEl = doc.createElement("Genre");
			genreEl.appendChild(getElement(doc, "title", genre));
			
			root.appendChild(genreEl);
			
			doc.getDocumentElement().normalize();
			
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(filePath);
			
			transformer.transform(source, result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mergeXML(File openXml) {
		try {
			builder = factory.newDocumentBuilder();
			Document docOpen = builder.parse(openXml);
			Document doc = builder.parse(filePath);
			
			Element rootOpen = (Element) docOpen.getElementsByTagName("DATABASE").item(0);
			Element root = (Element) doc.getElementsByTagName("DATABASE").item(0);
				
			Element catalogueOpen = (Element) rootOpen.getElementsByTagName("BookCatalogue").item(0);
			Element catalogue = (Element) root.getElementsByTagName("BookCatalogue").item(0);
				
			NodeList booksOpen = catalogueOpen.getChildNodes();
			NodeList books = catalogue.getChildNodes();
			
			int nextId = 0;
			
			if(books.getLength() > 0)
				nextId = Integer.parseInt(books.item(books.getLength() - 1).getFirstChild().getTextContent()) + 1;
			
			int pages, rating;
			String title, author, genre, description, url;
			File address;
			
			for(int i = 0; i < booksOpen.getLength(); i++) {
				Element el = (Element) booksOpen.item(i);
				title = el.getElementsByTagName("title").item(0).getTextContent();
				author = el.getElementsByTagName("author").item(0).getTextContent();
				genre = el.getElementsByTagName("genre").item(0).getTextContent();
				pages = Integer.parseInt(el.getElementsByTagName("pages").item(0).getTextContent());
				description = el.getElementsByTagName("description").item(0).getTextContent();
				rating = Integer.parseInt(el.getElementsByTagName("rating").item(0).getTextContent());
				
				String adr = el.getElementsByTagName("address").item(0).getTextContent();
				if(adr.isEmpty()) {
					address = null;
				} else {
					address = new File(adr);
				}
				
				url = el.getElementsByTagName("url").item(0).getTextContent(); 
				
				catalogue.appendChild(getBook(doc, nextId++, title, author, genre, pages, description, rating, address, url));
			}
			
			Element genresCatalogueOpen = (Element) rootOpen.getElementsByTagName("Genres").item(0);
			Element genresCatalogue = (Element) root.getElementsByTagName("Genres").item(0);
			
			NodeList genres = genresCatalogueOpen.getChildNodes();
			
			for(int i = 0; i < genres.getLength(); i++) {
				Element el = (Element) genres.item(i);
				title = el.getElementsByTagName("title").item(0).getTextContent();
				
				if(!getGenresList().contains(title)) {
					el = doc.createElement("Genre");
					el.appendChild(getElement(doc, "title", title));
					
					genresCatalogue.appendChild(el);
				}
			}
			
			DOMSource source = new DOMSource(doc);
								
			StreamResult file = new StreamResult(filePath);
				
			transformer.transform(source, file);
//				
//			System.out.println("XML is created");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}	
			
	private static Element getElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}
