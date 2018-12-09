package cz.nigol.zpravodaj.services.impl;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cz.nigol.zpravodaj.config.Configuration;
import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.enums.Category;
import cz.nigol.zpravodaj.services.ArticleService;

@Stateless
public class ArticleServiceImpl implements ArticleService {
    @PersistenceContext(unitName="zpravodajPU")
    private EntityManager em;
    @Resource
    private Configuration configuration;
    @Inject
    private Log log;
    
    @Override
    public List<Article> getAllArticles() {
	TypedQuery<Article> typedQuery = em.createNamedQuery(Article.GET_ALL, Article.class);
	return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public Article getArticleById(String id) {
	return em.find(Article.class, id);
    }

    @Override
    public Article saveArticle(Article article, String body) {
	Article result = em.merge(article);
	result.setBody(body);
	return result;
    }

    @Override
    public Article loadArticleBody(Article article) {
	Article result = em.find(Article.class, article.getId());
	result.setBody(result.getBody());
	return result;
    }

    @Override
    public List<Article> getArticlesByUser(User user) {
	TypedQuery<Article> typedQuery = em.createNamedQuery(Article.GET_BY_USER, Article.class);
	typedQuery.setParameter(Article.USER_PARAM, user);
	return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public void deleteArticle(Article article) {
	em.remove(em.merge(article));
    }

    @Override
    public List<Article> getPublishedArticles() {
	TypedQuery<Article> typedQuery = em.createNamedQuery(Article.GET_PUBLISHED, Article.class);
	return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public Article getFeaturedArticle() {
	TypedQuery<Article> typedQuery = em.createNamedQuery(Article.GET_FEATURED, Article.class);
	List<Article> articles = typedQuery.getResultList();
	return articles.isEmpty() ? null : articles.get(0);
    }

    @Override
    public List<Article> getArticlesByCategory(Category category) {
	TypedQuery<Article> typedQuery = em.createNamedQuery(Article.GET_BY_CATEGORY, Article.class);
	typedQuery.setParameter(Article.CATEGORY_PARAM, category);
	return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public void generateRss(OutputStream outputStream) {
	DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	try {
	    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	    Document document = documentBuilder.newDocument();
	    prepareRssDocument(document);
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(document);
	    StreamResult result = new StreamResult(outputStream);
	    transformer.transform(source, result);
	} catch (ParserConfigurationException | TransformerException  e) {
	    log.error(e);
	}
    }

    private void prepareRssDocument(Document document) {
	Element rootElement = document.createElement("rss");
	rootElement.setAttribute("version", "2.0");
	Element element = document.createElement("channel");
	rootElement.appendChild(element);
	prepareRssHeader(element, document);
	prepareRssItems(element, document);
	document.appendChild(rootElement);
    }

    private void prepareRssItems(Element root, Document document) {
	getPublishedArticles().stream()
	    .forEach(a -> {
		    Element element = document.createElement("item");
		    try {
			prepareOneRssItem(element, document, a);
		    } catch (DOMException | UnsupportedEncodingException e) {
			log.error(e);
		    }
		    root.appendChild(element);
		});
    }

    private void prepareOneRssItem(Element root, Document document, Article article)
	throws DOMException, UnsupportedEncodingException {
	Element element = document.createElement("title");
	element.appendChild(document.createTextNode(article.getLabel()));
	root.appendChild(element);
	element = document.createElement("link");
	String articleUrl = configuration.getUrl() + "clanek.jsf?id=" +	URLEncoder.encode(article.getId(), "UTF-8");
	element.appendChild(document.createTextNode(articleUrl));
	root.appendChild(element);
	element = document.createElement("guid");
	element.appendChild(document.createTextNode(articleUrl));
	root.appendChild(element);
	element = document.createElement("description");
	element.appendChild(document.createTextNode(article.getLeadParagraph()));
	root.appendChild(element);
	element = document.createElement("pubDate");
	DateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy");
	element.appendChild(document.createTextNode(formatter.format(article.getPublishedAt()) + " 00:00:00 GMT"));
	root.appendChild(element);
    }

    private void prepareRssHeader(Element root, Document document) {
	Element element = document.createElement("title");
	element.appendChild(document.createTextNode("Tršický Zpravodaj"));
	root.appendChild(element);
	element = document.createElement("link");
	element.appendChild(document.createTextNode(configuration.getUrl()));
	root.appendChild(element);
	element = document.createElement("description");
	element.appendChild(document.createTextNode("Tršický Zpravodaj. Periodický tisk územního samosprávného celku. Obec Tršice."));
	root.appendChild(element);
    }
}
